package edu.hzuapps.androidlabs.Soft1714080902219;

import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class Soft1714080902219Activity3 extends FragmentActivity implements View.OnClickListener {
    ArrayList<HashMap<String,String>> arrayList = new ArrayList<>();
    Soft1714080902219MyAdapter adapter;
    ListView listView;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1){
                Log.d("mylog","listView.setAdapter(adapter);");
                adapter = new Soft1714080902219MyAdapter(Soft1714080902219Activity3.this,arrayList);
                listView.setAdapter(adapter);
            }
        }
    };

    public void getDataByNet(){

        try {
            String url_s = "https://raw.githubusercontent.com/wanshanghong/liulangzhe/master/myjson.json"; //我的阿里云的url:http://47.103.6.223:8080/liulangzhe-manager-web/myjson.json
            URL url = new URL(url_s);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(500000);
            conn.setUseCaches(false);
            conn.setRequestProperty("User-Agent","Mozilla/4.0(compatible;MSIE 6.0;Windows NT 5.1;"+"SV1;.NET4.0C;.NET4.0E;.NET CLR 2.0.50727;"
                    +".NET CLR 3.0.4506.2152;.NET CLR 3.5.30729;Shuame)");
            conn.connect();
            InputStream inputStream = conn.getInputStream();
            InputStreamReader input = new InputStreamReader(inputStream);
            BufferedReader buffer = new BufferedReader(input);
            if (conn.getResponseCode() == 200) {
                String inputLine;
                StringBuffer resultData = new StringBuffer();
                while ((inputLine = buffer.readLine()) != null) {
                    resultData.append(inputLine);
                }

                String text = resultData.toString();
                String sb=new String(text.getBytes("8859_1"), "GB2312");
                System.out.println(sb);
                input.close();
                inputStream.close();
                Log.d("mylog","sb:"+sb);
                parseJson(text.toString());
            }
        } catch (Exception e) {
            Log.d("mylog","e.printStackTrace();22222222");
            e.printStackTrace();
        }
        /**
         OkHttpClient okHttpClient=new OkHttpClient();
         //服务器返回的地址
         Request request=new Request.Builder()
         .url("http://47.103.6.223:8080/liulangzhe-manager-web/myjson.json").build();
         try {
         Response response=okHttpClient.newCall(request).execute();
         //获取到数据
         String date=response.body().string();
         Log.d("mylog","date;"+date);
         // 把数据传入解析josn数据方法
         parseJson(date);
         } catch (IOException e) {
         Log.d("mylog","e.printStackTrace();22222222");
         // TODO Auto-generated catch block
         e.printStackTrace();
         }
         */
    }


    public void parseJson(String json){
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                Log.d("mylog", "i" + i);
                HashMap<String, String> map = new HashMap<>();
                JSONObject jo = jsonArray.getJSONObject(i);
                map.put("title", jo.getString("title"));
                map.put("pdate", jo.getString("pdate"));
                map.put("src", jo.getString("src"));
                arrayList.add(map);
            }
            handler.sendEmptyMessage(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //定义3个Fragment的对象
    protected Soft1714080902219Fragment1 fragment1;
    protected Soft1714080902219Fragment2 fragment2;
    protected Soft1714080902219Fragment3 fragment3;

    //帧布局对象,就是用来存放Fragment的容器
    private FrameLayout flayout;

    //定义底部导航栏的三个布局
    private LinearLayout ll_tab1;
    private LinearLayout ll_tab2;
    private LinearLayout ll_tab3;

    //定义底部导航栏中的ImageView与TextView
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;

    //定义要用的颜色值
    private int whirt = 0xFFFFFFFF;
    private int gray = 0xFF7597B3;
    private int blue =0xFF0AB2FB;


    //定义要用的颜色值
    FragmentManager fManager;
    private Soft1714080902219Fragment2 soft1714080902219Fragment2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902219_activity3);

        Log.d("mylog","Soft1714080902219Activity3;运行");
        fManager = getSupportFragmentManager();

        initViews();
        setChioceItem(0);
    }
    //完成组件的初始化

    public void initViews()

    {
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        ll_tab1 = (LinearLayout) findViewById(R.id.ll_tab1);
        ll_tab2 = (LinearLayout) findViewById(R.id.ll_tab2);
        ll_tab3 = (LinearLayout) findViewById(R.id.ll_tab3);
        ll_tab1.setOnClickListener(this);
        ll_tab2.setOnClickListener(this);
        ll_tab3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_tab1:
                setChioceItem(0);
                break;
            case R.id.ll_tab2:
                setChioceItem(1);
                break;
            case R.id.ll_tab3:
                setChioceItem(2);
                break;
            default:
                break;

        }
    }
    //定义一个选中一个item后的处理

    public void setChioceItem(int index)
    {
        //重置选项+隐藏所有Fragment
        FragmentTransaction transaction = fManager.beginTransaction();
        clearChioce();
        hideFragments(transaction);
        switch (index) {
            case 0:
                iv1.setImageResource(R.drawable.xinwen);
                tv1.setTextColor(blue);
                ll_tab1.setBackgroundResource(R.drawable.beijing);
                if (fragment1 == null) {
                    // 如果fg1为空，则创建一个并添加到界面上
                    fragment1 = new Soft1714080902219Fragment1();
                    transaction.add(R.id.main_content, fragment1);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fragment1);
                }
                break;
            case 1:
                iv2.setImageResource(R.drawable.shipin);
                tv2.setTextColor(blue);
                ll_tab2.setBackgroundResource(R.drawable.beijing);
                if (fragment2 == null) {
                    // 如果fg1为空，则创建一个并添加到界面上
                    fragment2 = new Soft1714080902219Fragment2();
                    transaction.add(R.id.main_content, fragment2);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fragment2);
                }
                break;
            case 2:
                iv3.setImageResource(R.drawable.ren);
                tv3.setTextColor(blue);
                ll_tab3.setBackgroundResource(R.drawable.beijing);
                if (fragment3 == null) {
                    // 如果fg1为空，则创建一个并添加到界面上
                    fragment3 = new Soft1714080902219Fragment3();
                    transaction.add(R.id.main_content, fragment3);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fragment3);
                    StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
                    Log.d("mylog","Soft1714080902219Fragment2onCreateView;运行");
                    listView =this.findViewById(R.id.listview);
                    getDataByNet();
                }
                break;
        }
        transaction.commit();
    }
    //隐藏所有的Fragment,避免fragment混乱
    private void hideFragments(FragmentTransaction transaction) {
        if (fragment1 != null) {
            transaction.hide(fragment1);
        }
        if (fragment2 != null) {
            transaction.hide(fragment2);
        }
        if (fragment3 != null) {
            transaction.hide(fragment3);
        }
    }
    //定义一个重置所有选项的方法

    public void clearChioce()
    {
        iv1.setImageResource(R.drawable.xinwen);//zhuyi
        ll_tab1.setBackgroundColor(whirt);
        tv1.setTextColor(gray);
        iv2.setImageResource(R.drawable.shipin);
        ll_tab2.setBackgroundColor(whirt);
        tv2.setTextColor(gray);
        iv3.setImageResource(R.drawable.ren);
        ll_tab3.setBackgroundColor(whirt);
        tv3.setTextColor(gray);
    }



}
