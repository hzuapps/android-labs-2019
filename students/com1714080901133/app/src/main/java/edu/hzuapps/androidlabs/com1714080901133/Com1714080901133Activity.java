package edu.hzuapps.androidlabs.com1714080901133;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import edu.hzuapps.androidlabs.com1714080901133.Com1714080901133Activity;

public class Com1714080901133Activity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.com_1714080901133_activity);
        ImageView jmp = findViewById(R.id.hzu);
        jmp.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Com1714080901133Activity.this,Com1714080901133Activity2.class));
            }
        });
    }
}