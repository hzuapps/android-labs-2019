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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_fankui:
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),Soft1707080714328Activity_fankui.class);
                startActivity(intent);
                break;
        }
    }
}
