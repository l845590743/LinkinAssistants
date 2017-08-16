package com.project.linkinassistant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.project.linkinassistant.R;
import com.project.linkinassistant.utils.PackageUtil;

public class SplashActivity extends AppCompatActivity {

    private TextView mTvVersion;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        setVersionName();
        gotoHome();
    }

    private void gotoHome() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    private void setVersionName() {
        mTvVersion.setText("版本：" + PackageUtil.getVersion(this));
    }

    private void initView() {
        mTvVersion = (TextView) findViewById(R.id.text_version);
    }

    @Override
    public void onBackPressed() {

    }
}
