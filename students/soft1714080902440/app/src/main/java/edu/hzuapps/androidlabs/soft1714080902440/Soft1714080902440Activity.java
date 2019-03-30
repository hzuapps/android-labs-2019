package edu.hzuapps.androidlabs.soft1714080902440;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import soft1714080902440.androidlabs.hzuapps.edu.soft1714080902440.R;

public class Soft1714080902440Activity extends AppCompatActivity {
    Button button6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902440_activity);
        button6=findViewById(R.id.button6);
        final Intent intent=getIntent();
        String data=intent.getStringExtra("001");    //只是得到data而已，但是还没有显示
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("002",12345678);
                setResult(0x02,intent);
                finish();
            }
        });
    }
}