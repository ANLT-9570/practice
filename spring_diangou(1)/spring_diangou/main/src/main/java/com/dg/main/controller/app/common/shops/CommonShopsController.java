package com.dg.main.controller.app.common.shops;

import com.dg.main.Entity.shop.Shops;
import com.dg.main.Entity.users.UserRealName;
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
@RequestMapping("/common/v1/shops")
@Api(tags = "通用-新的店铺")
public class CommonShopsController {

    @Autowired
    ShopsService shopsService;

//    @GetMapping("/showShops")
//    public Result show(@RequestParam(defaultValue = "0") Integer offset,@RequestParam(defaultValue = "15") Integer limit){
//        Result result = shopsService.show(offset,limit);
//        return result;
//    }


    @GetMapping("/ShopParticulars")
    @ApiOperation(value = "商品详情",notes = "ShopParticulars")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "specificationsId",value = "商品id")
    })
    public Result ShopParticulars(Long specificationsId){
        Result result = shopsService.ShopParticulars(specificationsId);
        return result;
    }



//    @PostMapping("/RealName")
//    @ApiOperation(value = "商家实名认证",notes = "RealName")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userRealName",value = "商家个人信息")
//
//    })
//    public Result RealName(@RequestBody UserRealName userRealName){
//        Result result = shopsService.RealName(userRealName);
//        return result;
//    }






    @GetMapping("/getShops")
    @ApiOperation(value = "根据商店id查询",notes = "getShops")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "商店id")
    })
    public Result getShops(Long id){
        Result result = shopsService.getShops(id);
        return result;
    }







}
