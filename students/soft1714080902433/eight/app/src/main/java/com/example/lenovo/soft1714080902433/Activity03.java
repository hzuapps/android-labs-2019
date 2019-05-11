package com.example.lenovo.soft1714080902433;


import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


@SuppressLint("Registered")
public class Activity03 extends AppCompatActivity {

    private Cursor listItemCursor = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity03);

// 设置添加笔记按钮事件，切换activity
        this.findViewById(R.id.addNote).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        Intent in = new Intent();
                        in.setClassName(getApplicationContext(),
                                "com.example.lenovo.soft1714080902433.NoteEditActivity");
                        startActivity(in);
                    }
                });

        // 查询所有笔记，并将笔记展示出来
        listItemCursor = DBService.queryAll();
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(Activity03.this,
                R.layout.note_item, listItemCursor, new String[] { "_id",
                "title", "createTime" }, new int[] { R.id.noteId,
                R.id.noteTitle, R.id.noteCreateTime },
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        ((ListView) this.findViewById(R.id.listNote)).setAdapter(adapter);

        initListNoteListener();

        }


    private void initListNoteListener() {
        // 长按删除
        ((ListView) this.findViewById(R.id.listNote))
                .setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent,
                                                   View view, int position, final long id) {
                        new AlertDialog.Builder(Activity03.this)
                                .setTitle("提示框")
                                .setMessage("确认删除该记录？？")
                                .setPositiveButton("确定",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface arg0,int arg1) {
                                                DBService.deleteNoteById((int) id);
                                                //删除后刷新列表
                                                Activity03.this.onResume();
                                                Toast.makeText(Activity03.this, "删除成功！！", Toast.LENGTH_SHORT).show();


                                            }
                                        }).setNegativeButton("取消", null).show();
                        return true;
                    }
                });

        //点击进行修改操作
        ((ListView) this.findViewById(R.id.listNote))
                .setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Intent in = new Intent();
                        in.setClassName(view.getContext(),
                                "com.example.lenovo.soft1714080902433.NoteEditActivity");
                        // 将id数据放置到Intent，切换视图后可以将数据传递过去
                        in.putExtra("id", id);
                        startActivity(in);
                    }
                });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    /**
     * 当从另一个视图进入该视图会调用该方法
     */
    @Override
    protected void onResume() {
        super.onResume();
        // 要求刷新主页列表笔记
        if (listItemCursor != null) {
            listItemCursor.requery();
        }
    }


}
