package com.example.miku;


        import android.content.Intent;
        import android.graphics.drawable.Drawable;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.view.View.OnClickListener;

public class MActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button button3 = (Button) findViewById(R.id.button1);
        button3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View LL) {
                Intent intent = new Intent(MActivity.this, V1_Activity.class);
                startActivity(intent);
            }
        });
    }

}










