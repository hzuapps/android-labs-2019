package edu.hzuapps.androidlabs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.hzuapps.androidlabs.bean.WeatherInfo;
import edu.hzuapps.androidlabs.soft1709081602719.R;

public class DownloadAdapter extends BaseAdapter {
    private List<WeatherInfo> list;
    private Context context;
    public DownloadAdapter(List<WeatherInfo> list,Context context){
        this.list=list;
        this.context=context;

    }

    @Override
    public int getCount() {
        return list.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(holder == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item,null);
            holder = new ViewHolder();
            holder.iv_icon = convertView.<ImageView>findViewById(R.id.iv_icon);
            holder.tv_date = convertView.<TextView>findViewById(R.id.tv_date);
            holder.tv_weather = convertView.<TextView>findViewById(R.id.tv_weather);
            holder.tv_temp = convertView.<TextView>findViewById(R.id.tv_temp);
            holder.tv_wind = convertView.<TextView>findViewById(R.id.tv_wind);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_date.setText(list.get(position).getDate());
        holder.tv_weather.setText(list.get(position).getWeather());
        holder.tv_temp.setText(list.get(position).getTemperature());
        holder.tv_wind.setText(list.get(position).getWinddirect());
        return convertView;
    }
    static class ViewHolder{
        ImageView iv_icon;
        TextView tv_date,tv_weather,tv_temp,tv_wind;
    }
}
