package ru.averveyko.hw5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        TextView editTitle = findViewById(R.id.edit_title);
        TextView editDescription = findViewById(R.id.edit_description);
        TextView editDate = findViewById(R.id.edit_date);
        Button butAddTask = findViewById(R.id.but_add_task);
        Button butCancel = findViewById(R.id.but_cancel);

        butAddTask.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra(MainActivity.EXTRA_TITLE, editTitle.getText().toString());
            intent.putExtra(MainActivity.EXTRA_DESCRIPTION, editDescription.getText().toString());
            intent.putExtra(MainActivity.EXTRA_DATE, editDate.getText().toString());
            setResult(RESULT_OK, intent);
            finish();
        });

        butCancel.setOnClickListener(v -> {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
        });
    }
}