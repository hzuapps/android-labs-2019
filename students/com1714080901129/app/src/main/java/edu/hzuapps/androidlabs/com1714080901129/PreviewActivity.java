package edu.hzuapps.androidlabs.com1714080901129;

import android.database.Observable;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import com.google.gson.Gson;
import com.yzk.ordis.dao.apiInterface;
import com.yzk.ordis.util.*;
import com.yzk.ordis.bean.*;


import org.reactivestreams.Subscriber;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class PreviewActivity extends AppCompatActivity {

    private TextView Time,ShortString,fortuna_time,fortuna_shortString;
    private ImageView Day,weather;
    long h=0,m=0,s=0;
    String str_time,hour,min;
    private CountDownTimer cetusTimer,fortunaTimer;
    CetusTime cetusTime =new CetusTime();
    FortunaTime fortunaTime=new FortunaTime();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        Day=(ImageView)findViewById(R.id.cetus_day);
        Time=(TextView)findViewById(R.id.cetus_time);
        ShortString=(TextView)findViewById(R.id.cetus_shortString);
        weather=(ImageView)findViewById(R.id.fortuna_weather);
        fortuna_time=(TextView)findViewById(R.id.fortuna_time);
        fortuna_shortString=(TextView)findViewById(R.id.fortuna_shortString);

        apiInterface apiInterface=new Retrofit.Builder()
                                    .baseUrl("https://api.warframestat.us/")
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                                    .build().create(com.yzk.ordis.dao.apiInterface.class);
        io.reactivex.Observable<CetusTime> cetusCycle=apiInterface.getCetusTime();
        io.reactivex.Observable<FortunaTime> vallisCycle=apiInterface.getVallisCycle();
        io.reactivex.Observable.merge(cetusCycle,vallisCycle)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                            if(o instanceof CetusTime){
                                Update((CetusTime) o);
                            }else if(o instanceof  FortunaTime){
                                Update((FortunaTime) o);
                            }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                //从网络获取数据
//                NetWorkOpreation();
//
//                //向Handler发送处理操作
//
//            }
//        }).start();

//

    }
//    private void NetWorkOpreation(){
//        try {
//            httpUtil.sendOkHttpRequest(cetus_Url, new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    String responseData = response.body().string();
//                    Gson gson=new Gson();
//                    cetusTime=gson.fromJson(responseData,CetusTime.class);
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Update();
//                        }
//                    });
//
//
//                }
//
//
//            });
//            httpUtil.sendOkHttpRequest(fortuna_Url, new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    String responseData=response.body().string();
//                    Gson gson=new Gson();
//                    fortunaTime=gson.fromJson(responseData,FortunaTime.class);
//
//                }
//            });
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public <T> void Update(T o)
    {
            if(o instanceof CetusTime){
                cetusTime=(CetusTime) o;
                setTimer(o);
                if(cetusTime.isIsDay()){
                    Day.setImageResource(R.drawable.sun);
                }else Day.setImageResource(R.drawable.moon);
            }else if(o instanceof FortunaTime){
                fortunaTime=(FortunaTime) o;
                setTimer(fortunaTime);
                if(fortunaTime.isIsWarm()){
                    weather.setImageResource(R.drawable.sun);
                }else weather.setImageResource(R.drawable.snow);
            }
    }
    public <T> void setTimer(T o){
        if(o instanceof CetusTime){
            cetusTime=(CetusTime)o;
            str_time=cetusTime.getTimeLeft();
            long time=Time(str_time);
            cetusTimer=new CountDownTimer(time,100) {
                @Override
                public void onTick(long millisUntilFinished) {
                    Time.setText(longPointDate(millisUntilFinished));
                    hour=longPointDate(millisUntilFinished).substring(0,2);
                    min=longPointDate(millisUntilFinished).substring(3,5);
                    if(!hour.equals("00")){
                        ShortString.setText("1h "+min+cetusTime.getShortString().substring(cetusTime.getShortString().indexOf("m")));
                    }else ShortString.setText(min+cetusTime.getShortString().substring(cetusTime.getShortString().indexOf("m")));
                }

                @Override
                public void onFinish() {
                    cetusTimer.start();
                    Log.e("test","test");
                }
            };
            cetusTimer.start();
        }else if(o instanceof  FortunaTime){
            fortunaTime=(FortunaTime)o;
            str_time=fortunaTime.getTimeLeft();
            long time=Time(str_time);
            fortunaTimer=new CountDownTimer(time,100) {
                @Override
                public void onTick(long l) {
                    fortuna_time.setText(longPointDate(l));
                    hour=longPointDate(l).substring(0,2);
                    min=longPointDate(l).substring(3,5);
                    fortuna_shortString.setText(min+fortunaTime.getShortString().substring(fortunaTime.getShortString().indexOf("m")));
                }

                @Override
                public void onFinish() {

                }

            };
            fortunaTimer.start();
        }
    }
    protected void onDestroy() {
        super.onDestroy();
        if (cetusTimer != null) {
            cetusTimer.cancel();
            cetusTimer = null;
        }
        if(fortunaTimer !=null){
            fortunaTimer.cancel();
            fortunaTimer =null;
        }

    }
    public static String longPointDate(long lo){
        Date date = new Date(lo);
        SimpleDateFormat sd = new SimpleDateFormat("HH:mm:ss");
        return sd.format(date);
    }
    public long Time(String time){
        String[] str=time.split(" ");
        int day=0;
        for(String string:str){
            int n;
            if((n=string.indexOf("d"))!=-1||(n=string.indexOf("D"))!=-1){
                day=Integer.parseInt(string.substring(0,n));
                continue;
            }
            if((n=string.indexOf("h"))!=-1||(n=string.indexOf("H"))!=-1){
                h=Integer.parseInt(string.substring(0,n));
                continue;
            }
            if((n=string.indexOf("m"))!=-1||(n=string.indexOf("M"))!=-1){
                m=Integer.parseInt(string.substring(0,n));
                continue;
            }
            if((n=string.indexOf("s"))!=-1||(n=string.indexOf("S"))!=-1){
                s=Integer.parseInt(string.substring(0,n));
            }
        }
        return (long)(day*86400+h*3600+m*60+s)*1000;
    }
}

