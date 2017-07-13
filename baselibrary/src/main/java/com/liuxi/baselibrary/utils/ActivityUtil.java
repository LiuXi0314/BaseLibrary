package com.liuxi.baselibrary.utils;

import android.app.Activity;
import android.content.Context;

import com.liuxi.baselibrary.base.BaseActivity;

import java.util.Stack;

/**
 * 自定义Activity栈的管理类
 */

public class ActivityUtil {

    private Stack<BaseActivity> mActivityStack;//Activity栈，管理记录启动的activity

    private static ActivityUtil mInstance;//单例模式对象

    private ActivityUtil() {
        mActivityStack = new Stack<>();
    }

    public static ActivityUtil getInstance() {
        if (mInstance == null) {
            mInstance = new ActivityUtil();
        }
        return mInstance;
    }

    /**
     * 添加启动的activity
     */
    public void addActivity(BaseActivity activity) {
        mActivityStack.add(activity);
    }

    /**
     * 清除栈中记录的Activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activity == null) {
            mActivityStack.remove(activity);
        }
    }

    /**
     * 关闭并清除目标Activity
     *
     * @param activity
     */
    public void finishActivity(BaseActivity activity) {
        if (activity != null) {
            mActivityStack.remove(activity);
            if (!activity.isFinishing()) {
                activity.finish();
            }
            activity = null;
        }
    }

    /**
     * 关闭并清除栈中最后一个Activity
     */
    public void finishActivity() {
        finishActivity(mActivityStack.lastElement());
    }

    /**
     * 关闭并清除栈中指定类名的Activity
     */
    public void finishActivity(Class<?> classes) {
        for (BaseActivity activity : mActivityStack) {
            if (activity.getClass().equals(classes)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 清除栈中所有Activity
     */
    private void finishAllActivity() {

        for (int i = 0; i < mActivityStack.size(); i++) {
            if (mActivityStack.get(i) != null) {
                if (!mActivityStack.get(i).isFinishing()) {
                    mActivityStack.get(i).finish();
                }
            }
        }
        mActivityStack.clear();
    }

    /**
     * 结束APP,清除栈中所有Activity
     */
    public void exitApp(Context context) {
        finishAllActivity();
        try {
            //正常退出
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
            //非正常退出
            System.exit(1);
        }
    }

}
