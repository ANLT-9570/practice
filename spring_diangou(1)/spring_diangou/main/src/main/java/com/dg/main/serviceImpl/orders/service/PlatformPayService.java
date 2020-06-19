package com.dg.main.serviceImpl.orders.service;

import com.dg.main.Entity.logs.OrderItemsLog;
import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.enums.UserStreamEnum;
import com.dg.main.serviceImpl.logs.OrderItemsLogService;
import com.dg.main.serviceImpl.logs.OrderStreamLogService;
import com.dg.main.serviceImpl.logs.UserMoneyStreamLogService;
import com.dg.main.serviceImpl.notification.JGSenderParam;
import com.dg.main.serviceImpl.notification.notification.JGNotificationService;
import com.dg.main.serviceImpl.orders.factory.OrderFactory;
import com.dg.main.serviceImpl.orders.factory.UserBalanceFactory;
import com.dg.main.serviceImpl.orders.wrapper.OrdersWrapper;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.google.gson.Gson;
import jodd.util.ArraysUtil;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class PlatformPayService {
    @Autowired
    private UserBalanceService userBalanceService;
    @Autowired
    private UserMoneyStreamLogService userMoneyStreamLogService;
    @Autowired
    private OrdersService ordersService;

    @Autowired
    OrderItemsService orderItemsService;
    @Autowired
    OrderItemsLogService orderItemsLogService;
    @Autowired
    JGNotificationService notificationService;
    @Transactional
    public void action(List<OrdersWrapper> ordersWrapper,UserBalance userBalance){
        Gson gson=new Gson();
        String users[]={};
        for(OrdersWrapper wrapper:ordersWrapper){
            Orders orders=wrapper.getOrders();
            orders.setPhase(2);
            ArraysUtil.append(users, JGSenderParam.PREVIOUS+orders.getBusinessId());
            orders.setIsCallbackSuccess(1);
            orders.setThirdOrdersNumber("");
            orders.setPayTime(new Timestamp(new Date().getTime()));
            orders.setModifyTime(new Timestamp(new Date().getTime()));
            UserBalance _oldBalance=userBalance;
            UserBalance _newBalance= UserBalanceFactory.newInstance(_oldBalance);
            _newBalance.setPlatformMoney(_oldBalance.getPlatformMoney()-orders.getTotalUserPayPlatformMoney());
            _newBalance.setModifyTime(new Timestamp(new Date().getTime()));
            _newBalance.setOperationTimes(_oldBalance.getOperationTimes()+1);
            userMoneyStreamLogService.saveUserBalances(_oldBalance,_newBalance,"PlatformPayService","action",
                    UserStreamEnum.BUYER_MONEY_PAY_ORDERS.getIndex(),orders.getOrderCode());
            userBalanceService.update(_newBalance);
            ordersService.update(orders);
            for(OrderItems items:wrapper.getItems()){
                OrderItems oldItems=OrderFactory.newInstance(items);
                items.setPhase(2);
                items.setIsCallbackSuccess(1);
                //  items.setThirdOrdersNumber("");
                items.setPayTime(new Timestamp(new Date().getTime()));
                items.setModifyTime(new Timestamp(new Date().getTime()));
                orderItemsService.update(items);
                OrderItemsLog log=new OrderItemsLog();
                log.setBusinessId(items.getSellerId());
                log.setCustomerId(items.getUserId());
                log.setOrderCode(items.getItemCode());
                log.setCurrentStatus(items.getPhase());
                log.setNewStatusOfItem(gson.toJson(items));
                log.setOldStatusOfItem(gson.toJson(oldItems));
                orderItemsLogService.save(log);
            }
        }
        System.out.println(users);
        JGSenderParam param=new JGSenderParam();
        param.setUserIds(users);
        param.setTitle("用户已买单,请发货");
       // param.setContent("用户已买单,请发货");
        notificationService.sendAll(param);

    }

}
