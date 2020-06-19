package com.dg.main.cache_service.orders;

import com.dg.main.Entity.settings.CompanyWithDrawPlatformMoneyRate;
import com.dg.main.repository.setting.CompanyWithDrawPlatformMoneyRateRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CompanyWithDrawPlatformMoneyRateCacheService {
    @Autowired
    CompanyWithDrawPlatformMoneyRateRepository repository;

    @Cacheable(value = "getBuyGoodsRateByTypeWithCache", key = "#type")
    public CompanyWithDrawPlatformMoneyRate getBuyGoodsRateByTypeWithCache(final Integer type){
        return repository.findByTypes(type);
    }
}
