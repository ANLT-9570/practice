package com.dg.main.dao.orders;

import com.dg.main.Entity.orders.UserMoneyTransToPlatformMoney;
import com.dg.main.base.BaseMapper;

import com.dg.main.trans_mapper_obj.CountUserDepositStream;
import com.dg.main.trans_mapper_obj.UserDepositStream;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMoneyTransToPlatformMoneyMapper    extends BaseMapper<UserMoneyTransToPlatformMoney>, BaseDao<UserMoneyTransToPlatformMoney> {
    List<UserDepositStream> userDepositStream(@Param("id")Integer id);
    List<CountUserDepositStream> countUserDepositStream(@Param("id")Integer id);
}
