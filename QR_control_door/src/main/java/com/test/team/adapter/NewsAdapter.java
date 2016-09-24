package com.test.team.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.team.bean.News;
import com.test.team.qrscandoor.R;

import java.util.List;

/**
 * 项目名称：QRScandoor
 * 创建人：Created by zhiyuan.
 * 创建时间：Created on 2016/9/21 22:29
 * 修改备注：ListView适配器
 */
public class NewsAdapter extends BaseAdapter {
    /**
     * Context实例
     */
    private Context context;
    /**
     * 存储News实体类
     */
    private List<News> newsList;
    /**item项的布局文件id*/
    //private int resourceId;

    /**
     * @param context
     * @param resource
     * @param objects  接收List类型的列表内容
     */
    public NewsAdapter(Context context, List<News> objects) {
        this.context = context;
        this.newsList = objects;
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listview_item, null);
            Log.d("getView", "getView: " + view.toString());
            //view = LayoutInflater.from(context).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.iv_news_image = (ImageView) view.findViewById(R.id.tv_news_image);
            viewHolder.tv_news_content = (TextView) view.findViewById(R.id.news_content);
            viewHolder.news_index = (TextView) view.findViewById(R.id.news_index);

            //将viewHolder存储在view中
            view.setTag(viewHolder);
        } else {
            view = convertView;
            //重新获取ViewHolder
            viewHolder = (ViewHolder) view.getTag();
        }
        News news = newsList.get(position);

        viewHolder.iv_news_image.setImageResource(news.getImageId());
        viewHolder.tv_news_content.setText(news.getNews_title());
        viewHolder.news_index.setText(news.getNews_index());

        return view;
    }

    /**
     * 定义View对应的控件实例
     */
    class ViewHolder {
        ImageView iv_news_image;
        TextView tv_news_content;
        TextView news_index;
    }
}
