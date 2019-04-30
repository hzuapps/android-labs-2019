package edus.hzuapp.androidlabs.soft1714080902134;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902134PowerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902134_power);

        Button button1=(Button) findViewById(R.id.billbutton);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent=new Intent(Soft1714080902134PowerActivity.this, Soft1714080902134RegisterActivity.class);
                startActivity(intent);
            }

        });
        Button button2=(Button) findViewById(R.id.telephonebutton);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent=new Intent(Soft1714080902134PowerActivity.this, Soft1714080902134RegisterActivity.class);
                startActivity(intent);
            }

        });
        Button button3=(Button) findViewById(R.id.trafficbutton);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent=new Intent(Soft1714080902134PowerActivity.this, Soft1714080902134RegisterActivity.class);
                startActivity(intent);
            }

        });
        Button button4=(Button) findViewById(R.id.packagebutton);
        button4.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                Intent intent=new Intent(Soft1714080902134PowerActivity.this, Soft1714080902134RegisterActivity.class);
                startActivity(intent);
            }

        });
    }

}
