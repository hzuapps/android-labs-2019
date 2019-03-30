package edu.hzuapps.androidlabs.soft1714080902439;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.soft171408092439.R;

public class Soft1714080902439AddBirthdayActivity extends AppCompatActivity {
    private TextView textView;
    private EditText editText;
    private RadioButton radioButton;
    private Spinner spinner;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902439_addbirthday_activity);
        button=(Button) findViewById(R.id.recall_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast button=Toast.makeText(Soft1714080902439AddBirthdayActivity.this,"",Toast.LENGTH_SHORT);
                Intent intent=new Intent(Soft1714080902439AddBirthdayActivity.this,Soft1714080902439Activity.class);
                startActivity(intent);
                button.show();
            }
        });
    }
}
