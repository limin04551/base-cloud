package com.base.order.dubbo;

import com.base.account.api.RemoteAccountService;
import com.base.order.api.RemoteOrderService;
import com.base.order.api.model.Order;
import com.base.order.mapper.OrderMapper;
import com.base.order.service.OrderService;
import io.seata.core.context.RootContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * @author min
 * @description
 * @date 2023/3/9
 */
@RequiredArgsConstructor
@Service
@DubboService
@Slf4j
public class RemoteOrderServiceImpl implements RemoteOrderService {

    @DubboReference
    private RemoteAccountService accountService;

    private final OrderService orderService;

    @Override
    public Order create(String userId, String commodityCode, int orderCount, int orderMoney) {
        log.info("全局事务id:{}" , RootContext.getXID());
        accountService.debit(userId, orderMoney);

        Order order = new Order().setUserId(userId)
                .setCommodityCode(commodityCode)
                .setCount(orderCount)
                .setMoney(orderMoney);


        orderService.save(order);
        return order;
    }
}
