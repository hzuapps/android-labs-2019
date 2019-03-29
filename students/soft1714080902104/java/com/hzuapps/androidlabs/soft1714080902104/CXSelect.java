package com.example.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.LinearLayout;

import com.example.menu.Adapter.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class CXSelect extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cxselect);

        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);

        adapter = new RecyclerViewAdapter(this, createData());

        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    private List<Integer> createData() {
        List<Integer> data = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                data.add(R.drawable.cp);
            } else {
                data.add(R.mipmap.ic_launcher);
            }
        }
        return data;
    }
}
