package com.example.devnews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devnews.R;
import com.example.devnews.activity.WebActivity;
import com.example.devnews.adapter.MainArticleAdapter;
import com.example.devnews.model.Article;
import com.example.devnews.rests.ApiClient;
import com.example.devnews.rests.ApiInterface;
import com.example.devnews.utils.OnRecyclerViewItemClickListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends Fragment implements OnRecyclerViewItemClickListener {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private LinearLayoutManager linearLayoutManager;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news,null);
        recyclerView = view.findViewById(R.id.fragment_news_rv);
        toolbar = view.findViewById(R.id.fragment_news_toolbar);
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Article>> call = apiService.getArticles();
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                Log.d("response",String.valueOf(response.body()));
                    List<Article> articleList = response.body();
                    final MainArticleAdapter mainArticleAdapter = new MainArticleAdapter(articleList);
                    mainArticleAdapter.setOnRecyclerViewItemClickListener(NewsFragment.this);
                    recyclerView.setAdapter(mainArticleAdapter);
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {}
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static NewsFragment newInstance() {

        Bundle args = new Bundle();

        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onItemClick(int position, View view) {
        switch (view.getId()){
            case R.id.item_fn_ll_root:
                Article article = (Article) view.getTag();
                if (!TextUtils.isEmpty(article.getUrl())) {
                    Log.e("clicked url", article.getUrl());
                    Intent webActivity = new Intent(view.getContext(), WebActivity.class);
                    webActivity.putExtra("url",article.getUrl());
                    startActivity(webActivity);
                }
                Log.d("ll_root","OnItemClick");
                break;
        }
    }
}
