<<<<<<< HEAD
package edu.hzuapps.androidlabs.soft1714080902331;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Soft1714080902331Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902331_activity);
        ImageView btnOpen = (ImageView) findViewById(R.id.imageButton);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Soft1714080902331Activity.this,Soft1714080902331Activity2.class));
            }
        });
        ImageView btnOpen2 = (ImageView) findViewById(R.id.imageButton2);
        btnOpen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Soft1714080902331Activity.this,Soft1714080902331Activity3.class));
            }
        });
    }

}
=======
package edu.hzuapps.androidlabs.soft1714080902331;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Soft1714080902331Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902331_activity);
        TextView btnOpen = (TextView) findViewById(R.id.textview_01);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Soft1714080902331Activity.this,Soft1714080902331Activity2.class));
            }
        });
        TextView btnOpen2 = (TextView) findViewById(R.id.textview_02);
        btnOpen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Soft1714080902331Activity.this,Soft1714080902331Activity3.class));
            }
        });
    }

}
>>>>>>> 04de59006bcef14253116cac674bc33ccca7169d
