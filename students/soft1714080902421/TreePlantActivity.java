package edu.hzuapps.androidlabs.soft1714080902421;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class TreePlantActivity extends AppCompatActivity {
    private ImageView imageView = null;
    private Button mBtnimage1 = null;//图1
    private Button mBtnimage2 = null;//图2

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_plant);
        imageView =  findViewById(R.id.imageView);
        mBtnimage1 = findViewById(R.id.btn_image1);
        mBtnimage2 = findViewById(R.id.btn_image2);
        mBtnimage1.setOnClickListener(listener);
        mBtnimage2.setOnClickListener(listener);
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        public void onClick(View v) {
            if (v == mBtnimage1) {
                //   currentImgId=(currentImgId-1+imgId.length)%imgId.length;
                //    imageView.setImageResource(imgId[currentImgId]);
                imageView.setImageResource(R.drawable.pic1);

            }
            if (v == mBtnimage2) {
                //  currentImgId=(currentImgId+1)%imgId.length;
                //  imageView.setImageResource(imgId[currentImgId]);
                imageView.setImageResource(R.drawable.pic2);
            }

        }


    };

    public boolean onTouchEvent(MotionEvent event){
        float x=event.getX();
        float y=event.getY();
        Toast.makeText(this,"更多树种正在筹备中，敬请期待~",Toast.LENGTH_SHORT).show();
        return super.onTouchEvent(event);
    }
}
