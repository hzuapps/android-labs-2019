package edu.hzuapps.androidlabs.soft1714080902207;

import android.content.Intent;

import android.graphics.Bitmap;

import android.os.Handler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileInputStream;

import java.io.FileOutputStream;



public class Soft_1714080902207_MainActivity extends AppCompatActivity {
    private EditText editText2;
    private Button showImage;
    private Button button4;
    private Button button2;
    private Button button3;
    private ImageView imageView2;

    private final int SHOW_IMAGE=1;
    String pathString="http://pic46.nipic.com/20140813/10153265_103918805357_2.jpg";
    Bitmap bitmap;
    Handler h=new Handler() {
        public void handleMessage(android.os.Message msg) {

            switch (msg.what) {
                case SHOW_IMAGE:
                    Bitmap bitmap = (Bitmap) msg.obj;
                    imageView2.setImageBitmap(bitmap);
                    break;
                default:
                    break; }};};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902207_activity_main);


        editText2 = (EditText) findViewById(R.id.editText2);

        button4 = (Button) findViewById(R.id.button4);
        button2 = (Button) findViewById(R.id.button2);
        button4.setOnClickListener(new ButtonListener());
        button2.setOnClickListener(new ButtonListener());

        button3 = (Button) findViewById(R.id.button3);
        button2 = findViewById(R.id.button2);
        //button2.setOnClickListener(this);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Soft_1714080902207_MainActivity.this, Soft_1714080902207_SecondActivity.class);
                startActivity(intent);
            }
        });



    }





               private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button2:
                    String saveInfo = editText2.getText().toString().trim();
                    FileOutputStream fos;
                    try {
                        fos = openFileOutput("data.txt", MODE_APPEND);//把文件输出到data中
                        fos.write(saveInfo.getBytes());//将我们写入的字符串变成字符数组）
                        fos.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft_1714080902207_MainActivity.this, "数据保存成功", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button4:
                    String content = "";
                    String conten = "";
                    try {
                        FileInputStream fis = openFileInput("data.txt");
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        content = new String(buffer);
                        content=new String(content+conten);
                        conten = new String(content);
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(Soft_1714080902207_MainActivity.this, "本次消费金额是：" + conten, Toast.LENGTH_SHORT).show();

                    break;

                default:
            }
        }
    }
}



