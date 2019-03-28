package edu.hzu.android.soft1714080902139;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class Activity03 extends AppCompatActivity {

    private RadioButton manRadio;
    private RadioButton womanRiod;
    private EditText et_password;
    private Button btn_send;
    private EditText et_name;

    @Override
    public void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        setContentView(R.layout.acitity03);

        et_name=(EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_send = (Button)findViewById(R.id.btn_send);
        manRadio=(RadioButton) findViewById(R.id.radioMale);
        womanRiod = (RadioButton) findViewById(R.id.radioFemale);

        btn_send.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                passDate();
            }

            private void passDate() {
                Intent intent = new Intent(Activity03.this,Soft1714080902139Activity2.class);
                intent.putExtra("name",et_name.getText().toString().trim());
                intent.putExtra("password",et_password.getText().toString().trim());
                String str="";
                if(manRadio.isChecked()){
                    str="男";
                }else if(womanRiod.isChecked()){
                    str="女";
                }
                intent.putExtra("sex",str);
                startActivity(intent);
            }
        });

    }
}
