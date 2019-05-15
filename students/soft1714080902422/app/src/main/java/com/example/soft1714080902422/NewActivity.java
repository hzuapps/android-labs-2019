package com.example.soft1714080902422;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class NewActivity extends AppCompatActivity implements View.OnClickListener{

    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    public static final int CHOOSE_PHOTO = 3;
    private Button takephoto;
    private Button choosephoto;
    private ImageView picture;
    private Uri imageUri;
    public static final int DOWNLOAD_CODE = 10001;
    public static final int DOWNLOAD_FAIL = 300;
    public static final int CONNECT_TIMEOUT = 2000;
    private ImageView image_download;
    private Button btn_download;

    private Handler handler;

    private String path = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1210556444,1378988191&fm=26&gp=0.jpg";

    private int fileLength;

    private Bitmap mBitmap;

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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        takephoto = (Button) findViewById(R.id.take_photo);
        picture = (ImageView) findViewById(R.id.picture);
        choosephoto = (Button) findViewById(R.id.choose_photo);
        /*
         * 实现监听器
         * */
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
        initialView();

        btn_download.setOnClickListener(this);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case DOWNLOAD_CODE:
                        Bitmap bitmap = (Bitmap) msg.obj;
                        if (bitmap != null) {
                            image_download.setImageBitmap(bitmap);//disPlay image
                        }
                        break;
                    case DOWNLOAD_FAIL:
                        Toast.makeText(NewActivity.this, "下载失败", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

    }

    public void initialView() {
        btn_download = findViewById(R.id.btn_download);
        image_download = findViewById(R.id.image_download);

    }
        @Override
        public void onClick (View view){
            switch (view.getId()) {
                case R.id.btn_download:
                    new Thread(new NewActivity.GetPictThread(handler, path)).start();
                    break;
            }
        }


    public class GetPictThread implements Runnable {
        public Handler handler;
        public String path;

        public GetPictThread(Handler handler, String path) {
            this.handler = handler;
            this.path = path;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(path);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("GET");
                connection.setConnectTimeout(30 * 1000);
                connection.connect();

                int requestCode = connection.getResponseCode();
                System.out.println(requestCode);

                if (requestCode == HttpURLConnection.HTTP_OK) {
                    fileLength = connection.getContentLength();

                    InputStream is = new BufferedInputStream(connection.getInputStream());

                    byte[] arr = streamToArr(is);

                    mBitmap = BitmapFactory.decodeByteArray(arr, 0, arr.length);

                    Message message = Message.obtain();
                    message.what = DOWNLOAD_CODE;
                    message.obj = mBitmap;
                    handler.sendMessage(message);

                } else {
                    Log.e("TAG", "run:error " + requestCode);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
                handler.sendEmptyMessage(DOWNLOAD_FAIL);
            } catch (IOException e) {
                e.printStackTrace();
                handler.sendEmptyMessage(DOWNLOAD_FAIL);
            }
        }
    }

    public byte[] streamToArr(InputStream inputStream) {

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;

            while ((len = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }

            baos.close();
            inputStream.close();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}