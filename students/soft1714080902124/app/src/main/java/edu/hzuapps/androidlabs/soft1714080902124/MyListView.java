package edu.hzuapps.androidlabs.soft1714080902124;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.myfirstapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyListView extends AppCompatActivity implements AbsListView.OnScrollListener, AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {


    private Context m_context;
    private ListView listView;
    private SimpleAdapter simpleAdapter;
    private List<Map<String, Object>> datalist;
    private Button add;
    private TextView textView;
    private NotesDB notesDB;
    private SQLiteDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_list_view);
        textView = (TextView) findViewById(R.id.t_context);
        listView = (ListView) findViewById(R.id.list_item);
        datalist = new ArrayList<Map<String, Object>>();

        add =(Button)findViewById(R.id.to_add);
        m_context = this;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTextActivity.ENTER_STATE = 0;
                Intent intent = new Intent(m_context,EditTextActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("info","");
                intent.putExtras(bundle);
                startActivityForResult(intent,1);
            }
        });
        notesDB = new NotesDB(this);
        database = notesDB.getReadableDatabase();
        //清空数据库表的内容
//        database.execSQL("delete from note");
        RefreshNotesList();

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);
        listView.setOnScrollListener(this);

    }

    public void RefreshNotesList(){
        int size = datalist.size();
        if (size>0){
            datalist.removeAll(datalist);
            simpleAdapter.notifyDataSetChanged();
            listView.setAdapter(simpleAdapter);
        }
//        适配器渲染list
        simpleAdapter = new SimpleAdapter(this,getData(),R.layout.list,new String[]{
                "t_content","t_date "},new int[]{
                R.id.t_context,R.id.t_date
        });
        listView.setAdapter(simpleAdapter);
    }
    private List<Map<String, Object>> getData(){
        Cursor cursor = database.query("note",null,"content!=\"\"",null,null,null,null);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("content"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("t_content",name);
            map.put("t_date",date);
            datalist.add(map);
        }
        cursor.close();
        return datalist;
    }
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState){
            case SCROLL_STATE_FLING:
                Log.i("main","用户在手指离开屏幕之前，由于用力的滑了一下，视图能依靠惯性继续滑动");
            case SCROLL_STATE_IDLE:
                Log.i("main","视图已经停止滑动");
            case SCROLL_STATE_TOUCH_SCROLL:
                Log.i("main","手指没有离开屏幕，视图正在滑动");
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        EditTextActivity.ENTER_STATE =1;
        String content = listView.getItemAtPosition(position) + "";
        String content1 = content.substring(content.indexOf("=") + 1,content.indexOf(","));
        Log.d("CONTENT",content1);
        Cursor cursor = database.query("note",null, "content=" + "'" + content1 + "'" ,null,null,null,null);
        while (cursor.moveToNext()){
            String No = cursor.getString(cursor.getColumnIndex("_id"));
            Log.d("TEXT",No);
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("info",content1);
            EditTextActivity.id = Integer.parseInt(No);
            intent.putExtras(bundle);
            intent.setClass(MyListView.this, EditTextActivity.class);
            startActivityForResult(intent,1);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 1&& resultCode == 2){
            RefreshNotesList();
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        final int n = position;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("删除本日志");
        builder.setMessage("确认删除吗？");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String content = listView.getItemAtPosition(n) + "";
                String content1 = content.substring(content.indexOf("=") + 1,content.indexOf(","));
                Cursor cursor = database.query("note",null,"content=" + "'" + content1 + "'", null,null,null,null);
                while (cursor.moveToNext()){
                    String id = cursor.getString(cursor.getColumnIndex("_id"));
                    String sql_del = "update note set content = '' where _id=" + id;
                    database.execSQL(sql_del);
                    RefreshNotesList();
                }
            }
        });
        builder.create();
        builder.show();
        return true;
    }

}