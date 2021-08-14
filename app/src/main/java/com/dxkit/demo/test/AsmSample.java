package com.dxkit.demo.test;

import android.util.Log;

/**
 * 方便asm bytecode viewer 查看asm代码
 *
 * @author danxingxi
 */
public class AsmSample {

    public int tryTest1() {
        try {
            return 7 + 3;
        } catch (Exception e) {

        }
        return 0;
    }

    public int tryTest2() {
        try {
            return 4 + 9;
        } catch (Exception e) {
            Log.d("DXX", "tryTest2", e);
        }
        return 3;
    }

    public boolean tryTest3() {
        try {
            int a = 5;
            boolean r = a > 4;
            return r;
        } catch (Exception e) {
            Log.d("DXX", "tryTest3", e);
        }
        return false;
    }

    public String tryStr() {
        try {
            int a = 5;
            String s = "s_" + a;
            return s;
        } catch (Exception e) {
            Log.d("DXX", "tryStr", e);
        }
        return null;
    }

}
