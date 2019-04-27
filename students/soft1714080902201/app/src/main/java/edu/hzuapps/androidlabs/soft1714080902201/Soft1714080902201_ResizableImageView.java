package edu.hzuapps.androidlabs.soft1714080902201;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

public class Soft1714080902201_ResizableImageView extends android.support.v7.widget.AppCompatImageView {

    public Soft1714080902201_ResizableImageView(Context context) {
        super(context);
    }

    public Soft1714080902201_ResizableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        Drawable d = getDrawable();
        if(d!=null){
            // ceil not round - avoid thin vertical gaps along the left/right edges
            int width = MeasureSpec.getSize(widthMeasureSpec);
            //高度根据使得图片的宽度充满屏幕计算而得
            int height = (int) Math.ceil((float) width * (float) d.getIntrinsicHeight() / (float) d.getIntrinsicWidth());
            setMeasuredDimension(width, height);
        }else{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
