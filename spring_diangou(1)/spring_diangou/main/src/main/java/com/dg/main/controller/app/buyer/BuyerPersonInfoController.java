package com.dg.main.controller.app.buyer;

import com.dg.main.serviceImpl.PersonInfoService;
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
@RequestMapping("/buyer/person/nfo")
@Api(tags = "用户-[我的界面]")
public class BuyerPersonInfoController {

    @Autowired
    PersonInfoService psersonInfoService;

    @GetMapping("/newLogistics")
    @ApiOperation(value = "根据用户id查询订单状态的物流信息",notes = "newLogistics")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id"),
    })
    public Result newLogistics(Integer userId){
        return psersonInfoService.newLogistics(userId);
    }

    @GetMapping("/findByOrder")
    @ApiOperation(value = "根据订单号查询物流信息",notes = "findByOrder")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "order",value = "物流号"),
    })
    public Result findByOrder(String order){
        return psersonInfoService.findByOrder(order);
    }

    @GetMapping("/allKindsOf")
    @ApiOperation(value = "各个订单状态的统计",notes = "allKindsOf")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id"),
    })
    public Result allKindsOf(Long userId){
        return psersonInfoService.allKindsOf(userId);
    }


}