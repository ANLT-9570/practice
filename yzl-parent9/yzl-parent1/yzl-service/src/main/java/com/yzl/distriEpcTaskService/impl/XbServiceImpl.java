package com.yzl.distriEpcTaskService.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hpsf.Decimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.yzl.distriEpcTaskService.XbService;
import com.yzl.mapper.YzlEpcTaskProgressMapper;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.mapper.YzlXbMapper;
import com.yzl.pojo.YzlUser;
import com.yzl.pojo.dto.TaskDTO;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.TaskStatuUtils;
import com.yzl.utils.YzlResult;
import com.yzl.utils.dto.CountyTaskWorkingDTO;
import com.yzl.utils.enums.AuthorityEnum;
import com.yzl.utils.enums.DistrictEnum;
import com.yzl.utils.enums.ResultEnum;
import com.yzl.utils.enums.TaskWorkSatusEnum;
import com.yzl.utils.exception.YzlException;
import com.yzl.utils.vo.DrilldownNode;
import com.yzl.utils.vo.HighchartsResultVO;


@Service
public class XbServiceImpl implements XbService {

	
	@Autowired
	private YzlEpcTaskProgressMapper epcTaskProgressMapper;
	@Autowired
	private YzlXbMapper xbMapper;
	@Autowired
	private YzlMenuMapper menuMapper;
	
	/**饼形图*/
	/***条形图数据显示*/
	@Override
	public List<HighchartsResultVO> pie(HttpServletRequest request,String year) {
		//List<Map> taskProgressSheetHighchartsList = taskProgressSheetMapper.queryTaskGroupByCity();
		//如果year为空 则获取当前时间的年份 并赋值
		if(StringUtils.isBlank(year)){
			year = new SimpleDateFormat("yyyy").format(new Date());
		}
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		List<String> aNumbers = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		if(CollectionUtils.isEmpty(aNumbers)){
			throw new YzlException(ResultEnum.USER_NOT_MENU);
		}
		List<String> taskStat = new ArrayList<>();
		//查询小班已审核通过的数据 根据市行政编号进行分组
		List<TaskDTO> XTJSBMJGroupByCity = xbMapper.queryXTJSBMJAndCityGroupByCity(aNumbers,year,TaskWorkSatusEnum.TASK_CITY_VERIFIED.getCode().toString());
		//查询任务下发的数据 根据市行政编号进行分组
		//List<TaskDTO> SumXTJSBMJGroupByCity = xbMapper.queryXTJSBMJGroupByCity(aNumbers,year,null);

		Double CityXTJSBMJ = 0.0;
		CityXTJSBMJ = xbMapper.sumCityXTJSBMJ(TaskWorkSatusEnum.TASK_CITY_VERIFIED.getCode().toString());//统计已完成任务数量的值
		
		BigDecimal CityXTJSBMJDecimal = null;
		
		if (CityXTJSBMJ == null) {
			CityXTJSBMJDecimal = new BigDecimal("0");
		}else {
			CityXTJSBMJDecimal = new BigDecimal(CityXTJSBMJ);
		}
		
		List<HighchartsResultVO> highchartsResultVO = new ArrayList<>();
		for (TaskDTO CityXTJSBMJtaskDTO : XTJSBMJGroupByCity ) {
			//截取市级行政编码
			String cityCode = CityXTJSBMJtaskDTO.getaNumber();
			String cityName = CityXTJSBMJtaskDTO.getCity();
			HighchartsResultVO resultVO = new HighchartsResultVO();	
			BigDecimal CityXTJSBMJtaskNumber=CityXTJSBMJtaskDTO.getTaskNumber();
			if(CityXTJSBMJDecimal!=BigDecimal.ZERO){//判断是否为0
				Double number = CityXTJSBMJtaskNumber.divide(CityXTJSBMJDecimal, 4,RoundingMode.HALF_UP).doubleValue();
				resultVO.setY(number*100);
			}else{
				resultVO.setY(0.0);
			}
			resultVO.setName(cityName);
			resultVO.setDrilldown(cityName);
			//创建子节点
			DrilldownNode drilldownNode = new DrilldownNode();
		    drilldownNode.setName(cityName);
		    drilldownNode.setId(cityName);
		    //drilldownNode.setY(number*100);
			 //根据年份 查询已审核通过的小班数据  根据县行政编号进行分组
		    List<TaskDTO> countyFinishNumber = xbMapper.queryXTJSBMJAndCountyByCityCode(aNumbers, year,TaskWorkSatusEnum.TASK_CITY_VERIFIED.getCode().toString(),cityCode);// 待指定已审核通过
		    List<List> voList= new ArrayList<>();
	    	for (TaskDTO countyfinishTaskNumber : countyFinishNumber) {
				//封装县级数据
				List data = new ArrayList();
				data.add(countyfinishTaskNumber.getCounty());
    			if(CityXTJSBMJtaskNumber!=BigDecimal.ZERO){
    				//计算当前县任务完成百分比 四舍五入 取小数点后4位
    				Double cityNumber = countyfinishTaskNumber.getTaskNumber().divide(CityXTJSBMJtaskNumber, 4,RoundingMode.HALF_UP).doubleValue();
    				data.add(cityNumber*100);
    			}else{
    				data.add(0);
    			}
					voList.add(data);
			}
	    	drilldownNode.setData(voList);
	    	resultVO.setDrilldownNode(drilldownNode);
	    	highchartsResultVO.add(resultVO);
		}
		//根据任务下发任务判断是否是自治区管理员   
		if(!aNumbers.contains(AuthorityEnum.TASK_ISSUED_ADD.getPerms())){
			//不是自治区管理员则数据只获取到县级的
			List<HighchartsResultVO> countHighchartsResultVOList = new ArrayList<>();
			for (HighchartsResultVO countyHighchartsResultVO : highchartsResultVO) {
				DrilldownNode countyDrilldownNode = countyHighchartsResultVO.getDrilldownNode();
				List<List> data = countyDrilldownNode.getData();
				for (List list : data) {
					HighchartsResultVO resultHighchartsVO = new HighchartsResultVO();
					for (int i=0; list.size()>i;i++) {
						resultHighchartsVO.setName((String)list.get(0));
						resultHighchartsVO.setY((Double)list.get(1));
						break;
					}
					countHighchartsResultVOList.add(resultHighchartsVO);
				}
			}
			return countHighchartsResultVOList;
		}
	    return highchartsResultVO;
	}

	
	/***条形图数据显示*/
	@Override
	public List<HighchartsResultVO> queryTaskGroupByCity(HttpServletRequest request,String year) {
		//List<Map> taskProgressSheetHighchartsList = taskProgressSheetMapper.queryTaskGroupByCity();
		//如果year为空 则获取当前时间的年份 并赋值
		if(StringUtils.isBlank(year)){
			year = new SimpleDateFormat("yyyy").format(new Date());
		}
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		List<String> aNumbers = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		if(CollectionUtils.isEmpty(aNumbers)){
			throw new YzlException(ResultEnum.USER_NOT_MENU);
		}
		List<String> taskStat = new ArrayList<>();
		taskStat.add(TaskWorkSatusEnum.TASK_CITY_VERIFIED.getCode().toString());
		//查询小班已审核通过的数据 根据市行政编号进行分组
		List<TaskDTO> XTJSBMJGroupByCity = xbMapper.queryXTJSBMJGroupByCity(aNumbers,year,taskStat);
		//查询任务下发的数据 根据市行政编号进行分组
		List<TaskDTO> TaskGroupByCity = epcTaskProgressMapper.queryTaskGroupByCity(aNumbers,year);	
		
		List<HighchartsResultVO> highchartsResultVO = new ArrayList<>();
		for (TaskDTO CityXTJSBMJtaskDTO : TaskGroupByCity ) {
			//截取市级行政编码
			String cityCode = CityXTJSBMJtaskDTO.getaNumber().substring(0, 4);
			int cityFlag=0;//判断下发的任务  是否已有提交
			for (TaskDTO CitytaskDTO : XTJSBMJGroupByCity ) {
				try {
					Map<String,Object> map = new HashMap<>();
					//判断市行政编码是否一致
					if(CitytaskDTO.getaNumber().equals(cityCode)){
						cityFlag++;
						//计算任务完成比例   四舍五入 取小数点后4位
						Double number = CitytaskDTO.getTaskNumber().divide(CityXTJSBMJtaskDTO.getTaskNumber(), 4,RoundingMode.HALF_UP).doubleValue();
						//取城市名称
						String city = CityXTJSBMJtaskDTO.getCity();
						//封装市级数据 model
						HighchartsResultVO resultVO = new HighchartsResultVO();
						
						resultVO.setName(city);
						resultVO.setDrilldown(city);
						resultVO.setY(number*100);
						//创建子节点
						DrilldownNode drilldownNode = new DrilldownNode();
					    drilldownNode.setName(city);
					    drilldownNode.setId(city);
					    drilldownNode.setY(12.5D);
					    xbMapper.queryXTJSBMJGroupByCounty(aNumbers);
					    //根据年份 查询任务发布的数据  根据县行政编号进行分组
					    List<TaskDTO> epcTaskNumber = epcTaskProgressMapper.selectTaskNumberGroupByCountyAndByMark(aNumbers, cityCode,year);
					  //根据年份 查询已审核通过的小班数据  根据县行政编号进行分组
					    List<TaskDTO> finishNumber = xbMapper.queryXTJSBMJGroupCountyAndByCity(aNumbers, cityCode,year,TaskWorkSatusEnum.TASK_CITY_VERIFIED.getCode());// 待指定已审核通过
					    List<List> voList= new ArrayList<>();
					    for (TaskDTO countyTaskNumber : epcTaskNumber) {
					    	//标识 当前县的任务有没有开始   默认为0任务没有进行
					    	int countyFlag=0;
					    	for (TaskDTO countyfinishTaskNumber : finishNumber) {
								if(countyTaskNumber.getaNumber().equals(countyfinishTaskNumber.getaNumber())){
									//计算当前县任务完成百分比 四舍五入 取小数点后4位
									Double cityNumber = countyfinishTaskNumber.getTaskNumber().divide(countyTaskNumber.getTaskNumber(), 4,RoundingMode.HALF_UP).doubleValue();
									//封装县级数据
									List data = new ArrayList();
									data.add(countyTaskNumber.getCounty());
									data.add(cityNumber*100);
									voList.add(data);
									countyFlag++;
								}
							}
					    	//如果任务没有提交 或者审核通过 则任务完成比例用0%填充
					    	if(countyFlag==0){
								List data = new ArrayList();
								data.add(countyTaskNumber.getCounty());
								data.add(0.0D);
								voList.add(data);
					    	}
					    	
						}	
					    drilldownNode.setData(voList);
					    resultVO.setDrilldownNode(drilldownNode);
					    highchartsResultVO.add(resultVO);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if(cityFlag==0){//如果等于0 则下发的任务  没有进行提交，或者审核通过
				HighchartsResultVO resultVO = new HighchartsResultVO();
				String city = CityXTJSBMJtaskDTO.getCity();
				resultVO.setName(city);
				resultVO.setDrilldown(city);
				resultVO.setY(0.0d);//设置为0
				List<TaskDTO> epcTaskNumber = epcTaskProgressMapper.selectTaskNumberGroupByCountyAndByMark(aNumbers, cityCode,year);
				List<List> voList= new ArrayList<>();
				for (TaskDTO taskDTO : epcTaskNumber) {	
					List data = new ArrayList();
					data.add(taskDTO.getCounty());
					data.add(0.0D);
					voList.add(data);
				}
				//创建子节点
				DrilldownNode drilldownNode = new DrilldownNode();
				drilldownNode.setData(voList);
				drilldownNode.setId(city);
				drilldownNode.setName(city);
				resultVO.setDrilldownNode(drilldownNode);
				highchartsResultVO.add(resultVO);
			}
		}
		if(!aNumbers.contains(AuthorityEnum.TASK_ISSUED_ADD.getPerms())){
			List<HighchartsResultVO> countHighchartsResultVOList = new ArrayList<>();
			for (HighchartsResultVO countyHighchartsResultVO : highchartsResultVO) {
				DrilldownNode countyDrilldownNode = countyHighchartsResultVO.getDrilldownNode();
				List<List> data = countyDrilldownNode.getData();
				for (List list : data) {
					HighchartsResultVO resultHighchartsVO = new HighchartsResultVO();
					for (int i=0; list.size()>i;i++) {
						resultHighchartsVO.setName((String)list.get(0));
						resultHighchartsVO.setY((Double)list.get(1));
						break;
					}
					countHighchartsResultVOList.add(resultHighchartsVO);
				}
			}
			return countHighchartsResultVOList;
		}
		return highchartsResultVO;
	}


	@Override
	public EasyUIResult queryTaskWorkingByGroupCountyAndGCLBAndZLLB(HttpServletRequest request,Integer page,Integer rows,String year) {
		//如果页面传入的year为空则 用当前时间进行赋值
		if(StringUtils.isBlank(year)){
			year = new SimpleDateFormat("yyyy").format(new Date());
		}
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		List<String> markList = menuMapper.queryPermsByUserId(Integer.valueOf(loginUser.getId().toString()));
		
		List<Integer> stats = new ArrayList<>();
		stats.add(TaskWorkSatusEnum.TASK_COUNTY_WORK.getCode());
		stats.add(TaskWorkSatusEnum.TASK_CITY_AUDIT.getCode());
		
		List<CountyTaskWorkingDTO> countyTaskWorkingDTOList = xbMapper.queryTaskWorkingByGroupCountyAndGCLBAndZLLB(markList,year,stats);
		
		return this.pageQueryUtils(countyTaskWorkingDTOList, page, rows);
	}
	
	

	@Override
	public EasyUIResult queryTaskWorkingByGroupCountyAndGCLBAndZLLBAndAreaCode(HttpServletRequest request, Integer page,
			Integer rows, String areaCode,String year) {
		//如果当前年是Null,则默认是当前时间的年
		if(StringUtils.isBlank(year)){
			year = new SimpleDateFormat("yyyy").format(new Date());
		}
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		List<String> markList = menuMapper.queryPermsByUserId(Integer.valueOf(loginUser.getId().toString()));
		List<Integer> stats = TaskStatuUtils.getTaskStatUnreviewed();
		List<CountyTaskWorkingDTO> countyTaskWorkingDTOList = xbMapper.queryTaskWorkingByGroupCountyAndGCLBAndZLLBAndAreaCode(markList,areaCode,year,stats);
		return this.pageQueryUtils(countyTaskWorkingDTOList, page, rows);
	}
	
	
	//对数据进行分页处理
	private EasyUIResult pageQueryUtils(List<CountyTaskWorkingDTO> countyTaskWorkingDTOList,Integer page,Integer rows){
		List<HashMap> resultList = new ArrayList<>();
		//用做判断当前数据是否已经添加过
		List<Integer> idList = new ArrayList<>();
		try {
			for (int i = 0; i < countyTaskWorkingDTOList.size(); i++) {
				CountyTaskWorkingDTO IcountyTaskWorkingDTO = countyTaskWorkingDTOList.get(i);
				Integer IcountyNumber = IcountyTaskWorkingDTO.getCountyNumber();
				//判断当前数据是否已添加过，如果添加过则直接跳过本次循环 不在进行遍历判断
				if(idList.contains(IcountyTaskWorkingDTO.getId())) continue;
				//封装数据
				HashMap<Object, Object> resultMap = new HashMap<>();
				resultMap.put("city", IcountyTaskWorkingDTO.getCity());
				resultMap.put("county", IcountyTaskWorkingDTO.getCounty());
				resultMap.put("JHND", IcountyTaskWorkingDTO.getJHND());
				resultMap.put("ZYND", IcountyTaskWorkingDTO.getZYND());
				//添加县统计面积到集合、用工程编号+任务类别编号做拼接为Key
				resultMap.put(IcountyTaskWorkingDTO.getGCLB()+"T"+IcountyTaskWorkingDTO.getZLLB(), IcountyTaskWorkingDTO.getXTJSBMJ().setScale(5, BigDecimal.ROUND_HALF_UP));//向上取整，取小数位后5位
				for (int j = i+1; j < countyTaskWorkingDTOList.size(); j++) {
					//判断当前数据是否已添加过，如果添加过则直接跳过本次循环 不在进行遍历判断
					CountyTaskWorkingDTO JcountyTaskWorkingDTO = countyTaskWorkingDTOList.get(j);
					//判断当前数据是否已添加过，如果添加过则直接跳过本次循环 不在进行遍历判断
					if(idList.contains(JcountyTaskWorkingDTO.getId())) continue;
					//获取县行政编号
					Integer JcountyNumber = JcountyTaskWorkingDTO.getCountyNumber();
					//判断是不是同一个县级
					if(IcountyNumber.equals(JcountyNumber)){
						//判断作业年度和计划年度是否一致
						if(IcountyTaskWorkingDTO.getZYND().equals(JcountyTaskWorkingDTO.getZYND())
								&&IcountyTaskWorkingDTO.getJHND().equals(JcountyTaskWorkingDTO.getJHND())){
							//添加县统计面积到集合、用工程类别编号+造林类别编号做拼接为Key                      
							resultMap.put(JcountyTaskWorkingDTO.getGCLB()+"T"+JcountyTaskWorkingDTO.getZLLB(), JcountyTaskWorkingDTO.getXTJSBMJ().setScale(5, BigDecimal.ROUND_HALF_UP));//向上取整，取小数位后5位
							
							idList.add(JcountyTaskWorkingDTO.getId());
						}
					}
				}
				resultList.add(resultMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//封装页面显示记录和总记录数
		EasyUIResult easyUIResult = new EasyUIResult();
		//计算分页起始位置
		Integer beginIndex = (page-1)*rows;
		//计算分页结束位置
		Integer lastIndex = beginIndex+rows;
		//如果大于总记录数 则 分页最大索引为 总记录数
		if(lastIndex>resultList.size()){
			lastIndex=resultList.size();
		}
		List<HashMap> result = new ArrayList<HashMap>();
		//截取页面需要显示数据
		if(resultList.size()<10){
			result=resultList;
		}else{
			result = resultList.subList(beginIndex, lastIndex);
		}
		easyUIResult.setRows(result);
		easyUIResult.setTotal(resultList.size());
		return easyUIResult;
	}


	
}
