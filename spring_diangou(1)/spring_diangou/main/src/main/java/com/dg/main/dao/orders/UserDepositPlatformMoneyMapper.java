package com.dg.main.dao.orders;

import com.dg.main.Entity.orders.UserDepositPlatformMoney;
import com.dg.main.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDepositPlatformMoneyMapper   extends BaseMapper<UserDepositPlatformMoney>, BaseDao<UserDepositPlatformMoney> {
    UserDepositPlatformMoney findByCode(@Param("code") String code);
}
