package edu.hzuapps.androidlabs.soft1714080902407;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {
    public SQLiteHelper(Context context){
        super(context,"itcast.db",null,2);
    }
    public void onCreate(SQLiteDatabase db){
        System.out.println("onCreate");
        db.execSQL("CREATE TABLE Message(id INTEGER PRIMARY KEY AUTOINCREMENT,msg varchar(100),time varchar(100),send boolean)");
    }
    public void onUpgrade(SQLiteDatabase db,int OldVersion,int NewVersion){
        System.out.println("onUpgrade");
    }
}
