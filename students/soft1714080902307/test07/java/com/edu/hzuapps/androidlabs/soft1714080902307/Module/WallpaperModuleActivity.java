package com.edu.hzuapps.androidlabs.soft1714080902307.Module;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.edu.hzuapps.androidlabs.soft1714080902307.Module.Function.Wallpaper.ListViewShowPicActivity;
import com.edu.hzuapps.androidlabs.soft1714080902307.R;

public class WallpaperModuleActivity extends AppCompatActivity {
    private Button button0;
    private Button button01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_wallpaper_module);
            Button button0 = findViewById(R.id.function09);

            button0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(WallpaperModuleActivity.this, ListViewShowPicActivity.class);
                    startActivity(intent);
                }
            });
            Button button01 = findViewById(R.id.function10);

            button01.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(WallpaperModuleActivity.this, SecondFunctionActivity.class);
                    startActivity(intent);
                }
            });

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // 给左上角图标的左边加上一个返回的图标 。
            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            int id = item.getItemId();
            return super.onOptionsItemSelected(item);
        }

}
