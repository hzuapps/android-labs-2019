package edu.hzuapps.androidlabs.com1714080901106;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class Com1714080901106Activity01 extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private Activity_Introduction introduction;
    private Activity_Checkpoint checkpoint;
    private Activity_Collection collection;
    private Activity_Achievement achievement;
    private Fragment[] fragments;
    private int lastFragment;

    private void switchFragment(int lastFragment, int index){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastFragment]);
        if(fragments[index].isAdded()==false){
            transaction.add(R.id.linearLayout, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

    private void initFragment(){
        introduction = new Activity_Introduction();
        checkpoint = new Activity_Checkpoint();
        collection = new Activity_Collection();
        achievement = new Activity_Achievement();
        fragments = new Fragment[]{introduction, checkpoint, collection, achievement};

        lastFragment = 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.linearLayout, introduction).show(introduction).commit();   //默认为Home
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                mOnNavigationItemSelectedListener
        );
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_introduction:{
                    if (lastFragment!=0){
                        switchFragment(lastFragment, 0);
                        lastFragment = 0;
                    }
                    return true;
                }
                case R.id.navigation_checkpoint:{
                    if(lastFragment!=1){
                        switchFragment(lastFragment, 1);
                        lastFragment = 1;
                    }
                    return true;
                }
                case R.id.navigation_collection:{
                    if(lastFragment!=2){
                        switchFragment(lastFragment, 2);
                        lastFragment = 2;
                    }
                    return true;
                }
                case R.id.navigation_achievement:{
                    if(lastFragment!=1){
                        switchFragment(lastFragment, 3);
                        lastFragment = 3;
                    }
                    return true;
                }
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_01);
        initFragment();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
