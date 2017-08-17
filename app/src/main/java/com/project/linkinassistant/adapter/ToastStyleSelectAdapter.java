package com.project.linkinassistant.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.linkinassistant.R;
import com.project.linkinassistant.bean.AddressStyleInfo;

import java.util.List;


public class ToastStyleSelectAdapter extends BaseAdapter {
	private Context                mContext;
	private List<AddressStyleInfo> mData;

	public ToastStyleSelectAdapter(Context context,
			List<AddressStyleInfo> data) {
		super();
		this.mContext = context;
		this.mData = data;
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = View.inflate(mContext, R.layout.item_address_style_select, null);
		//初始化控件
		ImageView icon = (ImageView) view.findViewById(R.id.item_address_style_select_icon);
		TextView name = (TextView) view.findViewById(R.id.item_address_style_select_name);
		ImageView check = (ImageView) view.findViewById(R.id.item_address_style_select_check);
		
		//绑定数据 mData
		AddressStyleInfo data = mData.get(position);
		//icon.setImageResource(data.getColor()); 不要使用ImageResource
		icon.setBackgroundResource(data.getColor());
		name.setText(data.getName());
		if (data.isCheck()) {
			check.setVisibility(View.VISIBLE);
		}else{
			check.setVisibility(View.INVISIBLE);
		}
		
		return view;
	}
}
