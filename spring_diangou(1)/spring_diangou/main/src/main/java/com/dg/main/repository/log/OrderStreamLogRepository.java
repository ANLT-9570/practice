package com.dg.main.repository.log;

import com.dg.main.Entity.logs.OrderStreamLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderStreamLogRepository extends JpaRepository<OrderStreamLog,Long>, JpaSpecificationExecutor<OrderStreamLog> {
}
