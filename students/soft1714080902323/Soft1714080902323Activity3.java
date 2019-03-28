package androidlabs2019.students.soft1714080902323;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Soft1714080902323Activity3 extends Activity {
    private Button w31,w32,w33,w34;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902323_activity3);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String xuehao=intent.getStringExtra("xuehao");
        String banji=intent.getStringExtra("banji");
        String sex=intent.getStringExtra("sex");
        w32=(Button)findViewById(R.id.w32);
        w31=(Button)findViewById(R.id.w31);
        w33=(Button)findViewById(R.id.w33);
        w34=(Button)findViewById(R.id.w34);
        w32.setText(name);
        w31.setText(xuehao);
        w33.setText(sex);
        w34.setText(banji);
    }
}
