package cn.leeii.libmvp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 网络判断 Created by Leeii on 2015/3/26.
 */
public class NetUtil {
    
    /**
     * 判断网络是否可用
     * 
     * @param context
     *            上下文
     * @return true 可用 false 不可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                                                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo =
                                    connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected())
                return networkInfo.getState() == NetworkInfo.State.CONNECTED;
        }
        return false;
    }
    
    /**
     * 判断当前连接是否为wifi
     * 
     * @param context
     *            上下文
     * @return true wifi false 非wifi
     */
    public static boolean isWifiEnabled(Context context) {
        ConnectivityManager connectivityManager =
                                                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo =
                                    connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected())
                return networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
        }
        return false;
    }
    
    /**
     * 判断当前连接是否为手机网络连接
     * 
     * @param context
     *            上下文
     * @return true 手机网络 false 非手机网络
     */
    public static boolean isNetMobile(Context context) {
        ConnectivityManager connectivityManager =
                                                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo =
                                    connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected())
                return networkInfo.getType() == ConnectivityManager.TYPE_MOBILE;
        }
        return false;
    }
}
