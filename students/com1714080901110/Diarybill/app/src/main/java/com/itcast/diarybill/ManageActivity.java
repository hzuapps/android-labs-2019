package com.itcast.diarybill;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.itcast.diarybill.bean.Bill;
import com.itcast.diarybill.databases.BillDao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ManageActivity extends AppCompatActivity {
    private EditText et_income;
    private EditText et_coast;
    private Button btn_save;
    private Button btn_detail;
    //数据库操作类
    private BillDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record_coast);
        initView();
        dao=new BillDao(this);
    }
    //初始化控件
    private void initView(){
        //从布局文件中获取数据
        et_coast=findViewById(R.id.et_coast);
        et_income=findViewById(R.id.et_income);
        btn_save=findViewById(R.id.btn_save);
        btn_detail=findViewById(R.id.btn_detail);
        //设计响应事件
        btn_detail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ManageActivity.this,BillRecord.class);
                startActivity(intent);
            }
        });
        //删除图片的点击事件触发的方法
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除图片之前首先弹出一个对话框
                android.content.DialogInterface.OnClickListener listener=new android.content.DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] strNow = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).toString().split("-");
                        Integer year = Integer.parseInt(strNow[0]);
                        Integer month = Integer.parseInt(strNow[1]);
                        Integer day = Integer.parseInt(strNow[2]);
                        String date=year.toString()+"-"+month.toString()+"-"+day.toString();
                        String income=et_income.getText().toString().trim();
                        String coast=et_coast.getText().toString().trim();
                        Bill b=new Bill(date,income.equals("")?0:Integer.parseInt(income),coast.equals("")?0:Integer.parseInt(coast));
                        dao.insert(b);

                    }
                };
                //创建对话框
                AlertDialog.Builder builder=new AlertDialog.Builder(ManageActivity.this);
                builder.setTitle("确定要保存吗");//设置标题
                //设置确定按钮的文本以及监听器
                builder.setPositiveButton("确定",listener);
                builder.setNegativeButton("取消",null);//设置取消对话框
                builder.show();//显示对话框
            }
        });
    }
}
