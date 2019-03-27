package edu.hzuapps.androidlabs.soft1714080902436;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Soft1714080902436ConfirmActivity extends AppCompatActivity {

    private TextView Transmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902436_confirm);
        final Intent intent1 = this.getIntent();
        final String transmit = intent1.getStringExtra("transmit");
        TextView Transmit=(TextView)findViewById(R.id.transmit);
        Transmit.setText(transmit);
        Button backButton = (Button) findViewById(R.id.bank_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
