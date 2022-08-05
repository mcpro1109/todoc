package com.cleanup.todoc;

import android.app.Application;

public class TodocApp extends Application {
    public  static  Application app;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
    }
}
