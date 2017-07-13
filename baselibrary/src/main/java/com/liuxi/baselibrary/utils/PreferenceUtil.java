package com.liuxi.baselibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferenceUtil {

    public final static String COMMON_NAME = "common";
    private final int MODE_PRIVATE = 0;//私有
    private final int MODE_PUBLIC = 1;//公有

    private SharedPreferences mPref = null;
    private static PreferenceUtil mUtil = null;

    private PreferenceUtil(Context context, String prefName) {
        mPref = context.getSharedPreferences(prefName, MODE_PRIVATE);
    }

    private PreferenceUtil(Context context, String prefName, int mode) {
        mPref = context.getSharedPreferences(prefName, mode);
    }

    public static PreferenceUtil getPreference(Context context, String prefName) {
        if (mUtil == null) {
            mUtil = new PreferenceUtil(context, prefName);
        } else {
            mUtil.setPreference(context, prefName);
        }
        return mUtil;
    }

    public static PreferenceUtil getPreference(Context context, String prefName, int mode) {
        if (mUtil == null) {
            mUtil = new PreferenceUtil(context, prefName, mode);
        } else {
            mUtil.setPreference(context, prefName);
        }
        return mUtil;
    }

    private void setPreference(Context context, String name) {
        mPref = context.getSharedPreferences(name, MODE_PRIVATE);
    }

    public Boolean get(String name, boolean def) {
        return mPref.getBoolean(name, def);
    }

    public String get(String name, String def) {
        return mPref.getString(name, def);
    }

    public void add(String name, boolean value) {
        Editor editor = mPref.edit();
        editor.putBoolean(name, value);
        editor.commit();
    }

    public void add(String name, String value) {
        Editor editor = mPref.edit();
        editor.putString(name, value);
        editor.commit();
    }
}
