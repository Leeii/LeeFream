package com.leeiidesu.libcommon.android;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

/**
 * _ SharedPreferences辅助类 _ Created by dgg on 2017/5/27.
 */

public class PreferencesHelper {
    private static final String SP_NAME = "config";

    private PreferencesHelper() {
    }

    private static SharedPreferences mSharedPreferences;

    public static void init(Context context) {
        if (mSharedPreferences == null) {
            mSharedPreferences =
                    context.getSharedPreferences(SP_NAME,
                            Context.MODE_PRIVATE);
        }
    }

    public static void putString(String key, String value) {
        mSharedPreferences.edit().putString(key, value).apply();
    }

    public static void putInt(String key, int value) {
        mSharedPreferences.edit().putInt(key, value).apply();
    }

    public static void putBoolean(String key, boolean value) {
        mSharedPreferences.edit().putBoolean(key, value).apply();
    }

    public static void putFloat(String key, float value) {
        mSharedPreferences.edit().putFloat(key, value).apply();
    }

    public static void putLong(String key, long value) {
        mSharedPreferences.edit().putLong(key, value).apply();
    }

    public static void putStringSet(String key, Set<String> value) {
        mSharedPreferences.edit().putStringSet(key, value).apply();
    }

    public static String getString(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public static int getInt(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public static float getFloat(String key, float defaultValue) {
        return mSharedPreferences.getFloat(key, defaultValue);
    }

    public static long getLong(String key, long defaultValue) {
        return mSharedPreferences.getLong(key, defaultValue);
    }

    public static Set<String> getStringSet(String key, Set<String> defaultValue) {
        return mSharedPreferences.getStringSet(key, defaultValue);
    }

    public static String getString(String key) {
        return mSharedPreferences.getString(key, null);
    }

    public static int getInt(String key) {
        return mSharedPreferences.getInt(key, 0);
    }

    public static boolean getBoolean(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    public static float getFloat(String key) {
        return mSharedPreferences.getFloat(key, 0);
    }

    public static long getLong(String key) {
        return mSharedPreferences.getLong(key, 0);
    }

    public static Set<String> getStringSet(String key) {
        return mSharedPreferences.getStringSet(key, null);
    }

    public static void remove(String key) {
        mSharedPreferences.edit().remove(key).apply();
    }

    public static void clear() {
        mSharedPreferences.edit().clear().apply();
    }

    public static void release() {
        mSharedPreferences = null;
    }
}
