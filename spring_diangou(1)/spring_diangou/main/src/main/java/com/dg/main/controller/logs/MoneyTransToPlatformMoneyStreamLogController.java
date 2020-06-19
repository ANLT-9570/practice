package com.dg.main.controller.logs;

import com.dg.main.serviceImpl.orders.service.MoneyTransToPlatformMoneyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dg.main.serviceImpl.logs.MoneyTransToPlatformMoneyStreamLogService;

@Controller
@Api(tags = "记录-平台转账记录")
@RequestMapping(value = "/MoneyTransToPlatformMoneyStreamLog")
public class MoneyTransToPlatformMoneyStreamLogController {

	@Autowired
	private MoneyTransToPlatformMoneyStreamLogService moneyTransToPlatformMoneyStreamLogService;
	@Autowired
	MoneyTransToPlatformMoneyService moneyTransToPlatformMoneyService;
	
//	@GetMapping("/findAll")
//	@ResponseBody
//	@ApiOperation(value = "findAll",notes = "查询所有")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "offset",value = "第几页"),
//			@ApiImplicitParam(name = "limit",value = "每页数量"),
//			@ApiImplicitParam(name = "search",value = "查询条件")
//	})
//	public slzcResult findAll(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15") Integer limit) {
//		return moneyTransToPlatformMoneyService.selectAll(offset, limit);
//	}
	
	//页面跳转
	@RequestMapping("/skip")
	public String SkipPage() {
		return "/order/MoneyTransToPlatformMoneyStreamLog";
	}
}
