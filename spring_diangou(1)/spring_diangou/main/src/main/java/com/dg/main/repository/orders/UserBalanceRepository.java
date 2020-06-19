package com.dg.main.repository.orders;

import com.dg.main.Entity.users.UserBalance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UserBalanceRepository extends JpaRepository<UserBalance,Long> , JpaSpecificationExecutor<UserBalance> {
    @Query(value = "update user_balance  set operation_times=operation_times+1,money=money+ :money,modify_time=now() where id= :id and operation_times= :operationTimes",nativeQuery = true)
    int increaseVersionUpdateMoney(Long operationTimes,Long money,Long id);
    @Modifying
    @Query(value = "update user_balance  set operation_times=operation_times+1,platformMoney=platformMoney+ :platformMoney,modify_time=now() where id= :id and operation_times= :operationTimes",nativeQuery = true)
    int increaseVersionUpdatePlatformMoney(@Param("operationTimes")Long operationTimes, @Param("platformMoney")Long platformMoney, @Param("id")Long id);
    @Modifying
    @Query(value = "update user_balance set operation_times=operation_times+1,money=money- :money,modify_time=now() where id= :id and operation_times= :operationTimes",nativeQuery = true)
    int decreaseVersionUpdateMoney(@Param("operationTimes")Long operationTimes,@Param("money")Long money,@Param("id")Long id);
    @Modifying
    @Query(value = "update user_balance set operation_times=operation_times+1,platformMoney=platformMoney- :platformMoney,modify_time=now() where id= :id and operation_times= :operationTimes",nativeQuery = true)
    int decreaseVersionUpdatePlatformMoney(@Param("operationTimes")Long operationTimes,@Param("platformMoney")Long platformMoney,@Param("id")Long id);
    @Modifying
    @Query(value = " update user_balance set operation_times=operation_times+1,money=money- :money,platform_money=platform_money+ :platformMoney,modify_time=now()  where id= :id and operation_times= :operationTimes",nativeQuery = true)
    int transToPlatform(@Param("operationTimes")Long operationTimes,@Param("id")Long id,@Param("platformMoney")Long platformMoney,@Param("money")Long money);

//  UserBalance selectOneWithNoLock(@Param("id")Long id);
//    UserBalance findByUserIdWithNoLock(@Param("userId")Integer userId);
    UserBalance findByUserId(Long userId);
}
