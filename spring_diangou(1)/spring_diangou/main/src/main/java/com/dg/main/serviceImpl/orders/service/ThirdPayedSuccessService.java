package com.dg.main.serviceImpl.orders.service;

import com.dg.main.Entity.logs.OrderItemsLog;
import com.dg.main.Entity.logs.OrderStreamLog;
import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.serviceImpl.logs.OrderItemsLogService;
import com.dg.main.serviceImpl.logs.OrderStreamLogService;
import com.dg.main.serviceImpl.notification.JGSenderParam;
import com.dg.main.serviceImpl.notification.notification.JGNotificationService;
import com.dg.main.serviceImpl.orders.factory.OrderFactory;
import com.dg.main.serviceImpl.orders.wrapper.OrderUpdateWrapper;
import com.google.gson.Gson;
import jodd.util.ArraysUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Service
public class ThirdPayedSuccessService {
    @Autowired
    OrdersService ordersService;
    @Autowired
    OrderStreamLogService orderStreamLogService;
    @Autowired
    JGNotificationService notificationService;
    @Autowired
    OrderItemsService orderItemsService;
    @Autowired
    OrderItemsLogService orderItemsLogService;
    @Transactional
    public void action(List<OrderUpdateWrapper> wrappers,String code){
        Gson gson=new Gson();
        String users[]={};
        for (OrderUpdateWrapper wrapper:wrappers){
        Orders _old=wrapper.getOrders();
        Orders _new= OrderFactory.newInstance(_old);
            ArraysUtil.append(users, JGSenderParam.PREVIOUS+_old.getBusinessId());
        _new.setPhase(2);
        _new.setThirdCode(code);
        _new.setIsCallbackSuccess(1);
        _new.setPayTime(new Timestamp(new Date().getTime()));
        _new.setThirdOrdersNumber(_old.getThirdOrdersNumber());
        _new.setModifyTime(new Timestamp(new Date().getTime()));
        OrderStreamLog orderStreamLog=new OrderStreamLog();
        orderStreamLog.setBusinessId(_old.getBusinessId());
        orderStreamLog.setCustomerId(_old.getCustomerId());
        orderStreamLog.setOrderCode(_old.getOrderCode());
        orderStreamLog.setJsonStateOfModifiedOrders(gson.toJson(_new));
        orderStreamLog.setJsonStateOfOrders(gson.toJson(_old));
        ordersService.update(_new);
        orderStreamLogService.saveOrders(_old,_new);
                for (OrderItems items:wrapper.getNewItems()){
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
