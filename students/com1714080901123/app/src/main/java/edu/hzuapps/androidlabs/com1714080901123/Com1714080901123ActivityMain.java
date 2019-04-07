package edu.hzuapps.androidlabs.com1714080901123;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class Com1714080901123ActivityMain extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private Com1714080901123FragmentHome fragmentHome;
    private Com1714080901123FragmentMap fragmentMap;
    private Com1714080901123FragmentClothing fragmentClothing;
    private Com1714080901123FragmentCounter fragmentCounter;
    private Com1714080901123FragmentAmiibo fragmentAmiibo;
    private Fragment[] fragments;
    private int lastFragment;  //用于记录上个选择的 Fragment
    /*这个方法控制的话，底部菜单栏不会跟着改变
    private BottomNavigationView.OnNavigationItemSelectedListener changeFrament = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()){
                case R.id.navigation_home:{
                    if (lastFragment!=0){
                        switchFragment(lastFragment, 0);
                        lastFragment = 0;
                    }
                    return true;
                }
                case R.id.navigation_map:{
                    if(lastFragment!=1){
                        switchFragment(lastFragment, 1);
                        lastFragment = 1;
                    }
                    return true;
                }
            }
            return false;
        }
    };
    //*/
    private void switchFragment(int lastFragment, int index){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[lastFragment]);  //隐藏上个Fragment
        if(fragments[index].isAdded()==false){
            transaction.add(R.id.tablehome, fragments[index]);
        }
        transaction.show(fragments[index]).commitAllowingStateLoss();
    }

    private void initFragment(){
        fragmentHome = new Com1714080901123FragmentHome();
        fragmentMap = new Com1714080901123FragmentMap();
        fragmentClothing = new Com1714080901123FragmentClothing();
        fragmentCounter = new Com1714080901123FragmentCounter();
        fragmentAmiibo = new Com1714080901123FragmentAmiibo();
        fragments = new Fragment[]{fragmentHome, fragmentMap, fragmentClothing, fragmentCounter, fragmentAmiibo};
        lastFragment = 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.tablehome, fragmentHome).show(fragmentHome).commit();   //默认为Home
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                mOnNavigationItemSelectedListener
        );
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {   //底部菜单监听
            switch (item.getItemId()){
                case R.id.navigation_home:{
                    if (lastFragment!=0){
                        switchFragment(lastFragment, 0);
                        lastFragment = 0;
                    }
                    return true;
                }
                case R.id.navigation_map:{
                    if(lastFragment!=1){
                        switchFragment(lastFragment, 1);
                        lastFragment = 1;
                    }
                    return true;
                }
                case R.id.navigation_clothing:{
                    if(lastFragment!=2){
                        switchFragment(lastFragment, 2);
                        lastFragment = 2;
                    }
                    return true;
                }
                case R.id.navigation_counter:{
                    if(lastFragment!=1){
                        switchFragment(lastFragment, 3);
                        lastFragment = 3;
                    }
                    return true;
                }
                case R.id.navigation_amiibo:{
                    if(lastFragment!=1){
                        switchFragment(lastFragment, 4);
                        lastFragment = 4;
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
        setContentView(R.layout.activity_main);
        initFragment(); //调用初始化

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
