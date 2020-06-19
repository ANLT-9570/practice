package com.dg.main.serviceImpl.orders.service.buyer;

import com.dg.main.Entity.logs.OrderItemsLog;
import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.repository.orders.OrderItemsRepository;
import com.dg.main.repository.orders.OrdersRepository;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.serviceImpl.logs.OrderItemsLogService;
import com.dg.main.serviceImpl.orders.wrapper.OrdersWrapper;
import com.google.gson.Gson;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrdersUnPayedCreateService {
//    @Autowired
//    private OrdersService ordersService;
//    @Autowired
//    private SpecificationsServer specificationsServer;
//    @Autowired
//    private OrderRefundLogService orderRefundLogService;
//    @Autowired
//    private OrderStreamLogService orderStreamLogService;
//    @Autowired
//    UserCouponsMapper userCouponsMapper;
    @Autowired
    OrderItemsRepository orderItemsRepository;
    @Autowired
    OrdersRepository ordersRepository;
    @Autowired
    OrderItemsLogService orderItemsLogService;
    @Transactional
    public void save(List<OrdersWrapper> wrappers) {
        Gson gson=new Gson();
        for(OrdersWrapper wrapper:wrappers){
            Orders insideOrder=wrapper.getOrders();
            ordersRepository.save(insideOrder);
            for(OrderItems items:wrapper.getItems()){
                items.setOrdersId(insideOrder.getId());
                items.setGroupByField(items.getShopId()+"_"+insideOrder.getOrderCode());
                orderItemsRepository.save(items);
                OrderItemsLog log=new OrderItemsLog();
                log.setBusinessId(items.getSellerId());
                log.setCustomerId(items.getUserId());
                log.setOrderCode(items.getItemCode());
                log.setCurrentStatus(items.getPhase());
                log.setNewStatusOfItem(gson.toJson(items));
                log.setOldStatusOfItem(gson.toJson(items));
                orderItemsLogService.save(log);

            }
        }

    }

}
