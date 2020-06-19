package com.dg.main.serviceImpl;

import java.util.List;

import com.dg.main.vo.OrdersParamVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dg.main.vo.OrdersParamVo;
import com.dg.main.util.slzcResult;
import com.dg.main.dao.OrdersParamMapper;
import com.dg.main.param.orders.OrdersParam;
import com.dg.main.service.OrdersParamServer;

@Service
public class OrdersParamServiceImpl implements OrdersParamServer{

	@Autowired
	private OrdersParamMapper ordersParamMapper;
	
	@Override
	public OrdersParamVo selectOne(OrdersParamVo t) {
		
		return ordersParamMapper.selectOne(t);
	}


	@Override
	public int delete(OrdersParamVo t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(OrdersParamVo t) {
		
		return ordersParamMapper.update(t);
	}

	@Override
	public int save(OrdersParamVo t) {
		
		return ordersParamMapper.save(t);
	}


	@Override
	public List<OrdersParamVo> selectAll(OrdersParamVo t) {
		// TODO Auto-generated method stub
		return null;
	}


	//分页
	@Override
	public slzcResult selectAll(int offset,int limit,String search) {
		
		slzcResult result = new slzcResult();
		
		int count = ordersParamMapper.selectCount(search);
		
		List<OrdersParamVo> administrator = ordersParamMapper.selectPageAll( offset,limit,search);
		result.setRows(administrator);
		result.setTotal(count);
		
		return result;
	}

	@Override
	public List<OrdersParamVo> selectPageAll( int offset,int limit) {
		
		return null;
	}

	@Override
	public int deleteAllId(String [] t) {
		
		return ordersParamMapper.deleteAllId(t);
	}


	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public List<OrdersParamVo> selectByBusinessStatus(Integer uid,Integer status,Integer role,Integer offset,Integer limit) {
		return ordersParamMapper.selectByBusinessStatus(uid,status,role,offset,limit);
	}
	
	@Override
	public List<OrdersParamVo> selectByCustomerStatus(Integer uid,Integer status,Integer role,Integer offset,Integer limit) {
		return ordersParamMapper.selectByCustomerStatus(uid,status,role,offset,limit);
	}


	@Override
	public List<OrdersParam> selectByToday(Integer uid, Long status, Integer role) {
		return ordersParamMapper.selectByToday(uid, status, role);
	}


	@Override
	public slzcResult selectAll(int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}


	////查询所有的退货订单（商家）
	@Override
	public List<OrdersParamVo> HavePaidOrders(Integer uid, Integer role, Integer status, Integer offset, Integer limit,int isRefun) {
		return ordersParamMapper.HavePaidOrders( uid,  role,  status,  offset,  limit,isRefun);
	}


	@Override
	public List<OrdersParamVo> AccountPaid(Integer uid, Integer status, Integer role, Integer isfund, Integer offset,
			Integer limit) {
		return ordersParamMapper.AccountPaid( uid,  status,  role,  isfund,  offset, limit);
	}

	//商家代发货
	@Override
	public List<OrdersParamVo> BusinessAccountPaid(Integer uid, Integer status, Integer role, Integer isfund,
			Integer offset, Integer limit) {
		return ordersParamMapper.BusinessAccountPaid(uid,status,role,isfund,offset,limit);
	}


	//用户待收货
	@Override
	public List<OrdersParamVo> stayDelivery(Integer uid, Integer code, Integer isDelivery, Integer offset,
			Integer limit) {
		return ordersParamMapper.stayDelivery(uid,code,isDelivery,offset,limit);
	}


	//商家交易完成的订单
	@Override
	public List<OrdersParamVo> accomplishOrders(Integer uid, Integer code, Integer offset,Integer limit) {
		return ordersParamMapper.accomplishOrders(uid,code,offset,limit);
	}


	@Override
	public OrdersParamVo findCode(String code) {
		OrdersParamVo or = ordersParamMapper.findCode(code);
		return or;
	}


	@Override
	public int delCode(String code) {
		
		return ordersParamMapper.delCode(code);
	}

}
