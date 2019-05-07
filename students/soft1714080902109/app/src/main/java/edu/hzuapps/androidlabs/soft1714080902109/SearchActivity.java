package edu.hzuapps.androidlabs.soft1714080902109;



import android.content.Context;

import android.content.DialogInterface;

import android.content.Intent;

import android.net.Uri;

import android.os.Handler;

import android.os.Message;

import android.provider.MediaStore;

import android.support.v7.app.AlertDialog;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.util.Log;

import android.view.View;

import android.webkit.WebChromeClient;

import android.webkit.WebResourceRequest;

import android.webkit.WebSettings;

import android.webkit.WebView;

import android.webkit.WebViewClient;

import android.widget.Button;

import android.widget.EditText;

import android.widget.ImageView;

import android.widget.ProgressBar;

import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.FileNotFoundException;
import edu.hzuapps.androidlabs.Soft1714080902109.R;



public class SearchActivity extends AppCompatActivity {



    private ProgressBar progress;              //进度条

    private WebView webView;            //网页显示区

    private EditText editText;       //网址输入框

    private Button click;             //点击搜索

    private Context context;

    private ImageView imageView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        context = this;

        initView();                   //初始化界面

        initDate();                   //初始化数据

        initListener();                 //初始化监听器
    }
    private void initDate() {

        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);

        settings.setUseWideViewPort(true);//设置此属性，可任意比例缩放

        settings.setLoadWithOverviewMode(true);

        // 使页面支持缩放

        settings.setBuiltInZoomControls(true);

        settings.setSupportZoom(true);

        //支持自动加载图片

        settings.setLoadsImagesAutomatically(true);

        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);// 排版适应屏幕

        // 缩放按钮

        settings.setDisplayZoomControls(false);
        webView.setWebViewClient(new WebViewClient(){
            // 页面在当前页面跳转

            @Override

            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                return super.shouldOverrideUrlLoading(view, request);

            }
            // 页面加载结束

            @Override

            public void onPageFinished(WebView view, String url) {

                super.onPageFinished(view, url);

                if(progress!=null){

                    progress.setVisibility(View.GONE);
                }
            }
        });
    }
    private void initView() {        //初始化界面

        progress = (ProgressBar) findViewById(R.id.progress);

        webView = (WebView) findViewById(R.id.webView);

        editText = (EditText) findViewById(R.id.url);

        click = (Button) findViewById(R.id.click);

        imageView=(ImageView)findViewById(R.id.image);

    }
    private void initListener() {

        // 网页加载进度显示

        webView.setWebChromeClient(new WebChromeClient(){

            @Override

            public void onProgressChanged(WebView view, int newProgress) {

                super.onProgressChanged(view, newProgress);

                progress.setVisibility(View.VISIBLE);

                progress.setProgress(newProgress);

            }

        });
        click.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View view) {

                Log.e("输入的网站",editText.getText().toString().trim());

                webView.loadUrl(editText.getText().toString().trim());
            }

        });

        // 长按点击事件

        webView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override

            public boolean onLongClick(View view) {

                final WebView.HitTestResult hitTestResult = webView.getHitTestResult();

                // 如果是图片类型或者是带有图片链接的类型

                if(hitTestResult.getType()== WebView.HitTestResult.IMAGE_TYPE||

                        hitTestResult.getType()== WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE){

                    // 弹出保存图片的对话框

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);

                    builder.setTitle("提示");

                    builder.setMessage("保存图片到本地");

                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {

                        @Override

                        public void onClick(DialogInterface dialogInterface, int i) {

                            String url = hitTestResult.getExtra();

                            // 下载图片到本地

                            Log.d("11111", "handleMessage: "+url);

                            Glide.with(getApplicationContext()).load(url).into(imageView);

                            DownPicUtil.downPic(url, new DownPicUtil.DownFinishListener(){



                                @Override

                                public void getDownPath(String s) {

                                    Toast.makeText(context,"下载完成", Toast.LENGTH_LONG).show();

                                    Message msg = Message.obtain();

                                    msg.obj=s;

                                    handler.sendMessage(msg);
                                }

                            });
                        }

                    });

                    builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

                        // 自动dismiss

                        @Override

                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });

                    AlertDialog dialog = builder.create();

                    dialog.show();

                }
                return true;
            }

        });
        webView.loadUrl("https://image.baidu.com/search/index?tn=baiduimage&ct=201326592&lm=-1&cl=2&ie=gb18030&word=%D6%F7%CC%E2%B7%BF%BC%E4%CD%BC%C6%AC&fr=ala&ala=1&alatpl=adress&pos=0&hs=2&xthttps=111111\n");
    }
    Handler handler =new Handler(){

        @Override

        public void handleMessage(Message msg) {

            super.handleMessage(msg);

            String picFile = (String) msg.obj;

            String[] split = picFile.split("/");

            String fileName = split[split.length-1];

            try {

                MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), picFile, fileName, null);

            } catch (FileNotFoundException e) {

                e.printStackTrace();

            }

            // 最后通知图库更新

            getApplicationContext().sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + picFile)));

            Toast.makeText(context,"图片保存图库成功",Toast.LENGTH_LONG).show();

        }

    };



}
