package com.dg.main.controller.app.buyer.message;

import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buyer/v1/ordersMessage")
@Api(value = "用户-订单消息")
public class BuyerOrdersMessageController {

    @RequestMapping("/list/{userId}")
    @ApiOperation(value = "list",notes = "根据用户id查询订单消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId",value = "用户id"),
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页显示记录数")
    })
    public Result list(@PathVariable(value = "userId")Long userId, @RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15")Integer limit){
        return Result.successResult();
    }
}
