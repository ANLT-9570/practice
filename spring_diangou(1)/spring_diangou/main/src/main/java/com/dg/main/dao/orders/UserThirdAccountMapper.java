package com.dg.main.dao.orders;

import com.dg.main.Entity.users.UserThirdAccount;
import com.dg.main.base.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserThirdAccountMapper  extends BaseMapper<UserThirdAccount>, BaseDao<UserThirdAccount> {
    UserThirdAccount  findByUserId(@Param("userId")Long userId);
}
