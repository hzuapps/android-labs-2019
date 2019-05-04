package edu.hzuapps.androidlabs.Soft1714080902238;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.master.permissionhelper.PermissionHelper;

import java.util.UUID;

import lun.com.myapplication.R;

public class DownPicActivity extends AppCompatActivity {

    private View btn;
    private ImageView img;
    private PermissionHelper permissionHelper;
    private String filename = "http://moondevil.top/tv/img/anzhuo/rou";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_pic);
        //getResources().getDrawable((R.drawable.rou1)
        img=(ImageView) findViewById(R.id.image);
        switch (MsgQueue.i)
        {
            case 1:
                img.setImageResource(R.drawable.rou1);
                break;
            case 2:
                img.setImageResource(R.drawable.rou4);
                break;
            case 3:
                img.setImageResource(R.drawable.rou2);
                break;
            case 4:
                img.setImageResource(R.drawable.rou3);
                break;
        }

        bindID();
        permissionHelper = new PermissionHelper(DownPicActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        permissionHelper.request(new PermissionHelper.PermissionCallback() {
            @Override
            public void onPermissionGranted() {

                filename+=(MsgQueue.i+".jpg");
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DownLoad task=new DownLoad(DownPicActivity.this, btn);

                        UUID uuid  =  UUID.randomUUID();
                        String s = UUID.randomUUID().toString();
                        s+="jpg";
                        task.execute(filename, s);

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
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionHelper != null) {
            permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
