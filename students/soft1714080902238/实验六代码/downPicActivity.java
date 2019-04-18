package edu.hzuapps.androidlabs.Soft1714080902238;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.master.permissionhelper.PermissionHelper;

import lun.com.myapplication.R;

public class downPicActivity extends AppCompatActivity {

    private View btn;
    private View img;
    private PermissionHelper permissionHelper;
    private String filename = "http://upload2.95171.cn/pic/AESH10008430/67698.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_pic);

        bindID();
        permissionHelper = new PermissionHelper(downPicActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        permissionHelper.request(new PermissionHelper.PermissionCallback() {
            @Override
            public void onPermissionGranted() {
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DownLoad task=new DownLoad(downPicActivity.this, btn, img);

                        task.execute(filename, "5222.jpg");

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
