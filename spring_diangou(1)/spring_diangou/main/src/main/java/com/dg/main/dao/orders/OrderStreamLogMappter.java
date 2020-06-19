package com.dg.main.dao.orders;

import com.dg.main.Entity.logs.OrderStreamLog;
import com.dg.main.base.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStreamLogMappter    extends BaseMapper<OrderStreamLog>,BaseDao<OrderStreamLog>{
}
