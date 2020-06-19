package com.dg.main.serviceImpl.logs;

import com.dg.main.Entity.logs.CallbackFailureLog;
import com.dg.main.repository.log.CallbackFailureLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallbackFailureLogService {
    @Autowired
    CallbackFailureLogRepository repository;
   public void save(CallbackFailureLog item){
       repository.save(item);
   }
}
