package com.dg.main.controller.admin;


import com.dg.main.exception.ExceptionCode;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.dg.main.Entity.Comment;
import com.dg.main.util.slzcResult;
import com.dg.main.service.CommentServer;

@RequestMapping("/CommentController")
@Controller
@Api(tags = "后台-评论后台")
public class CommentController {

	@Autowired
	private CommentServer feedbackServer;
	
	@GetMapping("/findAll")
	@ResponseBody
	@ApiOperation(value = "findAll",notes = "查询所有")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "offset",value = "第几页"),
			@ApiImplicitParam(name = "limit",value = "每页数量"),
			@ApiImplicitParam(name = "search",value = "查询条件")
	})
	public slzcResult findAll(@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15")Integer limit,String search) {
		return feedbackServer.selectAll(offset, limit,search);
	}
	
	//
	@PostMapping("/add")
	@ResponseBody
	@ApiOperation(value = "addBusiness",notes = "添加")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "feedback",value = "信息")
	})
	public Result addBusiness(Comment feedback) {
		ModelMap map = new ModelMap();
		
		
		int save = feedbackServer.save(feedback);
		
		if (save>0) {
//			map.put("data", 200);
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
		
//		ModelMap map = new ModelMap();
		
		int dels = 0;
		if (ids.length > 0) {
			dels = feedbackServer.deleteAllId(ids);
		}
		if (dels > 0) {
//			map.put("data",200);
			return Result.successResult();
		}
//		map.put("data",400);
		return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
	}
	
	//修改
	@PostMapping("/up")
	@ResponseBody
	@ApiOperation(value = "update",notes = "修改")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "feedback",value = "信息")
	})
	public Result update(Comment feedback) {
		
		ModelMap map = new ModelMap();
		int update = feedbackServer.update(feedback);
		if (update > 0) {
			map.put("data",200);
			return Result.successResult();
		}
//		map.put("data",400);
		return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
	}
	
	//页面跳转
	@GetMapping("/skip")
	@ApiOperation(value = "SkipPage",notes = "页面跳转")
	public String SkipPage() {
		return "Comment";
	}
}
