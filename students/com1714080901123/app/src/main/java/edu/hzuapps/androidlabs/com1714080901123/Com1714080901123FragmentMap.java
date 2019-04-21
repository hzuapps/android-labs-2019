package edu.hzuapps.androidlabs.com1714080901123;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class Com1714080901123FragmentMap extends Fragment {

    private WebView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_com1714080901123_fragment_map, container, false);
        webView = (WebView)view.findViewById(R.id.webview1);
        initWebView();

        return view;
    }

    private void initWebView(){
        //webView.getSettings().setUseWideViewPort(true);
        //webView.getSettings().setLoadWithOverviewMode(true);
        //WebView加载web资源
        //webView.loadUrl("https://splatoon2.ink/");
        webView.loadUrl("https://splatoon.nintendo.com/news-video/");
        //webView.loadUrl("https://splatoonwiki.org/");
        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true时在WebView打开，为false时调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
    }
}
