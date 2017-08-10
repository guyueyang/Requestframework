package com.guyueyang.retrofitframework.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;

/**
 * 日志打印处理工具
 */
public class LogUtils {

    private static Boolean mIsDebugMode = null;

    public static boolean isDebugMode() {
        return mIsDebugMode == null ? false : mIsDebugMode.booleanValue();
    }

    public static void syncAppDebugMode(Context context) {
        if (mIsDebugMode == null) {
            mIsDebugMode = context.getApplicationInfo() != null &&
                    (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        }
    }

    /**
     * 第一种打印情况
     **/
    public static void print_i(String tag, String msg) {
        if (!isDebugMode()) return;

        Log.i(tag, msg);
    }

    public static void print_i(Class<?> clz, String msg) {
        if (clz != null) print_i(clz.getSimpleName(), msg);
    }

    public static void print_e(String tag, String msg) {
        if (!isDebugMode()) return;

        Log.e(tag, msg);
    }

    public static void print_e(Class<?> clz, String msg) {
        if (clz != null) print_e(clz.getSimpleName(), msg);
    }

    public static void v(String tag, String msg) {
        if (isDebugMode()) Log.v(tag, msg);
    }

    public static void v(String tag, String msg, Throwable t) {
        if (isDebugMode()) Log.v(tag, msg, t);
    }

    public static void d(String tag, String msg) {
        if (isDebugMode()) Log.d(tag, msg);
    }

    public static void d(String tag, String msg, Throwable t) {
        if (isDebugMode()) Log.d(tag, msg, t);
    }

    public static void i(String tag, String msg) {
        if (isDebugMode()) Log.i(tag, msg);
    }

    public static void i(String tag, String msg, Throwable t) {
        if (isDebugMode()) Log.i(tag, msg, t);
    }

    public static void w(String tag, String msg) {
        if (isDebugMode()) Log.w(tag, msg);
    }

    public static void w(String tag, String msg, Throwable t) {
        if (isDebugMode()) Log.w(tag, msg, t);
    }

    public static void e(String tag, String msg) {
        if (isDebugMode()) Log.e(tag, msg);
    }

    public static void e(String tag, String msg, Throwable t) {
        if (isDebugMode()) Log.e(tag, msg, t);
    }
}
