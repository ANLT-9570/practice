package com.dg.main.serviceImpl.orders.service.seller;

import com.dg.main.Entity.logs.OrderItemsLog;
import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.repository.log.OrderItemsRefundLogRepository;
import com.dg.main.serviceImpl.logs.OrderItemsLogService;
import com.dg.main.serviceImpl.logs.OrderItemsRefundLogService;
import com.dg.main.serviceImpl.logs.OrderStreamLogService;
import com.dg.main.serviceImpl.notification.JGSenderParam;
import com.dg.main.serviceImpl.notification.notification.JGNotificationService;
import com.dg.main.serviceImpl.orders.factory.OrderFactory;
import com.dg.main.serviceImpl.orders.service.OrderItemsService;
import com.dg.main.serviceImpl.orders.service.OrdersService;
import com.dg.main.serviceImpl.orders.wrapper.OrderBusinessSendedWrapper;
import com.dg.main.serviceImpl.orders.wrapper.OrderUpdateWrapper;
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
public class OrderBusinessSendedService {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private OrderStreamLogService orderStreamLogService;
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
        Gson gson=new Gson();
        Orders oldOrder=wrapper.getOrders();
        String users[]={};
       // Orders newOrder= OrderFactory.newInstance(oldOrder);
        List<OrderItems> oldOrderItems=wrapper.getOldItems();
        List<OrderItems> newOrderItems=wrapper.getNewItems();
        if(oldOrder!=null){
            ArraysUtil.append(users, JGSenderParam.PREVIOUS+oldOrder.getCustomerId());
            Orders newOrder= OrderFactory.newInstance(oldOrder);
            newOrder.setPhase(3);
          //  newOrder.set
            newOrder.setSendTime(new Timestamp(new Date().getTime()));
            newOrder.setModifyTime(new Timestamp(new Date().getTime()));
            newOrder.setScheduleTime(new Timestamp(DateUtils.addDay(new Date(),1).getTime()));
            ordersService.update(newOrder);

            for(int i=0;i<oldOrderItems.size();i++){
                OrderItems items=oldOrderItems.get(i);
                items.setPhase(3);
                items.setSendTime(new Timestamp(new Date().getTime()));
                items.setModifyTime(new Timestamp(new Date().getTime()));
                items.setScheduleTime(new Timestamp(DateUtils.addDay(new Date(),1).getTime()));
                items.setIsBusinessSendedInPhaseThree(1);
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
        param.setTitle("商家已发货");
        // param.setContent("用户已买单,请发货");
        notificationService.sendAll(param);
    }
}
