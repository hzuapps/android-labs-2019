package androidlabs2019.students.soft1714080902323;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Soft1714080902323Activity2 extends Activity {
     private RadioButton manRadio;
    private RadioButton womanRadio;
    private EditText banji;
    private EditText xuehao;
    private EditText name;
    private Button enter;
    private Button camera;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902323_activity2);
        name=(EditText) findViewById(R.id.name);
        banji=(EditText) findViewById(R.id.banji);
        xuehao=(EditText) findViewById(R.id.xuehao);
        manRadio=(RadioButton) findViewById(R.id.radioMale);
        womanRadio=(RadioButton) findViewById(R.id.radioFemale);
        enter=(Button)findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passData();
            }
        });
        camera= (Button) findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902323Activity2.this, Soft1714080902323Activity6.class);
                startActivity(intent);
            }
        });
    }

    public void passData(){
        Intent intent=new Intent(this,Soft1714080902323Activity3.class);
        intent.putExtra("name",name.getText().toString().trim());
        intent.putExtra("xuehao",xuehao.getText().toString().trim());
        intent.putExtra("banji",banji.getText().toString().trim());
        String str="";
        if(manRadio.isChecked()){
            str="男";
        }else if (womanRadio.isChecked()){
            str="女";
        }
        intent.putExtra("sex",str);
        startActivity(intent);
    }
}
