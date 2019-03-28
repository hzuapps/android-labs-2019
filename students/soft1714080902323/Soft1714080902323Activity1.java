package androidlabs2019.students.soft1714080902323;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Soft1714080902323Activity1 extends Activity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902323_activity1);
        button= (Button) findViewById(R.id.xinxibiao);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Soft1714080902323Activity1.this, Soft1714080902323Activity2.class);


                startActivity(intent);
            }
        });

    }
}








