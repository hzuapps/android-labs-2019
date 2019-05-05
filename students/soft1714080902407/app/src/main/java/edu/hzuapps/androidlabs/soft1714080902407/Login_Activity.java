package edu.hzuapps.androidlabs.soft1714080902407;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        boolean conn = ConnectionUtil.isConn(Login_Activity.this);

        if (!conn) {
            ConnectionUtil.setNetworkMethod(Login_Activity.this);
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.button_login) {
            Intent intent = new Intent(Login_Activity.this,Soft1714080902407Activity.class);
            startActivity(intent);
        }
    }
}
