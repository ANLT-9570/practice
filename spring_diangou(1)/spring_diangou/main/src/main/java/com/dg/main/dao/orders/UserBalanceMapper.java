package com.dg.main.dao.orders;

import com.dg.main.Entity.users.UserBalance;
import com.dg.main.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserBalanceMapper   extends BaseMapper<UserBalance>, BaseDao<UserBalance> {
    public List<UserBalance> topPlatmoney(@Param(value = "limit") Integer limit);
    UserBalance findByUserId(@Param(value = "userId")Integer userId);

    int increaseVersionUpdateMoney(@Param("operationTimes")Long operationTimes,@Param("money")Long money,@Param("id")Long id);

    int increaseVersionUpdatePlatformMoney(@Param("operationTimes")Long operationTimes,@Param("PlatformMoney")Long platformMoney,@Param("id")Long id);

    int decreaseVersionUpdateMoney(@Param("operationTimes")Long operationTimes,@Param("money")Long money,@Param("id")Long id);

    int decreaseVersionUpdatePlatformMoney(@Param("operationTimes")Long operationTimes,@Param("PlatformMoney")Long platformMoney,@Param("id")Long id);
     int transToPlatform(@Param("operationTimes")Long operationTimes,@Param("id")Long id,@Param("platformMoney")Long platformMoney,@Param("money")Long money);

    UserBalance selectOneWithNoLock(@Param("id")Long id);
    UserBalance findByUserIdWithNoLock(@Param("userId")Integer userId);
}
