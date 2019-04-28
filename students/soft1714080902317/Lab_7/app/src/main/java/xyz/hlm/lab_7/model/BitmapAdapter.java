package xyz.hlm.lab_7.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.util.List;

import xyz.hlm.lab_7.R;
import xyz.hlm.lab_7.view.ShowPicture;

public class BitmapAdapter extends RecyclerView.Adapter<BitmapAdapter.ViewHolder>{
    private List<Uri> list;

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView)itemView.findViewById(R.id.image);
        }
    }

    public BitmapAdapter(List<Uri> list){
        this.list = list;
    }

    @Override
    public BitmapAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.images,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BitmapAdapter.ViewHolder holder, int position) {
        final Uri uri = list.get(position);
        try {
            BitmapFactory.Options options =  new  BitmapFactory.Options();
            options.inSampleSize = 8 ;
            final Activity activity = (Activity)holder.imageView.getContext();
            Bitmap bitmap = BitmapFactory.decodeStream(holder.imageView.getContext().getContentResolver().openInputStream(uri),null,options);
            bitmap = cut(bitmap);
            if (bitmap != null)holder.imageView.setImageBitmap(bitmap);
            holder.imageView.setRotation(90);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(activity, ShowPicture.class);
                    intent.setData(uri);
                    activity.startActivity(intent);
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static Bitmap cut(Bitmap bitmap) {
        int width = bitmap.getHeight();
        int length = (bitmap.getWidth() - width)/2;
        return Bitmap.createBitmap(bitmap,length,5,width,width-10,null,false);
    }
}
