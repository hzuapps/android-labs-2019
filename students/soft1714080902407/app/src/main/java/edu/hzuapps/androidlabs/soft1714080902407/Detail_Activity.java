package edu.hzuapps.androidlabs.soft1714080902407;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
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

public class Detail_Activity extends AppCompatActivity {
    private Button mCheckButton;
    private Button mDownloadImageButton;
    private Button mloadimgButton;
    private ImageView mimageView;
    private File mPrivateRootDir;
    private File mImagesDir;
    private NetworkFileDownloader mFileDownloader;
    public static final String TAG = Detail_Activity.class.getSimpleName();
    public static final String IMAGE_URL_PREFIX = "https://github.com/Firethecode/android-labs-2019/raw/master/students/soft1714080902407/app/src/main/res/drawable/";
    static String[] imageNames = {"zjq.jpg"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        mDownloadImageButton = (Button) findViewById(R.id.button_download_image);
        mimageView = (ImageView) findViewById(R.id.image);
        mloadimgButton = (Button) findViewById(R.id.button_loadimg);

        mPrivateRootDir = getFilesDir();

        mImagesDir = new File(mPrivateRootDir, "images");

    }

    public void onClick(View view) {
        if (view.getId() == R.id.button_download_image) {
            downloadImages();
        }else if(view.getId() == R.id.button_loadimg) {
            showjpg();
        }else if(view.getId()==R.id.button_back){
            Intent intent = new Intent(Detail_Activity.this,Soft1714080902407Activity.class);
            startActivity(intent);
        }
    }


    private void downloadImages() {
        mFileDownloader = new NetworkFileDownloader(new NetworkFileDownloader.OnImageDownloadListener() {
            @Override
            public void onError(String error) {
                Toast.makeText(Detail_Activity.this, error, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onProgressChange(int percent) {
                Log.i(TAG, "当前进度 = " + percent);
            }

            @Override
            public void onComplete(Bitmap bitmap, String imageUrl) {
                final Bitmap.CompressFormat format = Bitmap.CompressFormat.PNG;
                String filename = imageUrl.replace(IMAGE_URL_PREFIX, "");
                final File imageFile = new File(mImagesDir, filename);
                NetworkFileDownloader.writeToDisk(imageFile, bitmap, new NetworkFileDownloader.OnBitmapSaveListener() {
                    @Override
                    public void onBitmapSaved() {
                        Toast.makeText(Detail_Activity.this, "文件已保存: " + imageFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onBitmapSaveError(String error) {
                        Toast.makeText(Detail_Activity.this, error, Toast.LENGTH_LONG).show();
                    }
                }, format, false);
            }
        });

        for(String imageName : imageNames) {
            String imageUrl = IMAGE_URL_PREFIX + imageName;
            mFileDownloader.download(imageUrl, true);
        }
    }

    public void showjpg(){
        Bitmap bitmap = getLoacalBitmap("/data/user/0/edu.hzuapps.androidlabs.Soft1714080902407/files/images/zjq.jpg");
        mimageView .setImageBitmap(bitmap);
    }

    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }}
