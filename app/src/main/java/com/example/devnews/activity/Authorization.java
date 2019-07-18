package com.example.devnews.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.devnews.MainActivity;
import com.example.devnews.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Authorization extends AppCompatActivity{
    private FloatingActionButton addAcount;
    private Button signIn;
    private TextInputEditText login;
    private TextInputEditText password;
    public static final String LOGIN_USER = "login_user";
    public static final String PASS_USER = "pass_user";
    public static final String MY_PREFERENCES = "mysettings";
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autorization);
        sharedPreferences = getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        login = findViewById(R.id.text_input_username_autorization);
        password = findViewById(R.id.text_input_pass_autorizathion);
        addAcount = findViewById(R.id.fab_add_user);
        addAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),Registration.class));
            }
        });
        signIn = findViewById(R.id.btn_sign_in);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("onclick","clicked");
                Log.d("onclick",sharedPreferences.getString(MainActivity.LOGIN_ACCOUNT,""));
                Log.d("onclick",sharedPreferences.getString(MainActivity.PASSWORD_ACCOUNT,""));
                if ((login.getText().toString().equals(sharedPreferences.getString(MainActivity.LOGIN_ACCOUNT,"")))
                        && (password.getText().toString().equals(sharedPreferences.getString(MainActivity.PASSWORD_ACCOUNT,"")))){
                    Log.d("onclick","equals");
                    SharedPreferences.Editor  editor = sharedPreferences.edit();
                    editor.putString(LOGIN_USER,login.getText().toString());
                    editor.putString(PASS_USER,password.getText().toString());
                    editor.apply();
                    finish();
                }  else {
                    Log.d("onclick","not equals");
                    Toast.makeText(getBaseContext(), "Login or password not correct !", Toast.LENGTH_SHORT).show();
                }
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
