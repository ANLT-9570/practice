package com.dg.main.dao.orders;

import com.dg.main.Entity.orders.UserDepositPlatformMoney;
import com.dg.main.Entity.orders.UserWithdrawPlatformMoney;
import com.dg.main.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserWithdrawPlatformMoneyMapper   extends BaseMapper<UserWithdrawPlatformMoney>,BaseDao<UserWithdrawPlatformMoney> {
    UserWithdrawPlatformMoney findByCode(@Param("code") String code);
    List<UserWithdrawPlatformMoney> findUnpayedList();
    int countByUnpayed();
}
