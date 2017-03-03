package com.updrv.commonlib;

import android.content.Context;
import android.os.Looper;
import android.widget.Toast;

/**
 * 捕捉全局异常
 */
public abstract class CrashHandler implements Thread.UncaughtExceptionHandler {
    public static final String TAG = "CrashHandler";
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;

    public CrashHandler() {

    }

    public void init(Context ctx) {
        mContext = ctx;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, final Throwable ex) {
        System.out.println("uncaughtException");
        handleException(ex);
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                if (!showException(ex)) {
                    Toast.makeText(mContext, "程序出现错误，即将退出，请重新启动APP", Toast.LENGTH_SHORT).show();
                }
                showException(ex);
                Looper.loop();
                try {
                    sleep(3000);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
                //退出程序  
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        }.start();
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成. 开发者可以根据自己的情况来自定义异常处理逻辑
     *
     * @param
     * @return true:如果处理了该异常信息;否则返回false
     */
    public abstract boolean handleException(Throwable ex);

    public abstract boolean showException(Throwable ex);
}