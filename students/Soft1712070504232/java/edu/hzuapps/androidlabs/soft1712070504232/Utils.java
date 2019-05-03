package edu.hzuapps.androidlabs.soft1712070504232;


import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

public class Utils{
    public static void startAnim(RelativeLayout mImgUp, RelativeLayout mImgDn){
        AnimationSet animUp = new AnimationSet(true);
        TranslateAnimation start0 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,-0.5f);
        start0.setDuration(1000);
        TranslateAnimation start1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,+0.5f);
        start1.setDuration(1000);
        start1.setStartOffset(1000);
        animUp.addAnimation(start0);
        animUp.addAnimation(start1);
        mImgUp.setAnimation(animUp);

        AnimationSet animDn = new AnimationSet(true);
        TranslateAnimation end0 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,+0.5f);
        end0.setDuration(1000);
        TranslateAnimation end1 = new TranslateAnimation(Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,-0.5f);
        end1.setDuration(1000);
        end1.setStartOffset(1000);
        animDn.addAnimation(end0);
        animDn.addAnimation(end1);
        mImgDn.setAnimation(animDn);
    }
}
