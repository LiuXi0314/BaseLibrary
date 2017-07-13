package com.liuxi.baselibrary.utils;

import android.content.Intent;
import android.net.Uri;

import com.liuxi.baselibrary.bean.UrlParam;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UrlUtils {

    public static String getUrlHead(String url){
        int firstIndex = url.indexOf('?');
        firstIndex = firstIndex == -1 ? url.length() : firstIndex+1;
        return url.substring(0, firstIndex);
    }

    public static Map<String, String> getUrlParams(String url){
        int firstIndex = url.indexOf('?');
        if(firstIndex == -1) return null;
        return parseStringParamsToMap(url.substring(firstIndex+1));
    }

    public static String parseUrlParamsToString(String url){
        int firstIndex = url.indexOf('?');
        if(firstIndex > -1){
            return url.substring(firstIndex+1, url.length());
        }
        return "";
    }

    public static String parseUrlWithoutParams(String url){
        int firstIndex = url.indexOf('?');
        if(firstIndex > -1){
            return url.substring(0, firstIndex+1);
        }
        return url;
    }

    public static Map parseStringParamsToMap(String paramStr){
        Map paramMap = new HashMap<>();
        if(FormatUtil.isEmpty(paramStr)) return paramMap;
        String[] paramAry = paramStr.split("&");
        if(paramAry == null || paramAry.length == 0) return paramMap;
        int size = paramAry.length;
        for(int i=0;i < size;i ++){
            String paramItemStr = paramAry[i];
            if(FormatUtil.isEmpty(paramItemStr)) continue;
            String[] kv = paramItemStr.split("=");
            if(kv == null || kv.length < 2) continue;
            paramMap.put(kv[0], getDecodeParamValue(kv[1]));
        }
        return paramMap;
    }

    private static String getDecodeParamValue(String value){
        String decodeValue = FormatUtil.decode(value);
        if(decodeValue.equals(value)){
            return decodeValue.trim();
        }else{
            return FormatUtil.encode(decodeValue.trim());
        }
    }

    public static UrlParam getUrlParam(String url){
        UrlParam urlParam = new UrlParam();
        String baseUrl = parseUrlWithoutParams(url);
        urlParam.baseUrl = FormatUtil.string(baseUrl);
        String paramStr = parseUrlParamsToString(url);
        urlParam.paramStr = FormatUtil.string(paramStr);
        urlParam.params = parseStringParamsToMap(paramStr);
        try {
            Uri uri = Uri.parse(url);
            urlParam.uri = uri;
        }catch(RuntimeException e){
        }
        return urlParam;
    }

    public static String paramMapToString(Map<String, String> map){
        if(map == null) return "";
        Set<String> set = map.keySet();
        Object[] ob = set.toArray();
        Arrays.sort(ob);
        StringBuilder paramBuilder = new StringBuilder();

        for(Object obj : ob){
            paramBuilder.append("&"+obj+"="+map.get(obj));
        }
        if(paramBuilder.length() > 0){
            paramBuilder.deleteCharAt(0);
        }
        return paramBuilder.toString();
    }

    public static Map<String, String> getIntentParams(Intent intent){
        if(intent == null) return null;
        if(intent.hasExtra("uri")){
            String uri = intent.getStringExtra("uri");
            if(uri.contains("?")){
                uri = parseUrlParamsToString(uri);
            }
            return parseStringParamsToMap(uri);
        }
        return null;
    }

    public static boolean isInternalHttp(String url, String host){
        try {
            URL mUrl = new URL(url);
            String mHost = mUrl.getHost();
            return mHost.endsWith(host);
        }catch(Exception e){
            return false;
        }
    }
}
