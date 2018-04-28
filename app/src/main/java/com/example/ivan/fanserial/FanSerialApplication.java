package com.example.ivan.fanserial;

import android.app.Application;
import android.content.Context;

public class FanSerialApplication extends Application {
    private static volatile Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getInstanse(){
        return context;
    }
}
