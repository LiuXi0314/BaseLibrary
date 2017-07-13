package com.liuxi.baselibrary.utils;

import android.util.Log;

/**
 * Created by david on 17-4-12.
 */

public class LogUtil {

    private static final String TAG = AppUtil.getAppName();

    public static boolean LogEnable = true;

    public LogUtil() {
    }

    public static void v(String string) {
        if (LogEnable) {
            Log.v(TAG, string);
        }
    }

    public static void d(String string) {
        if (LogEnable) {
            Log.d(TAG, string);
        }
    }

    public static void e(String string) {
        if (LogEnable) {
            Log.e(TAG, string);
        }
    }

    public static void api(String string) {
        if (LogEnable) {
            Log.d(TAG, string);
        }
    }
}
