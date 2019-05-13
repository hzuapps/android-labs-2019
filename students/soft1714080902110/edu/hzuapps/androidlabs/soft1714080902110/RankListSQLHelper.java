package edu.hzuapps.androidlabs.soft1714080902110;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RankListSQLHelper extends SQLiteOpenHelper {

    private static final String INSERT_DATA = "insert into rank (name, grade) values (?,?)";

    private static final String CREATE_LIST = "create table rank (" +
            "id integer primary key AUTOINCREMENT," +
            "name varchar(20) NOT NULL," +
            "grade integer NOT NULL)";

    private Context mContext;

    public RankListSQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
