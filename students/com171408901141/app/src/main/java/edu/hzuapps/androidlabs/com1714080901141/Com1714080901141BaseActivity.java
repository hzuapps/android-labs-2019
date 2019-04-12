package edu.hzuapps.androidlabs.com1714080901141;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Com1714080901141BaseActivity extends AppCompatActivity {
    private int TAG;    //Com1714080901141 is too long，not better for log.d() function.
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("TAG",getClass().getSimpleName());//to know exactly wher you are
        Com1714080901141ActivityCollector.addActivity(this);//static class is share with all
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Com1714080901141ActivityCollector.removeActivity(this);
    }
}
