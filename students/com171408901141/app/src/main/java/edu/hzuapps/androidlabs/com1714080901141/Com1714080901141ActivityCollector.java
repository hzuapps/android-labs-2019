package edu.hzuapps.androidlabs.com1714080901141;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class Com1714080901141ActivityCollector {
    public static List<Activity> activities=new ArrayList<>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    public static void finishAll(){
        for(Activity activity:activities){
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
        activities.clear();
        //android.os.Process.killProcess(android.os.Process.myPid());//kill all processes,exit without heritage
    }
}
