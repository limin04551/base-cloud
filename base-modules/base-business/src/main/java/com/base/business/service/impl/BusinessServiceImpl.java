package com.base.business.service.impl;

import com.base.business.service.BusinessService;
import com.base.order.api.RemoteOrderService;
import com.base.storage.api.RemoteStorageService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @author min
 * @description
 * @date 2023/3/9
 */
@Service
@Slf4j
public class BusinessServiceImpl implements BusinessService {

    @DubboReference
    private RemoteStorageService remoteStorageService;

    @DubboReference
    private RemoteOrderService remoteOrderService;

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public void purchase(String userId, String commodityCode, int orderCount, int money) {
        log.info("全局事务id:{}" , RootContext.getXID());
        remoteStorageService.deduct(commodityCode, orderCount);
        remoteOrderService.create(userId, commodityCode, orderCount, money);
    }
}
