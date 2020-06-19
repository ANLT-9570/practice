package com.dg.main.serviceImpl.users;

import com.dg.main.Entity.users.UserBankCard;
import com.dg.main.repository.orders.UserBalanceRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.repository.users.UserBankCardRepository;
import com.dg.main.util.Result;
import org.ini4j.spi.RegEscapeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserBankCardService {
    @Autowired
    MobileUserRepository mobileUserRepository;
    @Autowired
    UserBankCardRepository userBankCardRepository;
    public void save(UserBankCard item){
        userBankCardRepository.save(item);
    }
    public void update(UserBankCard item){
        userBankCardRepository.save(item);
    }
    public Result list(Long userId){
        return Result.successResult(userBankCardRepository.findByUserId(userId));
    }
    public Result deleteById(Long id){
        userBankCardRepository.deleteById(id);
        return Result.successResult();
    }

}
