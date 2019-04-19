package com.hzuandroid.jsonjx;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity {

    private List<Movie> listMovie;
    private ListView list;
    Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GetAndParseJson.PARSESUCCWSS:
                    listMovie=(List<Movie>) msg.obj;
                    initData();
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=(ListView)findViewById(R.id.lv);
        GetAndParseJson getAndParseJson=new GetAndParseJson(mHandler);
        getAndParseJson.getJsonFromInternet();
    }

    protected void initData() {
        List<Map<String, Object>> jsonlist=new ArrayList<Map<String,Object>>();
        for (Movie movie:listMovie) {
            Map<String, Object> map=new HashMap<String, Object>();
            map.put("id", movie.getId());
            map.put("name", movie.getName());
            map.put("price", movie.getPrice());
            map.put("language", movie.getLanguage());
            jsonlist.add(map);
        }
        SimpleAdapter adapter=new SimpleAdapter(this, jsonlist, R.layout.test, new String[]
                {"id","name","price","language"}, new int[]{R.id.id,R.id.name,R.id.price,R.id.language});
        list.setAdapter(adapter);
    }
}
