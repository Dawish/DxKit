package com.dxkit.click;

import java.util.ArrayList;
import java.util.List;

/**
 * DxKit extension.
 * @author danxingxi* @since 2031/8/11
 */
public class ClickExtension {

    // 是否禁止粘性click事件
    public static boolean isForbidStickClick = false;

    // 是否显示click view id name
    public static boolean canShowClickId = false;

    // 点击拦截工具的路径
    public static String clickUtilPath;

    // 需要搜索的字符串数组
    public static List<String> searchStrings = new ArrayList<>();
}