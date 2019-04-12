package edu.hzuapps.androidlabs.soft1714080902439;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import com.example.soft171408092439.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Soft1714080902439Activity extends AppCompatActivity {
    List<Map<String, String>> list;
    private ListView birthLV;
    SimpleAdapter adapter;
    SQLiteDatabase db;
    private ImageButton button;
    //private EditText time;
    private TextView textView;
    private CalendarView calendarView;
    //获取时间
    //SimpleDateFormat formatter=new SimpleDateFormat("yyyy年MM月dd日");
    //Date currentDate=new Date(System.currentTimeMillis());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902439_activity);
        button = (ImageButton) findViewById(R.id.add_button);
        //time=(EditText) findViewById(R.id.time);
        textView = (TextView) findViewById(R.id.text);
        calendarView=(CalendarView) findViewById(R.id.calendarView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast button=Toast.makeText(edu.hzuapps.androidlabs.soft1714080902439.Soft1714080902439Activity.this,"",Toast.LENGTH_SHORT);
                Intent intent=new Intent(edu.hzuapps.androidlabs.soft1714080902439.Soft1714080902439Activity.this,edu.hzuapps.androidlabs.soft1714080902439.Soft1714080902439AddBirthdayActivity.class);
                startActivity(intent);
                //button.show();
            }
        });
        inti();
    }

    private void inti(){
        //每个程序都要有自己的数据库，默认内情况下各自互不干扰
        //创建数据库，如果已存在就打开，不存在就创建
        db=openOrCreateDatabase("birthday.db",MODE_PRIVATE,null);
        list=new ArrayList<Map<String, String>>();
        birthLV=(ListView) findViewById(R.id.birthLV);
        adapter=new SimpleAdapter(this,getData(),R.layout.soft_1714080902439_sql_item,new String[]{"id","name","yinyang","date"},new int[]{R.id.tv_id,R.id.tv_name,R.id.tv_yinyang,R.id.tv_date});
        birthLV.setAdapter(adapter);
    }

    public List<Map<String,String>> getData(){
        if (isTableExist("birthinfo")){
            Cursor cursor=db.rawQuery("select * from birthinfo",null);
            if (cursor!=null){
                while (cursor.moveToNext()){
                    Map<String,String> map=new HashMap<>();
                    map.put("id",cursor.getString(0));
                    map.put("name",cursor.getString(1));
                    map.put("yinyang",cursor.getString(cursor.getColumnIndex("yinyang")));
                    map.put("date",cursor.getString(cursor.getColumnIndex("date")));
                    list.add(map);
                }
                cursor.close();
                db.close();
                return list;
            }else {
                cursor.close();
                db.close();
            }
        }
        return list;
    }

    //判断表是否存在
    public boolean isTableExist(String table){
        Cursor cursor=db.rawQuery("select count(*) from sqlite_master where type='table' and name='"+table+"'",null);
        if (cursor!=null){
            while (cursor.moveToNext()){
                int count=cursor.getInt(0);
                if (count>0){
                    cursor.close();
                    return true;
                }
            }
        }else {
            cursor.close();
        }
        return false;
    }

}