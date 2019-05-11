package androidlabs.hzuapps.ezu.soft1707080714328;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.zxing.activity.CaptureActivity;
import com.google.zxing.util.Constant;


public class Soft1707080714328Activity_First extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1707080714328_activity__first);

        intUI();
    }

    private void intUI() {
        findViewById(R.id.btn_saomazhifu).setOnClickListener(this);
        findViewById(R.id.btn_fankui).setOnClickListener(this);
        findViewById(R.id.wenti).setOnClickListener(this);
        findViewById(R.id.gushi).setOnClickListener(this);
        findViewById(R.id.xiaoxi).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_saomazhifu:
                startQrCode();
                break;
            case R.id.btn_fankui:
                Intent intent4 = new Intent();
                intent4.setClass(getApplicationContext(),Soft1707080714328Activity_FanKui.class);
                startActivity(intent4);
                break;
            case R.id.wenti:
                Intent intent1 = new Intent();
                intent1.setClass(getApplicationContext(),Soft1707080714328Activity_WenTi.class);
                startActivity(intent1);
                break;
            case R.id.gushi:
                Intent intent2 = new Intent();
                intent2.setClass(getApplicationContext(), Soft1707080714328Activity_GuShi.class);
                startActivity(intent2);
                break;
            case R.id.xiaoxi:
                Intent intent3 = new Intent();
                intent3.setClass(getApplicationContext(),Soft1707080714328Activity_XiaoXi.class);
                startActivity(intent3);
                break;
        }
    }

    private void startQrCode() {
        // 申请相机权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .CAMERA)) {
                Toast.makeText(this, "请至权限中心打开本应用的相机访问权限", Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(Soft1707080714328Activity_First.this, new String[]{Manifest.permission.CAMERA}, Constant.REQ_PERM_CAMERA);
            return;
        }
        // 申请文件读写权限
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // 申请权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission
                    .WRITE_EXTERNAL_STORAGE)) {
                Toast.makeText(this, "请至权限中心打开本应用的文件读写权限", Toast.LENGTH_SHORT).show();
            }
            ActivityCompat.requestPermissions(Soft1707080714328Activity_First.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constant.REQ_PERM_EXTERNAL_STORAGE);
            return;
        }
        // 二维码扫码
        Intent intent = new Intent(Soft1707080714328Activity_First.this, CaptureActivity.class);
        startActivityForResult(intent, Constant.REQ_QR_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case Constant.REQ_PERM_CAMERA:
                // 摄像头权限申请
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获得授权
                    startQrCode();
                } else {
                    // 被禁止授权
                    Toast.makeText(Soft1707080714328Activity_First.this, "请至权限中心打开本应用的相机访问权限", Toast.LENGTH_LONG).show();
                }
                break;
            case Constant.REQ_PERM_EXTERNAL_STORAGE:
                // 文件读写权限申请
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 获得授权
                    startQrCode();
                } else {
                    // 被禁止授权
                    Toast.makeText(Soft1707080714328Activity_First.this, "请至权限中心打开本应用的文件读写权限", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

}
