package com.example.eduhuzappfuyouapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.List;


public class Com1714080901237Activity extends AppCompatActivity {


    private TextView tv_show_entity;
    private String string = "";
    private InputStream is=null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com1714080901237);
        tv_show_entity = (TextView) findViewById(R.id.tv_show_entity);
    }
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Button01:
                Intent intent_1 = new Intent(this, Com1714080901237Activity02.class);
                startActivity(intent_1);
                break;
            case R.id.Button02:
                Intent intent_2 = new Intent(this, Com1714080901237Activity03.class);
                startActivity(intent_2);
                break;
            case R.id.StartXML:
                startXML(tv_show_entity);
                break;
            default:break;
        }
    }
    public void startXML(TextView Tv) {
        SAXService saxService = new SAXService();
        try {
            File file = new File(Environment.getExternalStorageDirectory(),
                    "shujuxl.xml");
            is=new FileInputStream(file);
            List<Course> courses = saxService.getCourses(is);
            for (Course course : courses) {
                Log.e("TAG", course.toString());
                string += course.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e( "error","解析出错1");
        } catch (IOException e) {
            Log.e( "error","解析出错2");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e( "error","解析出错3");
        }
        Tv.setText(string);
    }
}

