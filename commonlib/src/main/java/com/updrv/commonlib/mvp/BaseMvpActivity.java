package com.updrv.commonlib.mvp;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


/**
 * activity基类
 *
 * @createDate 2016-3-8
 * @createTime 下午4:27:56
 * @author lcb
 */
public abstract class BaseMvpActivity<T extends BasePresenter, V extends BaseIView> extends Activity{

    protected Context mContext;

    protected T mPresenter;

    protected V mIView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView();
        findView();
        initView();
        mPresenter = initPresenter() ;
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
     * 初始化数据
     *
     * @createDate 2016-3-8
     * @createTime 下午4:14:13
     * @auther lcb
     */
    public abstract void initData();

    /**
     * 初始化IView
     * @return
     */
    public abstract V initIView();

    /**
     * 初始化Presenter
     * @return
     */
    public abstract T initPresenter();

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

    @Override
    protected void onStop() {
        super.onStop();
        if(mPresenter!=null){
            mPresenter.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.onDestroy();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(mPresenter!=null){
            mPresenter.onActivityResult(requestCode,resultCode,data);
        }
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
