package com.dg.main.serviceImpl.orders.factory;


import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.orders.Orders;
import org.springframework.beans.BeanUtils;

public class OrderFactory {
    public static Orders newInstance(Orders orders){
        Orders temp= new Orders();
        BeanUtils.copyProperties(orders,temp);
        return temp;
    }
    public static OrderItems newInstance(OrderItems orderItems){
        OrderItems temp= new OrderItems();
        BeanUtils.copyProperties(orderItems,temp);
        return temp;
    }
}
