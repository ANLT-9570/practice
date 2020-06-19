package com.dg.main.cache_service.orders;

import com.dg.main.Entity.settings.BuyGoodsRate;

import com.dg.main.repository.setting.BuyGoodsRateRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class BuyGoodsRateCacheService {
    @Autowired
    BuyGoodsRateRepository repository;
    @Cacheable(value = "buyGoodsRateByTypeWithCache", key = "#type")
    public BuyGoodsRate getBuyGoodsRateByTypeWithCache(final Integer type){
        return repository.findByTypes(type);
    }
}
