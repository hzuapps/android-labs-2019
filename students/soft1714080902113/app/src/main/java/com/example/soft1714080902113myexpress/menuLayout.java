package com.example.soft1714080902113myexpress;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class menuLayout extends LinearLayout {
    public menuLayout(Context context, AttributeSet attrs){
        super(context,attrs);
        LayoutInflater.from(context).inflate(R.layout.menu,this);
        Button MainBtn=(Button) findViewById(R.id.mainBtn);
        Button ReceiveBtn=(Button) findViewById(R.id.receiveBtn);
        Button SendBtn=(Button) findViewById(R.id.sendBtn);
        Button InfoBtn=(Button) findViewById(R.id.infoBtn);

        MainBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getContext(),"Clicked MainActivity",Toast.LENGTH_SHORT).show();
            }
        });

        ReceiveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getContext(),"Clicked ReceiveExpressActivity",Toast.LENGTH_SHORT).show();
            }
        });

        SendBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getContext(),"Clicked SendExpressActivity",Toast.LENGTH_SHORT).show();
            }
        });

        InfoBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(getContext(),"Clicked MyInfoActivity",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
