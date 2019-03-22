package edu.hzuapps.androidlabs.soft1714080902227;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.content.Intent;
import com.example.tuku.R;

public class soft1714080902227Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appfirst);
       // getWindow().setBackgroundDrawableResource(R.drawable.fenmian);//设置背景照片
        ActionBar actionbar=getSupportActionBar();
        if(actionbar!=null)
        {
            actionbar.hide();
        }
        ImageButton btn2=findViewById(R.id.btn2);
        ImageButton btn3=findViewById(R.id.btn3);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(soft1714080902227Activity2.this, soft1714080902227Activity.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(soft1714080902227Activity2.this, soft1714080902227Activity3.class);
                startActivity(intent);
            }
        });

    }



    }

