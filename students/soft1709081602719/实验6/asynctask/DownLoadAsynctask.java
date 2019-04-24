package edu.hzuapps.androidlabs.asynctask;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import edu.hzuapps.androidlabs.adapter.DownloadAdapter;
import edu.hzuapps.androidlabs.bean.WeatherInfo;
import edu.hzuapps.androidlabs.util.HttpUtils;
import edu.hzuapps.androidlabs.util.ParserJsonUtils;

public class DownLoadAsynctask extends AsyncTask<String,Void,List<WeatherInfo>> {
    private Context context;
    private ListView lv;
    public DownLoadAsynctask (Context context,ListView lv){
        this.context = context;
        this.lv = lv;
    }
    @Override
    protected List<WeatherInfo> doInBackground(String... params) {
        byte[] buff = HttpUtils.getHttpResult(params[0]);
        String jsonString=null;
        if(buff!=null && buff.length!=0){
            jsonString = new String(buff,0,buff.length);
        }
        List<WeatherInfo> list = ParserJsonUtils.parserJsonToList(jsonString);
        return list;
    }

    @Override
    protected void onPostExecute(List<WeatherInfo> weatherInfos) {
        if(weatherInfos!=null && weatherInfos.size()!=0){
            DownloadAdapter adapter = new DownloadAdapter(weatherInfos,context);
            lv.setAdapter(adapter);
        }else {
            Log.i("Tag","下载异常！");
        }
    }
}
