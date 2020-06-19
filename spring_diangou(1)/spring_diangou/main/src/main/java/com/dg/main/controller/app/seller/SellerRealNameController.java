package com.dg.main.controller.app.seller;

import com.dg.main.Entity.users.UserRealName;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.serviceImpl.users.UserRealNameService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("seller/v1/UserRealName")
@Api(tags = "商家-实名认证")
public class SellerRealNameController {

    @Autowired
    private UserRealNameService userRealNameService;

    @PostMapping("/RealName")
    @ApiOperation(value = "商家实名认证",notes = "RealName")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userRealName",value = "商家个人信息")

    })
    public Result RealName(@RequestBody UserRealName userRealName){
        Result result = userRealNameService.save(userRealName);
        return result;
    }


//    @DeleteMapping("/delRealName")
//    @ApiOperation(value = "根据id删除商家认证信息",notes = "delRealName")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "id")
//
//    })
//    public Result delRealName(Long id){
//        Result result = userRealNameService.delRealName(id);
//        return result;
//    }


//    @DeleteMapping("/delByUserIdRealName")
//    @ApiOperation(value = "根据用户id删除商家认证信息",notes = "delByUserIdRealName")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "userId",value = "userId")
//
//    })
//    public Result delByUserIdRealName(Long userId){
//        Result result = userRealNameService.delByUserIdRealName(userId);
//        return result;
//    }


    @PostMapping("/updateRealName")
    @ApiOperation(value = "修改认证信息",notes = "updateRealName")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userRealName",value = "认证信息")

    })
    public Result updateRealName(@RequestBody UserRealName userRealName){
        Result result = userRealNameService.updateRealName(userRealName);
        return result;
    }


    @GetMapping("/getRealName")
    @ApiOperation(value = "根据用户id查询",notes = "getRealName")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id")

    })
    public Result getRealName(Long userId){
        if(userId == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USERID);
        }
        UserRealName userRealName= userRealNameService.findByUserId(userId);
        return Result.successResult(userRealName);
    }


//    @GetMapping("/getIdRealName")
//    @ApiOperation(value = "根据id查询",notes = "getIdRealName")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id",value = "id")
//
//    })
//    public Result getIdRealName(Long id){
//        Result result= userRealNameService.getIdRealName(id);
//        return result;
//    }
}
