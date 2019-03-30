package edu.androidlabs.soft1709081602719;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;
public class MyAdapter extends BaseAdapter {

    List<FileInfo> list;
    LayoutInflater inflater;


    public MyAdapter(Context context) {

        this.inflater = LayoutInflater.from(context);

    }

    public void setList(List<FileInfo> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return (list == null)?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.item, null);
            holder = new ViewHolder();
            holder.icon = convertView.findViewById(R.id.icon);
            holder.name = convertView.findViewById(R.id.name);
            holder.path = convertView.findViewById(R.id.path);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        FileInfo item = list.get(position);
        holder.icon.setImageResource(item.icon);
        holder.name.setText(item.name);
        return convertView;
    }

    public class ViewHolder{
        ImageView icon;
        TextView name;
        TextView path;
    }
}
