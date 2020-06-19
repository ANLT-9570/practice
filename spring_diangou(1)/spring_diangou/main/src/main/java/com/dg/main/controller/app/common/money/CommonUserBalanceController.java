package com.dg.main.controller.app.common.money;

import com.dg.main.Entity.users.UserBalance;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.serviceImpl.users.UserBalanceService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common/v1/userBalance")
@Api(tags = "通用-用户账户")
public class CommonUserBalanceController {
    @Autowired
    UserBalanceService userBalanceService;
    @GetMapping("/findOne")
    @ApiOperation(value = "根据ID获取用户余额")
    public Result findOne(Integer id){
        if(id==null){
            return Result.failureResult(ExceptionCode.Failure.NO_USERS);
        }
        UserBalance userBalance=userBalanceService.findByUserId(new Long(id));
        if(userBalance==null){
            return Result.failureResult(ExceptionCode.Failure.NO_USER_BALANCE);
        }
        userBalance.setCreateTime(null);
        userBalance.setModifyTime(null);
        userBalance.setId(null);
        userBalance.setUserId(null);
        userBalance.setOperationTimes(null);
        return Result.successResult(userBalance);

    }
//    @RequestMapping("topUserBalance")
//    public Result topUserBalance(){
//    //    userBalanceService.topPlatformMoney(Optional.ofNullable(null));
//        return Result.successResult("");
//    }
}
