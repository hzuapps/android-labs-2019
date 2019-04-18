package com.example.tuku3;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Soft1714080902227Activity2 extends AppCompatActivity {
    private WebView mWebView;
    private Button mImageLoad;
    private String List[];
    private String Address;
    protected static final int CHANGE_UI = 1;
    protected static final int ERROR = 2;
    RecyclerView recyclerView;
    ImageAdapter imageAdapter;
    String Fruit[];
    ProgressDialog dialog = null;
    private Handler handler = new Handler(){
        @SuppressLint("WrongConstant")
        public void handleMessage(Message msg) {
            recyclerView.setAdapter(imageAdapter);
        };
    };
    private ProgressDialog mProgressDialog;
    private String TAG = "qijian";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Bundle bundle = getIntent().getExtras(); //读取上一个网页Intent传来的数据
        Address = bundle.getString("address");   //得到网址
        mWebView = (WebView)findViewById(R.id.webview);
        mProgressDialog = new ProgressDialog(this);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new Soft1714080902227Activity2.JsInterface(Soft1714080902227Activity2.this), "imagelistner");
        mWebView.getSettings().setBlockNetworkImage(false);  // 设置是否显示网络图像---true,封锁网络图片，不显示     false----允许显示网络图片
        mWebView.loadUrl(Address);  //加载网页
        dialog = ProgressDialog.show(Soft1714080902227Activity2.this,null,"图片加载中，请稍后..");
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {  //开始加载
                super.onPageStarted(view, url, favicon);
                mProgressDialog.show();
            }
            @Override
            public void onPageFinished(WebView view, String url) {        //加载完成
                view.getSettings().setJavaScriptEnabled(true);
                super.onPageFinished(view, url);
                mProgressDialog.hide();
                //延时10秒再加载图片
                new Handler().postDelayed(new Runnable(){
                    public void run(){
                        //execute the task
                        Log.i("chen", "========进入延时========");
                        addLocalJs();
                        dialog.dismiss();
                    }
                },5000);
            }
        });
    }
    public void addLocalJs() {
        mWebView.loadUrl("javascript:(function(){ "
                + "var objs = document.getElementsByTagName(\"img\");"  //用var声明一个变量，不限定类型，javascript语言 getElementsByTagName() 方法可返回带有指定标签名的对象的集合。
                + "var array=new Array(); "                                 //创建一个数组
                + " for(var j=0,i=1;j<objs.length;j++){ "
                + "array[j]=objs[j].src;"          //获取每张图片地址 <img> 标签的 src 属性是必需的。它的值是图像文件的 URL
                + " }  "
                + "window.imagelistner.getImage(array);"
                + "})()");
    }
    private class JsInterface {
        private Context context;
        public JsInterface(Context context) {
            this.context = context;
        }
        @JavascriptInterface
        public void getImage(String[] urls) throws IOException {
            Log.i("chen", "========进入js方法========");
            if (urls != null && urls.length > 0) {
                for (int i = 0; i < urls.length; i++) {
                    Log.i("===666" + i + "===", urls[i]);
                }
            }
            List = urls;
            new Thread(){
                public void run(){

                  /*  Fruit = List;
                    recyclerView = (RecyclerView) findViewById(R.id.recycler_image);
                    Fruit=delete(Fruit,0);
                    imageAdapter = new ImageAdapter(Fruit);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Soft1714080902227Activity2.this));
                    Message msg = new Message();
                    msg.what = CHANGE_UI;
                    msg.obj = imageAdapter;
                    handler.sendMessage(msg);*/
                    handler.post(runnableUi);
                }
            }.start();
           /* List = urls;
            Fruit = List;
            recyclerView = (RecyclerView) findViewById(R.id.recycler_image);
            Fruit=delete(Fruit,0);
            imageAdapter = new ImageAdapter(Fruit);
            recyclerView.setLayoutManager(new LinearLayoutManager(Soft1714080902227Activity2.this));
            Message msg = new Message();
            msg.what = CHANGE_UI;
            msg.obj = imageAdapter;
            handler.sendMessage(msg);*/

        }
    }
    Runnable   runnableUi=new  Runnable(){
        @Override
        public void run() {
            //更新界面
            Fruit = List;
            recyclerView = (RecyclerView) findViewById(R.id.recycler_image);
            Fruit=delete(Fruit,0);
            imageAdapter = new ImageAdapter(Fruit);
            recyclerView.setLayoutManager(new LinearLayoutManager(Soft1714080902227Activity2.this));
            Message msg = new Message();
            msg.what = CHANGE_UI;
            msg.obj = imageAdapter;
            handler.sendMessage(msg);
        }

    };

    public static String[] delete(String  []n,int index) {
        int j=0;
        if(index<0||index>=n.length) {
            System.out.println("没有对应的元素可删除");
            return n;
        }
        String[] b=new String [n.length-1];
        for(int i=0;i<n.length;i++) {
            if(i==index)continue;
            b[j++]=n[i];
        }
        return b;
    }
}