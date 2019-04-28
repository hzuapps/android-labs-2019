package soft1709081602232.androidlabs.hzuapps.edu.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Main2Activity extends AppCompatActivity {

    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView=(ImageView)findViewById(R.id.xuezhiq);
        Glide.with(getApplicationContext()).load("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1333999247,4178461882&fm=26&gp=0.jpg").into(imageView);
    }
}
