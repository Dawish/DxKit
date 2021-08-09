package com.dxkit.library.utils;


import androidx.annotation.Keep;

@Keep
public final class FastClickUtil {

    private FastClickUtil() {

    }

    private static final int FAST_CLICK_TIME_DISTANCE = 300;
    private static long sLastClickTime = 0;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        DxLog.d("FastClickUtil", "isFastDoubleClick time:" + time);
        long timeDistance = time - sLastClickTime;
        if (0 < timeDistance && timeDistance < FAST_CLICK_TIME_DISTANCE) {
            DxLog.e("FastClickUtil", "isFastDoubleClick true");
            return true;
        }
        sLastClickTime = time;
        return false;
    }
}
