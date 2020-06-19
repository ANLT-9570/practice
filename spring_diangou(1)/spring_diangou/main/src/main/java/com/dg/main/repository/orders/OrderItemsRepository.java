package com.dg.main.repository.orders;

import com.dg.main.Entity.orders.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItems,Long>, JpaSpecificationExecutor<OrderItems> {
    List<OrderItems> findByOrdersIdAndIsDeleted(Long ordersId,Integer isDeleted);
    List<OrderItems> findByUserIdAndIsDeleted(Long userId,Integer isDeleted);
    List<OrderItems> findByShopIdAndIsDeleted(Long shopId,Integer isDeleted);
    List<OrderItems> findByGroupByFieldAndIsDeleted(String code,Integer isDeleted);
    OrderItems findByItemCode(String code);
    @Query(value = "select * from order_items where  orders_id in (select id from orders where customer_id = :userId) and is_deleted=2 and is_refund_finished=1",nativeQuery = true)
    List<OrderItems>  customerRefundList(@Param("userId") Long userId);
    @Query(value = "select * from order_items where  user_id = :userId and is_deleted=2 and phase=1 group by group_by_field limit :offset,:limit",nativeQuery = true)
    List<OrderItems> customerUnpayList(@Param("userId")Long userId,@Param("offset")Integer offset,@Param("limit")Integer limit);
    @Query(value = "select * from order_items where  user_id = :userId and is_deleted=2 and phase=2 and is_refunding=2  group by group_by_field limit :offset,:limit",nativeQuery = true)
    List<OrderItems> customerUnsendList(@Param("userId")Long userId,@Param("offset")Integer offset,@Param("limit")Integer limit);
    @Query(value = "select * from order_items where   user_id = :userId  and (phase=3 or phase=2)  and is_refund_finished=2 and is_refunding=2 and is_deleted=2 and is_business_sended_in_phase_three=1  group by group_by_field limit :offset,:limit",nativeQuery = true)
    List<OrderItems> customerUndeliveryList(@Param("userId")Long userId,@Param("offset")Integer offset,@Param("limit")Integer limit);
    @Query(value = "select * from order_items where user_id = :userId and is_deleted=2 and phase=10   group by group_by_field",nativeQuery = true)
    List<OrderItems> customerCompletedList(@Param("userId")Long userId);
    @Query(value = "select * from order_items where user_id = :userId and is_deleted=2 and phase=10 and ranks=0   group by group_by_field limit :offset,:limit",nativeQuery = true)
    List<OrderItems> unCommentList(@Param("userId")Long userId,@Param("offset")Integer offset,@Param("limit")Integer limit);


    @Query(value = "select * from order_items where  orders_id in (select id from orders where business_id = :userId) and is_deleted=2 and is_refund_finished=1",nativeQuery = true)
    List<OrderItems>  businessRefundListByUserId(@Param("userId")Long userId);
    @Query(value = "select * from order_items where  orders_id in (select id from orders where business_id = :userId) and is_deleted=2 and phase=1",nativeQuery = true)
    List<OrderItems> businessUnpayListByUserId(@Param("userId")Long userId);
    @Query(value = "select * from order_items where  orders_id in (select id from orders where business_id = :userId) and is_deleted=2 and phase=2",nativeQuery = true)
    List<OrderItems> businessPayedListByUserId(@Param("userId")Long userId);
    @Query(value = "select * from order_items where  orders_id in (select id from orders where business_id = :userId) and  and (phase=3 or phase=2)  and is_refund_finished=2 and is_deleted=2",nativeQuery = true)
    List<OrderItems> businessSendingListByUserId(@Param("userId")Long userId);
    @Query(value = "select * from order_items where  orders_id in (select id from orders where business_id = :userId) and is_deleted=2 and phase=10",nativeQuery = true)
    List<OrderItems> businessCompletedListByUserId(@Param("userId")Long userId);

    @Query(value = "select * from order_items where  orders_id in (select id from orders where shop_id = :shopId) and is_deleted=2 and is_refunding=1 limit :offset,:limit",nativeQuery = true)
    List<OrderItems>  businessRefundListByShopId(@Param("shopId")Long shopId,@Param("offset")Integer offset,@Param("limit")Integer limit);
    @Query(value = "select * from order_items where  orders_id in (select id from orders where shop_id = :shopId) and is_deleted=2 and phase=1 limit :offset,:limit",nativeQuery = true)
    List<OrderItems> businessUnpayListByShopId(@Param("shopId")Long shopId,@Param("offset")Integer offset,@Param("limit")Integer limit);


    @Query(value = "select * from order_items where  orders_id in (select id from orders where shop_id = :shopId) and is_deleted=2 and phase=2 and is_refund_finished=2 and is_deleted=2 and is_business_sended_in_phase_three=2 limit :offset,:limit",nativeQuery = true)
    List<OrderItems> businessUnsendListByShopId(@Param("shopId")Long shopId,@Param("offset")Integer offset,@Param("limit")Integer limit);
    @Query(value = "select * from order_items where  orders_id in (select id from orders where shop_id = :shopId) and (phase=3 or phase=2)  and is_refund_finished=2 and is_deleted=2 and is_business_sended_in_phase_three=1 limit :offset,:limit",nativeQuery = true)
    List<OrderItems> businessUndeliveryListByShopId(@Param("shopId")Long shopId,@Param("offset")Integer offset,@Param("limit")Integer limit);

    @Query(value = "select * from order_items where  orders_id in (select id from orders where business_id = :userId) and is_deleted=2 and phase=2 and is_refund_finished=2 and is_deleted=2",nativeQuery = true)
    List<OrderItems> businessUnsendListByUserId(@Param("userId")Long userId);
    @Query(value = "select * from order_items where  orders_id in (select id from orders where business_id = :userId) and (phase=3 or phase=2)  and is_refund_finished=2 and is_deleted=2 ",nativeQuery = true)
    List<OrderItems> businessUndeliveryListByUserId(@Param("userId")Long userId);


    @Query(value = "select * from order_items where shop_id = :shopId and phase =10 and is_deleted=2",nativeQuery = true)
    List<OrderItems> findByShopIdAndPhaseAndIsDeleted(@Param("shopId") Long shopId);

    @Query(value = "select * from order_items where user_id = :userId and phase = 2 or phase =3 or phase =11",nativeQuery = true)
    List<OrderItems> findByUserIdAndPhase(@Param("userId")Integer userId);

    @Query(nativeQuery = true,value = "select count(*) as total from order_items where user_id = :userId and phase = :phase and is_deleted = 2 and is_refunding=2")
    Long findByUserIdAndPhaseAndCount(@Param("userId")Long userId,@Param("phase") long phase);

    @Query(nativeQuery = true,value = "select count(*) as total from order_items where shop_id = :shopId and phase = :phase and is_deleted = 2")
    Long findByShopIdAndPhaseAndCount(@Param("shopId")Long shopId,@Param("phase") long phase);

    @Query(nativeQuery = true,value = "select count(*) as total from order_items where user_id = :userId and phase = 10 and is_deleted = 2 and ranks = 0")
    Long findByUserIdAndPhaseAndRanksAndCount(@Param("userId")Long userId);

    List<OrderItems> findBySpecificationId(Long specificationId);

    @Query(nativeQuery = true,value = "select * from order_items where specification_id = :specificationId and phase = :phase")
    List<OrderItems> findBySpecificationIdAndPhase(@Param("specificationId")Long specificationId,@Param("phase")Long phase);

    @Query(nativeQuery = true,value = "select * from order_items where phase = 10 group by specification_id limit :offset,:limit")
    List<OrderItems> findByPhaseAndGroup(@Param("offset")Integer offset, @Param("limit")Integer limit);

    @Query(nativeQuery = true,value = "select count(*) as total from order_items where user_id = (:userId) and phase = (:phase) and is_deleted = 2 and create_time >= curdate()")
    Long findByUserIdAndPhaseAndCountAndCreateTime(@Param("userId")Long userId,@Param("phase") long phase);

    @Query(nativeQuery = true,value = "select sum(money) as total from order_items where user_id = (:userId) and phase = (:phase) and is_deleted = 2 and create_time >= curdate()")
    Long findByUserIdAndPhaseAndSumAndCreateTime(@Param("userId")Long userId,@Param("phase") long phase);

    @Query(nativeQuery = true,value = "select count(*) as total from order_items where shop_id = (:shop_id) and phase = (:phase) and is_deleted = 2 and create_time >= curdate()")
    Long findByShopIdAndPhaseAndCountAndCreateTime(@Param("shop_id")Long shop_id,@Param("phase") long phase);

    @Query(nativeQuery = true,value = "select sum(money) as total from order_items where shop_id = (:shop_id) and phase = (:phase) and is_deleted = 2 and create_time >= curdate()")
    Long findByShopIdAndPhaseAndSumAndCreateTime(@Param("shop_id")Long shop_id,@Param("phase") long phase);

    @Query(nativeQuery = true,value = "select count(*) from order_items where refund_time is not null and refund_time >= curdate()")
    Long findByRefundTime();

    @Query(nativeQuery = true,value = "select count(*) from order_items where user_id = (:userId) and refund_time is not null and refund_time >= curdate()")
    Long findByUserIdRefundTime(@Param("userId")Long userId);

    @Query(nativeQuery = true,value = "select count(*) from order_items where shop_id = (:shopId) and refund_time is not null and refund_time >= curdate()")
    Long findByShopIdRefundTime(@Param("shopId")Long shopId);

    @Modifying
    @Query(nativeQuery = true,value = "update order_items set is_refund_finished = (:code) where item_code = (:order)")
    void updateIsRefundFinished(@Param("order")String order,@Param("code")Integer code);

    @Query(nativeQuery = true,value = "select * from order_items where user_id = (:userId) and is_deleted = 2 and phase = (:phase) group by user_id")
    List<OrderItems> findByUserIdAndPhaseAndIsDeletedAndGroupBy(@Param("userId")Long userId,@Param("phase")Integer phase);

    @Query(nativeQuery = true,value = "select * from order_items where shop_id = (:userId) and is_deleted = 2 and phase = (:phase) group by shop_id")
    List<OrderItems> findByShopIdAndPhaseAndIsDeletedAndGroupBy(@Param("userId")Long userId,@Param("phase")Integer phase);

    @Query(nativeQuery = true,value = "select * from order_items where user_id = (:userId) and shop_id = (:shopId) and is_deleted=2 and phase = (:phase)")
    List<OrderItems> findByUserIdAndShopIdAndPhase(@Param("userId")Long userId,@Param("shopId") Long shopId,@Param("phase") Integer phase);

    @Query(nativeQuery = true,value = "select * from order_items where user_id = (:userId) and shop_id = (:shopId) and is_deleted=2 and phase = (:phase)")
    List<OrderItems> findByShopIdAndUserIdAndPhase(@Param("userId")Long userId,@Param("shopId") Long shopId,@Param("phase") Integer phase);

    List<OrderItems> findByPhaseAndUserIdAndIsDeleted(Integer phase,Long userId,Integer code);

    List<OrderItems> findByPhaseAndShopIdAndIsDeleted(Integer phase, Long shopId,Integer code);

    @Query(nativeQuery = true,value = "select count(*) from order_items where user_id = (:userId) and is_deleted = 2 and phase = (:phase) and create_time like (:dateTime)")
    Long findByUserIdAndPhaseAndCreateTimeAndCount(@Param("userId")Long userId, @Param("dateTime")String dateTime,@Param("phase")Long phase);

    @Query(nativeQuery = true,value = "select count(*) from order_items where shop_id = (:shopId) and is_deleted = 2 and phase = (:phase) and create_time like (:dateTime)")
    Long findByShopIdAndPhaseAndCreateTimeAndCount(@Param("shopId")Long shopId, @Param("dateTime")String dateTime,@Param("phase")Long phase);

    @Query(nativeQuery = true,value = "select sum(money) from order_items where user_id = (:userId) and is_deleted = 2 and phase = (:phase) and create_time like (:dateTime)")
    Long findByUserIdAndPhaseAndCreateTimeAndSum(@Param("userId")Long userId,@Param("dateTime") String dateTime,@Param("phase") long phase);

    @Query(nativeQuery = true,value = "select sum(money) from order_items where shop_id = (:shopId) and is_deleted = 2 and phase = (:phase) and create_time like (:dateTime)")
    Long findByShopIdAndPhaseAndCreateTimeAndSum(@Param("shopId")Long shopId,@Param("dateTime") String dateTime,@Param("phase") long phase);

    @Query(nativeQuery = true,value = "select count(*) from order_items where user_id = (:userId) and refund_time is not null and refund_time like (:dateTime)")
    Long findByUserIdRefundTimeSomeDay(@Param("userId")Long userId,@Param("dateTime")String dateTime);

    @Query(nativeQuery = true,value = "select count(*) from order_items where shop_id = (:shopId) and refund_time is not null and refund_time like (:dateTime)")
    Long findByShopIdRefundTimeSomeDay(@Param("shopId")Long shopId,@Param("dateTime")String dateTime);

    @Query(value = "select * from order_items where shop_id in(select id from shops where user_id = (:userId)) and is_deleted = 2 and phase = 2 and create_time like (:dataTime)",nativeQuery = true)
    List<OrderItems> findByShopIdsAndPhase(@Param("userId")Long userId,@Param("dataTime") String dataTime);

    @Query(value = "select * from order_items where shop_id = :ShopId and phase =2 and is_deleted=2 and create_time like (:dateTime) ",nativeQuery = true)
    List<OrderItems> findByShopIdAndPhaseAndIsDeleted(@Param("ShopId")Long ShopId,@Param("dateTime") String dateTime);

    @Query(value = "select * from order_items where user_id = (:userId) and is_deleted = 2 and phase = 2 and create_time like (:dataTime)",nativeQuery = true)
    List<OrderItems> findByUserIdAndPhaseAndIsDeletedAndCreateTime(@Param("userId")Long userId, @Param("dataTime")String dataTime);

    @Query(value = "select * from order_items where user_id = (:userId) and is_deleted = 2 and phase = 2 or phase = 3 or phase = 10 ",nativeQuery = true)
    List<OrderItems> findByUserIdAccumulative(@Param("userId")Long userId);

    @Query(value = "select * from order_items where user_id = (:userId) and is_deleted = 2 and phase = 2 or phase = 3 or phase = 10 and create_time like (:dataTime)",nativeQuery = true)
    List<OrderItems> findByUserIdAccumulativeAndTime(@Param("userId")Long userId, @Param("dataTime")String dataTime);

    @Query(value = "select * from order_items where user_id = (:userId) and refund_time is not null and refund_time >= curdate()",nativeQuery = true)
    List<OrderItems> findByUserIdRefundAndTime(@Param("userId")Long userId);

    @Query(nativeQuery = true,value = "select * from order_items where shop_id = (:shopId) and refund_time is not null and refund_time like (:dataTime)")
    List<OrderItems> findByShopIdRefundTime(@Param("shopId")Long shopId, @Param("dataTime")String dataTime);

    @Query(nativeQuery = true,value = "SELECT create_time from order_items where user_id =(:userId) and is_deleted =2 and phase = 2 GROUP BY DATE_FORMAT(create_time,'%Y-%m-%d') order by create_time asc")
    List<String> findByUserIdAndPhaseAndIsDeletedAndGroupByCreateTime(@Param("userId")Long userId);

    @Query(nativeQuery = true,value = "select * from order_items where user_id = (:userId) and is_deleted =2 and phase = 2 and create_time < (:dateTime)")
    List<OrderItems> findByUserIdAndPhaseAndIsDeletedAndCreateTimeAgo(@Param("userId")Long userId,@Param("dateTime") String dateTime);
}
