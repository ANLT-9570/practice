package com.dg.main.controller.logs;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.dg.main.serviceImpl.logs.ZhifubaoCallbackFailureLogService;

@Controller
@Api(tags = "记录-支付宝失败记录")
public class ZhifubaoCallbackFailureLogController {

	@Autowired
	private ZhifubaoCallbackFailureLogService zhifubaoCallbackFailureLogService;
	
//	@GetMapping("/ZhifubaoCallbackFailureLogController/findAll")
//	@ResponseBody
//	@ApiOperation(value = "findAll",notes = "查询所有")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "offset",value = "第几页"),
//			@ApiImplicitParam(name = "limit",value = "每页数量")
//	})
//	public slzcResult findAll(@RequestParam(defaultValue = "0") Integer offset,@RequestParam(defaultValue = "15") Integer limit) {
//		return zhifubaoCallbackFailureLogService.selectAll(offset, limit);
//	}
	
	//页面跳转
	@GetMapping("/ZhifubaoCallbackFailureLogController/skip")
	public String SkipPage() {
		return "/order/ZhifubaoCallbackFailureLog";
	}
}
