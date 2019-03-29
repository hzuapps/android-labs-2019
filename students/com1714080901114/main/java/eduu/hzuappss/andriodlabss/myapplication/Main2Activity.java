package edu.hzuapps.andriodlabs.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }
    public void ShowToast(View view){
        Toast.makeText(this,"未有版主编辑",Toast.LENGTH_SHORT).show();
    }
}
