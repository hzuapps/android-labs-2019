package edu.hzuapps.androidlabs.soft1714080902124;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.myfirstapp.R;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditTextActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button btn_ok;
    private Button btn_cancel;
    private NotesDB db;
    private SQLiteDatabase database;
    public static int ENTER_STATE = 0;
    public static String last_content;
    public static int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置没有标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit_text);
        textView = (TextView) findViewById(R.id.edit_title_text);
//        Date date = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
//        final String dateString = simpleDateFormat.format(date);
        textView.setText("新笔记");

        editText = (EditText) findViewById(R.id.edit_text);
        //设置软键盘自动弹出
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        db = new NotesDB(this);
        database = db.getReadableDatabase();
        Bundle bundle = this.getIntent().getExtras();
        last_content = bundle.getString("info");
        Log.d("LAST_CONTENT", last_content);
        editText.setText(last_content);
        //确认按钮的点击事件
        btn_ok = (Button) findViewById(R.id.title_save);
        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取日志内容
                String content = editText.getText().toString();
                Log.d("log1", content);
                //获取写日志时间
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dateNum = dateFormat.format(date);
                String sql;
                String sql_count = "SELECT COUNT(*) FROM note";
                SQLiteStatement sqLiteStatement = database.compileStatement(sql_count);
                long count = sqLiteStatement.simpleQueryForLong();
                Log.d("COUNT", count + "");
                Log.d("ENTER_STATE", ENTER_STATE + "");
                //添加一个新日志
                if (ENTER_STATE == 0) {
                    if (!content.equals("")) {
                        sql = "insert into " + NotesDB.TABLE_NAME_NOTES + " values(" + count + "," + "'" + content + "'" + "," + "'" + dateNum + "')";
                        Log.d("LOG", sql);
                        database.execSQL(sql);
                    }
                } else {
                    Log.d("执行命令", "执行了该函数");
                    String updatesql = "update note set content ='" + content + "' where _id=" + id;
                    database.execSQL(updatesql);
                }
                Intent data = new Intent();
                setResult(2, data);
                finish();
            }
        });
        btn_cancel = (Button) findViewById(R.id.title_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
    }
}