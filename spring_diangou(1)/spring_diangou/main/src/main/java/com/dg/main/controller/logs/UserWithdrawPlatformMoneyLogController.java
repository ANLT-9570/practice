package com.dg.main.controller.logs;

import com.dg.main.serviceImpl.orders.service.UserWithdrawPlatformMoneyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dg.main.serviceImpl.logs.UserWithdrawPlatformMoneyLogService;
//用户提前平台币记录
@Controller
@Api(tags = "记录-用户提钱平台币记录")
@RequestMapping("/UserWithdrawPlatformMoneyLogAdmin")
public class UserWithdrawPlatformMoneyLogController {

	@Autowired
	private UserWithdrawPlatformMoneyLogService userWithdrawPlatformMoneyLogService;
	@Autowired
	private UserWithdrawPlatformMoneyService userWithdrawPlatformMoneyService;
	
//	@GetMapping("/findAll")
//	@ResponseBody
//	@ApiOperation(value = "findAll",notes = "查询所有")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "offset",value = "第几页"),
//			@ApiImplicitParam(name = "limit",value = "每页数量")
//	})
//	public slzcResult findAll(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15") Integer limit) {
//		if (offset == null) {
//			offset =0;
//		}
//		if (limit == null) {
//			limit = 10;
//		}
//		return userWithdrawPlatformMoneyService.selectAll(offset, limit);
//	}
	//页面跳转
	@GetMapping("/skip")
	public String SkipPage() {
		return "/order/UserWithdrawPlatformMoneyLog";
	}
}
