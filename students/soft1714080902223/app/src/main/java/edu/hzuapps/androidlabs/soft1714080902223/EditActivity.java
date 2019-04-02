package edu.hzuapps.androidlabs.soft1714080902223;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.hzuapps.androidlabs.presenter.TaskService;

public class EditActivity extends AppCompatActivity {

    private Button mBtn;
    private EditText title;
    private EditText content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 调用父亲保存的状态
        super.onCreate(savedInstanceState);
        // 布局方法
        setContentView(R.layout.activity_edit);
        title = findViewById(R.id.edit_title);
        content = findViewById(R.id.edit_content);
        mBtn = findViewById(R.id.btn);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //标题栏为空时，提示并焦聚（暂未处理空白字符）
                if(title.getText().length() == 0){
                    Toast toast = Toast.makeText(EditActivity.this, "请输入标题", Toast.LENGTH_SHORT);
                    int[] location = new int[2];
                    title.getLocationInWindow(location);
                    //toast.setView(title);
                    toast.setGravity(Gravity.TOP, location[0], location[1]);
                    toast.show();
                    title.requestFocus();
                }
                //正文为空时，提示并焦聚（暂未处理空白字符）
                else if(content.getText().length() == 0) {
                    Toast toast = Toast.makeText(EditActivity.this, "请输入正文内容", Toast.LENGTH_SHORT);
                    int[] location = new int[2];
                    content.getLocationInWindow(location);
                    toast.setGravity(Gravity.TOP, location[0], location[1]);
                    toast.show();
                    content.requestFocus();
                }
                else{
                    TaskService taskService = new TaskService(EditActivity.this);
                    String titleStr = title.getText().toString();
                    String contentStr = content.getText().toString();
                    long id = taskService.saveTask(titleStr, contentStr);
                    Toast.makeText(EditActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                    //跳转到详情页
                    Intent intent = new Intent(EditActivity.this,
                            DetailActivity.class);
                    //把id传递给DetailActivity
                    intent.putExtra("id", id);
                    startActivity(intent);

                }
            }
        });



    }


}
