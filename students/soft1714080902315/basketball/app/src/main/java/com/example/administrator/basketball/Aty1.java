package com.example.administrator.basketball;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Aty1 extends ActionBarActivity {
    private Button btnClose;
    private Button video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty1);
        btnClose=(Button)findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        video=(Button)findViewById(R.id.video);
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Aty1.this, Aty2Activity.class);
                startActivity(i);
            }
        });
        System.out.println("onCreate");
    }

}
