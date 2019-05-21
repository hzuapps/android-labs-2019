package com.example.shiyan7;

import android.app.Activity;
import android.media.SoundPool;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    public static void startAnim(RelativeLayout mImgUp,RelativeLayout mImgDn){
        AnimationSet animUp = new AnimationSet(true);
        TranslateAnimation start0 = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,
                Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,
        -0.5f);
        start0.setDuration(1000);
        TranslateAnimation start1 = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,
                Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,
                +0.5f);
        start1.setDuration(1000);
        start1.setStartOffset(1000);
        animUp.addAnimation(start0);
        animUp.addAnimation(start1);
        mImgUp.startAnimation(animUp);

        AnimationSet animDn = new AnimationSet(true);
        TranslateAnimation end0 = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,
                Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,
                +0.5f);
        end0.setDuration(1000);
        TranslateAnimation end1 = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f,
                Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,
                -0.5f);
        end1.setDuration(1000);
        end1.setStartOffset(1000);
        animDn.addAnimation(end0);
        animDn.addAnimation(end1);
        mImgDn.startAnimation(animDn);
    }
    public static Map<Integer,Integer> loadSound(final SoundPool pool, final Activity context){
        final Map<Integer,Integer> soundPoolMap = new HashMap<Integer, Integer>();
        new Thread(){
            public void run(){
                try {
                    soundPoolMap.put( 0,pool.load(context.getAssets().openFd("sound/shake_sound_male.mp3"),1));
                    soundPoolMap.put( 1,pool.load(context.getAssets().openFd("sound/shake_match.mp3"),1));
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }.start();
        return soundPoolMap;
    }
}
