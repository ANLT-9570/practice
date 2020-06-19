package com.dg.main.controller.admin;

import com.dg.main.exception.ExceptionCode;

import com.dg.main.service.CertificationServer;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.dg.main.util.slzcResult;

import javax.annotation.Resource;


@Controller
@Api(tags = "后台-实名认证后台")
@RequestMapping("/RealNameAdmin")
public class RealNameController {

	@Resource
	private CertificationServer certificationServer;

	
	/**
	 * 分页查询
	 * @param limit	每页显示的数量
	 * @param offset 其实页
	 * @return
	 */
	@GetMapping("/findAll")
	@ResponseBody
	@ApiOperation(value = "findAll",notes = "查询所有")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "offset",value = "第几页"),
			@ApiImplicitParam(name = "limit",value = "每页数量"),
			@ApiImplicitParam(name = "search",value = "查询条件")
	})
	public slzcResult findAll(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "0")Integer limit,String search) {
//		return realNameServer.selectAll(offset,limit,search);.
		slzcResult slzcResult = certificationServer.list(offset,limit,search);
		return slzcResult;
	}
	
	//商家添加
//	@PostMapping("/add")
//	@ResponseBody
//	@ApiOperation(value = "addBusiness",notes = "添加")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "realName",value = "信息")
//	})
//	public Result addBusiness(RealName realName) {
//
//		ModelMap map = new ModelMap();
//		String iDcar = realName.getIDcar();
//		realName.setIDcar(null);
//		//查询手机号码是否存在
//		RealName busine = null;//realNameServer.selectOne(realName);
//		if (busine != null ) {
//			map.put("data",500);
//			return Result.successResult(500);
//		}
//		String phone = realName.getPhone();
//		realName.setPhone(null);
//		realName.setIDcar(iDcar);
//
//		//查询身份证是否存在
//		RealName selectOne = null;//ealNameServer.selectOne(realName);
//		if (selectOne != null ) {
//			map.put("data",600);
//			return Result.successResult(600);
//		}
//		realName.setPhone(phone);
//		Timestamp ts = new Timestamp(new Date().getTime());
//		realName.setCreatetime(ts);
//		realName.setModifytime(ts);
//
//		int save = 0;//realNameServer.save(realName);
//
//		if (save>0) {
//			map.put("data", 200);
//			return Result.successResult();
//		}
//		map.put("data", 400);
//		return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
//	}
	
	//删除
	@DeleteMapping("/del")
	@ResponseBody
	@ApiOperation(value = "del",notes = "删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "ids",value = "id")
	})
	public Result del(String [] ids) {
		
//		ModelMap map = new ModelMap();
		
		int dels = 0;
		if (ids.length > 0) {
			dels = 0;//realNameServer.deleteAllId(ids);
		}
		if (dels > 0) {
//			map.put("data",200);
			return Result.successResult();
		}
//		map.put("data",400);
		return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
	}
	
	//修改
//	@PostMapping("/up")
//	@ResponseBody
//	@ApiOperation(value = "update",notes = "修改")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "realName",value = "信息")
//	})
//	public Result update(RealName realName) {
//
//		ModelMap map = new ModelMap();
//
//		Timestamp ts = new Timestamp(new Date().getTime());
//		realName.setModifytime(ts);
//		int update = 0;//realNameServer.update(realName);
//
//		if (update > 0) {
//			map.put("data",200);
//			return Result.successResult();
//		}
//		map.put("data",400);
//		return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
//	}
	//页面跳转
	@GetMapping("/skip")
	public String SkipPage() {
		return "RealName";
	}
	@GetMapping("/index")
	public String SkipPageIndex() {
		return "login";
	}
//	@GetMapping("/verify")
//	@ResponseBody
//	@ApiOperation(value = "VerifyPI",notes = "校验")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "v",value = "信息")
//	})
//	public Result VerifyPI(String v) {
//		ModelMap map = new ModelMap();
//		RealName realName = new RealName();
//		realName.setPhone(v);
//		RealName one = null;//realNameServer.selectOne(realName);
//		if (one != null) {
//			map.put("data", 500);
//			return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
//		}
//		map.put("data", 200);
//		return Result.successResult();
//	}
	
	//通过
	@PostMapping("/pass")
	@ResponseBody
	@ApiOperation(value = "pass",notes = "通过")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id",value = "用户id"),
			@ApiImplicitParam(name = "status",value = "状态")
	})
	public Result pass(@RequestParam("id") Long id,@RequestParam("status")Integer status,@RequestParam("isPassed")Integer isPassed) {
		Result result = certificationServer.pass(id,status,isPassed);
		return result;
	}


}