package com.base.business.test;

import cn.hutool.http.HttpUtil;

/**
 *@description
 *@author limin
 *@date 2023/3/11
 */
public class AsyncThread extends Thread{
    @Override
    public void run() {
        String res =   HttpUtil.get("http://127.0.0.1:8080/base-business/business/purchase?orderCount=1&money=5&commodityCode=base1&userId=base");
        System.out.println(res);
    }

    public static void main(String[] args) {

        // 创建异步线程
        AsyncThread asyncThread1 = new AsyncThread();
        // 启动异步线程
        asyncThread1.start();

        AsyncThread asyncThread2 = new AsyncThread();
        // 启动异步线程
        asyncThread2.start();

    }

}