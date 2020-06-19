package com.dg.main.controller.app.seller;

import com.dg.main.serviceImpl.MerchandiseCentralService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller/v1/MerchandiseCentral")
@Api(tags = "商家-商品管理")
public class SellerMerchandiseCentralController {

    @Autowired
    MerchandiseCentralService merchandiseCentralService;

    @GetMapping("/getInSales")
    @ApiOperation(value = "在售中",notes = "getUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "商铺id"),
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页数量")
    })
    public Result getUser(Long shopId,@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15")Integer limit){
//        Result result = merchandiseCentralService.getInSales(userId);
        Result result = merchandiseCentralService.getUpAndDown(shopId, 1,offset,limit);
        return result;
    }


    @GetMapping("/getUnShelve")
    @ApiOperation(value = "已下架",notes = "getUnShelve")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "shopId",value = "商铺id"),
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页数量")
    })
    public Result getUnShelve(Long shopId,@RequestParam(defaultValue = "0")Integer offset, @RequestParam(defaultValue = "15")Integer limit){
//        Result result = merchandiseCentralService.getUnShelve(userId);
        Result result = merchandiseCentralService.getUpAndDown(shopId, 2,offset,limit);
        return result;
    }


    @PostMapping("/soldOut")
    @ApiOperation(value = "商品下架",notes = "soldOut")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "商品id")
    })
    public Result soldOut(Long id){
        Result result = merchandiseCentralService.updateStatus(id, 2);
        return result;
    }


    @PostMapping("/putAway")
    @ApiOperation(value = "商品上架",notes = "putAway")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "商品id")
    })
    public Result putAway(Long id){
        Result result = merchandiseCentralService.updateStatus(id, 1);
        return result;
    }

}
