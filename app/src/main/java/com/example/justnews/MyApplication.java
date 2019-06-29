package com.example.justnews;

import android.app.Application;

import com.example.justnews.DatabaseUtil.NewsDataBaseManger;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        NewsDataBaseManger.init(this);
    }
}
