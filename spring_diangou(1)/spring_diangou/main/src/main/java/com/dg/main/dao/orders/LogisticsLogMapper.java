package com.dg.main.dao.orders;

import com.dg.main.Entity.orders.LogisticsLog;
import com.dg.main.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LogisticsLogMapper   extends BaseMapper<LogisticsLog>,BaseDao<LogisticsLog>{
    List<LogisticsLog> findByLogisticsStateId(@Param("logisticsStateId") Long logisticsStateId);
}

