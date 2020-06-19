package com.dg.main.serviceImpl.orders.wrapper;

import com.dg.main.Entity.orders.OrderItems;
import com.dg.main.Entity.orders.Orders;

import java.util.List;

public class OrderUpdateWrapper {
    private final List<OrderItems> newItems;
    private final List<OrderItems> oldItems;
    private final Orders orders;

    public OrderUpdateWrapper(List<OrderItems> newItems, List<OrderItems> oldItems, Orders orders) {
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
