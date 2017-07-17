package com.liuxi.baselibrary.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * 获取设备相关信息
 */

public class DeviceUtils {

    public static int width(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int height(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static String getDeviceId(Context context) {
        String iMei = getImei(context);
        if (FormatUtil.isEmpty(iMei)) {
            iMei = getAndroidId(context);
        }
        return iMei;
    }

    public static String getImei(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

    public static String getAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static boolean isWifi(Context context) {
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State wifi = conMan.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState();
        if (NetworkInfo.State.CONNECTED != wifi) {
            return false;
        }
        return true;
    }

    public static boolean isAvailable(Context context) {
        ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = conMan.getActiveNetworkInfo();
        if (NetworkInfo.State.CONNECTED != info.getState()) {
            return false;
        }
        return true;
    }

    public static boolean isPad(Context context) {
        return isScreenSizeOver6(context) && isLargeSizeScreen(context);
    }

    public static boolean isMiUi() {
        Build build = new Build();
        return build.MANUFACTURER.toLowerCase().equals("xiaomi");
    }

    private static boolean isLargeSizeScreen(Context context) {
        return (context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    private static boolean isScreenSizeOver6(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        // 屏幕宽度
        float screenWidth = display.getWidth();
        // 屏幕高度
        float screenHeight = display.getHeight();
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        double x = Math.pow(dm.widthPixels / dm.xdpi, 2);
        double y = Math.pow(dm.heightPixels / dm.ydpi, 2);
        // 屏幕尺寸
        double screenInches = Math.sqrt(x + y);
        // 大于6尺寸则为Pad
        if (screenInches >= 6.0) {
            return true;
        }
        return false;
    }
}
