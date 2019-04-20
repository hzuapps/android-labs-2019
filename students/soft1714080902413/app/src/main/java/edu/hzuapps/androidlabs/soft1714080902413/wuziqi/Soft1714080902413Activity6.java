package edu.hzuapps.androidlabs.soft1714080902413.wuziqi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Soft1714080902413Activity6 extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = Soft1714080902413Activity6.class.getSimpleName();
    public static final String IMAGE_URL_PREFIX = "https://raw.githubusercontent.com/jiyimilelu/android-labs-2019/master/students/soft1714080902413/app/src/main/res/drawable/";
    static String[] imageNames = {"timg.jpg"};

    private Button mCheckButton;
    private Button mDownloadImageButton;
    private Button mShowImageButton;
    private TextView mNetworkText;
    private ImageView mshowphoto;

    private boolean mConnected;

    private Soft1714080902413Downloader mFileDownloader;

    // App的内部存储目录
    private File mPrivateRootDir;
    // “images”子目录
    private File mImagesDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft17140809024136);

        mCheckButton = (Button) findViewById(R.id.btn_check);
        mDownloadImageButton = (Button) findViewById(R.id.btn_downphoto);
        mShowImageButton = (Button) findViewById(R.id.btn_showphoto);
       mNetworkText = (TextView) findViewById(R.id.text_check);
        mshowphoto = (ImageView) findViewById(R.id.imageView_showphoto);

        mCheckButton.setOnClickListener(this);
        mDownloadImageButton.setOnClickListener(this);
        mShowImageButton.setOnClickListener(this);

        // 获取内部存储目录: files
        mPrivateRootDir = getFilesDir();
        // 获取内部存储子目录: files/images
        mImagesDir = new File(mPrivateRootDir, "images");
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_check) {
            checkNetworkState(); // 检查网络
        } else if (view.getId() == R.id.btn_downphoto) {
            downloadImages(); // 下载图片
        } else if (view.getId() == R.id.btn_showphoto) {
            showphoto1();
        }
    }

    // 检查网络状态
    private void checkNetworkState() {
        // 取得连接管理器
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // 检查当前激的网络
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            mConnected = true;
        } else {
            mConnected = false;
        }

        String types = "";

        // 检查Wi-Fi
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifiConn = networkInfo != null && networkInfo.isConnected();
        types += isWifiConn ? "Wi-Fi" : "";

        // 检查数据网络
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConn = networkInfo != null && networkInfo.isConnected();
        types += isMobileConn ? "流量" : "";

        // 检查蓝牙
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_BLUETOOTH);
        boolean isBluetoothConn = networkInfo != null && networkInfo.isConnected();
        types += isBluetoothConn ? ", 蓝牙" : "";

        mNetworkText.setTextColor(mConnected ? Color.GREEN : Color.RED);
        mNetworkText.setText(mConnected ? "网络正常 (" +types + ")" : "网络未连接!");
    }

    // 下载图片
    private void downloadImages() {
        // 创建下载器
        mFileDownloader = new Soft1714080902413Downloader(new Soft1714080902413Downloader.OnImageDownloadListener() {
            @Override
            public void onError(String error) {
                Toast.makeText(Soft1714080902413Activity6.this, error, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProgressChange(int percent) {
                Log.i(TAG, "当前进度 = " + percent);
            }

            @Override
            public void onComplete(Bitmap bitmap, String imageUrl) {
                // 下载的图片格式为PNG
                final Bitmap.CompressFormat format = Bitmap.CompressFormat.PNG;
                // 解析出原始文件名
                String filename = imageUrl.replace(IMAGE_URL_PREFIX, "");
                final File imageFile = new File(mImagesDir, filename);
                // 将文件保存到磁盘中
                Soft1714080902413Downloader.writeToDisk(imageFile, bitmap, new Soft1714080902413Downloader.OnBitmapSaveListener() {
                    @Override
                    public void onBitmapSaved() {
                        Toast.makeText(Soft1714080902413Activity6.this, "文件已保存: " + imageFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onBitmapSaveError(String error) {
                        Toast.makeText(Soft1714080902413Activity6.this, error, Toast.LENGTH_LONG).show();
                    }
                }, format, false);
            }
        });

        // 下载所有文件
        for(String imageName : imageNames) {
            String imageUrl = IMAGE_URL_PREFIX + imageName;
            mFileDownloader.download(imageUrl, true);
        }
    }

    // 下载网页
    private void downloadWebPage() {
    }

    private void showphoto1() {
        Bitmap bitmap = getLoacalBitmap("/data/user/0/edu.hzuapps.androidlabs.soft1714080902413.wuziqi/files/images/timg.jpg");
        mshowphoto.setImageBitmap(bitmap);
    }

    private Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}

