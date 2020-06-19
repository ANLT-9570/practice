package com.dg.main.controller.admin;

import com.dg.main.serviceImpl.setting.CompanyBalanceService;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dg.main.serviceImpl.logs.CompanyMoneyStreamLogService;

@Controller
@Api(tags = "后台-公司金钱流水记录")
@RequestMapping("CompanyMoneyStreamLog")
public class CompanyMoneyStreamLogController {

	@Autowired
	private CompanyMoneyStreamLogService companyMoneyStreamLogService;
	@Autowired
	CompanyBalanceService companyBalanceService;

	@GetMapping("/company")
	@ResponseBody
	@ApiOperation(value = "company",notes = "查询所有")
	public Result company() {
		return Result.successResult(companyBalanceService.getBalance());
	}

//	@GetMapping("/findAll")
//	@ResponseBody
//	@ApiOperation(value = "findAll",notes = "查询所有")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "offset",value = "第几页"),
//			@ApiImplicitParam(name = "limit",value = "每页数量")
//	})
//	public slzcResult findAll(@RequestParam(defaultValue = "0")Integer offset, @RequestParam(defaultValue = "15")Integer limit) {
//		return companyMoneyStreamLogService.selectAll(offset, limit);
//	}
	
	//页面跳转
	@RequestMapping("/skip")
	public String SkipPage() {
		return "/order/CompanyMoneyStreamLog";
	}
}
