package com.dg.main.controller.logs;

import com.dg.main.serviceImpl.logs.WeixinCallbackLogService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Api(tags = "记录-微信回调记录")
@RequestMapping("WeixinCallbackLogAdmin")
public class WeixinCallbackLogController {

	@Autowired
	private WeixinCallbackLogService weixinCallbackLogService;
//
//	@GetMapping("/findAll")
//	@ResponseBody
//	@ApiOperation(value = "findAll",notes = "查询所有")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "offset",value = "第几页"),
//			@ApiImplicitParam(name = "limit",value = "每页数量")
//	})
//	public slzcResult findAll(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15") Integer limit) {
//		return weixinCallbackLogService.selectAll(offset, limit);
//	}
	
	//页面跳转
	@GetMapping("/skip")
	public String SkipPage() {
		return "/order/WeixinCallbackFailureLog";
	}
}
