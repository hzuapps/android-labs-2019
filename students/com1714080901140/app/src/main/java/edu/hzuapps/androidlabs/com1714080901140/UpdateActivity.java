package edu.hzuapps.androidlabs.com1714080901140;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
public class UpdateActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button);
    }
    public void click(View view) {
        Intent intent = new Intent(updateActivity.this, Com1714080901140Activity.class);
        startActivity(intent);
    }
}