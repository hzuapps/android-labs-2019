package com.example.practice;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class PracticeAdapter extends RecyclerView.Adapter<PracticeAdapter.ViewHolder> {

    private List<Data> mDataList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View dataView;
        ImageView dataImage;
        TextView dataName;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            dataView = view;
            dataImage = (ImageView) view.findViewById(R.id.data_image);
            dataName = (TextView) view.findViewById(R.id.data_name);
            imageView = (ImageView) view.findViewById(R.id.image_view);
        }
    }

    public PracticeAdapter(List<Data> dataList) {
        mDataList = dataList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, final int Type) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.dataView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Data data = mDataList.get(position);
                String url = "http://guolin.tech/book.png";
                Glide.with(v.getContext())
                        .load(url)
                        .into(holder.imageView);
                Toast.makeText(v.getContext(), data.getName() + (position + 1), Toast.LENGTH_SHORT).show();
            }
        });
        holder.dataImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Data data = mDataList.get(position);
                Toast.makeText(v.getContext(), data.getName() + (position + 1), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), SecondActivity.class);
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Data data = mDataList.get(position);
        holder.dataImage.setImageResource(data.getImageId());
        holder.dataName.setText(data.getName() + (position + 1));
    }


    @Override
    public int getItemCount() {
        return mDataList.size();
    }

}
