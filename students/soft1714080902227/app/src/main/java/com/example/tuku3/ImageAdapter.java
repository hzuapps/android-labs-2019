package com.example.tuku3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by sl on 2018/1/13.
 */

public class ImageAdapter extends RecyclerView.Adapter <ImageAdapter.ViewHolder>{

    String mAddress[];
    Context mContext;

    public ImageAdapter(String address[]){
        this.mAddress = address;
    }

    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ImageAdapter.ViewHolder holder, final int position) {
        Glide.with(mContext).load(mAddress[position]).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ImagePreviewActivity.class);
                intent.putExtra("ImageUrl",mAddress[position]);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mAddress.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image_item);
        }
    }

}
