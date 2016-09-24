package com.test.team.bean;

/**
 * 项目名称：QRScandoor
 * 创建人：Created by zhiyuan.
 * 创建时间：Created on 2016/9/22 20:34
 * 修改备注：
 */
public class News extends BaseBean{
    private int imageId;
    private String news_title;
    private String news_index;

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setNews_title(String news_title) {
        this.news_title = news_title;
    }

    public void setNews_index(String news_index) {
        this.news_index = news_index;
    }

    public int getImageId() {
        return imageId;
    }

    public String getNews_title() {
        return news_title;
    }

    public String getNews_index() {
        return news_index;
    }
}
