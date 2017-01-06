package cn.leeii.lib.utils;

import java.util.List;

/**
 * 列表Util Created by Leeii on 2015/9/17.
 */
public class ListUtil {
    
    /**
     * 判断当前列表是否为空
     * 
     * @param list
     *            列表
     * @return true 空 false 非空
     */
    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }
    
}
