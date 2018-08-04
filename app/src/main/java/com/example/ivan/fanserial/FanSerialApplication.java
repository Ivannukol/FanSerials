package com.example.ivan.fanserial;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class FanSerialApplication extends Application {
    private static volatile Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
    public static  boolean isConnectedToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager)getInstanse().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }

        }
        return false;
    }
    public static Context getInstanse(){
        return context;
    }
}
