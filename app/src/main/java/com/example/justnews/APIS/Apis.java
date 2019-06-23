package com.example.justnews.APIS;

import com.example.justnews.APIS.Model.NewsModel.ArticlesItem;
import com.example.justnews.APIS.Model.NewsModel.NewsResponse;
import com.example.justnews.APIS.Model.SourcesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Apis {

    @GET("sources")
    Call<SourcesResponse>getNewsSources(@Query("language") String lang,
                                        @Query("apiKey") String apikey);
     @GET("everything")
    Call<NewsResponse> getNews(@Query("apiKey") String apikey,
                               @Query("language") String lang,
                               @Query("sources") String source);
}
