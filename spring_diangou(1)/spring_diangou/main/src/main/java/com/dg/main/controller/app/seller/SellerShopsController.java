package com.dg.main.controller.app.seller;

import com.dg.main.Entity.shop.Shops;
import com.dg.main.serviceImpl.shop.ShopsService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller/v1/shops")
@Api(tags = "商家-店铺")
public class SellerShopsController {
    @Autowired
    ShopsService shopsService;
    @PostMapping("/setUpShop")
    @ApiOperation(value = "商家开店",notes = "setUpShop")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shops",value = "店铺信息")
    })
    public Result setUpShop(@RequestBody Shops shops){
        Result result = shopsService.setUpShop(shops);
        return result;
    }


    @DeleteMapping("/delShop")
    @ApiOperation(value = "根据店铺删除商店",notes = "delShop")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "店铺id")
    })
    public Result delShop(Long id){
        Result result = shopsService.delShop(id);
        return result;
    }


    @DeleteMapping("/delAllShop")
    @ApiOperation(value = "批量删除商店",notes = "delAllShop")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids",value = "商店id")
    })
    public Result delAllShop(@RequestParam(value = "ids") List<Long> ids){
        Result result = shopsService.delAllShop(ids);
        return result;
    }


    @DeleteMapping("/delUserIdShop")
    @ApiOperation(value = "根据用户id删除",notes = "delAllShop")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id")
    })
    public Result delUserIdShop(Long userId){
        Result result = shopsService.delUserIdShop(userId);
        return result;
    }


    @PostMapping("/upDateShop")
    @ApiOperation(value = "商店修改",notes = "updateShop")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shops",value = "商店信息")
    })
    public Result updateShop(@RequestBody Shops shops){
        Result result = shopsService.updateShop(shops);
        return result;
    }


    @GetMapping("/getListShops")
    @ApiOperation(value = "查询用户下的所有商店",notes = "getListShops")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id")
    })
    public Result getListShops(Long userId){
        Result result = shopsService.getListShops(userId);
        return result;
    }
    @GetMapping("/switchShops")
    @ApiOperation(value = "切换店铺",notes = "switchShops")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "店铺id"),
            @ApiImplicitParam(name = "userId",value = "用户id")
    })
    public Result switchShops(Long shopId,Long userId){
        Result result = shopsService.switchShops(shopId,userId);
        return result;
    }


}
