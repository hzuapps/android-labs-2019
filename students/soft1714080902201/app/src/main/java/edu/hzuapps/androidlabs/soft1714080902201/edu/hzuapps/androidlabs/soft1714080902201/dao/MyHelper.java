package edu.hzuapps.androidlabs.soft1714080902201.edu.hzuapps.androidlabs.soft1714080902201.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context){
        super(context,"bookstore.db",null,2);
    }
    public void onCreate(SQLiteDatabase db) {
        Log.d("MyHelper", "onCreate:");
        db.execSQL("CREATE TABLE book(_id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR(20),author VARCHAR(20),balance INTEGER)");
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        Log.d("MyHelper", "onUpgrade:");
    }
}
