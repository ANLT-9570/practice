package com.dg.main.controller.admin;

import com.dg.main.repository.users.CustomerAddressRepository;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.dg.main.Entity.users.CustomerAddress;
import com.dg.main.util.slzcResult;

@Controller
@Api(tags = "后台-购物券后台")
@RequestMapping("/CustomerAddress")
public class CustomerAddressController {

	@Autowired
	private CustomerAddressRepository repository;
	
	@GetMapping("/findAllCustomerAdd")
	@ResponseBody
	@ApiOperation(value = "findAll",notes = "查询所有")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "offset",value = "第几页"),
			@ApiImplicitParam(name = "limit",value = "每页数量"),
			@ApiImplicitParam(name = "search",value = "查询条件")
	})
	public slzcResult findAll(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15")Integer limit,String search) {
//		if (offset == null) {
//			offset = 0;
//		}
//		if (limit == null) {
//			limit = 10;
//		}
		return null;
	}
	
	//添加地址
	@PostMapping("/addCustomer")
	@ResponseBody
	@ApiOperation(value = "addBusiness",notes = "添加")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "customerAddress",value = "信息")
	})
	public Result addBusiness(CustomerAddress customerAddress) {
		ModelMap map = new ModelMap();
		
		repository.save(customerAddress);
		

			return Result.successResult();

	}
	
	//删除
	@DeleteMapping("/delCustomerAddress")
	@ResponseBody
	@ApiOperation(value = "del",notes = "删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids",value = "id")
	})
	public Result del(String [] ids) {
		
//		ModelMap map = new ModelMap();
		
//		int dels = 0;
//		if (ids.length > 0) {
//			dels = customerAddressServer.deleteAllId(ids);
//		}
//		if (dels > 0) {
////			map.put("data",200);
//			return Result.successResult();
//		}
//		map.put("data",400);
		return Result.successResult();
	}
	
	//修改
	@PostMapping("/upCad")
	@ResponseBody
	@ApiOperation(value = "update",notes = "修改")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "customerAddress",value = "信息")
	})
	public Result update(CustomerAddress customerAddress) {
		
		ModelMap map = new ModelMap();
		
		 repository.save(customerAddress);

			return Result.successResult();

//		map.put("data",400);
	//	return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
	}
	//页面跳转
	@RequestMapping("/skip")
	public String SkipPage() {
		return "CustomerAddress";
	}
}
