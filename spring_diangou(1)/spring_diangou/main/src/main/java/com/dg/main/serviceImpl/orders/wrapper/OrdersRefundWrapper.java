package com.dg.main.serviceImpl.orders.wrapper;

import com.dg.main.Entity.orders.*;

import java.util.List;

public class OrdersRefundWrapper {
//    private final Orders newOrders;
//    private final Orders oldOrders;
    private final List<OrderItems> newItems;
    private final List<OrderItems> oldItems;
    private final Orders orders;

    public OrdersRefundWrapper(List<OrderItems> newItems, List<OrderItems> oldItems, Orders orders) {
        this.newItems = newItems;
        this.oldItems = oldItems;
        this.orders = orders;
    }

    public List<OrderItems> getNewItems() {
        return newItems;
    }

    public List<OrderItems> getOldItems() {
        return oldItems;
    }

    public Orders getOrders() {
        return orders;
    }
}
