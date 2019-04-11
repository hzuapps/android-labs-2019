package edu.hzuapps.androidlabs.presenter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import edu.hzuapps.androidlabs.soft1714080902223.R;

public class JsonService {


    public JSONObject getHomeJson(){
        JSONObject jsonObject = null;
        try {
            //建立http连接
            String url_s = "https://raw.githubusercontent.com/TRLVMMR/android-" +
                    "labs-2019/bd5d63db1f4aae84b62860eb66f8733710936206/students/s" +
                    "oft1714080902223/app/src/main/info.json";
            URL url = new URL(url_s);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setUseCaches(false);
            conn.connect();
            //获取文件放入输出流中
            InputStream inputStream = conn.getInputStream();
            InputStreamReader input = new InputStreamReader(inputStream);
            BufferedReader buffer = new BufferedReader(input);
            //当返回成功时，读取json数据
            if(conn.getResponseCode() == 200){
                String inputLine;
                StringBuffer resultData  = new StringBuffer();
                while((inputLine = buffer.readLine())!= null){
                    resultData.append(inputLine);
                }
                jsonObject = new JSONObject(resultData.toString());
            }
        } catch(Exception e){
            //如果超时，或者其他异常，输出异常信息
            e.printStackTrace();
        }
        return jsonObject;
    }
}
