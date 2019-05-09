package edu.hzuapps.androidlabs.soft1714080902413.wuziqi;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

//public class GobangView extends View implements Runnable {
public class GobangView extends View {


    protected static int GRID_SIZE = 10;
    protected static int GRID_WIDTH = 100; // 棋盘格的宽度
    protected static int CHESS_DIAMETER = 65; // 棋的直径
    protected static int mStartX;// 棋盘定位的左上角X
    protected static int mStartY;// 棋盘定位的左上角Y

    private Bitmap[] mChessBW; // 黑棋和白棋
    private static int[][] mGridArray; // 网格

    boolean key = false;

    int wbflag = 1; //该下白棋了=2，该下黑棋了=1. 这里先下黑棋（黑棋以后设置为机器自动下的棋子）
    int mLevel = 1; //游戏难度
    int mWinFlag = 0;
    private final int BLACK=1;
    private final int WHITE=2;

    int mGameState = GAMESTATE_RUN; //游戏阶段：0=尚未游戏，1=正在进行游戏，2=游戏结束
    static final int GAMESTATE_PRE = 0;
    static final int GAMESTATE_RUN = 1;
    static final int GAMESTATE_PAUSE = 2;
    static final int GAMESTATE_END = 3;

    //private TextView mStatusTextView; //  根据游戏状态设置显示的文字
    public TextView mStatusTextView; //  根据游戏状态设置显示的文字

    private Bitmap btm1;
    private final Paint mPaint = new Paint();

    CharSequence mText;
    CharSequence STRING_WIN = "恭喜白棋取得胜利！";
    CharSequence STRING_LOSE = "恭喜黑棋取得胜利！";
    CharSequence STRING_EQUAL = "这局为平局！双方的实力旗鼓相当！";

    public GobangView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public GobangView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);

        init();
    }


    // 初始化黑白棋的Bitmap
    public void init() {
        mGameState = 1; //设置游戏为开始状态
        wbflag = BLACK; //初始为先下黑棋
        mWinFlag = 0; //清空输赢标志。
        mGridArray = new int[GRID_SIZE-1][GRID_SIZE-1];

        mChessBW = new Bitmap[2];

        Bitmap bitmap = Bitmap.createBitmap(CHESS_DIAMETER, CHESS_DIAMETER, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Resources r = this.getContext().getResources();

        Drawable tile = r.getDrawable(R.drawable.chess1);
        tile.setBounds(0, 0, CHESS_DIAMETER, CHESS_DIAMETER);
        tile.draw(canvas);
        mChessBW[0] = bitmap;

        tile = r.getDrawable(R.drawable.chess2);
        tile.setBounds(0, 0, CHESS_DIAMETER, CHESS_DIAMETER);
        tile.draw(canvas);
        mChessBW[1] = bitmap;
    }


    public void setTextView(TextView tv){
        mStatusTextView =tv;
        mStatusTextView.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mStartX = w / 2 - GRID_SIZE * GRID_WIDTH / 2;
        mStartY = h / 2 - GRID_SIZE * GRID_WIDTH / 2;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        switch (mGameState) {
            case GAMESTATE_PRE:
                break;
            case GAMESTATE_RUN: {
                int x;
                int y;
                float x0 = GRID_WIDTH - (event.getX() - mStartX) % GRID_WIDTH;
                float y0 = GRID_WIDTH - (event.getY() - mStartY) % GRID_WIDTH;
                if (x0 < GRID_WIDTH / 2) {
                    x = (int) ((event.getX() - mStartX) / GRID_WIDTH);
                } else {
                    x = (int) ((event.getX() - mStartX) / GRID_WIDTH) - 1;
                }
                if (y0 < GRID_WIDTH / 2) {
                    y = (int) ((event.getY() - mStartY) / GRID_WIDTH);
                } else {
                    y = (int) ((event.getY() - mStartY) / GRID_WIDTH) - 1;
                }
                if ((x >= 0 && x < GRID_SIZE - 1)
                        && (y >= 0 && y < GRID_SIZE - 1)) {
                    if (mGridArray[x][y] == 0) {
                        if (wbflag == BLACK) {
                            putChess(x, y, BLACK);
                            //this.mGridArray[x][y] = 1;
                            if(checkWin(BLACK)){ //如果是黑棋赢了
                                mText = STRING_LOSE;
                                mGameState = GAMESTATE_END;
                                showTextView(mText);
                            }else if(checkFull()){//如果棋盘满了
                                mText = STRING_EQUAL;
                                mGameState = GAMESTATE_END;
                                showTextView(mText);
                            }

                            wbflag = WHITE;
                        } else if (wbflag == WHITE) {
                            putChess(x, y, WHITE);
                            //this.mGridArray[x][y] = 2;
                            if(checkWin(WHITE)){//如果是白棋赢了
                                mText = STRING_WIN;
                                mGameState = GAMESTATE_END;
                                showTextView(mText);
                            }else if(checkFull()){//如果棋盘满了
                                mText = STRING_EQUAL;
                                mGameState = GAMESTATE_END;
                                showTextView(mText);
                            }
                            wbflag = BLACK;
                        }
                    }
                }
            }

            break;
            case GAMESTATE_PAUSE:
                break;
            case GAMESTATE_END:
                break;
        }

        this.invalidate();
        return true;

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent msg) {
        Log.e("KeyEvent.KEYCODE_DPAD_CENTER", " " + keyCode);

        if(keyCode == KeyEvent.KEYCODE_DPAD_CENTER){
            switch(mGameState){
                case GAMESTATE_PRE:
                    break;
                case GAMESTATE_RUN:
                    break;
                case GAMESTATE_PAUSE:
                    break;
                case GAMESTATE_END:
                {//游戏结束后，按CENTER键继续

                    Log.e("Fire Key Pressed:::", "FIRE");
                    mGameState = GAMESTATE_RUN;
                    this.setVisibility(View.VISIBLE);
                    this.mStatusTextView.setVisibility(View.INVISIBLE);
                    this.init();
                    this.invalidate();


                }
                break;
            }
        }

        return super.onKeyDown(keyCode, msg);
    }

    @Override
    public void onDraw(Canvas canvas) {


        // 画棋盘
        {
            Paint paintRect = new Paint();
            paintRect.setColor(Color.GRAY);
            paintRect.setStrokeWidth(7);
            paintRect.setStyle(Paint.Style.STROKE);

            for (int i = 0; i < GRID_SIZE; i++) {
                for (int j = 0; j < GRID_SIZE; j++) {
                    int mLeft = i * GRID_WIDTH + mStartX;
                    int mTop = j * GRID_WIDTH + mStartY;
                    int mRright = mLeft + GRID_WIDTH;
                    int mBottom = mTop + GRID_WIDTH;
                    canvas.drawRect(mLeft, mTop, mRright, mBottom, paintRect);
                }
            }

            //画棋盘的外边框
            paintRect.setStrokeWidth(4);
            canvas.drawRect(mStartX, mStartY, mStartX + GRID_WIDTH*GRID_SIZE, mStartY + GRID_WIDTH*GRID_SIZE, paintRect);
        }

        //画棋子

        for (int i = 0; i < GRID_SIZE-1; i++) {
            for (int j = 0; j < GRID_SIZE-1; j++) {
                if(mGridArray[i][j] == BLACK){
                    //通过图片来画
                    //canvas.drawBitmap(mChessBW[0], mStartX + (i+1) * GRID_WIDTH - CHESS_DIAMETER/2 , mStartY + (j+1)* GRID_WIDTH - CHESS_DIAMETER/2 , mPaint);

                    //通过圆形来画
                    {
                        Paint paintCircle = new Paint();
                        paintCircle.setColor(Color.BLACK);
                        canvas.drawCircle(mStartX + (i+1) * GRID_WIDTH, mStartY + (j+1)* GRID_WIDTH, CHESS_DIAMETER/2, paintCircle);
                    }

                }else if(mGridArray[i][j] == WHITE){
                    //通过图片来画
                    //canvas.drawBitmap(mChessBW[1], mStartX + (i+1) * GRID_WIDTH - CHESS_DIAMETER/2 , mStartY + (j+1)* GRID_WIDTH - CHESS_DIAMETER/2 , mPaint);

                    //通过圆形来画
                    {
                        Paint paintCircle = new Paint();
                        paintCircle.setColor(Color.WHITE);
                        canvas.drawCircle(mStartX + (i+1) * GRID_WIDTH, mStartY + (j+1)* GRID_WIDTH, CHESS_DIAMETER/2, paintCircle);
                    }
                }
            }
        }
    }

    public void putChess(int x, int y, int blackwhite){
        mGridArray[x][y] = blackwhite;
    }

    public boolean checkWin(int wbflag){
        for(int i = 0; i < GRID_SIZE - 1 ; i++ ) //i表示列(根据宽度算出来的)
            for(int j = 0; j < GRID_SIZE - 1; j++){//i表示行(根据高度算出来的)
                //检测横轴五个相连
                if(((i+4) < (GRID_SIZE - 1))&&
                        (mGridArray[i][j] == wbflag) && (mGridArray[i+1][j] == wbflag)&& (mGridArray[i + 2][j] == wbflag) && (mGridArray[i + 3][j] == wbflag) && (mGridArray[i + 4][j] == wbflag)){
                    Log.e("check win or loss:", wbflag + "win");

                    mWinFlag = wbflag;
                }

                //纵轴5个相连
                if(((j+4) < (GRID_SIZE - 1))&&
                        (mGridArray[i][j] == wbflag) && (mGridArray[i][j+1] == wbflag)&& (mGridArray[i ][j+ 2] == wbflag) && (mGridArray[i ][j+ 3] == wbflag) && (mGridArray[i ][j+ 4] == wbflag)){
                    Log.e("check win or loss:", wbflag + "win");

                    mWinFlag = wbflag;
                }

                //左上到右下5个相连
                if(((j+4) < (GRID_SIZE - 1))&& ((i+4) < (GRID_SIZE - 1)) &&
                        (mGridArray[i][j] == wbflag) && (mGridArray[i+1][j+1] == wbflag)&& (mGridArray[i + 2 ][j+ 2] == wbflag) && (mGridArray[i + 3][j+ 3] == wbflag) && (mGridArray[i + 4 ][j+ 4] == wbflag)){
                    Log.e("check win or loss:", wbflag + "win");

                    mWinFlag = wbflag;
                }

                //右上到左下5个相连
                if(((i-4) >= 0)&& ((j+4) < (GRID_SIZE - 1)) &&
                        (mGridArray[i][j] == wbflag) && (mGridArray[i-1][j+1] == wbflag)&& (mGridArray[i - 2 ][j+ 2] == wbflag) && (mGridArray[i - 3][j+ 3] == wbflag) && (mGridArray[i - 4 ][j+ 4] == wbflag)){
                    Log.e("check win or loss:", wbflag + "win");

                    mWinFlag = wbflag;
                }
            }

        if( mWinFlag == wbflag){
            return true;
        }else
            return false;


    }

    public boolean checkFull(){
        int mNotEmpty = 0;
        for(int i = 0; i < GRID_SIZE -1; i ++)
            for(int j = 0; j < GRID_SIZE - 1; j ++){
                if(mGridArray[i][j] != 0) mNotEmpty +=1;
            }

        if(mNotEmpty == (GRID_SIZE-1)*(GRID_SIZE-1)) return true;
        else return false;
    }

    public void showTextView(CharSequence mT){
        this.mStatusTextView.setText(mT);
        mStatusTextView.setVisibility(View.VISIBLE);

    }

}