package com.dg.main.controller.app.seller;

import java.math.BigDecimal;
import java.util.List;

import com.dg.main.exception.ExceptionCode;
import com.dg.main.param.shops.SpecificationsParam;
import com.dg.main.service.SpecificationsServer;

import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;



import com.dg.main.Entity.shop.Size;
import com.dg.main.Entity.shop.Specifications;



@Api(tags = "商家-商品规格")
@RestController
@RequestMapping("/seller/AppSpecifications")
public class SellerSpecificationsController {
	
	@Autowired
	private SpecificationsServer specificationsServer;
	

	/**
	 * 
	 * @return
	 */
	
//	@GetMapping("/listSort")
//	@ApiOperation(value = "listSort",notes = "所有商品")
//	public Result listSort(){
//		ModelMap map = new ModelMap();
//		 List<Homepage> listSort = specificationsServer.listSort();
//		 map.put("listSort", listSort);
//
//		return Result.successResult(listSort);
//	}
	
//	@PostMapping("/count")
//	public void count (RealName realName) {
////		realNameServer.updateUpdateBrowsingVolume(realName);
//	}
	
	//查询商铺商品
//	@GetMapping("/listShopSort")
//	@ApiOperation(value = "listShopSort",notes = "查询商铺商品")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "mobileUseId",value = "用户id"),
//	})
//	public Result listShopSort(Long mobileUseId){
//		ModelMap map = new ModelMap();
//		 List<Homepage> listSort = specificationsServer.listShop(mobileUseId);
//		 map.put("listSort", listSort);
//
//		return Result.successResult(listSort);
//	}
	
	
	
	@GetMapping("/selectSpecifications")
	@ApiOperation(value = "根据商品id查询",notes = "selectSpecifications")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "SpecificationsId",value = "商品id"),
	})
	public Result selectSpecifications (long SpecificationsId) {
		List<Size> selectSpecifications = specificationsServer.selectSpecifications(SpecificationsId);
		return Result.successResult(selectSpecifications);
	}
	
	@PostMapping("/appAdd")
	@ApiOperation(value = "添加商品",notes = "appAdd")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "param",value = "商品信息"),
	})
	public Result appAdd (@RequestBody SpecificationsParam param) {
//		return specificationsServer.appAdd(specifications);
		///return Result.successResult(specificationsServer.appAdd(specifications));
		specificationsServer.add(param);
		return Result.successResult();
	}
	
	
	//修改规格
	@PostMapping("/upZie")
	@ApiOperation(value = "修改规格",notes = "upZie")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "sid",value = "商家id"),
			@ApiImplicitParam(name = "count",value = "库存"),
			@ApiImplicitParam(name = "jg",value = "价格"),
			@ApiImplicitParam(name = "spidd",value = "商品Id"),
	})
	public Result upZie (Long sid,int count,BigDecimal jg,Long spidd) {
		return Result.successResult(specificationsServer.upZie(sid,count,jg,spidd));
	}
	
	
	@PostMapping("/SpecificationsCount")
	@ApiOperation(value = "减库存",notes = "SpecificationsCount")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "specificationsId",value = "商品id"),
			@ApiImplicitParam(name = "number",value = "数量")
	})
	public Result SpecificationsCount(Long specificationsId,Long number){
		ModelMap map = new ModelMap();
		int m = specificationsServer.m(specificationsId,number);
		if(m > 0){
			map.put("data", 200);
			return Result.successResult();
		}else{
			map.put("data", 400);

			return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
		}

	}
	
//	商品下架
	@PostMapping("/soldOut")
	@ApiOperation(value = "商品下架",notes = "soldOut")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "specificationsId",value = "商品id"),
			@ApiImplicitParam(name = "status",value = "状态")
	})
	public Result soldOut(Long specificationsId,int status){
		ModelMap map = new ModelMap();
		int m = specificationsServer.soldOut(specificationsId,status);
		if(m > 0){
			map.put("data", 200);
			return Result.successResult();
		}else{
			map.put("data", 400);
			return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
		}
	}
	
	
//	删除商品
	@DeleteMapping("/canCel")
	@ApiOperation(value = "删除商品",notes = "canCel")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "specificationsId",value = "商品id")

	})
	public Result Cancel(Long specificationsId){
		ModelMap map = new ModelMap();
		int m = specificationsServer.canCel(specificationsId);
		if(m > 0){
			map.put("data", 200);
			return Result.successResult();
		}else{
			map.put("data", 400);
			return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
		}
	}
//	查询库存是否还有
	@GetMapping("/findCount")
	@ApiOperation(value = "查询库存是否还有",notes = "findCount")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "specificationsId",value = "商品id")
	})
	public Result findCount(Long szid){
		ModelMap map = new ModelMap();
		Size size = specificationsServer.findCount(szid);
			map.put("data", size);
		return Result.successResult(size);
	}
	@GetMapping("/search")
	@ApiOperation(value = "查询",notes = "search")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "term",value = "查询条件")
	})
	public Result search(String term){
		return Result.successResult(specificationsServer.searchSpecifications(term));
	}


//	@GetMapping("/findByShopId")
//	@ApiOperation(value = "根据店铺id查询",notes = "findByShopId")
//	@ApiImplicitParams({
//			@ApiImplicitParam(name = "shopId",value = "店铺shopId")
//	})
//	public Result findByShopId(Long shopId){
//		Result result = specificationsServer.findByShopId(shopId);
//		return result;
//	}

}
