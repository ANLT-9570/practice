package com.dg.main.serviceImpl.orders.wrapper;

import com.dg.main.Entity.shop.Specifications;
import com.dg.main.Entity.orders.*;
import org.apache.commons.compress.utils.Lists;

import java.util.List;

public class OrdersWrapper {

    private final Orders orders;
    private final List<OrderItems> items;



    public OrdersWrapper(Orders orders,List<OrderItems> items) {
        this.orders = orders;

        this.items=items;
    }

    public Orders getOrders() {
        return orders;
    }

    public List<OrderItems> getItems() {
        return items;
    }
}
