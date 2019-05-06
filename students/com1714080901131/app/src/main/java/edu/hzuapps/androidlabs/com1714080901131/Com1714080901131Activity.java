package edu.hzuapps.androidlabs.com1714080901131;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Com1714080901131Activity extends AppCompatActivity {
private LinearLayout linear;
private ArrayList<Record> recordArrayList;
private TextView changeText;
private Button b;
SQDB myDB;
private ListView myListView;
private String url_image="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555160992873&di=d1414fd286331f6cec589f7da0d8a8b9&imgtype=0&src=http%3A%2F%2Fimg18.3lian.com%2Fd%2Ffile%2F201704%2F10%2Fae2c6607bb5c12c371f8e042de1832a6.jpg";
private Handler handler=new Handler()
    {
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Bitmap bmp=(Bitmap)msg.obj;
                    linear.setBackground(new BitmapDrawable(bmp));
                    break;
            }
        };
    };
    private Bitmap getImage(String url_image) {
        Bitmap bmp = null;
        try {
            URL Myurl = new URL(url_image);
            HttpURLConnection conn = (HttpURLConnection) Myurl.openConnection();
            conn.setConnectTimeout(6000);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.connect();
            InputStream is = conn.getInputStream();
            bmp = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bmp;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_1714080901131_activity);
        linear=findViewById(R.id.linear);
        myListView=findViewById(R.id.list_view);
        changeText=findViewById(R.id.text_background);
        b=findViewById(R.id.createButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Com1714080901131Activity.this,Com1714080901131Activity2.class);
                startActivity(intent);
            }
        });
        changeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Bitmap bmp = getImage(url_image);
                        Message msg = new Message();
                        msg.what = 0;
                        msg.obj = bmp;
                        handler.sendMessage(msg);
                    }
                }).start();
            }
        });

        //显示备忘录代码
        myDB =new SQDB(this);
        SQLiteDatabase db = myDB.getReadableDatabase();
        recordArrayList=new ArrayList<>();
        Cursor cursor=db.rawQuery("select * from Record",null);
        while(cursor.moveToNext()) {
            String id = cursor.getString(cursor.getColumnIndex("id"));
            String titleName = cursor.getString(cursor.getColumnIndex("titleName"));
            String textBody = cursor.getString(cursor.getColumnIndex("textBody"));
            Record record = new Record(id,titleName, textBody);
            recordArrayList.add(record);
        }
        myListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return recordArrayList.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view;
                if(convertView==null)
                {
                    LayoutInflater inflater=Com1714080901131Activity.this.getLayoutInflater();
                    view=inflater.inflate(R.layout.com_1714080901131_activity3,null);
                }
                else
                {
                    view=convertView;
                }
                Record record=recordArrayList.get(position);
                TextView id=(TextView)view.findViewById(R.id.list_item_id);
                TextView titleName=(TextView)view.findViewById(R.id.list_item_title);
                TextView textBody=(TextView)view.findViewById(R.id.list_item_body);
                id.setText(record.getId());
                titleName.setText(record.getTitleName());
                textBody.setText(record.getTextBody());
                return view;
            }
        });


    }


}


