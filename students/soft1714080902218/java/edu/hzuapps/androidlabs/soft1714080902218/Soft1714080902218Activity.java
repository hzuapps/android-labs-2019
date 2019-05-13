package edu.hzuapps.androidlabs.soft1714080902218;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902218Activity extends Activity {

    private Button button2 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902218_activity);

        getWindow().setBackgroundDrawableResource(R.drawable.rainbow);

        button2 = (Button)findViewById(R.id.button2) ;

        //绑定监听器
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(Soft1714080902218Activity.this,TrdActivity.class) ;
                startActivity(intent) ;
            }

        });


    }
}