package com.dg.main.controller.app.common.money;

import com.dg.main.Entity.orders.RedPack;
import com.dg.main.Entity.shop.Shops;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.param.orders.RedPackParam;
import com.dg.main.repository.shop.ShopsRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.serviceImpl.orders.BaseProccess;
import com.dg.main.serviceImpl.orders.create.RedPackCreateProccess;
import com.dg.main.serviceImpl.orders.factory.RedPackWrapperFactory;
import com.dg.main.serviceImpl.orders.service.RedPackCreateService;
import com.dg.main.serviceImpl.orders.service.RedPackService;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.dg.main.serviceImpl.logs.UserMoneyStreamLogService;
import com.dg.main.util.Result;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/common/v1/redPack/")
@Api(tags = "通用-关于红包")
public class CommonRedPackController {
    @Autowired
    UserBalanceService userBalanceService;
    @Autowired
    RedPackService redPackService;
    @Autowired
    UserMoneyStreamLogService userMoneyStreamLogService;

    @Autowired
    private MobileUserRepository mobileUserRepository;
    @Autowired
    private ShopsRepository shopsRepository;
    


    @GetMapping("/userMoney")
    @ApiOperation(value = "用户钱",notes = "userMoney")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id")
    })
    public Result userMoney(Long userId){
	    if(userId==null){
	        return Result.failureResult(ExceptionCode.Failure.NO_PARAMETERS);
        }
        MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
        if(mobileUser == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
	    return redPackService.countUserMoney(userId);
    }


    @GetMapping("/findByShopIdRecord")
    @ApiOperation(value = "根据店铺查询领取的红包记录",notes = "findByShopIdRecord")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "店铺id"),
            @ApiImplicitParam(name = "dateTime",value = "默认是当天")
    })
    public Result findByShopIdRecord(Long shopId,String dateTime){
        if(shopId==null){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOPID);
        }
        Optional<Shops> optional = shopsRepository.findById(shopId);
        if(!optional.isPresent()){
            return Result.failureResult(ExceptionCode.Failure.NOT_SHOP);
        }
        return redPackService.findByShopIdRecord(shopId,dateTime);
    }
}
