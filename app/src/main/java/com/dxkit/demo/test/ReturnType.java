package com.dxkit.demo.test;

/**
 * 使用ASM 查看，判断返回类型
 *
 * @author danxingxi
 */
public class ReturnType {

    public int getInt() {
        return 0;
    }

    public String getString() {
        return null;
    }

    public Object getObj() {
        return null;
    }

    public float getFloat() {
        return 0f;
    }

}
