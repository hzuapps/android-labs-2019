package edu.hzuapps.androidlabs.examples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import edu.hzuapps.androidlabs.R;

public class LinearLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // XML
        setContentView(R.layout.activity_linear_layout);
        // Java

        View root = findViewById(R.id.linear_layout);
        LinearLayout linearLayout = (LinearLayout) root;
        linearLayout.setId(R.id.linear_layout);
        linearLayout.setBaselineAligned(true);
        linearLayout.setBaselineAlignedChildIndex(0);
        linearLayout.setGravity(1);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.setWeightSum(1.0f);

    }

}
