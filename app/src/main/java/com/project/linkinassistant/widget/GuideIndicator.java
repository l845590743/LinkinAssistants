package com.project.linkinassistant.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.project.linkinassistant.R;


public class GuideIndicator extends LinearLayout {
	public GuideIndicator(Context context) {
		this(context, null);
	}

	public GuideIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		//读取自定义属性
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GuideIndicator);
		//读取step属性
		int step = a.getInt(R.styleable.GuideIndicator_step, -1);
		//读取total属性
		int total = a.getInt(R.styleable.GuideIndicator_total, -1);
		
		//数据校验
		if (total == -1) {
			throw new RuntimeException("必须设置total属性！");
		}
		if (step == -1) {
			throw new RuntimeException("必须设置step属性！");
		}
		if (step > total) {
			throw new RuntimeException("step属性必须小于total！");
		}
		
		//使用完记得及时回收
		a.recycle();
		
		//设置朝向，默认是水平朝向
		setOrientation(LinearLayout.HORIZONTAL);
		
		//添加5张图片
		for (int i = 1; i <= total; i++) {
			ImageView icon = new ImageView(context);
			
			if (step == i) {
				//如果是当前步骤，则显示绿色图标
				icon.setImageResource(android.R.drawable.presence_online);
			}else{
				//否则则显示灰色图标
				icon.setImageResource(android.R.drawable.presence_invisible);
			}
			this.addView(icon);
		}
	}
}
