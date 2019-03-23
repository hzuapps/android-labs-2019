package edu.hzuapps.androidlabs.soft1714080902335activity;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.TextView;

        import org.w3c.dom.Text;

public class Soft1714080902335Activity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft1714080902335);

        textView=(TextView)findViewById(R.id.textview01);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Soft1714080902335Activity.this,SecondActivity.class);
                startActivity(intent);
            }
        });



    }
}
