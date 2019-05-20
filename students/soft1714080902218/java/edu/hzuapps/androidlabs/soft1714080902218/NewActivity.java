package edu.hzuapps.androidlabs.soft1714080902218;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class NewActivity extends Activity {

    private Button button3 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newone);

        getWindow().setBackgroundDrawableResource(R.drawable.cloud);

        button3 = (Button)findViewById(R.id.button3) ;


        //绑定监听器
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(NewActivity.this,Soft1714080902218Activity.class) ;
                startActivity(intent) ;
            }

        });


    }
}