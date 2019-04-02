package edu.hzuapps.androidlabs.dao;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

public class TaskDatabaseHelper extends SQLiteOpenHelper {
    /**
     * @param context to use for locating paths to the the database
     * @param name of the database file, or null for an in-memory database
     */
    public TaskDatabaseHelper(Context context, String name){
        super(context, name, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建数据库后执行，创建数据表
        String CREATE_TABLE = "create table Task(" +
                "id integer primary key autoincrement," +
                "title text,"  +
                "content text," +
                "finish integer default 0," +
                "create_time timestamp not null default (datetime('now', 'localtime'))," +
                "last_time timestamp not null default (datetime('now', 'localtime')))";
        db.execSQL(CREATE_TABLE);
        System.out.println("创建数据表");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
