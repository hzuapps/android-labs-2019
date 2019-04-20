package edu.hzuapps.androidlabs;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class Soft1714080902209_Login extends AppCompatActivity {

    private TextView mTextMessage;
    private FloatingActionButton FAButton;
    private ListView listview;
    private ArrayList<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
    private JSONObject object;
    private TextView tv;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @SuppressLint("RestrictedApi")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    listview.setVisibility(View.VISIBLE);
                    FAButton.setVisibility(View.VISIBLE);
                    tv.setVisibility(View.INVISIBLE);
                    return true;
                case R.id.navigation_dashboard:
                    listview.setVisibility(View.INVISIBLE);
                    FAButton.setVisibility(View.INVISIBLE);
                    tv.setVisibility(View.VISIBLE);
                    return true;
                case R.id.navigation_notifications:
                    listview.setVisibility(View.INVISIBLE);
                    FAButton.setVisibility(View.INVISIBLE);
                    tv.setVisibility(View.INVISIBLE);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft1714080902209_login);

        load_info();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FAButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        FAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_add = new Intent();
                intent_add.setClass(Soft1714080902209_Login.this, Soft1714080902209_AddInfo.class);//登陆成功后跳转
                startActivity(intent_add);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                JSONObject jsonObject = new Soft1714080902209_JSON().getNetJSON();
                if(jsonObject != null){
                    try {
                        String text = jsonObject.getString("name") + "\n"
                                + jsonObject.getString("sex")+ "\n"
                                + jsonObject.getString("age")+ "\n"
                                + jsonObject.getString("tel");
                        tv.setText(text);
                        /*
                        JSONArray jsonarray = jsonObject.getJSONArray("ZhangSan");
                        for(int i=0;i<jsonarray.length();i++) {
                            object = jsonarray.getJSONObject(i);
                            Map<String, Object> map = new HashMap<String, Object>();
                            try {
                                String name = object.getString("name");
                                map.put("name", name);
                                list.add(map);
                                mybaseadapter list_item = new mybaseadapter();
                                listview.setAdapter(list_item);
                            }catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        */
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }



    private long mExitTime;
    public boolean onKeyDown(int keyCode, KeyEvent event){
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if((System.currentTimeMillis()-mExitTime) > 2000){
                Object mHelperUtils;
                Toast.makeText(this,"再按一次退出程序",Toast.LENGTH_SHORT).show();
                mExitTime=System.currentTimeMillis();
            }else{
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }

    public void load_info(){

        listview=(ListView)findViewById(R.id.list_view);
        tv=(TextView)findViewById(R.id.textView);
        String[] data={"ZhangSan","LiSi","WangWu"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(edu.hzuapps.androidlabs.Soft1714080902209_Login.this,android.R.layout.simple_list_item_1,data);
        listview.setAdapter(adapter);

    }


    public class mybaseadapter extends BaseAdapter {

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

                convertView = getLayoutInflater().inflate(R.layout.soft1714080902209_login, null);

                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.name.setText(list.get(position).get("name").toString());
            return convertView;
        }
    }
    final static class ViewHolder {
        TextView name;
        TextView sex;
        TextView age;
        TextView tel;
    }
}
