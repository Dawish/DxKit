package com.dxkit.demo.test;

import android.os.Build;

import java.util.function.Consumer;

import androidx.annotation.RequiresApi;

/**
 * 使用ASM 查看，判断返回类型
 *
 * @author danxingxi
 */
public class ReturnType {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public int getInt() {

        // lambda表达式
        Consumer<String> print = System.out::println;
        print.accept("danxingxi");
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
