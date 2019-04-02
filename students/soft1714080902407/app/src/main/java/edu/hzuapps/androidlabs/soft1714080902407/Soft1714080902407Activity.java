package edu.hzuapps.androidlabs.soft1714080902407;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Soft1714080902407Activity extends Activity{


    public void onClick(View view){
        Intent intent = new Intent(Soft1714080902407Activity.this,Detail_Activity.class);
        startActivity(intent);
    }

    private  List<Message> list;
    private MessageDao dao;
    private EditText msgET;
    private Button msgBT;
    private MyAdapter adapter;
    private ListView msgLV;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902407_activity);
        initView();
        dao=new MessageDao(this);
        list=dao.queryAll();
        adapter=new MyAdapter();
        msgLV.setAdapter(adapter);
    }

    private void initView(){
        msgLV=(ListView)findViewById(R.id.Listview);
        msgET=(EditText)findViewById(R.id.input_text);
    }

    public void add(View v){
        String msg=msgET.getText().toString().trim();
        Message m=new Message(msg);
        dao.insert(m);
        list.add(m);
        adapter.notifyDataSetChanged();
        msgLV.setSelection(msgLV.getCount()-1);
        msgET.setText("");
    }

    private class MyAdapter extends BaseAdapter{
        public int getCount(){
            return list.size();
        }

        public Object getItem(int position) {
             return list.get(position);
        }

        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parents) {
            View item=convertView!=null?convertView:View.inflate(getApplicationContext(),R.layout.item,null);
            TextView msgTV=(TextView)item.findViewById(R.id.edittext);
            final Message a=list.get(position);
            msgTV.setText(a.getMsg());
            return item;
        }
    }


}
