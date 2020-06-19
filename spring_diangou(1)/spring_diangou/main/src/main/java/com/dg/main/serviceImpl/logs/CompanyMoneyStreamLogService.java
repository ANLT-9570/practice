package com.dg.main.serviceImpl.logs;


import com.dg.main.Entity.logs.CompanyMoneyStreamLog;
import com.dg.main.repository.log.CompanyMoneyStreamLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyMoneyStreamLogService {
   @Autowired
    CompanyMoneyStreamLogRepository repository;
   public void save(CompanyMoneyStreamLog item){
       repository.save(item);
   }
}
