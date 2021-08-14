package com.dxkit.demo;

import android.app.Application;

public class DxKitApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public int getInt() {
        int n = 2 + 3;
        return n;
    }


    public String getStr() {

        try {
            String n = "danxingxi" + 3;
            return n;
        } catch (Exception e) {

        }

        return null;
    }

}
