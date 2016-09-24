package com.test.team.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.team.qrscandoor.R;

/**
 * 项目名称：QRScandoor
 * 创建人：Created by zhiyuan.
 * 创建时间：Created on 2016/9/13 16:35
 * 修改备注：
 */
public class MeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fg_me, container, false);
        TextView tv_right = (TextView) view.findViewById(R.id.tv_right);
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText("设置");
        tv_right.setTextSize(15);
        return view;
    }
}
