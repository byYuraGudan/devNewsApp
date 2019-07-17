package com.example.devnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.devnews.fragment.NewsFragment;
import com.example.devnews.fragment.NewsOfTagFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.main_navigation_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        FragmentManager manager = getSupportFragmentManager();
        int id = menuItem.getItemId();
        switch (id){
            case R.id.menu_item_news:
                manager.beginTransaction().replace(R.id.main_root_layout_of_fragment, NewsFragment.newInstance()).commit();
                break;
            case  R.id.menu_item_news_tag:
                manager.beginTransaction().replace(R.id.main_root_layout_of_fragment, NewsOfTagFragment.newInstance()).commit();
                break;
        }
        return false;
    }
}