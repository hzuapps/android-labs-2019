package com.example.myandroid_2_3.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myandroid_2_3.bean.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private MyHelper helper;
    public AccountDao(Context context){
        helper= new MyHelper(context);
    }
    public void insert(Account account){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("actions",account.getActions());
        values.put("cost",account.getCost());
        long id=db.insert("jizhangben`",null,values);
        account.setId(id);
        db.close();
    }
    public List<Account> queryALL(){
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor c=db.query("jizhangben",null,null,null,null,null,"cost DESC");
        List<Account> list=new ArrayList<>();
        while(c.moveToNext()){
            long id=c.getLong(c.getColumnIndex("id"));
            String name=c.getString(1);
            int balance=c.getInt(2);
            list.add(new Account(id,name,balance));
        }
        c.close();
        db.close();
        return list;
    }
}
