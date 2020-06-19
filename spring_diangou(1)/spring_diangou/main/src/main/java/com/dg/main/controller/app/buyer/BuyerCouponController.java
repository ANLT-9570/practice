package com.dg.main.controller.app.buyer;


import com.dg.main.Entity.users.Coupon;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.users.CouponRepository;
import com.dg.main.serviceImpl.users.UserCouponsService;
import com.dg.main.util.Result;
import com.dg.main.vo.CouponItemVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;


import com.dg.main.serviceImpl.CouponServiceImpl;

import java.util.List;


@RestController
@RequestMapping("/buyer/App/AppCoupon")
@Api(tags = "用户-购物券")
public class BuyerCouponController {

//	@Autowired
//    CouponServiceImpl couponService;

//	@Autowired
//	private CouponRepository couponRepository;
	@Autowired
	UserCouponsService userCouponsService;

	//客户查看自己的购物券
	@GetMapping("/findByUserId")
	@ApiOperation(value = "客户查看自己的购物券",notes = "findByUserId")
	@ApiImplicitParams({@ApiImplicitParam(name = "userId",value = "用户Id"),
			@ApiImplicitParam(name = "offset",value = "第几页"),
			@ApiImplicitParam(name = "limit",value = "每页显示数量")
	})
	public Result findByUserId(Long userId, @RequestParam(defaultValue = "0")int offset, @RequestParam(defaultValue = "15")int limit){
		PageRequest pageRequest = PageRequest.of(offset, limit);
		Result result = userCouponsService.getUserList(userId,pageRequest);

		return result;
	}
	//	删除购物卷
	@DeleteMapping(value="/del")
	@ApiOperation(value = "单个删除购物卷",notes = "del")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "couponItemId",value = "购物券id")
	})
	public Result del(Long couponItemId) {
		if(couponItemId == null){
			return Result.failureResult(ExceptionCode.Failure.NOT_ID);
		}
		return userCouponsService.deleteByItemId(couponItemId);
	}

	@GetMapping("/findByShopIdAndUserId")
	@ApiOperation(value = "根据商铺id和用户id查询",notes = "findByShopIdAndUserId")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId",value = "用户id"),
			@ApiImplicitParam(name = "shopId",value = "商铺id"),
//			@ApiImplicitParam(name = "offset",value = "第几页"),
//			@ApiImplicitParam(name = "limit",value = "每页显示数量")
	})
	public Result findByShopIdAndUserId(Long userId,Long  shopId) {

		return userCouponsService.findByShopIdAndUserId(userId,shopId);
	}

	@DeleteMapping(value="/deleteByIdUserId")
	@ApiOperation(value = "批量删除购物卷",notes = "deleteByIdUserId")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id",value = "购物券id")
	})
	public Result deleteByIdUserId(Long userId) {
		if(userId == null){
			return Result.failureResult(ExceptionCode.Failure.NOT_ID);
		}
		return userCouponsService.deleteByUserId(userId);
	}
	//领取购物券
	@PostMapping(value="/getCoupon")
	@ApiOperation(value = "领取购物卷",notes = "getCoupon")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "coupon",value = "购物券信息"),
	})
	public Result getCoupon(Long userId, Long shopId,Long couponId) {

		return userCouponsService.add(userId,shopId,couponId);
	}
	
	//商铺中的所有购物券
//	@PostMapping("/findByShopId")
//	@ApiOperation(value = "findByShopId",notes = "商铺中的所有购物券")
//	@ApiImplicitParams({@ApiImplicitParam(name = "shopId",value = "商铺Id"),
//			@ApiImplicitParam(name = "offset",value = "第几页"),
//			@ApiImplicitParam(name = "limit",value = "每页显示数量")
//	})
//	public Result findByShopId(Long shopId,@RequestParam(defaultValue = "0")Integer offset,@RequestParam(defaultValue = "15")Integer limit){
////		return couponService.findByShopId(shopId,offset,limit);
////		Specification<Coupon> specification = new Specification<Coupon>() {
////			@Override
////			public Predicate toPredicate(Root<Coupon> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
////				List<Predicate> list = new ArrayList<>();
//////				query.where(cb.);
//////				list.add(cb.equal(root.get("mobileUseId").as(Long.class),Long.valueOf(uid)));
////				list.add(cb.equal(root.get("shopId").as(Long.class),shopId));
////				Predicate[] p = new Predicate[list.size()];
////				return cb.and(list.toArray(p));
////			}
////		};
////		PageRequest of = PageRequest.of(offset, limit);
////		Page<Coupon> page = couponRepository.findAll(specification,of);
//		return Result.successResult();
//	}
	//添加购物券
//	@PostMapping(value="/addCoupon")
//	@ApiOperation(value = "商铺添加购物卷",notes = "addCoupon")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "coupon",value = "购物券信息"),
//	})
//	public Result addCoupon(@RequestBody Coupon coupon) {
//		return userCouponsService.addCoupon(coupon);
//	}



}
