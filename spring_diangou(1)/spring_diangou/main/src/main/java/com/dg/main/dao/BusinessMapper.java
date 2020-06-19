package com.dg.main.dao;


import java.util.List;


import com.dg.main.Entity.users.MobileUser;
import com.dg.main.dao.orders.BaseDao;
import org.apache.ibatis.annotations.Param;

import com.dg.main.base.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessMapper extends BaseMapper<MobileUser>   , BaseDao<MobileUser> {

	public List<MobileUser> selectPageAll(@Param("offset") int offset,@Param("limit")int limit);
	
	public int deleteAllId(@Param("t")String[] t) ;
	
	
	public  int update(@Param("t")MobileUser t) ;
	 
	 public MobileUser selectfindpone(MobileUser business);
	 
	 public  void yaoqingma(MobileUser business);

	public int updateImg(@Param("imageUrl") String imageUrl,@Param("mobileUserId") String mobileUserId);

//	public int dainpuUpdate(@Param("t") RealName realName);
	public MobileUser selectmobileUse(MobileUser business);
	public int updateMobileUse (MobileUser business);
	public MobileUser selectPassword(@Param("mobileUseId")Long mobileUserId);
	public int  addPassword(MobileUser business);
	public int updateMobileUsePhone (MobileUser business);

	public int updateMakr(Integer userid);

	public MobileUser findByCodeMb(String yaoqingCode, long mobileUseId);

	public MobileUser findByStaffCode(String yaoqingCode);

	
}
