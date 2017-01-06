package cn.leeii.lib.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 对SharePreference的封装 Created by Leeii on 2016/1/19.
 */
public class PreferenceHelper {

    /**
     * 保存的Share
     */
    private static SharedPreferences mSharedPreferences = null;


    /**
     * 初始化默认SharePreferences
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        if (null == mSharedPreferences) {
            String pageName = "default_preference";
            String[] strings = CoreUtil.getVersion(context);
            if (strings != null && strings.length == 3)
                pageName = strings[0].replaceAll("\\.", "_");
            mSharedPreferences =
                    context.getSharedPreferences(pageName,
                            Context.MODE_PRIVATE);
        }
    }

    /**
     * 删除数据
     *
     * @param key 键
     */
    public static void removeKey(String key) {
        mSharedPreferences.edit()
                .remove(key)
                .apply();
    }

    /**
     * 删除所有数据
     */
    public static void removeAll() {
        mSharedPreferences.edit()
                .clear()
                .apply();
    }

    /**
     * 提交字符串数据
     *
     * @param key   键
     * @param value 值
     */
    public static void commitString(String key, String value) {
        mSharedPreferences.edit()
                .putString(key, value)
                .apply();
    }

    /**
     * 获取字符串
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 当前键对应的值
     */
    public static String getString(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    /**
     * 提交整型
     *
     * @param key   键
     * @param value 值
     */
    public static void commitInt(String key, int value) {
        mSharedPreferences.edit()
                .putInt(key, value)
                .apply();
    }

    /**
     * 获取整型
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 当前键对应的值
     */
    public static int getInt(String key, int defaultValue) {
        return mSharedPreferences.getInt(key, defaultValue);
    }

    /**
     * 提交长整型
     *
     * @param key   键
     * @param value 值
     */
    public static void commitLong(String key, long value) {
        mSharedPreferences.edit()
                .putLong(key, value)
                .apply();
    }

    /**
     * 获取整型
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 当前键对应的值
     */
    public static long getLong(String key, long defaultValue) {
        return mSharedPreferences.getLong(key, defaultValue);
    }

    /**
     * 提交长整型
     *
     * @param key   键
     * @param value 值
     */
    public static void commitBoolean(String key, boolean value) {
        mSharedPreferences.edit()
                .putBoolean(key, value)
                .apply();
    }

    /**
     * 获取整型
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 当前键对应的值
     */
    public static Boolean getBoolean(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }
}
