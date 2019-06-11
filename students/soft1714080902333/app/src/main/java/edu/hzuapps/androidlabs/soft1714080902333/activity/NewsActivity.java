package edu.hzuapps.androidlabs.soft1714080902333.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import edu.hzuapps.androidlabs.soft1714080902333.R;

public class NewsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        // 新页面接收数据
        Bundle bundle = this.getIntent().getExtras();
        // 接收name值
        String name = bundle.getString("uri");

        WebView webView = (WebView) findViewById(R.id.mWebView);

        WebSettings webSettings = webView.getSettings();

        // Js交互权限
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        webSettings.setDomStorageEnabled(true);

        webView.loadUrl(name);
    }
}
