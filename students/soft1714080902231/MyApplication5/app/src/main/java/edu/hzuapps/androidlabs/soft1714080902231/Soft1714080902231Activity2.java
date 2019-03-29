package edu.hzuapps.androidlabs.soft1714080902231;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Soft1714080902231Activity2 extends AppCompatActivity {
    ImageButton imgButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft17140809022312);
        imgButton =(ImageButton)findViewById(R.id.dierye);
        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Soft1714080902231Activity2.this, Soft1714080902231Activity3.class);
                startActivity(intent);
            }
        });
    }


}
