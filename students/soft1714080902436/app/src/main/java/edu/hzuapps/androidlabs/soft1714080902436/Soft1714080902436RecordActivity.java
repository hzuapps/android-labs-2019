package edu.hzuapps.androidlabs.soft1714080902436;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Soft1714080902436RecordActivity extends AppCompatActivity {

    private TextView Transmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902436_record);

        //跳转记录页面
        Button RecordButton = (Button) findViewById(R.id.RecordButton);
        RecordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Soft1714080902436RecordActivity.this, Soft1714080902436Activity.class);
                startActivity(intent2);
            }
        });

        //跳转分享页面
        Button ShareButton = (Button) findViewById(R.id.ShareButton);
        ShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Soft1714080902436RecordActivity.this, Soft1714080902436ShareActivity.class);
                startActivity(intent2);
            }
        });

        //跳转个人页面
        Button PersonButton = (Button) findViewById(R.id.PersonButton);
        PersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Soft1714080902436RecordActivity.this, Soft1714080902436ShareActivity.class);
                startActivity(intent2);
            }
        });

        //插入记录
            final Intent intent1 = this.getIntent();
            final String transmit = intent1.getStringExtra("transmit");
            TextView Transmit=(TextView)findViewById(R.id.transmit);
            Transmit.setText(transmit);
    }
}