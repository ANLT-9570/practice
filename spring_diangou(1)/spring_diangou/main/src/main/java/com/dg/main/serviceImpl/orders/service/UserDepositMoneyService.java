package com.dg.main.serviceImpl.orders.service;

import com.dg.main.Entity.orders.UserDepositMoney;
import com.dg.main.repository.orders.UserDepositMoneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDepositMoneyService {
    @Autowired
    UserDepositMoneyRepository repository;
    public void save(UserDepositMoney item){
        repository.save(item);
    }
    public UserDepositMoney  findByCode(String code){
        return repository.findByDepositCode(code);
    }
    public void update(UserDepositMoney item){
        repository.save(item);
    }

}
