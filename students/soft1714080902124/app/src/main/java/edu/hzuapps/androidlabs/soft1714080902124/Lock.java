package edu.hzuapps.androidlabs.soft1714080902124;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.Toast;

import com.example.myfirstapp.R;

public class Lock extends AppCompatActivity {

    private LockNum lockNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lock);
        lockNum = (LockNum) findViewById(R.id.num_lock);
        lockNum.setInputListener(new LockNum.InputListener() {
            @Override
            public void inputFinish(String result) {
                Toast.makeText(Lock.this,result,Toast.LENGTH_SHORT).show();
                SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
                int count = sp.getInt("count",0);
                SharedPreferences.Editor editor = sp.edit();
                if (count == 0){
                    editor.putString("pass",Md5.md5(result));
                    editor.putInt("count",++count);
                    editor.commit();
                    Intent intent=new Intent(Lock.this, Soft1714080902124Activity.class);
                    startActivity(intent);
                }else{
                    if (Md5.md5(result).equals(sp.getString("pass",""))){
                        Toast.makeText(Lock.this,"success",Toast.LENGTH_SHORT).show();

                        Intent intent =  new Intent(Lock.this, Soft1714080902124Activity.class);
                        startActivity(intent);
                    }
                }

                lockNum.showErrorStatus();
            }
        });
    }
}
