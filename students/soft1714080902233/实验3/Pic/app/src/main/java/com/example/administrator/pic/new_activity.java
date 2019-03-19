package com.example.administrator.pic;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.net.Uri;

public class new_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity2);
        Button csdn=(Button) findViewById(R.id.Skip_to_csdn);
        csdn.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://blog.csdn.net/weixin_41544329")));
                    }
                }
        );
    }

}
