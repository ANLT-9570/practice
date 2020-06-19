package com.dg.main.dao.orders;

import com.dg.main.Entity.orders.LogisticsLog;
import com.dg.main.Entity.orders.LogisticsState;
import com.dg.main.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticsStateMapper  extends BaseMapper<LogisticsState>,BaseDao<LogisticsState>{
    LogisticsState  findByOrderCode(@Param("code")String code);
}
