package com.dg.main.serviceImpl.orders.create;

import com.alipay.api.AlipayApiException;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.service.CreateOrdersByItemCodeService;
import com.dg.main.serviceImpl.orders.wrapper.OrdersWrapper;

import java.io.IOException;

public class CreateOrdersByItemCode extends BaseProccess {
    final private OrdersWrapper wrapper;
    final private CreateOrdersByItemCodeService service;

    public CreateOrdersByItemCode(OrdersWrapper wrapper, CreateOrdersByItemCodeService service) {
        this.wrapper = wrapper;
        this.service = service;
    }

    @Override
    public boolean validate() {
        return true;
    }
    public OrdersWrapper getOrdersWrapper() {
        return wrapper;
        // return null;
    }
    @Override
    public void action() throws IOException, AlipayApiException {
            service.action(wrapper);
    }

    @Override
    public void notifyChange() {

    }
}
