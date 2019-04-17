package edu.hzuapps.androidlabs.soft1714080902128;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
public class ThirdActivity extends AppCompatActivity {

    private Button return01;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);
        return01=(Button)findViewById(R.id.button_return);
        Intent intent=getIntent();
        String s=intent.getStringExtra("name");
        ((TextView)findViewById(R.id.textview_name)).setText("名称:"+s);
        int x=getImageResourceId(s);//获得传来名称对应的id
        ((ImageView)findViewById(R.id.textview_zhaopian)).setBackgroundResource(x);
        return01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public int getImageResourceId(String name) {
        R.drawable drawables=new R.drawable();
        //默认的id
        int resId=0x7f02000b;
        try {
            //根据字符串字段名，取字段//根据资源的ID的变量名获得Field的对象,使用反射机制来实现的
            java.lang.reflect.Field field=R.drawable.class.getField(name);
            //取值
            resId=(Integer)field.get(drawables);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resId;
    }
}