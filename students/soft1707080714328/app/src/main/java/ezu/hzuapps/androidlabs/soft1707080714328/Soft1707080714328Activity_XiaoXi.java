package androidlabs.hzuapps.ezu.soft1707080714328;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Soft1707080714328Activity_XiaoXi extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1707080714328_activity__xiaoxi);

        intUI();
    }

    private void intUI() {

        findViewById(R.id.gushi).setOnClickListener(this);
        findViewById(R.id.wo).setOnClickListener(this);
        findViewById(R.id.xiaoxi).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gushi:
                Intent intent1 = new Intent();
                intent1.setClass(getApplicationContext(), Soft1707080714328Activity_GuShi.class);
                startActivity(intent1);
                break;
            case R.id.wo:
                Intent intent2 = new Intent();
                intent2.setClass(getApplicationContext(), Soft1707080714328Activity_First.class);
                startActivity(intent2);
                break;
            case R.id.xiaoxi:
                Intent intent3 = new Intent();
                intent3.setClass(getApplicationContext(), Soft1707080714328Activity_XiaoXi.class);
                startActivity(intent3);
                break;
        }
    }

}
