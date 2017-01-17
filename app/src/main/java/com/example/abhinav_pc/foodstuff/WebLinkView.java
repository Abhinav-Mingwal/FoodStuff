package com.example.abhinav_pc.foodstuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebLinkView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_link_view);
        WebView myWebView = (WebView) findViewById(R.id.activity_web_link_view);
        Intent intent = getIntent();
        myWebView.getSettings().setDomStorageEnabled(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setUseWideViewPort(true);
        myWebView.loadUrl(intent.getStringExtra("link"));
    }
}
