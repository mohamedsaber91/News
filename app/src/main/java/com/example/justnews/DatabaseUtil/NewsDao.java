package com.example.justnews.DatabaseUtil;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.justnews.APIS.Model.NewsModel.ArticlesItem;
import com.example.justnews.APIS.Model.SourcesItem;

import java.util.List;

@Dao
public interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSources(List<SourcesItem> sourcesItems);

    @Query("select * from SourcesItem")
    List<SourcesItem> getAllSources();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNews(List<ArticlesItem> articlesItems);

    @Query("select * from ArticlesItem where sourceId =:sourceid")
    List<ArticlesItem> getAllNews(String sourceid);
}
