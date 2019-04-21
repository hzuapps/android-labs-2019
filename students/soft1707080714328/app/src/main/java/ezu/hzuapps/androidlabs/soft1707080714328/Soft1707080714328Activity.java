package androidlabs.hzuapps.ezu.soft1707080714328;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Soft1707080714328Activity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1707080714328_activity);
        //初始化方法
        intUI();
    }

    private void intUI() {
        findViewById(R.id.ibtn_skip).setOnClickListener(this);
    }

    public void btn_zhuce(View view){
       Toast.makeText(getApplicationContext(),"暂时无法注册,请点击右上角跳过",Toast.LENGTH_SHORT).show();
    }

    public void btn_denglu(View view){
        Toast.makeText(getApplicationContext(),"暂时无法登录,请点击右上角跳过",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ibtn_skip:
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),Soft1707080714328Activity_First.class);
                this.startActivity(intent);
                break;
        }
    }
}
