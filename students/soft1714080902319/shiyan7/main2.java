package edu.hzuapps.androidlabs.soft1714080902319;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class main2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        if (isNetWorkContent(main2.this)) {
            Toast.makeText(main2.this, "当前网络通畅", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(main2.this, "当前无网络", Toast.LENGTH_LONG).show();
        }


    }
    public boolean isNetWorkContent(Context context) {

        if (context != null) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null) {
                return info.isAvailable();
            }
        }
        return false;
    }

}