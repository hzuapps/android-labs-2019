package edu.hzuapps.androidlabs;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Soft1714080902209_Reg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft1714080902209_reg);
        Reg_RegButton=(Button)findViewById(R.id.Main_Reg);
        Reg_RegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=((EditText)findViewById(R.id.UserName_TextEdit)).getText().toString();
                String password=((EditText)findViewById(R.id.Passcode_TextEdit)).getText().toString();
                if(username.equals("") && password.equals("")){
                    Toast.makeText(Soft1714080902209_Reg.this,"请输入账号密码",Toast.LENGTH_SHORT).show();
                }else {
                    dbhelper = new Soft1714080902209_UPSQL(Soft1714080902209_Reg.this);
                    db = dbhelper.getReadableDatabase();
                    db.execSQL("insert into username (name,password) values(?,?)",new String[]{username,password});
                    Toast.makeText(Soft1714080902209_Reg.this,"注册成功",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private Button Reg_RegButton;
    private Soft1714080902209_UPSQL dbhelper;
    private SQLiteDatabase db;
}
