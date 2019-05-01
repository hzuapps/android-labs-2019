package edu.hzuapps.androidlabs.com1714080901141;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Com1714080901141MainActivity extends Com1714080901141BaseActivity {

    private String TAG="MainActivity";
    private EditText editText;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_first:
                    replaceFragment(new Com1714080901141FragmentMine());
                    return true;
                case R.id.navigation_second:
                    replaceFragment(new Com1714080901141FragmentStudy());
                    return true;
                case R.id.navigation_third:
                    replaceFragment(new Com1714080901141FragmentCommunication());
                    return true;
                case R.id.navigation_forth:
                    replaceFragment(new Com1714080901141FragmentPhotoRecord());
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(Com1714080901141MainActivity.this,Com1714080901141DialogActivity.class);
        startActivity(intent);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {//This is MainActivity's onCreate function.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(new Com1714080901141FragmentMine());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout,fragment);
        transaction.commit();
    }
}