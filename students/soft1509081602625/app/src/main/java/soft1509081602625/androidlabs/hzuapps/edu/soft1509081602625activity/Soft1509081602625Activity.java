package soft1509081602625.androidlabs.hzuapps.edu.soft1509081602625activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Soft1509081602625Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1509081602625_activity);
    }

    public void onClick(View view) {
        Intent intent = new Intent(Soft1509081602625Activity.this, CarinfoActivity.class);
        startActivity(intent);
    }

        public void onClick3 (View view){
            Intent intent = new Intent(Soft1509081602625Activity.this, MyCollectionActivity.class);
            startActivity(intent);
        }
}

