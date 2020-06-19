package com.yzl.distriEpcTaskService.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzl.distriEpcTaskService.DistrictService;
import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlDistrictExample;
import com.yzl.pojo.YzlDistrictExample.Criteria;
import com.yzl.pojo.YzlUser;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.YzlResult;
import com.yzl.utils.enums.DisEnums;
import com.yzl.utils.enums.DistrictEnum;
import com.yzl.utils.enums.ResultEnum;
import com.yzl.utils.exception.YzlException;
import com.yzl.utils.vo.DistrictTreeVO;

import net.sourceforge.pinyin4j.PinyinHelper;

@Service
public class DistrictServiceImpl implements DistrictService {

	@Autowired
	private YzlDistrictMapper districtMapper;
	@Autowired
	private YzlMenuMapper menuMapper;
	
	
	/***
	 * 地区添加service
	 */
	@Override
	public void save(YzlDistrict district) {
		String city = district.getCity();
		String county = district.getCounty();
		
		//市的简码
		//String[] citys = PinYin4jUtils.getHeadByString(city);
		//简码
		//String citys = city.substring(0, city.length()-1);
		String sub = city+county;
		//String[] headByString = PinYin4jUtils.getHeadByString(sub);
		//String join = StringUtils.join(headByString);
		
		//district.setShortcode(join);
		
		//编码
		//String pinyin = PinYin4jUtils.hanziToPinyin(county,"");
		//district.setCitycode(pinyin);
		
		//String joc = StringUtils.join(citys);
		//获取到所有对的是对应的编号
		DisEnums[] values = DisEnums.values();
/*		for (DisEnums disEnums : values) {
			if (disEnums.toString().equals(joc)) {
				district.setFlag(disEnums.getMark());
			}
		}*/
		
		if(StringUtils.isBlank(city)){
			throw new YzlException(ResultEnum.CITY_IS_NULL);
		}
		if(StringUtils.isBlank(county)){
			throw new YzlException(ResultEnum.COUNTY_IS_NULL);
		}
		//取市级+县级每个字的首字母,并用'-'区分开
		//String[] headPYs = PinYin4jUtils.getHeadByString(city+"-"+county);
		//将首字母拼接起来
		//String headPy = StringUtils.join(headPYs,"");
	//	district.setMark(headPy);
		districtMapper.insert(district);
	}

	
	/***
	 * 地区分页查询service
	 */
	@Override
	public EasyUIResult pageQuery(int page,int rows) {
		//设置分页查询条件
		PageHelper.startPage(page, rows);
		YzlDistrictExample example = new YzlDistrictExample();
		List<YzlDistrict> list = districtMapper.selectByExample(example);
		PageInfo<YzlDistrict> pageInfo = new PageInfo<>(list);
		
		//封装分页数据
		EasyUIResult easyUIResult = new EasyUIResult();
		easyUIResult.setRows(pageInfo.getList());
		easyUIResult.setTotal((int)pageInfo.getTotal());
		return easyUIResult;
	}

	/***
	 * 根据id 删除service
	 */
	@Override
	public void delete(String ids) {
		if(ids!=null && ids != ""){
			String[] dIds = ids.split(",");
			for (String id : dIds) {
				districtMapper.deleteByPrimaryKey(Integer.valueOf(id));
			}
		}
	}

/*搜索*/
	@Override
	public YzlResult searchByCityOrCounty(String searchKey) {
		List<YzlDistrict> list = districtMapper.findByCityOrCounty(searchKey);
		if(list!=null&&list.size()>0){
			return YzlResult.ok(list);
		}
		return YzlResult.build(400, "未搜索到任何结果！");
	}

	
	/**
	 * 查询所有地区
	 */
	@Override
	public YzlResult queryDistinctCity() {
		List<Map> citys = districtMapper.queryDistinctCity();
		if(citys!=null && citys.size()>0){
			return YzlResult.ok(citys);
		}
		return YzlResult.build(400, "未查询到任何地区！");
	}


	
	/***
	 * 根据市名查询 县/区 
	 */
	@Override
	public List<YzlDistrict> queryCountyByCityName(String cityName) {
		YzlDistrictExample example = new YzlDistrictExample();
		Criteria criteria = example.createCriteria();
		criteria.andCityEqualTo(cityName);
		List<YzlDistrict> list = districtMapper.selectByExample(example);
		if(list!=null && list.size()>0){
			return list;
		}
		return null;
	}


	@Override
	public List<YzlDistrict> show(String q) {
		List<YzlDistrict> list = new ArrayList<YzlDistrict>();
		if (StringUtils.isNotBlank(q)) {
			list = districtMapper.selectValue(q);
		}else {
			YzlDistrictExample example=new YzlDistrictExample();
			list = districtMapper.selectByExample(example);
		}
		if (list.size()>0) {
			for (YzlDistrict yzlDistrict : list) {
				yzlDistrict.setName(yzlDistrict.getCity()+"，"+yzlDistrict.getCounty());
			}
		}
		return list;
	}


	@Override
	public YzlDistrict ver(String county) {
		
		YzlDistrictExample example=new YzlDistrictExample();
		example.createCriteria().andCountyEqualTo(county);
		List<YzlDistrict> list = districtMapper.selectByExample(example);
		
		YzlDistrict district = null;
		if (list!=null && list.size()>0) {
			district = list.get(0);
		}
		return district;
	}


	//查询地区树形节点展示
	@Override
	public List<DistrictTreeVO> queryTreeNode(HttpServletRequest request) {
		List<DistrictTreeVO> districtTreeVOList = new ArrayList<>();
		/*创建最高级父节点*/
		DistrictTreeVO districtTreeVO = new DistrictTreeVO();
		districtTreeVO.setText(DistrictEnum.REGION_ADMIN.getName());
		//默认父节点是打开状态
		districtTreeVO.setState("open");
		/*行政编号*/
		districtTreeVO.setId(DistrictEnum.REGION_ADMIN.getCode());
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		List<String> queryPermsByUserId = menuMapper.queryPermsByUserId(Integer.parseInt(loginUser.getId().toString()));
		//根据用户权限查询所有地区
		List<DistrictTreeVO> districtTreeVOChildren = districtMapper.queryRecursionDistrict(queryPermsByUserId);
		//添加叶子节点
		for (int i = 0; i < districtTreeVOChildren.size(); i++) {
			List<DistrictTreeVO> nodeChildren = districtMapper.findDistrictByCityCode(queryPermsByUserId, districtTreeVOChildren.get(i).getId());
			districtTreeVOChildren.get(i).setChildren(nodeChildren);
			if (districtTreeVOChildren.size()==1) {
				districtTreeVOChildren.get(i).setState("open");
			}
		}
		//添加子节点
		districtTreeVO.setChildren(districtTreeVOChildren);
		districtTreeVOList.add(districtTreeVO);
		return districtTreeVOList;
	}
}
