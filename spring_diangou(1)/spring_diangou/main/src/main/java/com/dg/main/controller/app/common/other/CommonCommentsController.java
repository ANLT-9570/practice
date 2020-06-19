package com.dg.main.controller.app.common.other;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.dg.main.Entity.users.MobileUser;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.AppCommentsRepostory;
import com.dg.main.repository.AppReplyRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.serviceImpl.orders.service.OrdersService;

import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;
import com.dg.main.Entity.AppComment;
import com.dg.main.Entity.AppReply;

import com.dg.main.service.AppCommentService;

@RestController
@RequestMapping("/common/app/comments")
@Api(tags = "通用-评论_2")
public class CommonCommentsController {
	
	@Autowired
	private AppCommentService  appCommentService;

	@Autowired
	AppCommentsRepostory commentRepository;

	@Autowired
	MobileUserRepository mobileUserRepository;

	@Autowired
	AppReplyRepository appReplyRepository;

	@Autowired
	OrdersService ordersService;
	@GetMapping("/list")
	@ApiOperation(value = "list",notes = "根据商品规格id查询")
	@ApiImplicitParams({@ApiImplicitParam(name = "SpecificationsId",value = "商品id")
	})
	public Result list( Long SpecificationsId) {
		if(SpecificationsId == null){
			return Result.failureResult(ExceptionCode.Failure.NOT_ID);
		}
		ModelMap modelMap = new ModelMap();
		List<AppComment> list = appCommentService.list(SpecificationsId);
		if(list!=null&&list.size()>0){
			
			 Map<Long, AppComment> hashMap =  new HashMap<Long, AppComment>();
			 Map<String, List<AppComment>> hashMaps =  new HashMap<String, List<AppComment>>();
			for (AppComment appComment : list) {
			
				hashMap.put(appComment.getCommentId(), appComment);
			}
			
			for(Long mLong :hashMap.keySet()){
				System.out.println("mLong"+mLong);
				 List<AppComment> huifu = new ArrayList<AppComment>();
				for(AppComment appComment : list){
					if(appComment.getCommentId()==mLong){
						huifu.add(appComment);
					}
				}
				AppComment appComment = hashMap.get(mLong);
				
				Gson gson  = new Gson();
				String json = gson.toJson(appComment);
				hashMaps.put(json, huifu);
			}
			modelMap.put("data", hashMaps);
			return Result.successResult(hashMap);
		}else{

			modelMap.put("data", "");
		}
		return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
	}
	
	@PostMapping("/appComment")
	@ApiOperation(value = "add",notes = "添加评论")
	@ApiImplicitParams({@ApiImplicitParam(name = "appComment",value = "评论信息")
	})
	public Result add(AppComment appComment){

		//	ModelMap modelMap = new ModelMap();
//		if(!ordersService.isBuyed(appComment.getMobileUserId().intValue(),appComment.getSpecificationsId())){
//			return Result.failureResult(ExceptionCode.Failure.USER_NO_BUYED);
//		}
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		appComment.setCreateTime(timestamp);

		MobileUser selectMobileUse = mobileUserRepository.selectMobileUse(appComment.getMobileUserId());
//		Business selectMobileUse = appCommentService.selectMobileUse(appComment.getMobileUserId());

			if(selectMobileUse.getName()!=null&&selectMobileUse.getName()!=""){
				appComment.setName(selectMobileUse.getName());
				
			}else{
				 String str = "****";
				 String name = selectMobileUse.getPhone();
				 StringBuilder sb = new StringBuilder(name);
				 sb.replace(3, 7, str);
				 appComment.setName(sb.toString());
			}
			

//		int add = appCommentService.add(appComment);
		AppComment save = commentRepository.save(appComment);

		if(save != null){
			return Result.successResult();
		}else{
			return Result.failureResult(ExceptionCode.Failure.INSERT_FAILED);
		}
	}
	
	
	
	@PostMapping("/addReply")
	@ApiOperation(value = "addReply",notes = "回复")
	@ApiImplicitParams({@ApiImplicitParam(name = "appReply",value = "回复内容")
	})
	public Result addReply(AppReply appReply){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		appReply.setCreateTime(timestamp);

		AppReply save = appReplyRepository.save(appReply);
		if (save == null) {
			return Result.failureResult("44444","添加失败");
		}
//		appCommentService.addReply(appReply);
//

		return Result.successResult();
	}
	
	

}
