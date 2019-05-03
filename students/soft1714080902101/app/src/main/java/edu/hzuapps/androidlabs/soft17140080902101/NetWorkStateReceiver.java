package edu.hzuapps.androidlabs.soft1714080902101;

/**
 * Created by Lenovo on 2019/5/4.
 */
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class NetWorkStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println("网络状态发生变化");
        //检测API是不是小于23，因为到了API23之后getNetworkInfo(int networkType)方法被弃用
        if (android.os.Build.VERSION.SDK_INT < 23) {
            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //获取ConnectivityManager对象对应的NetworkInfo对象
            //获取WIFI连接的信息
            NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            //获取移动数据连接的信息
            NetworkInfo dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
                Toast.makeText(context, "WIFI已连接,移动数据已连接", Toast.LENGTH_SHORT).show();
            } else if (wifiNetworkInfo.isConnected() && !dataNetworkInfo.isConnected()) {
                Toast.makeText(context, "WIFI已连接,移动数据已断开", Toast.LENGTH_SHORT).show();
            } else if (!wifiNetworkInfo.isConnected() && dataNetworkInfo.isConnected()) {
                Toast.makeText(context, "WIFI已断开,移动数据已连接", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "WIFI已断开,移动数据已断开", Toast.LENGTH_SHORT).show();
            }
        }else {
            System.out.println("API level 大于23");
            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            try{
                //获取所有网络连接的信息
                NetworkInfo[] networks = connMgr.getAllNetworkInfo();
                //用于存放网络连接信息
                String pk1=new String();
                String pk2=new String();
                //通过循环将网络信息逐个取出来
                for (int i=0; i < networks.length; i++){
                    //获取ConnectivityManager对象对应的NetworkInfo对象
                    NetworkInfo networkInfo = networks[i];
                    int type=networkInfo.getType();
                    if(ConnectivityManager.TYPE_MOBILE==type){
                        if(networkInfo.isConnected()){pk1="成功";Toast.makeText(context,"移动数据连接成功！",Toast.LENGTH_LONG).show();}
                        else pk1="失败";

                    }else if(ConnectivityManager.TYPE_WIFI==type){
                        if(networkInfo.isConnected()){pk2="成功";Toast.makeText(context,"无线网络连接成功！",Toast.LENGTH_LONG).show(); }
                        else pk2="失败";
                    }
                }
                if(pk1=="失败"&&pk2=="失败"){
                Toast.makeText(context,"网络连接失败",Toast.LENGTH_LONG).show();}
            }
            catch(Exception e){

            }
        }
    }
}