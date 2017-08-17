package com.project.linkinassistant.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.project.linkinassistant.R;

/**
 * Created by lzm on 2017/8/16.
 */
public class SettingItemView extends RelativeLayout {

    public SettingItemView(Context context) {
        this(context,null);
    }

    public SettingItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = View.inflate(context, R.layout.view_setting_item, null);
        addView(view);
    }
}
