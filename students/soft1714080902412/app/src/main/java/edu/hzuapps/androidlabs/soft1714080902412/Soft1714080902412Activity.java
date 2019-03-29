package edu.hzuapps.androidlabs.soft1714080902412;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Soft1714080902412Activity extends AppCompatActivity implements View.OnClickListener {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902412);
        button1=(Button)findViewById(R.id.button4);
        button1.setOnClickListener(this);
        button2=(Button)findViewById(R.id.button5);
        button2.setOnClickListener(this);
        button3=(Button)findViewById(R.id.miaobiao);
        button3.setOnClickListener(this);
        button4=(Button)findViewById(R.id.beiwanglu);
        button4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button4:
            Intent intent1 = new Intent(Soft1714080902412Activity.this, Soft1714080902412Activity2.class);
            startActivity(intent1);break;
            case R.id.button5:
            Intent intent2 = new Intent(Soft1714080902412Activity.this, Soft1714080902412Activity3.class);
            startActivity(intent2);break;
            case R.id.miaobiao:
            Intent intent3 = new Intent(Soft1714080902412Activity.this, Soft1714080902412Activity4.class);
            startActivity(intent3);break;
            case R.id.beiwanglu:
            Intent intent4 = new Intent(Soft1714080902412Activity.this, Soft1714080902412Activity5.class);
            startActivity(intent4);break;

        }
    }
}

