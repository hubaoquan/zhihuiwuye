package com.test.team.utils.utils.sharetools;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.Toast;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by zhiyuan on 2016/7/28.
 * 分享给微信客户端
 */
public class ShareToWeiXin {
    public static final String APP_ID = "";
    private IWXAPI mIwxApi;
    //将Bitmap转换为二进制格式的数组
    private byte[] bmpToByteArray(final Bitmap bitmap, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        //是否释放内存
        if (needRecycle) {
            bitmap.recycle();
        }
        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    //启动微信客户端
    public void Lauch_Winxin(Context context) {
        //启动微信，成功返回true,失败返回flase
        mIwxApi = WXAPIFactory.createWXAPI(context, APP_ID);
        mIwxApi.openWXApp();
    }

    //为请求生成唯一的标识
    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    //将Bitmap对象转化为二进制格式发送到微信客户端
    public void ShareToWeixin(Context context,Bitmap bitmap_image) {
        //用WXImageObject对象包装Bitmap
        WXImageObject imgObj = new WXImageObject(bitmap_image);
        //WXMediaMessage对象
        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imgObj;
        //压缩图像
        Bitmap thumBmp = Bitmap.createScaledBitmap(bitmap_image, 120, 150, true);
        //释放图像资源的内存资源
        bitmap_image.recycle();
        msg.thumbData = bmpToByteArray(thumBmp, true);//设置略缩图
        //发送图像
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("image");
        req.message = msg;
        //设置发送的场景  分享给好友
        req.scene = SendMessageToWX.Req.WXSceneSession;
        //输出发送结果标识
        Toast.makeText(context, String.valueOf(mIwxApi.sendReq(req)), Toast.LENGTH_SHORT);
        //发送完成后关闭当前的Activity
    }
}
