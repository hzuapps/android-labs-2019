package edu.hzuapps.androidlabs.soft1714080902232;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Soft1714080902232Activity_GameView extends GridLayout {
    private Soft1714080902232Activity_Card cards[][] = new Soft1714080902232Activity_Card[4][4];
    private List<Point> emptyCards = new ArrayList<Point>();
    Random rd = new Random();
    int score = 0;

    public Soft1714080902232Activity_GameView(Context context) {
        super(context);
        initGame();
    }

    public Soft1714080902232Activity_GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initGame();
    }

    public Soft1714080902232Activity_GameView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initGame();
    }

    private void initGame() {
        setColumnCount(4);
        setBackgroundColor(0xffffcccc);

        setOnTouchListener(new View.OnTouchListener() {

            private float startX, startY;
            private float offsetX, offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:


                        Gameover();

                        offsetX = event.getX() - startX;
                        offsetY = event.getY() - startY;
                        if (Math.abs(offsetX) > Math.abs(offsetY)) {
                            if (offsetX < -3) {
                                moveLeft();
                                System.out.println("----左");
                            } else if (offsetX > 3) {
                                moveRight();
                                System.out.println("----右");
                            }
                        } else {
                            if (offsetY < -3) {
                                moveUp();
                                System.out.println("----上");
                            } else if (offsetY > 3) {
                                moveDown();
                                System.out.println("----下");
                            }
                        }

                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    private void moveRight() {

        boolean flage = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 3; x >= 0; x--) {
                for (int x1 = x - 1; x1 >= 0; x1--) {
                    if (cards[x1][y].getNumber() > 0) {
                        if (cards[x][y].getNumber() < 2) {
                            cards[x][y].setNumber(cards[x1][y].getNumber());
                            cards[x1][y].setNumber(0);
                            x++;
                            flage = true;
                            score += 2;
                        } else if (cards[x][y].getNumber() ==
                                cards[x1][y].getNumber()) {
                            cards[x][y].setNumber(cards[x][y].getNumber() * 2);
                            score += cards[x][y].getNumber();
                            cards[x1][y].setNumber(0);
                            flage = true;
                        }
                        break;
                    }
                }
            }
        }
        if (flage) {
            creatRandomCard();
        }
    }

    private void moveLeft() {

        boolean flage = false;
        for (int y = 0; y < 4; y++) {
            for (int x = 0; x < 4; x++)
                for (int x1 = x + 1; x1 < 4; x1++) {
                    if (cards[x1][y].getNumber() > 0) {
                        if (cards[x1][y].getNumber() > 0) {
                            if (cards[x][y].getNumber() < 2) {
                                cards[x][y].setNumber(cards[x1][y].getNumber());
                                cards[x][y].setNumber(0);
                                x--;
                                flage = true;
                                score += 2;
                            } else if (cards[x][y].getNumber() == cards[x1][y].getNumber()) {
                                cards[x][y].setNumber(cards[x][y].getNumber() * 2);
                                score += cards[x][y].getNumber();
                                cards[x1][y].setNumber(0);
                                flage = true;
                            }
                            break;
                        }
                    }
                }
        }

        if (flage) {
            creatRandomCard();
        }
    }

    private void moveDown() {
        boolean flage = false;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; x < 4; x++) {
                for (int y1 = y + 1; y1 < 4; y1++) {
                    if (cards[x][y1].getNumber() > 0) {
                        if (cards[x][y].getNumber() < 2) {
                            cards[x][y].setNumber(cards[x][y1].getNumber());
                            cards[x][y1].setNumber(0);
                            y--;
                            flage = true;
                            score += 2;
                        } else if (cards[x][y].getNumber() == cards[x][y1].getNumber()) {
                            cards[x][y].setNumber(cards[x][y].getNumber() * 2);
                            score += cards[x][y].getNumber();
                            cards[x][y1].setNumber(0);
                            flage = true;
                        }
                        break;
                    }
                }
            }
        }
        if (flage) {
            creatRandomCard();
        }

    }
    private void moveUp() {
        boolean flage = false;
        for (int x = 0; x <4; x++) {
            for (int y =0; y < 4; y++) {
                for (int y1 = y + 1;y1 < 4; y1++) {
                    if (cards [x] [y1].getNumber() > 0) {
                        if (cards [x] [y].getNumber() < 2) {
                            cards [x] [y].setNumber(cards [x] [y1].getNumber());
                            cards [x] [y1].setNumber(0);
                            y--;
                            flage = true;
                            score+=2;
                        }else if (cards [x] [y].getNumber() == cards [x] [y1].getNumber()) {
                            cards [x] [y].setNumber(cards [x] [y].getNumber() * 2);
                            score +=cards [x] [y].getNumber();
                            cards [x] [y1].setNumber(0);
                            flage =true;
                        }
                        break;
                    }
                }
            }
        }
        if (flage) {
            creatRandomCard();
        }
    }

    private void Gameover() {
        boolean OverGame=true;
        for (int y = 0;y < 4;y++) {
            for (int x = 0; x < 4; x++) {
                if(cards[x] [y].getNumber() <= 0 ||
                        (x > 0 && cards[x] [y].getNumber() ==
                                cards[x-1] [y].getNumber()) ||
                        (x < 3 && cards[x] [y].getNumber() ==
                                cards[x+1] [y].getNumber()) ||
                        (y > 0 && cards[x] [y].getNumber() ==
                                cards[x] [y-1].getNumber()) ||
                        (y < 3 && cards[x] [y].getNumber() ==
                                cards[x] [y+1].getNumber())
                ){
                    OverGame=false;
                }
            }
        }
        if(OverGame){
            new AlertDialog.Builder(getContext ()).setTitle("hi").setMessage("again").
                    setPositiveButton("yes",new AlertDialog.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            GameStart();
                            score = 0;
                        }
                    }).setNegativeButton("No", null).show();
        }
    }

    private void AddCard(int width, int height) {
        Soft1714080902232Activity_Card c;
        for (int y = 0;y < 4; y++) {
            for(int x =0;x < 4; x++) {
                c =new Soft1714080902232Activity_Card(getContext());
                cards [x] [y] = c;
                c.setNumber (0);
                addView(c, width, height);
            }
        }
    }

    @Override
    protected void onSizeChanged(int w,int h,int oldw,int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int width = (w -10) / 4;
        AddCard(width, width);
        GameStart();
    }
    private void creatRandomCard() {
        emptyCards.clear();
        for (int y = 0; y < 4; y++) {
            for(int x = 0; x < 4; x++) {
                if (cards [x] [y].getNumber() < 2) {
                    Point point = new Point(x,y);
                    emptyCards.add(point);
                }
            }
        }
        int selat = rd.nextInt(emptyCards.size());
        Point p = emptyCards.get(selat);
        emptyCards.remove(selat);
        int number = 0;
        if (rd.nextInt(10) > 4) {

            number = 4;
        }else
            number = 2;
        cards[p.x] [p.y].setNumber(number);
    }

    public void GameStart() {
        for (int y =0; y < 4; y++) {
            for (int x =0; x < 4; x++) {
                cards [x] [y].setNumber(0);
            }
        }
        creatRandomCard();
        creatRandomCard();
    }
}
