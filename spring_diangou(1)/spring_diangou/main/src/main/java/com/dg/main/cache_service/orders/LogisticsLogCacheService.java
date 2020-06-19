package com.dg.main.cache_service.orders;

import com.dg.main.Entity.orders.LogisticsLog;
import com.dg.main.repository.log.LogisticsLogRepository;
import com.dg.main.util.Result;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class LogisticsLogCacheService {

    @Autowired
    LogisticsLogRepository logisticsLogRepository;

    @Cacheable(value = "logistics",key = "#id")
    public Result get(Long id){
        logisticsLogRepository.findById(id);
        return Result.successResult();
    }

    @CacheEvict(value = "logistics",key = "#id")
    public Result delete(Long id){
        logisticsLogRepository.deleteById(id);
        return Result.successResult();
    }

    @CachePut(value = "logistics",key = "#logisticsLog.id")
    public Result update(LogisticsLog logisticsLog){
        logisticsLogRepository.save(logisticsLog);
        return Result.successResult();
    }

    @Cacheable(value = "logistics",key = "#logisticsLog.id")
    public  Result save(LogisticsLog logisticsLog){
        logisticsLogRepository.save(logisticsLog);
        return Result.successResult();
    }


}
