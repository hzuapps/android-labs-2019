package edu.hzuapps.androidlabs.soft1714080902232;



import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


public class Soft1714080902232Activity_Web extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceStanceState) {
        super.onCreate(savedInstanceStanceState);
        setContentView(R.layout.soft_1714080902232_activity_web);
        final Soft1714080902232Activity_MyImageView myImageView = (Soft1714080902232Activity_MyImageView) findViewById(R.id.image_view);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                myImageView.setImageURL("http://g.hiphotos.baidu.com/image/pic/item/50da81cb39dbb6fd04a2a7440724ab18962b37d2.jpg");
            }
        });
    }
}