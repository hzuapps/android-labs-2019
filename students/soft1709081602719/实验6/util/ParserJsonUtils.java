package edu.hzuapps.androidlabs.util;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.hzuapps.androidlabs.bean.WeatherInfo;

/**
 *json解析工具类
 */
public class ParserJsonUtils {
    public static List<WeatherInfo> parserJsonToList(String jsonString){
        List<WeatherInfo> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for(int i = 0;i<jsonArray.length();i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                WeatherInfo info = new WeatherInfo();
                info.setDate(obj.getString("date"));
                info.setIcon(obj.getString("icon"));
                info.setTemperature(obj.getString("temperature"));
                info.setWeather(obj.getString("weather"));
                info.setWinddirect(obj.getString("winddirect"));
                list.add(info);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
