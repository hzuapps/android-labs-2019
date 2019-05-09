package com.example.soft1706081301317sweepacticity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PastActivity extends AppCompatActivity {
    public ListView lv;
    String url = "https://raw.githubusercontent.com/cqr22/android-labs-2019/master/students/soft1706081301317/app/src/main/assets/content.json";

    final static class ViewHolder {
        TextView title;
        TextView content;
        TextView data;
    }

    String size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        init();
        //设置强制竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        Button button = (Button)findViewById(R.id.backWrite);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PastActivity.this,WriteActivity.class);
                startActivity(intent);
            }
        });


    }

    private void init() {

        lv=(ListView) findViewById(R.id.list_view);
        new Thread(new Runnable() {
            @Override
            public void run() {

                OkHttpClient okHttpClient=new OkHttpClient();
                //服务器返回的地址
                Request request=new Request.Builder()
                        .url(url).build();
                try {
                    Response response=okHttpClient.newCall(request).execute();
                    //获取到数据
                    String date=response.body().string();
                    //把数据传入解析josn数据方法
                    jsonJX(date);

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }).start();;



        }

    private void jsonJX(String date) {
        //判断数据是空
        if(date!=null){
            try {
                //将字符串转换成jsonObject对象
                JSONObject jsonObject = new JSONObject(date);
                //获取返回数据中flag的值
                String resultCode = jsonObject.getString("flag");
                //如果返回的值是success则正确
                if (resultCode.equals("success")) {
                    //获取到json数据中里的activity数组内容
                    JSONArray resultJsonArray = jsonObject.getJSONArray("activity");
                    //遍历
                    for(int i=0;i<resultJsonArray.length();i++){
                        jsonObject=resultJsonArray.getJSONObject(i);

                        Map<String, Object> map=new HashMap<String, Object>();

                        try {
                            //获取到json数据中的activity数组里的内容title
                            String title = jsonObject.getString("title");
                            //获取到json数据中的activity数组里的内容content
                            String content=jsonObject.getString("content");
                            //获取到json数据中的activity数组里的内容data
                            String data=jsonObject.getString("data");
                            //存入map
                            map.put("title", title);
                            map.put("content", content);
                            map.put("data", data);
                            //ArrayList集合
                            if (!allList.getList().contains(map))
                                allList.getList().add(map);


                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    }

                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }




    }
    //Handler运行在主线程中(UI线程中)，  它与子线程可以通过Message对象来传递数据
    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {


        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Mybaseadapter list_item=new Mybaseadapter();
                    lv.setAdapter(list_item);
                    break;
            }


        }
    };
    //listview的适配器
    public class Mybaseadapter extends BaseAdapter {

        @Override
        public int getCount() {
            return allList.getList().size();

        }

        @Override
        public Object getItem(int position) {
            return allList.getList().get(position);
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();

                convertView = getLayoutInflater().inflate(R.layout.item_list, null);
                viewHolder.title = (TextView) convertView.findViewById(R.id.list_item_title);
                viewHolder.content = (TextView) convertView.findViewById(R.id.list_item_body);
                viewHolder.data = (TextView) convertView.findViewById(R.id.list_item_time);


                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Log.v("位置", String.valueOf(position));
            viewHolder.title.setText(allList.getList().get(position).get("title").toString());
            viewHolder.content.setText(allList.getList().get(position).get("content").toString());
            viewHolder.data.setText(allList.getList().get(position).get("data").toString());
            return convertView;
        }

    }

}
