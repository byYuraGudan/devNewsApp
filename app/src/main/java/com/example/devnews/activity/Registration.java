package com.example.devnews.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.devnews.MainActivity;
import com.example.devnews.R;
import com.google.android.material.textfield.TextInputEditText;

public class Registration extends AppCompatActivity {
    private Button getStarted;
    private TextInputEditText login;
    private TextInputEditText password;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        sharedPreferences = getSharedPreferences(Authorization.MY_PREFERENCES, Context.MODE_PRIVATE);
        getStarted = findViewById(R.id.btn_register);
        login = findViewById(R.id.text_input_username_register);
        password = findViewById(R.id.text_input_password_register);
        getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString(MainActivity.LOGIN_ACCOUNT,login.getText().toString());
                edit.putString(MainActivity.PASSWORD_ACCOUNT,password.getText().toString());
                edit.apply();
                finish();
            }
        });
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
