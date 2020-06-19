package com.dg.main.serviceImpl.orders.update_status.seller;

import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.exception.BaseException;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.exception.orders.OrderStatusChangeException;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.IStatusChange;
import com.dg.main.serviceImpl.orders.service.seller.OrderBusinessSendedService;
import com.dg.main.serviceImpl.orders.wrapper.OrderUpdateWrapper;
import com.dg.main.util.Tuple3;

import java.util.function.Function;

public class OrderBusinessSendedProccess extends BaseProccess {
    private final OrderUpdateWrapper wrapper;
//    private OrdersService ordersService;
//    private OrderStreamLogService orderStreamLogService;
    private OrderBusinessSendedService orderBusinessSendedService;

    public OrderBusinessSendedProccess(OrderUpdateWrapper wrapper, OrderBusinessSendedService orderBusinessSendedService) {
        this.wrapper = wrapper;
        this.orderBusinessSendedService = orderBusinessSendedService;
    }

    private static final Function<Orders,Boolean> checkState= orders -> {
        if(orders.getPhase()!=2){
            return false;
        }
        return true;
    };
    private static final Function<OrderItems,Boolean> checkIsRefund=orders -> {
        if(orders.getIsRefundFinished()==1||orders.getIsRefundInPhaseTwo()==1){
            return false;
        }
        return true;
    };
    private static final Function<OrderItems,Boolean> isSended=orders -> {
        if(orders.getIsBusinessSendedInPhaseThree()==1){
            return false;
        }
        return true;
    };
    private static final Function<OrderItems,Boolean> checkItemState= orders -> {
        if(orders.getPhase()!=2){
            return false;
        }
        return true;
    };
    @Override
    public boolean validate() {
        if(wrapper.getOrders()!=null){

            Tuple3<Boolean, BaseException,Orders> check=IStatusChange.TWO_TO_THREE_PHASE.nextTo(wrapper.getOrders());
            if(!check._1){
                exceptions.add(check._2);
                return false;
            }
        }else{
            for(OrderItems items:wrapper.getOldItems()){
                if(!checkIsRefund.apply(items)){
                    exceptions.add(new OrderStatusChangeException(ExceptionCode.Failure.ORDERS_REFUND));
                    return false;
                }
                if(!isSended.apply(items)){
                    exceptions.add(new OrderStatusChangeException(ExceptionCode.Failure.ORDERS_SENDED));
                    return false;
                }
//                Tuple3<Boolean, BaseException,items> check=IStatusChange.TWO_TO_THREE_PHASE.nextTo(orderBusinessSendedWrapper.getOrders());
//                if(!check._1){
//                    exceptions.add(check._2);
//                    return false;
//                }
            }
        }


        return true;
    }

    @Override
    public void action() {
        orderBusinessSendedService.action(wrapper);

    }

    @Override
    public void notifyChange() {

    }
}
