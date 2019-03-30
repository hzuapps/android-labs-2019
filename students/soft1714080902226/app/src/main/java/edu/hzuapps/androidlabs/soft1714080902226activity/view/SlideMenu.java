package edu.hzuapps.androidlabs.soft1714080902226activity.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;

public class SlideMenu extends FrameLayout{
    private View menuView,mainView;
    private int menuWidth;
    private Scroller scroller;
    public SlideMenu(Context context) {
        super(context);
        init();
    }
    public SlideMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        scroller = new Scroller(getContext());
    }
    /**
     * 当1级子view全部加载完调用，可以用初始化子view引用
     * 注意这里无法获取子view的宽高
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        menuView = getChildAt(0);
        mainView = getChildAt(1);
        menuWidth = menuView.getLayoutParams().width;
    }
    //使Menu也具有滑动功能
    public boolean onInterceptTouchEvent(MotionEvent ev){
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = (int) (ev.getX() - downX);
                if (Math.abs(deltaX) > 8){
                    return true;
                }
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    /**
     * s设置两个子view在页面上的布局
     * @param l:当前子view的左边在父view的坐标系的x坐标
     * @param t:当前子view的顶边在父view的坐标系的y坐标
     * @param r:当前子view的宽
     * @param b:当前子view的高
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        menuView.layout(-menuWidth, 0, 0, b);
        mainView.layout(0, 0, r, b);
    }

    /**
     * 处理屏幕滑动事件
     */
    private int downX;
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = (int) event.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                int moveX = (int) event.getX();
                int deltaX = moveX - downX;
                int newScrollX = getScrollX() - deltaX;
                if (newScrollX < -menuWidth) newScrollX = -menuWidth;
                if (newScrollX > 0) newScrollX = 0;
                scrollTo(newScrollX, 0);
                downX = moveX;
                break;
            case MotionEvent.ACTION_UP:
                //当滑动距离小于Menu宽度的一半时，平滑滑动到主页面
                if(getScrollX()>-menuWidth/2){
                    closeMenu();
                }else {
                    //当滑动距离大于Menu宽度的一半时，平滑滑动到Menu页面
                    openMenu();
                }
                break;
        }
        return true;
    }
    //关闭menu
    private void closeMenu(){
        scroller.startScroll(getScrollX(),0,0-getScrollX(),0,400);
        invalidate();
    }
    //打开menu
    private void openMenu(){
        scroller.startScroll(getScrollX(),0,-menuWidth-getScrollX(),0,400);
        invalidate();
    }
    /**
     * Scroller不主动去调用这个方法
     * 而invalidate()可以调用这个方法
     * invalidate->draw->computeScroll
     */
    public void computeScroll(){
        super.computeScroll();
        if(scroller.computeScrollOffset()){
            //返回true,表示动画没结束
            scrollTo(scroller.getCurrX(),0);
            invalidate();
        }
    }
    /**
     * 切换菜单的开和关
     */
    public void switchMenu(){
        if(getScrollX()==0){
            openMenu();
        }else {
            closeMenu();
        }
    }
}
