package edu.androidlabs.thirdtest;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button test_btn=null;
    private Button listbtn=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listbtn=(Button) findViewById(R.id.musicList);
        listbtn.setOnClickListener(new MainActivity.MyButtonListener1());
    }

    private class MyButtonListener1 implements View.OnClickListener{
        @Override
        public void onClick(View view){
            Intent intent = new Intent(MainActivity.this,MusicListActivity.class);
            startActivity(intent);
        }
    }
}
