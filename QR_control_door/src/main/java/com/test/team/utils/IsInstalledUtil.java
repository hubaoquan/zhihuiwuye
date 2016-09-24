package com.test.team.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * Created by zhiyuan on 2016/7/26.
 */
public class IsInstalledUtil {
    /**
     *
     * @param pkgName 包名，通过判断APP的包名是否存在，判断当前Android系统是否安装相应的APP
     * */
     public static boolean IsInstalledApp(Context context, String packageName){
         PackageInfo packageInfo = null;
         try {
             packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
         }catch(PackageManager.NameNotFoundException e){
             Log.i("tip>>>>>>>>>>>>>>>>","未安装" + packageName);
             e.printStackTrace();
         }
         if(packageInfo == null){
             return false;
         }else{
             return true;
         }
     }

    /**
     *
     * @param context
     * @return boolean
     *  判断当前的Android系统是否安装微信客户端
     */
    public static boolean isWXAppInstalledAndSupported(Context context) {
        IWXAPI msgApi = WXAPIFactory.createWXAPI(context, null);
        msgApi.registerApp(Constants.QQ_APPID);
        boolean sIsWXAppInstalledAndSupported = msgApi.isWXAppInstalled()
                && msgApi.isWXAppSupportAPI();

        return sIsWXAppInstalledAndSupported;
    }
}
