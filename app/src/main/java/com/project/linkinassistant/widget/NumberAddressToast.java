package com.project.linkinassistant.widget;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.TextView;

import com.project.linkinassistant.R;

public class NumberAddressToast {
	private static final String TAG = "NumberAddressToast";

	private TextView mTvAddress;
	private Context mContext;
	
	//窗口管理器
	private WindowManager mWM;
	//布局参数
	private final WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();
	
	private View mView;
	
	public NumberAddressToast(Context context){
		mContext = context;
		mWM = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
		
        final WindowManager.LayoutParams params = mParams;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                /*| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE*/
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        params.format = PixelFormat.TRANSLUCENT;
        //params.windowAnimations = com.android.internal.R.style.Animation_Toast;
        //TYPE_XX表示视图的类型或特性，能展示在哪里，能被谁覆盖
        //TYPE_TOAST优先级较低，会被电话窗口抢走触摸事件
        //params.type = WindowManager.LayoutParams.TYPE_TOAST; 
        //优先级比TYPE_TOAST高，能响应触摸事件，同时把电话窗口的焦点抢走了，需要设置FLAG_NOT_FOCUSABLE的flag参数。
        params.type = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE;
        params.setTitle("Toast");
		
		//初始化view
		mView = View.inflate(context, R.layout.view_number_address_toast, null);
		mTvAddress = (TextView) mView.findViewById(R.id.view_number_address_toast_tv_address);
		
		//设置触摸监听
		mView.setOnTouchListener(mTouchListener);
	}
	
	public void show(String address){
		//每次都更新文字内容
		mTvAddress.setText(address);
		
		//每次都更新背景颜色
		mView.setBackgroundResource(R.drawable.number_address_toast_bg_orange);

		//避免重复添加导致崩溃
		if (mView.getParent() == null) {
			mWM.addView(mView, mParams);
		}
	}
	
	public void hide(){
		//避免没被add过就移除导致崩溃
		if (mView.getParent() != null) {
			mWM.removeView(mView);
		}
	}
	
	private OnTouchListener mTouchListener = new OnTouchListener() {
		private float startX;
		private float startY;
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			Log.d(TAG, "onTouch()");
			
			int action = event.getAction();
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				//按下
				startX = event.getRawX();
				startY = event.getRawY();
				Log.d(TAG, "ACTION_DOWN, (" + startX + ", " + startY + ")");
				
				break;
				
			case MotionEvent.ACTION_MOVE:
				//移动
				float x = event.getRawX();
				float y = event.getRawY();
				Log.d(TAG, "ACTION_MOVE, (" + x + ", " + y + ")");
				//偏移位置
				float offsetX = x - startX;
				float offsetY = y - startY;
				
				//改变位置-增量更新位置
				mParams.x = (int) (mParams.x + offsetX);
				mParams.y = (int) (mParams.y + offsetY);
				mWM.updateViewLayout(mView, mParams);
				
				//更新start位置，当前move位置作为新的起点
				startX = x;
				startY = y;
				
				break;
				
			case MotionEvent.ACTION_UP:
				Log.d(TAG, "ACTION_UP");
				//抬起
				break;

			default:
				break;
			}
			
			return true;//返回true表示我们已经消费掉时间，不再传递事件
		}
	};
}
