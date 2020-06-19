package com.dg.main.controller.app.common;

import com.dg.main.Entity.users.UserCoupons;
import com.dg.main.serviceImpl.UserCouponsServiceImpl;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/common/v1/userCoupons/")
@Api(tags = "通用-优惠券V_2")
public class CommonUserCouponsController {
    @Autowired
    UserCouponsServiceImpl userCouponsService;
    @GetMapping("/findAll")
    @ApiOperation(value = "findAll",notes = "查询所有")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页数量")
    })
    public Result findAll(@RequestParam(defaultValue = "0") Integer offset, @RequestParam(defaultValue = "15") Integer limit) {

        return Result.successResult(userCouponsService.selectAll(offset, limit));
    }
    @PostMapping("/save")
    @ApiOperation(value = "save",notes = "添加保存")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCoupons",value = "优惠券信息")
    })
    public Result save(UserCoupons userCoupons){
        userCouponsService.save(userCoupons);
        return Result.successResult("");
    }
    @GetMapping("/update")
    @ApiOperation(value = "update",notes = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userCoupons",value = "卡号信息")
    })
    public Result update(UserCoupons userCoupons){
        userCouponsService.update(userCoupons);
        return Result.successResult("");
    }
    @GetMapping("/findOne")
    @ApiOperation(value = "findOne",notes = "根据id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id")
    })
    public Result findOne(@RequestParam Long id){
        return Result.successResult(userCouponsService.findBy(id));

    }
    @DeleteMapping("/delete")
    @ApiOperation(value = "delete",notes = "根据id删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id")
    })
    public Result delete(@RequestParam Long id){
        userCouponsService.deleteBy(id);
        return Result.successResult("");
    }
}
