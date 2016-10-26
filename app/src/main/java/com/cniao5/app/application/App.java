package com.cniao5.app.application;

import android.app.Application;

public class App extends Application {
    private static App instance = null;

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
    }

    public static App getInstance() {
        return instance;
    }
}
