package edu.hzuapps.androidlabs.soft1714080902223;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Map;

import edu.hzuapps.androidlabs.listview.HomeListAdapter;
import edu.hzuapps.androidlabs.presenter.TaskService;

public class DetailActivity extends AppCompatActivity {

    ImageButton imageButton;
    TextView tv_title;
    TextView tv_content;
    Button btn_edit;
    Button btn_delete;
    TextView tv_last_time;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageButton = findViewById(R.id.detail_ibtn);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        btn_delete = findViewById(R.id.btn_delete);
        btn_edit = findViewById(R.id.btn_edit);
        //跳转到编辑页面的监听器
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, EditActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
        //删除后跳转到主页
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailActivity.this, HomeActivity.class);
                TaskService.INSTANCE.delete(position);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //获取传递过来的position的值
        Intent intent = getIntent();
        position = intent.getIntExtra("position", -1);
        tv_title = findViewById(R.id.detail_title);
        tv_content = findViewById(R.id.detail_content);
        tv_last_time = findViewById(R.id.last_time);
        TaskService taskService = TaskService.INSTANCE.getTaskService(this);
        Map<String, String> map = taskService.get(position);
        tv_title.setText(map.get("title"));
        tv_content.setText(map.get("content"));
        tv_last_time.setText("提醒时间 " + map.get("last_time"));
    }
}
