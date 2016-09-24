package com.test.team.utils.jsonTools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhiyuan on 2016/7/21.
 */
public class JSONUtils {
    //解析JSON数据
    public static String parseJsonWithJSONObject(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);//获取JSONArray对象
            //循环获得JSONArray的变量
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String version = jsonObject.getString("version");
                String name = jsonObject.getString("name");
            }

        } catch (JSONException je) {
            je.printStackTrace();
        }
        return null;
    }
}
