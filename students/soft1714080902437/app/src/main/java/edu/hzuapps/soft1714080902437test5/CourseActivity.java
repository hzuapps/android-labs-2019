package edu.hzuapps.soft1714080902437test5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CourseActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        button = findViewById(R.id.button5);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(CourseActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
