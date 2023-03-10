package com.base.order.service.impl;


import com.base.order.api.model.Order;
import com.base.order.mapper.OrderMapper;
import com.base.order.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiuyue
 * @since 2023-03-09
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
