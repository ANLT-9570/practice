package com.dg.main.controller.app.common.other;

import java.util.List;

import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.dg.main.Entity.Comment;
import com.dg.main.service.CommentServer;

@RestController
@RequestMapping("/common/App/AppCommentController")
@Api(tags = "通用-评论")
public class CommonCommentController {

	@Autowired
	private CommentServer commentServer;
	
	@PostMapping("/add")
	@ApiOperation(value = "add",notes = "添加评论")
	@ApiImplicitParams({@ApiImplicitParam(name = "comment",value = "评论信息")
	})
	public Result add(Comment comment) {
		 commentServer.save(comment);
		return Result.successResult();
	}
	
	//根据商品规格id查询
	@GetMapping("/findBySpecificationsId")
	@ApiOperation(value = "findBySpecificationsId",notes = "根据商品规格id查询")
	@ApiImplicitParams({@ApiImplicitParam(name = "SpecificationsId",value = "商品id")
	})
	public Result findBySpecificationsId(@RequestParam("SpecificationsId") Long SpecificationsId){
		List<Comment> bySpecificationsId = commentServer.findBySpecificationsId(SpecificationsId);
		return Result.successResult(bySpecificationsId);
	}
	
}
