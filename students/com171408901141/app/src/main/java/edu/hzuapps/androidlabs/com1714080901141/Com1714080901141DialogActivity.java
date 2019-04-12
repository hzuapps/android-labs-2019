package edu.hzuapps.androidlabs.com1714080901141;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Com1714080901141DialogActivity extends Com1714080901141BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        Button select_yes=(Button) findViewById(R.id.hope_to_exit);
        Button select_no=(Button) findViewById(R.id.regret_to_exit);
        select_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//exit dialog activity

            }
        });
        select_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Com1714080901141MainActivity.exit=true;
                Com1714080901141ActivityCollector.finishAll();//exit all activity
            }
        });

    }
}
