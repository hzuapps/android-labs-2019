package edu.hzuapps.androidlabs.com1714080901141;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.StringReader;
import java.util.List;



public class Com1714080901141FragmentCommunication extends Fragment implements View.OnClickListener {
    private Button fresh_button;
    private TextView dialog_view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_communication, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initListener();
    }

    private void initView() {
        fresh_button = (Button) getActivity().findViewById(R.id.fresh_button);
        dialog_view = (TextView) getActivity().findViewById(R.id.dialog_box);
    }

    private void initListener() {
        fresh_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fresh_button) {
            sendRequestWithOkHttpURL();
        }
    }

    private void sendRequestWithOkHttpURL() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    //Request request = new Request.Builder().url("https://raw.githubusercontent.com/cyh1069247088/turbo-lamp/master/chat.xml").build();
                    Request request = new Request.Builder().url("https://raw.githubusercontent.com/cyh1069247088/turbo-lamp/master/chat.json").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    //parseXMLWithPull(responseData);
                    //parseJSONWithJSONObject(responseData);
                    parseJSONWithGSON(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    private void parseXMLWithPull(String xmlData) {
        String all="";
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType = xmlPullParser.getEventType();
            String student = "", teacher = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG: {
                        if ("student".equals(nodeName)) {
                            student = xmlPullParser.nextText();
                        } else if ("teacher".equals(nodeName)) {
                            teacher = xmlPullParser.nextText();
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG: {
                        if ("communication".equals(nodeName)) {
                            if (!student.isEmpty()) {
                                all += "student : "+student+"\r\n";
                                Log.d("FragmentCommunication", "student --> " + student);
                                student="";
                            }
                            if (!teacher.isEmpty()) {
                                all += "teacher : "+teacher+"\r\n";
                                Log.d("FragmentCommunication", "teacher --> " + teacher);
                                teacher="";
                            }
                        }
                        break;
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialog_view.setText(all);
    }
    private void parseJSONWithGSON(String jsonData){
        Gson gson=new Gson();
        List<Com1714080901141App> appList=gson.fromJson(jsonData,new TypeToken<List<Com1714080901141App>>(){}.getType());
        String all="";
        for(Com1714080901141App app : appList){
            String student=app.getStudent();
            String teacher=app.getTeacher();
            if(!student.isEmpty()){
                all += "student : "+student+"\r\n";
                Log.d("FragmentCommunication","student : "+student);
            }
            if(!teacher.isEmpty()){
                all += "teacher : "+teacher+"\r\n";
                Log.d("FragmentCommunication","teacher : "+teacher);
            }
        }
        dialog_view.setText(all);
    }
    private void parseJSONWithJSONObject(String jsonData){
        String all="";
        try{
            JSONArray jsonArray = new JSONArray(jsonData);
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                String student=jsonObject.getString("student");
                String teacher=jsonObject.getString("teacher");
                if (!student.isEmpty()){
                    all += "student : "+student+"\r\n";
                    Log.d("FragmentCommunication", "student --> " + student);
                }
                if (!teacher.isEmpty()){
                    all += "teacher : "+teacher+"\r\n";
                    Log.d("FragmentCommunication", "teacher --> " + teacher);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        dialog_view.setText(all);
    }
}