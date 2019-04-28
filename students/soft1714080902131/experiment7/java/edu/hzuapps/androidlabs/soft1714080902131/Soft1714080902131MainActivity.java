package edu.hzuapps.androidlabs.soft1714080902131;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import edu.hzuapps.androidlabs.soft1714080902131.bean.Thing;
import edu.hzuapps.androidlabs.soft1714080902131.dao.ThingDao;

public class Soft1714080902131MainActivity extends AppCompatActivity {
//    private ImageView btn1;
//    private ImageView btn2;
    private TextView tv;
    private Button bt1;
    //SQLiteBase以及ListView的实现
    private List<Thing> list;
    private ThingDao dao;
    private EditText et1;
    private MyAdapter adapter;
    private ListView lv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902131_main);
        IntentFilter filter = new IntentFilter();
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(new NetworkConnectChangedReceiver(), filter);

//        boolean conn = ConnectionUtil.isConn(Soft1714080902131MainActivity.this);
//        //如果没有网络
//        if (!conn) {
//            //调用网络工具类中的方法，跳转到设置网络的界面
//            ConnectionUtil.setNetworkMethod(Soft1714080902131MainActivity.this);
//        } else {
//            //有的话就做自己的操作
//        }
//        btn1 = findViewById(R.id.imageView);
//        btn2 = findViewById(R.id.add);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        tv = findViewById(R.id.textView);
        bt1 = findViewById(R.id.bg);
//        Bundle bundle = getIntent().getExtras();
//        String thing = bundle.getString("thing");
//        tv.setText(thing);
        initView();
        dao = new ThingDao(this);
        list = dao.queryAll();
        adapter = new MyAdapter();
        lv1.setAdapter(adapter);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Soft1714080902131MainActivity.this,Soft1714080902131ModifyActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("thing",tv.getText().toString());
//                intent.putExtras(bundle);
//                startActivityForResult(intent,0);
//            }
//        });
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Soft1714080902131MainActivity.this,Soft1714080902131EditActivity.class);
//                startActivity(intent);
//            }
//        });
//        Soft1714080902131ModifyActivity soft1714080902131ModifyActivity = new Soft1714080902131ModifyActivity();
//        tv.setText(soft1714080902131ModifyActivity.read());

    }


    //    private Soft1714080902131ModifyActivity soft1714080902131ModifyActivity;


//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        tv.setText(data.getExtras().getString("thing"));
//    }
    private void initView(){
        lv1 = findViewById(R.id.thLv);
        et1 = findViewById(R.id.inputth);
//        lv1.setOnItemClickListener(new MyOnItemClickListener());
    }

    //activity_main.xml对应ImageView的点击事件触发事件
    public void add(View v){
        String thing = et1.getText().toString().trim();
        Thing t = new Thing(thing);
        list.add(t);
        adapter.notifyDataSetChanged();
        lv1.setSelection(lv1.getCount()-1);
        et1.setText("");
    }
    //自定义一个适配器
    private class MyAdapter extends BaseAdapter{

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
            //重用convertView
            View item = convertView!=null?convertView:View.inflate(getApplicationContext(),R.layout.item_layout,null);
            //获取该视图中的TextView
            TextView textView = item.findViewById(R.id.textView);
            //根据当前位置获取Thing对象
            final Thing t = list.get(position);
            //把Thing对象中的数据放到TextView中
            textView.setText(t.getThing()+"");
            //获取图片按键
            ImageView deleteIv = item.findViewById(R.id.imageView2);
            //删除图片的点击事件触发的方法
            deleteIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    android.content.DialogInterface.OnClickListener listener =
                            new android.content.DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog,int which){
                                    list.remove(t);
                                    dao.delete(t.getId());
                                    notifyDataSetChanged();
                                }
                            };
                    AlertDialog.Builder builder= new AlertDialog.Builder(Soft1714080902131MainActivity.this);
                    builder.setTitle("确定要删除吗？");
                    builder.setPositiveButton("yes",listener);
                    builder.setNegativeButton("no",null);
                    builder.show();
                }
            });
            return item;
        }
    }
    public class NetworkConnectChangedReceiver extends BroadcastReceiver {
        private String getConnectionType(int type) {
            String connType = "";
            if (type == ConnectivityManager.TYPE_MOBILE) {
                connType = "网络数据";
            } else if (type == ConnectivityManager.TYPE_WIFI) {
                connType = "WIFI网络";
            }
            return connType;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (WifiManager.WIFI_STATE_CHANGED_ACTION.equals(intent.getAction())) {// 监听wifi的打开与关闭，与wifi的连接无关
                int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
                Log.e("TAG", "wifiState:" + wifiState);
                switch (wifiState) {
                    case WifiManager.WIFI_STATE_DISABLED:
                        break;
                    case WifiManager.WIFI_STATE_DISABLING:
                        break;
                }
            }
            // 监听wifi的连接状态即是否连上了一个有效无线路由
            if (WifiManager.NETWORK_STATE_CHANGED_ACTION.equals(intent.getAction())) {
                Parcelable parcelableExtra = intent
                        .getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
                if (null != parcelableExtra) {
                    // 获取联网状态的NetWorkInfo对象
                    NetworkInfo networkInfo = (NetworkInfo) parcelableExtra;
                    //获取的State对象则代表着连接成功与否等状态
                    NetworkInfo.State state = networkInfo.getState();
                    //判断网络是否已经连接
                    boolean isConnected = state == NetworkInfo.State.CONNECTED;
                    Log.e("TAG", "isConnected:" + isConnected);
                    if (isConnected) {
                    } else {

                    }
                }
            }
            // 监听网络连接，包括wifi和移动数据的打开和关闭,以及连接上可用的连接都会接到监听
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                //获取联网状态的NetworkInfo对象
                NetworkInfo info = intent
                        .getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
                if (info != null) {
                    //如果当前的网络连接成功并且网络连接可用
                    if (NetworkInfo.State.CONNECTED == info.getState() && info.isAvailable()) {
                        if (info.getType() == ConnectivityManager.TYPE_WIFI
                                || info.getType() == ConnectivityManager.TYPE_MOBILE) {
                            Log.i("TAG", getConnectionType(info.getType()) + "连上");
                        }
                    } else {
                        AlertDialog.Builder builder=new AlertDialog.Builder(Soft1714080902131MainActivity.this);
                        builder.setTitle("网络设置提示").setMessage("网络连接不可用,是否进行设置?").setPositiveButton("yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                Intent intent=null;
                                if(android.os.Build.VERSION.SDK_INT>10){
                                    intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                                }else{
                                    intent = new Intent();
                                    ComponentName component = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
                                    intent.setComponent(component);
                                    intent.setAction("android.intent.action.VIEW");
                                }
                                Soft1714080902131MainActivity.this.startActivity(intent);
                            }
                        }).setNegativeButton("no", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                dialog.dismiss();
                            }
                        }).show();
                        Log.i("TAG", getConnectionType(info.getType()) + "断开");
                    }
                }
            }
        }
    }

}


//https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555605418635&di=7b969e7c82a3e0fadea0726abf21f0f9&imgtype=0&src=http%3A%2F%2Fd.5857.com%2Fxqxbz_160923%2Fdesk_001.jpg