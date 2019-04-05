package edu.hzuapps.androidlabs.soft1714080902201.edu.hzuapps.androidlabs.soft1714080902201.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.hzuapps.androidlabs.soft1714080902201.edu.hzuapps.androidlabs.soft1714080902201.bean.Book;

public class BookDao {
    private  MyHelper helper;
    public BookDao(Context context){
        //创建Dao时，创建Helper
        helper=new MyHelper(context);
    }
    public void insert(Book book){
        //获取数据库对象
        SQLiteDatabase db=helper.getWritableDatabase();
        //用来装载要插入的数据的Map<列名，列的值>
        ContentValues values=new ContentValues();
        values.put("name",book.getName());
        values.put("author",book.getAuthor());
        values.put("balance",book.getBalance());
        long id=db.insert("book",null,values);
        book.setId(id);
        db.close();
    }
    //根据id删除数据
    public int delete(long id){
        SQLiteDatabase db=helper.getWritableDatabase();
        //按条件删除指定表中的数据，返回受影响的行数
        int count=db.delete("book","_id=?",new String[] {id+""});
        db.close();
        return count;
    }
    //更新数据
    public int update(Book book){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("name",book.getName());
        values.put("author",book.getAuthor());
        values.put("balance",book.getBalance());
        int count=db.update("book",values,"_id=?",new String[] {book.getId()+""});
        db.close();
        return count;
    }
    //查询所有数据倒序排列
    public List<Book> queryAll(){
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor c=db.query("book",null,null,null,null,null,"balance DESC");
        List<Book> list=new ArrayList<Book>();
        while(c.moveToNext()){
            //可以根据列名获取索引
            long id=c.getLong(c.getColumnIndex("_id"));
            String name=c.getString(1);
            String author=c.getString(2);
            int balance=c.getInt(3);
            list.add(new Book(id,name,author,balance));
        }
        c.close();
        db.close();
        return list;
    }
}
