package com.dg.main.serviceImpl.orders.payment;

import com.dg.main.Entity.users.UserBalance;
import com.dg.main.serviceImpl.orders.service.PlatformPayService;
import com.dg.main.serviceImpl.orders.wrapper.OrdersWrapper;

import java.util.List;

public class PlatformPay implements IPay {
    private PlatformPayService platformPayService;
    private List<OrdersWrapper> ordersWrapper;

    public PlatformPay(PlatformPayService platformPayService) {
        this.platformPayService = platformPayService;

    }

    public void setOrdersWrapper(List<OrdersWrapper> ordersWrapper) {
        this.ordersWrapper = ordersWrapper;
    }
    private UserBalance userBalance;

    public void setUserBalance(UserBalance userBalance) {
        this.userBalance = userBalance;
    }

    @Override
    public void pay() {
        System.out.println("------------------platform pay-----------------------");
        platformPayService.action(ordersWrapper,userBalance);
    }
}
