package com.dg.main.dao.orders;

import java.util.List;


import com.dg.main.Entity.orders.RedPack;
import com.dg.main.base.BaseMapper;
import com.dg.main.vo.RedPackVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RedPackMapper  extends BaseMapper<RedPack>,BaseDao<RedPack>{

	List<RedPackVo> tokizaneRedPack();

	RedPack findByUid(Long businessId);
	List<RedPack> shopList(@Param("userId") Long shopId);
	List<RedPack> schedulerList();
	List<RedPack> isSend(@Param("userId")Long userId);
	List<RedPack> isSendv1(@Param("userId")Long userId);
	Long countUserMoney(@Param("userId")Long userId);
	Long countTodaySendRedpack(@Param("userId")Long userId);
}
