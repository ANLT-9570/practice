package com.dg.main.serviceImpl.orders;

import com.dg.main.Entity.orders.Orders;
import com.dg.main.exception.BaseException;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.exception.orders.OrderStatusChangeException;
import com.dg.main.util.Tuple3;

public interface IStatusChange<T> {
    public  Tuple3<Boolean, BaseException,T> nextTo(T t);
    public static final IStatusChange<Orders>        ONE_TO_TWO_PHASE=orders -> {
//      if(orders.getPhase()!=1){
//          return new Tuple3<>(false,new OrderStatusChangeException(ExceptionCode.Failure.ORDER_STATUS_CHANGE),null);
//      }
      //  orders.setPhase(2);
      return new Tuple3<>(true,null,orders);
    };
    public static final IStatusChange<Orders> TWO_TO_THREE_PHASE=orders -> {
//      if(orders.getPhase()!=2){
//          return new Tuple3<>(false,new OrderStatusChangeException(ExceptionCode.Failure.ORDER_STATUS_CHANGE),null);
//      }
//      if(orders.getIsRefundInPhaseTwo()==1){
//          return new Tuple3<>(false,new OrderStatusChangeException(ExceptionCode.Failure.ORDERS_REFUND),null);
//      }
   //   orders.setPhase(3);
        return new Tuple3<>(true,null,orders);
    };
    public static final IStatusChange<Orders> THREE_TO_TEN_PHASE=orders -> {
//        if(orders.getPhase()!=3){
//            return new Tuple3<>(false,new OrderStatusChangeException(ExceptionCode.Failure.ORDER_STATUS_CHANGE),null);
//        }
      //  orders.setPhase(4);
        return new Tuple3<>(true,null,orders);
    };
}