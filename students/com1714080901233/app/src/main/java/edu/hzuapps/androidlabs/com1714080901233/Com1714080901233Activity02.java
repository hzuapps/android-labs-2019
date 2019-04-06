package edu.hzuapps.androidlabs.com1714080901233;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Com1714080901233Activity02 extends Activity {
    private TextView tv_date;
    private EditText et_content;
    private Button button7;
    private Button button6;
    private NotesDB DB;
    private SQLiteDatabase dbread;
    public static int ENTER_STATE = 0;
    public static String last_content;
    public static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // 设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.com_1714080901233_activity_02);

        tv_date = (TextView) findViewById(R.id.tv_date);
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = sdf.format(date);
        tv_date.setText(dateString);

        et_content = (EditText) findViewById(R.id.et_content);
        // 设置软键盘自动弹出
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        DB = new NotesDB(this);
        dbread = DB.getReadableDatabase();

        Bundle myBundle = this.getIntent().getExtras();
        last_content = myBundle.getString("info");
        Log.d("LAST_CONTENT", last_content);
        et_content.setText(last_content);
        // 确认按钮的点击事件
        button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                // 获取日志内容
                String content = et_content.getText().toString();
                Log.d("LOG1", content);
                // 获取写日志时间
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String dateNum = sdf.format(date);
                String sql;
                String sql_count = "SELECT COUNT(*) FROM note";
                SQLiteStatement statement = dbread.compileStatement(sql_count);
                long count = statement.simpleQueryForLong();
                Log.d("COUNT", count + "");
                Log.d("ENTER_STATE", ENTER_STATE + "");
                // 添加一个新的日志
                if (ENTER_STATE == 0) {
                    if (!content.equals("")) {
                        sql = "insert into " + NotesDB.TABLE_NAME_NOTES
                                + " values(" + count + "," + "'" + content
                                + "'" + "," + "'" + dateNum + "')";
                        Log.d("LOG", sql);
                        dbread.execSQL(sql);
                    }
                }
                // 查看并修改一个已有的日志
                else {
                    Log.d("执行命令", "执行了该函数");
                    String updatesql = "update note set content='"
                            + content + "' where _id=" + id;
                    dbread.execSQL(updatesql);
                    // et_content.setText(last_content);
                }
                Intent data = new Intent();
                setResult(2, data);
                finish();
            }
        });
        button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(new OnClickListener() {
            public void onClick(View arg0) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
    }
}
