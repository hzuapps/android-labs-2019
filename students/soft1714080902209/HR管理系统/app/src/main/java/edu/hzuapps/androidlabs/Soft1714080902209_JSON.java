package edu.hzuapps.androidlabs;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Soft1714080902209_JSON {

    public JSONObject getNetJSON(){
        JSONObject jsonObject =null;
        String json_url="https://raw.githubusercontent.com/LamKP/android-labs-2019/master/students/soft1714080902209/HR%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F/app/src/main/res/json/candidate.json";
        try{

            HttpURLConnection httpConn = (HttpURLConnection)new URL(json_url).openConnection();
            httpConn.setConnectTimeout(5000);
            httpConn.setRequestMethod("GET");
            httpConn.setUseCaches(false);
            httpConn.connect();


            InputStream inputStream = httpConn.getInputStream();
            InputStreamReader input = new InputStreamReader(inputStream);
            BufferedReader buffer = new BufferedReader(input);

            if(httpConn.getResponseCode() == 200){
                String inputLine;
                StringBuffer resultData  = new StringBuffer();
                while((inputLine = buffer.readLine())!= null){
                    resultData.append(inputLine);
                }
                jsonObject = new JSONObject(resultData.toString());

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(jsonObject);
        return jsonObject;
    }

//https://raw.githubusercontent.com/LamKP/android-labs-2019/master/students/soft1714080902209/HR%E7%AE%A1%E7%90%86%E7%B3%BB%E7%BB%9F/app/src/main/res/json/candidate.json

}
