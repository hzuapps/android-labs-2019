package edu.hzuapps.androidlabs.soft1714080902111;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import edu.hzuapps.androidlabs.soft1714080902111.R;

public class Soft1714080902111MainActivity extends AppCompatActivity {

    private Button mBtnTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft1714080902111mainactivity);
        mBtnTextView = findViewById(R.id.btnTextView1);
        mBtnTextView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Soft1714080902111MainActivity.this, Soft1714080902111TestActivity.class);
                startActivity(intent);
            }
        });
    };

}
