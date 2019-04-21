package edu.hzuapps.androidlabs.soft1714080902414;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResourceActivity extends AppCompatActivity implements View.OnClickListener{

        private Button button;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_resource);
            button=(Button)findViewById(R.id.but);
            button.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            Intent intent =new Intent(ResourceActivity.this, WordtestActivity.class);
            startActivity(intent);
        }
    }
