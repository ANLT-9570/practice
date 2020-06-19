package com.dg.main.cache_service.orders;

import com.dg.main.Entity.settings.PlatformRate;
import com.dg.main.repository.orders.PlatformRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PlatformRateCacheService {
    @Autowired
    PlatformRateRepository repository;
    @CachePut(value = "emp", key = "targetClass + #p0.id")
    public PlatformRate update(PlatformRate obj){
        repository.save(obj);
        return obj;
    }
    @Cacheable(value = "emp",key = "#id")
    public PlatformRate get(Integer id){
        return repository.getOne(id);
    }
    @Cacheable(value = "emp", key = "targetClass +#p0.id")//清空缓存
    public PlatformRate save(PlatformRate obj) {
        repository.save(obj);
        return obj;
    }
    @CacheEvict(value = "emp",key="#id")
    public void delete(Long id){
        repository.deleteById(id.intValue());
    }
    @CacheEvict(value = "accountCache",beforeInvocation = true)
    public void deleteAll(){
        repository.deleteAll();
    }
}
