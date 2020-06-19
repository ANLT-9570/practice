package com.yzl.planManagementService.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzl.mapper.YzlQualitypromotionMapper;
import com.yzl.planManagementService.QualityPromotionService;
import com.yzl.pojo.YzlQualitypromotion;
import com.yzl.pojo.YzlQualitypromotionExample;
import com.yzl.utils.EasyUIResult;

@Transactional
@Service
public class QualityPromotionServiceImpl implements QualityPromotionService{

	@Autowired
	private YzlQualitypromotionMapper QualitypromotionMapper;
	
	@Override
	public EasyUIResult findAll(int page, int rows) {
		PageHelper.startPage(page, rows);
		YzlQualitypromotionExample example = new YzlQualitypromotionExample();
		List<YzlQualitypromotion> list = QualitypromotionMapper.selectByExample(example);
		
		PageInfo<YzlQualitypromotion> pageInfo = new PageInfo<>(list);
		
		EasyUIResult result = new EasyUIResult();
		result.setRows(pageInfo.getList());
		result.setTotal(pageInfo.getTotal());
		
		return result;
	}

}
