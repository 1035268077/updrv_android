package com.updrv.commonlib;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;


/**
 * activity基类
 *
 * @createDate 2016-3-8
 * @createTime 下午4:27:56
 * @author lcb
 */
public abstract class BaseActivity extends Activity{

    protected Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView();
        findView();
        initView();
        initListener();
        initData();
    }

    /**
     * 查找view
     *
     * @createDate 2016-3-8
     * @createTime 下午4:13:53
     * @auther lcb
     */
    public abstract void setContentView();


    /**
     * 查找view
     *
     * @createDate 2016-3-8
     * @createTime 下午4:13:53
     * @auther lcb
     */
    public abstract void findView();

    /**
     * 初始化view
     *
     * @createDate 2016-3-8
     * @createTime 下午4:14:05
     * @auther lcb
     */
    public abstract void initView();

    /**
     * 初始化监听器
     *
     * @createDate 2016-3-8
     * @createTime 下午4:14:13
     * @auther lcb
     */
    public abstract void initListener();

    /**
     * 初始化数据
     *
     * @createDate 2016-3-8
     * @createTime 下午4:14:13
     * @auther lcb
     */
    public abstract void initData();


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
     * 设置是否是沉浸式状态栏
     * @param on
     */
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 设置全屏
     */
    private void setFullScreen(){
        requestWindowFeature(Window.FEATURE_NO_TITLE); // 取消标题
    }



    class ConfigBuilder{

        /**
         * 是否是沉浸式状态栏目
         */
        private boolean isTranslucentStatus;
        /**
         * 是否全屏
         */
        private boolean isFullScreen;

        /**
         * 是否允许屏幕旋转
         */
        private boolean isAllowScreenRoate;

    }

}
