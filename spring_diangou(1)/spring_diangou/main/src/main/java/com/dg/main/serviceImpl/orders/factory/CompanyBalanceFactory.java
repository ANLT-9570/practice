package com.dg.main.serviceImpl.orders.factory;

import com.dg.main.Entity.settings.CompanyBalance;
import org.springframework.beans.BeanUtils;

public class CompanyBalanceFactory {
    public static CompanyBalance newInstance(CompanyBalance orders){
        CompanyBalance temp= new CompanyBalance();
        BeanUtils.copyProperties(orders,temp);
        return temp;
    }
}
