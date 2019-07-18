package com.example.devnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.devnews.activity.Authorization;
import com.example.devnews.fragment.NewsFragment;
import com.example.devnews.model.UserInformation;
import com.example.devnews.rests.ApiClient;
import com.example.devnews.rests.ApiInterface;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navigationView;
    private TextView userName;
    private ImageButton imageProfile;
    private SharedPreferences mySharedPreferences;
    public static final String LOGIN_ACCOUNT = "user";
    public static final String PASSWORD_ACCOUNT = "pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mySharedPreferences = getSharedPreferences(Authorization.MY_PREFERENCES, Context.MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.main_navigation_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }

        View view = navigationView.inflateHeaderView(R.layout.navigation_header_image);
        userName = view.findViewById(R.id.header_profile_username);
        imageProfile = view.findViewById(R.id.header_profile_photo);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UserInformation> call = apiService.getUserOfUrl("alfredosalzillo");
        call.enqueue(new Callback<UserInformation>() {
            @Override
            public void onResponse(Call<UserInformation> call, Response<UserInformation> response) {
                UserInformation userInformation = response.body();
                userName.setText(userInformation.getName());
                Glide.with(getBaseContext())
                        .load(userInformation.getProfileImage())
                        .circleCrop().into(imageProfile);
            }

            @Override
            public void onFailure(Call<UserInformation> call, Throwable t) {

            }
        });
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.main_root_layout_of_fragment, NewsFragment.newInstance()).addToBackStack(null).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("logining","onResume");
        if(mySharedPreferences.contains(Authorization.LOGIN_USER)){
            Log.d("logining","exist");
            String user_account = mySharedPreferences.getString(MainActivity.LOGIN_ACCOUNT,"");
            String pass_account = mySharedPreferences.getString(MainActivity.PASSWORD_ACCOUNT,"");
            String user_name = mySharedPreferences.getString(Authorization.LOGIN_USER,"");
            String pass_name = mySharedPreferences.getString(Authorization.PASS_USER,"");
            if (!(user_name.equals(user_account) && pass_name.equals(pass_account))){
                startActivity(new Intent(getApplicationContext(),Authorization.class));
            }}
        else {startActivity(new Intent(getApplicationContext(),Authorization.class));}
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        FragmentManager manager = getSupportFragmentManager();
        int id = menuItem.getItemId();
        switch (id){
            case R.id.menu_item_news:
                manager.beginTransaction().replace(R.id.main_root_layout_of_fragment, NewsFragment.newInstance()).addToBackStack(null).commit();
                break;
            case R.id.menu_item_logout:
                SharedPreferences.Editor edit = mySharedPreferences.edit();
                edit.putString(Authorization.LOGIN_USER,"");
                edit.putString(Authorization.PASS_USER,"");
                edit.apply();
                startActivity(new Intent(getBaseContext(),Authorization.class));
                break;
        }
        return false;
    }
}