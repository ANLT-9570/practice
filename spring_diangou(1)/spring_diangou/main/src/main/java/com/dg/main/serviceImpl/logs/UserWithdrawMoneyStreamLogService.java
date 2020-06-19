package com.dg.main.serviceImpl.logs;


import com.dg.main.Entity.logs.UserWithdrawMoneyStreamLog;
import com.dg.main.repository.log.UserWithdrawMoneyStreamLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserWithdrawMoneyStreamLogService {
  @Autowired
    UserWithdrawMoneyStreamLogRepository repository;
  public void save(UserWithdrawMoneyStreamLog item){
      repository.save(item);
  }
}
