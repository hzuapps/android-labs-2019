package edu.hzu.soft1714080902324_4;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Soft1714080902324Activity extends AppCompatActivity implements View.OnClickListener {

    int COLOR[]={Color.RED,Color.YELLOW},i=1;
    TextView text;
    Button b1,b2,b3,b4,b5,b6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft1714080902324activity);

        text = (TextView)findViewById(R.id.text);
        b1 = (Button)findViewById(R.id.cj);
        b2 = (Button)findViewById(R.id.kc);
        b3 = (Button)findViewById(R.id.js);
        b4 = (Button)findViewById(R.id.sk);
        b5 = (Button)findViewById(R.id.ts);
        b6 = (Button)findViewById(R.id.ykt);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);

        new Thread(new Runnable(){
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);
                }
            }
        }).start();
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            text.setTextColor(COLOR[i%2]);
            i++;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cj:
                dialog("成绩");
                break;
            case R.id.kc:
                dialog("课程");
                break;
            case R.id.js:
                dialog("空教室查询");
                break;
            case R.id.sk:
                dialog("水卡余额查询");
                break;
            case R.id.ts:
                dialog("图书馆借阅");
                break;
            case R.id.ykt:
                dialog("一卡通余额");
                break;
        }
    }

    private void dialog(String title){
        AlertDialog alertDialog1 = new AlertDialog.Builder(this)
                .setTitle(title)//标题
                .setMessage("内容待更新")//内容
                .setIcon(R.mipmap.ic_launcher)//图标
                .create();
        alertDialog1.show();
    }
}
