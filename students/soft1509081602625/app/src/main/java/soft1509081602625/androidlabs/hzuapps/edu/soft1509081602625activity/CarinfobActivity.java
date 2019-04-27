package soft1509081602625.androidlabs.hzuapps.edu.soft1509081602625activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

import static soft1714080902407.androidlabs.hzuapps.edu.soft1509081602625activity.R.id.aodi1_info;

public class CarinfobActivity extends AppCompatActivity {
    private TextView aodi1_info;
    private Button btn_collect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carinfob_activity);
        //获取布局文件中的控件
        aodi1_info = (TextView) findViewById(R.id.aodi1_info);
        btn_collect = (Button) findViewById(R.id.btn_collect);
        btn_collect.setOnClickListener(new CarinfobActivity.ButtonListener());
    }
    public void onClick2(View view) {
        Intent intent = new Intent(CarinfobActivity.this, Soft1509081602625Activity.class);
        startActivity(intent);
    }
    private class ButtonListener implements View.OnClickListener{
        public void onClick(View v){
            switch (v.getId()){
                case R.id.btn_collect:
                    String collection = aodi1_info.getText().toString().trim();
                    FileOutputStream fos;
                    try{
                        fos = openFileOutput("car.text", Context.MODE_APPEND);
                        fos.write(collection.getBytes());
                        fos.close();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    Toast.makeText(CarinfobActivity.this,"收藏成功",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

}
