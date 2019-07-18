package com.example.devnews.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.devnews.R;
import com.google.android.material.textfield.TextInputEditText;

public class Registration extends AppCompatActivity {
    private Button getStarted;
    private TextInputEditText email;
    private TextInputEditText login;
    private TextInputEditText password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getStarted = findViewById(R.id.btn_register);
        email = findViewById(R.id.text_input_email_register);
        login = findViewById(R.id.text_input_username_register);
        password = findViewById(R.id.text_input_password_register);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().hide();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getSupportActionBar().show();
    }
}
