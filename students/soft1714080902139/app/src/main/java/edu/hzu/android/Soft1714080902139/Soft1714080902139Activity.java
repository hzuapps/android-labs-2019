package edu.hzu.android.Soft1714080902139;

        import android.annotation.SuppressLint;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.KeyEvent;
        import android.view.MotionEvent;
        import android.widget.Toast;

public class Soft1714080902139Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soft_1714080902139_activity);
    }
    @SuppressLint("WrongConstant")
    public boolean onKeyDown(int keyCode, KeyEvent event){
        Toast.makeText(this,"按键按下！",0).show();
        return super.onKeyUp(keyCode, event);
    }

    @SuppressLint("WrongConstant")
    public boolean onKeyUp(int keyCode, KeyEvent event){
        Toast.makeText(this,"按键弹起！",0).show();
        return  super.onKeyUp(keyCode, event);
    }

    @SuppressLint("WrongConstant")
    public boolean onTouchEvent(MotionEvent event){
        float x=event.getX();
        float y=event.getY();
        Toast.makeText(this,"点击的坐标为（"+x+"："+y+"）",0).show();
        return  super.onTouchEvent(event);
    }


}
