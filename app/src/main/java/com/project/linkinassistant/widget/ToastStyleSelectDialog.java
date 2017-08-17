package com.project.linkinassistant.widget;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.ListView;

import com.project.linkinassistant.R;
import com.project.linkinassistant.adapter.ToastStyleSelectAdapter;
import com.project.linkinassistant.bean.AddressStyleInfo;

import java.util.ArrayList;
import java.util.List;

public class ToastStyleSelectDialog extends Dialog {
	//颜色资源
	private static final int[] COLOR_RES = {
		R.drawable.number_address_toast_bg_alpha,
		R.drawable.number_address_toast_bg_orange, 
		R.drawable.number_address_toast_bg_blue, 
		R.drawable.number_address_toast_bg_gray, 
		R.drawable.number_address_toast_bg_green};
	//颜色名
	private static final String[] COLOR_NAME = {"半透明", "活力橙", "卫士蓝", "金属灰", "苹果绿"};
	
	private ListView                mListView;
	private List<AddressStyleInfo>  mData;
	private ToastStyleSelectAdapter mAdapter;

	public ToastStyleSelectDialog(Context context) {
		//指定自定义的dialog样式
		this(context, R.style.ToastStyleSelect);
	}
	
	public ToastStyleSelectDialog(Context context, int theme) {
		super(context, theme);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.dialog_toast_style_select);


		if (COLOR_RES.length != COLOR_NAME.length) {
			throw new RuntimeException("COLOR_RES.length != COLOR_NAME.length!");
		}
		
		//初始化数据-数组转换成list
		mData = new ArrayList<AddressStyleInfo>();
		AddressStyleInfo tmp = null;
		for (int i = 0; i < COLOR_RES.length; i++) {
			tmp = new AddressStyleInfo();
			tmp.setColor(COLOR_RES[i]);
			tmp.setName(COLOR_NAME[i]);
			//check状态
//			if (COLOR_RES[i] == savedColor) {
//				tmp.setCheck(true);
//			}
			
			//添加到集合里
			mData.add(tmp);
		}
		
		//初始化控件
		mListView = (ListView) findViewById(R.id.dialog_toast_style_select_listview);
		mAdapter = new ToastStyleSelectAdapter(getContext(), mData);
		mListView.setAdapter(mAdapter);
		
		//设置显示位置
		Window window = getWindow();
		LayoutParams params = window.getAttributes();
		//显示在窗口底部居中，使用|符号组合不同标志
		params.width = LayoutParams.MATCH_PARENT;
		params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
	}

}
