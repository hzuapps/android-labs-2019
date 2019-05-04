package edu.hzuapps.androidlabs.soft1714080902420;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


public class PictureActivity extends AppCompatActivity {
    Button button;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        button = (Button) findViewById(R.id.button20);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     imageView1 = (ImageView) findViewById(R.id.imageView1);
                     Glide.with(PictureActivity.this).load("https://img3.doubanio.com/view/photo/l/public/p2553240846.webp").into(imageView1);
                     imageView2 = (ImageView) findViewById(R.id.imageView2);
                     Glide.with(PictureActivity.this).load("https://img3.doubanio.com/view/photo/l/public/p2552111736.webp").into(imageView2);
                     imageView3 = (ImageView) findViewById(R.id.imageView3);
                     Glide.with(PictureActivity.this).load("https://img3.doubanio.com/view/photo/l/public/p2552111724.webp").into(imageView3);
                     imageView4 = (ImageView) findViewById(R.id.imageView4);
                     Glide.with(PictureActivity.this).load("https://img1.doubanio.com/view/photo/l/public/p2551875159.webp").into(imageView4);
                     imageView5 = (ImageView) findViewById(R.id.imageView5);
                     Glide.with(PictureActivity.this).load("https://img3.doubanio.com/view/photo/l/public/p2550759575.webp").into(imageView5);
                     imageView6 = (ImageView) findViewById(R.id.imageView6);
                     Glide.with(PictureActivity.this).load("https://img1.doubanio.com/view/photo/l/public/p2552111738.webp").into(imageView6);
                     imageView7 = (ImageView) findViewById(R.id.imageView7);
                     Glide.with(PictureActivity.this).load("https://img3.doubanio.com/view/photo/l/public/p2552111746.webp").into(imageView7);
                     imageView8 = (ImageView) findViewById(R.id.imageView8);
                     Glide.with(PictureActivity.this).load("https://img3.doubanio.com/view/photo/l/public/p2552111735.webp").into(imageView8);
                     imageView9 = (ImageView) findViewById(R.id.imageView9);
                     Glide.with(PictureActivity.this).load("https://img3.doubanio.com/view/photo/l/public/p2552111723.webp").into(imageView9);
                     imageView10 = (ImageView) findViewById(R.id.imageView10);
                     Glide.with(PictureActivity.this).load("https://img3.doubanio.com/view/photo/l/public/p2547228203.webp").into(imageView10);
            }
        });
    }
}

