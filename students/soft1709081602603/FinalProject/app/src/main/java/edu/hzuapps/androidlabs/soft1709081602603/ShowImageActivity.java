package edu.hzuapps.androidlabs.soft1709081602603;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ShowImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        final ViewImageActivity viewImage=(ViewImageActivity) findViewById(R.id.image_view);
        final EditText et_image=(EditText) findViewById(R.id.et_image);
        Button button=(Button) findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewImage.setImageURL(et_image.getText().toString().trim());
            }
        });
    }
}