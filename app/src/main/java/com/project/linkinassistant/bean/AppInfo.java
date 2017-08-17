package com.project.linkinassistant.bean;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.format.Formatter;


public class AppInfo {
	private Drawable icon; // 应用图标
	private String name;// 应用名
	private boolean isOnSDcard; // 安装位置
	private boolean isSysApp; // 是否是系统应用
	private long size;// apk文件大小

	public Drawable getIcon() {
		return icon;
	}

	public void setIcon(Drawable icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOnSDcard() {
		return isOnSDcard;
	}

	/**
	 * 获取安装位置描述信息
	 * 
	 * @return
	 */
	public String getLocationDesc() {
		if (this.isOnSDcard) {
			return "SD卡";
		} else {
			return "手机内存";
		}
	}

	public void setOnSDcard(boolean isOnSDcard) {
		this.isOnSDcard = isOnSDcard;
	}

	public boolean isSysApp() {
		return isSysApp;
	}

	public void setSysApp(boolean isSysApp) {
		this.isSysApp = isSysApp;
	}

	public long getSize() {
		return size;
	}

	/**
	 * 获取文件大小
	 * 
	 * @return
	 */
	public String getSizeStr(Context context) {
		return Formatter.formatFileSize(context, this.size);
	}

	public void setSize(long size) {
		this.size = size;
	}

}
