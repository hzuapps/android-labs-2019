package androidlabs.hzuapps.ezu.soft1707080714328;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Soft1707080714328Activity_First extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1707080714328_activity__first);

        intUI();
    }

    private void intUI() {

        findViewById(R.id.btn_fankui).setOnClickListener(this);
        findViewById(R.id.wenti).setOnClickListener(this);
        findViewById(R.id.gushi).setOnClickListener(this);
        findViewById(R.id.xiaoxi).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_fankui:
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),Soft1707080714328Activity_fankui.class);
                startActivity(intent);
                break;
            case R.id.wenti:
                Intent intent1 = new Intent();
                intent1.setClass(getApplicationContext(),Soft1707080714328Activity_wenti.class);
                startActivity(intent1);
                break;
            case R.id.gushi:
                Intent intent2 = new Intent();
                intent2.setClass(getApplicationContext(), Soft1707080714328Activity_gushi.class);
                startActivity(intent2);
                break;
            case R.id.xiaoxi:
                Intent intent3 = new Intent();
                intent3.setClass(getApplicationContext(),Soft1707080714328Activity_xiaoxi.class);
                startActivity(intent3);
                break;
        }
    }
}