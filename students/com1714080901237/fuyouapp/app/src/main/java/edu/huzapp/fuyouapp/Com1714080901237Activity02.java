package edu.huzapp.fuyouapp;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class Com1714080901237Activity02 extends AppCompatActivity {
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        //setContentView(R.layout.activity02_com1714080901237);
    }


    public void click(View view){
        Intent intent = new Intent(this, Com1714080901237Activity.class);
        startActivity(intent);
    }
}
