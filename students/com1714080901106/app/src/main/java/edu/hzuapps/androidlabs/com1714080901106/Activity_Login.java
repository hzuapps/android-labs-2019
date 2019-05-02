package edu.hzuapps.androidlabs.com1714080901106;

import android.Manifest;
import android.content.ContentUris;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.hzuapps.androidlabs.com1714080901106.R;

public class Activity_Login extends AppCompatActivity {
    private EditText username;//用户名文本框
    private EditText password;//密码文本框
    private Button login;//登录按钮
    private Button register;//注册按钮
    private Button dl_image;//下载图片按钮
    private Button take_photo;//拍照按钮
    private Button choose_photo;//选择照片
    private String name;//用户名
    private String word;//密码
    private ConstraintLayout layout;//主界面
    private Uri imageUri;//图片路径
    static final int TAKE_PHOTO = 1;//拍照请求
    static final int CHOOSE_PHOTO = 2;//选择照片请求

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = findViewById(R.id.user);
        password = findViewById(R.id.pwd);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        dl_image = findViewById(R.id.download_image);
        take_photo = findViewById(R.id.take_photos);
        choose_photo = findViewById(R.id.choose_image);
        layout = findViewById(R.id.background);

        login.setOnClickListener(new View.OnClickListener() {//登录
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Login.this, Com1714080901106Activity01.class);
                SharedPreferences user = getSharedPreferences("data", MODE_PRIVATE);
                name = user.getString("username","");
                word = user.getString("password","");
                if(username.getText().toString().equals(name) && password.getText().toString().equals(word)) {
                    Toast.makeText(Activity_Login.this,"登录成功！",Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else Toast.makeText(Activity_Login.this, "账号或密码错误！",Toast.LENGTH_SHORT).show();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {//注册
            @Override
            public void onClick(View v) {
                name = username.getText().toString();
                word = password.getText().toString();
                if (!TextUtils.isEmpty(username.getText()) && !TextUtils.isEmpty(password.getText())) {
                    SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                    editor.putString("username", name);
                    editor.putString("password", word);
                    editor.apply();
                    Toast.makeText(Activity_Login.this, "注册成功！", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(username.getText()) && !TextUtils.isEmpty(password.getText())) {
                    Toast.makeText(Activity_Login.this, "请输入用户名！", Toast.LENGTH_SHORT).show();
                } else if (!TextUtils.isEmpty(username.getText()) && TextUtils.isEmpty(password.getText())) {
                    Toast.makeText(Activity_Login.this, "请输入密码！", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(Activity_Login.this, "请输入用户名和密码！", Toast.LENGTH_SHORT).show();
            }
        });

        dl_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImage();
                Toast.makeText(Activity_Login.this, "加载成功！", Toast.LENGTH_LONG).show();
            }
        });

        take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputImage = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "photo.jpg");
                try {//初始化图片
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageUri = (Uri) FileProvider.getUriForFile(Activity_Login.this, getPackageName() + ".fileprovider", outputImage);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);//打开相机
            }
        });

        choose_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.GET_CONTENT");
                intent.setType("image/*");
                startActivityForResult(intent, CHOOSE_PHOTO);//打开相册
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CHOOSE_PHOTO && resultCode == RESULT_OK)
            handleImageOnKitKat(data);
    }

    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();//获取路径
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(
                    uri.getAuthority())) {
                String id = docId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(
                    uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);
        }
        displayImage(imagePath);//设置背景图片
    }

    private String getImagePath(Uri uri, String selection) {//获取照片路径
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {//将照片设置为背景图片
        //获取运行时权限
        if (ActivityCompat.checkSelfPermission(Activity_Login.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Activity_Login.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }

        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            layout.setBackground(new BitmapDrawable(getResources(), bitmap));
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    private void showImage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap = null;
                HttpURLConnection connection = null;
                try {
                    URL url = new URL("https://b-ssl.duitang.com/uploads/blog/201406/14/20140614050141_nsvAt.jpeg");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    bitmap = BitmapFactory.decodeStream(in);
                    show_image(bitmap);
                    Toast.makeText(Activity_Login.this, "加载成功！", Toast.LENGTH_SHORT).show();
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if(connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void show_image(final Bitmap bitmap) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                layout.setBackground(new BitmapDrawable(getResources(), bitmap));
            }
        });
    }

}
