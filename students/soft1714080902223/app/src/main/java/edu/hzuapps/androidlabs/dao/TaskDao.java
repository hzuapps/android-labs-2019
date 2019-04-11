package edu.hzuapps.androidlabs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import edu.hzuapps.androidlabs.model.Task;

public class TaskDao {
    private String tableName;
    private TaskDatabaseHelper taskDatabaseHelper;

    public TaskDao(Context context){
        tableName = "Task";
        taskDatabaseHelper = new TaskDatabaseHelper(context,"TaskStore.db");
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    /**
     *
     * @param title : 标题
     * @param content : 内容
     * @return 返回成功插入的数据数
     */
    public long save(String title, String content){
        SQLiteDatabase database = taskDatabaseHelper.getWritableDatabase();
        // 使用Android封装的SQL语法
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("content", content);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                new Locale("zh", "CN"));
        Date d = new Date();
        String date = sdf.format(d);
        values.put("last_time",date);
        long id = database.insert(tableName,null,values);
        database.close();
        return id;
    }

    public void update(String title, String content, long id){
        SQLiteDatabase database = taskDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("content", content);
        database.update(tableName, values, "id=?",
                new String[]{Long.toString(id)});
        database.close();
    }


    public long delete(long id){
        SQLiteDatabase database = taskDatabaseHelper.getWritableDatabase();
        String idStr = Long.toString(id);
        int nums =  database.delete(tableName, "id == ?", new String[]{idStr});
        database.close();
        return nums;

    }

    public Task findById(long id){
        Task task = new Task();
        SQLiteDatabase database = taskDatabaseHelper.getReadableDatabase();
        Cursor cursor = database.query(tableName, null,
                "id=?", new String[]{Long.toString(id)}, null, null, null);

        //遍历
        if(cursor.moveToFirst()){
            task.setId(cursor.getLong(cursor.getColumnIndex("id")));
            task.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            task.setContent(cursor.getString(cursor.getColumnIndex("content")));
            task.setDate(cursor.getString(cursor.getColumnIndex("last_time")));
            task.setFinish(cursor.getInt(cursor.getColumnIndex("finish")));
        }
        cursor.close();
        database.close();
        return task;
    }

    /**
     *
     * @return 返回所有Task数据，按创建先后顺序倒序排序
     */

    public List<Task> findAll(){
        List<Task> taskList = new ArrayList<Task>();
        SQLiteDatabase database = taskDatabaseHelper.getReadableDatabase();
        Cursor cursor = database.query(tableName, null, null, null, null, null, "id desc");
        while(cursor.moveToNext()){
            Task task = new Task();
            task.setId(cursor.getLong(cursor.getColumnIndex("id")));
            task.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            task.setContent(cursor.getString(cursor.getColumnIndex("content")));
            task.setFinish(cursor.getInt(cursor.getColumnIndex("finish")));
            task.setDate(cursor.getString(cursor.getColumnIndex("last_time")));
            taskList.add(task);
        }
        cursor.close();
        return taskList;
    }


}
