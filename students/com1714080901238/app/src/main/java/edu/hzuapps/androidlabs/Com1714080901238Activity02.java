package edu.hzuapps.androidlabs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.FileInputStream;
import java.io.FileOutputStream;






public class Com1714080901238Activity02 extends Activity {
    private Button btn_save;
    private Button btn_read;
    private Button button_tz;
    private EditText et_info;
        @Override
    protected void onCreate( Bundle savedInstanceState) {
        setContentView(R.layout.com_1714080901238_activity02);
        super.onCreate(savedInstanceState);
            btn_save=(Button) findViewById(R.id.btn_save);
            btn_read=(Button) findViewById(R.id.btn_read);
            button_tz=(Button) findViewById(R.id.button_tz) ;
            et_info=(EditText) findViewById(R.id.et_info1);
    }
    public void money(View view){
        switch(view.getId()){
            case R.id.btn_save:
                String saveinfo=et_info.getText().toString().trim();
                FileOutputStream fos;
                try{
                    //保存数据
                    fos=openFileOutput("money.txt",Context.MODE_APPEND);
                    fos.write(saveinfo.getBytes());
                    fos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(Com1714080901238Activity02.this,"数据保存成功",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_read:
                String content="";
                try{
                    //读取保存数据
                    FileInputStream fis=openFileInput("money.txt");
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    content=new String(buffer);
                    fis.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(Com1714080901238Activity02.this,"保存的数据是:"+content,Toast.LENGTH_SHORT).show();
                break;
                case R.id.button_tz:
                        Intent intent = new Intent(Com1714080901238Activity02.this,Com1714080901238Activity03.class);
                        startActivity(intent);

            default: break;
        }
    }
}

