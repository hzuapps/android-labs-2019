package com.yzk.ordis.dao;

import com.yzk.ordis.bean.CetusTime;
import com.yzk.ordis.bean.FortunaTime;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface apiInterface {
    @GET("pc/cetusCycle")
    Observable<CetusTime> getCetusTime();

    @GET("pc/vallisCycle")
     Observable<FortunaTime> getVallisCycle();
}
