package edu.hzuapps.androidlabs.demo2;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Soft1714080902220AlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog alert=new AlertDialog.Builder(this).create();
        alert.setIcon(R.drawable.alarm);
        alert.setTitle("传递正能量");
        alert.setMessage("要么出众，要么出局");
        alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }
}
