package com.exampler.mynews;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

    private List<News> mNewsList;

    public interface OnItemOnClickListener{
        void onItemLongOnClick(View view, int pos);
    }

    private OnItemOnClickListener mOnItemOnClickListener;

    public void setOnItemClickListener(OnItemOnClickListener listener) {
        this.mOnItemOnClickListener = listener;
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        ViewDataBinding binding;
        View newsView;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            newsView = binding.getRoot();
            this.binding=binding;
        }
    }

    public NewsAdapter(List<News> newsList) {
        mNewsList = newsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int Type) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ViewDataBinding binding= DataBindingUtil.inflate(inflater,R.layout.activity_news,parent,false);
        final ViewHolder holder = new ViewHolder(binding);
        holder.newsView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                News news = mNewsList.get(position);
                Intent intent = new Intent(v.getContext(), ContentActivity.class);
                intent.putExtra("title", news.getTitle());
                intent.putExtra("image", news.getImageId());
                intent.putExtra("time", news.getTime());
                intent.putExtra("content", news.getContent());
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ViewDataBinding binding=holder.binding;
        NewsVM viewModel=new NewsVM(mNewsList.get(position));
        binding.setVariable(BR.vm,viewModel);
        binding.setVariable(BR.adapter,this);
        //立即重新绑定数据
        binding.executePendingBindings();
        holder.newsView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mOnItemOnClickListener.onItemLongOnClick(holder.newsView, position);
                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    @BindingAdapter("app:uri")
    public static void setImage(ImageView imageView , String uri){
        if (uri!=null){
            Glide.with(imageView.getContext())
                    .load(uri)
                    .into(imageView);
        }
    }

}
