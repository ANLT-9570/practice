package com.dg.main.controller.app.buyer;

import com.dg.main.serviceImpl.shop.ShopsService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = {"用户-商店"})
@RequestMapping("/buyer/v1/shop")
public class BuyerShopController {
    @Autowired
    ShopsService shopsService;
    @GetMapping("/isCollection")
    @ApiOperation(value = "是否收藏",notes = "isCollection")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "specificationId",value = "商品id"),
            @ApiImplicitParam(name = "userId",value = "用户id")
    })
    public Result isCollection(Long specificationId, Long userId){
        Result result = shopsService.isCollection(specificationId,userId);
        return result;
    }
    @GetMapping("/commentCount")
    @ApiOperation(value = "各评论数量",notes = "commentCount")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "specificationId",value = "商品id")
    })
    public Result commentCount(Long specificationId){
        Result result = shopsService.commentCount(specificationId);
        return result;
    }
    @GetMapping("/storesParticulars")
    @ApiOperation(value = "店铺详情",notes = "storesParticulars")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "商铺id")
    })
    public Result storesParticulars(Long shopId){
        Result result = shopsService.storesParticulars(shopId);
        return result;
    }

    @GetMapping("/storesCommodity")
    @ApiOperation(value = "商铺下的商品级搜索",notes = "storesCommodity")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量"),
            @ApiImplicitParam(name = "shopId",value = "商铺id"),
            @ApiImplicitParam(name = "condition",value = "条件")
    })
    public Result storesCommodity(Long shopId, String condition, @RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "15") Integer limit){
//        Result result = shopsService.storesCommodity(shopId,userId,condition,offset,limit);
        Result result = shopsService.storesCommodityV2(shopId,condition,offset,limit);
        return result;
    }
    @GetMapping("/SuperShop")
    @ApiOperation(value = "超级店家",notes = "SuperShop")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量")
    })
    public Result SuperShop(@RequestParam(defaultValue = "0") Integer offset,@RequestParam(defaultValue = "15") Integer limit){
        Result result = shopsService.SuperShop(offset,limit);
        return result;
    }
}
