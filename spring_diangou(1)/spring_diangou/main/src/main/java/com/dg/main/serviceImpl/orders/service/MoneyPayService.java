package com.dg.main.serviceImpl.orders.service;

import com.dg.main.Entity.logs.OrderItemsLog;
import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.enums.UserStreamEnum;
import com.dg.main.repository.orders.OrderItemsRepository;
import com.dg.main.serviceImpl.logs.OrderItemsLogService;
import com.dg.main.serviceImpl.logs.OrderStreamLogService;
import com.dg.main.serviceImpl.logs.UserMoneyStreamLogService;
import com.dg.main.serviceImpl.orders.factory.OrderFactory;
import com.dg.main.serviceImpl.orders.factory.UserBalanceFactory;
import com.dg.main.serviceImpl.orders.wrapper.OrdersWrapper;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class MoneyPayService {
    @Autowired
    private UserBalanceService userBalanceService;
    @Autowired
    private UserMoneyStreamLogService userMoneyStreamLogService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    OrderItemsLogService orderItemsLogService;
    @Autowired
    OrderItemsService orderItemsService;
    @Transactional
    public void action(List<OrdersWrapper> ordersWrapper,UserBalance userBalance){
        Gson gson=new Gson();
        for(OrdersWrapper wrapper:ordersWrapper){
            Orders orders=wrapper.getOrders();
            orders.setPhase(2);
            orders.setIsCallbackSuccess(1);
            orders.setThirdOrdersNumber("");
            orders.setPayTime(new Timestamp(new Date().getTime()));
            orders.setModifyTime(new Timestamp(new Date().getTime()));
            UserBalance _oldBalance=userBalance;
            UserBalance _newBalance= UserBalanceFactory.newInstance(_oldBalance);
            _newBalance.setMoney(_oldBalance.getMoney()-orders.getTotalMoney());
            _newBalance.setModifyTime(new Timestamp(new Date().getTime()));
            _newBalance.setOperationTimes(_oldBalance.getOperationTimes()+1);
            userMoneyStreamLogService.saveUserBalances(_oldBalance,_newBalance,"MoneyPayService","action",
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
//        Orders _old=ordersWrapper.getOrders();
//        Orders _new= OrderFactory.newInstance(_old);
//        _new.setPhase(2);
//        _new.setIsCallbackSuccess(1);
//        _new.setThirdOrdersNumber("");
//        _new.setPayTime(new Timestamp(new Date().getTime()));
//        _new.setModifyTime(new Timestamp(new Date().getTime()));

     //  orderStreamLogService.saveOrders(_old,_new);
    }
//    @Transactional
//    public void listAction(ListOrdersWrapper listOrdersWrapper){
//        listOrdersWrapper.getOrders().stream().forEach(item->{
//            Orders _new=OrderFactory.newInstance(item);
////            _new.setPhase(2);
////            _new.setIsCallbackSuccess(1);
////            _new.setThirdOrdersNumber("");
////            _new.setPayTime(new Timestamp(new Date().getTime()));
////            _new.setModifyTime(new Timestamp(new Date().getTime()));
////            UserBalance _oldBalance=userBalanceService.findByUserId(item.getCustomerId());
////            UserBalance _newBalance= UserBalanceFactory.newInstance(_oldBalance);
////            _newBalance.setMoney(_oldBalance.getMoney()-item.getTotal());
////            _newBalance.setModifyTime(new Timestamp(new Date().getTime()));
////            _newBalance.setOperationTimes(_oldBalance.getOperationTimes()+1);
////            userMoneyStreamLogService.saveUserBalances(_oldBalance,_newBalance,"MoneyPayService","listAction");
////            userBalanceService.update(_newBalance);
//            ordersService.update(_new);
//            orderStreamLogService.saveOrders(item,_new);
//        });
//    }
}
