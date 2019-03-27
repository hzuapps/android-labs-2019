package edu.hzuapps.androidlabs.Soft1714080902215;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class Questionnaire extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionnaire);
        Log.i("Questionnaire","onCreate()");
    }
}
