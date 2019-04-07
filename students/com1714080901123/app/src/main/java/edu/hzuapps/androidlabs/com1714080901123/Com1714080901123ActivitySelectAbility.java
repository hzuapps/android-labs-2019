package edu.hzuapps.androidlabs.com1714080901123;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

public class Com1714080901123ActivitySelectAbility extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com1714080901123_select_ability);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ImageButton imageButton01 = (ImageButton)findViewById(R.id.imgButton_select_ability_main_01);
        ImageButton imageButton02 = (ImageButton)findViewById(R.id.imgButton_select_ability_main_02);
        ImageButton imageButton03 = (ImageButton)findViewById(R.id.imgButton_select_ability_main_03);
        ImageButton imageButton04 = (ImageButton)findViewById(R.id.imgButton_select_ability_main_04);

        imageButton01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButton(1);
            }
        });
        imageButton02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButton(2);
            }
        });
        imageButton03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButton(3);
            }
        });
        imageButton04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickButton(4);
            }
        });
    }

    protected void clickButton(int i) {
        Intent intent = new Intent();
        intent.putExtra("AbilitySelect",i);
        setResult(2, intent);
        finish();
    }
}
