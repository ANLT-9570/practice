package com.dg.main.service;



import com.dg.main.Entity.users.MobileUser;
import com.dg.main.base.BaseMapper;


public interface BusinessServer extends BaseMapper<MobileUser> {
	 public MobileUser selectfindpone(MobileUser business);
	 public  void yaoqingma(MobileUser business);
	public int updateImg(String imageUrl,String mobileUserId);
//	public int dainpuUpdate(RealName realName);
	public MobileUser selectmobileUse(MobileUser business);
	public int updateMobileUse (MobileUser business);
	public MobileUser selectPassword(Long mobileUserId);
	public int  addPassword(MobileUser business);
	public int updateMobileUsePhone (MobileUser business);
	public int updateMakr(Integer userid);
	public MobileUser findByCodeMb(String yaoqingCode, long mobileUseId);
	public MobileUser findByStaffCode(String yaoqingCode);
	
}
