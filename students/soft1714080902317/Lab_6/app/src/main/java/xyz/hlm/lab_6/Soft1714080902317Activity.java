package xyz.hlm.lab_6;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class Soft1714080902317Activity extends AppCompatActivity implements View.OnClickListener {

    ImageView image = null;
    Button submit = null;
    EditText input = null;
    TextView textView = null;
    GetPicture getPicture;
    boolean allow = false;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    if (ContextCompat.checkSelfPermission(getApplication(),Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
                    {
                        allow = true;
                        getPicture = new GetPicture(handler);
                    }
                    break;
                case 1:
                    image.setImageBitmap(GetPicture.getImageBitmap());
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft1714080902317_activity);
        (submit = (Button) findViewById(R.id.login)).setOnClickListener(this);
        image = (ImageView) findViewById(R.id.image);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                check();
                while (!allow){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Message msg = new Message();
                    msg.what = 0;
                    handler.sendMessage(msg);
                }
                while (!(new File("sdcard/lab/lab6.png")).exists())
                    getPicture.download("https://raw.githubusercontent.com/hlm520/android-labs-2019/master/students/soft1714080902317/2017520112942506.png?raw=true");
            }
        }).start();
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED)
        {
            Message msg = new Message();
            msg.what = 1;
            handler.sendMessage(msg);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    public void onClick(View view) {
        input = (EditText)findViewById(R.id.input);
        String in = input.getText().toString().toLowerCase();
        textView = (TextView)findViewById(R.id.success);
        if (in.equals("qnmx")) {
            Toast.makeText(this,"验证成功!",Toast.LENGTH_LONG).show();
            gone();
            textView.setVisibility(View.VISIBLE);
        }
        else {
            Toast.makeText(this,"验证失败!",Toast.LENGTH_LONG).show();
            input.setText("");
        }
    }
    private void gone(){
        image.setVisibility(View.GONE);
        input.setVisibility(View.GONE);
        submit.setVisibility(View.GONE);
    }
    /**动态权限授权*/
    private void check() {
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE))
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,}, 1);
        }
    }
}
