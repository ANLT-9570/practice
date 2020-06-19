package com.dg.main.controller.app.buyer;

import com.dg.main.service.AppRedPackService;
import com.dg.main.serviceImpl.users.MobileUserService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buyer/v1/balance")
@Api(tags = "用户--[钱包界面]")
public class BuyerBalanceController {

    @Autowired
    MobileUserService service;
    @Autowired
    private AppRedPackService appRedPackService;
//    @PostMapping("/usersWallets")
//    @ApiOperation(value = "用户的钱包",notes = "usersWallets")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId",value = "用户id")
//    })
//    public Result usersWallets(Integer userId){
//        return service.usersWallets(userId);
//    }
    @GetMapping("/bestNewRecord")
    @ApiOperation(value = "钱包页面的数据",notes = "bestNewRecord")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户Id"),
    })
    public Result bestNewRecord (Integer userId){
        Result result = appRedPackService.bestNewRecord(userId);
        return result;
    }
}
