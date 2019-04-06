package com.hzuandroid.soft1714080902408.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context){
        super(context,"soft.db",null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("onCreate");
        db.execSQL("CREATE TABLE account(_id INTEGER PRIMARY KEY AUTOINCREMENT,name  VARCHAR(20),balance INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        System.out.println("onUpgrade");
    }
}
