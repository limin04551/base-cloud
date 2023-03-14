package com.base.business.test;

import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author limin
 * @description
 * @date 2023/3/13
 */
@Slf4j
public class Test {


    public static void main(String[] args) {
        for (int i = 0;i<40;i++){
            new Thread(() -> {
                log.info(HttpUtil.get("http://127.0.0.1:9001/business/purchase?orderCount=1&money=5&commodityCode=base1&userId=base"));
            }).start();
        }

    }
}
