package com.updrv.commonlib.bean;

/**
 * 基本的网络服务器返回类
 * Created by user on 2017/3/2.
 */

public class BaseResponseData<T> {
    private int code;
    private int message;
    private T data;
}
