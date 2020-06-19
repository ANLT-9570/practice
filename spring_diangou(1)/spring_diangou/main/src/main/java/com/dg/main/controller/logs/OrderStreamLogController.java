package com.dg.main.controller.logs;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

////订单状态改变记录
@Controller
@Api(tags = "记录-订单状态改变记录")
@RequestMapping("/OrderStreamLog")
public class OrderStreamLogController {

//	@Autowired
//	private OrderStreamLogService orderStreamLogService;
//
//	@GetMapping("/findAll")
//	@ResponseBody
//	@ApiOperation(value = "findAll",notes = "查询所有")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "offset",value = "第几页"),
//			@ApiImplicitParam(name = "limit",value = "每页数量")
//	})
//	public slzcResult findAll(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15") Integer limit) {
//		return orderStreamLogService.selectAll(offset, limit);
//	}
//
//	//页面跳转
//	@GetMapping("/skip")
//	public String SkipPage() {
//		return "/order/OrderStreamLog";
//	}
}
