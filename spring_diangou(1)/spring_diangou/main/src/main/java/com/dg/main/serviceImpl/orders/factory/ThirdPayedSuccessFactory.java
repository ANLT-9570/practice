package com.dg.main.serviceImpl.orders.factory;

import com.dg.main.Entity.orders.Orders;
import com.dg.main.serviceImpl.orders.update_status.ThirdPayedSuccessProccess;
import com.dg.main.serviceImpl.orders.wrapper.ThirdPayedSuccessWrapper;

public class ThirdPayedSuccessFactory {
    public static ThirdPayedSuccessWrapper newInstance(Orders orders,String thirdNumber){
        return new ThirdPayedSuccessWrapper(orders,thirdNumber);
    }
}
