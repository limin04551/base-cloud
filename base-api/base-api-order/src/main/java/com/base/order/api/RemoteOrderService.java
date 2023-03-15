package com.base.order.api;

import com.base.common.core.exception.base.BaseException;
import com.base.order.api.model.Order;
import jakarta.validation.ValidationException;


/**
 * @author min
 * @description
 * @date 2023/3/8
 */
public interface RemoteOrderService {

    /**
     * 创建订单
     */
    Order create(String userId, String commodityCode, int orderCount,int orderMoney) throws BaseException, ValidationException;

}
