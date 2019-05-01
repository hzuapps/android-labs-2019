package edu.hzuapps.androidlabs.com1714080901106;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.hzuapps.androidlabs.com1714080901106.R;

public class Activity_Login extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button login;
    private Button register;
    private Button dl_image;
    private String name;
    private String word;
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = findViewById(R.id.user);
        password = findViewById(R.id.pwd);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        dl_image = findViewById(R.id.download_image);
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
            }
        });
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
