package com.dg.main.controller.app.buyer;

import com.dg.main.Entity.shop.ShopCar;
import com.dg.main.dto.shop.ShopCarDto;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.param.orders.AddShopCarParam;

import com.dg.main.serviceImpl.shop.ShopCarService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buyer/v1/shoppingCar")
@Api(tags = "用户-新的购物车")
public class BuyerShoppingCarController {
    @Autowired
    ShopCarService shopCarService;
    @PostMapping("/addShopCar")
    @ApiOperation(value = "addShopCar",notes = "添加购物车")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "shopId",value = "商铺id")
//    })
    public Result addShopCar(@RequestBody AddShopCarParam param){
        return shopCarService.addItems(param);
    }
//    @RequestMapping("/addSize")
//    public Result addSize(){
//        return Result.successResult();
//    }
    @GetMapping("/list")
    @ApiOperation(value = "list",notes = "用户购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户ID")
    })
    public Result list(Long userId){
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        return Result.successResult(shopCarService.userList(userId));
    }
    @PostMapping("/addNumber")
    @ApiOperation(value = "addNumber",notes = "增加购物车数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopCarItemId",value = "购物车项目ID")
    })
    public Result addNumber(Long shopCarItemId){
        if(shopCarItemId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_ID);
        }
        Result result = shopCarService.addNumber(shopCarItemId);
        return result;
    }
    @PostMapping("/minusNumber")
    @ApiOperation(value = "minusNumber",notes = "减少购物车数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopCarItemId",value = "购物车项目ID")
    })
    public Result minusNumber(Long shopCarItemId){
        return shopCarService.minusNumber(shopCarItemId);
    }
    @DeleteMapping("/remove")
    public Result removeShopCar(@RequestBody List<ShopCarDto> shopCarDto){
        shopCarService.multiDelete(shopCarDto);
        return Result.successResult();
    }
    @DeleteMapping("/deleteAll")
    @ApiOperation(value = "deleteAll",notes = "删除购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户ID")
    })
    public Result deleteAll(Long userId){
        return shopCarService.deleteAll(userId);
    }
//    @RequestMapping("/removeSize")
//    public Result removeSize(){
//        return Result.successResult();
//    }
}
