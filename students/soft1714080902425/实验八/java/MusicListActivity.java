package edu.androidlabs.thirdtest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MusicListActivity extends AppCompatActivity {
    private Button listbtn1 = null;
    private Button listbtn2 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musiclist);
        listbtn1 = (Button) findViewById(R.id.list1);
        listbtn1.setOnClickListener(new MusicListActivity.MyButtonListener1());
        listbtn2 = (Button) findViewById(R.id.list2);
        listbtn2.setOnClickListener(new MusicListActivity.MyButtonListener2());
    }

    private class MyButtonListener1 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MusicListActivity.this, MusicList1.class);
            startActivity(intent);
        }
    }
    private class MyButtonListener2 implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MusicListActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
