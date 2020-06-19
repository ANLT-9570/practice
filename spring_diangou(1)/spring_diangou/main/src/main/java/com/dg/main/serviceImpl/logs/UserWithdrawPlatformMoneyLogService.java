package com.dg.main.serviceImpl.logs;

import com.dg.main.Entity.logs.UserWithdrawPlatformMoneyLog;
import com.dg.main.repository.log.UserWithdrawPlatformMoneyLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserWithdrawPlatformMoneyLogService{
    @Autowired
    UserWithdrawPlatformMoneyLogRepository repository;

//	slzcResult selectAll(@Param("offset")Integer offset, @Param("limit")Integer limit);
public UserWithdrawPlatformMoneyLog findBy(Long id) {

    return repository.getOne(id);
}





    public void save(UserWithdrawPlatformMoneyLog t) {

         repository.save(t);
    }



    public List<UserWithdrawPlatformMoneyLog> selectAll(UserWithdrawPlatformMoneyLog t) {
        // TODO Auto-generated method stub
        return null;
    }



}
