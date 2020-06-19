package com.dg.main.cache_service.orders;

import com.dg.main.Entity.users.UserThirdAccount;
import com.dg.main.repository.users.UserThirdAccountRepository;
//import com.dg.main.repository.orders.UserThirdAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserThirdAccountCacheService {
    @Autowired
    UserThirdAccountRepository userThirdAccountRepository;
    @CachePut(value = "userThirdAccount",key = "#p0.userId")
   public UserThirdAccount update(UserThirdAccount obj){
        userThirdAccountRepository.save(obj);
       return obj;
   }
   @CachePut(value = "userThirdAccount",key = "#p0.userId")
   public UserThirdAccount save(UserThirdAccount obj){
       userThirdAccountRepository.save(obj);
        return obj;
   }
   @Cacheable(value = "userThirdAccount",key = "#id")
   public UserThirdAccount get(Long id){
        return userThirdAccountRepository.findByUserId(id);
   }
//   @CacheEvict(value = "userThirdAccount",key = "#id")
//    public void delete()
}
