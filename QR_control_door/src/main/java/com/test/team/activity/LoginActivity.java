package com.test.team.activity;

import android.app.Activity;
import android.os.Bundle;

import com.test.team.qrscandoor.R;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("请登录");
    }
}
