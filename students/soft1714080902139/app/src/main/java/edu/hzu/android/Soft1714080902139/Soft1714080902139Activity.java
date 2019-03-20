package edu.hzu.android.soft1714080902139;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;

public class Soft1714080902139Activity extends AppCompatActivity {

    private View startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902139_activity);
    }

    public void skip(View view){
        Intent intent = new Intent();
        intent.setClass(Soft1714080902139Activity.this,Soft1714080902139Activity2.class);
        startActivity(intent);
    }


}