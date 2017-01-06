package cn.leeii.lib.utils;

import android.text.TextUtils;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作类 Created by Leeii on 2015/6/23.
 */
public class StringUtil {

    /**
     * 判断字符串不为空
     *
     * @param str 字符串
     * @return true 不为空 false 为空
     */
    public static boolean isNotEmpty(String str) {
        boolean result = false;
        if (str != null && !"".equals(str) && !"null".equals(str))
            result = true;
        return result;
    }

    /**
     * 判断字符串为空
     *
     * @param str 字符串
     * @return true 空 false 不空
     */
    public static boolean isEmpty(String str) {
        return !isNotEmpty(str);
    }

    /**
     * 判断字符串是否是手机
     *
     * @param mobile 手机号
     * @return true 是手机 false 不是手机
     */
    public static boolean isMobilePhone(String mobile) {
        return mobile.matches("^[1][3578]\\d{9}$");
    }

    /**
     * 查看是否是数字英文中文
     *
     * @param str 字符
     * @return true包含 false 不包含
     */
    public static boolean isNumCnEn(String str) {
        Pattern p = Pattern.compile("[A-Za-z0-9\\u4e00-\\u9fa5]");
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 检查是否是数字和英文
     *
     * @param str 字符
     * @return true包含 false 不包含
     */
    public static boolean isNumEn(String str) {
        Pattern p = Pattern.compile("[A-Za-z0-9]");
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 验证邮箱
     */
    public static boolean isEmail(String email) {
        boolean flag;
        try {
            String check =
                    "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 四舍五入 保留2位小数
     *
     * @param value 值
     * @return 结果 两位小数
     */
    public static String format2Decimal(float value) {
        return String.format(Locale.CHINA, "%1$.2f", value);
    }

    /**
     * 四舍五入 保留1位小数
     *
     * @param value 值
     * @return 结果 一位小数
     */
    public static String formatDecimal(float value) {
        return String.format(Locale.CHINA, "%1$.1f", value);
    }

    /**
     * 135****9186
     * @param phoneNumber
     * @return
     */
    public static String encodePhone(String phoneNumber) {
        if (TextUtils.isEmpty(phoneNumber) || phoneNumber.length() < 8)
            return phoneNumber;
        else {
            StringBuilder sb = new StringBuilder();
            sb.append(phoneNumber.substring(0, 3));
            for (int i = 0; i < phoneNumber.length() - 7; i++) {
                sb.append("*");
            }
            sb.append(phoneNumber.substring(phoneNumber.length() - 4, phoneNumber.length()));
            return sb.toString();
        }
    }
}
