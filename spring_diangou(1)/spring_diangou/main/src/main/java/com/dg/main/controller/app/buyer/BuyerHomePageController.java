package com.dg.main.controller.app.buyer;

import com.dg.main.Entity.shop.Specifications;
import com.dg.main.serviceImpl.HomePageService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buyer/app/home/page")
@Api(value = "首页",tags = {"用户-[新的首页界面]"})
public class BuyerHomePageController {

    @Autowired
    private HomePageService homePageService;

    @GetMapping("/show")
    @ApiOperation(value = "首页展示",notes = "首页展示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量"),
            @ApiImplicitParam(name = "times",value = "时间2020-01-03，测试用")
    })
    public Result show(String times,@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "10") Integer limit){
        Result result = homePageService.show(offset,limit,times);

        return result;
    }

    @GetMapping("/random")
    @ApiOperation(value = "名门贵族",notes = "名门贵族")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量")
    })
    public Result random(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15") Integer limit){
        Result result = homePageService.random(offset,limit);
        return result;
    }

    @GetMapping("/GoodStuffRandom")
    @ApiOperation(value = "好货大赏",notes = "好货大赏")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量"),
            @ApiImplicitParam(name = "name",value = "商品名称"),
            @ApiImplicitParam(name = "city",value = "地区")
    })
    public Result GoodStuffRandom(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15") Integer limit,String name,String city){
        Result result = homePageService.GoodStuffRandom(offset,limit,name,city);
        return result;
    }

    @GetMapping("/GoodThingRandom")
    @ApiOperation(value = "好物推荐",notes = "好物推荐")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量"),
            @ApiImplicitParam(name = "name",value = "商品类型"),
            @ApiImplicitParam(name = "city",value = "地区")
    })
    public Result GoodThingRandom(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15") Integer limit,String name,String city){
        Result result = homePageService.GoodThingRandom(offset,limit,name,city);
        return result;
    }

    @GetMapping("/searchList")
    @ApiOperation(value = "搜索列表",notes = "搜索列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量"),
            @ApiImplicitParam(name = "condition",value = "查询条件")
    })
    public Result searchList(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15") Integer limit,String condition){
        Result result = homePageService.searchList(offset,limit,condition);
        return result;
    }

    @GetMapping("/goodShops")
    @ApiOperation(value = "逛好货",notes = "逛好货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量")
    })
    public Result goodShops(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15") Integer limit){
        Result result = homePageService.random(offset,limit);
        return result;
    }

    @GetMapping("/citySort")
    @ApiOperation(value = "地区排名",notes = "地区排名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "city",value = "地区"),
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量")
    })
    public Result citySort(String city,@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15") Integer limit){
        Result result = homePageService.citySort(city,offset,limit);
        return result;
    }

    @GetMapping("/nobleV1")
    @ApiOperation(value = "首页的名门贵族V1",notes = "名门贵族_2")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量")
    })
    public Result nobleV1(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "6") Integer limit){
        Result result = homePageService.nobleV1(offset,limit);
        return result;
    }

    @GetMapping("/nobleV2")
    @ApiOperation(value = "首页的名门贵族V2",notes = "名门贵族_2")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量")
    })
    public Result nobleV2(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "6") Integer limit){
        Result result = homePageService.nobleV2(offset,limit);
        return result;
    }

    @GetMapping("/GoodStuffBigV1")
    @ApiOperation(value = "首页好货大赏_1",notes = "好货大赏_1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量")
    })
    public Result GoodStuffBigV1(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "4") Integer limit){
        Result result = homePageService.GoodStuffBigV1(offset,limit);
        return result;
    }
    @GetMapping("/GoodStuffBigV2")
    @ApiOperation(value = "首页好货大赏_2",notes = "好货大赏_2")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页显示数量")
    })
    public Result GoodStuffBigV2(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "4") Integer limit){
        Result result = homePageService.GoodStuffBigV2(offset,limit);
        return result;
    }
}
