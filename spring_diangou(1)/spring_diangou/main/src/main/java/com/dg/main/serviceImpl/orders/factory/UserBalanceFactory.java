package com.dg.main.serviceImpl.orders.factory;

import com.dg.main.Entity.users.UserBalance;
import org.springframework.beans.BeanUtils;

public class UserBalanceFactory {
    public static UserBalance newInstance(UserBalance item){
        UserBalance temp= new UserBalance();
        BeanUtils.copyProperties(item,temp);
        return temp;
    }
}
