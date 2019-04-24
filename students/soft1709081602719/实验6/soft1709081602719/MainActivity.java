package edu.hzuapps.androidlabs.soft1709081602719;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import edu.hzuapps.androidlabs.asynctask.DownLoadAsynctask;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private String path="https://raw.githubusercontent.com/Administrator404/android-labs-2019/master/students/soft1709081602719/JSON/weather.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = this.<ListView>findViewById(R.id.lv);
        new DownLoadAsynctask(this,lv).execute(path);
    }
}
