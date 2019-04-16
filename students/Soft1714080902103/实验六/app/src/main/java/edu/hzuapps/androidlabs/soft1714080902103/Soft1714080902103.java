package com.example.myapplication;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Soft1714080902103 extends AppCompatActivity implements View.OnClickListener {
    TextView responseText;

    public class App{
        private String id;
        private String name;
        private String version;
        public String getId(){
            return id;
        }
        public void setId(String id){
            this.id=id;
        }
        String getName(){
            return name;
        }
        public void setName(String name){
            this.id=name;
        }
        String getVersion(){
            return version;
        }
        public void setVersion(String version){
            this.id=version;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstaneState){
        super.onCreate(savedInstaneState);
        setContentView(R.layout.activity_soft1714080902103);
        Button sendRequest = (Button) findViewById(R.id.send_request);
        responseText =(TextView) findViewById(R.id.response_text);
        sendRequest.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.send_request){
//            sendRequestWithHttpURLConnection();
            sendRequestWithOkHttp();
        }
    }

//    private void sendRequestWithHttpURLConnection() {
//        new  Thread(new Runnable() {
//            @Override
//            public void run() {
//                    HttpURLConnection connection=null;
//                    BufferedReader reader = null;
//                    try {
//                        URL url =new URL("http://localhost:81/get_data.json");
//                        connection =(HttpURLConnection)url.openConnection();
//                        connection.setRequestMethod("GET");
//                        connection.setConnectTimeout(8000);
//                        connection.setReadTimeout(8000);
//                        InputStream in =connection.getInputStream();
//                        reader =new BufferedReader(new InputStreamReader(in));
//                        StringBuilder response =new StringBuilder();
//                        String line;
//                        while ((line=reader.readLine())!=null){
//                            response.append(line);
//                        }
//                        showResponse(response.toString());
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    } finally {
//                        if (reader!=null){
//                            try {
//                                reader.close();
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    } if (connection != null){
//                        connection.disconnect();
//                    }
//                }
//        }).start();
//    }
//    private void showResponse(final String response) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                responseText.setText(response);
//            }
//        });
//    }


    private void sendRequestWithOkHttp() {
        {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        OkHttpClient client = new OkHttpClient();
                        Request request = new Request.Builder()
                                .url("http://localhost:81/get_data.json")
//                                .url("http://localhost:81/get_data.xml")
                                .build();
                        Response response = client.newCall(request).execute();
                        String responseData = response.body().string();
                        parseJSONWithGSON(responseData);
//                        parseXMLWithPull(responseData);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }
    private void parseJSONWithGSON (String jsonData){

        Gson gson=new Gson();
        List<App>appList=gson.fromJson(jsonData,new TypeToken<List<App>>(){}.getType());
        for(App app:appList){
            Log.d("Soft1714080902103","id is"+app.getId());
            Log.d("Soft1714080902103","name is"+app.getName());
            Log.d("Soft1714080902103","version is"+app.getVersion());
        }
    }

//    private void parseXMLWithPull(String xmlData) {
//        try{
//            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
//            XmlPullParser xmlPullParser=factory.newPullParser();
//            xmlPullParser.setInput(new StringReader(xmlData));
//            int eventType=xmlPullParser.getEventType();
//            String id="";
//            String name="";
//            String version="";
//            while (eventType!=XmlPullParser.END_DOCUMENT){
//                String nodeName=xmlPullParser.getName();
//                switch (eventType){
//                    case XmlPullParser.START_DOCUMENT:{
//                        if("id".equals(nodeName)){
//                            id=xmlPullParser.nextText();
//                        }else if("name".equals(nodeName)){
//                            id=xmlPullParser.nextText();
//                        }else if("version".equals(nodeName)) {
//                            id = xmlPullParser.nextText();
//                        }
//                        break;
//                    }
//                    case XmlPullParser.END_TAG:{
//                        if("app".equals(nodeName)){
//                            Log.d("Soft1714080902103","id is"+id);
//                            Log.d("Soft1714080902103","name is"+name);
//                            Log.d("Soft1714080902103","version is"+version);
//                        }
//                        break;
//                    }
//                    default:
//                        break;
//                }
//                eventType=xmlPullParser.next();
//            }
//        } catch (XmlPullParserException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}