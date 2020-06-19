package com.dg.main.controller.admin;

import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.dg.main.exception.ExceptionCode;
import com.dg.main.util.Result;
import com.dg.main.vo.OrdersParamVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.dg.main.util.slzcResult;
import com.dg.main.service.OrdersParamServer;

@Controller
@Api(tags = "后台-后台订单管理")
@RequestMapping("/OrdersParam")
public class OrdersParamController {

	public static final String FIND_ALL = "/findAll";
	@Autowired
	private OrdersParamServer ordersParamServer;
	
	@GetMapping(FIND_ALL)
	@ResponseBody
	@ApiOperation(value = "findAll",notes = "查询所有")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "offset",value = "第几页"),
			@ApiImplicitParam(name = "limit",value = "每页数量"),
			@ApiImplicitParam(name = "search",value = "查询条件")
	})
	public slzcResult findAll(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15")Integer limit,String search,HttpServletRequest request) {
		
		return ordersParamServer.selectAll(offset, limit,search);
	}
	
	//添加订单
	@RequestMapping("/add")
	@ResponseBody
	@ApiOperation(value = "addBusiness",notes = "添加")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "order",value = "信息")
	})
	public Result addBusiness(OrdersParamVo order) {
		ModelMap map = new ModelMap();
		
//		order.setOrderNumber();
		order.setOrderCode(String.valueOf(new Date().getTime()));
//		order.setCreateTime(new Timestamp(new Date().getTime()));
//		order.setModifyTime(new Timestamp(new Date().getTime()));
		order.setCreateTime(new Timestamp(new Date().getTime()));
		
		int save = ordersParamServer.save(order);
		
		if (save>0) {
			map.put("data", 200);
			return Result.successResult();
		}
		map.put("data", 400);
		return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
	}
	
	//删除
	@DeleteMapping("/del")
	@ResponseBody
	@ApiOperation(value = "del",notes = "删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids",value = "id")
	})
	public Result del(String [] ids) {
		
		ModelMap map = new ModelMap();
		
		int dels = 0;
		if (ids != null && ids.length > 0) {
			dels = ordersParamServer.deleteAllId(ids);
		}
		if (dels > 0) {
			map.put("data",200);
			return Result.successResult();
		}
		map.put("data",400);
		return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
	}
	
	//修改
	@PostMapping("/up")
	@ResponseBody
	@ApiOperation(value = "update",notes = "修改")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "order",value = "信息")
	})
	public Result update(OrdersParamVo order) {
		
		ModelMap map = new ModelMap();
//		order.setModifyTime(new Timestamp(new Date().getTime()));
		int update = ordersParamServer.update(order);
		if (update > 0) {
			map.put("data",200);
			return Result.successResult();
		}
//		map.put("data",400);
		return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
	}
	//页面跳转
	@GetMapping("/skip")
	public String SkipPage() {
		return "OrdersParam";
	}
}
