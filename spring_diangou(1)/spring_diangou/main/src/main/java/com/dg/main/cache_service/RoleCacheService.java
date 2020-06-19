package com.dg.main.cache_service;

import com.dg.main.Entity.Role;
import com.dg.main.repository.RoleRepository;
import com.dg.main.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public class RoleCacheService {

    @Autowired
    RoleRepository roleRepository;

    @Cacheable(value = "role",key = "#id")
    public Result get(Long id){
        roleRepository.findById(id);
        return Result.successResult();
    }

    @Cacheable(value = "role",key = "#role.id")
    public Result save(Role role){
        roleRepository.save(role);
        return Result.successResult();
    }

    @CachePut(value = "role",key = "#role.id")
    public Result update(Role role){
        roleRepository.save(role);
        return Result.successResult();
    }

    @CacheEvict(value = "role",key = "#id")
    public Result delete(Long id){
        roleRepository.deleteById(id);
        return Result.successResult();
    }
}
