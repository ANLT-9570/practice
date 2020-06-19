package com.yzl.planManagementService.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzl.mapper.YzlLandscaperenovationMapper;
import com.yzl.planManagementService.LandscapeRenovationService;
import com.yzl.pojo.YzlLandscaperenovation;
import com.yzl.pojo.YzlLandscaperenovationExample;
import com.yzl.utils.EasyUIResult;

@Transactional
@Service
public class LandscapeRenovationServiceImpl implements LandscapeRenovationService{

	@Autowired
	private YzlLandscaperenovationMapper LandscaperenovationMapper;
	
	@Override
	public EasyUIResult findAll(int page, int rows) {
		PageHelper.startPage(page, rows);
		YzlLandscaperenovationExample example = new YzlLandscaperenovationExample();
		List<YzlLandscaperenovation> list = LandscaperenovationMapper.selectByExample(example);
		
		PageInfo<YzlLandscaperenovation> pageInfo = new PageInfo<>(list);
		
		EasyUIResult result = new EasyUIResult();
		result.setRows(pageInfo.getList());
		result.setTotal(pageInfo.getTotal());
		
		return result;
	}

}
