package edu.hzuapps.androidlabs.soft1714080902309;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Soft1714080902309Activity2 extends Activity {
    //定义要显示的书名和图片
    private String[] bookname={"红楼梦","西游记","水浒传","三国演义",
            "鲁宾逊飘流记","简·爱","傲慢与偏见","钢铁是怎样炼成的"};
    private int[] imageId={R.drawable.rendream,R.drawable.westtrave,R.drawable.water,R.drawable.threecountry,
            R.drawable.lulutrave,R.drawable.jianai,R.drawable.and,R.drawable.howto};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902309_activity2);
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

        SimpleAdapter simplead = new SimpleAdapter(this,list_map,R.layout.soft_1714080902309_activity3,new String[]{"name","fengmian"},new int[]{R.id.name,R.id.fegmian});
        ListView lis1 =(ListView)findViewById(R.id.listview);
        lis1.setAdapter(simplead);
    }
}

