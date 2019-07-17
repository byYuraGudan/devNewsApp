package com.example.devnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        FragmentManager manager = getSupportFragmentManager();
        int id = menuItem.getItemId();
        switch (id){
            case R.id.menu_item_news:
                manager.beginTransaction().replace(R.id.main_root_layout_of_fragment, NewsFragment.newInstance()).commit();
                break;
        }
        return false;
    }
}