package ru.averveyko.appone

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class SecondActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_activity)

        val button = findViewById<Button>(R.id.act2_button)
        val edit = findViewById<EditText>(R.id.act2_edit)

        button.setOnClickListener {
            val newText = edit.text.toString()
            val intent = Intent()
            intent.putExtra("txt", newText)
            setResult(0, intent)
            finish()
        }

        val stringExtra = intent.getStringExtra("txt")
        edit.setText(stringExtra)
    }
}