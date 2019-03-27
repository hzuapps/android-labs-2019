package edu.hzuapps.androidlabs.com1714080901108;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class Com1714080901108Activity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com1714080901108);
        Button button=(Button) findViewById(R.id.button);
        imageView=(ImageView) findViewById(R.id.img_list);
        button.setOnClickListener(this);
    }

        @Override
    public void onClick(View view) {
        Intent intent=new Intent(Com1714080901108Activity.this,SecondActivity.class);
        startActivity(intent);
}
}