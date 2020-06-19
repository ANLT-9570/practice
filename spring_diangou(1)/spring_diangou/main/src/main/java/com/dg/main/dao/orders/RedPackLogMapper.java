package com.dg.main.dao.orders;

import com.dg.main.Entity.logs.RedPackLog;
import com.dg.main.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RedPackLogMapper extends BaseMapper<RedPackLog>,BaseDao<RedPackLog>
{
    RedPackLog findByCodeAndUserId(@Param("userId") Long userId,@Param("code") String code);
    RedPackLog randRedPackLog(@Param("code")String code);
    List<RedPackLog> UntakedList(@Param("code")String code);
}
