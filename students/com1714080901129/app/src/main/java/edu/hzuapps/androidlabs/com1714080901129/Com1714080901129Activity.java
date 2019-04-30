package edu.hzuapps.androidlabs.com1714080901129;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzk.ordis.bean.CetusTime;
import com.yzk.ordis.bean.FortunaTime;
import com.yzk.ordis.dao.apiInterface;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Com1714080901129Activity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
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
        setContentView(R.layout.activity_com1714080901129);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(" ");//设置toolbar本来的标题为空
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        ActionBarDrawerToggle drawerToggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,0,0){

        };
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();//图标和侧边栏同步

        Intent intent=getIntent();
        String path=intent.getStringExtra("pic");
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        if(path!=null)Log.i("path", path);
        ConstraintLayout pre_layout=(ConstraintLayout)findViewById(R.id.preview_layout);
        Drawable drawable=new BitmapDrawable(getResources(),bitmap);
        pre_layout.setBackground(drawable);

        Day=(ImageView)findViewById(R.id.cetus_day);
        Time=(TextView)findViewById(R.id.cetus_time);
        ShortString=(TextView)findViewById(R.id.cetus_shortString);
        weather=(ImageView)findViewById(R.id.fortuna_weather);
        fortuna_time=(TextView)findViewById(R.id.fortuna_time);
        fortuna_shortString=(TextView)findViewById(R.id.fortuna_shortString);

        verifyInternetPermissions(this);

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
        NavigationView navigationView=(NavigationView)findViewById(R.id.left_draw);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.sorite){
                    Intent intent=new Intent(Com1714080901129Activity.this, SortieActivity.class);
                    startActivity(intent);
                }else if(id==R.id.camera){
                    Intent intent=new Intent(Com1714080901129Activity.this, CameraActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });
    }

    private static final int REQUEST_EXTERNAL_INTERNET = 1;
    private static String[] PERMISSIONS_INTERNET = {
            "android.permission.INTERNET"};

    public static void verifyInternetPermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = android.support.v4.app.ActivityCompat.checkSelfPermission(activity,
                    "android.permission.INTERNET");
            if (permission != android.content.pm.PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                android.support.v4.app.ActivityCompat.requestPermissions(activity, PERMISSIONS_INTERNET,REQUEST_EXTERNAL_INTERNET);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_com1714080901129, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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
                    min=longPointDate(l).substring(3,5);
                    if(!min.equals("00"))
                    fortuna_shortString.setText(min+fortunaTime.getShortString().substring(fortunaTime.getShortString().indexOf("m")));
                    else fortuna_shortString.setText(fortunaTime.getShortString());
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
        sd.setTimeZone(TimeZone.getTimeZone("GMT+0"));
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
