package edu.hzuapps.androidlabs.Soft1714080902215;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.Manifest;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.master.permissionhelper.PermissionHelper;
public class Pic_comment extends AppCompatActivity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.pic_comment);
//        Log.i("Questionnaire","onCreate()");
//    }
    private View btn;
    private View img;
    private PermissionHelper permissionHelper;
    private String filename= "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1555855739568&di=ee5050871b13064d2648064c67651840&imgtype=0&src=http%3A%2F%2Fwww.wallcoo.com%2Fhuman%2FSydney%2520Australia%2Fwallpapers%2F1024x768%2FAustralia_Sydney_photo_picture_Choices.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic_comment);


        bindID();
        permissionHelper = new PermissionHelper(Pic_comment.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        permissionHelper.request(new PermissionHelper.PermissionCallback() {
            @Override
            public void onPermissionGranted() {
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Pic_download task=new Pic_download(Pic_comment.this, btn, img);

                        task.execute(filename, "nice.jpg");

                    }
                });
            }

            @Override
            public void onIndividualPermissionGranted(String[] grantedPermission) {

            }

            @Override
            public void onPermissionDenied() {

            }

            @Override
            public void onPermissionDeniedBySystem() {

            }
        });
    }

    private void bindID() {
        btn = findViewById(R.id.button);
        img = findViewById(R.id.image);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionHelper != null) {
            permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
