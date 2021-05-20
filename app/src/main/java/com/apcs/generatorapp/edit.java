package com.apcs.generatorapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class edit extends AppCompatActivity {

    private EditText nameInput;
    private EditText telInput;
    private EditText emailInput;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        prefs = getSharedPreferences("MY_DATA", MODE_PRIVATE);
        String name = prefs.getString("MY_NAME", "");
        Long tel = prefs.getLong("MY_TEL", 0);
        String email = prefs.getString("MY_EMAIL", "");

        nameInput = (EditText)findViewById(R.id.nameInput);
        telInput = (EditText)findViewById(R.id.telInput);
        emailInput = (EditText)findViewById(R.id.emailInput);

        nameInput.setText(name);
        telInput.setText(tel+"");
        emailInput.setText(email);
    }

    public void saveData(View view) {
        String name = nameInput.getText().toString();
        Long tel = Long.parseLong(telInput.getText().toString());
        String email = emailInput.getText().toString();

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("MY_NAME", name);
        editor.putLong("MY_TEL", tel);
        editor.putString("MY_EMAIL", email);
        editor.apply();

        startActivity(new Intent(getApplicationContext(), MainActivity.class));

    }
}
