package com.example.justnews;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.justnews.Base.BaseActivity;

public class NewsDetail extends BaseActivity implements View.OnClickListener {

    protected ImageView newsImageDetail;
    protected TextView newsTitleDetail;
    protected TextView newsDetailTime;
    protected TextView newsDetailAuthor;
    protected TextView newsDetailContent;
    protected TextView showNewsDetailInWebView;
    protected String alldetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.detail_item_news);
        initView();
        showData();
    }

    private void showData() {
        Intent intent = getIntent();
        Glide.with(activity).load(intent.getStringExtra("image")).into(newsImageDetail);
        newsTitleDetail.setText(intent.getStringExtra("tittle"));
        newsDetailTime.setText(intent.getStringExtra("time"));
        newsDetailAuthor.setText(intent.getStringExtra("author"));
        newsDetailContent.setText(intent.getStringExtra("content"));
        alldetail = intent.getStringExtra("url");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.show_news_detail_in_web_view) {
              Intent intent = new Intent(activity,AllDetail.class);
              intent.putExtra("detail",alldetail);
              startActivity(intent);
        }
    }

    private void initView() {
        newsImageDetail =  findViewById(R.id.news_image_detail);
        newsTitleDetail =  findViewById(R.id.news_title_detail);
        newsDetailTime =  findViewById(R.id.news_detail_time);
        newsDetailAuthor =  findViewById(R.id.news_detail_author);
        newsDetailContent =  findViewById(R.id.news_detail_content);
        showNewsDetailInWebView =  findViewById(R.id.show_news_detail_in_web_view);
        showNewsDetailInWebView.setOnClickListener(NewsDetail.this);
    }
}
