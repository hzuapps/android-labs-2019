package edu.hzuapps.androidlabs.soft1714080902334;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class Soft1714080902334Activity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902334);
        Button button=(Button) findViewById(R.id.button);
        imageView=(ImageView) findViewById(R.id.img_list);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902334Activity.this,Main3Activity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }
}