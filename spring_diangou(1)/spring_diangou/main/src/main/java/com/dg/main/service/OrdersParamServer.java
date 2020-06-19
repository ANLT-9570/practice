package com.dg.main.service;

import java.util.List;



import com.dg.main.util.slzcResult;
import com.dg.main.base.BaseMapper;
import com.dg.main.param.orders.OrdersParam;
import com.dg.main.vo.OrdersParamVo;

public interface OrdersParamServer extends BaseMapper<OrdersParamVo>{

	//根据状态查询
	/**
	 * 用户
	 * @param uid  用户id
	 * @param status 状态
	 * @param role	角色 用户 or 商家
	 * @return
	 */
	List<OrdersParamVo> selectByCustomerStatus(Integer uid,Integer status,Integer role,Integer offset,Integer limit);
	
	//根据状态查询
	/**
	 * 商家
	 * @param uid  用户id
	 * @param status 状态
	 * @param role	角色 用户 or 商家
	 * @return
	 */
	List<OrdersParamVo> selectByBusinessStatus(Integer uid,Integer status,Integer role,Integer offset,Integer limit);
		
	//今天的第订单
	List<OrdersParam> selectByToday(Integer uid,Long status,Integer role);
	
	 slzcResult selectAll(int offset, int limit,String search);

	 /**
	  * 查询所有的退货订单（商家）
	  * @param uid用户id
	  * @param role 角色 用户 or 商家
	  * @param status 支付状态
	  * @param offset
	  * @param limit
	  * @return
	  */
	List<OrdersParamVo> HavePaidOrders(Integer uid, Integer role, Integer status, Integer offset, Integer limit,int isRefun);

	/**
	 * 已付款
	 * @param uid	用户
	 * @param role		角色
	 * @param status 	状态
	 * @param isfund	是否退款
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<OrdersParamVo> AccountPaid(Integer uid, Integer status, Integer role, Integer isfund, Integer offset,
			Integer limit);
	
	List<OrdersParamVo> BusinessAccountPaid(Integer uid, Integer status, Integer role, Integer isfund, Integer offset,
			Integer limit);
	/**
	 * 用户待收货
	 * @param uid
	 * @param code
	 * @param isDelivery
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<OrdersParamVo> stayDelivery(Integer uid, Integer code, Integer isDelivery, Integer offset, Integer limit);

	//商家交易已完成的订单
	List<OrdersParamVo> accomplishOrders(Integer uid, Integer code, Integer offset,Integer limit);

	OrdersParamVo findCode(String code);

	int delCode(String code);
}
