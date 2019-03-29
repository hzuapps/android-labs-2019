package package edu.hzuapps.androidlabs.soft1714080902412;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902412Activity5 extends AppCompatActivity implements View.OnClickListener{
    private Button button1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft17140809024125);
        button1=(Button)findViewById(R.id.button51);
        button1.setOnClickListener(this);
    }
    public void onClick(View v) {
        Intent intent1 = new Intent(Soft1714080902412Activity5.this, Soft1714080902412Activity.class);
        startActivity(intent1);
        Soft1714080902412Activity5.this.finish();
    }
}
