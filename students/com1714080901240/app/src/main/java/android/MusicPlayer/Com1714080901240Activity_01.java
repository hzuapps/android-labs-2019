package android.MusicPlayer;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Com1714080901240Activity_01 extends AppCompatActivity {

    private Button btn_save;
    private Button btn_read;
    private EditText et_info;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com1714080901240_01);
        btn_save=(Button) findViewById(R.id.btn_save);
        btn_read=(Button) findViewById(R.id.btn_read);
        et_info=(EditText) findViewById(R.id.et_info);
    }
    public void findSong(View view){
        switch(view.getId()){
            case R.id.btn_save:
                String saveinfo=et_info.getText().toString().trim();
                FileOutputStream fos;
                try{
                    //保存数据
                    fos=openFileOutput("music.txt",Context.MODE_APPEND);
                    fos.write(saveinfo.getBytes());
                    fos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(Com1714080901240Activity_01.this,"数据保存成功",0).show();
                break;
            case R.id.btn_read:
                String content="";
                try{
                    //读取保存数据
                    FileInputStream fis=openFileInput("music.txt");
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    content=new String(buffer);
                    fis.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                Toast.makeText(Com1714080901240Activity_01.this,"保存的数据是:"+content,0).show();
            break;
            default: break;
        }
    }
}
