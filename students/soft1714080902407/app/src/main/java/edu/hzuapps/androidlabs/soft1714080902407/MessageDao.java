package edu.hzuapps.androidlabs.soft1714080902407;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class MessageDao {
    private MyHelper myHelper;
    public MessageDao(Context context){
        myHelper=new MyHelper(context);
    }
    public void insert(Message message){
        SQLiteDatabase db=myHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("msg",message.getMsg());
        long id=db.insert("message",null,values);
        message.setId(id);
        db.close();
    }

    public List<Message> queryAll(){
        SQLiteDatabase db=myHelper.getReadableDatabase();
        Cursor cursor=db.query("message",null,null,null,null,null,null);
        List<Message>list=new ArrayList<Message>();
        while(cursor.moveToNext()){
            long id= cursor.getLong(cursor.getColumnIndex("_id"));
            String msg= cursor.getString(1);
            list.add(new Message(id,msg));
        }
        cursor.close();
        db.close();
        return list;
    }
}
