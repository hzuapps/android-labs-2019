package edu.hzuapps.androidlabs.soft1714080902227;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tuku.BankActivity;
import com.example.tuku.MainActivity;
import com.example.tuku.R;
public class soft1714080902227Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);               //选择XML
        //setContentView(R.layout.activity_main);
        getWindow().setBackgroundDrawableResource(R.drawable.fenmian);//设置背景照片
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null)
        {
            actionbar.hide();
        }

        Button btn1=(Button)findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(soft1714080902227Activity.this, soft1714080902227Activity2.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu); //这个函数使不使用好像没有影响

        return true;
    }

    }

