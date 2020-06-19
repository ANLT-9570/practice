package com.dg.main.dao.orders;

import com.dg.main.Entity.orders.UserDepositMoney;
import com.dg.main.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDepositMoneyMapper   extends BaseMapper<UserDepositMoney>, BaseDao<UserDepositMoney> {
    UserDepositMoney findByCode(@Param("code") String code);
}
