package edu.hzuapps.androidlabs.soft1714080902439;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import java.io.ByteArrayInputStream;
import java.util.List;
import edu.huzapps.androidlabs.soft1714080902439.R;
import edu.hzuapps.androidlabs.soft1714080902439.service.StarInfoService;

public class Soft1714080902439ConstellationActivity extends AppCompatActivity {
    private ListView constellation_list;
    private LinearLayout loading;
    private static List<StarInfo> starInfos;

    //ListView适配器
    private class StarAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return starInfos.size();
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
            View view = View.inflate(Soft1714080902439ConstellationActivity.this, R.layout.soft_1714080902439_constellation_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            TextView constellation_title = (TextView) view.findViewById(R.id.constellation_title);
            TextView constellation_time = (TextView) view.findViewById(R.id.constellation_time);
            TextView constellation_desc = (TextView) view.findViewById(R.id.constellation_desc);
            StarInfo starInfo = starInfos.get(position);
            String icon = starInfo.getIconPath();
            //星座图
            Glide.with(Soft1714080902439ConstellationActivity.this).load(icon).into(imageView);
            //星座名
            constellation_title.setText(starInfo.getName());
            //星座时间
            constellation_time.setText(starInfo.getTime());
            //星座特点
            constellation_desc.setText(starInfo.getDesc());
            return view;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_171408092439_constellation);
        constellation_list=(ListView) findViewById(R.id.constellation_list);
        loading=(LinearLayout) findViewById(R.id.loading);
        fillData();
    }

    private void fillData() {
        AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.get(getString(R.string.serverurl),new AsyncHttpResponseHandler(){
            //请求成功
            public void onSuccess(String content){
                super.onSuccess(content);
                Toast.makeText(Soft1714080902439ConstellationActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                byte[] bytes=content.getBytes();
                ByteArrayInputStream bais=new ByteArrayInputStream(bytes);
                starInfos=StarInfoService.getStarInfos(bais);
                if (starInfos==null){
                    //解析失败
                    Toast.makeText(Soft1714080902439ConstellationActivity.this,"解析失败",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Soft1714080902439ConstellationActivity.this,"解析成功",Toast.LENGTH_SHORT).show();
                    loading.setVisibility(View.INVISIBLE);
                    constellation_list.setAdapter(new StarAdapter());
                }
            }
            //请求失败
            public void onFailure(Throwable error,String content){
                super.onFailure(error,content);
                Toast.makeText(Soft1714080902439ConstellationActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
