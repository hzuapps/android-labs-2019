package edu.hzuapps.androidlabs.soft1714080902207;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBase extends SQLiteOpenHelper {
static String name="soft.db";
static int version=1;
    public MyDataBase(Context context) {
        super(context, name,null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
        String sql="create table user(编号 Integer,姓名 varchar(10),年龄 Integer)";
        arg0.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
