package android.MusicPlayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Com1714080901240Activity_02 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com1714080901240_02);
    }
    public void click03(View view){
        //创建一个Intent对象，开启下一个activity
        Intent intent3 = new Intent(this,Com1714080901240Activity_03.class);
        startActivity(intent3);
    }
}
