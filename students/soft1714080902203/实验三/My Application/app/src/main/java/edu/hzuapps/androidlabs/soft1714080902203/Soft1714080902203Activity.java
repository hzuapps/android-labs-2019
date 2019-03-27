package edu.hzuapps.androidlabs.soft1714080902203;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
/*new*/
public class Soft1714080902203Activity extends AppCompatActivity {
    Button button = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902203_activity);
        button = findViewById(R.id.bt7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Soft1714080902203Activity.this,Soft1714080902203Activity2.class);
                startActivity(intent);
            }
        });
    }
}
