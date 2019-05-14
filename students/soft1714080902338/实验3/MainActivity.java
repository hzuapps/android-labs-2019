package java.soft1714080902338activity.soft1714080902338.androidlabs.hzuapps.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void  click(View view){
        Intent intent=new Intent(this,MainActivity3.class);
        startActivity(intent);
    }

}
