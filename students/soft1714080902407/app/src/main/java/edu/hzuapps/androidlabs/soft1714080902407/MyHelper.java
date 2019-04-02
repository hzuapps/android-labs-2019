package edu.hzuapps.androidlabs.soft1714080902407;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context){
        super(context,"itcast.db",null,2);
    }
    public void onCreate(SQLiteDatabase db){
        System.out.println("onCreate");
        db.execSQL("CREATE TABLE message(_id INTEGER PRIMARY KEY AUTOINCREMENT,msg varchar(100))");
    }
    public void onUpgrade(SQLiteDatabase db,int OldVersion,int NewVersion){
        System.out.println("onUpgrade");
    }
}
