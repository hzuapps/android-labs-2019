package edu.hzuapps.androidlabs.soft1712070504232;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Soft1712070504232Activity extends Activity {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1712070504232_activity);
        textView = (TextView) findViewById(R.id.phone);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1712070504232Activity.this, Soft1712070504232Activity2.class);
                startActivity(intent);
            }
        });
    }
}
