package com.dg.main.serviceImpl.orders.service.seller;

import com.dg.main.Entity.logs.OrderItemsLog;
import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.enums.UserStreamEnum;
import com.dg.main.serviceImpl.logs.OrderItemsLogService;
import com.dg.main.serviceImpl.logs.OrderItemsRefundLogService;
import com.dg.main.serviceImpl.logs.UserMoneyStreamLogService;
import com.dg.main.serviceImpl.orders.factory.OrderFactory;
import com.dg.main.serviceImpl.orders.factory.UserBalanceFactory;
import com.dg.main.serviceImpl.orders.service.OrderItemsService;
import com.dg.main.serviceImpl.orders.service.OrdersService;
import com.dg.main.serviceImpl.orders.wrapper.OrderUpdateWrapper;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.dg.main.util.DateUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class SellerAgreeInPhaseTwoService {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    OrderItemsLogService orderItemsLogService;
    @Autowired
    OrderItemsService orderItemsService;
    @Autowired
    OrderItemsRefundLogService orderItemsRefundLogService;
//
    @Autowired
    private UserBalanceService userBalanceService;
    @Autowired
    private UserMoneyStreamLogService userMoneyStreamLogService;
    @Transactional
    public void action(OrderUpdateWrapper wrapper){

        Gson gson=new Gson();
        Orders oldOrder=wrapper.getOrders();
        // Orders newOrder= OrderFactory.newInstance(oldOrder);
        List<OrderItems> oldOrderItems=wrapper.getOldItems();
        List<OrderItems> newOrderItems=wrapper.getNewItems();

            Orders newOrder= OrderFactory.newInstance(oldOrder);
            UserBalance oldBalance=userBalanceService.findByUserId(newOrder.getCustomerId());
            UserBalance newBalance= UserBalanceFactory.newInstance(oldBalance);
            newBalance.setModifyTime(new Timestamp(new Date().getTime()));
            newBalance.setOperationTimes(oldBalance.getOperationTimes()+1);

            if(newOrder.getThirdPlatformAction()==1){
                 newBalance.setMoney(oldBalance.getMoney()+newOrder.getTotalMoney());
            }else if(newOrder.getThirdPlatformAction()==2){
                 newBalance.setPlatformMoney(oldBalance.getPlatformMoney()+newOrder.getTotalPlatformMoney());
            }
            newOrder.setModifyTime(new Timestamp(new Date().getTime()));
            newOrder.setIsRefundFinished(1);

            ordersService.update(newOrder);
            userBalanceService.update(newBalance);
                    if(newOrder.getThirdPlatformAction()==1) {
            userMoneyStreamLogService.saveUserBalances(oldBalance, newBalance, "OrdersRefundService", "action",
            UserStreamEnum.BUYER_MONEY_REFUND_ORDERS.getIndex(), newOrder.getOrderCode());
        }else{
            userMoneyStreamLogService.saveUserBalances(oldBalance, newBalance, "OrdersRefundService", "action",
                    UserStreamEnum.BUYER_PLATFORM_MONEY_REFUND_ORDERS.getIndex(), newOrder.getOrderCode());
        }
       // orderStreamLogService.saveOrders(oldOrders,newOrders);
            for(int i=0;i<oldOrderItems.size();i++){
                OrderItems items=oldOrderItems.get(i);
                items.setModifyTime(new Timestamp(new Date().getTime()));
                items.setIsRefundFinished(1);
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




    @Transactional
    public void disagree(OrderUpdateWrapper wrapper){

        Gson gson=new Gson();
        Orders oldOrder=wrapper.getOrders();
        // Orders newOrder= OrderFactory.newInstance(oldOrder);
        List<OrderItems> oldOrderItems=wrapper.getOldItems();
        List<OrderItems> newOrderItems=wrapper.getNewItems();
        if(oldOrder!=null){
            Orders newOrder= OrderFactory.newInstance(oldOrder);
           // newOrder.setPhase(3);
       //     newOrder.setSendTime(new Timestamp(new Date().getTime()));
            newOrder.setModifyTime(new Timestamp(new Date().getTime()));
            newOrder.setIsRefundFinished(1);

            ordersService.update(newOrder);

            for(int i=0;i<oldOrderItems.size();i++){
                OrderItems items=oldOrderItems.get(i);
                items.setModifyTime(new Timestamp(new Date().getTime()));
                items.setIsRefundFinished(1);
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
                items.setIsRefundFinished(1);
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


//        OrderRefundLog orderRefundLog=new OrderRefundLog();
//        orderRefundLog.setMoney(newOrders.getMoney());
//        orderRefundLog.setPlatformMoney(newOrders.getPlatformMoney());
//        orderRefundLog.setIsRefund(2);
//        orderRefundLog.setOrderCode(newOrders.getOrderCode());
//        orderRefundLog.setJsonStateOfModifiedOrders(gson.toJson(newOrders));
//        orderRefundLog.setJsonStateOfOrders(gson.toJson(oldOrders));


       // ordersService.update(newOrders);
        //  userBalanceService.update(newBalance);



    //    orderRefundLogService.save(orderRefundLog);
     //    userMoneyStreamLogService.saveUserBalances(oldBalance,newBalance,"OrdersRefundService","action");
     //   orderStreamLogService.saveOrders(oldOrders,newOrders);
    }
    @Transactional
    public void thirdCallbackSuccess(OrderUpdateWrapper wrapper){
        Gson gson=new Gson();
        Orders oldOrder=wrapper.getOrders();
        // Orders newOrder= OrderFactory.newInstance(oldOrder);
        List<OrderItems> oldOrderItems=wrapper.getOldItems();
        List<OrderItems> newOrderItems=wrapper.getNewItems();
        if(oldOrder!=null){
            Orders newOrder= OrderFactory.newInstance(oldOrder);
            // newOrder.setPhase(3);
            //     newOrder.setSendTime(new Timestamp(new Date().getTime()));
            newOrder.setModifyTime(new Timestamp(new Date().getTime()));
            newOrder.setIsRefundFinished(1);

            ordersService.update(newOrder);

            for(int i=0;i<oldOrderItems.size();i++){
                OrderItems items=oldOrderItems.get(i);
                items.setModifyTime(new Timestamp(new Date().getTime()));
                items.setIsRefundFinished(1);
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
                items.setIsRefundFinished(1);
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
       // newOrders.setIsRefunding(1);
//        newOrders.setModifyTime(new Timestamp(new Date().getTime()));
//        newOrders.setRefundTime(new Timestamp(new Date().getTime()));
//     //   newOrders.setIsRefundInPhaseOne(1);
//    //    newOrders.setIsRefundInPhaseTwo(1);
//        newOrders.setIsRefundFinished(1);
//
//
//        OrderRefundLog orderRefundLog=new OrderRefundLog();
//        orderRefundLog.setMoney(newOrders.getMoney());
//        orderRefundLog.setPlatformMoney(newOrders.getPlatformMoney());
//        orderRefundLog.setIsRefund(1);
//        orderRefundLog.setOrderCode(newOrders.getOrderCode());
//        orderRefundLog.setJsonStateOfModifiedOrders(gson.toJson(newOrders));
//        orderRefundLog.setJsonStateOfOrders(gson.toJson(oldOrders));


        //ordersService.update(newOrders);
        //  userBalanceService.update(newBalance);



     //   orderRefundLogService.save(orderRefundLog);
        // userMoneyStreamLogService.saveUserBalances(oldBalance,newBalance,"OrdersRefundService","action");
     //   orderStreamLogService.saveOrders(oldOrders,newOrders);
    }
}
