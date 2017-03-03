package com.updrv.commonlib.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.updrv.commonlib.WeakHandler;

/**
 * presenter基类
 *
 * @createDate 2016-3-8
 * @createTime 下午4:27:43
 * @author lcb
 */
public abstract class BasePresenter<T extends BaseIView> {

	protected Context mContext;

	protected WeakHandler mHandler;

	protected T mIView;

	public BasePresenter(Context mContext, T iView) {
		super();
		this.mContext = mContext;
		this.mIView = iView;
		mHandler = new WeakHandler(initHandler());
		initData();
		initListener();
	}

	/**
	 * 初始化数据
	 */
	protected abstract void initData();

	/**
	 * 初始化监听器
	 *
	 * @createDate 2016-3-8
	 * @createTime 下午4:14:13
	 * @auther lcb
	 */
	public abstract void initListener();

	/**
	 * onResume的时候调用
	 */
	protected void onResume(){

	}

	/**
	 * onPause的时候调用
	 */
	protected void onPause(){};

	/**
	 * onStop的时候调用
	 */
	protected void onStop(){};

	/**
	 * onDestroy的时候调用
	 */
	protected void onDestroy(){
		unRegisterListener();
	};

	/**
	 * 初始化handler
	 * @return
     */
	protected abstract Handler.Callback initHandler();

	/**
	 * 解除监听
	 */
	public abstract void unRegisterListener();


	/**
	 * 显示toast
	 * @param msg
     */
	protected void showToast(int msg){

	}

	/**
	 * 显示toast
	 * @param msg
	 */
	protected void showToast(String msg){

	}

	/**
	 * 显示toast
	 * @param msg
	 */
	protected void showToastLong(String msg){

	}

	/**
	 * 显示toast
	 * @param msg
	 */
	protected void showToastLong(int msg){

	}
	/**
	 * 启动一个activity，默认flag启动
	 * @param targetActivity
	 * @param bundle
     */
	protected void startActivity(Class<?> targetActivity, Bundle bundle){
		Intent intent = new Intent();
		intent.setClass(mContext,targetActivity);
		if(bundle!=null){
			intent.putExtras(bundle);
		}
		mContext.startActivity(intent);
	}

	/**
	 * 启动一个activity，并且需要有返回值，默认flag启动
	 * @param targetActivity
	 * @param bundle
	 */
	protected void startActivityForResult(Class<?> targetActivity, Bundle bundle,int requestCode){
		Intent intent = new Intent();
		intent.setClass(mContext,targetActivity);
		if(bundle!=null){
			intent.putExtras(bundle);
		}
		((Activity)mContext).startActivityForResult(intent,requestCode);
	}

	/**
	 * 其他activity返回监听
	 * @param requestCode
	 * @param resultCode
	 * @param data
     */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	}
}
