package com.example.justnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.justnews.APIS.Model.NewsModel.ArticlesItem;
import com.example.justnews.APIS.Model.SourcesItem;
import com.example.justnews.Adapters.NewsAdapter;
import com.example.justnews.Base.BaseActivity;
import com.example.justnews.Repositry.NewsRepositry;

import java.util.List;

public class HomeNews extends BaseActivity {

    protected TabLayout tablayout;
    protected RecyclerView sourcesRecyclerview;
    protected NewsRepositry newsRepositry;
    NewsAdapter adapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_homenews);
        initView();
        showProgressBar(R.string.please_wait, R.string.loading);
        getNewsSourcesFromRepositry();

    }



    private void getNewsSourcesFromRepositry() {
        newsRepositry.getSourcesnamefromApi(new NewsRepositry.onNewsSourcesPreperedListener() {
            @Override
            public void onNewsSourcesPrepered(final List<SourcesItem> sourcesItems) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgressBar();
                        showSourcesInTablayout(sourcesItems);
                    }
                });

            }
        });

    }




    private void initView() {
        tablayout =  findViewById(R.id.tablayout);
        sourcesRecyclerview =  findViewById(R.id.sources_recyclerview);
        newsRepositry = new NewsRepositry(LanguageDataHolder.currentLanguage);
        layoutManager = new LinearLayoutManager(activity);
        adapter = new NewsAdapter(null);
        sourcesRecyclerview.setLayoutManager(layoutManager);
        sourcesRecyclerview.setAdapter(adapter);
        adapter.setItemClickListener(itemClickListener);
    }
     NewsAdapter.onItemClickListener itemClickListener =
             new NewsAdapter.onItemClickListener() {
                 @Override
                 public void onItemClick(ArticlesItem articlesItem) {
                     Intent intent = new Intent(activity,NewsDetail.class);
                     intent.putExtra("image",articlesItem.getUrlToImage());
                     intent.putExtra("title",articlesItem.getTitle());
                     intent.putExtra("time",articlesItem.getPublishedAt());
                     intent.putExtra("author",articlesItem.getAuthor());
                     intent.putExtra("content",articlesItem.getContent());
                     intent.putExtra("url",articlesItem.getUrl());
                     startActivity(intent);
                 }
             };
    private void showSourcesInTablayout(List<SourcesItem> sourcesResponses){
             if (sourcesResponses == null)  return;

             for (int i = 0; i<sourcesResponses.size(); i++){
                 SourcesItem source = sourcesResponses.get(i);
                 TabLayout.Tab tab = tablayout.newTab();

                 tab.setText(source.getName());
                 tab.setTag(source);
                 tablayout.addTab(tab);
             }

             tablayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
                 @Override
                 public void onTabSelected(TabLayout.Tab tab) {
                     SourcesItem source = (SourcesItem) tab.getTag();
                     newsRepositry.getNewsfromApi(source.getId(),newsPreperedListener);

                 }

                 @Override
                 public void onTabUnselected(TabLayout.Tab tab) {

                 }

                 @Override
                 public void onTabReselected(TabLayout.Tab tab) {
                     SourcesItem source = (SourcesItem) tab.getTag();
                     showProgressBar(R.string.please_wait,R.string.loading);
                     newsRepositry.getNewsfromApi(source.getId(),newsPreperedListener);
                 }
             });
             tablayout.getTabAt(0).select();
    }
    NewsRepositry.onNewsPreperedListener newsPreperedListener =
            new NewsRepositry.onNewsPreperedListener() {
                @Override
                public void onNewsPrepered(final List<ArticlesItem> articlesItems) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideProgressBar();
                            adapter.changeData(articlesItems);
                        }
                    });

                }
            };


}
