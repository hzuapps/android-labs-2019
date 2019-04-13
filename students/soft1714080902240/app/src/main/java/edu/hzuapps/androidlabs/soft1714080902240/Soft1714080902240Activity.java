package edu.hzuapps.androidlabs.soft1714080902240;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902240Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902240_activity);

        final MyImageView myImageView = findViewById(R.id.image_view);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myImageView.setImageURL("http://c.hiphotos.baidu.com/zhidao/pic/item/b3fb43166d224f4ac217158f0df790529922d1a4.jpg");
            }
        });



        Button button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902240Activity.this,Soft1714080902240Activity2.class);
                startActivity(intent);
            }
        });
    }




}
