package com.example.administrator.pic;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.PictureCallback;

import com.youth.banner.loader.ImageLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class new_activity extends AppCompatActivity {

    public static final int TAKE_PHOTO = 1;
    private Uri imageUri;
    private ImageView picture;
    String file_path="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity2);
        Button get_pic=(Button) findViewById(R.id.get_pic);
        get_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File outputImage = new File(Environment.getExternalStorageDirectory(), "/DCIM/Camera/"+"test" + ".jpg");
                if (!outputImage.getParentFile().exists()){
                    outputImage.getParentFile().mkdirs();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUri = FileProvider.getUriForFile(new_activity.this, "com.example.administrator.pic.fileprovider", outputImage);
                } else {
                    Toast.makeText(new_activity.this,"version is to low", Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent();
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);

            }
        });
        initView();
        json_anal();
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        switch (requestCode){
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK){
                    try {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }

    private void json_anal(){
        Gson gson=new Gson();
        //String csdnjson="{\"cdsn_id\":\"super_machine_gun\",\"http\":\"https://blog.csdn.net/weixin_41544329\",\"emotion\":\"（＝。＝）\"}";
        String csdnjson="    {\n" +
                "        \"csdn_id\": \"super_machine_gun\",\n" +
                "        \"http\": \"https://blog.csdn.net/weixin_41544329\",\n" +
                "        \"emotion\": \"（＝。＝）\"\n" +
                "    }";
        final javabean_web bean=gson.fromJson(csdnjson,javabean_web.class);
        Button csdn=(Button) findViewById(R.id.Skip_to_csdn);
        csdn.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        //"正在访问"+bean.getCsdn_id()+"的博客"+"目标网址："+bean.getHttp()+bean.getEmotion()
                        Toast.makeText(new_activity.this,"正在访问"+bean.getCsdn_id()+"的博客  "+"  目标网址："+bean.getHttp()+"   "+bean.getEmotion(), Toast.LENGTH_LONG).show();
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://blog.csdn.net/weixin_41544329")));
                    }
                }
        );
    }



    private void initView() {
        Banner banner;
        banner = (Banner) findViewById(R.id.banner1);
        final ArrayList<String> list_path;
        final ArrayList<String> list_title;
        list_path = new ArrayList<>();
        list_title = new ArrayList<>();
        list_path.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555432842011&di=2c632c93ae289336571c1d3366665779&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Ff48dde221d1c755653696f4f8fe43b7063faf5dc306e97-CazFAe_fw236");
        list_path.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555432839435&di=1e76cc7271baf30796a97b9840321c37&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201703%2F23%2F20170323174003_PY8cs.thumb.224_0.jpeg");
        list_path.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555432836624&di=296cb48ffd6b8202af6c88c86f55f57b&imgtype=0&src=http%3A%2F%2Fhbimg.b0.upaiyun.com%2Fef833549e93f58ff30866eff432d26ba5338a6f72d335d-G2Iuai_fw236");
        list_path.add("file://"+Environment.getExternalStorageDirectory()+"/DCIM/Camera/"+"test" + ".jpg");
        list_title.add("一");
        list_title.add("二");
        list_title.add("三");
        list_title.add("四");

        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.setImageLoader(new MyLoader());
        banner.setBannerAnimation(Transformer.Default);
        banner.setBannerTitles(list_title);
        banner.setDelayTime(3000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(list_path)
                .start();
    }

}
