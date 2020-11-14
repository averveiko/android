package ru.averveyko.hw3;

import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Button;
import android.widget.Chronometer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;

    private long timeElapsed;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v("keash", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chronometer = findViewById(R.id.chronometer);

        Button butStart = findViewById(R.id.but_start);
        Button butStop = findViewById(R.id.but_stop);
        Button butReset = findViewById(R.id.but_reset);

        if (savedInstanceState != null
                && savedInstanceState.containsKey("timeElapsed")
                && savedInstanceState.containsKey("isRunning")
        ) {
            timeElapsed = savedInstanceState.getLong("timeElapsed");
            isRunning = savedInstanceState.getBoolean("isRunning");
            chronometer.setBase(SystemClock.elapsedRealtime() - timeElapsed);
        }

        butStart.setOnClickListener(v -> {
            if (!isRunning) {
                startChronometer();
            }
            isRunning = true;
        });

        butStop.setOnClickListener(v -> {
            if (isRunning) {
                stopChronometer(SystemClock.elapsedRealtime() - chronometer.getBase());
            }
            isRunning = false;
        });

        butReset.setOnClickListener(v -> {
            isRunning = false;
            resetChronometer();
        });
    }

    private void startChronometer() {
        if (timeElapsed == 0) {
            chronometer.setBase(SystemClock.elapsedRealtime());
        } else {
            chronometer.setBase(SystemClock.elapsedRealtime() - timeElapsed);
        }
        chronometer.start();
    }

    private void resetChronometer() {
        stopChronometer(0);
        chronometer.setBase(SystemClock.elapsedRealtime());
    }

    private void stopChronometer(long timeElapsed) {
        chronometer.stop();
        this.timeElapsed = timeElapsed;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isRunning) {
            stopChronometer(SystemClock.elapsedRealtime() - chronometer.getBase());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isRunning) {
            startChronometer();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("timeElapsed", timeElapsed);
        outState.putBoolean("isRunning", isRunning);
    }
}