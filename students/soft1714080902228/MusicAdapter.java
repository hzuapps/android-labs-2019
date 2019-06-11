package edu.hzuapps.androidlabs.soft1714080902228;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MusicAdapter extends ArrayAdapter {
    private final int resourceId;

    public MusicAdapter(Context context, int textViewResourceId, List<Music> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Music music = (Music)getItem(position); // 获取当前项的Fruit实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象
        ImageView fruitImage = (ImageView) view.findViewById(R.id.music_img);//获取该布局内的图片视图
        TextView fruitName = (TextView) view.findViewById(R.id.music_name);//获取该布局内的文本视图
        fruitImage.setImageResource(music.getImageId());//为图片视图设置图片资源
        fruitName.setText(music.getName());//为文本视图设置文本内容
        return view;
    }
}
