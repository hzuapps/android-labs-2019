package edu.hzuapps.androidlabs.soft1714080902439;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

import edu.huzapps.androidlabs.soft1714080902439.R;


public class GuaGua extends View {
    private boolean isMove = false;
    private Bitmap bitmap;       //被覆盖层
    private Bitmap frontBitmap;     //覆盖层
    private Path path;
    private Canvas canvas;
    private Paint pathPaint;        //模拟手指头刮开路径的画笔
    private Paint contentPaint;     //用来当做覆盖用的图层文字画笔
    //private ImageView imgv;
    public GuaGua(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        GGBitmp();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.drawBitmap(frontBitmap,0,0,null);
    }

    public void GGBitmp() {
        //画笔
        pathPaint = new Paint();
        pathPaint.setStyle(Paint.Style.STROKE);
        pathPaint.setAlpha(0);
        pathPaint.setStrokeWidth(50);
        //去两层绘制交集，显示下层
        pathPaint.setXfermode(new PorterDuffXfermode((PorterDuff.Mode.DST_IN)));
        pathPaint.setStrokeJoin(Paint.Join.ROUND);
        pathPaint.setStrokeCap(Paint.Cap.ROUND);

        //paint.setDither(true);

        //pathPaint.setAntiAlias(true);
        //path
        path = new Path();

        //被覆盖的那一层
        int[] ImageArray = new int[]{R.drawable.gg1,R.drawable.gg2};
        Random random = new Random();
        int randomImage = 0;
        for(int i=0;i<2;i++){
            randomImage = random.nextInt(2);
        }
        random.nextInt(2);

        bitmap= BitmapFactory.decodeResource(getResources(), ImageArray[randomImage]);
        //覆盖层
        frontBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        //创建一个canva对象，初始画布
        canvas = new Canvas(frontBitmap);
        canvas.drawColor(Color.GRAY);
        //初始化内容画笔
        contentPaint=new Paint();
        contentPaint.setColor(Color.WHITE);
        contentPaint.setTextSize(50);
        contentPaint.setStrokeWidth(10);
        //覆盖图层上的绘制文字
        canvas.drawText("刮刮看你的生日礼物是什么？",canvas.getWidth()/4,canvas.getHeight()/2,contentPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int action=event.getAction();
        switch (action){
            case MotionEvent.ACTION_DOWN:
                //清空画笔
                path.reset();
                //原点移动至手指触摸点
                path.moveTo(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                break;
        }
        canvas.drawPath(path, pathPaint);
        invalidate();
        return true;

    }

}
