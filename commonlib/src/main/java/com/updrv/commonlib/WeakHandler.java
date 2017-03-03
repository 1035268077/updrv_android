package com.updrv.commonlib;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by lily on 2016/6/28.
 */
public class WeakHandler extends Handler {

    WeakReference<Callback> weakCallback;

    public WeakHandler(Callback callback) {
        super(callback);
        weakCallback = new WeakReference<Callback>(callback);
    }

    @Override
    public void handleMessage(Message msg) {
        if(weakCallback.get()!=null){
            weakCallback.get().handleMessage(msg);
        }
    }
}

