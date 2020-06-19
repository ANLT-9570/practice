package com.dg.main.dao.orders;

import com.dg.main.Entity.logs.OrderRefundLog;
import com.dg.main.base.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRefundLogMapper   extends BaseMapper<OrderRefundLog>, BaseDao<OrderRefundLog> {
}
