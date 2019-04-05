package com.edu.hzuapps.androidlabs.soft1714080902307.Function.Notepad;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2018/6/5.
 */

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DB_NAME="NotePad.db";
    public static final int VERSION=1;
    public static final String TABLE_NAME="Diary";
    //建表语句
    public static final String CREATE_DIARY="create table Diary(" +
            "id integer primary key autoincrement," +
            "title text," +
            "content text)";

    private Context mContext;

    public DatabaseHelper(Context context,String name,
                          SQLiteDatabase.CursorFactory factory,int version){
        super(context,name,factory,version);
        mContext=context;

    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DIARY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
