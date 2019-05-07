package androidlabs2019.students.soft1714080902323;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Soft1714080902323Activity extends Activity {
    private TextView textView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902323_activity);
        textView = (TextView) findViewById(R.id.textview_01);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902323Activity.this, Soft1714080902323Activity1.class);
                startActivity(intent);
            }
        });

    }
}
