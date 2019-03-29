package edu.hzuapps.androidlabs.soft1714080902313;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Soft1714080902313Activity1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902106_activity1);
        Button button1=(Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Soft1714080902313Activity1.this,Soft1714080902313Activity2.class);
                startActivity(intent);
                switch (v.getId()){
                    case R.id.button1:
                        ProgressDialog progressDialog = new ProgressDialog(Soft1714080902313Activity1.this);
                        progressDialog.setTitle("正在加载。。。");
                        progressDialog.setMessage("Loading...");
                        progressDialog.setCancelable(true);
                        progressDialog.show();
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
