package com.test.team.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.team.qrscandoor.R;

/**
 * 项目名称：QRScandoor
 * 创建人：Created by zhiyuan.
 * 创建时间：Created on 2016/9/12 20:56
 * 修改备注：发现的界面布局   （Android 3.0(11)以上有Fragment）
 */
public class FindFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //加载布局
        View view ;
        view = inflater.inflate(R.layout.fg_find,container,false);
        return view;
    }

}
