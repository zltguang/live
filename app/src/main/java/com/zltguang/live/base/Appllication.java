package com.zltguang.live.base;

import android.app.Application;

/**
 * Created by Administrator on 2016/10/25 0025.
 */

public class Appllication extends Application{
    private static Application instance;
    public static Application getInstance(){
        return instance;
    }
}
