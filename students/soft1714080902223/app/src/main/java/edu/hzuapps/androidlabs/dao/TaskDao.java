package edu.hzuapps.androidlabs.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.net.PortUnreachableException;
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
    public final static String LAST_TIME_PATTERN = "yyyy-MM-dd HH:mm";
    public final static String CREATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
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
    public long save(String title, String content, String lastTime){
        SQLiteDatabase database = taskDatabaseHelper.getWritableDatabase();
        // 使用Android封装的SQL语法
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("content", content);
        if(lastTime == null) {
            SimpleDateFormat sdf = new SimpleDateFormat(LAST_TIME_PATTERN,
                    new Locale("zh", "CN"));
            Date d = new Date();
            String date = sdf.format(d);
            values.put("last_time", date);
        }
        else {
            values.put("last_time", lastTime);
        }
        long id = database.insert(tableName,null,values);
        database.close();
        return id;
    }

    public void update(String title, String content, String lastTime, long id){
        update(title, content, lastTime, -1, id);
    }

    public void update(String title, String content, String lastTime, int finish, long id){
        SQLiteDatabase database = taskDatabaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if(title != null)
            values.put("title", title);
        if(content != null)
            values.put("content", content);
        if(lastTime != null)
            values.put("last_time", lastTime);
        if(finish != -1){
            values.put("finish", finish);
        }
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
            task.setLastTime(cursor.getString(cursor.getColumnIndex("last_time")));
            task.setCreateTime(cursor.getString(cursor.getColumnIndex("create_time")));
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
            task.setLastTime(cursor.getString(cursor.getColumnIndex("last_time")));
            task.setCreateTime(cursor.getString(cursor.getColumnIndex("create_time")));
            taskList.add(task);
        }
        cursor.close();
        return taskList;
    }


}
