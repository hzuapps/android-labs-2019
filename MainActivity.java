package com.example.shiyan33;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends AppCompatActivity {
    private EditText et_info;
    private Button btn_save;
    private Button btn_read;
    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    public static final int CHOOSE_PHOTO = 3;
    private Button takephoto;
    private Button choosephoto;
    private ImageView picture;
    private Uri imageUri;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 获取布局文件中的控件
        et_info = (EditText) findViewById(R.id.et_info);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_read = (Button) findViewById(R.id.btn_read);
        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());
        takephoto = (Button) findViewById(R.id.take_photo);
        picture = (ImageView) findViewById(R.id.picture);
        choosephoto = (Button) findViewById(R.id.choose_photo);

        takephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                 * 创建File对象，实现拍照图片的存储
                 * */
                File outputImage = new File(Environment.getExternalStorageDirectory(),
                        "output_image.jpg");
                /*
                 * 初始化图片
                 * */
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                /*
                 * 将File对象转成Uri对象
                 * */
                imageUri = Uri.fromFile(outputImage);
                /*
                 * 构建隐式Intent，指定图片输出地址
                 * */
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                /*
                 * 启动相机，并向下一个活动传递参数
                 * */
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });

        choosephoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                /*
                 * 打开相册
                 * */
                startActivityForResult(intent, CHOOSE_PHOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            /*
             * 拍照成功返回结果，跳转到裁剪
             * */
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Intent intent = new Intent("com.android.camera.action.CROP");
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    /*
                     * 启动剪裁程序
                     * */
                    startActivityForResult(intent, CROP_PHOTO);
                }
                break;
            /*
             * 裁剪结束，将.jpg解析成Bitmap显示出来
             * */
            case CROP_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(
                                getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap); //显示剪裁后的图片
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            /*
             * 判断版本，选择处理图片方式
             * */
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        /*
                         * 4.4及以上版本
                         * */
                        handleImageOnKitKat(data);
                    } else {
                        /*
                         * 4.4以下版本
                         * */
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    /*
     * 4.4以上版本需要封装Uri
     * */
    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        /*
         * 获取路径
         * */
        Uri uri = data.getData();
        /*
         *Document类型Uri对document id处理
         * */
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            /*
             * 与Uri中Authority部分比较,media格式需进一步解析
             * */
            if ("com.android.providers.media.documents".equals(
                    uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                /*
                 * 传入getImagePath获得真实路径
                 * */
                imagePath = getImagePath(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(
                    uri.getAuthority())) {
                /*
                 * downloads格式获得路径
                 * */
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            /*
             * 普通处理
             * */
            imagePath = getImagePath(uri, null);
        }
        /*
         * 图片显示
         * */
        displayImage(imagePath);
    }

    /*
     * 4.4以下版本返回图片真实Uri，直接处理
     * */
    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    /*
     * 获取图片路径
     * */
    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            /*
             * 定位第一行返回指定列名称
             * */
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            /*
             * 释放资源
             * */
            cursor.close();
        }
        return path;
    }

    /*
     * 打印图片
     * */
    private void displayImage(String imagePath) {
        if (imagePath != null) {
            /*
             * 将照片解析为Bitmap形式展现
             * */
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }



    private class ButtonListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_save:
                    String saveinfo = et_info.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        fos = openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(saveinfo.getBytes());
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this, "数据保存成功", 0).show();
                    break;
                case R.id.btn_read:
                    String content = "";
                    try {
                        FileInputStream fis = openFileInput("data.txt");
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        content = new String(buffer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(MainActivity.this, "保存的数据是：" + content, 0)
                            .show();
                    break;
                default:
                    break;
            }
        }
    }

    public void read(View view) {

    }





    public void skip(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,otherActivity.class);
        startActivity(intent);
    }
}
