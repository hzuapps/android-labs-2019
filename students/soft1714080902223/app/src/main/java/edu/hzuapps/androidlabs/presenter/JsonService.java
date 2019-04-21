package edu.hzuapps.androidlabs.presenter;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class JsonService {

    public JSONObject getHomeJson(){
        JSONObject jsonObject = null;
        try {
            //建立http连接
            String url = "https://raw.githubusercontent.com/TRLVMMR/android-" +
                    "labs-2019/bd5d63db1f4aae84b62860eb66f8733710936206/students/s" +
                    "oft1714080902223/app/src/main/info.json";
            // 通过url得到文件字节流
            InputStream inputStream = NetworkService.getInternetData(url);
            //如果没有获取到数据，返回null
            if(inputStream == null){
                return null;
            }
            InputStreamReader input = new InputStreamReader(inputStream);
            BufferedReader buffer = new BufferedReader(input);
            String inputLine;
            StringBuffer resultData = new StringBuffer();
            while ((inputLine = buffer.readLine()) != null) {
                resultData.append(inputLine);
            }
            jsonObject = new JSONObject(resultData.toString());

        } catch(Exception e){
            //如果超时，或者其他异常，输出异常信息
            e.printStackTrace();
        }
        return jsonObject;
    }
}
