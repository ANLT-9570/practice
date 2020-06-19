package com.dg.main.dao.orders;

import com.dg.main.Entity.message.UserOrderMessage;
import com.dg.main.base.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserOrderMessageMapper   extends BaseMapper<UserOrderMessage>, BaseDao<UserOrderMessage>{
}
