package com.itcast.diarybill;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.itcast.diarybill.bean.Bill;
import com.itcast.diarybill.databases.BillDao;

import java.util.List;

public class BillRecord extends AppCompatActivity {

    //需要适配的数据集合
    private List<Bill> list;
    //数据库的增删改查操作类
    private BillDao dao;
    //适配器
    private Myadapter adapter;
    //ListView
    private ListView billLV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_record);
        //初始化控件
        initView();
        dao=new BillDao(this);
        //从数据库查询出所有的数据
        list=dao.queryAll();
        adapter=new Myadapter();
        billLV.setAdapter(adapter);
    }
    //初始化控件
    private void initView(){
        billLV=findViewById(R.id.BillLV);
        //添加监听器，监听条目点击事件
        billLV.setOnItemClickListener(new MyOnItemClickListener());
    }
    //自定义一个适配器(把数据装到ListView的工具)
    private class Myadapter extends BaseAdapter {
        public int getCount(){//获取条目总数
            return list.size();
        }
        public Object getItem(int position){ //根据位置获取对象
            return list.get(position);
        }
        public long getItemId(int position){ //根据位置获取id
            return position;
        }
        //获取一个条目视图
        public View getView(int position, View convertView, ViewGroup parent){
            //重用convertView
            View item=convertView!=null?convertView:View.inflate(getApplicationContext(),R.layout.bill_item,null);
            //获取视图的TextView
            TextView billDay=item.findViewById(R.id.bill_day);
            TextView billIncome=item.findViewById(R.id.bill_income);
            TextView billCoast=item.findViewById(R.id.bill_coast);
            //获取当前位置的Account对象
            final Bill b=list.get(position);
            //把Account对象的数据放到TextView中
            billDay.setText(b.getDate()+"");
            billIncome.setText(b.getIncome()+"元");
            billCoast.setText(b.getCoast()+"元");
            ImageView deletepIV=item.findViewById(R.id.delete_record);

            //删除图片的点击事件触发的方法
            deletepIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //删除图片之前首先弹出一个对话框
                    android.content.DialogInterface.OnClickListener listener=new android.content.DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            list.remove(b);//从集合中删除
                            dao.delete(b.getId());//从数据库删除
                            notifyDataSetChanged();//刷新页面
                        }
                    };
                    //创建对话框
                    AlertDialog.Builder builder=new AlertDialog.Builder(BillRecord.this);
                    builder.setTitle("确定要删除吗");//设置标题
                    //设置确定按钮的文本以及监听器
                    builder.setPositiveButton("确定",listener);
                    builder.setNegativeButton("取消",null);//设置取消对话框
                    builder.show();//显示对话框
                }
            });

            return item;
        }
    }
    //ListView的Item点击事件
    private class MyOnItemClickListener implements AdapterView.OnItemClickListener{
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //获取点击位置上的数据
            Bill b=(Bill) parent.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(),b.toString(),Toast.LENGTH_SHORT).show();
        }
    }
}
