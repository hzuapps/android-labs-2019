package edu.hzuapps.androidlabs.soft1714080902133;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

public class Soft1714080902133QueryActivity extends AppCompatActivity {


    private SharedPreferences mSharedPref1;
    private SharedPreferences mSharedPref2;

    public static final String BUILDING_KEY1 = "edu.hzuapps.androidlabs.soft1714080902133.BUILDING1";
    public static final String BUILDING_bool1  = "edu.hzuapps.androidlabs.soft1714080902133.bool1";
    public static final String BUILDING_KEY2  = "edu.hzuapps.androidlabs.soft1714080902133.BUILDING2";
    public static final String BUILDING_bool2  = "edu.hzuapps.androidlabs.soft1714080902133.bool2";

    //public  String[] buidering = getResources().getStringArray(R.array.Data01);//所有选项数组
    public  String[] buidering = {"17栋","18栋","19栋","20栋","21栋"};
    public  String[] Nobuidering = {"a401","a402","a403","a404","a405","a406","a407","a408"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902133_queryactivity);
        //界面标题bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.query_toolbar);
        setSupportActionBar(toolbar);//获得ToolBar实例
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);//菜单，默认图片返回图片
        }

        final Spinner spinner01 = findViewById(R.id.building_spinner);
        final Spinner spinner02 = findViewById(R.id.nobuilding_spinner);

        mSharedPref1 = PreferenceManager.getDefaultSharedPreferences(this);
        boolean b = mSharedPref1.getBoolean(BUILDING_bool1,false); //没有设置为false
        if(b){
            String showItems = mSharedPref1.getString(BUILDING_KEY1,"");
            for(int i = 0;i<buidering.length;i++){
                if(buidering[i].equals(showItems)){
                    spinner01.setSelection(i);//获取上次选中的位置，并设置保存
                    break;
                }
            }
        }

        mSharedPref2 = PreferenceManager.getDefaultSharedPreferences(this);
        boolean b2 = mSharedPref1.getBoolean(BUILDING_bool2,false); //没有设置为false
        if(b2){
            String showItems = mSharedPref1.getString(BUILDING_KEY2,"");
            for(int i = 0;i<Nobuidering.length;i++){
                if(Nobuidering[i].equals(showItems)){
                    spinner02.setSelection(i);//获取上次选中的位置，并设置保存
                    break;
                }
            }
        }

        //内部文件存储，纪录保存上一次下拉框所选择的内容
        spinner01.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String t = buidering[position];//获取到的内容，即是宿舍楼
                saveValueToPreferences1(t);//内部保存
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner02.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String t = Nobuidering[position];//获取到的内容，即是宿舍楼
                saveValueToPreferences2(t);//内部保存
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }



    /**
     * 保存设置值,宿舍楼
     */
    private void saveValueToPreferences1(String t) {
        if (mSharedPref1 == null) {
            mSharedPref1 = this.getSharedPreferences(BUILDING_KEY1, Context.MODE_PRIVATE);
        }

        // 写入数据
        SharedPreferences.Editor editor = mSharedPref1.edit();
        // 将配置值保存到首选项存储中
        editor.putString(BUILDING_KEY1,t);
        editor.putBoolean(BUILDING_bool1,true);
        editor.apply();
    }


    /**
     * 保存设置值,宿舍楼
     */
    private void saveValueToPreferences2(String t) {
        if (mSharedPref2 == null) {
            mSharedPref2 = this.getSharedPreferences(BUILDING_KEY2, Context.MODE_PRIVATE);
        }

        // 写入数据
        SharedPreferences.Editor editor = mSharedPref2.edit();
        // 将配置值保存到首选项存储中
        editor.putString(BUILDING_KEY2,t);
        editor.putBoolean(BUILDING_bool2,true);
        editor.apply();
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home://点击了返回，结束该活动，返回上一个活动
                Intent intent = new Intent(Soft1714080902133QueryActivity.this, Soft1714080902133HomeActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
