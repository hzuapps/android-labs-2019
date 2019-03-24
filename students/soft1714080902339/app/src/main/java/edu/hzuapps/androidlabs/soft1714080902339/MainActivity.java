package edu.hzuapps.androidlabs.soft1714080902339;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //savedInstanceState：已经保存的实例对象
        //实例跟对象都是真正存在内存里的
        /**
         * CXY笔记
         * 1.如果数据发生变化，状态就发生变化
         *   Data=State
         * 2.intent帮我们做交互的
         * 3.所有的交互都是发生在某一个控件上的（无论是图片还是文本）（例如可以通过按钮点击跳转到某张图片上）
         */
        setContentView(R.layout.activity_main);
    }
}
