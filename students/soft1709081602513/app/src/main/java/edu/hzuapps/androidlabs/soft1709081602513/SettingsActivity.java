package edu.hzuapps.androidlabs.soft1709081602513;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity{
    private EditText mName;
    private Button mSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mName = findViewById(R.id.setname);
        mSave = findViewById(R.id.config_save);

        SharedPreferences pref=getSharedPreferences("config",MODE_PRIVATE);
        String name=pref.getString("username","");
        mName.setText(name);



        mSave.setOnClickListener(new Button.OnClickListener(){
        // 保存用户名
            @Override
        public void onClick(View v){
                mName=findViewById(R.id.setname);
                String name=mName.getText().toString();
                SharedPreferences.Editor editor=getSharedPreferences("config",MODE_PRIVATE).edit();
                editor.putString("username",name);
                editor.commit();
                Toast.makeText(SettingsActivity.this,"提交成功！",Toast.LENGTH_SHORT).show();

        }
    });
    }


//菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_home:
                Toast.makeText(SettingsActivity.this,"未保存！！",Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.navigation_start:
                mSave.performClick();
                Intent intent2 = new Intent(SettingsActivity.this, StartActivity.class);
                startActivity(intent2);
        }
        return true;
    }
}
