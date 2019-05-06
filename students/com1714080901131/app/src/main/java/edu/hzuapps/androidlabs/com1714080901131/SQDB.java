package edu.hzuapps.androidlabs.com1714080901131;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQDB extends SQLiteOpenHelper {
    public final static String TABLE_NAME = "Record";

    public final static String RECORD_ID = "id";
    public final static String RECORD_TITLE = "titleName";
    public final static String RECORD_BODY = "textBody";
    public SQDB(Context context)
    {
        super(context,"Record",null,3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+RECORD_ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                RECORD_TITLE+" VARCHAR(30)," +
                RECORD_BODY+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
