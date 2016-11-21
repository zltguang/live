package com.zltguang.application.base;

import cn.finalteam.okhttpfinal.OkHttpFinal;
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration;

/**
 * Created by Administrator on 2016/10/25 0025.
 */

public class Application extends android.app.Application{
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();

        OkHttpFinalConfiguration.Builder builder = new OkHttpFinalConfiguration.Builder();
        OkHttpFinal.getInstance().init(builder.build());
    }
    public static Application getInstance(){
        return instance;
    }


}
