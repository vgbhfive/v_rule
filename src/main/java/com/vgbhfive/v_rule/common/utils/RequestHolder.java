package com.vgbhfive.v_rule.common.utils;

import java.util.Map;

/**
 * @Author vgbhfive
 * @Date 2026/3/2 17:58
 */
public class RequestHolder {

    private final static ThreadLocal<Map<String, Object>> requestHolder = new ThreadLocal<>();

    public static void set(Map<String, Object> obj) {
        requestHolder.set(obj);
    }

    public static Map<String, Object> get() {
        return requestHolder.get();
    }

    public static void remove() {
        requestHolder.remove();
    }

}
