package com.dxkit.demo.test;

import android.content.Context;
import android.widget.Toast;

import java.util.List;

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

    public void show(Context context, String msg) {
        if (msg.equals("danxingxi")) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Empty!!!", Toast.LENGTH_SHORT).show();
        }
    }

    public TestBean getBean() {
        TestBean bean = new TestBean();
        bean.setName("danxinxgi");
        bean.setAge(18);
        return bean;
    }

    public double getDouble() {
        double a = 2.3d;
        return a;
    }

    public long getLong() {
        long a = 1234567L;
        return a;
    }

    public float getFloat() {
        float a = 231234213.45f;
        return a;
    }

    public short getShort() {
        short a = 32767;
        return a;
    }

    public byte getByte() {
        byte a = 127;
        return a;
    }

    public char getChar() {
        return 0;
    }

    public int[] getArrayInt() {
        return null;
    }

    public int[][] getArrayInt2() {
        int[][] a = new int[2][2];

        a[0][0] = 2;
        a[0][1] = 3;
        a[1][0] = 4;
        a[0][1] = 5;

        return null;
    }

    public boolean[] getArrayBoolean() {
        return null;
    }

    public boolean[][] getArrayBoolean2() {
        return null;
    }

    public Object[] getArrayObj() {
        return null;
    }

    public Object[][] getArrayObj2() {
        return null;
    }

    public List<String> getList() {
        return null;
    }

}
