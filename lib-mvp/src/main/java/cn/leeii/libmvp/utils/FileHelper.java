package cn.leeii.libmvp.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 文件处理工具 Created by Leeii on 2015/6/18.
 */
public class FileHelper {

    /**
     * 缓存文件位置设置
     */
    private static final String cacheDir = "/Android/data/cn.leeii";

    /**
     * 文件缓存地址
     *
     * @param context   上下文
     * @param cacheName 缓存文件名
     * @return 缓存文件
     */
    public static File cacheFile(Context context, String cacheName) {
        File fileDir = null;
        // 判断是否有内置SD卡存在
        if (isExistSD())
            fileDir = new File(Environment.getExternalStorageDirectory(),
                    String.format("%s/%s", cacheDir, cacheName));
        // 判断文件夹是否存在
        if (fileDir == null || !fileDir.exists() && !fileDir.mkdirs())
            fileDir = context.getCacheDir();

        return fileDir;
    }

    /**
     * 获取图片缓存文件
     *
     * @param context 上下文
     * @return 缓存文件对象
     */
    public static File imageCacheFile(Context context) {

        File fileDir = null;
        // 判断是否有内置SD卡存在
        if (isExistSD())
            fileDir = cacheFile(context, "");
        // 判断文件夹是否存在
        if (fileDir == null || !fileDir.exists() && !fileDir.mkdirs())
            fileDir = context.getCacheDir();

        return fileDir;
    }

    /**
     * 清除图片缓存
     *
     * @return true 成功 false 失败
     */
    public static boolean clearImageCache() {
        File fileDir = new File(Environment.getExternalStorageDirectory(),
                String.format("%s/%s", cacheDir, "images"));
        return deleteDirectory(fileDir);
    }

    /**
     * 判断SD卡是否存在
     *
     * @return true 存在 false 不存在
     */
    public static boolean isExistSD() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取Assets下的json文件
     *
     * @param context  上下文
     * @param fileName 文件名
     * @return json字符串
     */
    public static String getAssetsJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf =
                    new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    /**
     * 删除文件夹
     *
     * @param dirFile 文件夹对象
     * @return 是否成功 成功 true 失败 false
     */
    public static boolean deleteDirectory(File dirFile) {
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return true;
        }
        boolean flag = true;
        // 删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (File file : files) {
            // 删除子文件
            if (file.isFile()) {
                flag = deleteFile(file);
                if (!flag)
                    break;
            } // 删除子目录
            else {
                flag = deleteDirectory(file);
                if (!flag)
                    break;
            }
        }
        if (!flag)
            return false;
        // 删除当前目录
        return "images".equals(dirFile.getName()) || dirFile.delete();
    }

    /**
     * 删除单个文件
     *
     * @return 单个文件删除成功返回true，否则返回false
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static boolean deleteFile(File file) {
        // 路径为文件且不为空则进行删除
        return file.isFile() && file.exists() && file.delete();
    }

    /**
     * 返回缓存文件夹
     */
    public static File getCacheFile(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = null;
            file = context.getExternalCacheDir();//获取系统管理的sd卡缓存文件
            if (file == null) {//如果获取的为空,就是用自己定义的缓存文件夹做缓存路径
                file = new File(getCacheFilePath(context));
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
            return file;
        } else {
            return context.getCacheDir();
        }
    }

    /**
     * 获取自定义缓存文件地址
     * @param context
     * @return
     */
    public static String getCacheFilePath(Context context) {
        String packageName = context.getPackageName();
        return "/mnt/sdcard/" + packageName;
    }
}
