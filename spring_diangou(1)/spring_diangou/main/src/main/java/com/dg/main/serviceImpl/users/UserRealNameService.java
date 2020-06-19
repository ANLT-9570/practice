package com.dg.main.serviceImpl.users;

import com.dg.main.Entity.users.UserRealName;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.users.UserRealNameRepository;
import com.dg.main.util.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UserRealNameService {
    @Autowired
    UserRealNameRepository realNameRepository;
   public UserRealName findByUserId(Long userId){
        return realNameRepository.findByUserId(userId);
    }


    public Result save(UserRealName userRealName) {
       if (userRealName == null){
           return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
       }
        Long userId = userRealName.getUserId();
        if (userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USERID);
        }
        UserRealName NewUserRealName = realNameRepository.findByUserId(userId);
        if(NewUserRealName != null){
            return Result.failureResult(ExceptionCode.Failure.ALREADY_AUTHEN);
        }
        realNameRepository.save(userRealName);
       return Result.successResult();
    }


    public Result delRealName(Long id) {
        if (id == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
       realNameRepository.deleteById(id);
        return Result.successResult();
    }


    @Transactional
    public Result delByUserIdRealName(Long userid) {
        if (userid == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        realNameRepository.deleteByUserId(userid);
       return Result.successResult();
    }

    public Result updateRealName(UserRealName userRealName) {
        if (userRealName == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        Long id = userRealName.getUserId();
        UserRealName realName = realNameRepository.findByUserId(id);

        if(realName.getStatus()==1){
            return Result.failureResult(ExceptionCode.Failure.ALREADY_AUTHEN);
        }
        realName.setBirthDay(userRealName.getBirthDay());
        realName.setCompanyCode(userRealName.getCompanyCode());
        realName.setCompanyLicenseImg(userRealName.getCompanyLicenseImg());
        realName.setEmail(userRealName.getEmail());
        realName.setIsCompany(userRealName.getIsCompany());
        realName.setIDcar(userRealName.getIDcar());
        realName.setNegImg(userRealName.getNegImg());
        realName.setPhone(userRealName.getPhone());
        realName.setSelfieImg(userRealName.getSelfieImg());
        realName.setOppImg(userRealName.getOppImg());
        realName.setSex(userRealName.getSex());
        realName.setStatus(3);

        realNameRepository.save(realName);
        return Result.successResult();
    }

    public Result getIdRealName(Long id) {
        if (id == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        Optional<UserRealName> optional = realNameRepository.findById(id);
        if (!optional.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_PERMISSION);
        }
        return Result.successResult(optional.get());
    }
}
