package edu.hzuapps.androidlabs.soft1714080902431;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* DisplayMetrics metrics =new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);//sdk17+
        int screenWidth = metrics.widthPixels;//屏幕宽
        int screenHeight = metrics.heightPixels;//屏幕高

        Bitmap back=Bitmap.createScaledBitmap(bitmap,
                MainActivity.getScreenWidth(),
                MainActivity.getScreenHeight(),
                true);

         private final int COL=3;//列，默认3列
         private final int ROW=3;//行，默认3行
         int tileWidth=back.getWidth()/COL;//每一块的宽
         int tileHeight=back.getHeight()/ROW;//每一块的高
         Bitmap[] bitmapTiles =new Bitmap[COL*ROW];
         int idx=0;
         for(int i=0;i<ROW;i++) {
            for(int j=0;j<COL;j++) {
                bitmapTiles[idx++]=Bitmap.createBitmap(back, j*tileWidth, i*tileHeight, tileWidth,tileHeight);
            }
         }

        private void createIntegerArray(int row,int col) {
             array=new int[row][col];
             int idx=0;
             for(int i=0;i<row;i++)
                 for(int j=0;j<col;j++)
                     array[i][j]=idx++;
         }

        //四个方向 private int[][] dir={ {0,1},//下 {1,0},//右 {0,-1},//上 {-1,0}//左 }; /**

        private Point move(int srcX,int srcY,int xOffset,int yOffset) {
            int x=srcX+xOffset;
            int y=srcY+yOffset;
            if(x<0||y<0||x>=col||y>=row)
                return new Point(-1,-1);
                int temp=array[y][x];
                array[y][x]=array[srcY][srcX];
                array[srcY][srcX]=temp; return new Point(x,y);
     }
   private android.graphics.Point getNextPoint(Point src) { Random rd=new Random(); int idx=rd.nextInt(4);//，因为有4个方向，所以产生0~3的随机数 int xOffset=dir[idx][0]; int yOffset=dir[idx][1]; Point newPoint=move(src.getX(),src.getY(),xOffset,yOffset); if(newPoint.getX()!=-1&&newPoint.getY()!=-1) { return newPoint;//找到了新的点 } return getNextPoint(src);//没有找到，继续 } /**

            public int[][] createRandomBoard(int row,int col) {
                 if(row<2||col<2)
                     throw new IllegalArgumentException("行和列都不能小于2");
                 this.row=row;
                 this.col=col;
                 createIntegerArray(row,col);//初始化拼图数据
                 int count=0; Point tempPoint=new Point(col-1,row-1);//最后一块是空白块
                Random rd=new Random(); int num=rd.nextInt(100)+20;//产生20~119的随机数，表示重复的次数 while (count<num) { tempPoint=getNextPoint(tempPoint);//获得下个点，并更新空白块位置 count++; } return array; }

    }*/
    }
}