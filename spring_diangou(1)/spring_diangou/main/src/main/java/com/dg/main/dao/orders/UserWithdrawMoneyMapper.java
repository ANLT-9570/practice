package com.dg.main.dao.orders;

import com.dg.main.Entity.orders.UserDepositPlatformMoney;
import com.dg.main.Entity.orders.UserWithdrawMoney;
import com.dg.main.base.BaseMapper;
import com.dg.main.trans_mapper_obj.UserWithdrawStream;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserWithdrawMoneyMapper   extends BaseMapper<UserWithdrawMoney>, BaseDao<UserWithdrawMoney> {
    UserWithdrawMoney findByCode(@Param("code") String code);
    List<UserWithdrawMoney> findUnpayedList();
    int countByUnpayedList();
    List<UserWithdrawStream> userWithdrawStream(@Param("userId") Integer userId);
    Long countUserWithdraw(@Param("userId") Integer userId);
}
