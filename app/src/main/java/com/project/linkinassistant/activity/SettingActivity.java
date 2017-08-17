package com.project.linkinassistant.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.project.linkinassistant.R;
import com.project.linkinassistant.widget.NumberAddressToast;
import com.project.linkinassistant.widget.SettingItemView;
import com.project.linkinassistant.widget.ToastStyleSelectDialog;

/**
 * Created by lzm on 2017/8/16.
 */
public class SettingActivity extends AppCompatActivity {

    private SettingItemView mZdgx;
    private SettingItemView mSdgx;
    private NumberAddressToast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
        initData();

    }

    private void initData() {
        mToast = new NumberAddressToast(SettingActivity.this);
        mZdgx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastStyleSelectDialog dialog = new ToastStyleSelectDialog(SettingActivity.this);
                dialog.show();
            }
        });
        mSdgx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mToast.show("Hi");
                    }
                });
            }
        });
    }

    private void initView() {
        mZdgx = (SettingItemView) findViewById(R.id.setting_zdgx);
        mSdgx = (SettingItemView) findViewById(R.id.setting_sdgx);
    }
}
