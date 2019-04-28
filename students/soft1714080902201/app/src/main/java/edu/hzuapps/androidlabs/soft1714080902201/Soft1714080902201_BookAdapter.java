package edu.hzuapps.androidlabs.soft1714080902201;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Soft1714080902201_BookAdapter extends RecyclerView.Adapter<Soft1714080902201_BookAdapter.ViewHolder>{
    private List<Soft1714080902201_Book1> mBook1List;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView bookImage;
        TextView bookName;

        public ViewHolder(View view){
            super(view);
            bookImage = (ImageView) view.findViewById(R.id.book_image);
            bookName = (TextView) view.findViewById(R.id.book_name);
        }
    }

    public Soft1714080902201_BookAdapter(List<Soft1714080902201_Book1> book1List){
        mBook1List = book1List;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.soft1714080902201_book_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Soft1714080902201_Book1 book1 = mBook1List.get(position);
        holder.bookImage.setImageResource(book1.getImage());
        holder.bookName.setText(book1.getName());
    }

    @Override
    public int getItemCount(){
        return mBook1List.size();
    }
}
