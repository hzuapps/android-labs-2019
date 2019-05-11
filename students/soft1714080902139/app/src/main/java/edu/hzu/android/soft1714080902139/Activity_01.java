package edu.hzu.android.soft1714080902139;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Activity_01 extends AppCompatActivity {
    @Override
    public void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.actweb01);
        WebView webView = (WebView) findViewById(R.id.web_view01);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://moba.163.com/");
    }
}