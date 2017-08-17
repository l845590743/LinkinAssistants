package com.project.linkinassistant.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.project.linkinassistant.R;
import com.project.linkinassistant.adapter.HomeAdapter;
import com.project.linkinassistant.bean.HomeListBean;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private ImageView mImageLogo;
    private GridView mGridview;
    private ArrayList<HomeListBean> mHomeList = new ArrayList<>();

    //标题数据
    private final static String[] TITLES = new String[] { "手机防盗", "骚扰拦截",
            "软件管家", "进程管理", "流量统计", "手机杀毒", "缓存清理", "常用工具" };
    //描述信息
    private final static String[] DESCS = new String[] { "远程定位手机", "全面拦截骚扰",
            "管理您的软件", "管理运行进程", "流量一目了然", "病毒无处藏身", "系统快如火箭", "工具大全" };
    //图标
    private final static int[] ICONS = new int[] { R.mipmap.sjfd,
            R.mipmap.srlj, R.mipmap.rjgj, R.mipmap.jcgl, R.mipmap.lltj,
            R.mipmap.sjsd, R.mipmap.hcql, R.mipmap.cygj };
    private ImageView mImageSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initData();
        initView();
        setLogoAnimation();
        filllsit();
    }

    private void initData() {
        for (int i = 0; i < TITLES.length; i++) {
            HomeListBean homeListBean = new HomeListBean();
            homeListBean.setIcon(ICONS[i]);
            homeListBean.setTitle(TITLES[i]);
            homeListBean.setDesc(TITLES[i]);
            mHomeList.add(homeListBean);
        }
    }

    private void filllsit() {
        HomeAdapter homeAdapter = new HomeAdapter(this,mHomeList);
        mGridview.setAdapter(homeAdapter);
        mGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 3:
                        Intent intent = new Intent(HomeActivity.this, AppManagerActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        });
    }

    private void setLogoAnimation() {
//        mImageLogo.setRotationY(rotationY);
//        ObjectAnimator rotationY = ObjectAnimator.ofFloat(mImageLogo, "rotation", 0f,360f);
//        rotationY.setRepeatCount(ObjectAnimator.INFINITE);
//        rotationY.setRepeatMode(ObjectAnimator.REVERSE);
//        rotationY.setDuration(2000);
//        rotationY.start();
    }

    private void initView() {
        mImageLogo = (ImageView) findViewById(R.id.home_logo);
        mGridview = (GridView) findViewById(R.id.home_gridview);
        mImageSetting = (ImageView) findViewById(R.id.home_setting);

        mImageSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });

    }
}
