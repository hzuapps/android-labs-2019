package edu.hzuapps.androidlabs.soft1714080902223;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Map;

import edu.hzuapps.androidlabs.presenter.TaskService;

public class DetailActivity extends AppCompatActivity {

    ImageButton imageButton;
    TextView tv_title;
    TextView tv_content;

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

    }

    @Override
    protected void onStart() {
        super.onStart();
        //获取传递过来的id的值
        Intent intent = getIntent();
        long id = intent.getLongExtra("id", -1);
        tv_title = findViewById(R.id.detail_title);
        tv_content = findViewById(R.id.detail_body);
        TaskService taskService = new TaskService(this);
        Map<String, String> map = taskService.get(id);
        tv_title.setText(map.get("title"));
        tv_content.setText(map.get("content"));

    }
}
