package com.example.devnews.rests;

import com.example.devnews.model.Article;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("articles")
    Call<List<Article>>  getArticles();
    @GET("articles")
    Call<List<Article>> getArticlesOfTag(@Query("tag")String tag);
 }
