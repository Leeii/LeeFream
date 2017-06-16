package cn.leeii.libmvp.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Environment;

import java.io.File;
import java.io.FileFilter;
import java.util.regex.Pattern;

/**
 * 系统方法 Created by Leeii on 2015/3/26.
 */
public class CoreUtil {
    
    /**
     * 描述：SD卡是否能用
     *
     * @return true 可用 false 不可用
     */
    public static boolean isCanUseSD() {
        try {
            return Environment.getExternalStorageState()
                              .equals(Environment.MEDIA_MOUNTED);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * 获取CPU核心数
     *
     * @return CPU核心数
     */
    public static int getNumCores() {
        try {
            // 获取本地CPU信息文件
            File dir = new File("/sys/devices/system/cpu/");
            // 过滤内部文件集
            File[] files = dir.listFiles(new FileFilter() {
                
                @Override
                public boolean accept(File pathname) {
                    // 检查文件名匹配cpu0~cpu9
                    return Pattern.matches("cpu[0-9]", pathname.getName());
                }
                
            });
            // 返回cpu核心数
            return files.length;
        }
        catch (Exception e) {
            // 异常返回1个核心
            return 1;
        }
    }
    
    /**
     * 获取版本号和版本名
     * 
     * @param context
     *            上下文
     * @return 版本号版本名字的数组
     */
    public static String[] getVersion(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            PackageInfo packageInfo =
                                    pm.getPackageInfo(context.getPackageName(),
                                                      PackageManager.GET_CONFIGURATIONS);
            String[] strs = new String[3];
            strs[0] = packageInfo.packageName;
            strs[1] = String.valueOf(packageInfo.versionCode);
            strs[2] = packageInfo.versionName;
            return strs;
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
