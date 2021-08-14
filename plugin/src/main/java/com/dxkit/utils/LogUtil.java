package com.dxkit.utils;

/**
 * @author danxingxi
 * @since 2021.8.14
 */
public class LogUtil {
    public static boolean isEnable = true;

    public static synchronized void log(String str) {

        if (!isEnable) {
            return;
        }

        System.out.println(str);
    }

    public static void setIsEnable(boolean isEnable) {
        LogUtil.isEnable = isEnable;
    }
}
