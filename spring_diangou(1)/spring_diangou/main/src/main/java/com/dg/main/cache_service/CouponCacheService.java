package com.dg.main.cache_service;

import com.dg.main.Entity.users.Coupon;
import com.dg.main.repository.users.CouponRepository;
import com.dg.main.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponCacheService {

    @Autowired
    private CouponRepository couponRepository;

    @Cacheable(value = "cou",key="#userId")
    public List<Coupon> findByUserId(Long userId,Integer offset,Integer limit){
       return couponRepository.findByUserId(userId,offset,limit);
    }

    @Cacheable(value = "cou",key="#coupon.id")
    public Result save(Coupon coupon){
        couponRepository.save(coupon);
        return Result.successResult();
    }

    @CacheEvict(value = "cou",key = "#id")
    public Result delete(Long id){
        couponRepository.deleteById(id);
        return Result.successResult();
    }

    @CachePut(value = "cou",key = "#coupon.id")
    public Result update(Coupon coupon){
        couponRepository.save(coupon);
        return Result.successResult();
    }

}
