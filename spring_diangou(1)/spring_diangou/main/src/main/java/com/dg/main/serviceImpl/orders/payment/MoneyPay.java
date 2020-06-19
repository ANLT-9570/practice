package com.dg.main.serviceImpl.orders.payment;

import com.dg.main.Entity.orders.Orders;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.serviceImpl.orders.factory.OrderFactory;
import com.dg.main.serviceImpl.orders.service.MoneyPayService;
import com.dg.main.serviceImpl.orders.wrapper.OrdersWrapper;
import com.dg.main.serviceImpl.orders.wrapper.ThirdPayedSuccessWrapper;
import com.dg.main.serviceImpl.users.UserBalanceService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class MoneyPay implements IPay {
    private List<OrdersWrapper> wrappers;
    private MoneyPayService moneyPayService;

    public MoneyPay(MoneyPayService moneyPayService) {
        this.moneyPayService = moneyPayService;

    }
    private UserBalance userBalance;
    private UserBalanceService userBalanceService;

    public UserBalance getUserBalance() {
        return userBalance;
    }

    public void setUserBalance(UserBalance userBalance) {
        this.userBalance = userBalance;
    }

    public UserBalanceService getUserBalanceService() {
        return userBalanceService;
    }

    public void setUserBalanceService(UserBalanceService userBalanceService) {
        this.userBalanceService = userBalanceService;
    }

    public void setOrdersWrapper(List<OrdersWrapper> wrappers) {
        this.wrappers = wrappers;
    }

    @Override
    public void pay() {
//        Orders _old=thirdPayedSuccessWrapper.getOrders();
//        Orders _new= OrderFactory.newInstance(_old);
//        _new.setPhase(2);
//        _new.setIsCallbackSuccess(1);
//        _new.setThirdOrdersNumber(thirdPayedSuccessWrapper.getThirdNumber());
//        _new.setModifyTime(new Timestamp(new Date().getTime()));
//        thirdPayedSuccessService.action(_old,_new);
        moneyPayService.action(wrappers,userBalance);
    }
}
