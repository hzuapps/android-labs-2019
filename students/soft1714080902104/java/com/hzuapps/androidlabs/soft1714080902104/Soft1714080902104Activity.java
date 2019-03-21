package com.example.menu;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class Soft1714080902104Activity extends AppCompatActivity {

    private RelativeLayout view_cpselect;

    private static final int RED = 0xffFF8080;
    private static final int BLUE = 0xff8080FF;
    private static final int CYAN = 0xff80ffff;
    private static final int GREEN = 0xff80ff80;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902104);
        view_cpselect=(RelativeLayout)findViewById(R.id.view_cpselect);
        ChangeColor();
        ChinaMenu();
        ForeignMenu();
    }

    private void ForeignMenu(){



        ImageView icon=new ImageView(this);
        icon.setImageResource(R.drawable.waiguo);
        FloatingActionButton actionButton=new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .setPosition(FloatingActionButton.POSITION_BOTTOM_RIGHT)
                .build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        ImageView itemIcon1 = new ImageView(this);
        itemIcon1.setImageResource(R.drawable.faguo);
        SubActionButton button1 = itemBuilder.setContentView(itemIcon1).build();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902104Activity.this,CXSelect.class);
                startActivity(intent);
            }
        });

        ImageView itemIcon2 = new ImageView(this);
        itemIcon2.setImageResource(R.drawable.hanguo);
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902104Activity.this,CXSelect.class);
                startActivity(intent);
            }
        });

        ImageView itemIcon3 = new ImageView(this);
        itemIcon3.setImageResource(R.drawable.riben);
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902104Activity.this,CXSelect.class);
                startActivity(intent);
            }
        });

        ImageView itemIcon4 = new ImageView(this);
        itemIcon4.setImageResource(R.drawable.taiguo);
        SubActionButton button4 = itemBuilder.setContentView(itemIcon4).build();
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902104Activity.this,CXSelect.class);
                startActivity(intent);
            }
        });

        ImageView itemIcon5 = new ImageView(this);
        itemIcon5.setImageResource(R.drawable.yidali);
        SubActionButton button5 = itemBuilder.setContentView(itemIcon5).build();
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902104Activity.this,CXSelect.class);
                startActivity(intent);
            }
        });

        FloatingActionMenu actionMenu=new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)
                .addSubActionView(button5)
                .attachTo(actionButton)
                .build();
    }

    private void ChinaMenu(){



        ImageView icon=new ImageView(this);
        icon.setImageResource(R.drawable.zhongguo);
        FloatingActionButton actionButton=new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .setPosition(FloatingActionButton.POSITION_TOP_LEFT)
                .build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        ImageView itemIcon1 = new ImageView(this);
        itemIcon1.setImageResource(R.drawable.yuecai);
        SubActionButton button1 = itemBuilder.setContentView(itemIcon1).build();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902104Activity.this,CXSelect.class);
                startActivity(intent);
            }
        });

        ImageView itemIcon2 = new ImageView(this);
        itemIcon2.setImageResource(R.drawable.chuancai);
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902104Activity.this,CXSelect.class);
                startActivity(intent);
            }
        });

        ImageView itemIcon3 = new ImageView(this);
        itemIcon3.setImageResource(R.drawable.kejiacai);
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902104Activity.this,CXSelect.class);
                startActivity(intent);
            }
        });

        ImageView itemIcon4 = new ImageView(this);
        itemIcon4.setImageResource(R.drawable.sucai);
        SubActionButton button4 = itemBuilder.setContentView(itemIcon4).build();
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902104Activity.this,CXSelect.class);
                startActivity(intent);
            }
        });

        ImageView itemIcon5 = new ImageView(this);
        itemIcon5.setImageResource(R.drawable.xiangcai);
        SubActionButton button5 = itemBuilder.setContentView(itemIcon5).build();
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902104Activity.this,CXSelect.class);
                startActivity(intent);
            }
        });

        FloatingActionMenu actionMenu=new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)
                .addSubActionView(button5)
                .attachTo(actionButton)
                .setStartAngle(0)
                .setEndAngle(90)
                .build();
    }

    private void ChangeColor(){

        ValueAnimator colorAnim = ObjectAnimator.ofInt(view_cpselect,"backgroundColor", RED, BLUE);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator()); colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();
    }
}
