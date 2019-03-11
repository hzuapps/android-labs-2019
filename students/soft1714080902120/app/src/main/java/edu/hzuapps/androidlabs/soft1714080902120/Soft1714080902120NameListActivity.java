package edu.hzuapps.androidlabs.soft1714080902120;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Soft1714080902120NameListActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //Adapter适配器
    Soft1714080902120HpAdapter hpAdapter = new Soft1714080902120HpAdapter(
                this,R.layout.hp_item,Soft1714080902120HistoryPeople.getHistoryPeople());
//通过ID获取ListView
    ListView listView =(ListView) findViewById(R.id.HistoryPeople_listView);
    //设置listView的适配器
    listView.setAdapter(hpAdapter);
    }
}
