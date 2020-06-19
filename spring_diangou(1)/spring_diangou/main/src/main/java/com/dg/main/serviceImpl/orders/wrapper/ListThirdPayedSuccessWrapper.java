package com.dg.main.serviceImpl.orders.wrapper;

import com.dg.main.Entity.orders.Orders;

import java.util.List;

public class ListThirdPayedSuccessWrapper {
    private final List<Orders> ordersList;

    public ListThirdPayedSuccessWrapper(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }
}
