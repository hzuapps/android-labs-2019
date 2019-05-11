package com.example.eduhuzappfuyouapp;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


public class Com1714080901237Activity02 extends AppCompatActivity {
        private Button btn_read;
        private Button  btn_save;
        private Button  btn_clr;
        private RadioButton radio01;
        private  RadioButton radio02;
        private  RadioButton radio03;
        private  RadioButton radio04;
        private  RadioButton radio05;
        private  RadioButton radio06;
        private  RadioButton radio07;
        private  RadioButton radio11;
        private  RadioButton radio12;
        private  RadioButton radio13;
        private  RadioButton radio14;
        private  RadioButton radio15;
        private  RadioButton radio16;
        private  RadioButton radio17;
        private EditText editView_cname;
        private TextView textView;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_file_storage);
            btn_read=(Button)findViewById(R.id.btn_read);
            btn_save=(Button)findViewById(R.id.btn_save);
            btn_clr=(Button)findViewById(R.id.btn_clean);
            radio01=(RadioButton)findViewById(R.id.mon);
            radio02=(RadioButton)findViewById(R.id.thur);
            radio03=(RadioButton)findViewById(R.id.tue);
            radio04=(RadioButton)findViewById(R.id.wen);
            radio05=(RadioButton)findViewById(R.id.fri);
            radio06=(RadioButton)findViewById(R.id.sat);
            radio07=(RadioButton)findViewById(R.id.sun);
            radio11=(RadioButton)findViewById(R.id.one);
            radio12=(RadioButton)findViewById(R.id.two);
            radio13=(RadioButton)findViewById(R.id.three);
            radio14=(RadioButton)findViewById(R.id.four);
            radio15=(RadioButton)findViewById(R.id.five);
            radio16=(RadioButton)findViewById(R.id.six);
            radio17=(RadioButton)findViewById(R.id.seven);
            textView=(TextView)findViewById(R.id.tv_2);
            editView_cname=(EditText)findViewById(R.id.get_cname);
            btn_save.setOnClickListener(new com.example.eduhuzappfuyouapp.Com1714080901237Activity02.ButtonListener());
            btn_read.setOnClickListener(new com.example.eduhuzappfuyouapp.Com1714080901237Activity02.ButtonListener());
            btn_clr.setOnClickListener(new com.example.eduhuzappfuyouapp.Com1714080901237Activity02.ButtonListener());

        }
        private class ButtonListener implements View.OnClickListener{
            Integer integer=0;
            FileOutputStream fos;
            List<Course> courses=new ArrayList<Course>();
            Course course;
            public void onClick(View v) {
                String ctime;
                String cname;
                String id;
                switch (v.getId()) {
                    case R.id.btn_save:
                        id = integer.toString();
                        cname = editView_cname.getText().toString();
                        ctime = "";
                        if (radio01.isChecked()) {
                            ctime = ctime.concat("周一 ");
                        } else if (radio02.isChecked()) {
                            ctime = ctime.concat("周二 ");
                        } else if (radio03.isChecked()) {
                            ctime = ctime.concat("周三 ");
                        } else if (radio04.isChecked()) {
                            ctime = ctime.concat("周四 ");
                        } else if (radio05.isChecked()) {
                            ctime = ctime.concat("周五 ");
                        } else if (radio06.isChecked()) {
                            ctime = ctime.concat("周六 ");
                        } else if (radio07.isChecked()) {
                            ctime = ctime.concat("周日 ");
                        }
                        if (radio11.isChecked()) {
                            ctime = ctime.concat(" 第一节");
                        } else if (radio12.isChecked()) {
                            ctime = ctime.concat(" 第二节");
                        } else if (radio13.isChecked()) {
                            ctime = ctime.concat(" 第三节");
                        } else if (radio14.isChecked()) {
                            ctime = ctime.concat(" 第四节");
                        } else if (radio15.isChecked()) {
                            ctime = ctime.concat(" 第五节");
                        } else if (radio16.isChecked()) {
                            ctime = ctime.concat(" 第六节");
                        } else if (radio17.isChecked()) {
                            ctime = ctime.concat(" 第七节");
                        }
                        course=new Course();
                        course.setCourse(id, cname, ctime);
                        courses.add(course);
                        textView.setText(course.toString());

                        integer++;

                        try {

                            File file = new File(Environment.getExternalStorageDirectory(),
                                    "shujuxl.xml");

                            fos = new FileOutputStream(file);
                            SAXwrite saxwrite = new SAXwrite();
                            saxwrite.setCourses(courses, fos);

                            fos.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.btn_read:
                        String string="";
                        try {

                            File file = new File(Environment.getExternalStorageDirectory(),
                                    "shujuxl.xml");
                            FileInputStream is = new FileInputStream(file);

                            SAXService saxService = new SAXService();
                            List<Course> cous = saxService.getCourses(is);
                            for (Course course : cous) {
                                Log.e("TAG", course.toString());
                                string += course.toString();

                                textView.setText(string);
                                is.close();
                             }
                        }catch (FileNotFoundException e) {
                                e.printStackTrace();
                        } catch (IOException e) {
                                e.printStackTrace();
                        } catch (Exception e) {
                                e.printStackTrace();
                        }
                         break;
                    case R.id.btn_clean:
                        courses.clear();
                        break;
                    default: break;
                }
                }
        }


}
