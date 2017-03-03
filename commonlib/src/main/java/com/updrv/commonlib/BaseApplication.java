package com.updrv.commonlib;

import android.app.Application;
import android.os.StrictMode;

public class BaseApplication extends Application{

    /**
     * 是否显示log
     */
    private boolean showLog;
    /**
     * 是否是测试版本
     */
    private boolean isTestVersion = true;

    private CrashHandler crashHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        crashHandler = new CrashHandler() {
            @Override
            public boolean handleException(Throwable ex) {
                return handleGlobalException(ex);
            }

            @Override
            public boolean showException(Throwable ex) {
                return showGlobalException(ex);
            }
        };
        crashHandler.init(this);
        if(isTestVersion()){
            doSomeThingTestVersion();
        }else {
            doSomeThingNormalVersion();
        }
    }

    /**
     * 捕捉到了全局异常
     * @param ex
     * @return
     */
    protected boolean handleGlobalException(Throwable ex){
        return false;
    }

    /**
     *
     * @param ex
     * @return
     */
    protected boolean showGlobalException(Throwable ex){
        return false;
    }

    /**
     *
     * @return
     */
    private boolean isTestVersion(){
        return isTestVersion;
    }

    /**
   * 如果是测试版本，这里可以做些操作
     *
     * 默认操作：
     * 1：S执行trictMode严苛模式
     */
    protected void doSomeThingTestVersion(){

        StrictMode.setThreadPolicy((new StrictMode.ThreadPolicy.Builder()).detectDiskReads()

                .detectDiskWrites()

                .detectNetwork()

                .penaltyLog()

                .build());

        StrictMode.setVmPolicy((new android.os.StrictMode.VmPolicy.Builder()).detectLeakedSqlLiteObjects()

                .penaltyLog()

                .penaltyDeath()

                .build());
    }

    /**
     * 如果是正式版本，这里可以做些操作
     * 比如去掉控制log打印，切换正式测试服务器，
     */
    protected void doSomeThingNormalVersion(){

    }

}
