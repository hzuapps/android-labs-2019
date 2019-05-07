package com.itcast.diarybill.databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.itcast.diarybill.bean.Bill;

import java.util.ArrayList;
import java.util.List;

public class BillDao {
    private BillSQLiteOpenHelper helper;
    public BillDao(Context context){
        //创建DAO时，创建Helper
        helper=new BillSQLiteOpenHelper(context);
    }
    public void insert(Bill bill){
        //获取数据库对象
        SQLiteDatabase db=helper.getWritableDatabase();
        //用来装载要插入数据的数据的Map<列名,列的值>
        ContentValues values=new ContentValues();
        values.put("date",bill.getDate());
        values.put("income",bill.getIncome());
        values.put("coast",bill.getCoast());
        //向account表插入数据values
        long id=db.insert("bill",null,values);
        bill.setId(id);//得到id
        db.close();//关闭数据库
    }
    //根据id删除数据
    public int delete(long id){
        SQLiteDatabase db=helper.getWritableDatabase();
        //根据条件删除指定表中的数据，返回受影响的行数
        int count=db.delete("bill","id=?",new String[]{id+""});
        db.close();
        return count;
    }
    //记账天数
    public Integer totalDay(){
        //获取数据库对象
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor = db.query("bill", null, null, null, null, null, null);
        int count=0;
        if (cursor != null) {
            while(cursor.moveToNext()){
                count++;
            }
        }
        cursor.close();
        db.close();//关闭数据库
        return count;
    }
    //总收入
    public Integer selectTotalIncome(){
        //获取数据库对象
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor = db.query("bill", null, null, null, null, null, null);
        Integer sum=0;
        if (cursor != null) {
            while(cursor.moveToNext()){
                String result=cursor.getString(cursor.getColumnIndex("income"));
                Integer income=Integer.parseInt(result);
                sum+=income;
            }
        }
        cursor.close();
        db.close();//关闭数据库
        return sum;
    }
    //总支出
    public Integer selectTotalCoast(){
        //获取数据库对象
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor = db.query("bill", null, null, null, null, null, null);
        Integer sum=0;
        if (cursor != null) {
            while(cursor.moveToNext()){
                String result=cursor.getString(cursor.getColumnIndex("coast"));
                Integer coast=Integer.parseInt(result);
                sum+=coast;
            }
        }
        cursor.close();
        db.close();//关闭数据库
        return sum;
    }
    //总剩余
    public Integer totalSurplus(){
        //获取数据库对象
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor = db.query("bill", null, null, null, null, null, null);
        Integer totalIncome=0;
        Integer totalCoast=0;
        if (cursor != null) {
            while(cursor.moveToNext()){
                String result1=cursor.getString(cursor.getColumnIndex("income"));
                Integer income=Integer.parseInt(result1);
                String result2=cursor.getString(cursor.getColumnIndex("coast"));
                Integer coast=Integer.parseInt(result2);
                totalIncome+=income;
                totalCoast+=coast;
            }
        }
        cursor.close();
        db.close();//关闭数据库
        Integer surplus=totalIncome-totalCoast;
        return surplus;
    }
    //平均收入
    public Float avgIncome(){
        //获取数据库对象
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor = db.query("bill", null, null, null, null, null, null);
        Integer sum=0;
        int count=0;
        if (cursor != null) {
            while(cursor.moveToNext()){
                String result=cursor.getString(cursor.getColumnIndex("income"));
                Integer income=Integer.parseInt(result);
                sum+=income;
                count++;
            }
        }
        cursor.close();
        db.close();//关闭数据库
        if(count==0){
            float result=0;
            return result;
        }
        else{
            return (float)sum/count;
        }

    }
    //平均支出
    public Float avgCoast(){
        //获取数据库对象
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor cursor = db.query("bill", null, null, null, null, null, null);
        Integer sum=0;
        int count=0;
        if (cursor != null) {
            while(cursor.moveToNext()){
                String result=cursor.getString(cursor.getColumnIndex("coast"));
                Integer coast=Integer.parseInt(result);
                sum+=coast;
                count++;
            }
        }
        cursor.close();
        db.close();//关闭数据库
        if(count==0){
            float result=0;
            return result;
        }
        else{
            return (float)sum/count;
        }
    }

    //将查询所有数据倒序排列
    public List<Bill> queryAll(){
        SQLiteDatabase db=helper.getWritableDatabase();
        Cursor c=db.query("bill",null,null,null,null,null,null);
        List<Bill> list=new ArrayList<Bill>();
        while (c.moveToNext()){
            //可根据列名获取索引
            long id=c.getLong(c.getColumnIndex("id"));
            String date=c.getString(1);
            int income=c.getInt(2);
            int coast=c.getInt(3);
            list.add(new Bill(id,date,income,coast));
        }
        c.close();
        db.close();
        return list;
    }
}
