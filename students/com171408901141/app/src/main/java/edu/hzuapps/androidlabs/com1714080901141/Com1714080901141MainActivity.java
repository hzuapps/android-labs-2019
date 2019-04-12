package edu.hzuapps.androidlabs.com1714080901141;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Com1714080901141MainActivity extends Com1714080901141BaseActivity {

    private TextView mTextMessage;
    public static boolean exit;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_first:
                    mTextMessage.setText(R.string.title_first);
                    return true;
                case R.id.navigation_second:
                    mTextMessage.setText(R.string.title_second);
                    return true;
                case R.id.navigation_third:
                    mTextMessage.setText(R.string.title_third);
                    return true;
                case R.id.navigation_forth:
                    mTextMessage.setText(R.string.title_forth);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Com1714080901141MainActivity.this,Com1714080901141DialogActivity.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        exit=false;
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView) findViewById(R.id.message);
    }
}
