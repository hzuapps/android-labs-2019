package com.example.soft1706081301317sweepacticity;

import android.app.Application;

import java.util.ArrayList;
import java.util.Map;

public class allList extends Application {



    public static ArrayList<Map<String, Object>> list=new ArrayList<Map<String,Object>>();
    public static ArrayList<Map<String, Object>> getList() {
        return list;
    }

    public static void setList(ArrayList<Map<String, Object>> list) {
        allList.list = list;
    }
}
