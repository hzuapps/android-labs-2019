package com.example.menu;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.menu.Model.CaiPu;
import com.example.menu.Model.Step;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;

public class Soft1714080902104Activity extends AppCompatActivity {

    private RelativeLayout view_cpselect;
    private ImageView imageView;
    private List<CaiPu> caiPuList=new ArrayList<>();
    private List<Step>stepList=new ArrayList<>();
    private String cid;

    private static final int RED = 0xffFF8080;
    private static final int BLUE = 0xff8080FF;
    private static final int CYAN = 0xff80ffff;
    private static final int GREEN = 0xff80ff80;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902104_activity);
        view_cpselect=(RelativeLayout)findViewById(R.id.view_cpselect);
        imageView=(ImageView)findViewById(R.id.main_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        if(!isConn(getApplicationContext())){
            setNetworkMethod(Soft1714080902104Activity.this);
        }
        ChangeColor();
        ChinaMenu();
        ForeignMenu();
    }

    private void ForeignMenu(){



        ImageView icon=new ImageView(this);
        icon.setImageResource(R.drawable.waiguo);
        FloatingActionButton actionButton=new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .setPosition(FloatingActionButton.POSITION_BOTTOM_RIGHT)
                .build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        ImageView itemIcon1 = new ImageView(this);
        itemIcon1.setImageResource(R.drawable.faguo);
        SubActionButton button1 = itemBuilder.setContentView(itemIcon1).build();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cid="125";
                getDatasync();
                Intent intent=new Intent(Soft1714080902104Activity.this,CXSelect.class);
                startActivity(intent);
            }
        });

        ImageView itemIcon2 = new ImageView(this);
        itemIcon2.setImageResource(R.drawable.hanguo);
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cid="18";
                getDatasync();
                Intent intent=new Intent(Soft1714080902104Activity.this,CXSelect.class);
                startActivity(intent);
            }
        });

        ImageView itemIcon3 = new ImageView(this);
        itemIcon3.setImageResource(R.drawable.riben);
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cid="17";
                getDatasync();
                Intent intent=new Intent(Soft1714080902104Activity.this,CXSelect.class);
                startActivity(intent);
            }
        });

        ImageView itemIcon4 = new ImageView(this);
        itemIcon4.setImageResource(R.drawable.taiguo);
        SubActionButton button4 = itemBuilder.setContentView(itemIcon4).build();
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cid="123";
                getDatasync();
                Intent intent=new Intent(Soft1714080902104Activity.this,CXSelect.class);
                startActivity(intent);
            }
        });

        ImageView itemIcon5 = new ImageView(this);
        itemIcon5.setImageResource(R.drawable.yidali);
        SubActionButton button5 = itemBuilder.setContentView(itemIcon5).build();
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cid="124";
                getDatasync();
                Intent intent=new Intent(Soft1714080902104Activity.this,CXSelect.class);
                startActivity(intent);
            }
        });

        FloatingActionMenu actionMenu=new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)
                .addSubActionView(button5)
                .attachTo(actionButton)
                .build();
    }

    private void ChinaMenu(){



        ImageView icon=new ImageView(this);
        icon.setImageResource(R.drawable.zhongguo);
        FloatingActionButton actionButton=new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .setPosition(FloatingActionButton.POSITION_TOP_LEFT)
                .build();

        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);

        ImageView itemIcon1 = new ImageView(this);
        itemIcon1.setImageResource(R.drawable.yuecai);
        SubActionButton button1 = itemBuilder.setContentView(itemIcon1).build();
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cid="11";
                getDatasync();
                Intent intent=new Intent(Soft1714080902104Activity.this,CXSelect.class);
                startActivity(intent);
            }
        });

        ImageView itemIcon2 = new ImageView(this);
        itemIcon2.setImageResource(R.drawable.chuancai);
        SubActionButton button2 = itemBuilder.setContentView(itemIcon2).build();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cid="10";
                getDatasync();
                Intent intent=new Intent(Soft1714080902104Activity.this,CXSelect.class);
                startActivity(intent);
            }
        });

        ImageView itemIcon3 = new ImageView(this);
        itemIcon3.setImageResource(R.drawable.kejiacai);
        SubActionButton button3 = itemBuilder.setContentView(itemIcon3).build();
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cid="117";
                getDatasync();
                Intent intent=new Intent(Soft1714080902104Activity.this,CXSelect.class);
                startActivity(intent);
            }
        });

        ImageView itemIcon4 = new ImageView(this);
        itemIcon4.setImageResource(R.drawable.sucai);
        SubActionButton button4 = itemBuilder.setContentView(itemIcon4).build();
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cid="104";
                getDatasync();
                Intent intent=new Intent(Soft1714080902104Activity.this,CXSelect.class);
                startActivity(intent);
            }
        });

        ImageView itemIcon5 = new ImageView(this);
        itemIcon5.setImageResource(R.drawable.xiangcai);
        SubActionButton button5 = itemBuilder.setContentView(itemIcon5).build();
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cid="12";
                getDatasync();
                Intent intent=new Intent(Soft1714080902104Activity.this,CXSelect.class);
                startActivity(intent);
            }
        });

        FloatingActionMenu actionMenu=new FloatingActionMenu.Builder(this)
                .addSubActionView(button1)
                .addSubActionView(button2)
                .addSubActionView(button3)
                .addSubActionView(button4)
                .addSubActionView(button5)
                .attachTo(actionButton)
                .setStartAngle(0)
                .setEndAngle(90)
                .build();
    }

    private void ChangeColor(){

        ValueAnimator colorAnim = ObjectAnimator.ofInt(view_cpselect,"backgroundColor", RED, BLUE);
        colorAnim.setDuration(3000);
        colorAnim.setEvaluator(new ArgbEvaluator()); colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();
    }

    public void getDatasync(){

        caiPuList.clear();

        OkHttpUtils
                .post()
                .url("http://apis.juhe.cn/cook/index")
                .addParams("key","ec094bb8c7cd8ad35114c5fa0c81d678")
                .addParams("cid",cid)
                .addParams("rn","30")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getApplicationContext(),"失败",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            JSONObject jsonObject1=jsonObject.getJSONObject("result");
                            JSONArray jsonArray=jsonObject1.getJSONArray("data");

                            for(int i=0;i<30;i++){
                                CaiPu caiPu=new CaiPu();
                                JSONObject jsonObject2=jsonArray.getJSONObject(i);
                                caiPu.setTitle(jsonObject2.getString("title"));
                                caiPu.setImtro(jsonObject2.getString("imtro"));
                                caiPu.setIngredients(jsonObject2.getString("ingredients"));
                                caiPu.setBurden(jsonObject2.getString("burden"));
                                JSONArray jsonArray1=jsonObject2.getJSONArray("albums");
                                caiPu.setAlbum(jsonArray1.getString(0));
                                JSONArray jsonArray2=jsonObject2.getJSONArray("steps");
                                for(int k=0;k<jsonArray2.length();k++){

                                }
                                caiPuList.add(caiPu);
                            }
                            //cp_caiming_text.setText(caiPuList.get(0).getTitle());
                            //cp_cailiao_text.setText(caiPuList.get(0).getBurden());
                            //cp_jianjieneirong_text.setText(caiPuList.get(0).getImtro());
                            //Glide.with(getApplicationContext()).load(caiPuList.get(0).getAlbum()).into(cp_image);
                            //saveTextIntoInternalStorage(jsonArray.toString());
                            ((DataApplication)getApplication()).SetDataList(caiPuList);
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                });
    }
    /*
     * 判断网络连接是否已开
     *true 已打开 false 未打开
     **/
    public static boolean isConn(Context context){
        boolean bisConnFlag=false;
        ConnectivityManager conManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = conManager.getActiveNetworkInfo();
        if(network!=null){
            bisConnFlag=conManager.getActiveNetworkInfo().isAvailable();
        }
        return bisConnFlag;
    }


    /*没有网络跳转到网络设置页面
     * 打开设置网络界面
     * */
    public static void setNetworkMethod(final Context context){
        //提示对话框
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setTitle("网络设置提示").setMessage("网络连接不可用,是否进行设置?").setPositiveButton("设置", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Intent intent=null;
                //判断手机系统的版本 即API大于10 就是3.0或以上版本
                if(Build.VERSION.SDK_INT>10){
                    intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                }else{
                    intent = new Intent();
                    ComponentName component = new ComponentName("com.android.settings","com.android.settings.WirelessSettings");
                    intent.setComponent(component);
                    intent.setAction("android.intent.action.VIEW");
                }
                context.startActivity(intent);
            }
        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.dismiss();
            }
        }).show();
    }
}
