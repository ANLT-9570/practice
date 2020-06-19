package com.dg.main.controller.logs;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dg.main.serviceImpl.logs.OrderRefundLogService;

@Controller
@RequestMapping("/OrderRefundLog")
@Api(tags = "记录-订单退款记录")
public class OrderRefundLogController {

	@Autowired
	private OrderRefundLogService orderRefundLogService;
	
//	@RequestMapping("/findAll")
//	@ResponseBody
//	@ApiOperation(value = "findAll",notes = "查询所有")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "offset",value = "第几页"),
//			@ApiImplicitParam(name = "limit",value = "每页数量")
//	})
//	public slzcResult findAll(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15") Integer limit) {
//		return orderRefundLogService.selectAll(offset, limit);
//	}
	
	//页面跳转
	@RequestMapping("/skip")
	public String SkipPage() {
		return "/order/OrderRefundLog";
	}
}
