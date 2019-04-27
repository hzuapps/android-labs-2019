package edu.hzuapps.androidlabs.soft1714080902201;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Soft1714080902201_Activity extends AppCompatActivity {
    private List<Soft1714080902201_Book1> book1List = new ArrayList<>();

    private IntentFilter intentFilter;
    private NetworkChangeRecevier netWorkChangeRecevier;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902201);

        //动态监听网络变化
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        netWorkChangeRecevier = new NetworkChangeRecevier();
        registerReceiver(netWorkChangeRecevier,intentFilter);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){ actionBar.hide(); }

        initBook(); //初始化数据
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        Soft1714080902201_BookAdapter adapter = new Soft1714080902201_BookAdapter(book1List);
        recyclerView.setAdapter(adapter);
    }


    //动态监听网络变化
    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(netWorkChangeRecevier);
    }

    class NetworkChangeRecevier extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent){
            ConnectivityManager connectionManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
            if (networkInfo != null&&networkInfo.isAvailable()){
                Toast.makeText(context,"已连接网络",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context,"网络不可用，请检查",Toast.LENGTH_SHORT).show();
            }
        }
    }

    //首页横向滑动模块
    private void initBook(){
            Soft1714080902201_Book1 book1=new Soft1714080902201_Book1("流浪地球",R.drawable.book1);
            book1List.add(book1);
            Soft1714080902201_Book1 book12 =new Soft1714080902201_Book1("四时歌",R.drawable.book2);
            book1List.add(book12);
            Soft1714080902201_Book1 book13 =new Soft1714080902201_Book1("星髓",R.drawable.book3);
            book1List.add(book13);
    }

    //跳转到搜索图书模块
    public void iconSearch (View view){
        Intent intent2 = new Intent(this, Soft1714080902201_SearchBook.class);
        startActivity(intent2);
    }

    //跳转到推荐书籍模块
    public void sendMessage2 (View view){
        Intent intent2 = new Intent(this, Soft1714080902201_Activity_Recommendation.class);
        startActivity(intent2);
    }

    //跳转到书籍增删模块
    public void sendMessage3 (View view){
        Intent intent3 = new Intent(this, Soft1714080902201_Activity_3.class);
        startActivity(intent3);
    }

}
