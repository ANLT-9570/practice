package com.dg.main.serviceImpl.logs;

import com.dg.main.Entity.logs.SellerBuyUseThirdPayLog;
import com.dg.main.repository.log.SellerBuyUseThirdPayLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerBuyUseThirdPayLogService {
        @Autowired
    SellerBuyUseThirdPayLogRepository repository;
        public void save(SellerBuyUseThirdPayLog item){
            repository.save(item);
        }
}
