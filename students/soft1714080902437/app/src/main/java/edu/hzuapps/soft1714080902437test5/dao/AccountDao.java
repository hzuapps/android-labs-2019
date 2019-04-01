package edu.hzuapps.soft1714080902437test5.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.hzuapps.soft1714080902437test5.bean.Account;

public class AccountDao {

        private MyHelper helper;
        public AccountDao(Context context){
            helper=new MyHelper(context);
        }
        public void insert(Account account){
            SQLiteDatabase db=helper.getWritableDatabase();
            ContentValues values=new ContentValues();
            values.put("cname",account.getName1());
            values.put("tname",account.getName2());
            long id=db.insert("account",null,values);
            account.setId(id);
            db.close();
        }
        public int delete(long id){
            SQLiteDatabase db=helper.getWritableDatabase();
            int count=db.delete("account","_id=?",new String[]{id+""});
            db.close();
            return count;
        }
        public int update(Account account){//更新数据
            SQLiteDatabase db=helper.getWritableDatabase();
            ContentValues values=new ContentValues();
            values.put("cname",account.getName1());
            values.put("tname",account.getName2());
            int count=db.update("account",values,"_id=?",new String[]{account.getId()+""});
            db.close();
            return count;
        }
    public List<Account>queryAll(){
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor c=db.query("account",null,null,null,null,null,null);
        List<Account>list=new ArrayList<Account>();
        while (c.moveToNext()){
            long  id=c.getLong(c.getColumnIndex("_id"));
            String cname=c.getString(1);
            String tname=c.getString(2);
            list.add(new Account(id,cname,tname));
        }
        c.close();
        db.close();
        return list;
    }
    }



