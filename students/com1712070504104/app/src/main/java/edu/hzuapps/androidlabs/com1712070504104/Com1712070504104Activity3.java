package edu.hzuapps.androidlabs.com1712070504104;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Com1712070504104Activity3 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_1712070504104_activity3);}
    public void startCamera(View view){
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE );
        startActivity(intent);
    }
}
