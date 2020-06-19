package com.dg.main.serviceImpl.orders.update_status;

import com.dg.main.Entity.orders.Orders;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.exception.orders.OrderStatusChangeException;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.factory.OrderFactory;
import com.dg.main.serviceImpl.orders.service.ThirdPayedSuccessService;
import com.dg.main.serviceImpl.orders.wrapper.OrderUpdateWrapper;
import com.dg.main.serviceImpl.orders.wrapper.ThirdPayedSuccessWrapper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class ThirdPayedSuccessProccess extends BaseProccess {
    private final List<OrderUpdateWrapper> wrappers;
    private ThirdPayedSuccessService thirdPayedSuccessService;
    private final String code;

    public ThirdPayedSuccessProccess(List<OrderUpdateWrapper> wrappers, ThirdPayedSuccessService thirdPayedSuccessService,String code) {
        this.wrappers = wrappers;
        this.thirdPayedSuccessService = thirdPayedSuccessService;
        this.code=code;
    }
    private static final Function<Orders,Boolean> checkPhase=orders -> {
//        if(orders.getPhase()!=1){
//            return false;
//        }
        return true;
    };
    @Override
    public boolean validate() {
//        if(!checkPhase.apply(thirdPayedSuccessWrapper.getOrders())){
//            getException().add(new OrderStatusChangeException(ExceptionCode.Failure.ORDER_STATUS_CHANGE));
//            return false;
//        }
        return true;
    }

    @Override
    public void action() {

//        Orders _old=thirdPayedSuccessWrapper.getOrders();
//        Orders _new= OrderFactory.newInstance(_old);
//        _new.setPhase(2);
//        _new.setIsCallbackSuccess(1);
//        _new.setPayTime(new Timestamp(new Date().getTime()));
//        _new.setThirdOrdersNumber(_old.getThirdOrdersNumber());
//        _new.setModifyTime(new Timestamp(new Date().getTime()));
        thirdPayedSuccessService.action(wrappers,code);
    }

    @Override
    public void notifyChange() {

    }
}
