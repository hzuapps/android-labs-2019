package edu.hzuapps.androidlabs.soft1714080902110;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RankListSQLHelper extends SQLiteOpenHelper {

    private static final String INSERT_DATA = "insert into rank (name, score) values (?,?)";

    private static final String CREATE_LIST = "create table rank (" +
            "name varchar(20) primary key NOT NULL," +
            "score integer NOT NULL)";

    private Context mContext;

    public RankListSQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LIST);
        db.execSQL(INSERT_DATA, new String[] {"张漆", "10"});
        db.execSQL(INSERT_DATA, new String[] {"张三", "60"});
        db.execSQL(INSERT_DATA, new String[] {"李四", "50"});
        db.execSQL(INSERT_DATA, new String[] {"王五", "91"});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
