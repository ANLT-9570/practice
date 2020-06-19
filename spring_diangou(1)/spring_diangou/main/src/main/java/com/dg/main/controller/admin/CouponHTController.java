package com.dg.main.controller.admin;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.dg.main.serviceImpl.CouponServiceImpl;

@Controller
@Api(tags = "后台-购物券后台")
@RequestMapping("/CouponHT")
public class CouponHTController {

	@Autowired
	private CouponServiceImpl couponServiceImpl;
	
//	@GetMapping("/findAll")
//	@ResponseBody
//	@ApiOperation(value = "findAll",notes = "查询所有")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "offset",value = "第几页"),
//			@ApiImplicitParam(name = "limit",value = "每页数量"),
//			@ApiImplicitParam(name = "search",value = "查询条件")
//	})
//	public slzcResult findAll(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15")Integer limit,String search) {
//		return couponServiceImpl.selectAll(offset,limit,search);
//	}
	
	//商家
//	@PostMapping("/add")
//	@ResponseBody
//	@ApiOperation(value = "addBusiness",notes = "添加")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "activitys",value = "信息")
//	})
//	public Result addBusiness(Coupon activitys) {
//
//		ModelMap map = new ModelMap();
//		Timestamp ts = new Timestamp(new Date().getTime());
//		System.out.println(ts);
//		activitys.setTime(ts);
//		activitys.setStartTime(ts);
//		activitys.setEndTime(ts);
//
//		int save = couponServiceImpl.save(activitys);
//
//		if (save>0) {
//			map.put("data", 200);
//			return Result.successResult();
//		}
//		map.put("data", 400);
//		return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
//	}
	
	//删除
//	@DeleteMapping("/del")
//	@ResponseBody
//	@ApiOperation(value = "del",notes = "删除")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "ids",value = "id")
//	})
//	public Result del(String [] ids) {
//
//		ModelMap map = new ModelMap();
//
//		int dels = 0;
//		if (ids.length > 0) {
//			dels = couponServiceImpl.deleteAllId(ids);
//		}
//		if (dels > 0) {
//			map.put("data",200);
//			return Result.successResult();
//		}
////		map.put("data",400);
//		return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
//	}
	
	//修改
//	@PostMapping("/up")
//	@ResponseBody
//	@ApiOperation(value = "update",notes = "修改")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "feedback",value = "信息")
//	})
//	public Result update(Coupon activitys) {
//
//		ModelMap map = new ModelMap();
////		activitys.setModifytime(new Timestamp(new Date().getTime()));
//
//		int update = couponServiceImpl.update(activitys);
//		if (update > 0) {
//			map.put("data",200);
//			return Result.successResult();
//		}
////		map.put("data",400);
//		return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
//	}
	//页面跳转
	@RequestMapping("/skip")
	public String SkipPage() {
		return "Coupon";
	}
		
}
