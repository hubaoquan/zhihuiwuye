package com.test.team.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.test.team.fragment.MeFragment;
import com.test.team.fragment.NewsFragment;
import com.test.team.fragment.FindFragment;
import com.test.team.fragment.ServiceFragment;
import com.test.team.qrscandoor.R;

public class MainActivity extends Activity implements View.OnClickListener {
    private final static String TAG = MainActivity.class.getSimpleName();
    /**
     * 缴费
     */
    private Button btn_1;
    /**
     * 通告
     */
    private Button btn_2;
    /**
     * 服务
     */
    private Button btn_3;

    /**
     * 我的信息
     */
    private Button btn_4;

    /**
     * 通过Context获得
     */
    private FragmentManager fragmentManager;

    private Fragment fr1, fr2, fr3, fr4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        fragmentManager = getFragmentManager();
        //初始化
        initView();
        //设置默认的Fragment
        setDefaultFragment();
    }

    /**
     * 设置默认的Fragment
     */
    private void setDefaultFragment() {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ServiceFragment fg_service = new ServiceFragment();
        fragmentTransaction.replace(R.id.change_layout, fg_service);
        fragmentTransaction.commit();
    }

    /**
     * 切换fragment id为change_layout
     *
     * @param newFragment
     */
    private void replaceFragment(Fragment newFragment) {
        //开启事物
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        //替换前判断该fragment是否已经加载到Activity
        if (!newFragment.isAdded()) {
            transaction.replace(R.id.change_layout, newFragment);
            transaction.commit();
        } else {
            transaction.show(newFragment);
        }
    }

    private void initView() {
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_1.setOnClickListener(this);

        btn_2 = (Button) findViewById(R.id.btn_2);
        btn_2.setOnClickListener(this);

        btn_3 = (Button) findViewById(R.id.btn_3);
        btn_3.setOnClickListener(this);

        btn_4 = (Button) findViewById(R.id.btn_4);
        btn_4.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_1://公告
                if (fr1 == null) {
                    fr1 = new NewsFragment();
                }
                replaceFragment(fr1);
                Log.d(TAG, "onClick: btn_1");
                break;
            case R.id.btn_2://发现
                if (fr2 == null) {
                    fr2 = new FindFragment();
                }
                replaceFragment(fr2);
                Log.d(TAG, "onClick: btn_2");
                break;
            case R.id.btn_3://服务
                if (fr3 == null) {
                    fr3 = new ServiceFragment();
                }
                replaceFragment(fr3);
                Log.d(TAG, "onClick: btn_3");
                break;
            case R.id.btn_4:
                if (fr4 == null) {
                    fr4 = new MeFragment();
                }
                replaceFragment(fr4);
                Log.d(TAG, "onClick: btn_4");
                break;
        }
    }

    private long lastClickTime = 0;

    /**
     * 退出操作的处理 根据用户两次按退出键的间隔判断是否为错误操作
     */
    @Override
    public void onBackPressed() {

        // super.onBackPressed();
        if (lastClickTime <= 0) {
            Toast.makeText(this, "再按一次后退键退出应用", Toast.LENGTH_SHORT).show();
            lastClickTime = System.currentTimeMillis();
        } else {
            long currentClickTime = System.currentTimeMillis();
            if (currentClickTime - lastClickTime < 1000) {// 在两次按键间隔小于1秒时说明退出程序
                finish();
            } else {
                Toast.makeText(this, "再按一次后退键退出应用", Toast.LENGTH_SHORT).show();
                lastClickTime = System.currentTimeMillis();
            }
        }
    }
}
