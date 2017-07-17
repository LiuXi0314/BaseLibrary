package com.liuxi.baselibrary.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

/**
 * 监听app中所有Activity的生命周期
 * Created by david on 17-6-20.
 */

public class BaseActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks{

    private static BaseActivityLifecycleCallbacks mInstance;


    public static BaseActivityLifecycleCallbacks getInstance() {
        if (mInstance == null) {
            mInstance = new BaseActivityLifecycleCallbacks();
        }
        return mInstance;
    }

    private Handler handler = new Handler();
    private Runnable mCheck;
    private int mShowActivityCount;
    private boolean isForeground = true;
    private List<OnForegroundChangedListener> listeners = new ArrayList<>();

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    public void onActivityStarted(Activity activity) {
    }

    @Override
    public void onActivityResumed(Activity activity) {
        mShowActivityCount += 1;
        if (!isForeground) {
            for (OnForegroundChangedListener listener : listeners) {
                if (listener != null) {
                    listener.toForeground();
                }
            }
            isForeground = true;
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        mShowActivityCount -= 1;
        if (handler != null) {
            handler.removeCallbacks(mCheck);
        }
        mCheck = new Runnable() {
            @Override
            public void run() {
                if (mShowActivityCount == 0) {
                    isForeground = false;
                    for (OnForegroundChangedListener listener : listeners) {
                        if (listener != null) {
                            listener.toBackground();
                        }
                    }
                } else {
                    isForeground = true;
                }
            }
        };
        handler.postDelayed(mCheck, 500);
    }

    @Override
    public void onActivityStopped(Activity activity) {
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
    }

    public interface OnForegroundChangedListener {

        public void toForeground();

        public void toBackground();
    }

    public void addOnForegroundChangedListener(OnForegroundChangedListener listener) {
        listeners.add(listener);
    }

    public void removeOnForegroundChangedListener(OnForegroundChangedListener listener) {
        listeners.remove(listener);
    }

}
