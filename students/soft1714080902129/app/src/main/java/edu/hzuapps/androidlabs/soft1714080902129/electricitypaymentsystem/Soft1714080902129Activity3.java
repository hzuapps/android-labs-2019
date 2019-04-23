package edu.hzuapps.androidlabs.soft1714080902129.electricitypaymentsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Soft1714080902129Activity3 extends AppCompatActivity {

    private SharedPreferences mSharedPref1;
    private  EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft17140809021293);
        editText = findViewById(R.id.id1);
        Button button = findViewById(R.id.button3);
        mSharedPref1 = PreferenceManager.getDefaultSharedPreferences(this);
        boolean b = mSharedPref1.getBoolean("test2",false);
        if (b){
            editText.setText(mSharedPref1.getString("test1",""));
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String edit = editText.getText().toString();
                SharedPreferences.Editor editor = mSharedPref1.edit();
                editor.putString("test1",edit);
                editor.putBoolean("test2",true);
                editor.apply();
            }
        });
    }
}
