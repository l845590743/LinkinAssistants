package com.project.linkinassistant.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListView;
import android.widget.TextView;

import com.project.linkinassistant.R;
import com.project.linkinassistant.adapter.AppManagerAdapter;
import com.project.linkinassistant.bean.AppInfo;
import com.project.linkinassistant.utils.AppInfoProvider;
import com.project.linkinassistant.widget.ProgressInfoView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class AppManagerActivity extends Activity {
	private static final String TAG = "AppManagerActivity";

	private ProgressInfoView mPivMemory;
	private ProgressInfoView mPivSdcard;

	private ListView mListView;

	private List<AppInfo> mUserData;
	private List<AppInfo> mSysData;

	//记录是否分组信息切换了
	private boolean mHasSwitchHeaderInfo = false;

	//listview滑动事件
	private OnScrollListener mScrollListener = new OnScrollListener() {
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			// 滑动状态改变
			switch (scrollState) {
			case OnScrollListener.SCROLL_STATE_FLING:
				// 惯性滑动
				Log.d(TAG, "SCROLL_STATE_FLING");
				break;

			case OnScrollListener.SCROLL_STATE_IDLE:
				Log.d(TAG, "SCROLL_STATE_IDLE");
				// 空闲状态（静止）
				break;

			case OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
				Log.d(TAG, "SCROLL_STATE_TOUCH_SCROLL");
				// 触摸滑动
				break;

			default:
				break;
			}
		}

		//该方法会大量执行，非常精确
		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			//firstVisibleItem: 第一个能看到的item的下表索引
			//visibleItemCount: 屏幕上可以看到的item个数
			//totalItemCount: listview总共数据条数
			/*Log.d(TAG, "onScroll(), firstVisibleItem = " + firstVisibleItem
					+ ", visibleItemCount = " + visibleItemCount
					+ ", totalItemCount = " + totalItemCount);*/
			
			//一进入页面时，onScroll方法就会执行几次，这个时候可能还没有数据
			if (mUserData == null) {
				return;
			}
			
			//计算当header看不见的时候，第一个可视的item的索引:
			int headerItemInvisibleIndex = mUserData.size() + 1;
			if (firstVisibleItem >= headerItemInvisibleIndex) {
				Log.w(TAG, "分组header看不见了!");
				//文字切换为listview分组头的信息
				if (!mHasSwitchHeaderInfo) {
					Log.e(TAG, "mTvUserAppHeader.setText()");
					mTvUserAppHeader.setText(getString(R.string.app_manager_sys_app_header, mSysData.size()));
					mHasSwitchHeaderInfo = true;					
				}
			}else{
				Log.d(TAG, "分组header看的见");
				//切回自己的信息（用户应用个数信息）
				if (mHasSwitchHeaderInfo) {
					Log.e(TAG, "mTvUserAppHeader.setText()");
					mTvUserAppHeader.setText(getString(R.string.app_manager_user_app_header, mUserData.size()));
					mHasSwitchHeaderInfo = false;
				}
			}

		}
	};

	private TextView mTvUserAppHeader;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_app_manager);

		initView();
		initLisenter();
		updateView();
		loadData();

		// 测试代码
		// mPivMemory.setTitle("内存:");
		// mPivMemory.setLeft("100MB已用");
		// mPivMemory.setRight("100MB可用");
		// mPivMemory.setProgress(60);
	}

	private void initLisenter() {
		mListView.setOnScrollListener(mScrollListener );
	}

	private void loadData() {
		//获取所有安装的应用信息
		List<AppInfo> appInfos = AppInfoProvider.getInstalledAppInfos(this);
		//数据分组
		sortData(appInfos);
		
		//设置用户程序分组头文字信息
		//mTvUserAppHeader.setText("用户程序(" + mUserData.size() + "个)");
		mTvUserAppHeader.setText(getString(R.string.app_manager_user_app_header, mUserData.size()));
		//mTvUserAppHeader.setText(getString(R.string.app_manager_user_app_header2, 1, 1.2f, "DK"));
		//展示数据
		mListView.setAdapter(new AppManagerAdapter(this, mUserData, mSysData));
	}

	//数据分组
	private void sortData(List<AppInfo> appInfos) {
		mUserData = new ArrayList<AppInfo>();
		mSysData = new ArrayList<AppInfo>();
		
		for (AppInfo info : appInfos) {
			if (info.isSysApp()) {
				mSysData.add(info);
			}else{
				mUserData.add(info);
			}
		}
	}

	private void updateView() {
		//更新内部存储视图信息
		updateMemoryInfo();

		//更新sd卡视图信息
		updateSdcardInfo();
	}

	//更新内部存储视图信息
	private void updateMemoryInfo() {
		// 获取内部存储容量信息，即/data目录
		File dataDir = Environment.getDataDirectory();
		Log.d(TAG, "dataDir: " + dataDir);
		
		// 总空间大小
		long totalSpace = dataDir.getTotalSpace();
		// 剩余空间
		long freeSpace = dataDir.getFreeSpace();
		// 可用空间
		long usableSpace = dataDir.getUsableSpace();
		Log.d(TAG, "内部存储信息, 总大小：" + totalSpace + ", 剩余空间： " + freeSpace
				+ "， 可用空间： " + usableSpace);
		
		//计算已用空间
		long usedSpace = totalSpace - usableSpace;
		//字节转换成具体文件尺寸大小，要使用android.text.format.Formatter
		String usedSpaceStr = Formatter.formatFileSize(this, usedSpace);
		//可用空间size大小
		String usableSpaceStr = Formatter.formatFileSize(this, usableSpace);
		//进度  200 * 100 / 500
		int percent = (int) (usedSpace * 100f / totalSpace);
		//避免使用空间很小进度条上显示不出进度的情况
		if (percent == 0 && usedSpace > 0){
			percent = 1;
		}
		Log.d(TAG, "percent: " + percent);

		//更新控件信息
		mPivMemory.setTitle("内存:");
		mPivMemory.setLeft(usedSpaceStr + "已用");
		mPivMemory.setRight(usableSpaceStr + "可用");
		mPivMemory.setProgress(percent);
	}

	//更新sd卡视图信息
	private void updateSdcardInfo() {
		// 获取外部存储目录信息
		String state = Environment.getExternalStorageState();
		Log.d(TAG, "sdcard状态: " + state);
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			File sdcardRoot = Environment.getExternalStorageDirectory();
			// 总空间大小
			long totalSpace = sdcardRoot.getTotalSpace();
			// 剩余空间
			long freeSpace = sdcardRoot.getFreeSpace();
			// 可用空间
			long usableSpace = sdcardRoot.getUsableSpace();
			Log.d(TAG, "SD卡存储信息, 总大小：" + totalSpace + ", 剩余空间： " + freeSpace
					+ "， 可用空间： " + usableSpace);
			
			//计算已用空间
			long usedSpace = totalSpace - usableSpace;
			//字节转换成具体文件尺寸大小，要使用android.text.format.Formatter
			String usedSpaceStr = Formatter.formatFileSize(this, usedSpace);
			//可用空间size大小
			String usableSpaceStr = Formatter.formatFileSize(this, usableSpace);
			//进度  200 * 100 / 500
			int percent = (int) (usedSpace * 100f / totalSpace);
			Log.d(TAG, "percent: " + percent);
			//避免使用空间很小进度条上显示不出进度的情况
			if (percent == 0 && usedSpace > 0){
				percent = 1;
			}

			//更新控件信息
			mPivSdcard.setTitle("SD卡:");
			mPivSdcard.setLeft(usedSpaceStr + "已用");
			mPivSdcard.setRight(usableSpaceStr + "可用");
			mPivSdcard.setProgress(percent);
		}
	}

	private void initView() {
		mPivMemory = (ProgressInfoView) findViewById(R.id.app_manager_piv_memory);
		mPivSdcard = (ProgressInfoView) findViewById(R.id.app_manager_piv_sdcard);
		mTvUserAppHeader = (TextView) findViewById(R.id.app_manager_tv_user_app_header);
		mListView = (ListView) findViewById(R.id.app_manager_listview);
	}
}
