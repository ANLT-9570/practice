package com.dg.main.serviceImpl.logs;


import com.dg.main.Entity.logs.UserDepositMoneyLog;
import com.dg.main.repository.log.UserDepositMoneyLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDepositMoneyLogService {
    @Autowired
    UserDepositMoneyLogRepository repository;
  public void save(UserDepositMoneyLog item){
      repository.save(item);
  }
}
