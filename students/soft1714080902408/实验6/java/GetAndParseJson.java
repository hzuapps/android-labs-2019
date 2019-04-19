package com.hzuandroid.jsonjx;

import android.os.Handler;
import android.os.Message;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetAndParseJson {
    private String url="https://raw.githubusercontent.com/Lyeye/android-labs-2019/4e3732d1d6639a50000ebff1767aeddf78f7913f/students/soft1714080902408/%E5%AE%9E%E9%AA%8C6/movie.json";
    public static final int PARSESUCCWSS=0x2016;
    private Handler handler;
    public GetAndParseJson(Handler handler) {
        this.handler=handler;
    }
    public void getJsonFromInternet () {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection conn=(HttpURLConnection) new URL(url).openConnection();
                    conn.setConnectTimeout(3000);
                    conn.setRequestMethod("GET");
                    if (conn.getResponseCode()==200) {
                        InputStream inputStream=conn.getInputStream();
                        List<Movie> listMovie=parseJson(inputStream);
                        if (listMovie.size()>0) {
                            Message msg=new Message();
                            msg.what=PARSESUCCWSS;
                            msg.obj=listMovie;
                            handler.sendMessage(msg);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    protected List<Movie> parseJson(InputStream inputStream)throws Exception {
        List<Movie>listMovie=new ArrayList<Movie>();
        byte[]jsonBytes=convertIsToByteArray(inputStream);
        String json=new String(jsonBytes);
        try {
            JSONArray jsonArray=new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jObject=jsonArray.getJSONObject(i);
                int id=jObject.getInt("id");
                String name=jObject.getString("name");
                String price=jObject.getString("price");
                String language=jObject.getString("language");
                Movie movie=new Movie(id, name, price,language);
                listMovie.add(movie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listMovie;
    }

    private byte[] convertIsToByteArray(InputStream inputStream) {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        byte buffer[]=new byte[1024];
        int length=0;
        try {
            while ((length=inputStream.read(buffer))!=-1) {
                baos.write(buffer, 0, length);
            }
            inputStream.close();
            baos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }
}
