package com.project.linkinassistant.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import com.project.linkinassistant.bean.AppInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class AppInfoProvider {
	/**
	 * 获取所有安装的应用信息
	 * @param context
	 * @return
	 */
	public static List<AppInfo> getInstalledAppInfos(Context context){
		//获取所有安装的应用
		PackageManager pm = context.getPackageManager();
		//flag 传0表示获取基本信息（不获取四大组件信息）
		List<PackageInfo> infos = pm.getInstalledPackages(0);
		//int flag = PackageManager.GET_ACTIVITIES | PackageManager.GET_RECEIVERS;
		//List<PackageInfo> infos = pm.getInstalledPackages(flag);
		List<AppInfo> result = new ArrayList<AppInfo>();
		AppInfo tmp = null;
		for (PackageInfo info : infos) {
			ApplicationInfo applicationInfo = info.applicationInfo;
			//图标
			Drawable icon = applicationInfo.loadIcon(pm);
			
			//应用名
			String name = applicationInfo.loadLabel(pm).toString();
			
			boolean isSysApp = false;
			//判断是不是系统app
			if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == ApplicationInfo.FLAG_SYSTEM) {
				isSysApp = true;
			}
			
			//安装位置
			boolean isOnSdcard = false;
			if ((applicationInfo.flags & ApplicationInfo.FLAG_EXTERNAL_STORAGE) == ApplicationInfo.FLAG_EXTERNAL_STORAGE) {
				isOnSdcard = true;
			}
			
			//apk文件大小
			String apkPath = applicationInfo.sourceDir;
			//Log.d(TAG, "apkPath: " + apkPath);
			File apkFile = new File(apkPath);
			long size = apkFile.length();
			
			//保存获取到的信息
			tmp = new AppInfo();
			tmp.setIcon(icon);
			tmp.setName(name);
			tmp.setOnSDcard(isOnSdcard);
			tmp.setSysApp(isSysApp);
			tmp.setSize(size);
			//添加到集合
			result.add(tmp);
		}
		
		return result;
	}
}
