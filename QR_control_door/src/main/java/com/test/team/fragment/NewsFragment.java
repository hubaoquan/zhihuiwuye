package com.test.team.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.test.team.adapter.NewsAdapter;
import com.test.team.bean.News;
import com.test.team.global.MyApplication;
import com.test.team.qrscandoor.R;

import java.util.LinkedList;
import java.util.List;

/**
 * 项目名称：QRScandoor
 * 创建人：Created by zhiyuan.
 * 创建时间：Created on 2016/9/13 16:18
 * 修改备注：通知公告的Fragment片段
 */
public class NewsFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ImageView iv_go_left;
    private ImageView iv_go_right;
    private ListView lv_news;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view ;
        view = inflater.inflate(R.layout.fg_news, container, false);
        //标题栏
        TextView tv_title = (TextView)view.findViewById(R.id.tv_title_topstyle);
        tv_title.setText(R.string.newsfg_title);
        iv_go_left = (ImageView) view.findViewById(R.id.tv_go_left_topstyle);
        iv_go_left.setOnClickListener(this);

        lv_news = (ListView)view.findViewById(R.id.lv_news_list);

        List<News> newsList = new LinkedList<News>();

        News news = new News();
        news.setImageId(R.drawable.ic_launcher);
        news.setNews_title("楼市疯狂，为何高层如此沉默？");
        news.setNews_index("社区管家|5分钟前");
        newsList.add(news);
        //获取Context实例

        NewsAdapter adapter = new NewsAdapter(MyApplication.getContext(),newsList);
        lv_news.setAdapter(adapter);
        lv_news.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tv_go_left_topstyle:
                break;
            case R.id.tv_go_right_topstyle:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}


