package com.example.soft1706081301317sweepacticity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class WriteActivity extends AppCompatActivity {
    private ListView lv;;
    final static class ViewHolder {
        TextView title;
        TextView content;
        TextView data;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_write);
        super.onCreate(savedInstanceState);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //设置强制竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);





        Button button = findViewById(R.id.eat);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText1 = findViewById(R.id.edit_title);
                EditText editText2 = findViewById(R.id.edit_body);
                EditText editText3 = findViewById(R.id.writeTime);
                Map<String, Object> map=new HashMap<String, Object>();
                map.put("title", editText1.getText());
                map.put("content", editText2.getText());
                map.put("data", editText3.getText());
                allList.getList().add(map);

                editText1.setText("");
                editText3.setText("");
                editText2.setText("负能量存起来了。睡个觉会更好点.晚安💗");
            }
        });
        Button button1 = (Button)findViewById(R.id.past);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WriteActivity.this,PastActivity.class);
                startActivity(intent);
            }
        });

    }




}
