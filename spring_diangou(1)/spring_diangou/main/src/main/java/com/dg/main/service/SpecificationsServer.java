package com.dg.main.service;

import java.math.BigDecimal;
import java.util.List;

import com.dg.main.param.shops.SpecificationsParam;
import com.dg.main.util.Result;
import org.springframework.ui.ModelMap;

import com.dg.main.Entity.shop.Size;
import com.dg.main.Entity.shop.Specifications;
import com.dg.main.util.slzcResult;
import com.dg.main.base.BaseMapper;


public interface SpecificationsServer extends BaseMapper<Specifications>{

	 slzcResult selectAll(int offset, int limit,String search) ;
	
//	public List<Homepage> listSort();
//	public List<Homepage>listShop(Long mobileUseId);
	
	public List<Size> selectSpecifications(long SpecificationsId);


    public Specifications findById(Long id);
    
	public List<Specifications> findAllFenye(int offset, int limit, int mobileUseId,Long uid,String search);//店铺产品页

	ModelMap appAdd(Specifications specifications);

	ModelMap upZie(Long sid, int count,BigDecimal jg,Long spidd);
	public int m(Long specificationsId,Long number);

	int soldOut(Long specificationsId,int status);

	int canCel(Long specificationsId);

	Size findCount(Long szid);

	List<Specifications> searchSpecifications(String term);

	Result add(SpecificationsParam param);

    Result findByShopId(Long shopId);
}
