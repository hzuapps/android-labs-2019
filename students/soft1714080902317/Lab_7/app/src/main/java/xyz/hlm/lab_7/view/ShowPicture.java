package xyz.hlm.lab_7.view;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import java.io.FileNotFoundException;

public class ShowPicture extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri = getIntent().getData();
        ImageView imageView = new ImageView(this);
        try {
            imageView.setImageBitmap(BitmapFactory.decodeStream(getContentResolver().openInputStream(uri)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        imageView.setRotation(90);
        setContentView(imageView);
    }
}
