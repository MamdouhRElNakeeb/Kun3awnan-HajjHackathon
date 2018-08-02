package com.kun3awnan.app.helper;

import android.app.Application;
import android.support.multidex.MultiDex;

public class MainApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
    }
}
