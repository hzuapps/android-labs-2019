package edu.hzuapps.androidlabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class Soft1714080902209_UPSQL extends SQLiteOpenHelper{
    public Soft1714080902209_UPSQL(Context context){
        super(context, "HR_Test.db",null,5);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table if not exists username(" +
                "id integer primary key autoincrement,"+
                "username varchar(30)," +
                "passcode varchar(30))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("alter table username add account varchar(20)");
    }

}