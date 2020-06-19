package com.dg.main.controller.app.common;

import com.dg.main.exception.ExceptionCode;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dg.main.Entity.Feedback;
import com.dg.main.service.FeedbackServer;

//反馈
@RestController
@Api(tags = "通用-反馈")
@RequestMapping("/common/AppFeedback")
public class CommonFeedbackController {

//	@Autowired
//	private FeedbackServer feedbackServer;
//
//	//意见反馈
//	@PostMapping("/add")
//	@ApiOperation(value = "add",notes = "反馈信息")
//	@ApiImplicitParams({@ApiImplicitParam(name = "feedback",value = "反馈信息")
//	})
//	public Result add(Feedback feedback) {
//
//		ModelMap map = new ModelMap();
//
//		int save = feedbackServer.save(feedback);
//		if (save>0) {
//			map.put("feedback", 200);
//			return Result.successResult();
//		}
//		map.put("feedback", 400);
//		return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
//	}
}
