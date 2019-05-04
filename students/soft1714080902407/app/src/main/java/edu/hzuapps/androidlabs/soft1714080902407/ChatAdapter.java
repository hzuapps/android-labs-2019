package edu.hzuapps.androidlabs.soft1714080902407;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ChatAdapter extends BaseAdapter {
    private List<Message> list;
    private Context context;

    public void setList(List<Message> list){
        this.list=list;
        notifyDataSetChanged();
    }
    public ChatAdapter(List<Message> list,Context context){
        this.list=list;
        this.context=context;
    }

    class HolderView{
        TextView chattime,chatmsg;
    }

    @Override
    public int getCount(){
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
    public boolean isEnabled(int position) {
        return false;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderView holderView = null;
        Message message = list.get(position);
        boolean isMeSend = message.getSend();
        if (holderView == null) {
            holderView = new HolderView();
            if (isMeSend == true) {
                convertView = View.inflate(context, R.layout.item_chat_right_bubble, null);
                holderView.chatmsg = (TextView) convertView.findViewById(R.id.tv_chatmsg);
                holderView.chatmsg.setText(message.getMsg());
                holderView.chattime = (TextView) convertView.findViewById(R.id.tv_chattime);
                holderView.chattime.setText(message.getTime());
            } else {
                convertView = View.inflate(context, R.layout.item_chat_left_bubble, null);

            }
            convertView.setTag(holderView);
        } else {
            holderView = (HolderView) convertView.getTag();
        }
        return convertView;
    }

}
