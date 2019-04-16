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
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import edu.hzuapps.androidlabs.soft1714080902133.javabean.Soft1714080902133Dor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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

        //getDatasync();

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


        //获取下拉框的宿舍楼，宿舍号，以便对应josn文件中的数据，查询对应的电量
        final boolean b3 = mSharedPref1.getBoolean(BUILDING_bool1,false); //没有设置为false
        final String t1 = mSharedPref1.getString(BUILDING_KEY1,"");

        final boolean b4 = mSharedPref2.getBoolean(BUILDING_bool2,false); //没有设置为false
        final String t2 = mSharedPref2.getString(BUILDING_KEY2,"");

        //实验6，访问事先上传在GitHub上的json文件中的数据，然后点击按钮在Textview上显示，查询电量
        final TextView textView = findViewById(R.id.query_view1);
        Button button = findViewById(R.id.query_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //主线程
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    //指定访问的网址，即在github上的文件
                                    .url("https://github.com/wzq-55552/android-labs-2019/blob/master/students/soft1714080902133/data.json")
                                    .build();
                            Response response = client.newCall(request).execute();
                            String responseData = response.body().string();
                            //获取json数组和对象
                            Gson gson  = new Gson();
                            List<Soft1714080902133Dor> dorList = gson.fromJson(responseData,new TypeToken<List<Soft1714080902133Dor>>(){}.getType());
                            for(Soft1714080902133Dor soft1714080902133Dor:dorList){
                                //日志,以便查看
                                Log.d("QueryActivity","宿舍楼:"+soft1714080902133Dor.getDormitory());
                                Log.d("QueryActivity","宿舍号:"+soft1714080902133Dor.getDno());
                                Log.d("QueryActivity","欠费电量:"+soft1714080902133Dor.getElectricQuantity());
                                if(b3&&b4&&t1.equals(soft1714080902133Dor.getDormitory())&&t2.equals(soft1714080902133Dor.getDno())){
                                    textView.setText(soft1714080902133Dor.getElectricQuantity());//设置显示
                                    Intent intent = new Intent(Soft1714080902133QueryActivity.this,Soft1714080902133QueryActivity.class);
                                    startActivity(intent);
                                }
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }


    /*public void getDatasync(String Dormitory,String Dno,String ElectricQuantity){
        OkHttpUtils
                .post()
                .url("https://github.com/wzq-55552/android-labs-2019/blob/master/students/soft1714080902133/data.json")
                .addParams("Dormitory",Dormitory)
                .addParams("Dno",Dno)
                .addParams("ElectricQuantity",ElectricQuantity)
                .build()
                .execute(new StringCallback() {
                             @Override
                             public void onError(Call call, Exception e, int id) {
                                 Toast.makeText(getApplicationContext(), "请求失败", Toast.LENGTH_SHORT).show();
                             }

                             @Override
                             public void onResponse(String response, int id) {
                                 try {
                                     Log.d("QueryActivity","宿舍楼:"+response.Dormitory);
                                     Log.d("QueryActivity","宿舍号:"+response.Dno);
                                     Log.d("QueryActivity","欠费电量:"+response.ElectricQuantity);
                                 } catch (Exception e) {
                                     e.printStackTrace();
                                 }
                             }
                         });
    }*/


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
     * 保存设置值,宿舍号
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
