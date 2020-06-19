package com.dg.main.repository.log;

import com.dg.main.Entity.orders.LogisticsLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticsLogRepository extends JpaRepository<LogisticsLog,Long>, JpaSpecificationExecutor<LogisticsLog> {

    @Query(value = "select * from logistics_log where logistics_state_id = :LogisticsStateId limit 1",nativeQuery = true)
    LogisticsLog findByLogisticsStateId(@Param("LogisticsStateId") Long LogisticsStateId);

}
