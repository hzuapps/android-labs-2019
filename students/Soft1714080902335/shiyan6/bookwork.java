package edu.hzuapps.androidlabs.soft1714080902335activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class bookwork extends AppCompatActivity {

    private ImageView tvtext;
    private Button gettvtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookwork);

        gettvtext = (Button) this.findViewById(R.id.gettvtext);
        tvtext = (ImageView) this.findViewById(R.id.tvtext);

        StrictMode.setThreadPolicy(new
                StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(
                new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());

        gettvtext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                gettvtext.setEnabled(false);
                String strURL = "http://image.so.com/view?q=%E6%97%85%E6%B8%B8%E5%9B%BE%E7%89%87&src=tab_www&correct=%E6%97%85%E6%B8%B8%E5%9B%BE%E7%89%87&ancestor=list&cmsid=7af22856951e08dc88a33be6b77364ab&cmran=0&cmras=6&cn=0&gn=0&kn=16&fsn=76#id=89211d90f09b74c082f8a67c8503f5c5&currsn=0";
                try {
                    Bitmap bitmap = getBitmap(strURL);
                    tvtext.setImageBitmap(bitmap);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    public Bitmap getBitmap(String path) throws IOException {
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            if (conn.getResponseCode() == 200) {
                InputStream inputStream = conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                return bitmap;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

}
