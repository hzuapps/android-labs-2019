package edu.hzuapps.androidlabs.soft1714080902124;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.myfirstapp.R;


public class Soft1714080902124Activity extends AppCompatActivity {


    private Button button;
    private Button button_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902124_activity);
        ActionBar actionBar = getSupportActionBar();    //标题框
        if (actionBar != null) {
            actionBar.hide();
        }

        button = (Button) findViewById(R.id.button);         //编辑页面
        button_search = (Button) findViewById(R.id.search_btn); //搜索
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Soft1714080902124Activity.this, EditTextActivity.class);
                startActivity(i);
            }
        });
        button_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Soft1714080902124Activity.this, SearchActivity.class);
                startActivity(i);
            }
        });
    }
}
