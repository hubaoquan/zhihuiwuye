package com.test.team.global;

import android.app.Application;
import android.content.Context;

/**
 * 项目名称：QRScandoor
 * 创建人：Created by zhiyuan.
 * 创建时间：Created on 2016/9/9 17:01
 * 修改备注：
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        context = getApplicationContext();
    }

    /**
     * 得到应用程序级别的Context
     * @return
     */
    public static Context getContext() {
        return context;
    }
}
