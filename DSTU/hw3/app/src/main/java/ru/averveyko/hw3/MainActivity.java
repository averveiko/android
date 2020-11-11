package ru.averveyko.hw3;

import android.os.Bundle;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    long timeElapsed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Chronometer chronometer = findViewById(R.id.chronometer);

        Button butStart = findViewById(R.id.but_start);
        Button butStop = findViewById(R.id.but_stop);
        Button butReset = findViewById(R.id.but_reset);

        chronometer.setOnChronometerTickListener(listener -> {
            timeElapsed = SystemClock.elapsedRealtime() - chronometer.getBase();
        });

        butStart.setOnClickListener(v -> {
            if (timeElapsed == 0) {
                chronometer.setBase(SystemClock.elapsedRealtime());
            } else {
                chronometer.setBase(SystemClock.elapsedRealtime() - timeElapsed);
            }
            chronometer.start();
        });

        butStop.setOnClickListener(v -> {
            chronometer.stop();
        });

        butReset.setOnClickListener(v -> {
            chronometer.stop();
            timeElapsed = 0;
            chronometer.setBase(SystemClock.elapsedRealtime());
        });
    }
}