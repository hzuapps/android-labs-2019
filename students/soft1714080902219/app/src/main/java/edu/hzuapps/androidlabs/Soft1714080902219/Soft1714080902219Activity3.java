package edu.hzuapps.androidlabs.Soft1714080902219;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Soft1714080902219Activity3 extends FragmentActivity implements View.OnClickListener {

    //定义3个Fragment的对象
    protected Soft1714080902219Fragment1 fragment1;
    protected Soft1714080902219Fragment2 fragment2;
    protected Soft1714080902219Fragment3 fragment3;

    //帧布局对象,就是用来存放Fragment的容器
    private FrameLayout flayout;

    //定义底部导航栏的三个布局
    private LinearLayout ll_tab1;
    private LinearLayout ll_tab2;
    private LinearLayout ll_tab3;

    //定义底部导航栏中的ImageView与TextView
    private ImageView iv1;
    private ImageView iv2;
    private ImageView iv3;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;

    //定义要用的颜色值
    private int whirt = 0xFFFFFFFF;
    private int gray = 0xFF7597B3;
    private int blue =0xFF0AB2FB;


    //定义要用的颜色值
    FragmentManager fManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902219_activity3);
        fManager = getSupportFragmentManager();

        initViews();
        setChioceItem(0);
    }
    //完成组件的初始化

    public void initViews()

    {
        iv1 = (ImageView) findViewById(R.id.iv1);
        iv2 = (ImageView) findViewById(R.id.iv2);
        iv3 = (ImageView) findViewById(R.id.iv3);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        ll_tab1 = (LinearLayout) findViewById(R.id.ll_tab1);
        ll_tab2 = (LinearLayout) findViewById(R.id.ll_tab2);
        ll_tab3 = (LinearLayout) findViewById(R.id.ll_tab3);
        ll_tab1.setOnClickListener(this);
        ll_tab2.setOnClickListener(this);
        ll_tab3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_tab1:
                setChioceItem(0);
                break;
            case R.id.ll_tab2:
                setChioceItem(1);
                break;
            case R.id.ll_tab3:
                setChioceItem(2);
                break;
            default:
                break;

        }
    }
    //定义一个选中一个item后的处理

    public void setChioceItem(int index)
    {
        //重置选项+隐藏所有Fragment
        FragmentTransaction transaction = fManager.beginTransaction();
        clearChioce();
        hideFragments(transaction);
        switch (index) {
            case 0:
                iv1.setImageResource(R.drawable.xinwen);
                tv1.setTextColor(blue);
                ll_tab1.setBackgroundResource(R.drawable.beijing);
                if (fragment1 == null) {
                    // 如果fg1为空，则创建一个并添加到界面上
                    fragment1 = new Soft1714080902219Fragment1();
                    transaction.add(R.id.main_content, fragment1);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fragment1);
                }
                break;
            case 1:
                iv2.setImageResource(R.drawable.shipin);
                tv2.setTextColor(blue);
                ll_tab2.setBackgroundResource(R.drawable.beijing);
                if (fragment2 == null) {
                    // 如果fg1为空，则创建一个并添加到界面上
                    fragment2 = new Soft1714080902219Fragment2();
                    transaction.add(R.id.main_content, fragment2);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fragment2);
                }
                break;
            case 2:
                iv3.setImageResource(R.drawable.ren);
                tv3.setTextColor(blue);
                ll_tab3.setBackgroundResource(R.drawable.beijing);
                if (fragment3 == null) {
                    // 如果fg1为空，则创建一个并添加到界面上
                    fragment3 = new Soft1714080902219Fragment3();
                    transaction.add(R.id.main_content, fragment3);
                } else {
                    // 如果MessageFragment不为空，则直接将它显示出来
                    transaction.show(fragment3);
                }
                break;
        }
        transaction.commit();
    }
    //隐藏所有的Fragment,避免fragment混乱
    private void hideFragments(FragmentTransaction transaction) {
        if (fragment1 != null) {
            transaction.hide(fragment1);
        }
        if (fragment2 != null) {
            transaction.hide(fragment2);
        }
        if (fragment3 != null) {
            transaction.hide(fragment3);
        }
    }
    //定义一个重置所有选项的方法

    public void clearChioce()
    {
        iv1.setImageResource(R.drawable.xinwen);//zhuyi
        ll_tab1.setBackgroundColor(whirt);
        tv1.setTextColor(gray);
        iv2.setImageResource(R.drawable.shipin);
        ll_tab2.setBackgroundColor(whirt);
        tv2.setTextColor(gray);
        iv3.setImageResource(R.drawable.ren);
        ll_tab3.setBackgroundColor(whirt);
        tv3.setTextColor(gray);
    }
}
