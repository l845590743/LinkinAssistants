package com.project.linkinassistant.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.linkinassistant.R;
import com.project.linkinassistant.bean.HomeListBean;

import java.util.ArrayList;

/**
 * Created by lzm on 2017/8/16.
 */
public class HomeAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<HomeListBean> mHomeList;

    public HomeAdapter(Context context, ArrayList<HomeListBean> homeList) {
        this.mContext = context;
        this.mHomeList = homeList;
    }

    @Override
    public int getCount() {
        return mHomeList.size();
    }

    @Override
    public Object getItem(int position) {
        return mHomeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        if (convertView == null) {
            view = View.inflate(parent.getContext(), R.layout.item_home, null);
            viewHolder = new ViewHolder();
            viewHolder.icon = (ImageView) view.findViewById(R.id.item_home_icon);
            viewHolder.title = (TextView) view.findViewById(R.id.item_home_title);
            viewHolder.desc = (TextView) view.findViewById(R.id.item_home_desc);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        HomeListBean homeListBean = mHomeList.get(position);
        viewHolder.icon.setImageResource(homeListBean.getIcon());
        viewHolder.title.setText(homeListBean.getTitle());
        viewHolder.desc.setText(homeListBean.getDesc());
        return view;
    }

    class ViewHolder {
        ImageView icon;
        TextView title;
        TextView desc;
    }

}
