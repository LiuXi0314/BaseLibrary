package com.liuxi.baselibrary.utils;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 17-4-13.
 */

public class FormatUtil {

    public static boolean isEmpty(String string) {
        if (string == null) {
            return true;
        }
        if (TextUtils.isEmpty(string)) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String string) {
        if (string == null) {
            return false;
        }
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        return true;
    }


    public static String string(String text) {
        return isNotEmpty(text) ? text : "";
    }

    public static int string2int(String text) {
        try {
            return Integer.valueOf(text);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    public static String getResourceName(Context context, int resId) {
        try {
            return context.getResources().getResourceEntryName(resId);
        } catch (Exception e) {
            return "";
        }
    }

    public static String encode(String source) {
        return Uri.encode(source);
    }

    public static String decode(String source) {
        return Uri.decode(source);
    }

    public static JSONArray list2JsonArray(List<String> stringList, String key) throws JSONException {
        if (stringList == null || stringList.isEmpty() || isEmpty(key)) return null;
        JSONArray jsonArray = new JSONArray();
        for (String s : stringList) {
            JSONObject jo = new JSONObject();
            jo.put(key, s);
            jsonArray.put(jo);
        }
        return jsonArray;
    }

    public static String list2String(List<String> stringList) {
        if (stringList == null || stringList.isEmpty()) return "";
        StringBuilder builder = new StringBuilder();
        int i = 0;
        for (String str : stringList) {
            if (i != 0)
                builder.append(",");
            builder.append(encode(str));
            i++;
        }
        return builder.toString();
    }

    public static List<String> string2List(String string) {
        if (isEmpty(string)) return null;
        List<String> stringList = new ArrayList<>();
        String[] strArray = string.split(",");
        for (String str : strArray) {
            stringList.add(decode(str));
        }
        return stringList;
    }
}
