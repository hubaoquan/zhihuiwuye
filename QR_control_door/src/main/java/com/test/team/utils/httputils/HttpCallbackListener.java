package com.test.team.utils.httputils;

/**
 * Created by zhiyuan on 2016/7/18.
 */

import java.io.UnsupportedEncodingException;

/**
 * 定义接口实现java的回调机制
 */
public interface HttpCallbackListener {
    void onFinish(String response) throws UnsupportedEncodingException;
    void onError(Exception e);
}
