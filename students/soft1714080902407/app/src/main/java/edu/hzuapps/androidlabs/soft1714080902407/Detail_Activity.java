package edu.hzuapps.androidlabs.soft1714080902407;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Detail_Activity extends AppCompatActivity  implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
    }

    public void onClick(View view){
        Intent intent = new Intent(Detail_Activity.this,Soft1714080902407Activity.class);
        startActivity(intent);
    }
}
