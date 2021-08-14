package com.dxkit.tryCatch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 需要加try catch的类及其中方法的信息
 *
 * @author danxingxi
 */
public class TryCatchExtension {

    //添加try catch的类  类中方法集合
    public static Map<String, List<String>> methodMap = new HashMap<>();

    // 处理异常的类名和方法名
    public static Map<String, String> exceptionHandler = new HashMap<>();

    public static boolean isValid() {
        if (methodMap != null && methodMap.size() > 0) {
            return true;
        }
        return false;
    }
}
