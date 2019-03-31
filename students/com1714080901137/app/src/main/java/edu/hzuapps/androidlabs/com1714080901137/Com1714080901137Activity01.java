package edu.hzuapps.androidlabs.com1714080901137;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class Com1714080901137Activity01 extends AppCompatActivity {
    private RadioButton manRadio;
    private RadioButton womanRadio;
    private EditText et_password;
    private Button btn_send;
    private EditText et_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com171408090113701);
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_send = (Button) findViewById(R.id.btn_send);
        manRadio = (RadioButton) findViewById(R.id.radioMale);
        womanRadio = (RadioButton) findViewById(R.id.radioFemale);
        btn_send = (Button) findViewById(R.id.btn_send);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passDate();
            }
        });
    }
     public void passDate(){
            Intent intent=new Intent(this,Com1714080901137Activity02.class);
            intent.putExtra("name",et_name.getText().toString().trim());
            intent.putExtra("password",et_password.getText().toString().trim());
            String str="";
            if(manRadio.isChecked()){
                str="男";
            }else if(womanRadio.isChecked()){
                str="女";
            }
            intent.putExtra("sex",str);
            startActivity(intent);
        }
    }


