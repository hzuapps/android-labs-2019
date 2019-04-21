package edu.hzuapps.androidlabs.com1714080901123;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.util.List;

import edu.hzuapps.androidlabs.com1714080901123.Com1714080901123ClassDrawableID;
import edu.hzuapps.androidlabs.com1714080901123.Com1714080901123ClassGearConfig;
import edu.hzuapps.androidlabs.com1714080901123.Com1714080901123ClassGearConfigService;

public class Com1714080901123ActivitySelectPlayersConfig extends AppCompatActivity implements AdapterView.OnItemClickListener {

    protected ListView lv_config;
    protected LinearLayout loading;
    protected List<Com1714080901123ClassGearConfig> gearConfigs;
    protected Com1714080901123ClassDrawableID drawableID = new Com1714080901123ClassDrawableID();

    //ListView适配器
    protected class GearConfigAdapter extends BaseAdapter {
        //ListView的item数
        public int getCount() {
            return gearConfigs.size();
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
            View view = View.inflate(Com1714080901123ActivitySelectPlayersConfig.this, R.layout.config_item, null);
            ImageView imageView = (ImageView)view.findViewById(R.id.imageView_config_item_weapon);
            TextView textViewName = (TextView)view.findViewById(R.id.textView_config_item_name);
            TextView textViewDescribe = (TextView)view.findViewById(R.id.textView_config_item_describe);
            Com1714080901123ClassGearConfig gearConfig = gearConfigs.get(position); //每个item与gearConfigs这个List中的每一项绑定

            //设置每个item的内容显示
            imageView.setImageResource(drawableID.getWeapon(gearConfig.getWeaponID()-1)); //根据gearConfig对象的weapon值加载图片
            textViewName.setText(gearConfig.getPlayerName());
            textViewDescribe.setText(gearConfig.getDescribe());

            return view;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com1714080901123_select_players_config);
        getSupportActionBar().hide();

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);  //为toolbar设置返回键有效
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        lv_config = (ListView)findViewById(R.id.lv_config);
        loading = (LinearLayout)findViewById(R.id.loading_config);
        //Log.d("SelectPlayerConfig", "success_onCreate"); //test
        loadXMLData();
        lv_config.setOnItemClickListener(this); //设置ListView的item按键监听
    }

    //设置item的按键响应
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Com1714080901123ClassGearConfig gearConfig = gearConfigs.get(position);
        Intent intent = new Intent();
        intent.putExtra("weaponID",gearConfig.getWeaponID());
        intent.putExtra("abilityID11",gearConfig.getAbilityID11());
        intent.putExtra("abilityID12",gearConfig.getAbilityID12());
        intent.putExtra("abilityID13",gearConfig.getAbilityID13());
        intent.putExtra("abilityID14",gearConfig.getAbilityID14());
        intent.putExtra("abilityID21",gearConfig.getAbilityID21());
        intent.putExtra("abilityID22",gearConfig.getAbilityID22());
        intent.putExtra("abilityID23",gearConfig.getAbilityID23());
        intent.putExtra("abilityID24",gearConfig.getAbilityID24());
        intent.putExtra("abilityID31",gearConfig.getAbilityID31());
        intent.putExtra("abilityID32",gearConfig.getAbilityID32());
        intent.putExtra("abilityID33",gearConfig.getAbilityID33());
        intent.putExtra("abilityID34",gearConfig.getAbilityID34());
        setResult(0,intent);
        finish();
    }

    protected void loadXMLData() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        //Log.d("SelectPlayerConfig", "success_come_to_loadXMLData"); //test
        asyncHttpClient.get(getString(R.string.url_gear_config_xml),
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, org.apache.http.Header[] headers, byte[] bytes) {
                        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
                        gearConfigs = Com1714080901123ClassGearConfigService.getGearConfigs(byteArrayInputStream);  //解析XML

                        if(gearConfigs == null) {
                            Toast.makeText(Com1714080901123ActivitySelectPlayersConfig.this,
                                    "解析失败",Toast.LENGTH_SHORT).show();
                        } else {
                            //解析成功，更新界面
                            loading.setVisibility(View.INVISIBLE);
                            lv_config.setAdapter(new GearConfigAdapter());
                        }
                    }

                    @Override
                    public void onFailure(int i, org.apache.http.Header[] headers, byte[] bytes, Throwable throwable) {
                        Toast.makeText(Com1714080901123ActivitySelectPlayersConfig.this,"请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
