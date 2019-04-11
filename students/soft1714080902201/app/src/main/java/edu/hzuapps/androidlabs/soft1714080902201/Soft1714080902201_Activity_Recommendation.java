package edu.hzuapps.androidlabs.soft1714080902201;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Soft1714080902201_Activity_Recommendation extends AppCompatActivity {
    public JSONObject object;
    public ListView lv;
    public ArrayList<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902201_recommendation);
        init();
    }

    private void init() {
        list.clear();
        lv=(ListView) findViewById(R.id.recommendationLV);
        new Thread(new Runnable() {
            @Override
            public void run() {

                OkHttpClient okHttpClient=new OkHttpClient();
                Request request=new Request.Builder()
                        .url("https://raw.githubusercontent.com/circle-hotaru/android-labs-2019/master/students/soft1714080902201/app/src/main/res/assets/book").build();
                try {
                    Response response=okHttpClient.newCall(request).execute();
                    String date=response.body().string();
                    jsonJX(date);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void jsonJX(String date) {
        if(date!=null){
            try {
                JSONObject jsonObject = new JSONObject(date);
                String resultCode = jsonObject.getString("flag");
                if (resultCode.equals("success")) {
                    //获取到json数据中里的book数组内容
                    JSONArray resultJsonArray = jsonObject.getJSONArray("book");
                    for(int i=0;i<resultJsonArray.length();i++){
                        object=resultJsonArray.getJSONObject(i);
                        Map<String, Object> map=new HashMap<String, Object>();
                        try {
                            String name = object.getString("name");
                            String author=object.getString("author");
                            int balance=object.getInt("balance");
                            map.put("name", name);
                            map.put("author", author);
                            map.put("balance", balance);
                            list.add(map);
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
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
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

                convertView = getLayoutInflater().inflate(R.layout.activity_soft1714080902201_recommendation_item, null);
                viewHolder.nameTV = (TextView) convertView.findViewById(R.id.recommendationNameTV);
                viewHolder.authorTV = (TextView) convertView.findViewById(R.id.recommendationAuthorTV);
                viewHolder.balanceTV = (TextView) convertView.findViewById(R.id.recommendationBalanceTV);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.nameTV.setText(list.get(position).get("name").toString());
            viewHolder.authorTV.setText(list.get(position).get("author").toString());
            viewHolder.balanceTV.setText(list.get(position).get("balance").toString());
            return convertView;
        }
    }

    final static class ViewHolder {
        TextView nameTV;
        TextView authorTV;
        TextView balanceTV;
    }
}
