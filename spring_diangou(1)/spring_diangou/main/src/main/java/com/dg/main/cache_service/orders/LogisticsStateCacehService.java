package com.dg.main.cache_service.orders;

import com.dg.main.Entity.orders.LogisticsState;
import com.dg.main.repository.LogisticsStateRepository;
import com.dg.main.util.Result;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class LogisticsStateCacehService {


    @Autowired
    private LogisticsStateRepository logisticsStateRepository;

    @Cacheable(value = "logisticsState",key = "#id")
    public Result get(Long id){
        logisticsStateRepository.findById(id);
        return Result.successResult();
    }

    @Cacheable(value = "logisticsState",key = "#logisticsState.id")
    public Result save(LogisticsState logisticsState){
        logisticsStateRepository.save(logisticsState);
        return Result.successResult();
    }

    @CachePut(value = "logisticsState",key = "#id")
    public Result update(LogisticsState logisticsState){
        logisticsStateRepository.save(logisticsState);
        return Result.successResult();
    }

    @CacheEvict(value = "logisticsState",key = "#id")
    public Result delete(Long id){
        logisticsStateRepository.deleteById(id);
        return Result.successResult();
    }


}
