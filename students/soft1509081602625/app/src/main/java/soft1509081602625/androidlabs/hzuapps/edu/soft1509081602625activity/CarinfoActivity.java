package soft1509081602625.androidlabs.hzuapps.edu.soft1509081602625activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CarinfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carinfo_activity);
    }
    public void onClick2(View view){
        Intent intent = new Intent(CarinfoActivity.this,Soft1509081602625Activity.class);
        startActivity(intent);
    }
}
