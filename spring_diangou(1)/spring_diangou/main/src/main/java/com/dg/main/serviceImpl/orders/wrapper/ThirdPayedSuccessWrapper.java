package com.dg.main.serviceImpl.orders.wrapper;

import com.dg.main.Entity.orders.Orders;

public class ThirdPayedSuccessWrapper {
    private final Orders orders;
    private final String thirdNumber;

    public String getThirdNumber() {
        return thirdNumber;
    }

    public ThirdPayedSuccessWrapper(Orders orders, String thirdNumber) {
        this.orders = orders;
        this.thirdNumber = thirdNumber;
    }

    public Orders getOrders() {
        return orders;
    }
}
