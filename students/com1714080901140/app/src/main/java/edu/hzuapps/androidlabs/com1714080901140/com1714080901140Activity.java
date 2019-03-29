package edu.hzuapps.androidlabs.com1714080901140;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
public class com1714080901140Activity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com1714080901140);
    }
    public void click(View view){
        Intent intent = new Intent(com1714080901140Activity.this,MainActivity.class);
        startActivity(intent);
    }
}
