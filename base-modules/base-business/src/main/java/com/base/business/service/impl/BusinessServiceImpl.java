package com.base.business.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.lock.annotation.Lock4j;
import com.base.business.service.BusinessService;
import com.base.common.core.exception.base.BaseException;
import com.base.order.api.RemoteOrderService;
import com.base.order.api.model.Order;
import com.base.storage.api.RemoteStorageService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author min
 * @description
 * @date 2023/3/9
 */
@Service
@Slf4j
public class BusinessServiceImpl implements BusinessService {

    @DubboReference(retries = 0)//写操作重试除非实现幂等,否则会出现错误 如为-1默认值还是会重试2次 慎用重试 防止 retry storm
    private RemoteStorageService remoteStorageService;

    @DubboReference(retries = 0)
    private RemoteOrderService remoteOrderService;

    private static volatile int i = 0;

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
//    @Lock4j(keys = {"'user:'+#userId","'commodity:'+#commodityCode"},expire = -1, acquireTimeout = 3000)
    @Lock4j(expire = -1, acquireTimeout = 3000) //分布式锁 todo 需要实现颗粒度更细的锁,如商品锁,账户锁否则会大量阻塞
    public String purchase(String userId, String commodityCode, int orderCount, int money) {
        log.info("全局事务id:{}", RootContext.getXID());
        if (ObjectUtil.isNull(RootContext.getXID())) {
            throw new BaseException("分布式事务xid为:null");
        }
        Integer result = remoteStorageService.deduct(commodityCode, orderCount);

        Order order = remoteOrderService.create(userId, commodityCode, orderCount, money);
        try {
            if (i % 2 == 1) {
                log.info("模拟业务处理偶尔慢");
                Thread.sleep(200);
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        i++;
//        if(new Random().nextInt(10) <5 ){
//            throw new BaseException("模拟随机异常全局回滚");
//        }
        return String.format("剩余库存为:%s;订单信息:%s",result,order.toString());
    }
}
