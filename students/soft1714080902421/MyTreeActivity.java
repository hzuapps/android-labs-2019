package edu.hzuapps.androidlabs.soft1714080902421;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class MyTreeActivity extends AppCompatActivity {
    private Button mBtnforedownload = null;
    private Button mBtnforemore = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tree);
        mBtnforedownload = findViewById(R.id.btn_foredownload);
        mBtnforemore = findViewById(R.id.btn_foremore);
        setOnClickListener();
    }

    private void setOnClickListener() {
        OnClick onClick = new OnClick();
        mBtnforedownload.setOnClickListener(onClick);
        mBtnforemore.setOnClickListener(onClick);
        //设置点击事件
    }

    private class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.btn_foredownload:
                    // intent = new Intent(MyTreeActivity.this, ForeDownActivity.class);
                    screenshot();
                    //Toast.makeText(MyTreeActivity.this, "已保存至本地相册，请查看~", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_foremore:
                    intent = new Intent(MyTreeActivity.this, ForeDownActivity.class);
                    break;
            }
            startActivity(intent);
        }

        private void screenshot() {
            View view = getWindow().getDecorView();
            view.setDrawingCacheEnabled(true);
            view.buildDrawingCache();
            Bitmap bmp = view.getDrawingCache();
            if (bmp != null) {
                try {
                    String path = Environment.getExternalStorageDirectory().getPath() + File.separator + "1.png";
                    File file = new File(path);
                    FileOutputStream out = new FileOutputStream(file);
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }


    }
}