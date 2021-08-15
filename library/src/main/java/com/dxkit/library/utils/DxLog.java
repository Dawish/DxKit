package com.dxkit.library.utils;

import android.util.Log;

public final class DxLog {

    public final static boolean ENABLE = true;

    private DxLog() {

    }

    public static void d(String tag, String msg) {
        if (!ENABLE) {
            return;
        }

        Log.d(tag, msg);
    }

    public static void i(String tag, String msg) {
        if (!ENABLE) {
            return;
        }

        Log.i(tag, msg);
    }

    public static void w(String tag, String msg) {
        if (!ENABLE) {
            return;
        }

        Log.w(tag, msg);
    }

    public static void e(String tag, String msg) {
        if (!ENABLE) {
            return;
        }

        Log.e(tag, msg);
    }

    public static void w(String tag, String msg, Throwable e) {
        if (!ENABLE) {
            return;
        }

        Log.w(tag, msg, e);
    }

    public static void e(String tag, String msg, Throwable e) {
        if (!ENABLE) {
            return;
        }

        Log.e(tag, msg, e);
    }

}
