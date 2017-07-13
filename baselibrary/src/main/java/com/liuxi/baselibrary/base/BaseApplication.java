package com.liuxi.baselibrary.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by david on 17-4-10.
 */

public class BaseApplication extends Application {

    private static BaseApplication mInstance;

    public static Context getContext() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

}
