package edu.hzuapps.androidlabs.soft1714080902407;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MessageDao {
    private SQLiteHelper sqLiteHelper;

    public MessageDao(Context context){
        sqLiteHelper=new SQLiteHelper(context);
    }

    public void insert(Message message){
        SQLiteDatabase sqLiteDatabase=sqLiteHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("msg",message.getMsg());
        contentValues.put("time",message.getTime());
        contentValues.put("send",message.getSend());
        long id=sqLiteDatabase.insert("message",null,contentValues);
        message.setId(id);
        sqLiteDatabase.close();
    }

    public List<Message> queryAll(){
        SQLiteDatabase sqLiteDatabase=sqLiteHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.query("message",null,null,null,null,null,null);
        List<Message> list=new ArrayList<Message>();
        while (cursor.moveToNext()){
            long id=cursor.getLong(cursor.getColumnIndex("id"));
            String msg=cursor.getString(1);
            String time=cursor.getString(2);
            int type=cursor.getInt(3);
            boolean send;
            if(type==1)
            {
                send=true;
            }
            else send=false;
            list.add(new Message(id,msg,time,send));
        }
        cursor.close();
        sqLiteDatabase.close();
        return list;
    }
}
