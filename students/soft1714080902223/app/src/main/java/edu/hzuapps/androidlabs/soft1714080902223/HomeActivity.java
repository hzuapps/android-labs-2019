package edu.hzuapps.androidlabs.soft1714080902223;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import edu.hzuapps.androidlabs.presenter.TaskService;

public class HomeActivity extends AppCompatActivity {

    private ImageButton mDetailBtn;
    private ListView lv_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mDetailBtn = findViewById(R.id.home_ibtn);
        mDetailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, EditActivity.class);
                startActivity(intent);

            }
        });

        lv_1 = findViewById(R.id.home_list);
        lv_1.setAdapter(new TaskService(this).allListAdapter());
        //为每个list_Item设置点击事件
        lv_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(HomeActivity.this, "您单击了" + position, Toast.LENGTH_SHORT).show();
            }
        });


    }


}

