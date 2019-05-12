package edu.hzuapps.androidlabs.soft1714080902122;


import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

public class RecordActivity extends AppCompatActivity {

    private ImageView picture;
    private static final int TAKE_PHOTO = 1;
    private static final int CHOOSE_PHOTO = 2;
    private Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        picture = findViewById(R.id.picture);


        Button fromAblum = findViewById(R.id.choose_from_album);
        fromAblum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(RecordActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(RecordActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }

            }
        });
    }


    /**
     * 打开相机
     */
    private void openCamera() {

        //创建用处存储拍照后的图片
        File outputImage = new File(getExternalCacheDir(), "output_image.jpg");

        try {
            if (outputImage.exists()) {
                outputImage.delete();
            }
            outputImage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (Build.VERSION.SDK_INT >= 24) {
            imageUri = FileProvider.getUriForFile(RecordActivity.this,
                    "com.dak.administrator.firstcode.fileprovider", outputImage);
        } else {
            imageUri = Uri.fromFile(outputImage);
        }
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);//输出地址
        startActivityForResult(intent, TAKE_PHOTO);
    }

    /**
     * 打开相册
     * gallery
     */
    private void openAlbum() {
        //很漂亮页面
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "请获取权限", Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
//                        压缩 循环 太慢
//                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                        int options = 100;
//                        while (baos.toByteArray().length / 1024 > 100) {//循环判断如果压缩后图片是否大于100kb,大于继续压缩
//                            baos.reset();//重置baos即清空baos
//                            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
//                            options -= 10;//每次都减少10
//                        }
//                        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
//                        Bitmap comBitmap = BitmapFactory.decodeStream(bais);
                        picture.setImageBitmap(bitmap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        //4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        //4.4及以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();

        if (DocumentsContract.isDocumentUri(this, uri)) {
            //如果是document 类型的uri ，则通过document id处理

            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                //如果uri的authority是media格式 再次解析id 将字符串分割取出后半部分才能得到真正的数字id
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //如果是content类型的uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            //如果是file类型的uri，直接获取图片路径
            imagePath = uri.getPath();
        }
        displayImage(imagePath);//根据图片路径显示图片
    }

    private String getImagePath(Uri externalContentUri, String selection) {
        String path = null;
        //通过Uri和selection来获取真实图片路径
        Cursor cursor = getContentResolver().query(externalContentUri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }

    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }


}