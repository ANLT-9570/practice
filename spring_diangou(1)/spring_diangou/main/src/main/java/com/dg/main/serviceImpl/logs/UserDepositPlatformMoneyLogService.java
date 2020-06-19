package com.dg.main.serviceImpl.logs;

import com.dg.main.Entity.logs.UserDepositPlatformMoneyLog;
import com.dg.main.repository.log.UserDepositPlatformMoneyLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDepositPlatformMoneyLogService {
    @Autowired
    UserDepositPlatformMoneyLogRepository repository;
    public void save(UserDepositPlatformMoneyLog item){
        repository.save(item);
    }
    public void update(UserDepositPlatformMoneyLog item){
        repository.save(item);
    }
}
