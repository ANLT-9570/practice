package com.dg.main.controller.admin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/appLoad/")
@Api(tags = "通用-载入")
public class AppLoadController {
    //页面跳转
    @GetMapping("/skip")
    @ApiOperation(value = "SkipPage",notes = "页面跳转")
    public String SkipPage() {
        return "/order/appload";
    }
}
