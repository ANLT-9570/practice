package com.dg.main.serviceImpl.orders.update_status.buyer;

import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.exception.BaseException;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.exception.orders.OrderStatusChangeException;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.IStatusChange;
import com.dg.main.serviceImpl.orders.service.buyer.OrdersDeliveryService;
import com.dg.main.serviceImpl.orders.wrapper.OrderUpdateWrapper;
import com.dg.main.util.Tuple3;

import java.util.function.Function;

public class OrdersDeliveryProccess  extends BaseProccess {
    private final OrderUpdateWrapper wrapper;
//    private UserBalanceService userBalanceService;
//    private UserMoneyStreamLogService userMoneyStreamLogService;
//    private CompanyBalanceService companyBalanceService;
//    private OrdersService ordersService;
//    private OrderStreamLogService orderStreamLogService;
    private OrdersDeliveryService ordersDeliveryService;

    public OrdersDeliveryProccess(OrderUpdateWrapper wrapper, OrdersDeliveryService ordersDeliveryService) {
        this.wrapper = wrapper;
        this.ordersDeliveryService = ordersDeliveryService;
    }

    private static final Function<Orders,Boolean> checkState= orders -> {
        if(orders.getPhase()!=3){
            return false;
        }
        return true;
    };
    private static final Function<OrderItems,Boolean> checkIsSended=orders -> {
        if(orders.getIsBusinessSendedInPhaseThree()==1){
            return false;
        }
        return true;
    };
    private static final Function<Orders,Boolean> isCompleted=orders -> {
      if(orders.getPhase()==10){
          return false;
      }
      return true;
    };


    @Override
    public boolean validate() {
        if(wrapper.getOrders()!=null){


        if(!isCompleted.apply(wrapper.getOrders())){
            exceptions.add(new OrderStatusChangeException(ExceptionCode.Failure.ORDERS_COMPLETED));
            return false;
        }
        Tuple3<Boolean, BaseException,Orders> tuple3= IStatusChange.THREE_TO_TEN_PHASE.nextTo(wrapper.getOrders());
        if(!tuple3._1){
            exceptions.add(tuple3._2);
            return false;
           }
        for(OrderItems items:wrapper.getOldItems()){
            if(!checkIsSended.apply(items)){
                exceptions.add(new OrderStatusChangeException(ExceptionCode.Failure.ORDERS_COMPLETED));
                return false;
              }
           }
        }

        return true;
    }

    @Override
    public void action() {
        ordersDeliveryService.action(wrapper);
//        UserBalance _oldBalance=ordersDeliveryWrapper.getSellerBalance();
//        UserBalance _newBalance= UserBalanceFactory.newInstance(_oldBalance);
//        CompanyBalance companyBalance=ordersDeliveryWrapper.getCompanyBalance();
//        Orders _oldOrders=ordersDeliveryWrapper.getOrders();
//        Orders _newOrders= OrderFactory.newInstance(_oldOrders);
//        if(_oldOrders.getThirdPlatformAction()==1||_oldOrders.getThirdPlatformAction()==3||_oldOrders.getThirdPlatformAction()==4){
//            _newBalance.setMoney(_oldBalance.getMoney()+_oldBalance.getMoney());
//            _newBalance.setOperationTimes(_oldBalance.getOperationTimes()+1);
//            _newBalance.setModifyTime(new Timestamp(new Date().getTime()));
//        }else{
//            _newBalance.setPlatformMoney(_oldBalance.getPlatformMoney()+_oldOrders.getPlatformMoney());
//            _newBalance.setOperationTimes(_oldBalance.getOperationTimes()+1);
//            _newBalance.setModifyTime(new Timestamp(new Date().getTime()));
//            companyBalance.setPlatformMoney(companyBalance.getPlatformMoney()+_oldOrders.getLeftPlatformMoney());
//        }
//        _newOrders.setPhase(10);
//        _newOrders.setIsCustomerDeliveriedInPhaseThree(1);
//        _newOrders.setModifyTime(new Timestamp(new Date().getTime()));
//        ordersService.update(_newOrders);
//        userBalanceService.update(_newBalance);
//        companyBalanceService.update(companyBalance);
//        userMoneyStreamLogService.saveUserBalances(_oldBalance,_newBalance);
//        orderStreamLogService.saveOrders(_oldOrders,_newOrders);

    }

    @Override
    public void notifyChange() {

    }
}
