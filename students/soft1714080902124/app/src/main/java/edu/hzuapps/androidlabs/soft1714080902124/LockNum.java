package edu.hzuapps.androidlabs.soft1714080902124;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Service;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static android.widget.RelativeLayout.CENTER_HORIZONTAL;
import static android.widget.RelativeLayout.CENTER_IN_PARENT;

public class LockNum extends LinearLayout {

    private String[] number = new String[]{
            "1","2","3","4","5","6","7","8","9","","0"
    };
    private int paddingLR;
    private int paddingSN;
    //4个密码位ImageView
    private ArrayList<CircleImageView> resultList;

    private LinearLayout linearLayout;
    //存储当前输入的内容
    private StringBuilder password;
    //振动效果
    private Vibrator vibrator;
    //整个键盘的颜色
    private int color;
    //4个密码位宽度
    private int resultRadius;
    //数字键盘每个键的宽度
    private int numRadius;
    //每个圆边界宽度
    private int strokeWidth;

    public LockNum(Context context){
        this(context,null);
    }
    public LockNum(Context context, AttributeSet attributeSet){
        this(context,attributeSet,0);
    }
    public LockNum(Context context,AttributeSet attributeSet,int style){
        super(context,attributeSet,style);
        paddingLR = dip2px(context,21);
        paddingSN = dip2px(context,10);
        color = Color.BLACK;
        resultRadius = dip2px(context,20);
        numRadius = dip2px(context,66);
        strokeWidth = dip2px(context,2);
        vibrator = (Vibrator)context.getSystemService(Service.VIBRATOR_SERVICE);
        resultList = new ArrayList<>();
        password = new StringBuilder();
        setOrientation(VERTICAL);
        setGravity(CENTER_HORIZONTAL);
        initView(context);
    }

    public void initView(Context context){
        linearLayout = new LinearLayout(context);
        for (int i=0;i<4;i++){
            CircleImageView resultItem = new CircleImageView(context);
            resultList.add(resultItem);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(resultRadius,resultRadius);
            params.leftMargin = dip2px(context,4);
            params.rightMargin = dip2px(context,4);
            resultItem.setPadding(dip2px(context,2),dip2px(context,2),dip2px(context,2),dip2px(context,2));
            resultItem.setLayoutParams(params);
            linearLayout.addView(resultItem);
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER_HORIZONTAL;
        params.bottomMargin = dip2px(context,34);
        linearLayout.setLayoutParams(params);
        addView(linearLayout);

        GridLayout numContainer = new GridLayout(context);
        numContainer.setColumnCount(3);
        for (int i=0;i<number.length;i++){
            RelativeLayout numItem = new RelativeLayout(context);
            numItem.setPadding(paddingLR,paddingSN,paddingLR,paddingSN);
            RelativeLayout.LayoutParams gridItemParams = new RelativeLayout.LayoutParams(numRadius,numRadius);
            gridItemParams.addRule(CENTER_IN_PARENT);
            final TextView numTv = new TextView(context);
            numTv.setText(number[i]);
            numTv.setTextColor(color);
            numTv.setTextSize(30);
            numTv.setGravity(Gravity.CENTER);
            numTv.setLayoutParams(gridItemParams);
            final CircleImageView numBgTv = new CircleImageView(context);
            numBgTv.setLayoutParams(gridItemParams);
            numTv.setOnTouchListener(new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            numBgTv.setFillCircle();
                            numTv.setTextColor(Color.WHITE);
                            if (password.length()<4){
                                password.append(numTv.getText());
                                resultList.get(password.length()-1).setFillCircle();
                                if (inputListener!=null && password.length() == 4){
                                    inputListener.inputFinish(password.toString());

                                }
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                                numBgTv.setStrokeCircle();
                                numTv.setTextColor(color);
                                break;
                    }
                    return true;
                }
            });
            numItem.addView(numBgTv);
            numItem.addView(numTv);
            numContainer.addView(numItem);
            if (i == 9){
                numItem.setVisibility(INVISIBLE);
            }
        }
        RelativeLayout deleteItem = new RelativeLayout(context);
        deleteItem.setPadding(paddingLR,paddingSN,paddingLR,paddingSN);
        RelativeLayout.LayoutParams gridItemParams = new RelativeLayout.LayoutParams(numRadius,numRadius);
        gridItemParams.addRule(CENTER_IN_PARENT);
        TextView deleteTv = new TextView(context);
        deleteTv.setText("Delete");
        deleteTv.setTextColor(color);
        deleteTv.setTextSize(dip2px(context,4));
        deleteTv.setLayoutParams(gridItemParams);
        deleteTv.setGravity(Gravity.CENTER);
        deleteItem.addView(deleteTv);
        numContainer.addView(deleteItem);
        deleteTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });
        LinearLayout.LayoutParams gridParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        gridParams.gravity = Gravity.CENTER_HORIZONTAL;
        numContainer.setLayoutParams(gridParams);
        addView(numContainer);
    }
    public void showErrorStatus(){
        vibrator.vibrate(new long[]{100,100,100,100},-1);
        List<Animator> animators = new ArrayList<>();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(linearLayout,"translationX",-50.0f,-50.0f,0.0f);
        objectAnimator.setDuration(400);
        animators.add(objectAnimator);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animators);
        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                resetResult();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    /**
     * 删除
     */
    public void delete(){
        if (password.length() == 0)
            return;
        resultList.get(password.length()-1).setStrokeCircle();
        password.deleteCharAt(password.length()-1);
    }

    /**
     * 重置密码输入
     */
    public void resetResult(){
        for (int i=0;i<resultList.size();i++){
            resultList.get(i).setStrokeCircle();
        }
        password.delete(0,4);
    }

    /**
     * 监听输入完毕
     * 接口
     */

    private InputListener inputListener;

    public void setInputListener(InputListener inputListener){
        this.inputListener = inputListener;
    }

    public interface InputListener{
        void inputFinish(String result);
    }

    /**
     * dip/dp转像素
     *
     * dipValue
     *      dip或dp大小
     *  像素值
     */

    public static int dip2px(Context context,float dipValue){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) (dipValue * (metrics.density) + 0.5f);
    }

    /**
     * 圆形背景ImageView（设置实心或空心）
     */
    public class CircleImageView extends android.support.v7.widget.AppCompatImageView {
        private Paint paint;
        private int width;
        private int height;
        public CircleImageView(Context context){
            this(context,null);
        }
        public CircleImageView(Context context,AttributeSet attributeSet){
            this(context,attributeSet,0);
        }
        public CircleImageView(Context context,AttributeSet attributeSet,int style){
            super(context,attributeSet,style);
            initView(context);
        }
        public void initView(Context context){
            paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(color);
            paint.setStrokeWidth(strokeWidth);
            paint.setAntiAlias(true);
        }
        protected void onSizeChanged(int w,int h,int oldw,int oldh){
            super.onSizeChanged(w,h,oldw,oldh);
            width = w;
            height = h;
        }
        public void draw(Canvas canvas){
            canvas.drawCircle(width/2,height/2,width/2-6,paint);
            super.draw(canvas);
        }

        /**
         * 设置圆为实心状态
         */
        public void setFillCircle(){
            paint.setStyle(Paint.Style.FILL);
            invalidate();
        }
        /**
         * 设置圆为空心
         */
        public void setStrokeCircle(){
            paint.setStyle(Paint.Style.STROKE);
            invalidate();
        }
    }
}
