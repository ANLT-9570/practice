package com.dg.main.controller.app.seller;

import com.dg.main.service.ToolsService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seller/v1/tools")
@Api(tags = "商家-[工具界面]")
public class SellerToolsController {

    @Autowired
    ToolsService toolsService;

    @GetMapping("/shopUproar")
    @ApiOperation(value = "店铺热度",notes = "shopUproar")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页数量")
    })
    public Result shopUproar(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15")Integer limit){
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        Result result = toolsService.shopUproar(offset,limit);
        return result;
    }

    @GetMapping("/commodityUproar")
    @ApiOperation(value = "商品热度",notes = "commodityUproar")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页数量")
    })
    public Result commodityUproar(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15")Integer limit){
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        Result result = toolsService.commodityUproar(offset,limit);
        return result;
    }

    @GetMapping("/shopWithSpecification")
    @ApiOperation(value = "查看这个店铺下的商品",notes = "shopWithSpecification")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页数量"),
            @ApiImplicitParam(name = "shopId",value = "商铺id")
    })
    public Result shopWithSpecification(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15")Integer limit,Long shopId){
        if(offset != 0){
            offset = offset * limit;
            limit = offset + limit;
        }
        Result result = toolsService.shopWithSpecification(offset,limit,shopId);
        return result;
    }

    @GetMapping("/commodityForTop")
    @ApiOperation(value = "商品置顶",notes = "commodityForTop")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "specificationId",value = "商品id"),
    })
    public Result commodityForTop(Long specificationId){
        Result result = toolsService.commodityForTop(specificationId);
        return result;
    }

}
