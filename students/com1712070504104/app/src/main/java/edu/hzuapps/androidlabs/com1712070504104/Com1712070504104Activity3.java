package edu.hzuapps.androidlabs.com1712070504104;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Com1712070504104Activity3 extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_1712070504104_activity3);
        Button b=findViewById(R.id.birthday_lable);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Com1712070504104Activity3.this,Com1712070504104Activity.class);
                startActivity(intent);
            }
        });}
    public void startCamera(View view){
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE );
        startActivity(intent);
    }
}
