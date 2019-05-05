package edu.hzuapps.androidlabs.soft1714080902407;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Soft1714080902407Activity extends Activity {
    private ListView listView;
    private EditText editText;
    private Button button;
    private List<Message> list=  new ArrayList<>();
    private ChatAdapter adapter;
    private MessageDao messageDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902407_activity);

        listView=(ListView)findViewById(R.id.listview);
        editText=(EditText)findViewById(R.id.edittext_input);
        button=(Button)findViewById(R.id.button_send);

        adapter=new ChatAdapter(list,this);
        messageDao=new MessageDao(this);
        list=messageDao.queryAll();
        listView.setAdapter(adapter);


        adapter.setList(list);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editText.getText().toString())) {
                    Toast.makeText(Soft1714080902407Activity.this, "发送内容不能为空", 0).show();
                    return;
                }
                Message message=new Message();
                message.setMsg(editText.getText().toString());
                message.setTime(time());
                message.setSend(true);
                messageDao.insert(message);
                list.add(message);
/*
                Message receive_message = new Message();
                receive_message.setSend(false);
                messageDao.insert(receive_message);
                list.add(receive_message);//测试接收信息
*/
                adapter.setList(list);
                adapter.notifyDataSetChanged();
                listView.setSelection(list.size()-1);
                editText.setText("");
            }
        });
    }

    public static String time(){
        SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dff.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        String ee = dff.format(new Date());
        return ee;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.chat_image) {
            Intent intent = new Intent(Soft1714080902407Activity.this,Detail_Activity.class);
            startActivity(intent);
        }
    }

}
