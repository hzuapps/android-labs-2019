package package edu.hzuapps.androidlabs.soft1714080902412;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Soft1714080902412Activity4 extends AppCompatActivity implements View.OnClickListener{
    private Button button1;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft17140809024124);
        button1=(Button)findViewById(R.id.button41);
        button1.setOnClickListener(this);
        imageView=(ImageView)findViewById(R.id.imageView);
        imageView.setOnClickListener(this);
    }
    public void onClick(View v) {
        Intent intent1 = new Intent(Soft1714080902412Activity4.this, Soft1714080902412Activity.class);
        startActivity(intent1);
        Soft1714080902412Activity4.this.finish();
    }
}
