package edu.androidlabs.soft1709081602719;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<FileInfo> list;
    MyAdapter adapter;

    final String ROOT = Utils.getSDCardPath();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        updateData();
        System.out.println("onCreate");

    }
    private void initView(){
        listView = findViewById(R.id.list);
        adapter = new MyAdapter(this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((AdapterView.OnItemClickListener) this);
    }
    private void updateData(){
        list = Utils.getListData(ROOT);
        adapter.setList(list);
        adapter.notifyDataSetChanged();

    }
}
