package edu.hzuapps.androidlabs.com1714080901123;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ImageView;

public class Com1714080901123Activity01 extends AppCompatActivity {

    //static int buttonState = 1;   //记录现在按的是哪个菜单按键
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com171408090112301);  //调用布局文件
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //final Button button1 = (Button)findViewById(R.id.button1);
        final Button button2 = (Button)findViewById(R.id.button2);

        /*
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonState != 1) {
                    //Drawable drawableTop = getResources().getDrawable(R.drawable.squid_while_2);
                    //button1.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null);
                    buttonState = 1;
                }
            }
        });
        //*/
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Drawable drawableTop = getResources().getDrawable(R.drawable.map_white);
                //button2.setCompoundDrawablesWithIntrinsicBounds(null, drawableTop, null, null);
                //buttonState = 2;
                Intent intent = new Intent(Com1714080901123Activity01.this, Com1714080901123Activity02.class);
                startActivity(intent);

            }
        });
    }

}
