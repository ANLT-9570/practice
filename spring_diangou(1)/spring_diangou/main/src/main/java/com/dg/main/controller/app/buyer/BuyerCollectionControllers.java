package com.dg.main.controller.app.buyer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.dg.main.Entity.shop.Shops;
import com.dg.main.Entity.shop.Specifications;
import com.dg.main.Entity.users.MobileUser;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.repository.CollectionRepository;
import com.dg.main.repository.SpecificationsRepository;
import com.dg.main.repository.shop.ShopsRepository;
import com.dg.main.repository.users.MobileUserRepository;
import com.dg.main.util.Result;
import com.dg.main.vo.MyCollectionVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.*;


import com.dg.main.Entity.MyCollection;
import com.dg.main.service.CollectionServer;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


@RestController
@RequestMapping("/buyer/app/collection")
@Api(tags = "用户-收藏")
public class BuyerCollectionControllers {

	@Autowired
	CollectionRepository collectionRepository;
	@Autowired
	SpecificationsRepository specificationsRepository;
	@Autowired
	private MobileUserRepository mobileUserRepository;
	@Autowired
	private ShopsRepository shopsRepository;
	
	//我的收藏（用户商品）
	@GetMapping("/list")
	@ApiOperation(value = "list",notes = "我的收藏")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "uid",value = "用户id"),
			@ApiImplicitParam(name = "offset",value = "第几页"),
			@ApiImplicitParam(name = "limit",value = "显示数量")})
	public Result list(Long uid, @RequestParam(defaultValue = "0")Integer offset, @RequestParam(defaultValue = "15")Integer limit) {
		if(offset != 0){
			offset = offset * limit;
			limit = offset + limit;
		}
		//我的收藏
		PageRequest pageRequest = PageRequest.of(offset, limit);
		Specification<MyCollection> specification = new Specification<MyCollection>() {
			@Override
			public Predicate toPredicate(Root<MyCollection> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list = new ArrayList<>();
				list.add(cb.equal(root.get("userId").as(Long.class),uid));
				Predicate[] p = new Predicate[list.size()];
				return cb.and(list.toArray(p));
			}
		};
		Page<MyCollection> page =collectionRepository.findAll(specification, pageRequest);

		List<MyCollection> list = page.getContent();
		List<MyCollectionVo> myCollectionVos = new ArrayList<>();
		for(MyCollection myCollection:list){

			MyCollectionVo myCollectionVo = new MyCollectionVo();
			BeanUtils.copyProperties(myCollection,myCollectionVo);
			Long specificationsId = myCollection.getSpecificationsId();

			Optional<Specifications> specifications = specificationsRepository.findById(specificationsId);
			myCollectionVo.setImage(specifications.get().getImg());
			myCollectionVo.setIntroduction(specifications.get().getName());
			myCollectionVo.setPrice(specifications.get().getPrice());
			myCollectionVos.add(myCollectionVo);
		}
		return Result.successResult(myCollectionVos);
	}
	
	/**
	 * 
	 * @param uid		用户的id
	 * @param specificationsId		商品的id
	 * @param shopId	商铺的id
	 * @return
	 */
	//点击收藏
	@PostMapping("/clickOrCancelCollection")
	@ApiOperation(value = "clickOrCancelCollection",notes = "添加收藏")
	@ApiImplicitParams({@ApiImplicitParam(name = "uid",value = "用户id"),
			@ApiImplicitParam(name = "specificationsId",value = "商品id")
	,@ApiImplicitParam(name = "shopId",value = "商铺id")})
	public Result clickOrCancelCollection(Long uid, Long specificationsId, Long shopId) {
		if(uid == null ){
			return Result.failureResult(ExceptionCode.Failure.NOT_USERID);
		}
		if(shopId==null){
			return Result.failureResult(ExceptionCode.Failure.NOT_SHOPID);
		}
		if(specificationsId == null){
			return Result.failureResult(ExceptionCode.Failure.NOT_SPE);
		}
		MobileUser mobileUser = mobileUserRepository.selectMobileUse(uid);
		if(mobileUser == null){
			return Result.failureResult(ExceptionCode.Failure.NOT_USER);
		}
		Optional<Shops> optional = shopsRepository.findById(shopId);
		if(!optional.isPresent()){
			return Result.failureResult(ExceptionCode.Failure.NOT_SHOP);
		}
		Optional<Specifications> specificationsOptional = specificationsRepository.findById(specificationsId);
		if(!specificationsOptional.isPresent()){
			return Result.failureResult(ExceptionCode.Failure.NOT_SPECIFICATION);
		}
//		2表示关注，1表示没有关注
//		查询该用户是否收藏过
		MyCollection myCollection =  collectionRepository.findByUserIdAndSpecificationsIdAndShopId(uid,specificationsId,shopId);

		int is=0;
		if(myCollection == null){
			myCollection = new MyCollection();
			myCollection.setShopId(shopId);
			myCollection.setSpecificationsId(specificationsId);
			myCollection.setUserId(uid);
			myCollection.setStatus(2);
//			添加关注
			MyCollection save = collectionRepository.save(myCollection);
			if(save!=null){
				 return Result.successResult("收藏成功");
			}else{
				 return Result.successResult("收藏失败");
			}
		}else{//不为空说明要取关
			collectionRepository.delete(myCollection);
			return Result.successResult("取消收藏成功");
		}

	}

	@DeleteMapping("/delIdsAll")
	@ApiOperation(value = "delIds",notes = "批量删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id",value = "id")
	})
	public Result delIds(@RequestBody List<Long>  id) {
		if(id == null || id.size() == 0){
			return Result.failureResult(ExceptionCode.Failure.NOT_ID);
		}
		collectionRepository.deleteIds(id);
		return Result.successResult();
	}

	@DeleteMapping("/delId")
	@ApiOperation(value = "delId",notes = "单个删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id",value = "id")
	})
	public Result delId(@RequestParam("id") Long  id) {
		if(id == null ){
			return Result.failureResult(ExceptionCode.Failure.NOT_ID);
		}
		collectionRepository.deleteById(id);
		return Result.successResult();
	}

	@DeleteMapping("/delUserId")
	@ApiOperation(value = "delUserId",notes = "根据用户id删除")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userId",value = "用户id")
	})
	public Result delUserId(@RequestParam("userId") Long  userId) {
		if(userId == null ){
			return Result.failureResult(ExceptionCode.Failure.NOT_ID);
		}
		collectionRepository.deleteByUserId(userId);
		return Result.successResult();
	}

}
