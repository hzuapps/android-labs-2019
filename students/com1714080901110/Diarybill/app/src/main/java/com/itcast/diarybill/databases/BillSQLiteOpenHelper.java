package com.itcast.diarybill.databases;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BillSQLiteOpenHelper extends SQLiteOpenHelper {
    public BillSQLiteOpenHelper(Context context){
        super(context,"accountBook.db",null,2);
    }
    public void onCreate(SQLiteDatabase db){
        System.out.println("onCreate");
        db.execSQL("CREATE TABLE bill(id  INTEGER primary key autoincrement,date varchar(20),income INTEGER,coast INTEGER)");
    }
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        System.out.print("onUpgrade");
    }
}
