package com.dg.main.controller.logs;

import com.dg.main.serviceImpl.orders.service.UserWithdrawMoneyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dg.main.serviceImpl.logs.UserWithdrawMoneyStreamLogService;
////用户提前人民币记录
@Controller
@Api(tags = "记录-用户提现人民币记录")
@RequestMapping("UserWithdrawMoneyStreamLogAdmin")
public class UserWithdrawMoneyStreamLogController {

	@Autowired
	private UserWithdrawMoneyStreamLogService userWithdrawMoneyStreamLogService;
	@Autowired
	UserWithdrawMoneyService userWithdrawMoneyService;
	
//	@GetMapping("/findAll")
//	@ResponseBody
//	@ApiOperation(value = "findAll",notes = "查询所有")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "offset",value = "第几页"),
//			@ApiImplicitParam(name = "limit",value = "每页数量")
//	})
//	public slzcResult findAll(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15") Integer limit) {
//		return userWithdrawMoneyService.selectAll(offset, limit);
//	}
	
	//页面跳转
	@GetMapping("/skip")
	public String SkipPage() {
		return "/order/UserWithdrawMoneyStreamLog";
	}
}
