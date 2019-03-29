package com.hzuandroid.soft1714080902408;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MmGwsy extends Mm {

    private Button test_btn=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout._03);
        test_btn=(Button) findViewById(R.id.Button01);
        test_btn.setOnClickListener(new Mm.MyButtonListener());
    }
    private class MyButtonListener implements View.OnClickListener{
        @Override
        public void onClick(View view){
            Intent intent=new Intent(MmGwsy.this,Gwsy.class);
            startActivity(intent);
        }
    }
}
