package com.dg.main.serviceImpl.logs;

import com.dg.main.Entity.logs.MoneyTransToPlatformMoneyStreamLog;
import com.dg.main.repository.log.MoneyTransToPlatformMoneyStreamLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MoneyTransToPlatformMoneyStreamLogService {
    @Autowired
    MoneyTransToPlatformMoneyStreamLogRepository repository;
    public MoneyTransToPlatformMoneyStreamLog findBy(Long id) {

        return repository.getOne(id);
    }



    public void deleteBy(Long id){
        repository.deleteById(id);
    }


    public void  update(MoneyTransToPlatformMoneyStreamLog t) {

         repository.save(t);
    }


    public void save(MoneyTransToPlatformMoneyStreamLog t) {

        repository.save(t);
    }



    public List<MoneyTransToPlatformMoneyStreamLog> selectAll(MoneyTransToPlatformMoneyStreamLog t) {
        // TODO Auto-generated method stub
        return null;
    }



}
