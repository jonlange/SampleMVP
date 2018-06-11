package com.test.restingrobot.testapplication.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by Jon Lange, 6/11/18
 */

public class TestApplication extends Application {

    public static Context fAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        fAppContext = this;
        //startApplication();
    }

    public static Context getContext() {
        return fAppContext;
    }

}
