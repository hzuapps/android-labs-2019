package edu.hzuapps.androidlabs.soft1714080902421;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TabHost;


public class StartStudyActivity extends AppCompatActivity{
    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_study);

        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        // 为TabHost添加标签
        // 新建一个newTabSpec(newTabSpec)用来指定该标签的id（就是用来区分标签）的
        // 设置其标签和图表(setIndicator)
        // 设置内容(setContent)
        // /*		 * 设置选项卡 : -- 设置按钮名称 : setIndicator(时钟); -- 设置选项卡内容 : setContent(),
        // * 可以设置视图组件, 可以设置Activity, 也可以设置Fragement;
        tabHost.addTab(tabHost.newTabSpec("tabClock").setIndicator("闹钟")
               .setContent(R.id.tabClock));
        tabHost.addTab(tabHost.newTabSpec("tabTimer").setIndicator("计时器")
                .setContent(R.id.tabTimer));
    }
}
