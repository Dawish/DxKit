package com.dxkit.library.utils;

import androidx.annotation.Keep;

/**
 * asm aop处理异常
 *
 * @author danxingxi
 */
@Keep
public final class ExceptionHandler {

    public static void handleException(Exception exception) {
        if (exception == null) {
            return;
        }
        DxLog.e("ExceptionHandler", "handleException:", exception);
    }

}
