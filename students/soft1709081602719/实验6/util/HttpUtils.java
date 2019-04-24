package edu.hzuapps.androidlabs.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    public static byte[] getHttpResult(String path){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try{
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setReadTimeout(5000);
            int responseCode = connection.getResponseCode();
            if (responseCode==200){
                InputStream inputStream = connection.getInputStream();
                int temp = 0;
                byte[] buff = new byte[1024];
                while ((temp = inputStream.read(buff))!=-1){
                    outputStream.write(buff,0,temp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }
}
