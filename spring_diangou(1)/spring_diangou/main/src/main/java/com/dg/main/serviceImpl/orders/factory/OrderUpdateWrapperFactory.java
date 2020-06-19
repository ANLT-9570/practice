package com.dg.main.serviceImpl.orders.factory;

import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.orders.Orders;
import com.dg.main.serviceImpl.orders.service.OrderItemsService;
import com.dg.main.serviceImpl.orders.service.OrdersService;
import com.dg.main.serviceImpl.orders.wrapper.OrderBusinessSendedWrapper;
import com.dg.main.serviceImpl.orders.wrapper.OrderUpdateWrapper;
import com.dg.main.serviceImpl.orders.wrapper.OrdersRefundWrapper;
import org.apache.commons.compress.utils.Lists;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class OrderUpdateWrapperFactory {
    public static OrderUpdateWrapper setBuyerGetPackage(Orders orders,List<OrderItems> orderItems){
        List<OrderItems> _newItems= Lists.newArrayList();
        List<OrderItems> _oldItems=Lists.newArrayList();
       for(OrderItems items:orderItems){
           _oldItems.add(OrderFactory.newInstance(items));
           items.setPhase(10);
           items.setIsSellerDeliveriedInPhaseTen(1);
           items.setModifyTime(new Timestamp(new Date().getTime()));
           _newItems.add(items);
       }
        return new OrderUpdateWrapper(_newItems,_oldItems,orders);
    }

    public static OrderUpdateWrapper setSellerLogistics(String logisticsType, String logisticsCode,Orders orders,List<OrderItems> orderItems){
        List<OrderItems> _newItems= Lists.newArrayList();
        List<OrderItems> _oldItems=Lists.newArrayList();

       orders.setLogisticsCode(logisticsCode);
       orders.setLogisticsType(logisticsType);
       for(OrderItems items:orderItems){
           _oldItems.add(OrderFactory.newInstance(items));
           items.setLogisticsCode(logisticsCode);
           items.setLogisticsType(logisticsType);
           _newItems.add(items);
       }
        return new OrderUpdateWrapper(_newItems,_oldItems,orders);
    }
    public static OrderUpdateWrapper setBuyerReasonCode(String reason,Orders orders,List<OrderItems> items){
        List<OrderItems> _newItems= Lists.newArrayList();
        List<OrderItems> _oldItems=Lists.newArrayList();

        orders.setRefundReason(reason);

        for(OrderItems items1:items){
            _oldItems.add(OrderFactory.newInstance(items1));
            items1.setRefundReason(reason);
            items1.setRefundTime(new Timestamp(new Date().getTime()));
            items1.setIsRefunding(1);
            items1.setModifyTime(new Timestamp(new Date().getTime()));
            items1.setIsRefundInPhaseTwo(1);
            _newItems.add(items1);
        }
        return new OrderUpdateWrapper(_newItems,_oldItems,orders);
    }
    public static OrderUpdateWrapper setSellerAggree(Integer type,Orders orders,List<OrderItems> orderItems){
        List<OrderItems> _newItems= Lists.newArrayList();
        List<OrderItems> _oldItems=Lists.newArrayList();
        orders.setIsSellerAgreeInPhaseTwo(type);
       for(OrderItems items:orderItems){
           _oldItems.add(OrderFactory.newInstance(items));
           items.setModifyTime(new Timestamp(new Date().getTime()));
           items.setIsSellerAgreeInPhaseTwo(type);
           _newItems.add(items);

       }
        return new OrderUpdateWrapper(_newItems,_oldItems,orders);
    }
}
