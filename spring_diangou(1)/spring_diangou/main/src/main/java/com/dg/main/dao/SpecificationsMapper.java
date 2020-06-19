package com.dg.main.dao;


import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;


import com.dg.main.Entity.shop.Size;
import com.dg.main.Entity.shop.Specifications;
import com.dg.main.base.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecificationsMapper extends BaseMapper<Specifications>{

	List<Specifications> selectPageAll(@Param("offset") int offset,@Param("limit")int limit,@Param("search")String search);
	
	int deleteAllId(@Param("t")String[] t) ;
	
	 int selectCount(@Param("search")String search);
	
	 int update(@Param("t")Specifications t) ;

	 //根据id查询
	Specifications selectById(@Param("specificationsId")String specificationsId);

	 
//	 public List<Homepage> listSort();
//	 public List<Homepage>listShop(Long mobileUseId);
	 
	 public List<Size> selectSpecifications(Long SpecificationsId);

	Specifications findById(Long id);
	public List<Specifications> findAllFenye(@Param("offset") int offset,@Param("limit") int limit,@Param("mobileUseId") int mobileUseId,@Param("uid")Long uid,@Param("search")String search);

	Long appAdd(Specifications specifications);

	int SizeAdd(List<Size> list);

	int upZie(Long sid, int count,BigDecimal jg);

	int upSped(Long spidd, BigDecimal jg);
	
	public int m(Long specificationsId,Long number);

	int soldOut(Long specificationsId,int status);

	int canCel(Long specificationsId);

	int canCelSize(Long specificationsId);

	Size findCount(Long szid);

	 List<Specifications> searchSpecifications(@Param("term")String term);

}
