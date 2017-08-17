package com.project.linkinassistant.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

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

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SettingItemView);
        String title = a.getString(R.styleable.SettingItemView_titles);
        int bgType = a.getInt(R.styleable.SettingItemView_bgType, -1);
        a.recycle();

        View view = View.inflate(context, R.layout.view_setting_item, null);
        addView(view);

        TextView tVtitle= (TextView) view.findViewById(R.id.view_setting_title);
        tVtitle.setText(title);
        switch (bgType) {
            case 0:
                view.setBackgroundResource(R.drawable.first_normal);
                break;
            case 1:
                view.setBackgroundResource(R.drawable.last_normal);
                break;
        }
    }
}
