package edu.hzuapps.androidlabs.soft1714080902201;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import edu.hzuapps.androidlabs.soft1714080902201.edu.hzuapps.androidlabs.soft1714080902201.bean.Book;
import edu.hzuapps.androidlabs.soft1714080902201.edu.hzuapps.androidlabs.soft1714080902201.dao.BookDao;
import edu.hzuapps.androidlabs.soft1714080902201.edu.hzuapps.androidlabs.soft1714080902201.dao.MyHelper;

public class Soft1714080902201_Activity_3 extends AppCompatActivity {
    private List<Book> list;
    private BookDao dao;
    private EditText nameET;
    private EditText authorET;
    private EditText balanceET;
    private Myadapter adapater;
    private ListView bookLV;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902201_3);
    //初始化控件
        initView();
        dao=new BookDao(this);
        //从数据库查询所有数据
        list=dao.queryAll();
        adapater=new Myadapter();
        bookLV.setAdapter(adapater);
}

//初始化控件
private void initView(){
    bookLV=(ListView) findViewById(R.id.bookLV);
    nameET=(EditText) findViewById(R.id.nameET);
    authorET=(EditText) findViewById(R.id.authorET);
    balanceET=(EditText) findViewById(R.id.balanceET);

    bookLV.setOnItemClickListener(new MyOnItemClickListener());
    }

    public void add(View v){
        String name=nameET.getText().toString().trim();
        String author=authorET.getText().toString().trim();
        String balance=balanceET.getText().toString().trim();
        Book b=new Book(name,author,balance.equals("")?0:Integer.parseInt(balance));
        dao.insert(b);
        list.add(b);
        adapater.notifyDataSetChanged();
        bookLV.setSelection(bookLV.getCount()-1);
        nameET.setText("");
        authorET.setText("");
        balanceET.setText("");
    }

    private class Myadapter extends BaseAdapter{
        public int getCount(){
            return list.size();
        }
        public Object getItem(int position){
            return list.get(position);
        }
        public long getItemId(int position){
            return position;
        }
        public View getView(int position, View convertView, ViewGroup parent){
            View item=convertView!=null?convertView:View.inflate(getApplicationContext(),R.layout.activity_soft1714080902201_item,null);
            TextView idTV=(TextView)item.findViewById(R.id.idTV);
            TextView nameTV=(TextView)item.findViewById(R.id.nameTV);
            TextView authorTV=(TextView)item.findViewById(R.id.authorTV);
            TextView balanceTV=(TextView)item.findViewById(R.id.balanceTV);
            final Book b=list.get(position);
            idTV.setText(b.getId()+"");
            nameTV.setText(b.getName());
            authorTV.setText(b.getAuthor());
            balanceTV.setText(b.getBalance()+"");
            ImageView upIV=(ImageView)item.findViewById(R.id.upIV);
            ImageView downIV=(ImageView)item.findViewById(R.id.downIV);
            ImageView deleteIV=(ImageView)item.findViewById(R.id.deleteIV);

            //向上箭头的点击事件触发方法
            upIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    b.setBalance(b.getBalance()+1);
                    notifyDataSetChanged();
                    dao.update(b);
                }
            });
            //向下箭头的点击事件触发方法
            downIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    b.setBalance(b.getBalance()-1);
                    notifyDataSetChanged();
                    dao.update(b);
                }
            });
            //删除图片的点击事件触发方法
            deleteIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    android.content.DialogInterface.OnClickListener listener=new android.content.DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog,int which){
                            list.remove(b);
                            dao.delete(b.getId());
                            notifyDataSetChanged();
                        }
                    };
                    //创建对话框
                    AlertDialog.Builder builder=new AlertDialog.Builder(Soft1714080902201_Activity_3.this);
                    builder.setTitle("确定要删除吗？");
                    builder.setPositiveButton("确定",listener);
                    builder.setNegativeButton("取消",null);
                    builder.show();
                }
            });
            return item;
        }
    }
    private class MyOnItemClickListener implements AdapterView.OnItemClickListener{
        public void onItemClick(AdapterView<?>parent,View view,int position,long id){
            Book b=(Book) parent.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(),b.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
