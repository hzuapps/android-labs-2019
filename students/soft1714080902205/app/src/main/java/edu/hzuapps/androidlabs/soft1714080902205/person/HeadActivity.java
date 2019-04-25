package edu.hzuapps.androidlabs.soft1714080902205.person;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import edu.hzuapps.androidlabs.soft1714080902205.R;
import edu.hzuapps.androidlabs.soft1714080902205.Soft1714080902205Activity;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class HeadActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    private Context mContext;
    private AlertDialog profilePictureDialog;
    private static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;
    private static final String PERMISSION_WRITE = Manifest.permission.WRITE_EXTERNAL_STORAGE;
    private static final int REQUEST_PERMISSION_CAMERA = 0x001;
    private static final int REQUEST_PERMISSION_WRITE = 0x002;
    private static final int CROP_REQUEST_CODE = 0x003;

    private ImageView ivAvatar;

    /**
     * 文件相关
     */
    private File captureFile;
    private File rootFile;
    private File cropFile;

    private String path;
    private String filepath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.head_activity);
        super.onCreate(savedInstanceState);
        ivAvatar = findViewById(R.id.iv_avatar);

        //加载Activity时读取sdcard的图片
        path=Environment.getExternalStorageDirectory().getAbsolutePath() + "/TakePhotoPic";
        filepath=path+"/avatar.jpg";
        File file=new File(filepath);
        if(file.exists()){
            Bitmap bm=BitmapFactory.decodeFile(filepath);
            ivAvatar.setImageBitmap(bm);
        }else{
            Toast.makeText(HeadActivity.this,"未成功读取图片",Toast.LENGTH_SHORT).show();
        }


        mContext = this;



        rootFile = new File(MyConstant.PIC_PATH);
        if (!rootFile.exists()) {
            rootFile.mkdirs();
        }

        Button btn_return=(Button)findViewById(R.id.btn_return);
        btn_return.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                Intent intent = new Intent(HeadActivity.this, Soft1714080902205Activity.class);
                startActivity(intent);
            }
        });
    }

    public void btnClick(View view) {

        if (profilePictureDialog == null) {
            @SuppressLint("InflateParams") View rootView = LayoutInflater.from(this).inflate(R.layout.item_profile_picture, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            rootView.findViewById(R.id.tv_take_photo).setOnClickListener(onTakePhotoListener);
            rootView.findViewById(R.id.tv_choose_photo).setOnClickListener(onChoosePhotoListener);
            builder.setView(rootView);
            profilePictureDialog = builder.create();
            profilePictureDialog.show();
        } else {
            profilePictureDialog.show();
        }
    }

    private void dismissProfilePictureDialog() {
        if (profilePictureDialog != null && profilePictureDialog.isShowing()) {
            profilePictureDialog.dismiss();
        }
    }

    private View.OnClickListener onTakePhotoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismissProfilePictureDialog();
            if (EasyPermissions.hasPermissions(mContext, PERMISSION_CAMERA, PERMISSION_WRITE)) {
                takePhoto();
            } else {
                EasyPermissions.requestPermissions(HeadActivity.this, "need camera permission", REQUEST_PERMISSION_CAMERA, PERMISSION_CAMERA, PERMISSION_WRITE);
            }
        }
    };

    //拍照
    private void takePhoto() {
        //用于保存调用相机拍照后所生成的文件
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        captureFile = new File(rootFile, "temp.jpg");
        //跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //判断版本 如果在Android7.0以上,使用FileProvider获取Uri
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(HeadActivity.this, "edu.hzuapps.androidlabs.soft1714080902205.person.fileprovider",captureFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
        } else {
            //否则使用Uri.fromFile(file)方法获取Uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(captureFile));
        }
        startActivityForResult(intent, REQUEST_PERMISSION_CAMERA);
    }

    private View.OnClickListener onChoosePhotoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dismissProfilePictureDialog();
            if (EasyPermissions.hasPermissions(mContext, PERMISSION_WRITE)) {
                    choosePhoto();
            } else {
                EasyPermissions.requestPermissions(HeadActivity.this, "need camera permission", REQUEST_PERMISSION_WRITE, PERMISSION_WRITE);
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    //从相册选择
    private void choosePhoto() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_PERMISSION_WRITE);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (requestCode == REQUEST_PERMISSION_CAMERA) {
            takePhoto();
        } else if (requestCode == REQUEST_PERMISSION_WRITE) {
            choosePhoto();
        }
    }

    /**
     * 裁剪图片
     */
    private void cropPhoto(Uri uri) {
        cropFile = new File(rootFile, "avatar.jpg");
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cropFile));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra("noFaceDetection", true);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(intent, CROP_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_PERMISSION_CAMERA:
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri contentUri = FileProvider.getUriForFile(HeadActivity.this, "edu.hzuapps.androidlabs.soft1714080902205.person.fileprovider",captureFile);
                        cropPhoto(contentUri);
                    } else {
                        cropPhoto(Uri.fromFile(captureFile));
                    }
                    break;
                case REQUEST_PERMISSION_WRITE:
                    cropPhoto(data.getData());
                    break;
                case CROP_REQUEST_CODE:
                    saveImage(cropFile.getAbsolutePath());
                    ivAvatar.setImageBitmap(BitmapFactory.decodeFile(cropFile.getAbsolutePath()));
                    break;
                default:
                    break;
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * @param path
     * @return
     */
    public String saveImage(String path) {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return null;
        }
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        try {
            FileOutputStream fos = new FileOutputStream(cropFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            return cropFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(this).build().show();
        }
    }
}