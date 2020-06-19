package com.dg.main.serviceImpl.orders.service;

import com.dg.main.serviceImpl.orders.wrapper.OrdersWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateOrdersByItemCodeService {
    @Autowired
    OrderItemsService orderItemsService;
    @Autowired
    OrdersService ordersService;
    @Transactional
    public void action(OrdersWrapper wrapper){
        ordersService.saveOrders(wrapper);

    }
}
