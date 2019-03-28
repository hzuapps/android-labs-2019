package edu.hzuapps.androidlabs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Soft1714080902209_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft1714080902209_main);


        Reg_button=(Button)findViewById(R.id.Main_Reg);
        Reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Soft1714080902209_Main.this, Soft1714080902209_Reg.class);
                startActivity(intent);
            }
        });

        Login_button=(Button)findViewById(R.id.Main_Login);
        Login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=((EditText)findViewById(R.id.UserName_TextEdit)).getText().toString();
                String password=((EditText)findViewById(R.id.Passcode_TextEdit)).getText().toString();
                if(username.equals("Admin") && password.equals("test")){
                    Toast.makeText(Soft1714080902209_Main.this,"登陆成功",Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent();
                        intent.setClass(Soft1714080902209_Main.this, Soft1714080902209_Login.class);
                        startActivity(intent);

                }else if(username.equals("") && password.equals("")){
                    Toast.makeText(Soft1714080902209_Main.this,"请输入账号密码",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Soft1714080902209_Main.this,"账号密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private Button Reg_button;
    private Button Login_button;
}
