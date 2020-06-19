package com.dg.main.controller.admin;

import com.dg.main.serviceImpl.logs.CompanyDailyStreamLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/companyDailyStreamLog/")
@Api(tags = "后台-公司的流水记录")
public class CompanyDailyStreamLogController {
    @Autowired
    CompanyDailyStreamLogService companyDailyStreamLogService;

//    @GetMapping("/findAll")
//    @ResponseBody
//    @ApiOperation(value = "findAll",notes = "查询所有")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "offset",value = "第几页"),
//            @ApiImplicitParam(name = "limit",value = "每页数量")
//    })
//    public slzcResult findAll(@RequestParam(defaultValue = "0")Integer offset, @RequestParam(defaultValue = "15")Integer limit) {
//        return companyDailyStreamLogService.selectAll(offset, limit);
//    }

    @GetMapping("/page")
    @ApiOperation(value = "SkipPage",notes = "页面跳转")
    public String page(){
        return "/order/CompanyDailyStreamLog";
    }
}
