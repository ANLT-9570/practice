package com.dg.main.repository.log;

import com.dg.main.Entity.logs.OrderItemsRefundLog;
import com.dg.main.Entity.orders.LogisticsLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderItemsRefundLogRepository extends JpaRepository<OrderItemsRefundLog,Long>, JpaSpecificationExecutor<OrderItemsRefundLog> {
}
