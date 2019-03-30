package edu.hzuapps.androidlabs.examples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsoluteLayout;

import edu.hzuapps.androidlabs.R;

public class AbsoluteLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absolute_layout);

        View root = findViewById(R.id.absolute_layout);

        AbsoluteLayout absoluteLayout = (AbsoluteLayout) root;

    }

}
