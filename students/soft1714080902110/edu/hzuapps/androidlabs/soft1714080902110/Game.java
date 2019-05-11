package edu.hzuapps.androidlabs.soft1714080902110;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Game extends AppCompatActivity {

    public static final int TAKE_PHOTO = 1;
    private RelativeLayout game;
    private File outputImage;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

//        Toolbar显示
        Toolbar toolbar = findViewById(R.id.game_title);
        setSupportActionBar(toolbar);

//        如果已拍过图片，直接显示
        game = findViewById(R.id.game);
        outputImage = new File(getCacheDir(), "myImage.jpg");
        if (outputImage.exists()) {
            showBitmap();
        }
    }

//    添加菜单显示
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game, menu);
        return true;
    }

//    实现菜单功能
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_background:
                takePhoto();
                break;
            default:
                break;
        }
        return true;
    }

    private void takePhoto() {
//        创建文件
        outputImage = new File(getCacheDir(), "myImage.jpg");
        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        调用相机
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, getImageUri());
        startActivityForResult(intent, TAKE_PHOTO);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    showBitmap();
                }
                break;
            default:
                break;
        }
    }

//    获取图片uri
    private Uri getImageUri() {
        Uri imageUri;
        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(Game.this, getPackageName(), outputImage);
        } else {
            imageUri = Uri.fromFile(outputImage);
        }
        return imageUri;
    }

//    显示bitmap图片
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void showBitmap() {
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(getImageUri()));
            game.setBackground(new BitmapDrawable(bitmap));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
