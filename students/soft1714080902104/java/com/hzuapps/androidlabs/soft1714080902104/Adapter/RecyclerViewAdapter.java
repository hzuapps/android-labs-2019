package com.example.menu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.menu.CPSelect;
import com.example.menu.CXSelect;
import com.example.menu.Model.CaiPu;
import com.example.menu.R;
import com.example.menu.Soft1714080902104Activity;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context context;


    private List<CaiPu>caiPuList=new ArrayList<>();

    public RecyclerViewAdapter(Context context, List<CaiPu> data) {
        this.context = context;
        this.caiPuList = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_cp, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        Glide.with(context).load(caiPuList.get(position).getAlbum()).into(holder.imageView);
        holder.textView.setText(caiPuList.get(position).getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,CPSelect.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.caiPuList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        private CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.imageView = (ImageView) itemView.findViewById(R.id.cp_tupian);
            this.textView=(TextView)itemView.findViewById((R.id.cp_name));
            this.cardView=(CardView)itemView.findViewById(R.id.cp_cardview);
        }
    }

}
