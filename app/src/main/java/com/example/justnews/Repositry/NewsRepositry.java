package com.example.justnews.Repositry;

import com.example.justnews.APIS.APIManger;
import com.example.justnews.APIS.Model.NewsModel.ArticlesItem;
import com.example.justnews.APIS.Model.NewsModel.NewsResponse;
import com.example.justnews.APIS.Model.SourcesItem;
import com.example.justnews.APIS.Model.SourcesResponse;
import com.example.justnews.DatabaseUtil.NewsDataBaseManger;

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
                                // save into database
                                InsertSourcesInDataBase insert_thread = new InsertSourcesInDataBase(response.body().getSources());
                                insert_thread.start();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<SourcesResponse> call, Throwable t) {
                            // get from database
                        GetSourcesFromDataBase get_thread = new GetSourcesFromDataBase(onNewsSourcesPreperedListener);
                        get_thread.start();
                    }
                });
    }

    public void getNewsfromApi(final String sourceId, final onNewsPreperedListener newsPreperedListener){
        APIManger.getApis()
                .getNews(apikey,lang,sourceId)
                .enqueue(new Callback<NewsResponse>() {
                    @Override
                    public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                        if (response.isSuccessful()){
                            if (newsPreperedListener != null){
                                newsPreperedListener.onNewsPrepered(response.body().getArticles());
                                // save into database
                                  InsertNewsInDataBase insertnewsthread = new InsertNewsInDataBase(response.body().getArticles());
                                  insertnewsthread.start();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<NewsResponse> call, Throwable t) {
                             // get from database
                        GetNewsFromDataBase getnewsthread = new GetNewsFromDataBase(newsPreperedListener,sourceId);
                        getnewsthread.start();
                    }
                });
    }

    class InsertSourcesInDataBase extends Thread{
        List<SourcesItem> sourcesItemstoIsert;
       public InsertSourcesInDataBase(List<SourcesItem> list){
           this.sourcesItemstoIsert = list;
       }
        @Override
        public void run() {
            super.run();
            NewsDataBaseManger.getInstance().newsDao().insertSources(sourcesItemstoIsert);
        }
    }

    class InsertNewsInDataBase extends Thread{
        List<ArticlesItem> list;
        public InsertNewsInDataBase(List<ArticlesItem> items){
            this.list = items;
        }
        @Override
        public void run() {
            super.run();

            for (ArticlesItem item: list) {
                item.setSourceId(item.getSource().getId());
                item.setSourceName(item.getSource().getName());
            }
            NewsDataBaseManger.getInstance()
                    .newsDao().insertNews(list);
        }
    }

    class GetSourcesFromDataBase extends Thread{
        onNewsSourcesPreperedListener onNewsSourcesPreperedListener;

        public GetSourcesFromDataBase(onNewsSourcesPreperedListener sourcesPreperedListener){
           this.onNewsSourcesPreperedListener = sourcesPreperedListener;
        }
        @Override
        public void run() {
            super.run();
           List<SourcesItem> sourcesList = NewsDataBaseManger.getInstance().newsDao()
                    .getAllSources();
           onNewsSourcesPreperedListener.onNewsSourcesPrepered(sourcesList);
        }
    }

    class GetNewsFromDataBase extends Thread{
        onNewsPreperedListener newsPreperedListener;
        String id;
        public GetNewsFromDataBase(onNewsPreperedListener newsPreperedListener, String sourceid){
            this.newsPreperedListener = newsPreperedListener;
            this.id = sourceid;
        }

        @Override
        public void run() {
            super.run();
            List<ArticlesItem> articlesItems = NewsDataBaseManger.getInstance()
                    .newsDao().getAllNews(id);
            newsPreperedListener.onNewsPrepered(articlesItems);
        }
    }

    public interface onNewsSourcesPreperedListener{
        void onNewsSourcesPrepered(List<SourcesItem> sourcesItems);
    }

    public interface onNewsPreperedListener{
        void onNewsPrepered(List<ArticlesItem> articlesItems);

    }
}
