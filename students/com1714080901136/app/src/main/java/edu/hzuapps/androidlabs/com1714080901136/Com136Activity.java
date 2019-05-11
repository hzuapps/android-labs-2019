package edu.hzuapps.androidlabs.com1714080901136;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Com136Activity extends AppCompatActivity {
    public final int TAKE_PHOTO = 1;
    public final int CHOOSE_PHOTO = 2;
    public final int CROP_REQUEST_CODE = 3;
    public ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com136);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        iv = findViewById(R.id.iv);
        Intent intent=getIntent();
        TextView tv=findViewById(R.id.tv);
        tv.setText(intent.getStringExtra("data"));
    }
    public void tianjia(View v) {
        Intent intent1 = new Intent(Com136Activity.this,Com136Activity2.class);
        startActivity(intent1);
    }
    public void fanhui(View v) {
        Intent intent = new Intent(Com136Activity.this, Com1714080901136Activity.class);
        startActivity(intent);
    }public void chose(View v) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, CHOOSE_PHOTO);
    }
    public void photograph(View v) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, TAKE_PHOTO);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (data != null) {
                    Uri mImageCaptureUri = data.getData();
                    if (mImageCaptureUri != null) {
                        Bitmap image;
                        try {
                            image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mImageCaptureUri);
                            if (image != null) {
                                iv.setImageBitmap(image);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Bundle extras = data.getExtras();
                        if (extras != null) {
                            Bitmap image = extras.getParcelable("data");
                            if (image != null) {
                                iv.setImageBitmap(image);
                            }
                        }
                    }

                }
                break;
            case CROP_REQUEST_CODE:
                if (data != null) {
                    Uri mImageCaptureUri = data.getData();
                    if (mImageCaptureUri != null) {
                        Bitmap image;
                        try {
                            image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), mImageCaptureUri);
                            if (image != null) {
                                iv.setImageBitmap(image);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Bundle extras = data.getExtras();
                        if (extras != null) {
                            Bitmap image = extras.getParcelable("data");
                            if (image != null) {
                                iv.setImageBitmap(image);
                            } } } }
                break;
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    cropPhoto(uri);//裁剪
                }
                break;
        }}
    private void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 3);
        intent.putExtra("aspectY", 2);
        intent.putExtra("outputX", 400);//剪裁后X的像素
        intent.putExtra("outputY", 200);//剪裁后Y的像素
        intent.putExtra("outputFormat", "JPEG");// 返回格式
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, CROP_REQUEST_CODE);
    }



}
