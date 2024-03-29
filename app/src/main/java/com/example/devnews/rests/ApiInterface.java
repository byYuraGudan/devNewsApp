package com.example.devnews.rests;

import com.example.devnews.model.Article;
import com.example.devnews.model.UserInformation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("articles")
    Call<List<Article>>  getArticles();

    @GET("articles")
    Call<List<Article>> getArticlesOfTag(@Query("tag")String tag);

    @GET("users/by_username")
    Call<UserInformation> getUserOfUrl(@Query("url")String url);
 }
