package com.example.justnews.DatabaseUtil;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.justnews.APIS.Model.NewsModel.ArticlesItem;
import com.example.justnews.APIS.Model.SourcesItem;

@Database(entities = {SourcesItem.class, ArticlesItem.class}, version = 2, exportSchema = false)
public abstract class NewsDataBaseManger extends RoomDatabase {

    private static NewsDataBaseManger newsDataBaseManger;
    public  abstract NewsDao newsDao();

    public static NewsDataBaseManger init(Context context){
        if (newsDataBaseManger == null){
            newsDataBaseManger = Room.databaseBuilder(context,
                    NewsDataBaseManger.class,
                    "News sources")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return newsDataBaseManger;
    }

    public static NewsDataBaseManger getInstance(){
        return  newsDataBaseManger;
    }
}
