package com.dg.main.repository.orders;

import com.dg.main.Entity.orders.Orders;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,Long>, JpaSpecificationExecutor<Orders> {
    List<Orders> findByCustomerId(Long customerId, Pageable pageable);
   // List<Orders> findByCustomerIdAndPhase(Long customerId,Integer phase);
   // List<Orders> findByBusinessId(Long businessId,Pageable pageable);
    Orders findByOrderCode(String orderCode);


    @Query(value = "select * from orders where customer_id = :userId and phase = 2 or phase =3 or phase =11",nativeQuery = true)
    List<Orders> findByCustomerIdAndPhase(@Param("userId") Integer userId);

    @Query(value = "select * from orders where business_id = (:userId) and is_delete = 2 group by shop_id",nativeQuery = true)
    List<Orders> findByShopIdGroupBy(@Param("userId") Long userId);
    List<Orders> findByDirectCodeAndPhase(String code,Integer phase);
//    @Query(value = "",nativeQuery = true)
//   List<Orders> CustomerRefundList();

    @Query(value = "select * from orders where   is_delete=2 and is_refund_finished=1 and costomer_id = :userId ",nativeQuery = true)
    List<Orders>  customerRefundList(@Param("userId") Long userId);
    @Query(value = "select * from orders where  customer_id = :userId and is_delete=2 and phase=1  limit :offset,:limit",nativeQuery = true)
    List<Orders> customerUnpayList(@Param("userId")Long userId,@Param("offset")Integer offset,@Param("limit")Integer limit);
    @Query(value = "select * from orders where  customer_id = :userId and is_delete=2 and phase=2 and is_refunding=2  limit :offset,:limit",nativeQuery = true)
    List<Orders> customerUnsendList(@Param("userId")Long userId,@Param("offset")Integer offset,@Param("limit")Integer limit);
    @Query(value = "select * from orders where   customer_id = :userId  and (phase=3 or phase=2)  and is_refund_finished=2 and is_refunding=2 and is_delete=2 and is_business_sended_in_phase_three=1   limit :offset,:limit",nativeQuery = true)
    List<Orders> customerUndeliveryList(@Param("userId")Long userId,@Param("offset")Integer offset,@Param("limit")Integer limit);
    @Query(value = "select * from orders where customer_id = :userId and is_delete=2 and phase=10   ",nativeQuery = true)
    List<Orders> customerCompletedList(@Param("userId")Long userId);
    @Query(value = "select * from orders where customer_id = :userId and is_delete=2 and phase=10 and ranks=0    limit :offset,:limit",nativeQuery = true)
    List<Orders> unCommentList(@Param("userId")Long userId,@Param("offset")Integer offset,@Param("limit")Integer limit);


    @Query(value = "select *  from orders where shop_id = :shopId and is_delete=2 and is_refunding=1 limit :offset,:limit",nativeQuery = true)
    List<Orders>  businessRefundListByShopId(@Param("shopId")Long shopId,@Param("offset")Integer offset,@Param("limit")Integer limit);
    @Query(value = "select * from orders where shop_id = :shopId  and is_delete=2 and phase=1 limit :offset,:limit",nativeQuery = true)
    List<Orders> businessUnpayListByShopId(@Param("shopId")Long shopId,@Param("offset")Integer offset,@Param("limit")Integer limit);


    @Query(value = "select * from orders where shop_id = :shopId  and is_delete=2 and phase=2 and is_refund_finished=2 and is_deleted=2 and is_business_sended_in_phase_three=2 limit :offset,:limit",nativeQuery = true)
    List<Orders> businessUnsendListByShopId(@Param("shopId")Long shopId,@Param("offset")Integer offset,@Param("limit")Integer limit);
    @Query(value = "select * from orders where shop_id = :shopId  and (phase=3 or phase=2)  and is_refund_finished=2 and is_delete=2 and is_business_sended_in_phase_three=1 limit :offset,:limit",nativeQuery = true)
    List<Orders> businessUndeliveryListByShopId(@Param("shopId")Long shopId,@Param("offset")Integer offset,@Param("limit")Integer limit);

    @Query(value = "select * from orders where shop_id = (:shopId) and payed_years = (:year) and payed_month = (:month) and payed_day=(:day) and phase = (:phase) and is_delete = 2",nativeQuery = true)
    List<Orders> findByShopIdAndPayedYearsAndPayedMonthAndPayedDayAndPhase(@Param("shopId")Long shopId,@Param("year") String year,@Param("month") String month,@Param("day") String day,@Param("phase") int phase);

    @Query(value = "select * from orders where business_id = (:userId) and payed_years = (:year) and payed_month = (:month) and payed_day=(:day) and phase = (:phase) and is_delete = 2",nativeQuery = true)
    List<Orders> findByUserIdAndPayedYearsAndPayedMonthAndPayedDayAndPhase(@Param("userId")Long userId,@Param("year") String year,@Param("month") String month,@Param("day") String day,@Param("phase") int phase);

    @Query(value = "select * from orders where business_id = (:userId) and refund_time is not null and refund_years = (:year) and refund_month = (:month) and refund_day =(:day) and is_delete=2",nativeQuery = true)
    List<Orders> findByBusinessIdAndRefundYearsAndRefundMonthAndRefundDay(@Param("userId")Long userId,@Param("year") String year,@Param("month") String month,@Param("day") String day);

    @Query(value = "select * from orders where business_id = (:userId) and payed_years = (:year) and payed_month = (:month) and phase =(:phase) and is_delete =2",nativeQuery = true)
    List<Orders> findByUserIdAndPayedYearsAndPayedMonthAndPhase(@Param("userId")Long userId,@Param("year") String year,@Param("month") String month,@Param("phase") int phase);

 @Query(value = "select * from orders where business_id = (:userId) and refund_time is not null and refund_years = (:year) and refund_month = (:month)  and is_delete=2",nativeQuery = true)
    List<Orders> findByBusinessIdAndRefundYearsAndRefundMonth(@Param("userId")Long userId,@Param("year") String year,@Param("month") String month);

   @Query(value = "select * from orders where shop_id = (:shopId) and refund_time is not null and refund_years = (:year) and refund_month = (:month) and refund_day =(:day) and is_delete=2",nativeQuery = true)
   List<Orders> findByShopIdAndRefundYearsAndRefundMonthAndRefundDay(@Param("shopId")Long shopId,@Param("year") String year,@Param("month") String month,@Param("day") String day);
 @Query(value = "select * from orders where shop_id = (:shopId) and refund_time is not null and refund_years = (:year) and refund_month = (:month)and is_delete=2",nativeQuery = true)
 List<Orders> findByShopIdAndRefundYearsAndRefundMonth(@Param("shopId")Long shopId,@Param("year") String year,@Param("month") String month);

 @Query(value = "select * from orders where shop_id = (:shopId) and payed_years = (:year) and payed_month = (:month)  and phase = (:phase) and is_delete = 2",nativeQuery = true)
 List<Orders> findByShopIdAndPayedYearsAndPayedMonthAndPhase(@Param("shopId")Long shopId,@Param("year") String year,@Param("month") String month, @Param("phase") int phase);
}
