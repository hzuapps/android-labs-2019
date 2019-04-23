package edu.hzuapps.androidlabs.dmc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InvalidObjectException;
import java.io.OutputStreamWriter;

import edu.hzuapps.androidlabs.com1714080901106.R;

public class Activity_Login extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button login;
    private Button register;
    private String name;
    private String word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = findViewById(R.id.user);
        password = findViewById(R.id.pwd);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

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
    }
    /*
    public void save(String input) {//保存数据
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("data", Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(input);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String load() {//读取数据
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("data");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
    */
}
