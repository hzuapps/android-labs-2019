package edu.hzu.android.soft1714080902139;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class Soft1714080902139Activity extends AppCompatActivity {

    private Button btn01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902139_activity);

        btn01 = (Button)findViewById(R.id.btn01);
        btn01.getBackground().setAlpha(0);
    }

    public void ski(View view){
        Intent intent = new Intent();
        intent.setClass(Soft1714080902139Activity.this,Soft1714080902139Activity2.class);
        startActivity(intent);
    }



}