package com.project.linkinassistant.activity;


import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.project.linkinassistant.R;
import com.project.linkinassistant.bean.AppInfo;
import com.project.linkinassistant.utils.AppInfoProvider;

import java.util.ArrayList;
import java.util.List;

public class AntiVirusActivity extends Activity{

	private ListView mListView;
	private AntiVirusTask antiVirusTask;
	private List<AppInfo> mList      = new ArrayList<AppInfo>();
	private List<AppInfo> mAntiVirus = new ArrayList<AppInfo>();
//	private AntiVirusAdapter antiVirusAdapter;
//	private ArcProgress mProgressbar;
	private TextView mPackageName;
	private int mPbTotalSize;
	private LinearLayout mLLAgin;
	private LinearLayout mLLProgressbar;
	private TextView mAntiText;
	private int width;
	private LinearLayout mLLImageView;
	private ImageView mLeft;
	private ImageView mRight;
	private Button mBtnAgin;
	private DeleteReceiver deleteReceiver;


	private class DeleteReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
//			//删除已经卸载的应用的条目，刷新界面，更新重新扫描显示数据
//			mList.remove(antiVirusAdapter.getDeleteInfo());
//			antiVirusAdapter.notifyDataSetChanged();
//			//刷新重新扫描数据
//			mAntiVirus.remove(antiVirusAdapter.getDeleteInfo());
//			//判断，如果集合中还有数据，表示有病毒应用还没有卸载，还要显示相应的数据
//			if (mAntiVirus.size() > 0) {
//				mAntiText.setText("您的手机有"+ mAntiVirus.size() + "个病毒！！");
//			}else{
//				mAntiText.setText("您的手机很安全");
//			}
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_anti_virus);
		initView();
		initData();
		setDeleteReceiver();
	}
	/**
	 * @Description:设置监听卸载的广播接受者
	 * @param:
	 */
	private void setDeleteReceiver() {
		deleteReceiver = new DeleteReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
		filter.addDataScheme("package");
		registerReceiver(deleteReceiver, filter);
	}

	/**
	 * @Description:初始化控件
	 * @param:
	 */
	private void initView() {
		mListView = (ListView) findViewById(R.id.antivirus_lv_applications);
//		mProgressbar = (ArcProgress) findViewById(R.id.antivirus_ap_progressbar);
		mPackageName = (TextView) findViewById(R.id.antivirus_tv_packageName);
		mLLAgin = (LinearLayout) findViewById(R.id.antivirus_ll_agin);
		mLLProgressbar = (LinearLayout) findViewById(R.id.antivirus_ll_progressbar);
		mAntiText = (TextView) findViewById(R.id.antivirus_tv_antitext);

		mLLImageView = (LinearLayout) findViewById(R.id.antivirus_ll_imageview);
		mLeft = (ImageView) findViewById(R.id.antivirus_iv_left);
		mRight = (ImageView) findViewById(R.id.antivirus_iv_right);

		mBtnAgin = (Button) findViewById(R.id.antivirus_btn_agin);
//		mBtnAgin.setOnClickListener(this);
	}

	/**
	 * @Description:初始化数据
	 * @param:
	 */
	private void initData() {
		antiVirusTask = new AntiVirusTask();
		antiVirusTask.execute();
	}

	private class AntiVirusTask extends AsyncTask<Void, AppInfo, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();

			// 初始化集合及进度条进度，方便重新进行操作
			mList.clear();
			mAntiVirus.clear();
//			mProgressbar.setProgress(0);
//
//			//重新开始扫描的时候，隐藏图片及病毒信息布局，显示进度条布局
//			mLLImageView.setVisibility(View.GONE);
//			mLLAgin.setVisibility(View.GONE);
//			mLLProgressbar.setVisibility(View.VISIBLE);
//
//			antiVirusAdapter = new AntiVirusAdapter(AntiVirusActivity.this,
//					mList);
//			mListView.setAdapter(antiVirusAdapter);
		}

		@Override
		protected Void doInBackground(Void... params) {
			List<AppInfo> appInfos = AppInfoProvider
					.getInstalledAppInfos(AntiVirusActivity.this);
			mPbTotalSize = appInfos.size();
			for (AppInfo appInfo : appInfos) {

				if (antiVirusTask.isCancelled()) {
					return null;
				}
				
//				// 根据保存的应用程序的md5值，判断应用程序是否是病毒（查询数据库中是否有md5值）
//				boolean antiVirus = AntiVirusDao.isAntiVirus(
//						AntiVirusActivity.this, appInfo.getMd5());
//				// 将是否是病毒的标示保存到bean类，方便adapter进行使用
//				appInfo.setAntivirus(antiVirus);

				publishProgress(appInfo);

				SystemClock.sleep(300);
			}
			return null;
		}

		@Override
		protected void onProgressUpdate(AppInfo... values) {
			super.onProgressUpdate(values);
			if (antiVirusTask.isCancelled()) {
				return;
			}
			// 显示数据
//			AppInfo appInfo = values[0];
//			// 如果有病毒，在listview的顶部显示
//			if (appInfo.isAntivirus()) {
//				mList.add(0, appInfo);
//				// 如果是病毒，保存起来，方便扫描完成显示
//				mAntiVirus.add(appInfo);
//			} else {
//				mList.add(appInfo);
//			}
//			// 通过listview展示数据
//			antiVirusAdapter.notifyDataSetChanged();
//			mListView.smoothScrollToPosition(mList.size() - 1);
//
//			// 更新进度和显示扫描应用程序的包名
//			mProgressbar
//					.setProgress((int) (mList.size() * 100f / mPbTotalSize + 0.5f));
//			mPackageName.setText(appInfo.getPackageName());
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			if (antiVirusTask.isCancelled()) {
				return;
			}
			// 滚动到第一个条目
			mListView.smoothScrollToPosition(0);
			// 隐藏进度条布局，显示重新扫描布局
			mLLProgressbar.setVisibility(View.GONE);
			mLLAgin.setVisibility(View.VISIBLE);
			// 设置显示是否有病毒的信息.有病毒，显示有几个病毒，没有显示很安全
			mAntiText.setText(mAntiVirus.size() > 0 ? "您的手机有"
					+ mAntiVirus.size() + "个病毒！！" : "您的手机很安全");

			// 显示图片的布局
			mLLImageView.setVisibility(View.VISIBLE);

			// 执行动画的操作
			// 1.获取进度条布局的图片
			mLLProgressbar.setDrawingCacheEnabled(true);// 是否允许获取控件的缓存图片，enabled：true：允许获取，false:不允许
			mLLProgressbar
					.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);// 设置获取的图片的质量
			Bitmap drawingCache = mLLProgressbar.getDrawingCache();// 获取控件缓存的图片，保存到bitmap中
			// 2.拆分图片,要将图片拆分成两部分，分别保存在不同的bitmap中
			// 获取左边图片,设置给左边的imageview
			mLeft.setImageBitmap(getLeftBtimap(drawingCache));
			// 获取右边图片,设置给右边的imageview
			mRight.setImageBitmap(getRightBitmap(drawingCache));
			// 3.执行动画
			startAnimation();
		}

	}

	/**
	 * @Description:获取左边图片
	 * @param:
	 */
	public Bitmap getLeftBtimap(Bitmap drawingCache) {
		// 1.创建bitmap
		// 1.1获取新的bitmap的宽高
		width = (int) (drawingCache.getWidth() / 2 + 0.5f);
		int height = drawingCache.getHeight();
		// 1.2创建新的bitmap
		// config:bitmap的配置属性信息
		Bitmap createBitmap = Bitmap.createBitmap(width, height,
				drawingCache.getConfig());

		// 2.根据原图片绘制新的图片，存放到创建的新的bitmap中
		Canvas canvas = new Canvas(createBitmap);// 绘制图片，并设置绘制的图片保存到哪个bitmap中
		Paint paint = new Paint();// 创建画笔
		Matrix matrix = new Matrix();// 设置图片的矩阵排列，默认3x3
		// 绘制图片
		// bitmap : 根据什么样的图片来绘制新的图片
		canvas.drawBitmap(drawingCache, matrix, paint);
		return createBitmap;
	}

	/**
	 * @Description:获取右边图片
	 * @param:
	 */
	public Bitmap getRightBitmap(Bitmap drawingCache) {
		// 1.创建bitmap
		// 1.1获取新的bitmap的宽高
		int width = (int) (drawingCache.getWidth() / 2 + 0.5f);
		int height = drawingCache.getHeight();
		// 1.2创建新的bitmap
		// config:bitmap的配置属性信息
		Bitmap createBitmap = Bitmap.createBitmap(width, height,
				drawingCache.getConfig());

		// 2.根据原图片绘制新的图片，存放到创建的新的bitmap中
		Canvas canvas = new Canvas(createBitmap);// 绘制图片，并设置绘制的图片保存到哪个bitmap中
		Paint paint = new Paint();// 创建画笔
		Matrix matrix = new Matrix();// 设置图片的矩阵排列，默认3x3
		// 设置矩阵向右平移原图片宽度的一半
		// 正的：向左
		// 负的：向右
		matrix.postTranslate(-width, 0);
		// 绘制图片
		// bitmap : 根据什么样的图片来绘制新的图片
		canvas.drawBitmap(drawingCache, matrix, paint);
		return createBitmap;
	}

	/**
	 * @Description:执行动画操作
	 * @param:
	 */
	public void startAnimation() {
		// 1.平移动画，2.渐变动画
		// 两个imageview是平移+有不透明到透明的动画，重新扫描的布局是从透明到不透明的动画，这些动画一起执行的
		AnimatorSet animatorSet = new AnimatorSet();// 获取属性动画的集合
		// 创建所有的属性动画
		// mLeft.setTranslationX(translationX)
		// mLeft.setAlpha(alpha)
		// 左边的平移效果
		ObjectAnimator animator1 = ObjectAnimator.ofFloat(mLeft,
				"translationX", 0, -width);// target :
											// 执行动画的控件，propertyName：执行属性动画的类型，values：动画执行的参数
		// 右边的平移效果
		ObjectAnimator animator2 = ObjectAnimator.ofFloat(mRight,
				"translationX", 0, width);
		// 左边的渐变的效果
		ObjectAnimator animator3 = ObjectAnimator.ofFloat(mLeft, "alpha", 1.0f,
				0f);
		// 右边的渐变的效果
		ObjectAnimator animator4 = ObjectAnimator.ofFloat(mRight, "alpha",
				1.0f, 0f);
		// 重新扫描的渐变动画
		ObjectAnimator animator5 = ObjectAnimator.ofFloat(mLLAgin, "alpha", 0f,
				1.0f);
		animatorSet.playTogether(animator1, animator2, animator3, animator4,
				animator5);// 设置多个属性动画一起执行
		animatorSet.setDuration(3000);// 设置动画的持续时间
		animatorSet.start();// 执行属性动画
	}


	/**
	 * @Description:执行关闭重新扫描动画
	 * @param:
	 */
	private void closeAniamtion() {
		// 1.平移动画，2.渐变动画
		// 两个imageview是平移+有不透明到透明的动画，重新扫描的布局是从透明到不透明的动画，这些动画一起执行的
		AnimatorSet animatorSet = new AnimatorSet();// 获取属性动画的集合
		// 创建所有的属性动画
		// mLeft.setTranslationX(translationX)
		// mLeft.setAlpha(alpha)
		// 左边的平移效果
		ObjectAnimator animator1 = ObjectAnimator.ofFloat(mLeft,
				"translationX", -width , 0);// target :
											// 执行动画的控件，propertyName：执行属性动画的类型，values：动画执行的参数
		// 右边的平移效果
		ObjectAnimator animator2 = ObjectAnimator.ofFloat(mRight,
				"translationX", width, 0);
		// 左边的渐变的效果
		ObjectAnimator animator3 = ObjectAnimator.ofFloat(mLeft, "alpha", 0f,1.0f);
		// 右边的渐变的效果
		ObjectAnimator animator4 = ObjectAnimator.ofFloat(mRight, "alpha",0f, 1.0f);
		// 重新扫描的渐变动画
		ObjectAnimator animator5 = ObjectAnimator.ofFloat(mLLAgin, "alpha",1.0f, 0f);
		animatorSet.playTogether(animator1, animator2, animator3, animator4,
				animator5);// 设置多个属性动画一起执行
		animatorSet.setDuration(3000);// 设置动画的持续时间
		//动画执行结束，重新进行扫描操作
		animatorSet.addListener(new AnimatorListener() {
			
			@Override
			public void onAnimationStart(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				//重新扫描操作
				initData();
			}
			//动画取消的时候执行的操作
			@Override
			public void onAnimationCancel(Animator animation) {
				
			}
		});
		animatorSet.start();// 执行属性动画
	}

	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		antiVirusTask.cancel(true);
		unregisterReceiver(deleteReceiver);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
