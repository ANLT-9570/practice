package com.dg.main.serviceImpl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dg.main.Entity.shop.Specifications;
import com.dg.main.exception.ExceptionCode;
import com.dg.main.param.shops.SKUParam;
import com.dg.main.param.shops.SpecificationsParam;
import com.dg.main.repository.SpecificationsRepository;

import com.dg.main.repository.shop.SizeRepository;
import com.dg.main.serviceImpl.shop.ShopsService;
import com.dg.main.util.Result;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import com.google.gson.Gson;

import com.dg.main.Entity.shop.Size;
import com.dg.main.util.slzcResult;
import com.dg.main.dao.SpecificationsMapper;
import com.dg.main.service.SpecificationsServer;

@Service
public class SpecificationsServiceImpl implements SpecificationsServer{

	@Autowired
	private SpecificationsMapper specificationsMapper;
	@Autowired
	SpecificationsRepository specificationsRepository;

	@Autowired
	private SizeRepository sizeRepository;
	@Autowired
	ShopsService shopsService;

	@Override
	public Specifications selectOne(Specifications t) {

		return specificationsMapper.selectOne(t);
	}


	@Override
	public int delete(Specifications t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Specifications t) {
		
		return specificationsMapper.update(t);
	}

	@Override
	public int save(Specifications t) {
		
		return specificationsMapper.save(t);
	}


	@Override
	public List<Specifications> selectAll(Specifications t) {
		// TODO Auto-generated method stub
		return null;
	}


	//分页
	@Override
	public slzcResult selectAll(int offset,int limit,String search) {
		
		slzcResult result = new slzcResult();
		
		int count = specificationsMapper.selectCount(search);
		
//		List<Business> businesses = specificationsMapper.selectAll(limit, offset,null);
		List<Specifications> businesses = specificationsMapper.selectPageAll( offset,limit,search);
		
		result.setRows(businesses);
		result.setTotal(count);
		
		return result;
	}

	@Override
	public List<Specifications> selectPageAll( int offset,int limit) {
		
		return null;
	}

	@Override
	public int deleteAllId(String [] t) {
		
		return specificationsMapper.deleteAllId(t);
	}


	@Override
	public int selectCount() {
		// TODO Auto-generated method stub
		return 0;
	}


//	@Override
//	public List<Homepage> listSort() {
//		// TODO Auto-generated method stub
//		return specificationsMapper.listSort();
//	}
//
//
//	@Override
//	public List<Homepage> listShop(Long mobileUseId) {
//		// TODO Auto-generated method stub
//		return specificationsMapper.listShop(mobileUseId);
//	}


	@Override
	public List<Size> selectSpecifications(long SpecificationsId) {
		// TODO Auto-generated method stub
		return specificationsMapper.selectSpecifications(SpecificationsId);
	}



	@Override
	public Specifications findById(Long id) {
		return specificationsMapper.findById(id);
	}



	@Override
	public List<Specifications> findAllFenye(int offset, int limit, int mobileUseId,Long uid,String search) {
		// TODO Auto-generated method stub
		return specificationsMapper.findAllFenye(offset, limit, mobileUseId,uid,search);
	}



	@Override
	public slzcResult selectAll(int offset, int limit) {
		// TODO Auto-generated method stub
		return null;
	}


	@Transactional
	@Override
	public ModelMap appAdd(Specifications specifications) {
		specifications.setCreatetime(new Timestamp(new Date().getTime()));
		ModelMap map = new ModelMap();
		specificationsMapper.appAdd(specifications);
		String json = "";//specifications.getJson();
		
		String[] split = json.split("=");
		List<Size> list = new ArrayList<Size>();
		for (String string : split) {
			System.out.println(string);
			if (!string.equals("undefined") && !string.equals(" ")) {
				Gson gson = new Gson();
				System.out.println(string);
				Size size = gson.fromJson(string, Size.class);
//				size.setSpecificationsId(specifications.getSpecificationsId());
			//	size.setWeight(String.valueOf(specifications.getWeight()));
				list.add(size);
//				System.out.println(size);
			}
		}
		int ssad = specificationsMapper.SizeAdd(list);
		if (ssad == list.size()) {
			map.put("data", 200);
		}else {
			map.put("data", 400);
		}
		return map;
	}


	@Transactional
	@Override
	public ModelMap upZie(Long sid, int count,BigDecimal jg,Long spidd) {
		
		int idds = specificationsMapper.upSped(spidd,jg);
		int idd = specificationsMapper.upZie(sid,count,jg);
		
		ModelMap map = new ModelMap();
		if (idd > 0 && idds > 0) {
			map.put("data", 200);
		}else {
			map.put("data", 200);
		}
		return map;
	}


	@Override
	public int m(Long specificationsId,Long number) {
		// TODO Auto-generated method stub
		return specificationsMapper.m(specificationsId,number);
	}

//下架
	@Override
	public int soldOut(Long specificationsId,int status) {
		return specificationsMapper.soldOut(specificationsId,status);
	}

	@Transactional
	@Override
	public int canCel(Long specificationsId) {
		int sp = specificationsMapper.canCel( specificationsId);
		int size = specificationsMapper.canCelSize( specificationsId);
		if(sp > 0 && size >0){
			return 1;
		}else{
			return 0;
		}
		
	}


	@Override
	public Size findCount(Long szid) {
		Size size = specificationsMapper.findCount( szid);
		return size;
	}

	@Override
	public List<Specifications> searchSpecifications(String term) {
		return specificationsMapper.searchSpecifications(term);
	}

	@Override
	public Result add(SpecificationsParam param) {
		if (param == null){
			return Result.failureResult(ExceptionCode.Failure.NOT_NULL);
		}
		if(param.getName() == null){
			return Result.failureResult(ExceptionCode.Failure.NAME_NOT_NULL);
		}
		Specifications specifications = new Specifications();
		BeanUtils.copyProperties(param,specifications);
		specifications.setPostMoney(specifications.getPostMoney()*100);
		specifications.setPrice(specifications.getPrice()*100);
		specifications.setCreatetime(new Timestamp(new Date().getTime()));
		specificationsRepository.save(specifications);

		List<SKUParam> items = param.getItems();
		for(SKUParam skuParam:items){
			Size size = new Size();

			BeanUtils.copyProperties(skuParam,size);
			size.setSpecificationsId(specifications.getId());
			sizeRepository.save(size);
//			skuParam.setSpecificationsId(save.getId());
//			skuParamRepository.save(skuParam);
		}
		return Result.successResult();
	}

	@Override
	public Result findByShopId(Long shopId) {
//		specificationsRepository.findByShopId(shopId);
		return null;
	}


}
