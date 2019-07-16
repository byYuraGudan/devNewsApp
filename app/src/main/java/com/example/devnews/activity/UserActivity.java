package com.example.devnews.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.devnews.R;
import com.example.devnews.model.Article;
import com.example.devnews.model.UserInformation;
import com.example.devnews.rests.ApiClient;
import com.example.devnews.rests.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    private ImageButton imageProfile;
    private TextView username;
    private TextView summary;
    private String username_url;
    public static final String USER_URL = "user_url";
    private UserInformation user = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        imageProfile = findViewById(R.id.user_profile_photo);
        username = findViewById(R.id.user_profile_name);
        summary = findViewById(R.id.user_profile_summary);
        Intent intent = getIntent();
        Log.d("qwe",intent.getStringExtra(USER_URL));
        if (intent.getStringExtra(USER_URL)!= null) {
                username_url = intent.getStringExtra(USER_URL);
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<UserInformation> call = apiService.getUserOfUrl(username_url);
                call.enqueue(new Callback<UserInformation>() {
                    @Override
                    public void onResponse(Call<UserInformation> call, Response<UserInformation> response) {
                        Log.d("userlog", String.valueOf(response.body()));
                        user = response.body();
                        username.setText(user.getName());
                        summary.setText(user.getSummary());
                        Glide.with(getBaseContext())
                                .load(user.getProfileImage())
                                .circleCrop()
                                .into(imageProfile);
                    }

                    @Override
                    public void onFailure(Call<UserInformation> call, Throwable t) {

                    }
                });
        }

    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
            return super.onCreateView(name, context, attrs);

    }

}
