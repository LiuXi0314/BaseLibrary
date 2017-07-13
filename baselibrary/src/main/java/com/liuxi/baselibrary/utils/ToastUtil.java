package com.liuxi.baselibrary.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

import com.liuxi.baselibrary.base.BaseApplication;

/**
 * Created by david on 17-4-12.
 */

public class ToastUtil {

    private static Context context = BaseApplication.getContext();

    private static Toast mToast;

    public static void showToast(String msg) {
        makeToast(msg, Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void showToast(int resId) {
        showToast(context.getString(resId));
    }

    private static void makeToast(String msg, int time) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, time);
        } else {
            mToast.setText(msg);
            mToast.setDuration(time);
        }
    }
}
