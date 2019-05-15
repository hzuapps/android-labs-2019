package fun.zzti.memoire;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import fun.zzti.memoire.database.MyDB;
import fun.zzti.memoire.enity.Record;

/**
 * create_by Android Studio
 *
 * @author zouguo0212@
 * @package_name fun.zzti
 * @description
 * @date 2018/10/27 13:20
 */
public class AmendActivity extends BaseActivity implements View.OnClickListener{

    private final static String TAG = "AmendActivity";

    MyDB myDB;
    private Button btnSave;
    private Button btnBack;
    private TextView amendTime;
    private TextView amendTitle;
    private EditText amendBody;
    private Record record;
    private AlertDialog.Builder dialog;

    private Button btnUpcoming;
    private Button btnNotice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.amend_linear_layout);
        init();

    }


    @Override
    public void onClick(View v) {
        String body;
        body = amendBody.getText().toString();
        switch (v.getId()){
            case R.id.button_save:
                if (updateFunction(body)){
                    intentStart();
                }
                break;
            case R.id.button_back:
                showDialog(body);
                clearDialog();
                break;
            case R.id.btn_amend_menu_notice:
                Log.i("AmendActivity","这是修改页面的提醒按钮被点击");
                break;
            case R.id.btn_amend_menu_upcoming:
                Log.i("AmendActivity","这是修改页面的待办按钮被点击");
                break;
            default:
                break;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //当返回按键被按下
            if (!isShowIng()){
                showDialog(amendBody.getText().toString());
                clearDialog();
            }
        }
        return false;
    }

    /*
     * 初始化函数
     */
    @SuppressLint("SetTextI18n")
    void init(){
        myDB = new MyDB(this);
        btnBack = findViewById(R.id.button_back);
        btnSave = findViewById(R.id.button_save);
        amendTitle = findViewById(R.id.amend_title);
        amendBody = findViewById(R.id.amend_body);
        amendTime = findViewById(R.id.amend_title_time);

        btnUpcoming = findViewById(R.id.btn_amend_menu_upcoming);
        btnNotice = findViewById(R.id.btn_amend_menu_notice);

        btnSave.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        btnNotice.setOnClickListener(this);
        btnUpcoming.setOnClickListener(this);

        Intent intent = this.getIntent();
        if (intent!=null){

            record = new Record();

            record.setId(Integer.valueOf(intent.getStringExtra(MyDB.RECORD_ID)));
            record.setTitleName(intent.getStringExtra(MyDB.RECORD_TITLE));
            record.setTextBody(intent.getStringExtra(MyDB.RECORD_BODY));
            record.setCreateTime(intent.getStringExtra(MyDB.RECORD_TIME));
            record.setNoticeTime(intent.getStringExtra(MyDB.NOTICE_TIME));

            amendTitle.setText(record.getTitleName());
            String str="";
            if (record.getNoticeTime()!=null){
                str = "    提醒时间："+record.getNoticeTime();
            }
            amendTime.setText(record.getCreateTime()+str);
            amendBody.setText(record.getTextBody());
        }
    }

    /*
     * 返回主界面
     */
    void intentStart(){
        Intent intent = new Intent(AmendActivity.this,MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    /*
     * 保存函数
     */
    boolean updateFunction(String body){

        SQLiteDatabase db;
        ContentValues values;

        boolean flag = true;
        if (body.length()>200){
            Toast.makeText(this,"内容过长",Toast.LENGTH_SHORT).show();
            flag = false;
        }
        if(flag){
            // update
            db = myDB.getWritableDatabase();
            values = new ContentValues();
            values.put(MyDB.RECORD_BODY,body);
            values.put(MyDB.RECORD_TIME,getNowTime());
            db.update(MyDB.TABLE_NAME_RECORD,values,MyDB.RECORD_ID +"=?",
                    new String[]{record.getId().toString()});
            Toast.makeText(this,"修改成功",Toast.LENGTH_SHORT).show();
            db.close();
        }
        return flag;
    }

    /*
     * 弹窗函数
     * @param title
     * @param body
     * @param createDate
     */
    void showDialog(final String body){
        dialog = new AlertDialog.Builder(AmendActivity.this);
        dialog.setTitle("提示");
        dialog.setMessage("是否保存当前编辑内容");
        dialog.setPositiveButton("保存",
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                updateFunction(body);
                intentStart();
                    }
                });

        dialog.setNegativeButton("取消",
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                intentStart();
                    }
                });
        dialog.show();
    }

    void clearDialog(){
        dialog = null;
    }

    boolean isShowIng(){
        if (dialog!=null){
            return true;
        }else{
            return false;
        }
    }

    /*
     * 得到当前时间
     * @return
     */
    String getNowTime(){
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        //获取当前时间
        Date date = new Date(System.currentTimeMillis());
        return simpleDateFormat.format(date);
    }

}
