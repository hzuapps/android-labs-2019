package edu.hzuapps.androidlabs.com1714080901133;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.widget.ImageView;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.widget.*;
import java.io.File;

public class Com1714080901133Activity extends Activity implements OnClickListener {
    TextView textview;
    private EditText info;
    private EditText info2;
    private Button ib1;
    private Button ib2;
    private Button save_info;
    private Button read_info;
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private Button btn_pick_photo;   //开启相册
    private String picPath;   //图片路径
    /* 头像名称 */
    private static final String PHOTO_FILE_NAME = "temp_photo.jpg";
    private File tempFile;
    private ImageView forDisplay;
    int[] image = {R.drawable.img_1,R.drawable.img_2,R.drawable.img_3,R.drawable.img_4};
    int counts = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_1714080901133_activity);
//跳转

        textview=findViewById(R.id.network);
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Com1714080901133Activity.this, "跳转中，请稍后", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Com1714080901133Activity.this,Com1714080901133Activity2.class);
                startActivity(i);
            }
        });


        //

//获取日期
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
//







//图片浏览
        ib1 = (Button) findViewById(R.id.next);
        ib2 = (Button) findViewById(R.id.previous);
        forDisplay = (ImageView) findViewById(R.id.img_display);

        forDisplay.setImageResource(image[0]);

        ib1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //因为数组中有4张图片，第一张已默认。
                if(counts >= 3){
                    counts = -1;//
                }
                /*counts初始值为0，所以不执行if判断，直接执行下面的语句，而数据的第一张图片已默认，
                 * 所以++counts的值为1，依次当点击第三次时，counts值为2，而++counts为3，再次点击时就执行if判断，将-1
                 * 赋值给counts，再执行下面的语句，++counts为0，重新开始*/
                forDisplay.setImageResource(image[++counts]);
            }
        });

        ib2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //因为数组中有4张图片，第一张已默认。
                if(counts <= 0){
                    counts = 4;//
                }
                /*counts初始值为0，所以不执行if判断，直接执行下面的语句，而数据的第一张图片已默认，
                 * 所以++counts的值为1，依次当点击第三次时，counts值为2，而++counts为3，再次点击时就执行if判断，将-1
                 * 赋值给counts，再执行下面的语句，++counts为0，重新开始*/
                forDisplay.setImageResource(image[--counts]);
            }
        });

//



//打卡

        info=(EditText) findViewById(R.id.info);
        info2=(EditText) findViewById(R.id.info2);
        save_info=(Button) findViewById(R.id.save_info);
        read_info=(Button) findViewById(R.id.read_info);

        save_info.setOnClickListener(new ButtonListener());
        read_info.setOnClickListener(new ButtonListener());



    }
    private class ButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.save_info:
                    String saveinfo=info.getText().toString().trim();
                    String saveinfo2=info2.getText().toString().trim();

                    //String str2=et_info2.getText().toString().trim();
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat   formatter   =   new SimpleDateFormat("yyyy年MM月dd日");
                    Date curDate =   new Date(System.currentTimeMillis());
                    //获取当前时间
                    String   str   =   formatter.format(curDate);

                    String sava_info="打卡时间：" + str + "\n运动内容："+saveinfo+"\n"+"运动地点："+saveinfo2+"\n";

                    FileOutputStream fos;
                    try{
                        fos=openFileOutput("data.txt", Context.MODE_APPEND);
                        fos.write(sava_info.getBytes());
                        fos.close();
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(Com1714080901133Activity.this, "保存成功", 1).show();
                    break;



                case R.id.read_info:
                    String content = "";
                    try {
                        FileInputStream fis = openFileInput("data.txt");
                        byte[] buffer = new byte[fis.available()];
                        fis.read(buffer);
                        content = new String(buffer);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Intent intent=new Intent(Com1714080901133Activity.this,Com1714080901133Activity3.class);
                    intent.putExtra("data",content);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    }

    public void read(View view) {

    }

    @Override
    public void onClick(View v) {

    }



//





//相机


    public void camera(View view){
        //激活相机
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        //判断存储卡是否可用，可用进行存储
        if(hasSdcard()){
            tempFile = new File (Environment.getExternalStorageDirectory (),PHOTO_FILE_NAME);
            //从文件创建uri
            Uri uri = Uri.fromFile (tempFile);
            intent.putExtra (MediaStore.EXTRA_OUTPUT,uri);
        }
        startActivityForResult (intent,PHOTO_REQUEST_CAREMA);
    }

    private boolean hasSdcard(){
        if(Environment.getExternalStorageState ().equals (Environment.MEDIA_MOUNTED)){
            return true;

        }else{
            return false;
        }
    }}


    //