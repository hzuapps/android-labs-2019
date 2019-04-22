package edu.hzuapps.androidlabs.soft1714080902439;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.huzapps.androidlabs.soft1714080902439.R;

public class Soft1714080902439AddBirthdayActivity extends AppCompatActivity {

    private EditText editText;
    private RadioGroup add_type;
    private RadioButton solar_button;
    private RadioButton lunar_button;
    private Button recall_add;
    private Button set_birth;

    private Spinner birthYear;
    private Spinner birthMonth;
    private Spinner birthDay;

    //存储年份的表
    private List<String> dataYear;
    private List<String> dataMonth;
    private List<String> dataDay;

    //定义年月日的适配器
    private ArrayAdapter<String> adapterBirthYear;
    private ArrayAdapter<String> adapterBirthMonth;
    private ArrayAdapter<String> adapterBirthDay;

    SQLiteDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902439_addbirthday_activity);
        editText=(EditText) findViewById(R.id.editText);
        add_type=(RadioGroup) findViewById(R.id.add_type);
        solar_button=(RadioButton) findViewById(R.id.solar_button);
        lunar_button=(RadioButton) findViewById(R.id.lunar_button);
        birthYear=(Spinner) findViewById(R.id.birth_year);
        birthMonth=(Spinner) findViewById(R.id.birth_month);
        birthDay=(Spinner) findViewById(R.id.birth_day);
        recall_add=(Button) findViewById(R.id.recall_add);
        set_birth=(Button) findViewById(R.id.set_birth);
        //取消添加生日
        recall_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast button=Toast.makeText(Soft1714080902439AddBirthdayActivity.this,"",Toast.LENGTH_SHORT);
                Intent intent=new Intent(Soft1714080902439AddBirthdayActivity.this,Soft1714080902439Activity.class);
                startActivity(intent);
                //button.show();
            }
        });

        db=openOrCreateDatabase("birthday.db",MODE_PRIVATE,null);
        db.execSQL("create table if not exists birthinfo(_id integer primary key autoincrement,name text,yinyang text,date text)");
        set_birth.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(Soft1714080902439AddBirthdayActivity.this,Soft1714080902439Activity.class);
                startActivity(intent);
                toSql();
            }
        });
        //年份设定
        Calendar cal=Calendar.getInstance();
        dataYear=new ArrayList<String>();
        for (int year=0;year<100;year++)
            dataYear.add("" + (cal.get(Calendar.YEAR)-year));// + year


        //把年份的列表添加到适配器里，还有其他下拉选项，this表示上下文
        adapterBirthYear=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dataYear);
        adapterBirthYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        birthYear.setAdapter(adapterBirthYear);//把布局加入适配器
        birthYear.setSelection(0);//默认选中今年

        //月份
        dataMonth=new ArrayList<String>();
        for (int mon=1;mon<=12;mon++)
            dataMonth.add("" + (mon < 10 ? "0" + mon : mon));
        adapterBirthMonth=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dataMonth);
        adapterBirthMonth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        birthMonth.setAdapter(adapterBirthMonth);

        //天
        dataDay=new ArrayList<String>();
        adapterBirthDay=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,dataDay);
        adapterBirthDay.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        birthDay.setAdapter(adapterBirthDay);
        birthMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //选择月，才决定得了一个月有多少天
                dataDay.clear();
                Calendar cal=Calendar.getInstance();
                cal.set(Calendar.YEAR,Integer.valueOf(birthYear.getSelectedItem().toString()));
                cal.set(Calendar.MONTH,position);
                int dayOFmonth=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                for (int day=1;day<=dayOFmonth;day++){
                    dataDay.add("" + (day < 10 ? "0" + day : day));
                }
                adapterBirthDay.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    void toSql(){
        String name=editText.getText().toString();
        String yinyang;
        if (solar_button.isChecked())
            yinyang="公历生日";
        else
            yinyang="农历生日";
        String date=birthYear.getSelectedItem().toString()+"-"+birthMonth.getSelectedItem().toString()+"-"+birthDay.getSelectedItem().toString();
        Toast tip=Toast.makeText(Soft1714080902439AddBirthdayActivity.this,"添加了"+name+"的生日^ v ^",Toast.LENGTH_SHORT);
        String insert="insert into birthinfo(name,yinyang,date) values('"+name+"','"+yinyang+"','"+date+"')";
        tip.show();
        db.execSQL(insert);
    }
}
