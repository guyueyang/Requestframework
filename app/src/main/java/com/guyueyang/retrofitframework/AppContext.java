package com.guyueyang.retrofitframework;

import android.app.Application;

/**
 * Created by Administrator on 2017/8/3.
 */
public class AppContext extends Application {
    private static AppContext instance;

    public static AppContext getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
