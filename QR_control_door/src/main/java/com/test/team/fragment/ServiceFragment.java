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
 * 创建时间：Created on 2016/9/13 16:28
 * 修改备注：将服务作为进入App功能界面的第一个界面
 */
public class ServiceFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //
        View view ;
        view = inflater.inflate(R.layout.fg_services,container,false);
        return view;
    }
}
