package edu.hzuapps.androidlabs.soft1714080902133;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import edu.hzuapps.androidlabs.soft1714080902133.javabean.Soft1714080902133User;

//惠大宿舍缴电费app
//个人中心，用户信息
public class Soft1714080902133UserActivity extends AppCompatActivity {

    //圆图片
    private CircleImageView circleImageView;
    //路径
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902133_useractivity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.users_toolbar);
        setSupportActionBar(toolbar);//获得ToolBar实例
        ActionBar actionBar = getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);//菜单，默认图片返回图片
        }


        //点击头像进入手机图库,或者拍照，确认图片后显示
        circleImageView = findViewById(R.id.users_icon_image);
        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //从图库选图的intent
                Intent i1 = new Intent(Intent.ACTION_PICK);
                i1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");

                //从相机拍照的intent
                File outputImage = new File(getExternalCacheDir(),"output_image");//存放图片路径
                try{
                    if(outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT>=24){
                    uri = FileProvider.getUriForFile(Soft1714080902133UserActivity.this,"soft1714080902133.cameraalbumtest,fileprovider",outputImage);
                }else{
                    uri = Uri.fromFile(outputImage);
                }
                Intent i2 = new Intent("android.media.action.IMAGE_CAPTURE");//行动
                i2.putExtra(MediaStore.EXTRA_OUTPUT,uri);

                //把俩个intent“凑成”一个intent发送
                Intent intent = Intent.createChooser(i1, "选择头像");
                intent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{i2});
                //把凑成的intent发送出去
                startActivityForResult(intent, 100);
            }
        });
    }

    //返回图片
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && resultCode==RESULT_OK){
            if(data==null){
                try{
                    //拍照返回的
                    Bitmap photo = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                    circleImageView.setImageBitmap(photo);
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }else{
                //动态获取SD内的文件，获取其读写能力，不然会被权限限制而无法获取到图片
                if (ContextCompat.checkSelfPermission(Soft1714080902133UserActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!=
                        PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(Soft1714080902133UserActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                }else{
                    //图库选择返回的
                    Uri uri = data.getData();
                    //寻找SDK图片的巨j绝对路径
                    String imagePath  = null;//存放图片路径
                    if(DocumentsContract.isDocumentUri(this,uri)){
                        //如果是document类型的uri,则通过document id 处理
                        String docID = DocumentsContract.getDocumentId(uri);
                        if("com.android.providers.media.documents".equals(uri.getAuthority())){
                            String id = docID.split(":")[1];//解析吃数字格式的id
                            String selection = MediaStore.Images.Media._ID+"="+id;
                            imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
                        }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                            Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docID));
                            imagePath = getImagePath(contentUri,null);
                        }
                    }else if("content".equalsIgnoreCase(uri.getScheme())){
                        //如果是content类型的Uri,则使用普通的方式处理
                        imagePath = getImagePath(uri,null);
                    }else if("file".equalsIgnoreCase(uri.getScheme())){
                        //如果是file类型的Uri,直接获取图片路径即可
                        imagePath = uri.getPath();
                    }
                    Bitmap photo = BitmapFactory.decodeFile(imagePath);
                    circleImageView.setImageBitmap(photo);
                }
            }
        }
    }

    private String getImagePath(Uri uri,String selection){
        String path = null;
        //通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if (cursor!=null){
            if (cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    //跳转登录界面
    public void go_login(View view) {
        Intent intent = new Intent(Soft1714080902133UserActivity.this, Soft1714080902133LoginActivity.class);
        startActivity(intent);
        finish();
    }

    //完善信息
    public void user_account_management(View view){
        Intent intent = new Intent(Soft1714080902133UserActivity.this, Soft1714080902133PerfectInformationActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
                case android.R.id.home://点击了返回，结束该活动，返回上一个活动
                Intent intent = new Intent(Soft1714080902133UserActivity.this, Soft1714080902133HomeActivity.class);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
