package edu.hzuapps.androidlabs.soft1714080902333.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

import java.util.List;

import edu.hzuapps.androidlabs.soft1714080902333.R;
import edu.hzuapps.androidlabs.soft1714080902333.bean.NewsInfo;

public class NewsAdapter extends ArrayAdapter<NewsInfo> {
    public NewsAdapter(Context context, List<NewsInfo> objects) {
        super(context, R.layout.news_item, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        NewsInfo news= getItem(position);
        View view;
        ViewHolder viewHolder;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.news_item, null);
            viewHolder = new ViewHolder();

            // View寻找
            viewHolder.siv_icon = (SmartImageView) view.findViewById(R.id.siv_icon);
            viewHolder.tv_title = (TextView) view.findViewById(R.id.tv_title);
            viewHolder.tv_content = (TextView) view.findViewById(R.id.tv_content);
            viewHolder.tv_comment = (TextView) view.findViewById(R.id.tv_comment);

            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        // 加载指定内容
        viewHolder.siv_icon.setImageUrl(news.getIcon());
        System.out.println(news.getIcon());
        viewHolder.tv_title.setText(news.getTitle());
        viewHolder.tv_content.setText(news.getContent());
        viewHolder.tv_comment.setText("评论数：" + news.getComment());

        return view;
    }

    class ViewHolder {
        TextView tv_title;
        TextView tv_content;
        TextView tv_comment;
        SmartImageView siv_icon;
    }
}
