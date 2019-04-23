package soft1509081602625.androidlabs.hzuapps.edu.soft1509081602625activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import soft1714080902407.androidlabs.hzuapps.edu.soft1509081602625activity.R;

public class Soft1509081602625Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1509081602625_activity);
    }
    @Override
    protected void onStart(){
        Log.i("Soft1509081602625Actvity","onStart");
        if(!ConnectionUtil.isConn(this)){
            ConnectionUtil.setNetworkMethod(this);
        }else{
            Toast.makeText(this, "当前网络可用", Toast.LENGTH_SHORT).show();
        }
        super.onStart();
    }
    public void onClick(View view) {
        Intent intent = new Intent(Soft1509081602625Activity.this, CarinfoActivity.class);
        startActivity(intent);
    };
    public void onClick3 (View view){
        Intent intent = new Intent(Soft1509081602625Activity.this, MyCollectionActivity.class);
        startActivity(intent);
    };
}

