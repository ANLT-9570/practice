package com.dg.main.controller.logs;

import com.dg.main.serviceImpl.orders.service.UserDepositPlatformMoneyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dg.main.util.slzcResult;
import com.dg.main.serviceImpl.logs.UserDepositPlatformMoneyLogService;

@Controller
@Api(tags = "记录-用户平台转账记录后台")
@RequestMapping("UserDepositPlatformMoneyLogAdmin")
public class UserDepositPlatformMoneyLogController {

	@Autowired
	private UserDepositPlatformMoneyLogService userDepositPlatformMoneyLogService;
	@Autowired
	UserDepositPlatformMoneyService userDepositPlatformMoneyService;

	@GetMapping("/findAll")
	@ResponseBody
	@ApiOperation(value = "findAll",notes = "查询所有")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "offset",value = "第几页"),
			@ApiImplicitParam(name = "limit",value = "每页数量")
	})
	public slzcResult findAll(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15") Integer limit) {
		return userDepositPlatformMoneyService.selectAll(offset, limit);
	}
	
	//页面跳转
	@GetMapping("/skip")
	public String SkipPage() {
		return "/order/UserDepositPlatformMoneyLog";
	}
}
