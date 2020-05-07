package com.hym.errands.app;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.xuexiang.xui.XUI;

import cn.bmob.v3.Bmob;

public class App extends Application {

    private String APP_KEY = "489ffacb02eec736c4889cafb736b726";

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化bmob
        Bmob.initialize(this, APP_KEY);
        XUI.init(this); //初始化UI框架
        //XUI.debug(true);  //开启UI框架调试日志

        //初始化百度sdk
        SDKInitializer.initialize(this);
        //設置坐標類型
        SDKInitializer.setCoordType(CoordType.BD09LL);

        MultiDex.install(this);
    }
}
