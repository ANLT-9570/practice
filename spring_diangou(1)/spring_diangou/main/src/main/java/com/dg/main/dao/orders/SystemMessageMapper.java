package com.dg.main.dao.orders;

import com.dg.main.Entity.message.SystemMessage;
import com.dg.main.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SystemMessageMapper  extends BaseMapper<SystemMessage>,BaseDao<SystemMessage>{
    List<SystemMessage> userUnreadSystemList(@Param("id")Integer userId);
    Integer userUnreadSystemCount(@Param("id")Integer userId);
}
