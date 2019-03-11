package edu.hzuapps.androidlabs.soft1714080902120;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Soft1714080902120HpAdapter extends ArrayAdapter<Soft1714080902120HistoryPeople> {
    public Soft1714080902120HpAdapter(Context context, int resource,
                                      List<Soft1714080902120HistoryPeople> objects) {
        super(context, resource, objects);
    }
     @Override
    public View getView(int position,View converView,ViewGroup parent)
     {
         //获取人物数据
         final Soft1714080902120HistoryPeople soft1714080902120HistoryPeople=getItem(position);

         //创建布局
         View oneHpView = LayoutInflater.from(getContext()).inflate(R.layout.hp_item,parent,false);

         //获取视图

         TextView textView=(TextView) oneHpView.findViewById(R.id.HistoryPeople_name_textView);

         //设置数据展现
         textView.setText(soft1714080902120HistoryPeople.getName());

         oneHpView.setOnClickListener(new View.OnClickListener()
         {
             @Override
             public void onClick(View v) {
                 //先初始化一个Intent
                 Intent intent= new Intent(getContext(),Soft1714080902120DetailActivity.class);

                 //传递数据
                 intent.putExtra("soft1714080902120_lvde",soft1714080902120HistoryPeople.getLvde());
                 intent.putExtra("soft1714080902120_repu",soft1714080902120HistoryPeople.getRepu());
                 //start跳转
                 getContext().startActivity(intent);
             }


         });
         return oneHpView;
     }

    }


