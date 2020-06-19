package com.dg.main.controller.logs;

import com.dg.main.serviceImpl.logs.SellerBuyUseThirdPayLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/sellerBuyUseThirdPayLog/")
@Api(tags = "记录-卖方使用第三方支付记录")
public class SellerBuyUseThirdPayLogController {
    @Autowired
    SellerBuyUseThirdPayLogService sellerBuyUseThirdPayLogService;

//    @GetMapping("/findAll")
//    @ResponseBody
//    @ApiOperation(value = "findAllRole",notes = "查询所有")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "offset",value = "第几页"),
//            @ApiImplicitParam(name = "limit",value = "每页数量")
//    })
//    public slzcResult findAll(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15") Integer limit) {
//        return sellerBuyUseThirdPayLogService.selectAll(offset, limit);
//    }
    @GetMapping("/page")
    public String page(){
        return "/order/SellerBuyUseThirdPayLog";
    }
}
