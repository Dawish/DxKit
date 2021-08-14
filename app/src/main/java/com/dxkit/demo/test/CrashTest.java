package com.dxkit.demo.test;

/**
 * asm添加try catch 测试
 *
 * @author danxingxi
 */
public class CrashTest {

    public static boolean crashMethod1() {
        int a = 1 / 0;
        return false;
    }

    public static void crashMethod2() {
        int a = 1 / 0;
    }

    public int getInt() {
        return 7 + 3;
    }

    public boolean isEnable() {

        int a = 2 + 3;

        return false;
    }

    public Object getObj() {
        Object object = new Object();
        return object;
    }

    public String getStr() {
        String a = "danxingxi" + "  yyds";
        return a;
    }

}
