package edu.hzuapps.androidlabs.Soft1714080902238;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.FileOutputStream;

import lun.com.myapplication.R;

public class ItemActivity extends Activity {
    private static final String TAG = "itemActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
    }
    protected void Click1(View view){
        Intent intent = new Intent(this,ShoppingActivity.class);
        String fileName="shopping.txt";
        String item="烤肉 10串";
        FileOutputStream fos;
        try{

            fos=openFileOutput(fileName,MODE_PRIVATE);
            fos.write(item.getBytes());
            fos.close();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        startActivity(intent);
    }
}
