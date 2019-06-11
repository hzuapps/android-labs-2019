package edu.hzuapps.androidlabs.soft1714080902333.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import edu.hzuapps.androidlabs.soft1714080902333.bean.NewsInfo;

public class HttpUtils {
    public  static List<NewsInfo> getNewsInfo(String json) {
        Gson gson = new Gson();

        Type listType = new TypeToken<List<NewsInfo>>(){
        }.getType();

        List<NewsInfo> newsInfos = gson.fromJson(json, listType);

        return newsInfos;
    }
}
