package com.example.devnews.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.devnews.R;
import com.example.devnews.adapter.MainArticleAdapter;
import com.example.devnews.model.Article;
import com.example.devnews.rests.ApiClient;
import com.example.devnews.rests.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsOfTagFragment extends Fragment {
    private SearchView searchView;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_of_tag,null);
        searchView = view.findViewById(R.id.fn_tag_search_view);
        recyclerView = view.findViewById(R.id.fn_tag_rv);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Article>> call = apiService.getArticles();
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                Log.d("response", String.valueOf(response.body()));
                List<Article> articleList = response.body();
                MainArticleAdapter mainArticleAdapter = new MainArticleAdapter(articleList, getContext());
                recyclerView.setAdapter(mainArticleAdapter);
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<List<Article>> call = apiService.getArticlesOfTag(searchView.getQuery().toString());
                call.enqueue(new Callback<List<Article>>() {
                    @Override
                    public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                        Log.d("response", String.valueOf(response.body()));
                        List<Article> articleList = response.body();
                        MainArticleAdapter mainArticleAdapter = new MainArticleAdapter(articleList, getContext());
                        recyclerView.setAdapter(mainArticleAdapter);
                    }

                    @Override
                    public void onFailure(Call<List<Article>> call, Throwable t) {
                    }
                });
                return false;
            }

        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static NewsOfTagFragment newInstance() {

        Bundle args = new Bundle();

        NewsOfTagFragment fragment = new NewsOfTagFragment();
        fragment.setArguments(args);
        return fragment;
    }

}
