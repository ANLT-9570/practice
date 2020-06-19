package com.dg.main.controller.app.buyer;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dg.main.Entity.shop.Shops;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.shop.ShopsRepository;
import com.dg.main.repository.users.FollowRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.util.Result;
import com.dg.main.vo.FollowVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;

import com.dg.main.Entity.Follow;

import com.dg.main.service.FollowServer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


//关注
@RestController
@RequestMapping("/buyer/App/AppFollow")
@Api(tags = "用户-关注")
public class BuyerFollowController {

	@Autowired
	private FollowServer followServer;

	@Autowired
	FollowRepository followRepository;
	@Autowired
	ShopsRepository shopsRepository;
	@Autowired
	MobileUserRepository mobileUserRepository;

	//我的关注(客户)
	@GetMapping("/findByMyFollow")
	@ApiOperation(value = "我的关注",notes = "我的关注(客户)")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "uid",value = "用户id"),
			@ApiImplicitParam(name = "offset",value = "页数"),
			@ApiImplicitParam(name = "limit",value = "每页显示的记录数")
	})
	public Result findByMyFollow(String uid, @RequestParam(defaultValue = "0") Integer offset,@RequestParam(defaultValue = "15") Integer limit) {
		if(uid == null){
			return Result.failureResult(ExceptionCode.Failure.NOT_USERID);
		}
		MobileUser mobileUser = mobileUserRepository.selectMobileUse(Long.valueOf(uid));
		if(mobileUser == null){
			return Result.failureResult(ExceptionCode.Failure.NOT_USER);
		}
		//根据用户id查询拥有的关注
		PageRequest pageRequest = PageRequest.of(offset, limit);
		Specification<Follow> specification = new Specification<Follow>() {
			@Override
			public Predicate toPredicate(Root<Follow> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<>();
//				query.where(cb.);
				list.add(cb.equal(root.get("userId").as(Long.class),uid));
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};

		Page<Follow> page = followRepository.findAll(specification,pageRequest);
		List<FollowVo> followVos = Lists.newArrayList();
		List<Follow> content = page.getContent();
		for(Follow follow : content){

			FollowVo followVo = new FollowVo();
			BeanUtils.copyProperties(follow,followVo);
			Optional<Shops> optional = shopsRepository.findById(follow.getShopId());
			if (optional.isPresent()){
				followVo.setShopName(optional.get().getName());
				followVo.setImage(optional.get().getDisplayImg());
				followVos.add(followVo);
			}

		}

		return Result.successResult(followVos);
	}
	
	@PostMapping("/clickFollow")
	@ApiOperation(value = "点击关注",notes = "点击关注")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "uid",value = "用户id"),
			@ApiImplicitParam(name = "shopId",value = "商铺id")
	})
	public Result clickFollow(Long uid,Long shopId) {
		if(uid == null){
			return Result.failureResult(ExceptionCode.Failure.NOT_USERID);
		}
		if(shopId == null){
			return Result.failureResult(ExceptionCode.Failure.NOT_SHOPID);
		}
		MobileUser mobileUser = mobileUserRepository.selectMobileUse(uid);
		if(mobileUser == null){
			return Result.failureResult(ExceptionCode.Failure.NOT_USER);
		}
		Optional<Shops> optional_ = shopsRepository.findById(shopId);
		if(!optional_.isPresent()){
			return Result.failureResult(ExceptionCode.Failure.NOT_SHOP);
		}
		System.out.println("<--------------------------------->");
//		擦好像1是否关注过
		Follow follow = followRepository.findByUserIdAndShopId(uid,shopId);
		int is=0;
		if(follow ==null){
			follow = new Follow();
//			添加关注
			follow.setShopId(shopId);
			follow.setUserId(uid);
			follow.setStatus(1L);

			Follow save = followRepository.save(follow);

			if(save!= null){
				return Result.successResult("关注成功");
				}else{
				return Result.successResult("关注失败");
			}
		}else{
			//取消关注直接删除
			followRepository.delete(follow);
			return Result.successResult("取消关注成功");
		}
	}
	
	
	@DeleteMapping("/delIdsAll")
	@ApiOperation(value = "批量删除",notes = "批量删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id",value = "id")
	})
	public Result delIds(@RequestParam("id") List<Long>  id) {
		if(id == null || id.size() == 0){
			return Result.failureResult(ExceptionCode.Failure.NOT_ID);
		}
		followRepository.deleteIds(id);
		return Result.successResult();
	}

	@DeleteMapping("/delId")
	@ApiOperation(value = "单个删除",notes = "单个删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id",value = "id")
	})
	public Result delFollow(@RequestParam("id") Long  id) {
		if(id == null ){
			return Result.failureResult(ExceptionCode.Failure.NOT_ID);
		}
		followRepository.deleteById(id);
		return Result.successResult();
	}

	@DeleteMapping("/delUserId")
	@ApiOperation(value = "根据用户id删除",notes = "根据用户id删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId",value = "用户id")
	})
	public Result delUserId(@RequestParam("userId") Long  userId) {
		if(userId == null ){
			return Result.failureResult(ExceptionCode.Failure.NOT_USERID);
		}
		MobileUser mobileUser = mobileUserRepository.selectMobileUse(userId);
		if(mobileUser == null){
			return Result.failureResult(ExceptionCode.Failure.NOT_USER);
		}
		followRepository.deleteByUserId(userId);
		return Result.successResult();
	}

}
