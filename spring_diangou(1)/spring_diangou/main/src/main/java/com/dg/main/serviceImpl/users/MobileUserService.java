package com.dg.main.serviceImpl.users;

import com.dg.main.Entity.logs.RedPackLog;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.log.RedPackLogRepository;
import com.dg.main.repository.orders.UserBalanceRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.util.Result;
import com.dg.main.util.ValidateCode;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.Strings;
import org.ini4j.spi.RegEscapeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Service
public class MobileUserService {
    @Autowired
    MobileUserRepository repository;
    @Autowired
    UserBalanceRepository userBalanceRepository;
    @Autowired
    RedPackLogRepository redPackLogRepository;
    @Transactional
    public Result updateUserPhone(Long userId,String phone){
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USERID);
        }
        Boolean aBoolean = ValidateCode.verifyPhone(phone);
        if(!aBoolean){
            return Result.failureResult(ExceptionCode.Failure.FORMAT_WRONG);
        }
        MobileUser user=repository.getOne(userId);
        if(user!=null){
           user.setPhone(phone);
            repository.save(user);
            return Result.successResult();
        }
        return Result.failureResult(ExceptionCode.Failure.NO_USERS);
    }
    @Transactional
    public Result changePayPassword(String phone, String oldPassword, String newPassword,Integer role){
        if(StringUtils.isEmpty(phone)){
            return Result.failureResult(ExceptionCode.Failure.NOT_PHONE);
        }
        Boolean aBoolean = ValidateCode.verifyPhone(phone);
        if(!aBoolean){
            return Result.failureResult(ExceptionCode.Failure.FORMAT_WRONG);
        }
        MobileUser user=repository.findByPhoneAndRole(phone,role);
        if(user!=null){
            if(user.getPayPassword().equals(oldPassword)){
                user.setPayPassword(newPassword);
                repository.save(user);
                return Result.successResult();
            }
            return Result.failureResult(ExceptionCode.Failure.PAY_PASSWORD_ERROR);
        }
        return Result.failureResult(ExceptionCode.Failure.NO_USERS);
    }
    @Transactional
    public Result changeLoginPassword(String phone, String oldPassword, String newPassword,Integer role){
        if(StringUtils.isEmpty(phone)){
            return Result.failureResult(ExceptionCode.Failure.NOT_PHONE);
        }
        Boolean aBoolean = ValidateCode.verifyPhone(phone);
        if(!aBoolean){
            return Result.failureResult(ExceptionCode.Failure.FORMAT_WRONG);
        }
        MobileUser user=repository.findByPhoneAndRole(phone,role);
        if(user!=null){
            if(user.getLoginPassword().equals(oldPassword)){
                user.setLoginPassword(newPassword);
                repository.save(user);
                return Result.successResult();
            }
            return Result.failureResult(ExceptionCode.Failure.LOGIN_PASSWORD_ERROR);
        }
        return Result.failureResult(ExceptionCode.Failure.NO_USERS);
    }
    @Transactional
    public Result updateUserInfo(Long userId,Integer sex,String nickName,String avatarImg){
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USERID);
        }
        MobileUser user=repository.getOne(userId);
        if(user!=null){
            user.setSex(sex);
            user.setName(nickName);
            user.setImage(avatarImg);
            repository.save(user);
            return Result.successResult();
        }
        return Result.failureResult(ExceptionCode.Failure.NO_USERS);
    }
    @Transactional
    public Result setPayPassword(String phone, String password,Integer role){
        if(StringUtils.isEmpty(phone)){
            return Result.failureResult(ExceptionCode.Failure.NOT_PHONE);
        }
        Boolean aBoolean = ValidateCode.verifyPhone(phone);
        if(!aBoolean){
            return Result.failureResult(ExceptionCode.Failure.FORMAT_WRONG);
        }
        MobileUser user=repository.findByPhoneAndRole(phone,role);
        if(user!=null){
            if(Strings.isEmpty(user.getPayPassword())){
                user.setPayPassword(password);
                repository.save(user);
                return Result.successResult();
            }
            return Result.failureResult(ExceptionCode.Failure.PAY_PASSWORD_ERROR);
        }
        return Result.failureResult(ExceptionCode.Failure.NO_USERS);
    }
    @Transactional
    public Result setLoginPassword(String phone, String password,Integer role){
        if(StringUtils.isEmpty(phone)){
            return Result.failureResult(ExceptionCode.Failure.NOT_PHONE);
        }
        Boolean aBoolean = ValidateCode.verifyPhone(phone);
        if(!aBoolean){
            return Result.failureResult(ExceptionCode.Failure.FORMAT_WRONG);
        }
        MobileUser user=repository.findByPhoneAndRole(phone,role);
        if(user!=null){
            if(Strings.isEmpty(user.getLoginPassword())){
                user.setLoginPassword(password);
                repository.save(user);
                return Result.successResult();
            }
            return Result.failureResult(ExceptionCode.Failure.LOGIN_PASSWORD_ERROR);
        }
        return Result.failureResult(ExceptionCode.Failure.NO_USERS);
    }

    public Result usersWallets(Integer userId) {
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
        }
        MobileUser mobileUser = repository.selectMobileUse(Long.valueOf(userId));
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        UserBalance balance = userBalanceRepository.findByUserId(Long.valueOf(userId));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd");
        String dateTime = simpleDateFormat.format(new Date());
        //今日收入
        Long todayRevenue = redPackLogRepository.findByUserIdCountAndCreateTime(userId,"%"+dateTime+"%");

        Map<String,Long> map = Maps.newHashMap();
        map.put("platformMoney",balance.getPlatformMoney());
        map.put("todayRevenue",todayRevenue);

        return Result.successResult(map);
    }
}
