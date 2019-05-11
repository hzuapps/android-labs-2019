package edu.hzuapps.androidlabs.soft1714080902124;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myfirstapp.R;

public class AlertPassword extends AppCompatActivity {

    private EditText oldPass;
    private EditText newPass;
    private EditText newPassCheck;
    private Button editpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_password);
        oldPass = (EditText) findViewById(R.id.old_password);
        newPass = (EditText) findViewById(R.id.new_password);
        newPassCheck = (EditText) findViewById(R.id.check_new_password);
        editpassword = (Button) findViewById(R.id.edit_password);

        editpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldp = oldPass.getText().toString();
                String newp = newPass.getText().toString();
                String newcheckp = newPassCheck.getText().toString();
                SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
                Log.d("oldMd5",oldp);
                Log.d("getpass",sp.getString("pass",""));
              if (Md5.md5(oldp).equals(sp.getString("pass",""))){
                  if (newp.equals(newcheckp)){
                      String newpMd5 = Md5.md5(newp);
                      //拿到编辑器
                      SharedPreferences.Editor ed = sp.edit();
                      //写数据
                      ed.putString ("pass", newpMd5);
                      //提交
                      ed.commit();
                      String messagescess = "密码修改成功";
                      Toast.makeText(AlertPassword.this,messagescess,Toast.LENGTH_SHORT).show();
                  }else{
                      String messageerror = "密码不一致";
                      Toast.makeText(AlertPassword.this,messageerror,Toast.LENGTH_SHORT).show();
                  }
              }else{
                  Toast.makeText(AlertPassword.this,"旧密码错误",Toast.LENGTH_SHORT).show();
              }
            }
        });
    }
}
