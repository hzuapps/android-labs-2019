package androidlabs.hzuapps.ezu.soft1707080714328;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Soft1707080714328Activity_fankui extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "Soft1707080714328Activity_fankui";
    private EditText fankui;
    private ImageButton fanhui;
    private Button wancheng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1707080714328_activity_fankui);
        intView();
        intUI();
    }
    //找到控件
    private void intView() {
        fankui = (EditText) findViewById(R.id.et_fankui);
        fanhui = (ImageButton) findViewById(R.id.ibtn_fanhui);
        wancheng = (Button) findViewById(R.id.btn_wancheng);
    }

    private void intUI() {
     //设置监听者对象
        fanhui.setOnClickListener(this);
        wancheng.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_fanhui:
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),Soft1707080714328Activity_First.class);
                startActivity(intent);
                break;
            case R.id.btn_wancheng:
                HandlerWanchengEvent(v);
        }
    }

    private void HandlerWanchengEvent(View v) {
        //获取反馈内容
        String fankuiText = fankui.getText().toString();

        //把反馈内容保存起来
        saveFankuiInfo(fankuiText);

        //成功保存反馈内容跳转回主页面
        fanHui();
    }

    private void fanHui() {
        Toast.makeText(getApplicationContext(),"感谢您的反馈,我们会尽快处理您的意见.",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(getApplicationContext(),Soft1707080714328Activity_First.class);
        startActivity(intent);
    }

    private void saveFankuiInfo(String fankuiText) {
        //查看日志,看是否能成功保存内容
        Log.d(TAG,"保存反馈内容...");

        //获取内部存储路径
        File dir = this.getFilesDir();
        File file = new File(dir,"fankuiText");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(fankuiText.getBytes());
            fos.close();
        } catch (Exception e) {

        }
    }
}
