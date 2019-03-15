package soft1714080902412.androidlabs.hzuapps.edu.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1714080902412Activity2 extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft17140809024122);
        button=(Button)findViewById(R.id.button2);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent =new Intent(Soft1714080902412Activity2.this,Soft1714080902412Activity3.class);
        startActivity(intent);
    }
}
