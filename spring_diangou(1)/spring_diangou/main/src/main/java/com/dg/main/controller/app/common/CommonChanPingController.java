package com.dg.main.controller.app.common;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.SpecificationsRepository;
import com.dg.main.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.dg.main.Entity.shop.Specifications;

import com.dg.main.service.CollectionServer;
import com.dg.main.service.SpecificationsServer;

@RestController
@RequestMapping("/common/app/chan/ping")
@Api(tags = "通用-商品")
public class CommonChanPingController {


	@Autowired
	private SpecificationsServer specificationsServer;
	@Autowired
	private CollectionServer collectionServer;

	@Autowired
	private SpecificationsRepository specificationsRepository;
	/**
	 * 分页查询
	 * @param limit	结束第几个
	 * @param offset 开始第几个
	 * @return
	 */
	@GetMapping("/Specifications/findAll")
	@ApiOperation(value = "findAll",notes = "商品分页查询")
	@ApiImplicitParams({@ApiImplicitParam(name = "offset",value = "第几页数"),
			@ApiImplicitParam(name = "limit",value = "每页显示的数量")})
	public Result findAll(@RequestParam(defaultValue = "0") Integer offset,@RequestParam(defaultValue = "15") Integer limit) {
		PageRequest pageRequest = PageRequest.of(offset, limit);
		Page<Specifications> page = specificationsRepository.findAll(pageRequest);
		List<Specifications> content = page.getContent();
		return Result.successResult(content);
	}
	//APP分页查询
	@ApiOperation(value = "findAll",notes = "APP商品分页查询")
	@ApiImplicitParams({@ApiImplicitParam(name = "offset",value = "第几页数"),
			@ApiImplicitParam(name = "limit",value = "每页显示的数量")
	,@ApiImplicitParam(name = "mobileUseId",value = "商家id"),@ApiImplicitParam(name = "uid",value = "用户id"),
			@ApiImplicitParam(name = "search",value = "搜索条件")})
	@GetMapping("/Specifications/findAllFenye")
	public Result findAllFenye(@RequestParam(defaultValue = "0")int offset,@RequestParam(defaultValue = "15")int limit,int mobileUseId,Long uid,String search) {
		ModelMap map=new ModelMap();
//	Specifications specifications=new Specifications();
       String code="500";
		System.err.println(offset+","+limit+","+mobileUseId);
		
		List<Specifications> listShop=new ArrayList<Specifications>();
		
		listShop=specificationsServer.findAllFenye(offset,limit,mobileUseId,uid,search);
	
//		listShop=specificationsServer.selectAll(offset,limit);
		if (listShop!=null && listShop.size()>0) {
			code="200";
			map.put("listShop", listShop);
		}
		map.put("code", code);
		return Result.successResult(listShop);
	}
	
	//商家添加
//	@ApiOperation(value = "addBusiness",notes = "商家添加")
//	@ApiImplicitParams({@ApiImplicitParam(name = "specifications",value = "商品参数")
//			})
//	@PostMapping("/Specifications/add")
//	public Result addBusiness(Specifications specifications,HttpServletResponse response,HttpServletRequest request) {

//		ModelMap map = new ModelMap();
//		String code="";
//        System.err.println(specifications.getImg());
//		Timestamp ts = new Timestamp(new Date().getTime());
//		System.out.println(ts);
//		Specifications specifications2=new Specifications();
////		specifications2.setMobileUseId(specifications.getMobileUseId());      //商家ID
////		specifications2.setName(specifications.getName());                  //商品名称
//		specifications2.setTypes(specifications.getTypes());        		//类型
//		specifications2.setDescribe(specifications.getDescribe());  		//描述
//		specifications2.setImg(specifications.getImg());                    //图片
//		specifications2.setPrice(specifications.getPrice());                //原价
//		specifications2.setDiscountPrice(specifications.getDiscountPrice());//优惠价
//		specifications2.setBrand(specifications.getBrand());                //品牌
//		specifications2.setNumber(specifications.getNumber());				//数量
//		specifications2.setSize(specifications.getSize());					//尺寸
//		specifications2.setColour(specifications.getColour());				//颜色
//		specifications2.setWeight(specifications.getWeight());				//重量
//		specifications2.setStatus(specifications.getStatus());              //状态 0上架 1下架 3删除
//		specifications2.setCountry(specifications.getCountry());           //国家
//		specifications2.setCreatetime(ts);
//		int save = specificationsServer.save(specifications2);
//
//		if (save>0) {
//			code="200";
//			map.put("code", code);
////			return map;
//			return Result.successResult();
//		}else {
//			code="500";
//			map.put("code", code);
////			return map;
//			return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
//		}

	//}
	
	//删除
	@DeleteMapping("/Specifications/del")
	@ApiOperation(value = "del",notes = "商家删除")
	@ApiImplicitParams({@ApiImplicitParam(name = "ids",value = "商品id")
	})
	public Result del(String [] ids) {
		
		ModelMap map = new ModelMap();
		
		int dels = 0;
		if (ids.length > 0) {
			dels = specificationsServer.deleteAllId(ids);
		}
		if (dels > 0) {
			map.put("data",200);
			return Result.successResult();
		}
		map.put("data",400);
		return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
	}
	
	//修改
	@PostMapping("/Specifications/up")
	@ApiOperation(value = "update",notes = "商家修改")
	@ApiImplicitParams({@ApiImplicitParam(name = "specifications",value = "商品信息")
	})
	public Result update(Specifications specifications) {
		
		ModelMap map = new ModelMap();
		specifications.setModifytime(new Timestamp(new Date().getTime()));
		
		int update = specificationsServer.update(specifications);
		if (update > 0) {
			map.put("data",200);
//			return new Result("200","","");
			return Result.successResult();
		}
		map.put("data",400);
		return Result.failureResult(ExceptionCode.Failure.ORDER_STATUS_CHANGE);
	}
	
}
