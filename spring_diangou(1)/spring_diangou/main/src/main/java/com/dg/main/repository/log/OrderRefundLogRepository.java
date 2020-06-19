package com.dg.main.repository.log;

import com.dg.main.Entity.logs.OrderRefundLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderRefundLogRepository extends JpaRepository<OrderRefundLog,Long>, JpaSpecificationExecutor<OrderRefundLog> {
}
