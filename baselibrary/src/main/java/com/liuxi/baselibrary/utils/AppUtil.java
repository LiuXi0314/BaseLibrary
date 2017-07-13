package com.liuxi.baselibrary.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.liuxi.baselibrary.base.BaseApplication;

/**
 * Created by david on 17-4-13.
 */

public class AppUtil {
    /**
     * 返回应用名称
     *
     * @return
     */
    public static String getAppName() {
        PackageManager packageManager = BaseApplication.getContext().getApplicationContext().getPackageManager();
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = packageManager.getApplicationInfo(BaseApplication.getContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return String.valueOf(packageManager.getApplicationLabel(applicationInfo));
    }
}
