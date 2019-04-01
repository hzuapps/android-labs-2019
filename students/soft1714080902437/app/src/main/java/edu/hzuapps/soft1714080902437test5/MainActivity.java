package edu.hzuapps.soft1714080902437test5;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;
import java.util.List;

import edu.hzuapps.soft1714080902437test5.bean.Account;
import edu.hzuapps.soft1714080902437test5.dao.AccountDao;

public class MainActivity extends Activity {

    private List<Account> list;
    //需要增删改查操作类
    private AccountDao dao;

    private EditText cnameET;

    private EditText tnameET;
    //适配器
    private MyAdapter adapter;
    //Listview
    private ListView accountLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        dao = new AccountDao(this);
        //从数据库查询所有数据
        list = dao.queryAll();
        adapter = new MyAdapter();
        accountLV.setAdapter(adapter);//给ListView添加适配器（自动把数据生成条目）
    }

    //初始化控件
    private void initView() {
        accountLV = (ListView) findViewById(R.id.accountLV);
        cnameET = (EditText) findViewById(R.id.cnameET);
        tnameET = (EditText) findViewById(R.id.tnameET);
        //添加监听器，监听条目点击事件
        accountLV.setOnItemClickListener(new MyOnItemClickListener());
    }

    //activity_main.xml对应的inageview的点击事件触发的方法
    public void add(View v) {
        String cname = cnameET.getText().toString().trim();
        String tname = tnameET.getText().toString().trim();
        if ("".equals(cname) || "".equals(tname)) {
            Toast.makeText(getApplicationContext(), "课程名字和老师名字不能为空", Toast.LENGTH_LONG).show();
            return ;
        }
        //三目运算balance.equals("")则等于0
        //如果balance不是空字符串，则进行强制类型转换
        Account a = new Account(cname,tname);
        dao.insert(a);//插入数据库
        list.add(a);//插入集合
        adapter.notifyDataSetChanged();//刷新界面
        //选中最后一个
        accountLV.setSelection(accountLV.getCount() -1);
        cnameET.setText("");
        tnameET.setText("");
    }

    //自定义一个适配器（把数据装到ListView的工具）
    private class MyAdapter extends BaseAdapter {
        public int getCount() {   //获取条目总数
            return list.size();
        }

        public Object getItem(int position) //根据位置获取对象
        {
            return list.get(position);
        }

        public long getItemId(int position) //根据位置获取id
        {
            return position;
        }

        //获取一个条目视图
        public View getView(int position, View convertView, ViewGroup parent) {
            //重用converView
            View item = convertView != null ? convertView : View.inflate(getApplicationContext(), R.layout.item, null);
            //获取该视图中的textview
            TextView idTV = (TextView) item.findViewById(R.id.idTV);
            TextView cnameTV = (TextView) item.findViewById(R.id.cnameTV);
            TextView tnameTV = (TextView) item.findViewById(R.id.tnameTV);
            //根据当前位置获取Account对象
            final Account a = list.get(position);
            //把Account对象中的数据放到textview中
            idTV.setText(a.getId() + "");
            cnameTV.setText(a.getName1());
            tnameTV.setText(a.getName2() + "");
            ImageView deleteIV = (ImageView) item.findViewById(R.id.deleteIV);

            //删除图片的点击事件触发的方法
            deleteIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //删除数据之前首先弹出一个对话框
                    android.content.DialogInterface.OnClickListener listener =
                            new android.content.DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    list.remove(a);//从集合删除
                                    dao.delete(a.getId());//从数据库删除
                                    notifyDataSetChanged();//刷新界面
                                }
                            };
                    //创建对话框
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("确定要删除吗？");           //设置标题
                    //设置确定按钮的文本以及监听器
                    builder.setPositiveButton("确定", listener);
                    builder.setNegativeButton("取消", null);
                    builder.show();
                }
            });
            return item;
        }
    }

    //Listview的item点击事件
    private class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //获取点击位置上的数据
            Account a = (Account) parent.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(), a.toString(), Toast.LENGTH_SHORT).show();
        }
    }

}





