package edu.hzuapps.androidlabs.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.hzuapps.androidlabs.soft1714080902223.R;

public class NetworkService {

    /**
     * 获取网上的数据并返回，若获取失败，抛出IOException
     * @param url_s 传入一个String类型的url
     * @throws IOException
     * @return 若获取成功，返回一个BufferedReader，若获取失败，返回null
     */
    public static InputStream getInternetData(String url_s) throws IOException {
        URL url = new URL(url_s);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setUseCaches(false);
        conn.connect();
        InputStream inputStream = conn.getInputStream();
        if(conn.getResponseCode() == 200){
            return inputStream;
        }
        //获取文件放入输出流中
        return null;
    }
    /**
     * 检查网络是否可用，默认给出alert提示
     * @param context
     * @return
     */

    public static boolean isNetworkAvailable(Context context) {
        return isNetworkAvailable(context, true);
    }
    /**
     * 检查网络是否可用
     *
     * @param context
     * @param alert 是否要给出alert提示
     * @return
     */
    public static boolean isNetworkAvailable(Context context, boolean alert) {

        ConnectivityManager manager = (ConnectivityManager) context
                .getApplicationContext().getSystemService(
                        Context.CONNECTIVITY_SERVICE);

        if (manager == null) {
            if(alert){
                notInternetAlert(context);
            }
            return false;
        }

        NetworkInfo networkinfo = manager.getActiveNetworkInfo();

        if (networkinfo == null || !networkinfo.isAvailable()) {
            if(alert){
                notInternetAlert(context);
            }
            return false;
        }

        return true;
    }

    public static void notInternetAlert(final Context context){
         new AlertDialog.Builder(context)
                 .setTitle("网络异常")
                 .setMessage("网络未连接，是否前往设置页打开网络连接")
                 .setIcon(R.drawable.ic_network_check_black_24dp)
                 .setPositiveButton("确定", new DialogInterface.OnClickListener(){
                     @Override
                     public void onClick(DialogInterface dialog, int which) {
                         //跳转到网络设置
                         context.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
                     }
                 })
                 .setNegativeButton("取消", null)
                 .show();
    }
}
