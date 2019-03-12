package edu.hzuapps.androidlabs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902209_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft1714080902209_main);
        Reg_button=(Button)findViewById(R.id.Main_Reg);
        Reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Soft1714080902209_Main_Avtivity.this, Soft1714080902209_Reg_Avtivity.class);
                startActivity(intent);
            }
        });
    }
    private Button Reg_button;
}
