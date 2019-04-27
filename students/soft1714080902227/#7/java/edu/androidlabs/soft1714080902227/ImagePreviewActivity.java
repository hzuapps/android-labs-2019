package edu.androidlabs.soft1714080902227;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tuku3.R;

public class ImagePreviewActivity extends AppCompatActivity {

    private ImageView imageView;
    private String Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_preview);
        imageView = (ImageView) findViewById(R.id.image_preview);
        Bundle bundle = getIntent().getExtras();
        Image = bundle.getString("ImageUrl");
        Glide.with(this).load(Image).into(imageView);
    }
}
