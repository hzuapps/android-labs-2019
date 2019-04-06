package com.hzuandroid.soft1714080902408.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hzuandroid.soft1714080902408.bean.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    private MyHelper helper;
    public AccountDao(Context context) {
        helper=new MyHelper(context);
    }
    public void insert (Account account){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",account.getName());
        values.put("balance",account.getBalance());

        long id=db.insert("account",null,values);
        account.setId(id);
        db.close();
    }
    public int delete(long id){
        SQLiteDatabase db=helper.getWritableDatabase();
        int count=db.delete("account","_id=?",new String[] {id+"" });
        db.close();
        return count;
    }
    public int update(Account account){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",account.getName());
        values.put("balance",account.getBalance());
        int count=db.update("account",values,"_id=?",new String[]{account.getId()+""});
        db.close();
        return count;
    }
    public List<Account> queryALL(){
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor c=db.query("account",null,null,null,null,null,"balance DESC");
        List<Account> list=new ArrayList<Account>();
        while (c.moveToNext()){
            long id=c.getLong(c.getColumnIndex("_id"));
            String name=c.getString(1);
            int balance=c.getInt(2);
            list.add(new Account(id,name,balance));
        }
        c.close();
        db.close();
        return list;
    }
}
