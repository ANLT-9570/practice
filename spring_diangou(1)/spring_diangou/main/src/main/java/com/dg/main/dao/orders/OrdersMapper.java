package com.dg.main.dao.orders;

import com.dg.main.Entity.orders.Orders;
import com.dg.main.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrdersMapper   extends BaseMapper<Orders>, BaseDao<Orders> {
//    Orders findByCode(@Param("code")String code);
//    List<Orders> CustomerRefundList(@Param("id")Integer userId);
//    List<Orders> BusinessRefundList(@Param("id")Integer userId);
//
//    List<Orders> customerUnpayList(@Param("id")Integer userId);
//    List<Orders> BusinessUnpayList(@Param("id")Integer userId);
//
//    List<Orders> CustomerPayedList(@Param("id")Integer userId);
//    List<Orders> BusinessPayedList(@Param("id")Integer userId);
//
//    List<Orders> CustomerSendingList(@Param("id")Integer userId);
//    List<Orders> BusinessSendingList(@Param("id")Integer userId);
//
//    List<Orders> CustomerCompletedList(@Param("id")Integer userId);
//    List<Orders> BusinessCompletedList(@Param("id")Integer userId);
//	List<Orders> selectByCodeAll(@Param("code") String code);
//	List<Orders> selectByPhaseAll(@Param("phase")Integer phase,@Param("uid")Integer uid);
//	List<Orders> selectByUidDaifa(@Param("uid")Integer uid);
//    List<Orders> listByUniteCode(@Param("uniteCode")String uniteCode);
//    void fakeDeletedById(@Param("id")Long id);
//    void fakeDeletedByCode(@Param("code")String code);
//
//    List<Orders> unsettleOrders();
//    List<Orders> findByUserIdAndSpecificationId(@Param("userId")Integer userId,@Param("specificationId")Long specificationId);
}
