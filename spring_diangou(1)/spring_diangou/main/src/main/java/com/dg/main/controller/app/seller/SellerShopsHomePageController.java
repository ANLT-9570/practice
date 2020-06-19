package com.dg.main.controller.app.seller;

import com.dg.main.serviceImpl.ShopsHomePageService;
import com.dg.main.serviceImpl.logs.RedPackRankLogService;
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
@RequestMapping("/seller/v1/ShopsHomePage")
@Api(tags = "商家-[商家首页界面]")
public class SellerShopsHomePageController {

    @Autowired
    ShopsHomePageService shopsHomePageService;
    @Autowired
    RedPackRankLogService redPackRankLogService;


    @GetMapping("/getUser")
    @ApiOperation(value = "商家个人信息",notes = "getUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id")
    })
    public Result getUser(Long userId){
        Result result = shopsHomePageService.getUser(userId);
        return result;
    }


    @GetMapping("/allKindsOfUser")
    @ApiOperation(value = "用户各个订单状态统计",notes = "allKindsOfUserId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id")
    })
    public Result allKindsOfUserId(Long userId){
        Result result = shopsHomePageService.allKindsOf(userId);
        return result;
    }


    @GetMapping("/allKindsOfShop")
    @ApiOperation(value = "店铺各个订单状态统计",notes = "allKindsOfShopId")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "店铺id")
    })
    public Result allKindsOfShopId(Long shopId){
        Result result = shopsHomePageService.allKindsOfShopId(shopId);
        return result;
    }


    @GetMapping("/getShopsData")
    @ApiOperation(value = "店铺数据---->弃用",notes = "getShopsData")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id")
    })
    public Result getShopsData(Long userId){
        Result result = shopsHomePageService.getShopsData(userId);
        return result;
    }


    @GetMapping("/getShopsDataTimeUser")
    @ApiOperation(value = "用户根据时间查询店铺数据",notes = "getShopsDataTime")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id"),
            @ApiImplicitParam(name = "dateTime",value = "时间")
    })
    public Result getShopsDataTime(Long userId,String dateTime){
        Result result = shopsHomePageService.getShopsDataTime(userId,dateTime);
        return result;
    }


    @GetMapping("/getShopsDataTimeShops")
    @ApiOperation(value = "店铺根据时间查询店铺数据",notes = "getShopsDataTimeShops")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "商铺id"),
            @ApiImplicitParam(name = "dateTime",value = "时间")
    })
    public Result getShopsDataTimeShops(Long shopId,String dateTime){
        Result result = shopsHomePageService.getShopsDataTimeShops(shopId,dateTime);
        return result;
    }


    @GetMapping("/getShopsDBDataTime")
    @ApiOperation(value = "店铺点币派送数据",notes = "getShopsDBDataTime")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "商铺id"),
            @ApiImplicitParam(name = "dateTime",value = "2020-03-13")
    })
    public Result getShopsDBDataTime(Long shopId,String dateTime){
        Result result = shopsHomePageService.getShopsDBDataTime(shopId,dateTime);
        return result;
    }


    @GetMapping("/getUserDBDataTime")
    @ApiOperation(value = "用户点币派送数据",notes = "getUserDBDataTime")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id"),
            @ApiImplicitParam(name = "dateTime",value = "2020-03-13")
    })
    public Result getUserDBDataTime(Long userId,String dateTime){
        Result result = shopsHomePageService.getUserDBDataTime(userId,dateTime);
        return result;
    }


    @GetMapping("/getShopsRank")
    @ApiOperation(value = "店铺总排名",notes = "getShopsRank")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "店铺id"),
    })
    public Result getShopsRank(Long shopId){
        Result result = redPackRankLogService.getShopsRank(shopId);
        return result;
    }


    @GetMapping("/getShopsRankList")
    @ApiOperation(value = "店铺总排名列表",notes = "getShopsRankList")
    @ApiImplicitParams({
//            @ApiImplicitParam(name = "shopId",value = "店铺id"),
    })
    public Result getShopsRankList(){
        Result result = redPackRankLogService.getShopsRankList();
        return result;
    }


    @GetMapping("/getShopsAreaRank")
    @ApiOperation(value = "店铺区域排名",notes = "getShopsAreaRank")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "店铺id"),
    })
    public Result getShopsAreaRank(Long shopId){
        Result result = redPackRankLogService.getShopsAreaRank(shopId);
        return result;
    }


    @GetMapping("/getShopsAreaRankList")
    @ApiOperation(value = "店铺区域排名列表",notes = "getShopsAreaRankList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "店铺id"),
    })
    public Result getShopsAreaRankList(Long shopId){
        Result result = redPackRankLogService.getShopsAreaRankList(shopId);
        return result;
    }
}
