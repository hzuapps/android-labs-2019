package edu.hzuapps.androidlabs.Soft1714080902238;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.FileOutputStream;

import lun.com.myapplication.R;

public class ItemActivity extends Activity {
    private static final String TAG = "ItemActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
    }
    protected void Click1(View view){
        Intent intent = new Intent(this,ShoppingActivity.class);
        String fileName="shopping.txt";
        int i=view.getId();
        i-=2131427426;
        TextView textView=null;
        MsgQueue.i=i;
        switch (i)
        {
            case 1:
                textView=(TextView)findViewById(R.id.t1);
                break;
            case 2:
                textView=(TextView)findViewById(R.id.t4);
                break;
            case 3:
                textView=(TextView)findViewById(R.id.t2);
                break;
            case 4:
                textView=(TextView)findViewById(R.id.t3);
                break;
        }

        String item= (String) textView.getText()+"\n\n";
        FileOutputStream fos;
        //FileInputStream fis;
//        try{
//            fis=openFileInput("shopping.txt");
//
//        }catch(Exception e)
//        {
          try{
            //  fos=new FileOutputStream(fileName,false);
              //MODE_APPEND
              fos=openFileOutput(fileName, MODE_APPEND);
              fos.write(item.getBytes());
              fos.close();
          }
          catch (Exception e2)
          {
              e2.printStackTrace();
          }

    //    }
        startActivity(intent);
    }
}
