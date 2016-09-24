package com.test.team.utils.httputils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zhiyuan on 2016/7/18.
 */
public class HttpUtil {
     public static void sendHttpRequest(final String address,final HttpCallbackListener listener){
         //创建一个新的线程处理网络连接请求的代码
         new Thread(new Runnable() {
             @Override
             public void run() {
                 HttpURLConnection connection = null;

                 try {
                     URL url = new URL(address);
                     connection = (HttpURLConnection) url.openConnection();
                     connection.setRequestMethod("GET");
                     connection.setConnectTimeout(8000);
                     connection.setReadTimeout(8000);
                     connection.setDoInput(true);
                     connection.setDoOutput(true);
                     //获取输入流
                     InputStream in = connection.getInputStream();
                     //存储获取到的结果 设置编码方式
                     BufferedReader reader = new BufferedReader(new InputStreamReader(in,"GBK"));
                     StringBuilder response = new StringBuilder();
                     String line;
                     while((line = reader.readLine()) != null){
                         response.append(line);
                     }
                     //log
                     Log.i("response------",response.toString());

                     if(listener != null){
                         listener.onFinish(response.toString());
                     }

                 }catch(Exception e){
                     e.printStackTrace();
                     if(listener != null){
                         listener.onError(e);
                     }
                 }finally {
                     if(connection != null){
                         connection.disconnect();//关闭连接
                     }
                 }
             }
         }).start();
     }
}
