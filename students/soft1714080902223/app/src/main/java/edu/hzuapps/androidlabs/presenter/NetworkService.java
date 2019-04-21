package edu.hzuapps.androidlabs.presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.hzuapps.androidlabs.soft1714080902223.R;

public class NetworkService {

    /**
     * 获取网上的数据并返回，若获取失败，抛出IOException
     * @param context
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
     * 检查网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager manager = (ConnectivityManager) context
                .getApplicationContext().getSystemService(
                        Context.CONNECTIVITY_SERVICE);

        if (manager == null) {
            notInternetAlert(context);
            return false;
        }

        NetworkInfo networkinfo = manager.getActiveNetworkInfo();

        if (networkinfo == null || !networkinfo.isAvailable()) {
            notInternetAlert(context);
            return false;
        }

        return true;
    }

    public static void notInternetAlert(Context context){
         new AlertDialog.Builder(context)
                 .setTitle("网络异常")
                 .setMessage("网络出现异常，请检查网络是否正确连接再重新打开此软件")
                 .setIcon(R.drawable.ic_network_check_black_24dp)
                 .setPositiveButton("确定", null)
                 .setNegativeButton("取消", null)
                 .show();
    }
}
