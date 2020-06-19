package com.dg.main.dao;


import java.util.List;

import com.dg.main.param.orders.OrdersParam;
import com.dg.main.vo.OrdersParamVo;
import org.apache.ibatis.annotations.Param;


import com.dg.main.base.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersParamMapper extends BaseMapper<OrdersParamVo>{

	List<OrdersParamVo> selectPageAll(@Param("offset") int offset,@Param("limit")int limit,@Param("search")String search);
	
	int deleteAllId(@Param("t")String[] t) ;

	 int selectCount(@Param("search")String search);

	 int update(@Param("t")OrdersParamVo t) ;

	 //根据状态查询
	 /**
	  * 用户
	  * @param uid用户id
	  * @param status状态
	  * @param role角色
	  * @return
	  */
	List<OrdersParamVo> selectByCustomerStatus(@Param("uid")Integer uid,@Param("status")Integer status,@Param("role")Integer role,@Param("offset")Integer offset,@Param("limit")Integer limit);
	
	//根据状态查询
	 /**
	  * 商家
	  * @param uid用户id
	  * @param status状态
	  * @param role角色
	  * @return
	  */
	List<OrdersParamVo> selectByBusinessStatus(@Param("uid")Integer uid,@Param("status")Integer status,@Param("role")Integer role,@Param("offset")Integer offset,@Param("limit")Integer limit);
		
		
	//今天的第订单
	List<OrdersParam> selectByToday(@Param("uid")Integer uid, @Param("status")Long status, @Param("role")Integer role);

	/**
	  * 查询所有的退货订单（商家）
	  * @param uid用户id
	  * @param role 角色 用户 or 商家
	  * @param status 支付状态
	  * @param offset
	  * @param limit
	  * @return
	  */
	List<OrdersParamVo> HavePaidOrders(@Param("uid")Integer uid,@Param("role") Integer role,@Param("status") Integer status,@Param("offset") Integer offset,@Param("limit") Integer limit,@Param("isRefun")int isRefun);

	/**
	 * 已付款
	 * @param uid
	 * @param status
	 * @param role
	 * @param isfund
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<OrdersParamVo> AccountPaid(@Param("uid")Integer uid,@Param("status") Integer status,@Param("role") Integer role,@Param("isrefund") Integer isrefund,
			@Param("offset") Integer offset,@Param("limit") Integer limit);

	List<OrdersParamVo> BusinessAccountPaid(@Param("uid")Integer uid,@Param("status") Integer status,@Param("role") Integer role,@Param("isrefund") Integer isrefund,
			@Param("offset") Integer offset,@Param("limit") Integer limit);

	//用户待收货
	List<OrdersParamVo> stayDelivery(@Param("uid")Integer uid, @Param("code")Integer code, @Param("isDelivery")Integer isDelivery, @Param("offset")Integer offset,@Param("limit") Integer limit);

	//商家交易已完成的订单
	List<OrdersParamVo> accomplishOrders(@Param("uid")Integer uid,@Param("code") Integer code, @Param("offset")Integer offset,@Param("limit") Integer limit);

	OrdersParamVo findCode(String code);

	int delCode(String code);
	
//	int add(Test t);
}