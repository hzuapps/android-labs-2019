package edu.hzuapps.androidlabs.examples;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.hzuapps.androidlabs.BackActivity;
import edu.hzuapps.androidlabs.R;

public class ActivityBasic2Activity extends BackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_basic2);

        final Activity thisActivity = this;

        Button btnClose = (Button) findViewById(R.id.button_close);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //thisActivity.finishActivity(2016);
                thisActivity.finish();
            }
        });
    }

}
