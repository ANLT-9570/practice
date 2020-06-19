package com.dg.main.serviceImpl.orders.service;

import com.dg.main.Entity.orders.UserMoneyTransToPlatformMoney;
import com.dg.main.repository.orders.UserMoneyTransToPlatformMoneyRepository;
import com.dg.main.util.slzcResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMoneyTransToPlatformMoneyService {
    @Autowired
    UserMoneyTransToPlatformMoneyRepository repository;
    public void save(UserMoneyTransToPlatformMoney item){
        repository.save(item);
    }
    public void update(UserMoneyTransToPlatformMoney item){
        repository.save(item);
    }
}
