package com.dg.main.controller.partner.home;

import com.dg.main.serviceImpl.partner.home.PartnerHomeService;
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
@Api(tags = "员工端-首页")
@RequestMapping("/v1/partner/home")
public class PartnerHomeController {

    @Autowired
    PartnerHomeService partnerHomeService;

    @GetMapping("getIndexData")
    @ApiOperation(value = "首页数据",notes = "getMyPartner")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id")
    })
    public Result getIndexData(Long userId){
        return partnerHomeService.getIndexData(userId);
    }
//    @GetMapping("getKindOf")
//    @ApiOperation(value = "getKindOf",notes = "getKindOf")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId",value = "用户id")
//    })
//    public Result getKindOf(Long userId){
//        return partnerHomeService.getKindOf(userId);
//    }
//
//    @GetMapping("getAllKindOf")
//    @ApiOperation(value = "getAllKindOf",notes = "getAllKindOf")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId",value = "用户id")
//    })
//    public Result getAllKindOf(Long userId){
//        return partnerHomeService.getAllKindOf(userId);
//    }

    @GetMapping("getMyPartner")
    @ApiOperation(value = "我的伙伴",notes = "getMyPartner")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id")
    })
    public Result getMyPartner(Long userId){
        return partnerHomeService.getMyPartner(userId);
    }

    @GetMapping("getMyMerchant")
    @ApiOperation(value = "我的商户",notes = "getMyMerchant")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id")
    })
    public Result getMyMerchant(Long userId){
        return partnerHomeService.getMyMerchant(userId);
    }

    @GetMapping("shopsInfo")
    @ApiOperation(value = "商户信息",notes = "shopsInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "商铺id")
    })
    public Result shopsInfo(Long shopId){
        return partnerHomeService.shopsInfo(shopId);
    }


    @GetMapping("getDMTransaction")
    @ApiOperation(value = "日or月交易",notes = "getDMTransaction")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "商铺id"),
            @ApiImplicitParam(name = "date",value = "时间"),
            @ApiImplicitParam(name = "type",value = "1是天格式2020-04-14，2是月"),
    })
    public Result getDMTransaction(Long shopId,String date,Integer type){
        return partnerHomeService.getDMTransaction(shopId,date,type);
    }

    @GetMapping("getDMRefunded")
    @ApiOperation(value = "日or月退货",notes = "getDMRefunded")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "商铺id"),
            @ApiImplicitParam(name = "date",value = "时间"),
            @ApiImplicitParam(name = "type",value = "1是天格式2020-04-14，2是月"),
    })
    public Result getDMRefunded(Long shopId,String date,Integer type){
        return partnerHomeService.getDMRefunded(shopId,date,type);
    }

    @GetMapping("getDMActualTransaction")
    @ApiOperation(value = "日or月实际成交额",notes = "getDMActualTransaction")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "商铺id"),
            @ApiImplicitParam(name = "date",value = "时间"),
            @ApiImplicitParam(name = "type",value = "1是天格式2020-04-14，2是月"),
    })
    public Result getDMActualTransaction(Long shopId,String date,Integer type){
        return partnerHomeService.getDMActualTransaction(shopId,date,type);
    }
}
