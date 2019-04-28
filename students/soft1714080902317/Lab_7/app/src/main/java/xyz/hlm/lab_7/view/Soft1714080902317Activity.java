package xyz.hlm.lab_7.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import xyz.hlm.lab_7.R;
import xyz.hlm.lab_7.contraller.MyRecycleView;
import xyz.hlm.lab_7.model.MyBitmap;

public class Soft1714080902317Activity extends AppCompatActivity implements View.OnClickListener{

    private MyBitmap myBitmap;
    private MyRecycleView recycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft1714080902317_activity);
        ((Button)findViewById(R.id.take)).setOnClickListener(this);
        check();
        myBitmap = MyBitmap.instance(this);
        recycleView = new MyRecycleView(this);
        recycleView.setting((RecyclerView)findViewById(R.id.recycle));
    }

    @Override
    public void onClick(View view) {
        myBitmap.take();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            recycleView = new MyRecycleView(this);
            recycleView.setting((RecyclerView)findViewById(R.id.recycle));
        }
    }

    /**动态权限授权*/
    private void check() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE))
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,}, 1);
        }
    }
}
