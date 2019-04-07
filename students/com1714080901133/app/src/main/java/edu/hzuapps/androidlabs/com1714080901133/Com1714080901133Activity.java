package edu.hzuapps.androidlabs.com1714080901133;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ashuo.myapplication.Main2Activity;
import com.example.ashuo.myapplication.MainActivity;
import com.example.ashuo.myapplication.R;

public class Com1714080901133Activity extends Activity {

    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_1714080901133_activity);


        button=findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Com1714080901133Activity.this, "跳转中，请稍后", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(Com1714080901133Activity.this,Com1714080901133Activity2.class);
                startActivity(i);
            }
        });

}
}
