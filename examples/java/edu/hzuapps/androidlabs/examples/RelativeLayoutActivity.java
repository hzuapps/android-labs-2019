package edu.hzuapps.androidlabs.examples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import edu.hzuapps.androidlabs.R;

public class RelativeLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_layout);

        View root = findViewById(R.id.relative_layout);

        //RelativeLayout relativeLayout = (RelativeLayout) root;
//
//        relativeLayout.setId(R.id.relative_layout);
//        relativeLayout.setGravity(1);
//        relativeLayout.setIgnoreGravity(R.id.relative_layout);

        //RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams();
        //ViewGroup.LayoutParams params = relativeLayout.getLayoutParams();
    }

}
