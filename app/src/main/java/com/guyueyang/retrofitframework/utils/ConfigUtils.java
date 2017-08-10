package com.guyueyang.retrofitframework.utils;


import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/5/12.
 * 简单存储数据  这边没用数据库
 */
public class ConfigUtils {
    private static final String SYSTEM_CONFIG = "system_config";
    private static final String UPLOAD_URL="upload_url";
    private static final String HTTP_HEADERS_COOKIE = "http_headers_cookie";
    private static final String COOKIES = "cookies";

    public static void setHttpHeadersCookie(Context context, String cookie) {
        SharedPreferences.Editor edit = getSharedPreferences(context, COOKIES).edit();
        edit.putString(HTTP_HEADERS_COOKIE, cookie);
        edit.commit();
    }

    public static String getHttpHeadersCookie(Context context) {
        return getSharedPreferences(context, COOKIES).getString(HTTP_HEADERS_COOKIE, "");
    }

    private static SharedPreferences getSharedPreferences(Context context, String fileName) {
        return context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
    }

    public static void setUploadUrl(Context context, String apiUrl) {
        SharedPreferences.Editor edit = getSharedPreferences(context, SYSTEM_CONFIG).edit();
        edit.putString(UPLOAD_URL, apiUrl);
        edit.commit();
    }

    public static String getUploadUrl(Context context) {
        return getSharedPreferences(context, SYSTEM_CONFIG).getString(UPLOAD_URL, "");
    }
}
