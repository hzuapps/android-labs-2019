package edu.hzuapps.androidlabs.soft1714080902420;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

public class NetworkUtils {
    public static boolean isNetworkAvalible(Context context) {
        //检测API是不是小于23，因为到了API23之后getNetworkInfo(int networkType)方法被弃用
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP){
            //获得ConnectivityManager对象
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            //获取WIFI连接的信息
            NetworkInfo wifiNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            //获取移动数据连接的信息
            NetworkInfo dataNetworkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            //无线网连接和数据网连接都断开
            if (!wifiNetworkInfo.isConnected() && !dataNetworkInfo.isConnected()) {
                return false;
            }
            //无线网连接和数据网连接一个
            return true;
            //API大于23时使用下面的方式进行网络监听
        }else{
            // 获得网络状态管理器
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager == null) {
                return false;
            } else {
                // 建立网络数组
                NetworkInfo[] net_info = connectivityManager.getAllNetworkInfo();
                if (net_info != null) {
                    for (int i = 0; i < net_info.length; i++) {
                        // 判断获得的网络状态是否是处于连接状态
                        if (net_info[i].getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
    }

    // 如果没有网络，则弹出网络设置对话框
    public static void checkNetwork(final Context context) {
        if (!NetworkUtils .isNetworkAvalible(context)) {
            new AlertDialog.Builder(context)
                    .setTitle("网络状态提示")
                    .setMessage("没有可以使用的网络，请先设置网络！")
                    .setCancelable(false)
                    .setIcon(R.mipmap.ic_launcher_round)
                    .setPositiveButton("设置",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int whichButton) {
                                    // 跳转到设置界面
                                    context.startActivity(new Intent(
                                            Settings.ACTION_WIRELESS_SETTINGS));
                                }
                            }).create().show();
        }
        return;
    }
}

