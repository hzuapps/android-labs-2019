package com.example.menu;

import android.app.Application;

import com.example.menu.Model.CaiPu;

import java.util.ArrayList;
import java.util.List;

public class DataApplication extends Application {

    private static List<CaiPu>caiPuList=new ArrayList<>();

    public List<CaiPu> GetDataList(){
        return caiPuList;
    }

    public void SetDataList(List<CaiPu>m_caiPuList){
        caiPuList.clear();
        caiPuList.addAll(m_caiPuList);
    }
}
