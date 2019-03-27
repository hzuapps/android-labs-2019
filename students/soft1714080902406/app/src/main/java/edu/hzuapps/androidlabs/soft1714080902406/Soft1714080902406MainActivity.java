package edu.hzuapps.androidlabs.soft1714080902406;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Soft1714080902406MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902406_activity_main);

        ImageView imageView =(ImageView) findViewById(R.id.imageView2);

        View.OnClickListener onClickListener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(Soft1714080902406MainActivity.this,ResultActivity.class);
                startActivity(intent);
            }
        };

        imageView.setOnClickListener(onClickListener);


    }
}
