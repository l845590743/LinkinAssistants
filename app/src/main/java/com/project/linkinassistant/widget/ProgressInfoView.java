package com.project.linkinassistant.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.project.linkinassistant.R;


public class ProgressInfoView extends LinearLayout {
	private ProgressBar mProgressBar;
	private TextView mTvTitle;
	private TextView mTvLeft;
	private TextView mTvRight;

	public ProgressInfoView(Context context) {
		this(context, null);
	}

	public ProgressInfoView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		//绑定布局
		//View view = View.inflate(context, R.layout.view_progress_info, null);
		//this.addView(view);
		//效果同上
		View view = View.inflate(context, R.layout.view_progress_info, this);
		
		mProgressBar = (ProgressBar) view.findViewById(R.id.view_progress_info_pbar);
		mTvTitle = (TextView) view.findViewById(R.id.view_progress_info_tv_title);
		mTvLeft = (TextView) view.findViewById(R.id.view_progress_info_tv_left);
		mTvRight = (TextView) view.findViewById(R.id.view_progress_info_tv_right);
	}
	
	//设置最左侧标题文字信息
	public void setTitle(String text){
		mTvTitle.setText(text);
	}
	
	//设置进度条左侧文字信息
	public void setLeft(String text){
		mTvLeft.setText(text);
	}
	
	//设置进度条右侧文字信息
	public void setRight(String text){
		mTvRight.setText(text);
	}
	
	//设置进度
	public void setProgress(int progress){
		mProgressBar.setProgress(progress);
	}
}
