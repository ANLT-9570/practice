package com.dg.main.serviceImpl.logs;


import com.dg.main.Entity.logs.OrderRefundLog;
import com.dg.main.repository.log.OrderRefundLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderRefundLogService {
    @Autowired
    OrderRefundLogRepository repository;
   public void save(OrderRefundLog item){
       repository.save(item);
   }
    public void update(OrderRefundLog item){
        repository.save(item);
    }
}
