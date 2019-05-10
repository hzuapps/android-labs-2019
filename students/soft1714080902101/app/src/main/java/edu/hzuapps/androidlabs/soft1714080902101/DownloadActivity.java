package edu.hzuapps.androidlabs.soft1714080902101;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.master.permissionhelper.PermissionHelper;

public class DownloadActivity extends AppCompatActivity {

    private Button btn;
    private ImageView img;
    private PermissionHelper permissionHelper;
    private String filename = "https://img.zcool.cn/community/01e97e554947190000019ae9580af3.jpg@1280w_1l_2o_100sh.jpg";
    private Button myButton_chat;
    private Button myButton_download;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        myButton_chat = (Button)findViewById(R.id.bt_chat);
        myButton_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DownloadActivity.this,Soft1714080902101Activity.class);
                startActivity(intent);

            }
        });


        bindID();
        permissionHelper = new PermissionHelper(DownloadActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        permissionHelper.request(new PermissionHelper.PermissionCallback() {
            @Override
            public void onPermissionGranted() {
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DownloadTask task=new DownloadTask(DownloadActivity.this, btn, img);

                        task.execute(filename, "5n1.jpg");

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
        btn = findViewById(R.id.btn);
        img = findViewById(R.id.img);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionHelper != null) {
            permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}

