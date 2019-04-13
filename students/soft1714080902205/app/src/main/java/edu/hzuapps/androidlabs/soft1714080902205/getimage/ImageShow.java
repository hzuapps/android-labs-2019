package edu.hzuapps.androidlabs.soft1714080902205.getimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import edu.hzuapps.androidlabs.soft1714080902205.R;

public class ImageShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_show_activity);

        final HeadImageView headImageView = (HeadImageView) findViewById(R.id.image_view);
        final EditText et_image = (EditText) findViewById(R.id.et_image) ;
        Button button = (Button) findViewById(R.id.button_image);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //直接把网络的图片路径写上就可以显示网络的图片了
                headImageView.setImageURL(et_image.getText().toString().trim());
            }
        });
    }
}
