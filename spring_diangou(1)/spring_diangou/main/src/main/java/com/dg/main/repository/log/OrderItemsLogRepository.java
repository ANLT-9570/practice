package com.dg.main.repository.log;

import com.dg.main.Entity.logs.OrderItemsLog;
import com.dg.main.Entity.orders.LogisticsLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderItemsLogRepository extends JpaRepository<OrderItemsLog,Long>, JpaSpecificationExecutor<OrderItemsLog> {
}
