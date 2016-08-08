package com.rage.plantwateringapp;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Application class to initialize Joda Time.
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
    }
}
