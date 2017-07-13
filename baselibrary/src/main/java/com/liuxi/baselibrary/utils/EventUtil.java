package com.liuxi.baselibrary.utils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by david on 17-4-13.
 */

public class EventUtil {
    /**
     * 绑定
     *
     * @param object
     */
    public static void register(Object object) {
        EventBus.getDefault().register(object);
    }

    /**
     * 解绑
     *
     * @param object
     */
    public static void unRegister(Object object) {
        EventBus.getDefault().unregister(object);
    }

    /**
     * 发送消息
     *
     * @param c
     */
    public static void post(Class<?> c) {
        EventBus.getDefault().post(c);
    }

}
