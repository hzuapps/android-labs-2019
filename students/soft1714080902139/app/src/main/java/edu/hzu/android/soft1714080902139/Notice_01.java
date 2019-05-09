package edu.hzu.android.soft1714080902139;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Notice_01 extends AppCompatActivity {
    @Override
    public void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.notiweb01);
        WebView webView = (WebView) findViewById(R.id.web_view03);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://moba.163.com/2019/s5/");
    }
}