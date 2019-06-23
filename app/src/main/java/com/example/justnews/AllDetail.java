package com.example.justnews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class AllDetail extends AppCompatActivity {

    protected WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_all_detail);
        initView();
        show();
    }

    private void show() {
        Intent intent = getIntent();
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl(intent.getStringExtra("detail"));

    }

    private void initView() {
        webview =  findViewById(R.id.webview);
    }
}
