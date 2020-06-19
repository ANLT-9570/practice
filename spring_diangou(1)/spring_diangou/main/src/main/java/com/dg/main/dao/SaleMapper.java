package com.dg.main.dao;

import java.util.List;


import com.dg.main.Entity.users.MobileUser;
import com.dg.main.base.BaseMapper;
import org.apache.ibatis.annotations.Param;


public interface SaleMapper extends BaseMapper<MobileUser> {

	List<MobileUser> selectPageAll(@Param("offset") int offset, @Param("limit") int limit, @Param("search") String search);

}
