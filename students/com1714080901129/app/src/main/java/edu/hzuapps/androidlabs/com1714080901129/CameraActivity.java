package edu.hzuapps.androidlabs.com1714080901129;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.yzk.ordis.util.getPhotoFromPhotoAlbum;
import java.io.File;
import java.util.List;
import pub.devrel.easypermissions.EasyPermissions;

public class CameraActivity extends AppCompatActivity  implements EasyPermissions.PermissionCallbacks,View.OnClickListener{
    private ImageView imageView;
    private Button bt_set;
    private File cameraSavePath= new File(Environment.getExternalStorageDirectory().getPath() + "/photo.jpg");;//拍照照片路径
    private Uri uri;
    private String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_activty);
        imageView=(ImageView)findViewById(R.id.camera_png);
        bt_set=(Button)findViewById(R.id.bt_set);
        imageView.setOnClickListener( this);
        bt_set.setOnClickListener( this);
    }

    public void onClick(View v){
        int id=v.getId();
        switch(id){
            case R.id.camera_png:
                getPermission();
                goCamera();
                break;
            case R.id.bt_set:
                setBg(cameraSavePath);
                break;
        }
    }
    //获取权限
    private void getPermission() {
        if (EasyPermissions.hasPermissions(this, permissions)) {
            //已经打开权限
            Toast.makeText(this, "已经申请相关权限", Toast.LENGTH_SHORT).show();
        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要获取您的相册、照相使用权限", 1, permissions);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //框架要求必须这么写
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    //成功打开权限
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

        Toast.makeText(this, "相关权限获取成功", Toast.LENGTH_SHORT).show();
    }
    //用户未同意权限
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "请同意相关权限，否则功能无法使用", Toast.LENGTH_SHORT).show();
    }




    private void goCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            uri = FileProvider.getUriForFile(CameraActivity.this,
                    "edu.hzuapps.androidlabs.com1714080901129.fileprovider",cameraSavePath);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            uri = Uri.fromFile(cameraSavePath);
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        CameraActivity.this.startActivityForResult(intent, 1);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String photoPath;
        if (requestCode == 1 && resultCode == RESULT_OK) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                photoPath = String.valueOf(cameraSavePath);
            } else {
                photoPath = uri.getEncodedPath();
            }
            Log.d("拍照返回图片路径:", photoPath);
            Glide.with(CameraActivity.this).load(photoPath).into(imageView);
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            photoPath = getPhotoFromPhotoAlbum.getRealPathFromUri(this, data.getData());
            Glide.with(CameraActivity.this).load(photoPath).into(imageView);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    public void setBg(File PhotoFile) {
        String path = String.valueOf(PhotoFile);
//        Bitmap bitmap = BitmapFactory.decodeFile(path);
        Intent intent = new Intent(this, Com1714080901129Activity.class);
        intent.putExtra("pic", path);
        startActivity(intent);
    }
}
