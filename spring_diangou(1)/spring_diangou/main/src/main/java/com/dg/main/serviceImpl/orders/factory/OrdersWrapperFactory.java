package com.dg.main.serviceImpl.orders.factory;

import com.dg.main.Entity.settings.BuyGoodsRate;
import com.dg.main.Entity.users.UserBalance;
import com.dg.main.param.orders.OrderItemsParam;
import com.dg.main.serviceImpl.orders.ThirdPayRate;
import com.google.gson.Gson;
import com.dg.main.Entity.orders.*;
import com.dg.main.param.orders.OrdersParam;
import com.dg.main.serviceImpl.orders.CodeGenerator;
import com.dg.main.serviceImpl.setting.BuyGoodsRateService;
import com.dg.main.serviceImpl.orders.service.MoneyTransToPlatformMoneyRateService;
import com.dg.main.serviceImpl.orders.wrapper.OrdersWrapper;
import org.apache.commons.compress.utils.Lists;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

public class OrdersWrapperFactory {

	public static List<OrdersWrapper> newInstance( List<OrdersParam> ordersParam, BuyGoodsRateService buyGoodsRateService, Integer isSinglePay) {//todo
		//Gson gson=new Gson();
		List<OrdersWrapper> wrappers=Lists.newArrayList();
		String code=CodeGenerator.thirdCode.create(null);
		for(OrdersParam obj:ordersParam){

			Orders orders=new Orders();
			orders.setIsSinglePay(isSinglePay);
			orders.setDirectCode(code);
			orders.setIsDirectToPay(obj.getIsDirectToPay());
			BuyGoodsRate buyGoodsRate=buyGoodsRateService.getRate();

			orders.setCustomerId(obj.getBuyerId());

			orders.setOrderCode(CodeGenerator.orderCode.create(orders));
			orders.setThirdPlatformAction(obj.getAction());
			orders.setShopId(obj.getShopId());
			orders.setBusinessId(obj.getSellerId());
			orders.setAddressId(obj.getAddressId());
			orders.setCouponId(obj.getCouponId());
			orders.setPostMoney(obj.getPostMoney());
			orders.setCouponMoney(obj.getCutOffMoney());
			List<OrderItems> items= Lists.newArrayList();
			Long orderTotalUserPayMoney=0l;
			Long orderTotalUserPayPlatform=0l;
			Long orderTotalCompanyIncome=0l;
			Long orderTotalThirdIncome=0l;
			Long orderTotalSellerIncome=0l;
			for (OrderItemsParam i : obj.getItems()){
				OrderItems items1=new OrderItems();
				items1.setPhase(1);
				BigDecimal moneyBigDecimal=new BigDecimal(i.getMoney());
				BigDecimal platformMoneyBigDecimal=new BigDecimal(i.getMoney());
				items1.setNumber(i.getNumber());
				items1.setSpecificationId(i.getSpecificationId());
				items1.setModifyTime(new Timestamp(new Date().getTime()));
				items1.setRefundTime(null);
				items1.setSendTime(null);
				items1.setShopId(i.getShopId());
				if(i.getAddressId()!=null) {
					items1.setAddressId(new Long(i.getAddressId()));
				}
				items1.setImg(i.getImage());
				items1.setPostMoney(i.getPostMoney());
				items1.setSizeId(i.getSizeId());
				items1.setMark(i.getMark());
				items1.setUserId(obj.getBuyerId());
				items1.setItemCode("");

			//	items1.setMoney(moneyBigDecimal.multiply(new BigDecimal(100)).longValue());
				//items1.setPlatformMoney(platformMoneyBigDecimal.longValue()*10);
				items1.setMoney(i.getMoney());
				items1.setPayPlatformMoney(i.getMoney()/10);
				items1.setTotalMoney(items1.getMoney()*items1.getNumber());
				items1.setTotalPlatformMoney(items1.getTotalMoney()/10);
				BigDecimal rate=new BigDecimal(buyGoodsRate.getRate()).add(new BigDecimal(100)).divide(new BigDecimal(1000));

				items1.setRatedPlatformMoney(new BigDecimal(items1.getTotalMoney()).multiply(rate).longValue());
				items1.setPayPlatformMoney(items1.getRatedPlatformMoney());
			//	orderTotalSellerIncome=items1.getTotalMoney();
				if(obj.getPostMoney()!=0) {

					items1.setPostMoney(obj.getPostMoney());
					items1.setIsPostMoneyFree(2);
					if(obj.getAction()==2) {
						items1.setPayPlatformMoney(items1.getRatedPlatformMoney() + items1.getPostMoney() / 10);
					}
					items1.setPayMoney(items1.getTotalMoney()+items1.getPostMoney());
				}else {
					items1.setPayPlatformMoney(items1.getRatedPlatformMoney());
					items1.setIsPostMoneyFree(1);
				}

				if(i.getCouponId()!=null){
					items1.setCouponId(i.getCouponId());
					items1.setDiscountMoney(i.getCouponPrice());
					items1.setDiscountPlatformMoney(i.getCouponPrice() /10);
					if(obj.getAction()==2) {
						items1.setPayPlatformMoney(items1.getPayPlatformMoney() - i.getCouponPrice() / 10);
					}
					items1.setPayMoney(items1.getPayMoney()-i.getCouponPrice());
				}
				if(obj.getAction()==3||obj.getAction()==4){
					Long thirdIncome=new BigDecimal(items1.getTotalMoney()).multiply(new BigDecimal(ThirdPayRate.ZHIFUBAO_RATE )).divide(new BigDecimal(1000)).longValue();
					Long platformIncome =new BigDecimal(items1.getTotalMoney()).multiply(new BigDecimal(99)).divide(new BigDecimal(100),0, RoundingMode.HALF_UP).longValue();

					items1.setThirdIncome(thirdIncome);
					items1.setCompanyIncome(items1.getTotalMoney()-platformIncome);
					orderTotalCompanyIncome=orderTotalCompanyIncome+items1.getCompanyIncome();
					orderTotalThirdIncome=orderTotalThirdIncome+items1.getThirdIncome();
					orderTotalSellerIncome=orderTotalSellerIncome+(items1.getTotalMoney()-(thirdIncome+platformIncome));

					// orders.setPayMoney(platformIncome);
				}else{
					orderTotalSellerIncome=orderTotalSellerIncome+items1.getTotalMoney();
				}
				items.add(items1);
				orderTotalUserPayPlatform=orderTotalUserPayPlatform+items1.getRatedPlatformMoney();
				orderTotalUserPayMoney=orderTotalUserPayMoney+items1.getTotalMoney();

			}
			if(orders.getCouponId()!=null){
				orderTotalUserPayPlatform=orderTotalUserPayPlatform-orders.getCouponMoney()/10;
				orderTotalUserPayMoney=orderTotalUserPayMoney-orders.getCouponMoney();
			}
			if(orders.getPostMoney()!=0){
				orderTotalUserPayPlatform=orderTotalUserPayPlatform+orders.getPostMoney()/10;
				orderTotalUserPayMoney=orderTotalUserPayMoney+orders.getPostMoney();
			}
			orders.setTotalUserPayPlatformMoney(orderTotalUserPayPlatform);
			orders.setTotalMoney(orderTotalUserPayMoney);
			orders.setTotalCompanyIncome(orderTotalCompanyIncome);
			orders.setTotalThirdIncome(orderTotalThirdIncome);

			OrdersWrapper wrapper=new OrdersWrapper(orders,items );
			wrappers.add(wrapper);
		}

		return wrappers;
	}
	public static List<OrdersWrapper> newInstance(Orders orders,Integer action,List<OrderItems> orderItems,String mark){
		List<OrdersWrapper> wrappers=Lists.newArrayList();

		orders.setThirdPlatformAction(action);
		orders.setMark(mark);
		for(OrderItems item:orderItems){
			item.setThirdPlatformAction(action);
		}

		OrdersWrapper wrapper=new OrdersWrapper(orders,orderItems);
		wrappers.add(wrapper);
		return wrappers;
	}
	public static OrdersWrapper newInstance(Orders orders,List<OrderItems> orderItems,String mark){
		Long total=0l;
		Long totalPlatformMoney=0l;
		orders.setOrderCode(CodeGenerator.orderCode.create(orders));
		orders.setPhase(1);
		orders.setMark(mark);
		//orders.setCustomerId();

		for(OrderItems item:orderItems){
			total=total+(item.getNumber()*item.getMoney());
			orders.setCustomerId(item.getUserId());
		}
		totalPlatformMoney=total/10;
		orders.setTotalPlatformMoney(totalPlatformMoney);
		orders.setTotalMoney(total);

		return new OrdersWrapper(orders,orderItems);
	}
//	    public static OrdersWrapper newInstance(UserBalance userBalance, Specifications specifications, ShoppingCart shoppingCart, BuyGoodsRateService buyGoodsRateService
//				, MoneyTransToPlatformMoneyRateService moneyTransToPlatformMoneyRateService,Long addressId,Long couponId,Long couponPrice) {//todo
//	        Gson gson=new Gson();
//	        Orders orders=new Orders();
//			BuyGoodsRate buyGoodsRate=buyGoodsRateService.getRate();
//			MoneyTransToPlatformMoneyRate moneyTransToPlatformMoneyRate=moneyTransToPlatformMoneyRateService.getRate();
//	        orders.setBusinessId(shoppingCart.getCustomerId());
//	        orders.setCustomerId(shoppingCart.getMobileUseId());
//	       // orders.setIsRefunding(2);
//	        orders.setOrderCode(CodeGenerator.orderStreamCode.create(orders));
//
////	        orders.setPhase(1);
////	        orders.setNumber(Long.valueOf(shoppingCart.getShopNumber()));
////	        BigDecimal money=new BigDecimal(shoppingCart.getShopPrice()).multiply(new BigDecimal(100));
////	        orders.setMoney(money.longValue());
////	        orders.setPlatformMoney(money.longValue()/10);
////	        orders.setThirdPlatformAction(1);
////	        orders.setSpecificationsId(shoppingCart.getSpecificationsId());
////	        orders.setModifyTime(new Timestamp(new Date().getTime()));
////	        orders.setRefundTime(null);
////	        orders.setSendTime(null);
////	        orders.setShopCarId(shoppingCart.getShoppingCartId());
////	        orders.setImg(shoppingCart.getShopImg());
////
////
////			if(couponId!=null) {
////				orders.setCouponId(couponId);
////				orders.setDiscountMoney(couponPrice);
////				orders.setDiscountPlatformMoney(couponPrice / 10);
////
////
////			}
////
////
////			orders.setAddressId(addressId);
////	        Long total = orders.getMoney()*orders.getNumber() ;
//
//
//			System.out.println(new BigDecimal(buyGoodsRate.getRate()).add(new BigDecimal(100)).divide(new BigDecimal(100)));
//
////			System.out.println(new BigDecimal(total).multiply(new BigDecimal(1.3)));
////			BigDecimal rate=new BigDecimal(buyGoodsRate.getRate()).add(new BigDecimal(100)).divide(new BigDecimal(100));
////			System.out.println(new BigDecimal(total).multiply(rate).longValue());
//
////			orders.setTotalPlatformMoney(new BigDecimal(total).multiply(rate).longValue());
////			if(shoppingCart.getPostage()!=null) {
////				orders.setTotal(total+shoppingCart.getPostage());
////				orders.setPostMoney(shoppingCart.getPostage());
////				orders.setIsPostMoneyFree(2);
////				orders.setTotalPlatformMoney(orders.getTotalPlatformMoney()+(shoppingCart.getPostage()));
////			}else {
////				orders.setTotal(total);
////				orders.setIsPostMoneyFree(1);
////			}
////			orders.setTotalPlatformMoney(orders.getTotalPlatformMoney()/10);
////			orders.setLeftPlatformMoney(total);
////			if(couponId!=null){
////				orders.setTotal(orders.getTotal()-couponPrice);
////				orders.setTotalPlatformMoney(orders.getTotalPlatformMoney()-(couponPrice/10));
////			}
//
//			OrderRefundLog orderRefundLog=new OrderRefundLog();
//	        orderRefundLog.setBusinessId(shoppingCart.getCustomerId());
//	        orderRefundLog.setCustomerId(shoppingCart.getMobileUseId());
//	      //  orderRefundLog.setCurrentStatus(orders.getPhase());
//	        orderRefundLog.setJsonStateOfModifiedOrders(gson.toJson(orders));
//	        orderRefundLog.setJsonStateOfOrders(gson.toJson(orders));
//	        orderRefundLog.setIsRefund(2);
//	        orderRefundLog.setOrderCode(orders.getOrderCode());
//	    //    orderRefundLog.setPlatformMoney(orders.getPlatformMoney());
//	     //   orderRefundLog.setMoney(orders.getMoney());
//	        return new OrdersWrapper(orders,orderRefundLog,null,userBalance,null,null,specifications);
//	    }

//	public static OrdersWrapper newInstance(Orders orders, UserBalance userBalance,
//											Specifications specifications, Integer action, String mark,  PlatformRateService platformRateService) {//todo

	//	orders.setThirdPlatformAction(action);

//	        orders.setModifyTime(new Timestamp(new Date().getTime()));
//	        orders.setMark(mark);
//	        if(action==3||action==4) {
//	            Long money = orders.getMoney();
//	            Long number = orders.getNumber();
//	            Long total = money * number;
//	            if(orders.getIsPostMoneyFree()!=1){
//	            	total=total+orders.getPostMoney();
//				}
//
//				Long thirdIncome=new BigDecimal(total).multiply(new BigDecimal(ThirdPayRate.ZHIFUBAO_RATE )).divide(new BigDecimal(1000)).longValue();
//	            Long platformIncome =new BigDecimal(total).multiply(new BigDecimal(99)).divide(new BigDecimal(100),0,RoundingMode.HALF_UP).longValue();
//
//	            orders.setThirdIncome(thirdIncome);
//	            orders.setPlatformIncome(total-platformIncome);
//	            orders.setPayMoney(platformIncome);
//
//	        }


		// orders.setPlatformMoney(platformMoney);
		//return new OrdersWrapper(orders,null,null,userBalance,null,null,specifications,null);
///	}
}
