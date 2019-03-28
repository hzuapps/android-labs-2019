package edu.hzuapps.androidlabs.soft1714080902232;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Soft1714080902232Activity_Card extends FrameLayout {
    private TextView text;
    private int number=0;
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
        if(number<2){
            text.setText("");
        }else{
            if(number>=64){
                text.setTextColor(0xffffff00);
            }else{
                text.setTextColor(0xff000000);
            }
            text.setText(number+"");
        }
    }

    public Soft1714080902232Activity_Card(Context context) {
        super(context);
        text = new TextView(context);
        text.setTextSize(28);
        text.setBackgroundColor(0x9966cccc);
        text.setGravity(Gravity.CENTER);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(-1,-1);
        params.setMargins(10, 10, 0,0);
        addView(text,params);
    }
}
