package com.example.justnews.Repositry;

import com.example.justnews.APIS.APIManger;
import com.example.justnews.APIS.Model.NewsModel.ArticlesItem;
import com.example.justnews.APIS.Model.NewsModel.NewsResponse;
import com.example.justnews.APIS.Model.SourcesItem;
import com.example.justnews.APIS.Model.SourcesResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepositry {

    protected static String apikey = "210e05cc301d4ee08b6ea10a065f0c99";
    protected String lang;

    public NewsRepositry(String lang) {
        this.lang = lang;
    }

       public void getSourcesnamefromApi(final onNewsSourcesPreperedListener onNewsSourcesPreperedListener) {
        APIManger.getApis()
                .getNewsSources(lang,apikey)
                .enqueue(new Callback<SourcesResponse>() {
                    @Override
                    public void onResponse(Call<SourcesResponse> call, Response<SourcesResponse> response) {
                        if (response.isSuccessful()){
                            if (onNewsSourcesPreperedListener != null){
                                onNewsSourcesPreperedListener.onNewsSourcesPrepered(response.body().getSources());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<SourcesResponse> call, Throwable t) {

                    }
                });
    }

    public void getNewsfromApi(String sourceId, final onNewsPreperedListener newsPreperedListener){
        APIManger.getApis()
                .getNews(apikey,lang,sourceId)
                .enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        if (response.isSuccessful()){
                            if (newsPreperedListener != null){
                                newsPreperedListener.onNewsPrepered(response.body().getArticles());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {

                    }
                });
    }

    public interface onNewsSourcesPreperedListener{
        void onNewsSourcesPrepered(List<SourcesItem> sourcesItems);
    }

    public interface onNewsPreperedListener{
        void onNewsPrepered(List<ArticlesItem> articlesItems);

    }
}
