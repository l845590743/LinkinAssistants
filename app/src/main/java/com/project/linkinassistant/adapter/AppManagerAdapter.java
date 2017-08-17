package com.project.linkinassistant.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.linkinassistant.R;
import com.project.linkinassistant.bean.AppInfo;

import java.util.List;


public class AppManagerAdapter extends BaseAdapter {
	//视图类型个数
	private static final int VIEW_TYPE_COUNT = 2;
	
	//分组类型
	//Integers must be in the range 0 to getViewTypeCount - 1. 
	//IGNORE_ITEM_VIEW_TYPE can also be returned
	//type值必须是从零开始的连续整数
	private static final int VIEW_TYPE_NORMAL = 0;
	private static final int VIEW_TYPE_HEADER = 1;
	
	
	private Context       mContext;
	private List<AppInfo> mUserData;
	private List<AppInfo> mSysData;

	public AppManagerAdapter(Context context, List<AppInfo> userData, List<AppInfo> sysData) {
		super();
		this.mContext = context;
		this.mUserData = userData;
		this.mSysData = sysData;
	}

	@Override
	public int getCount() {
		//中间多出的1表示分组头
		return mUserData.size() + 1 + mSysData.size();
	}

	//获取当前potision下对应的item的数据
	@Override
	public Object getItem(int position) {
		//例如如果position是3
		if (position < mUserData.size()) {
			//从用户应用里取数据
			return mUserData.get(position);
		}else{
			//从系统应用里取数据，多出的1表示分组头位置
			int offset = mUserData.size() + 1;
			return mSysData.get(position - offset);
		}
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	//给我们自己调用的
	@Override
	public int getItemViewType(int position) {
		if (position == mUserData.size()) {
			//header视图
			return VIEW_TYPE_HEADER;
		}else{
			//普通item视图
			return VIEW_TYPE_NORMAL;
		}
	}

	//告诉系统有几种视图
	@Override
	public int getViewTypeCount() {
		return VIEW_TYPE_COUNT;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		//区分当前item的视图类型
		int viewType = getItemViewType(position);
		if (viewType == VIEW_TYPE_NORMAL) {
			//创建普通视图
			return getNormalView(position, convertView, parent);
		}else{
			//创建分组头视图
			return getHeaderView(position, convertView, parent);
		}
	}

	//创建分组头视图
	private View getHeaderView(int position, View convertView, ViewGroup parent) {
		//代码实现方式
		// TextView tv = new TextView(mContext);
		// tv.setBackgroundColor(Color.parseColor("#999999"));
		// tv.setPadding(8, 8, 8, 8);
		// tv.setText("这里是header");
		
		//布局实现方式
		View view = View.inflate(mContext, R.layout.item_group_header, null);
		TextView tv = (TextView) view.findViewById(R.id.item_group_header_text);
		tv.setText(mContext.getString(R.string.app_manager_sys_app_header, mSysData.size()));
		return view;
	}

	//创建普通视图
	private View getNormalView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			convertView = View.inflate(mContext, R.layout.item_app_manager, null);
			
			holder = new ViewHolder();
			//初始化控件
			holder.icon = (ImageView) convertView.findViewById(R.id.item_app_manager_icon);
			holder.name = (TextView) convertView.findViewById(R.id.item_app_manager_name);
			holder.location = (TextView) convertView.findViewById(R.id.item_app_manager_location);
			holder.size = (TextView) convertView.findViewById(R.id.item_app_manager_size);
			//保存到view里
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		//绑定数据
		AppInfo data = (AppInfo) getItem(position);
		holder.icon.setImageDrawable(data.getIcon());
		holder.name.setText(data.getName());
		holder.location.setText(data.getLocationDesc() + ", " + data.isSysApp());
		holder.size.setText(data.getSizeStr(mContext));
		
		return convertView;
	}

	static class ViewHolder{
		ImageView icon;
		TextView name;
		TextView location;
		TextView size;
	}

}
