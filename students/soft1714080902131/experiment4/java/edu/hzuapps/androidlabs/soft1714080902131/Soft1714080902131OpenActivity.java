package edu.hzuapps.androidlabs.soft1714080902131;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Soft1714080902131OpenActivity extends AppCompatActivity {
    private ImageView mim1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902131_open);
        mim1 = findViewById(R.id.im_1);
        mim1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Soft1714080902131OpenActivity.this, Soft1714080902131MainActivity.class);
                startActivity(intent);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(Soft1714080902131OpenActivity.this, Soft1714080902131MainActivity.class);
                Soft1714080902131OpenActivity.this.startActivity(mainIntent);
                Soft1714080902131OpenActivity.this.finish();
            }
        }, 3000);

    }

}
