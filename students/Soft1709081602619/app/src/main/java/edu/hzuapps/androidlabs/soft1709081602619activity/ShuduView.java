package edu.hzuapps.androidlabs.soft1709081602619activity;

import android.R.integer;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.FontMetricsInt;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class ShuduView extends View{
    private float width;
    private float height;
    int selectedX;
    int selectedY;
    private Game game=new Game();
    public ShuduView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // TODO Auto-generated method stub
        this.width=w/9f;
        this.height=h/9f;
        super.onSizeChanged(w, h, oldw, oldh);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        //绘制背景颜色
        Paint background_paint = new Paint();
        background_paint.setColor(getResources().getColor(R.color.shudu_background));
        canvas.drawRect(0, 0, getWidth(), getHeight(), background_paint);

        Paint white=new Paint();
        white.setColor(getResources().getColor(R.color.shudu_hilite));

        Paint light=new Paint();
        light.setColor(getResources().getColor(R.color.shudu_light));

        Paint dark=new Paint();
        dark.setColor(getResources().getColor(R.color.shudu_dark));

        for(int i=0;i<9;i++)
        {
            //画出横向的线
            canvas.drawLine(0, i*height, getHeight(), i*height, light);
            canvas.drawLine(0, i*height+1, getHeight(), i*height+1, white);
            //画出纵向的线
            canvas.drawLine( i*width,0,  i*width,getHeight(), light);
            canvas.drawLine( i*width+1,0, i*width+1, getHeight(), white);
        }
        for(int i=0;i<9;i++)
        {
            if(i%3!=0)
            {
                continue;
            }
            canvas.drawLine(0, i*height, getHeight(), i*height, dark);
            canvas.drawLine(0, i*height+1, getHeight(), i*height+1, white);
            //画出纵向的线
            canvas.drawLine( i*width,0,  i*width,getHeight(), dark);
            canvas.drawLine( i*width+1,0, i*width+1, getHeight(), white);
        }

        Paint number_paint=new Paint();
        number_paint.setColor(Color.BLACK);
        //number_paint.setStyle(Paint.Style.STROKE);
        number_paint.setTextSize(height*0.75f);
        number_paint.setTextAlign(Paint.Align.CENTER);

        FontMetrics fm=number_paint.getFontMetrics();
        float x=width/2;
        float y=height/2-(fm.ascent+fm.descent)/2;



        for(int i=0;i<9;i++)
        {
            for(int j=0;j<9;j++)
            {
                canvas.drawText(game.getTileString(i, j), width*i+x, height*j+y, number_paint);
            }
        }

        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        if(event.getAction()!=MotionEvent.ACTION_DOWN)
        {
            return super.onTouchEvent(event);
        }
        selectedX=(int)(event.getX()/width);
        selectedY=(int)(event.getY()/height);

        int used[]=game.getUsedTileByCoor(selectedX, selectedY);
        int sum=0;
        int sumNumber=0;
        sumNumber=game.sum(game.getSudoku());
        if(sumNumber==45*9){
            AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
            builder.setMessage("Success!")
                    .setPositiveButton("Exit", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            arg0.cancel();

                        }
                    });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }
        else {
            for(int i=0;i<used.length;i++){
                sum+=used[i];
            }
            if(sum==45){
                AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
                builder.setMessage("No Number!")
                        .setNegativeButton("Exit", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                arg0.cancel();

                            }
                        });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
            else {
                StringBuffer sb=new StringBuffer();
                for(int i=0;i<used.length;i++)
                {
                    sb.append(used[i]);
//                            System.out.println(used[i]);
                }
                KeyDialog keyDialog= new KeyDialog(getContext(),used,this);
                keyDialog.show();
            }
        }

            /*StringBuffer sb=new StringBuffer();
            for(int i=0;i<used.length;i++)
            {
                    sb.append(used[i]);
//                    System.out.println(used[i]);
            }*/

//            //生成一个LayoutInflater对象
//            LayoutInflater layoutInflater=LayoutInflater.from(this.getContext());
//            //使用LayoutInflater对象根据一个布局文件，生成一个View
//            View layoutView=layoutInflater.inflate(R.layout.dialog, null);
//            //生成好的TextView当中，取出响应的控件
//            TextView textView=(TextView)layoutView.findViewById(R.id.usedTextId);
//            //设置Textview内容
//            textView.setText(sb.toString());
//            //生成一个对话框的Builder对象
//            AlertDialog.Builder builder=new AlertDialog.Builder(this.getContext());
//            //设置对话框的布局
//            builder.setView(layoutView);
//            //创建一个对话框
//            AlertDialog dialog=builder.create();
//            //显示对话框
//            dialog.show();
           /* KeyDialog keyDialog= new KeyDialog(getContext(),used,this);
            keyDialog.show();*/
        return true;
    }


    public void setSelectedTile(int tile) {
        // TODO Auto-generated method stub
        if(game.setTileIfValid(selectedX,selectedY,tile)){
            invalidate();
        }
    }

}

