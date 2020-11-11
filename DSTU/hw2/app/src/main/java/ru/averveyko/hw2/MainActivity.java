package ru.averveyko.hw2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editMsg = findViewById(R.id.edit_msg);
        Button butSend = findViewById(R.id.but_send);

        butSend.setOnClickListener(v -> {
            String txt = editMsg.getText().toString();

            if (txt.isEmpty()) {
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
                dlgAlert.setMessage(R.string.str_put_msg_txt_err);
                dlgAlert.setTitle(R.string.app_name);
                dlgAlert.setPositiveButton(android.R.string.ok, null);
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();
                return;
            }

            Intent intent = new Intent(this, SecondActivity.class);
            intent.putExtra("txt", txt);
            startActivity(intent);
        });
    }
}