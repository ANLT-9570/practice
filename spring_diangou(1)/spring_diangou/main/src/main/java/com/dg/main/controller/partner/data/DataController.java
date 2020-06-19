package com.dg.main.controller.partner.data;

import com.dg.main.serviceImpl.partner.data.DataService;
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
@Api(tags = "员工端-数据")
@RequestMapping("/v1/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("dataHome")
    @ApiOperation(value = "店铺交易信息及用户",notes = "dataHome")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id")
    })
    public Result dataHome(Long userId){
        return dataService.dataHome(userId);
    }

    @GetMapping("dataAllKindOf")
    @ApiOperation(value = "各项数据",notes = "dataAllKindOf")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id")
    })
    public Result dataAllKindOf(Long userId){
        return dataService.dataAllKindOf(userId);
    }


//    @GetMapping("getAllKindOfTransaction")
//    @ApiOperation(value = "各项数据交易",notes = "getAllKindOfTransaction")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId",value = "用户id")
//    })
//    public Result getAllKindOfTransaction(Long userId){
//        return dataService.getAllKindOfTransaction(userId);
//    }


    @GetMapping("getDOrMTransaction")
    @ApiOperation(value = "日or月交易数据",notes = "getDOrMTransaction")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id"),
            @ApiImplicitParam(name = "dateTime",value = "时间"),
            @ApiImplicitParam(name = "type",value = "1是天格式2020-04-14，2是月"),
    })
    public Result getDOrMTransaction(Long userId,String dateTime,Integer type){
        return dataService.getDOrMTransaction(userId,dateTime,type);
    }

    @GetMapping("getDOrMActualTransaction")
    @ApiOperation(value = "日or月实际成交额",notes = "getDOrMActualTransaction")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id"),
            @ApiImplicitParam(name = "dateTime",value = "时间"),
            @ApiImplicitParam(name = "type",value = "1天，2是月"),
    })
    public Result getDOrMActualTransaction(Long userId,String dateTime,Integer type){
        return dataService.getDOrMActualTransaction(userId,dateTime,type);
    }

    @GetMapping("getDOrMRefunded")
    @ApiOperation(value = "日or月退货",notes = "getDOrMRefunded")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id"),
            @ApiImplicitParam(name = "dateTime",value = "时间"),
            @ApiImplicitParam(name = "type",value = "1是天格式2020-04-14，2是月"),
    })
    public Result getDOrMRefunded(Long userId,String dateTime,Integer type){
        return dataService.getDOrMRefunded(userId,dateTime,type);
    }

    @GetMapping("getShopsData")
    @ApiOperation(value = "数据商户",notes = "getShopsData")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id")
    })
    public Result getShopsData(Long userId){
        return dataService.getShopsData(userId);
    }

    @GetMapping("getDOrMTransactionShops")
    @ApiOperation(value = "日or月交易数据根据商铺id",notes = "getDOrMTransactionShops")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "商铺id"),
            @ApiImplicitParam(name = "dateTime",value = "时间"),
            @ApiImplicitParam(name = "type",value = "1是天格式2020-04-14，2是月"),
    })
    public Result getDOrMTransactionShops(Long shopId,String dateTime,Integer type){
        return dataService.getDOrMTransactionShops(shopId,dateTime,type);
    }

    @GetMapping("getDOrMActualTransactionShops")
    @ApiOperation(value = "日or月实际成交额根据商铺id",notes = "getDOrMActualTransactionShops")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "商铺id"),
            @ApiImplicitParam(name = "dateTime",value = "时间"),
            @ApiImplicitParam(name = "type",value = "1是天格式2020-04-14，2是月"),
    })
    public Result getDOrMActualTransactionShops(Long shopId,String dateTime,Integer type){
        return dataService.getDOrMActualTransactionShops(shopId,dateTime,type);
    }

    @GetMapping("getDOrMRefundedShops")
    @ApiOperation(value = "日or月退货根据商铺id",notes = "getDOrMRefundedShops")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "商铺id"),
            @ApiImplicitParam(name = "dateTime",value = "时间"),
            @ApiImplicitParam(name = "type",value = "1是天格式2020-04-14，2是月"),
    })
    public Result getDOrMRefundedShops(Long shopId,String dateTime,Integer type){
        return dataService.getDOrMRefundedShops(shopId,dateTime,type);
    }
}
