package com.dg.main.controller.partner.earnings;

import com.dg.main.serviceImpl.partner.earnings.EarningsHomeService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "员工端-数据收益")
@RequestMapping("/v1/earnings/home")
public class EarningsHomeController {

    @Autowired
    EarningsHomeService earningsHomeService;

    @GetMapping("/")
    @ApiOperation(value = "收益页",notes = "earningsHome")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id")
    })
    public Result earningsHome(Long userId){
        return earningsHomeService.earningsHome(userId);
    }

    @GetMapping("/userDetail")
    @ApiOperation(value = "用户收益明细",notes = "userDetail")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id")
    })
    public Result userDetail(Long userId){
        return earningsHomeService.userDetail(userId);
    }

    @GetMapping("/shopsDetail")
    @ApiOperation(value = "商铺收益明细",notes = "shopsDetail")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "商铺")
    })
    public Result shopsDetail(Long userId){
        return earningsHomeService.earningsHome(userId);
    }

}
