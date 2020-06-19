package com.dg.main.serviceImpl.orders.service.buyer;

import com.dg.main.Entity.logs.OrderItemsLog;
import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.serviceImpl.logs.*;
import com.dg.main.serviceImpl.notification.JGSenderParam;
import com.dg.main.serviceImpl.notification.notification.JGNotificationService;
import com.dg.main.serviceImpl.orders.factory.OrderFactory;
import com.dg.main.serviceImpl.orders.service.OrderItemsService;
import com.dg.main.serviceImpl.orders.service.OrdersService;
import com.dg.main.serviceImpl.orders.wrapper.OrderUpdateWrapper;
import com.dg.main.serviceImpl.orders.wrapper.OrdersRefundWrapper;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.dg.main.util.DateUtils;
import com.google.gson.Gson;
import jodd.util.ArraysUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class OrdersRefundService {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    OrderItemsLogService orderItemsLogService;
    @Autowired
    OrderItemsService orderItemsService;
    @Autowired
    OrderItemsRefundLogService orderItemsRefundLogService;
    @Autowired
    JGNotificationService notificationService;
    @Transactional
    public void action(OrderUpdateWrapper wrapper){
//        Gson gson=new Gson();
//       // Orders oldOrders=ordersRefundWrapper.getOrders();
//        Orders newOrders= OrderFactory.newInstance(oldOrders);
//
//     //   UserBalance oldBalance=ordersRefundWrapper.getUserBalance();
//        UserBalance newBalance= UserBalanceFactory.newInstance(oldBalance);
//        newBalance.setModifyTime(new Timestamp(new Date().getTime()));
//        newBalance.setOperationTimes(oldBalance.getOperationTimes()+1);
//
////        if(oldOrders.getThirdPlatformAction()==1||oldOrders.getThirdPlatformAction()==3||oldOrders.getThirdPlatformAction()==4){
////            newBalance.setMoney(oldBalance.getMoney()+oldOrders.getMoney());
////        }else if(oldOrders.getThirdPlatformAction()==2){
////            newBalance.setPlatformMoney(oldBalance.getPlatformMoney()+oldBalance.getPlatformMoney());
////        }
//              //  if(oldOrders.getThirdPlatformAction()==1){
//          //  newBalance.setMoney(oldBalance.getMoney()+oldOrders.getTotal());
//        }else if(oldOrders.getThirdPlatformAction()==2){
//            newBalance.setPlatformMoney(oldBalance.getPlatformMoney()+oldOrders.getTotalPlatformMoney());
//        }
//
//      //  newOrders.setIsRefunding(1);
//        newOrders.setModifyTime(new Timestamp(new Date().getTime()));
//        newOrders.setRefundTime(new Timestamp(new Date().getTime()));
//        //newOrders.setIsRefundInPhaseOne(1);
//       // newOrders.setIsRefundInPhaseTwo(1);
//        newOrders.setIsRefundFinished(1);
//
//
////        OrderRefundLog orderRefundLog=new OrderRefundLog();
////        orderRefundLog.setMoney(newOrders.getMoney());
////        orderRefundLog.setPlatformMoney(newOrders.getPlatformMoney());
////        orderRefundLog.setIsRefund(1);
////        orderRefundLog.setOrderCode(newOrders.getOrderCode());
////        orderRefundLog.setJsonStateOfModifiedOrders(gson.toJson(newOrders));
////        orderRefundLog.setJsonStateOfOrders(gson.toJson(oldOrders));
//        List<OrderItems> items=orderItemsService.findByOrderId(oldOrders.getId());
//        for (OrderItems i:items){
//            i.setIsRefunding(1);
//            i.setModifyTime(new Timestamp(new Date().getTime()));
//            i.setRefundTime(new Timestamp(new Date().getTime()));
//            i.setIsRefundInPhaseOne(1);
//             i.setIsRefundInPhaseTwo(1);
//            i.setIsRefundFinished(1);
//        }
//        orderItemsService.updateBatch(items);
//
//        ordersService.update(newOrders);
//        userBalanceService.update(newBalance);
//
//
//
//      //  orderRefundLogService.save(orderRefundLog);
//        userMoneyStreamLogService.saveUserBalances(oldBalance,newBalance,"OrdersRefundService","action");
       // orderStreamLogService.saveOrders(oldOrders,newOrders);
    }
    @Transactional
    public void refund(OrderUpdateWrapper wrapper){
        Gson gson=new Gson();
        Orders oldOrder=wrapper.getOrders();
        // Orders newOrder= OrderFactory.newInstance(oldOrder);
        String users[]={};
        List<OrderItems> oldOrderItems=wrapper.getOldItems();
        List<OrderItems> newOrderItems=wrapper.getNewItems();
        if(oldOrder!=null){
            ArraysUtil.append(users, JGSenderParam.PREVIOUS+oldOrder.getBusinessId());
            Orders newOrder= OrderFactory.newInstance(oldOrder);
           // newOrder.setPhase(2);

            newOrder.setRefundTime(new Timestamp(new Date().getTime()));
            newOrder.setModifyTime(new Timestamp(new Date().getTime()));

           // newOrder.setScheduleTime(new Timestamp(DateUtils.addDay(new Date(),1).getTime()));
            ordersService.update(newOrder);

            for(int i=0;i<oldOrderItems.size();i++){
                OrderItems items=oldOrderItems.get(i);
           //     items.setPhase(3);
           //     items.setSendTime(new Timestamp(new Date().getTime()));
                items.setModifyTime(new Timestamp(new Date().getTime()));
                items.setRefundTime(new Timestamp(new Date().getTime()));
                items.setIsRefunding(1);
           //     items.setScheduleTime(new Timestamp(DateUtils.addDay(new Date(),1).getTime()));
                orderItemsService.update(items);
                OrderItemsLog log=new OrderItemsLog();
                log.setBusinessId(items.getSellerId());
                log.setCustomerId(items.getUserId());
                log.setOrderCode(items.getItemCode());
                log.setCurrentStatus(items.getPhase());
                log.setNewStatusOfItem(gson.toJson(items));
                log.setOldStatusOfItem(gson.toJson(newOrderItems.get(i)));
                orderItemsLogService.save(log);
            }

        }else{
            for(int i=0;i<oldOrderItems.size();i++){
                OrderItems items=oldOrderItems.get(i);
                items.setModifyTime(new Timestamp(new Date().getTime()));
                items.setRefundTime(new Timestamp(new Date().getTime()));
                items.setIsRefunding(1);
                orderItemsService.update(items);
                OrderItemsLog log=new OrderItemsLog();
                log.setBusinessId(items.getSellerId());
                log.setCustomerId(items.getUserId());
                log.setOrderCode(items.getItemCode());
                log.setCurrentStatus(items.getPhase());
                log.setNewStatusOfItem(gson.toJson(items));
                log.setOldStatusOfItem(gson.toJson(newOrderItems.get(i)));
                orderItemsLogService.save(log);
            }
        }
        System.out.println(users);
        JGSenderParam param=new JGSenderParam();
        param.setUserIds(users);
        param.setTitle("用户已退货");
        // param.setContent("用户已买单,请发货");
        notificationService.sendAll(param);
    }
    @Transactional
    public void thirdCallbackSuccess(OrdersRefundWrapper ordersRefundWrapper){
//        Gson gson=new Gson();
//        Orders oldOrders=ordersRefundWrapper.getOrders();
//        Orders newOrders= OrderFactory.newInstance(oldOrders);
////        newOrders.setIsRefunding(1);
////        newOrders.setModifyTime(new Timestamp(new Date().getTime()));
////        newOrders.setRefundTime(new Timestamp(new Date().getTime()));
////        newOrders.setIsRefundInPhaseOne(1);
////        newOrders.setIsRefundInPhaseTwo(1);
////        newOrders.setIsRefundFinished(1);
////
////
////        OrderRefundLog orderRefundLog=new OrderRefundLog();
////        orderRefundLog.setMoney(newOrders.getMoney());
////        orderRefundLog.setPlatformMoney(newOrders.getPlatformMoney());
////        orderRefundLog.setIsRefund(1);
////        orderRefundLog.setOrderCode(newOrders.getOrderCode());
////        orderRefundLog.setJsonStateOfModifiedOrders(gson.toJson(newOrders));
////        orderRefundLog.setJsonStateOfOrders(gson.toJson(oldOrders));
//
//
//        ordersService.update(newOrders);
      //  userBalanceService.update(newBalance);



//        orderRefundLogService.save(orderRefundLog);
       // userMoneyStreamLogService.saveUserBalances(oldBalance,newBalance,"OrdersRefundService","action");
      //  orderStreamLogService.saveOrders(oldOrders,newOrders);
    }

}
