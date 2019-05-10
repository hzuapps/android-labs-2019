package edu.hzuapps.androidlabs.homework;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookStore extends Activity {
    private EditText et_info;
    private Button btn_save;
    private Button btn_read;
    //定义要显示的书名和图片
    private String[] bookname={"红楼梦","西游记","水浒传","三国演义",
            "鲁宾逊飘流记","简·爱","傲慢与偏见","钢铁是怎样炼成的"};
    private int[] imageId={R.drawable.rendream,R.drawable.westtrave,R.drawable.water,R.drawable.threecountry,
            R.drawable.lulutrave,R.drawable.jianai,R.drawable.and,R.drawable.howto};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index2_activity);

        //获取布局文件中的控件
        et_info = (EditText) findViewById(R.id.et_info);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_read = (Button) findViewById(R.id.btn_read);
        //设置按钮点击函数
        btn_save.setOnClickListener(new ButtonListener());
        btn_read.setOnClickListener(new ButtonListener());

        //获取activity中的listview对象
        ListView listView =(ListView) findViewById(R.id.listview);
        //定义一个适配器对象list_map
        List<Map<String,Object>> list_map = new ArrayList<Map<String,Object>>();
        for (int i=0;i<bookname.length;i++){
            //创建一个键值对的Map集合pr，用来存放名字和头像
            Map<String,Object> pr = new HashMap<String,Object>();
            pr.put("fengmian",imageId[i]);
            pr.put("name",bookname[i]);
            //把这个存放好数据的Map集合-pr,放入到list(list_map)中
            list_map.add(pr);
        }

        SimpleAdapter simplead = new SimpleAdapter(this,list_map,R.layout.index3_activity,new String[]{"name","fengmian"},new int[]{R.id.name,R.id.fegmian});
        ListView lis1 =(ListView)findViewById(R.id.listview);
        lis1.setAdapter(simplead);
        lis1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            //list点击事件
            Bundle bundle = new Bundle();
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                // TODO: Implement this method
                switch(position){
                    case 0://第一个item
                        startActivity(new Intent(BookStore.this,
                                ShowDetail.class));
                    case 1://第二个item
                        Intent intent2 = new Intent();
                        intent2.setClass(BookStore.this, ShowDetail.class);
                        startActivity(intent2);
                        break;
                    case 2://第三个item
                        Intent intent3 = new Intent();
                        intent3.setClass(BookStore.this, ShowDetail.class);
                        startActivity(intent3);
                        break;
                }
            }
        });
    }

    //定义Button按钮点击事件
    private class ButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_save:
                    String saveinfo = et_info.getText().toString().trim();
                    FileOutputStream fileOutputStream;
                    try {
                        fileOutputStream=openFileOutput("data.txt", Context.MODE_APPEND);
                        fileOutputStream.write(saveinfo.getBytes());
                        fileOutputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(BookStore.this,"谢谢您的建议！",Toast.LENGTH_SHORT).show();
                    break;

                case R.id.btn_read:
                    String content="";
                    try{
                        FileInputStream fileInputStream=openFileInput("data.txt");
                        byte[] buffer=new byte[fileInputStream.available()];
                        fileInputStream.read(buffer);
                        content=new String(buffer);
                        fileInputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(BookStore.this,"您提的建议我们已经采纳："+content,Toast.LENGTH_SHORT).show();
                    break;


                default: break;
            }
        }
    }
}
