package com.dg.main.controller.admin;

import com.dg.main.serviceImpl.shop.ShopsService;
import com.dg.main.util.slzcResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("ShopsAdmin")
public class ShopsAdminController {

    @Autowired
    ShopsService shopsService;

    @GetMapping("/findAll")
    @ResponseBody
    @ApiOperation(value = "findAll",notes = "查询所有")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "offset",value = "第几页"),
            @ApiImplicitParam(name = "limit",value = "每页数量"),
            @ApiImplicitParam(name = "search",value = "查询条件")
    })
    public slzcResult findAllUser(@RequestParam(defaultValue = "15")int limit, @RequestParam(defaultValue = "0")int offset) {
        slzcResult result = shopsService.findPageAll(limit,offset);
        return result;
    }

    @RequestMapping("/skipPage")
    public String skipPage() {
        return "Shop";
    }
}
