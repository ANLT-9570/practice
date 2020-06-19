package com.yzl.planManagementService.Impl;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.gson.Gson;
import com.yzl.distriEpcTaskService.impl.TaskIssuedServiceImpl;
import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.mapper.YzlEpcMapper;
import com.yzl.mapper.YzlEpcTaskProgressMapper;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.mapper.YzlMonitoringstatisticsMapper;
import com.yzl.mapper.YzlTaskMapper;
import com.yzl.mapper.YzlXbMapper;
import com.yzl.planManagementService.CompletionTaskService;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlDistrictExample;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlMenu;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlUser;
import com.yzl.pojo.YzlXb;
import com.yzl.utils.CountZLLBDTO;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.LoginUtils;
import com.yzl.utils.YzlEpcAndTaskStaticti;
import com.yzl.utils.YzlResult;
import com.yzl.utils.enums.DistrictEnum;
import com.yzl.utils.enums.ResultEnum;
import com.yzl.utils.enums.TaskWorkSatusEnum;
import com.yzl.utils.exception.YzlException;

@Transactional
@Service
public class CompletionTaskServiceImpl implements CompletionTaskService{

	@Autowired
	private YzlXbMapper xbMapper;

	@Autowired
	private YzlDistrictMapper districtMapper;
	
	@Autowired
	private YzlEpcMapper epcMapper;
	
	@Autowired
	private YzlTaskMapper taskMapper;
	
	@Autowired
	private YzlMonitoringstatisticsMapper monitoringstatisticsMapper;
	
	@Autowired
	private YzlEpcTaskProgressMapper epcTaskProgressMapper;
	
	@Autowired
	private YzlMenuMapper menuMapper;
	@Autowired
	private YzlXbMapper XbMapper;
	private static Logger logger = Logger.getLogger(CompletionTaskServiceImpl.class);
	private static List<List<Map<String, String>>> list = new ArrayList<>();
//	private static List<HashMap<String, String>> epcAndTask = new ArrayList<>();//用来 存放 计划  完成  占计划% 
	String uid;
	//查询表头
//	@Override
//	public List<YzlTask> epcTab(String year,String disCode,String zllb) {
//		
//		//查询的状态
//		List<String> stats = new ArrayList<>();
//		stats.add("2");
//		//获取用户对应的全蝎
//		//List<String> menu = getMenu();
//		List<String> authoritys = LoginUtils.getLoginUserAuthority(menuMapper);
//		//查询审核完成的表头
//		List<YzlTask> resultTaskList = new ArrayList<>();//返回结果的taskList
//		//根据权限 地区行政编号，年份查询 任务下发中的 task
////		List<YzlTask> taskList = taskMapper.queryDISTINCTEnameAndMarkFormXbTableByZYNDAndCountyCode(year, disCode, authoritys, stats,zllb);
//		List<YzlTask> taskList = XbMapper.selectByTaskIssuedTableHead(year, disCode, zllb, authoritys,stats);
//		
//		for (YzlTask task : taskList) {
//			String ZLLB = task.getMark();//获取造林类别
//			//根据权限 地区行政编号，年份，造林类别。 查询任务下发中的 epc
//			List<YzlEpc> epcList = epcMapper.queryDISTINCTEnameAndMarkFormXbTableByZYNDAndCountyCode(year, disCode, authoritys, ZLLB, stats);
//			task.setList(epcList);//把工程list封装到task中
//			resultTaskList.add(task);//把任务封装到返回的数组中
//		}
//		return resultTaskList;
//	}
	
	//查询表头
		@Override
		public List<YzlTask> epcTab(String year, String disCode,String zllb) {
			System.out.println(disCode);
			if ("10".equals(zllb)) {
				zllb = null;
			}
			//要查询的状态
			List<String> stats = new ArrayList<>();
			stats.add("2");
			//获取用户对应的全蝎
			List<String> menu = getMenu();
			//查询审核完成的表头
//			List<YzlTask> tasks = XbMapper.selectEpcTabName(stats,year,disCode,menu,zllb);
			//查询下发的表头
			List<YzlTask> tasks = XbMapper.selectByTaskIssuedTableHead(year, disCode, zllb, menu,stats);
			
			for (YzlTask task : tasks) {
				String tcode = task.getMark();
				//查询这个造林类别拥有的工程
				//List<YzlEpc> epcs = XbMapper.selectByEpcPossessTask(tcode,year,stats,disCode,menu,zllb);
				List<YzlEpc> epcs = XbMapper.selectByZllb(tcode,year,disCode,menu,zllb,stats);
//						HashMap<String, String> hashMap = new HashMap<>();
				for (YzlEpc yzlEpc : epcs) {//把造林类别和工程拼在一起
					yzlEpc.setField(tcode+"T"+yzlEpc.getMark());
				}
				if (epcs.size()>0) {
					task.setList(epcs);
//							epcAndTask.add(hashMap);
				}
			}
			return tasks;
		}
	
	@Override
	public EasyUIResult queryTaskData(Integer page, Integer rows, String year, String areaCode,String ZLLB,String usr) {
		list.clear();
		if ("10".equals(ZLLB)) {
			ZLLB = null;
		}
		List<String> stats = new ArrayList<>();
		stats.add(TaskWorkSatusEnum.TASK_CITY_VERIFIED.getCode().toString());
		if(StringUtils.isBlank(year)){//如果年份等于 空 则获取当前时间的年份
			year = new SimpleDateFormat("yyyy").format(new Date());
		}
		if(StringUtils.isBlank(areaCode)){//如果地区为空 则抛出异常
			logger.error("需要查询的地区行政编号为空-- areaCode="+areaCode);
			throw new YzlException(ResultEnum.REGION_ID_ISNULL);
		}
		List<String> menu = getMenu();
//		YzlUser loginUser = LoginUserUtils.getLoginUser();
//		String userId = loginUser.getId().toString();
//		String keywork = loginUser.getKeywork();
//		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(userId));
		//获取当前登录用户的所有权限
		List<Map<String,String>> resultList = new ArrayList<>();
	//	if(areaCode.equals(DistrictEnum.REGION_ADMIN.getCode())){//如果相等就是当前选择的是自治区
		if(StringUtils.isBlank(areaCode) || areaCode.equals(DistrictEnum.REGION_ADMIN.getCode())){
//			resultList = queryCityDataAndCount(year,areaCode,stats,authoritys,ZLLB,keywork); //查询并统计市级数据
			resultList = selectByCityAndCount(year, stats, menu, areaCode, ZLLB);
		}else{
			resultList = selectByCountyCount(year, stats, menu, areaCode, ZLLB);
			//return epcTaskData(year, areaCode, page, rows, usr);
//			resultList = queryCountyDataAndCount(year, areaCode, stats, authoritys, ZLLB, keywork);//查询并统计县级数据
		}
/*		}else{
			//查询并统计县级数据
			resultList = queryCountyDataAndCount(year,areaCode,stats,authoritys,ZLLB,keywork);
		}*/

		//dataTreating(epcTaskProgressList, areaCode, authoritys, year);
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
		List<Map<String,String>> result = new ArrayList();
		//截取页面需要显示数据
		if(resultList.size()<rows){
			result=resultList;
		}else{
			result = resultList.subList(beginIndex, lastIndex);
		}
		easyUIResult.setRows(result);
		easyUIResult.setTotal(resultList.size());
		return easyUIResult;
	}

	
	
	
	
	private List<Map<String,String>> queryCountyDataAndCount(String year,String areaCode,List<Integer> stats,List<String> authoritys,String ZLLB,String keywork){
		list.clear();
		List<Map<String,String>> countDataList = new ArrayList<>();
		List<Map<String,String>> resultList = new ArrayList<>();//数据结果返回页面List
		//查询任务下发的数据 并统计县级数据
		List<YzlEpcTaskProgress> epcTaskProgressList = epcTaskProgressMapper.queryCountyData(year, areaCode, authoritys,ZLLB,null,stats);
		//查询任务完成的数据 并统计县级数据
//		taskIssuedDTOs.add(epcTaskProgressList);
		//查询任务完成的数据 并统计市级数据
		List<YzlEpcAndTaskStaticti> epcAndTaskStatictiList = xbMapper.queryCountyDataByParamGroupByCountyAndGCLBAndZLLBAndJHNDAndZYND(year, stats, authoritys, areaCode,ZLLB,null);
		try {
			for (int i = 0; i < epcAndTaskStatictiList.size(); i++) {//遍历任务完成的数据
				List flag = new ArrayList<>();//存储已经添加过的数据id
				HashMap countDataMap = new HashMap<>();
				YzlEpcAndTaskStaticti IepcAndTaskStaticti = epcAndTaskStatictiList.get(i);
				YzlDistrict district = districtMapper.selectByNumber(IepcAndTaskStaticti.getCounty());//根据县行政编号查询地区
				double XTJSBMJDouble = Double.valueOf(IepcAndTaskStaticti.getXTJSBMJ());//获取任务下发的数据量
				countDataMap.put("city", district.getCity());
				countDataMap.put("county", district.getCounty());
				for (int j = 0; j < epcTaskProgressList.size(); j++) {
					YzlEpcTaskProgress JepcTaskProgress = epcTaskProgressList.get(j);
					if(IepcAndTaskStaticti.getCounty().equals(JepcTaskProgress.getCountycode()) //判断县级行政编号是否相等
							&&IepcAndTaskStaticti.getZLLB().equals(JepcTaskProgress.getZllb())	//判断造林类别是否相等
								&&IepcAndTaskStaticti.getGCLB().equals(JepcTaskProgress.getGclb())){ //判断工程类别是否相等
						//获取任务下发的数量 
						//获取任务工作完成的数量
						double taskprogress = Double.valueOf(JepcTaskProgress.getTaskprogress());
						// 用任务完成数据除以任务发下数量得到 占任务完成比例的结果   
						double zjhNumber = XTJSBMJDouble/taskprogress;
						//封装 任务完成量占任务计划量的比例  保留一位小数
						countDataMap.put("jh"+IepcAndTaskStaticti.getZLLB()+"Y"+IepcAndTaskStaticti.getGCLB(), String.format("%.4f", taskprogress));
						countDataMap.put("zjh"+IepcAndTaskStaticti.getZLLB()+"Y"+IepcAndTaskStaticti.getGCLB(),  String.format("%.1f", zjhNumber*100)+"%");
					}
				}
				countDataMap.put("wc"+IepcAndTaskStaticti.getZLLB()+"Y"+IepcAndTaskStaticti.getGCLB(), String.valueOf(XTJSBMJDouble));
				countDataMap.put(IepcAndTaskStaticti.getZLLB()+"T"+IepcAndTaskStaticti.getGCLB(), String.valueOf(XTJSBMJDouble));
				countDataMap.put("cityCode", IepcAndTaskStaticti.getCity());//封装市级行政编号
				//countDataMap.put("filesNumber", FilesUrlSet.size());//封装市级行政编号
				//countDataMap.put("filesUrl", "<a href='www.baidu.com' >点击查看("+FilesUrlSet.size()+")</a>");//累加 文件个数
				countDataMap.put("countyCode", IepcAndTaskStaticti.getCounty());////封装县级行政编号
				//封装县级任务下发的数据并 保留小数点后4位
				countDataMap.put("particulars", "<a class='ptl'  href='#' value='"+"' >详情</a>");
				countDataList.add(countDataMap);
			}
			
			List<Map> AddedMap = new ArrayList<>();//存储 已经合并过的Map  判断是否重复添加
			for (int m=0;m<countDataList.size();m++){//遍历List已统计好的数据 进行同县合并
				Map mMap = countDataList.get(m);
				if(!AddedMap.contains(mMap)){
					Map resultMap = new HashMap<>();
					for (int n = m+1; n < countDataList.size(); n++) {
						Map nMap = countDataList.get(n);
						String countyCode = (String) mMap.get("countyCode");
						if(StringUtils.isBlank(countyCode)){
							logger.error("县级行政编号为空");
							throw new YzlException(ResultEnum.COUNTY_IS_NULL);
						}
						if(countyCode.equals(nMap.get("countyCode"))){
							mMap.putAll(nMap); //同一个县  则数据进行合并
							//nMap.put("filesUrl", "<a src='' 点击查看("+(mMapFilesNumber+=nMapFilesNumber)+")</a>");//累加 文件个数
							AddedMap.add(nMap);//把已经合并过的Map 存放到判断List 
						}
					}
					resultList.add(mMap);
				}
			}
		} catch (Exception e) {
			logger.error("异常信息="+e);
			e.printStackTrace();
		}
		list.add(resultList);
		return resultList;
	}
	
	
	//对市的统计和查询
	private List<Map<String,String>> selectByCityAndCount(String year,List<String> stats,List<String> menu,String disCode,String zllb){
		//统计市的任务下发
		List<YzlEpcTaskProgress> epcTaskProgresses = XbMapper.selectByCityCountyTaskIssued(year,menu,zllb,stats);
		LinkedHashSet<String> hashSetCity = new LinkedHashSet<>();
		
		//获得所有的市并去重
		for (YzlEpcTaskProgress yzlEpcTaskProgress : epcTaskProgresses) {
			hashSetCity.add(yzlEpcTaskProgress.getCitycode());
		}
		
		List<Map<String,String>> lists = new ArrayList<>();
		
		//查询任务完成的数据并统计市级数据
		List<YzlXb> xbs = XbMapper.selectByCityComplation(year,stats,menu,disCode,zllb,null);
		
		int flag = 0 ;
		for (YzlEpcTaskProgress epcTaskProgress : epcTaskProgresses) {//遍历所有的市
			
			//根据市编号查询
			YzlDistrictExample example=new YzlDistrictExample();
			com.yzl.pojo.YzlDistrictExample.Criteria criteria = example.createCriteria();
			criteria.andCitycodeEqualTo(epcTaskProgress.getCitycode());
			List<YzlDistrict> list = districtMapper.selectByExample(example);
			
			HashMap<String, String> hashMap = new HashMap<>();
			Float float1 = epcTaskProgress.getTaskprogress();//下发的任务数据
			
			for (YzlXb yzlXb : xbs) {//遍历工作的数据
				if (epcTaskProgress.getCitycode().equals(yzlXb.getCity()) && epcTaskProgress.getGclb().equals(yzlXb.getGclb()) 
				&& epcTaskProgress.getZllb().equals(yzlXb.getZllb()) && epcTaskProgress.getZynd().equals(yzlXb.getZynd())) {//如果市编号相同
					flag++;
					hashMap.put(epcTaskProgress.getZllb()+"zl", yzlXb.getXtjsbmj());
					hashMap.put("citycode", epcTaskProgress.getCitycode());
					hashMap.put("stat", yzlXb.getStat());
					hashMap.put("jh"+epcTaskProgress.getZllb()+"Y"+epcTaskProgress.getGclb(), String.format("%.2f", float1));//计划
					hashMap.put("city", list.get(0).getCity());//市
					String xtjsbmj = yzlXb.getXtjsbmj();//完成的数量
					Float wc = Float.valueOf(xtjsbmj)/float1;//String chardisPos = String.format("%.2f", Double.valueOf(XTJSBMJ));
					hashMap.put("wc"+epcTaskProgress.getZllb()+"Y"+epcTaskProgress.getGclb(), String.format("%.2f", Float.valueOf(xtjsbmj)));
					hashMap.put("zjh"+epcTaskProgress.getZllb()+"Y"+epcTaskProgress.getGclb(), String.format("%.2f", wc*100));//占计划 等于 完成的除以计划
					hashMap.put("particulars", "<a class='ptl'  href='#' value='"+"' >详情</a>");
					hashMap.put(epcTaskProgress.getZllb()+"T"+epcTaskProgress.getGclb(), yzlXb.getXtjsbmj());
					break;
				}
			}
			if (flag == 0) {
				hashMap.put(epcTaskProgress.getZllb()+"zl", "0");
				hashMap.put("citycode", epcTaskProgress.getCitycode());
				hashMap.put("stat", epcTaskProgress.getStat());
				hashMap.put("jh"+epcTaskProgress.getZllb()+"Y"+epcTaskProgress.getGclb(), String.format("%.2f", float1));//计划
				hashMap.put("city", list.get(0).getCity());//市
				hashMap.put("wc"+epcTaskProgress.getZllb()+"Y"+epcTaskProgress.getGclb(), "0");
				hashMap.put("zjh"+epcTaskProgress.getZllb()+"Y"+epcTaskProgress.getGclb(), "0");//占计划 等于 完成的除以计划
				hashMap.put("particulars", "<a class='ptl'  href='#' value='"+"' >详情</a>");
				hashMap.put(epcTaskProgress.getZllb()+"T"+epcTaskProgress.getGclb(), "0");
			}
			lists.add(hashMap);
			flag = 0;
		}
		List<Map<String,String>> disList = new ArrayList<>();
		
		for (String string : hashSetCity) {
			HashMap<String, String> hashMap = new HashMap<>();
			for (Map<String, String> map : lists) {
				String citycode = map.get("citycode");
				Set<Entry<String,String>> set = map.entrySet();
				if (string.equals(citycode)) {
					for (Entry<String, String> entry : set) {
						hashMap.put(entry.getKey(), entry.getValue());
					}
				}
			}
			disList.add(hashMap);
		}
		
		for (Map<String, String> map : disList) {
			String string = map.get("citycode");
			
			Set<Entry<String,String>> entrySet = map.entrySet();
			for (Entry<String, String> entry : entrySet) {
				String key = entry.getKey();
				
				if (key.contains("zl")) {
					
					String substring = key.substring(0, key.indexOf("z"));
					
					Float float1 = Float.valueOf(entry.getValue());
					for (Map<String, String> Old_map : disList) {
						
						Set<Entry<String,String>> entrySet2 = Old_map.entrySet();
						String string2 = Old_map.get("citycode");
						
						for (Entry<String, String> entry2 : entrySet2) {
							String key2 = entry2.getKey();
							if (key2.contains("wc"+substring) && string.equals(string2)) {
								float1+= Float.valueOf(entry2.getValue());
							}
						}
					}
					Float aFloat = float1-Float.valueOf(entry.getValue());
					entry.setValue(aFloat.toString());
				}
			}
		}
		list.add(disList);
		return disList;
	}
	
	
	//对县的统计和查询
	private List<Map<String, String>> selectByCountyCount(String year, List<String> stats, List<String> menu,String disCode,String zllb) {
		//统计县的任务下发
		List<YzlEpcTaskProgress> epcTaskProgresses = XbMapper.selectByCountyCountyTaskIssued(year,menu,disCode,zllb,stats,null);
		LinkedHashSet<String> hashSet = new LinkedHashSet<>();//装县的集合不含重复
		
		for (YzlEpcTaskProgress yzlEpcTaskProgress : epcTaskProgresses) {
			hashSet.add(yzlEpcTaskProgress.getCountycode());
		}
		List<Map<String,String>> lists = new ArrayList<>();//返回的数据
		
		//查询任务完成的数据并统计市级数据
		List<YzlXb> xbs = XbMapper.selectByCityComplation(year, stats, menu, disCode,zllb,null);
		
		for (YzlEpcTaskProgress yzlEpcTaskProgress : epcTaskProgresses) {
			
			//根据市编号查询
			YzlDistrictExample example=new YzlDistrictExample();
			com.yzl.pojo.YzlDistrictExample.Criteria criteria = example.createCriteria();
			criteria.andAnumberEqualTo(yzlEpcTaskProgress.getCountycode());
			List<YzlDistrict> list = districtMapper.selectByExample(example);
			
			HashMap<String, String> hashMap = new HashMap<>();
			Float float1 = yzlEpcTaskProgress.getTaskprogress();//下发的任务数据
			
			int flag = 0;
			
			for (YzlXb yzlXb : xbs) {
				
				if (yzlEpcTaskProgress.getCountycode().equals(yzlXb.getCounty()) && yzlEpcTaskProgress.getGclb().equals(yzlXb.getGclb()) 
						&& yzlEpcTaskProgress.getZllb().equals(yzlXb.getZllb()) && yzlEpcTaskProgress.getZynd().equals(yzlXb.getZynd())) {//如果市编号相同
					flag++;
					
					hashMap.put("countycode", yzlEpcTaskProgress.getCountycode());
					hashMap.put("stat", yzlXb.getStat());
					hashMap.put("jh"+yzlEpcTaskProgress.getZllb()+"Y"+yzlEpcTaskProgress.getGclb(), String.format("%.2f", float1));//计划
					hashMap.put("city", list.get(0).getCity());//市
					hashMap.put("county", list.get(0).getCounty());//县
					String xtjsbmj = yzlXb.getXtjsbmj();//完成的数量
					Float wc = Float.valueOf(xtjsbmj)/float1;//String chardisPos = String.format("%.2f", Double.valueOf(XTJSBMJ));
					hashMap.put("wc"+yzlEpcTaskProgress.getZllb()+"Y"+yzlEpcTaskProgress.getGclb(), String.format("%.2f", Float.valueOf(xtjsbmj)));
					hashMap.put("zjh"+yzlEpcTaskProgress.getZllb()+"Y"+yzlEpcTaskProgress.getGclb(), String.format("%.2f", wc*100));//占计划 等于 完成的除以计划
					hashMap.put("particulars", "<a class='ptl'  href='#' value='"+"' >详情</a>");
					hashMap.put(yzlEpcTaskProgress.getZllb()+"T"+yzlEpcTaskProgress.getGclb(), yzlXb.getXtjsbmj());
					break;
				}
				
			}
			if (flag == 0) {
				hashMap.put("countycode", yzlEpcTaskProgress.getCountycode());
				hashMap.put("stat", yzlEpcTaskProgress.getStat());
				hashMap.put("jh"+yzlEpcTaskProgress.getZllb()+"Y"+yzlEpcTaskProgress.getGclb(), String.format("%.2f", float1));//计划
				hashMap.put("city", list.get(0).getCity());//市
				hashMap.put("county", list.get(0).getCounty());//县
				hashMap.put("wc"+yzlEpcTaskProgress.getZllb()+"Y"+yzlEpcTaskProgress.getGclb(), "0");//完成
				hashMap.put("zjh"+yzlEpcTaskProgress.getZllb()+"Y"+yzlEpcTaskProgress.getGclb(), "0");//占计划 等于 完成的除以计划
				hashMap.put("particulars", "<a class='ptl'  href='#' value='"+"' >详情</a>");
				hashMap.put(yzlEpcTaskProgress.getZllb()+"T"+yzlEpcTaskProgress.getGclb(), "0");
			}
			flag = 0;
			lists.add(hashMap);
		}
		List<Map<String,String>> countyList = new ArrayList<>();
		for (String countyCode : hashSet) {//县的编号
			HashMap<String, String> hashMap = new HashMap<>();
			for (Map<String, String> map : lists) {//
				
				String string2 = map.get("countycode");
				if (countyCode.equals(string2)) {
					Set<Entry<String,String>> entrySet = map.entrySet();
					for (Entry<String, String> entry : entrySet) {
						hashMap.put(entry.getKey(), entry.getValue());
					}
				}
			}
			countyList.add(hashMap);
		}
		list.add(countyList);
		return countyList;
	}
	

	//查询市级数据并进行统计
	/*private List<Map<String,String>> queryCityDataAndCount(String year,String areaCode,List<Integer> stats,List<String> authoritys,String ZLLB,String keywork){
		list.clear();
		List<Map<String,String>> countDataList = new ArrayList<>();
		List<Map<String,String>> resultList = new ArrayList<>();//数据结果返回页面List
		//查询任务下发的数据 并统计市级数据
		List<YzlEpcAndTaskStaticti> epcAndTaskStatictiList = xbMapper.queryCityDataByParamGroupByCityAndGCLBAndZLLBAndJHNDAndZYND(year, stats, authoritys, areaCode,ZLLB,null);
		LinkedHashSet<String> hashSet = new LinkedHashSet<>();//存市编号
		for (YzlEpcAndTaskStaticti epcAndTaskStaticti : epcAndTaskStatictiList) {//不会吧重复的市编号添加进去
			hashSet.add(epcAndTaskStaticti.getCity());
		}
		List<YzlEpcTaskProgress> epcTaskProgressList = epcTaskProgressMapper.queryCityData(year, areaCode, authoritys,ZLLB,null);

		int record = 0;
		//查询任务完成的数据 并统计市级数据
		try {
			for (String string : hashSet) {//遍历所的市编号

				for (int i = 0; i < epcAndTaskStatictiList.size(); i++) {
					HashMap<String, String> map = new HashMap<>();
					YzlEpcAndTaskStaticti IepcAndTaskStaticti = epcAndTaskStatictiList.get(i);
					if (string.equals(epcAndTaskStatictiList.get(i).getCity())) {//如果跟市编号相等
					//	List flag = new ArrayList<>();//存储已经添加过的数据id
						for (int j = 0; j < epcTaskProgressList.size(); j++) {
							YzlEpcTaskProgress JepcTaskProgress = epcTaskProgressList.get(j);
							if(IepcAndTaskStaticti.getCity().equals(JepcTaskProgress.getCitycode()) //判断市级行政编号是否相等
									&&IepcAndTaskStaticti.getZLLB().equals(JepcTaskProgress.getZllb()) //判断造林类别是否相等
										&&IepcAndTaskStaticti.getGCLB().equals(JepcTaskProgress.getGclb())){//判断工程类别是否相等
								//获取任务工作完成的数量
								Double XTJSBMJDouble = Double.valueOf(IepcAndTaskStaticti.getXTJSBMJ());
								// 用任务完成数据除以任务发下数量得到  任务完成比例的结果   用结果向上取整保留小数后2位
								double zjh =XTJSBMJDouble/JepcTaskProgress.getTaskprogress().doubleValue();
								map.put("zjh"+JepcTaskProgress.getZllb()+"Y"+JepcTaskProgress.getGclb(), String.format("%.2f", zjh*100)+"%");
								map.put("jh"+JepcTaskProgress.getZllb()+"Y"+JepcTaskProgress.getGclb(), String.format("%.4f", Double.valueOf(JepcTaskProgress.getTaskprogress())));
							}
						}
						//封装市级任务下发的数据 并保留小数点后4位
					}
					YzlDistrict district = districtMapper.selectByNumber(IepcAndTaskStaticti.getCounty());//根据县行政编号查询地区
					map.put("city", district.getCity());
					map.put("wc"+IepcAndTaskStaticti.getZLLB()+"Y"+IepcAndTaskStaticti.getGCLB(), String.valueOf(IepcAndTaskStaticti.getXTJSBMJ()));
					map.put(IepcAndTaskStaticti.getZLLB()+"T"+IepcAndTaskStaticti.getGCLB(), String.valueOf(IepcAndTaskStaticti.getXTJSBMJ()));
					map.put("cityCode", IepcAndTaskStaticti.getCity());//封装市级行政编号			
					//封装市级任务下发的数据 并保留小数点后4位
					//把所有统计好封装到List集合中
					if (record < epcTaskProgressList.size()) {
						countDataList.add(map);
					}
					//String filesUrl = IepcTaskProgress.getFilesUrl();//把文件路径进行拆分
					Set<String> FilesUrlSet= new HashSet();//存储文件路径 并去重复
					
				}
			}
			List<Map> AddedMap = new ArrayList<>();//存储 已经合并过的Map  判断是否重复添加
			
			
			for (int m=0;m<countDataList.size();m++){
				Map mMap = countDataList.get(m);
				//统计造林类别总数
				List<CountZLLBDTO> cityZllbNumberDTOList = xbMapper.countCityZllbNumber(stats);
				if(countDataList.size()==1){
					for (CountZLLBDTO countZLLBDTO : cityZllbNumberDTOList) {
							//添加各个任务总数统计
							mMap.put(countZLLBDTO.getZLLB(),countZLLBDTO.getXTJSBMJ());
					}
					resultList.add(mMap);//添加到返回的集合
					break;
				}
				String cityCode = (String) mMap.get("cityCode");//获取市级行政编号
				if(StringUtils.isBlank(cityCode)){
					logger.error("市级行政编号为空");
					throw new YzlException(ResultEnum.CITY_IS_NULL);
				}
				//resultList.add(mMap);
				if(!AddedMap.contains(mMap)){
					for (int n = m+1; n < countDataList.size(); n++) {
						Map nMap = countDataList.get(n);
						if(cityCode.equals(nMap.get("cityCode"))){
							for (CountZLLBDTO countZLLBDTO : cityZllbNumberDTOList) {
								if(cityCode.equals(countZLLBDTO.getCity())){
									//添加各个任务总数统计
									nMap.put(countZLLBDTO.getZLLB(),countZLLBDTO.getXTJSBMJ());
								}
							}
							mMap.putAll(nMap); //同一个县  则数据进行合并
							AddedMap.add(nMap);//把已经合并过的Map 存放到判断List 
						}
					}
					resultList.add(mMap);
				}
			}		
		} catch (Exception e) {
			logger.error("异常信息="+e);
			e.printStackTrace();
		}
		list.add(resultList);
		return resultList;
	}*/
	
	
	//数据展示
	@Override
	public EasyUIResult epcTaskData(String year,String disCode,Integer page,Integer rows,String usr,String zllb) {
		list.clear();
		//要查询的状态
		List<String> stats = new ArrayList<>();
		stats.add("2");
		List<Integer> stas = new ArrayList<>();
		stas.add(2);
		//获取用户对应的全蝎
		List<String> menu = getMenu();

		YzlUser loginUser = LoginUserUtils.getLoginUser();
		String userId = loginUser.getId().toString();
		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(userId));
		
		List<HashMap<String, String>> maps = new ArrayList<>();
		List<String> citys = new ArrayList<>();
		
		//查询任务完成的数据 并统计市级数据
		//得到指定的地区
		if (disCode.equals("null") || disCode.equals("GX450")) {//查询的是自治区
			citys = xbMapper.selectByCityAndTimeAndStat(year, stats,menu);
		}else {//查询的是这个市或县
			citys = xbMapper.selectByCityAndTimeAndStatCountyAndCity(year, stats,disCode,menu);
		}
		List<YzlEpcAndTaskStaticti> YzlEpcAndTaskStaticti = new ArrayList<>();
		/*if (menu.size()>0) {
			YzlEpcAndTaskStaticti = XbMapper.selectByCityAndEpcInMenu(TaskWorkSatusEnum.TASK_CITY_VERIFIED.getCode(),year,citys,disCode);*/
			//遍历所有地区
			for (String city : citys) {
					//得出每个市今年的数据
					YzlEpcAndTaskStaticti = xbMapper.selectByCityAndEpcComplation(stats,year,city,disCode,citys,menu,zllb);
					List<HashMap<String,String>> municipality = municipality(YzlEpcAndTaskStaticti,disCode);
					HashMap<String,String> hashMap = disposeData(municipality);
					if (hashMap.size()>0) {
						maps.add(hashMap);
					}
					if (menu.size() > 0 && disCode.length() != 2 && disCode.length() != 6) {
						break;
					}
			}
//		list = maps;

		//起始 等于 当前页-1乘以每页记录数
		int page2 = (page-1)*rows;
		//如果集合中的数据没有分页的多久把集合的大小给分页
		if(rows>maps.size()) {
			rows = maps.size();
		}
		List<HashMap<String,String>> subList = new ArrayList<>();
		//截取
		if (maps.size()<10) {
			subList = maps;
		}else {
			maps.subList(page2, rows);
		}
		
		EasyUIResult result = new EasyUIResult();
		result.setRows(subList);
		result.setTotal(maps.size());
		
		return result;
	}
	
	private List<HashMap<String, String>> municipality(List<YzlEpcAndTaskStaticti> YzlEpcAndTaskStaticti,String disCode){
		
		List<String> stats = new ArrayList<>();
		stats.add("2");
		
		List<HashMap<String, String>> hashMaps = new ArrayList<>();
		
		YzlEpcAndTaskStaticti epcAndTaskStaticti = null;
		HashMap<String, String> hashMap = null;
		String cityCode = null;
		String zllb = null;
		String gclb = null;
		String county2 = null;
		String jhnd = null;
		String zynd = null;
		String city = null;
		String ccty = null;
		String ct = null;
		String jh = null;
		Float zjh = null;
		String XTJSBMJ = null;
		YzlEpcTaskProgress plan = null;
		
		for (YzlEpcAndTaskStaticti yzlEpcAndTaskStaticti2 : YzlEpcAndTaskStaticti) {//遍历
			hashMap = new HashMap<>();
			cityCode = yzlEpcAndTaskStaticti2.getCity();//获取市的行政编号
			zllb = yzlEpcAndTaskStaticti2.getZLLB();//造林类别
			gclb = yzlEpcAndTaskStaticti2.getGCLB();//工程类别
			county2 = yzlEpcAndTaskStaticti2.getCounty();//县的编号
			jhnd = yzlEpcAndTaskStaticti2.getJHND();//计划年度
			zynd = yzlEpcAndTaskStaticti2.getZYND();//作业年度
			city = yzlEpcAndTaskStaticti2.getCity();
			
			ct = xbMapper.selectByCity(cityCode);//根据市编号查询市名称
			
			
			if (yzlEpcAndTaskStaticti2.getCounty()!=null && !disCode.equals("null") && !disCode.equals("GX450")) {
				
				String countyCode = yzlEpcAndTaskStaticti2.getCounty();
				
				YzlDistrict district = districtMapper.selectByNumber(countyCode);
				
				hashMap.put("county", district.getCounty());
				hashMap.put("countyCode", countyCode);
			}
			if (disCode.equals("null") || disCode.equals("GX450")) {
				ccty = city;
			}else {
				ccty = county2;
			}
			//查出这个造林类别今年的计划任务是多少
			plan = xbMapper.selectByPlanTask(zllb,gclb,ccty,jhnd,zynd);
			
			if (plan!=null) {
				jh = plan.getTaskprogress().toString();
			}else {
				jh = "0.00";
			}
			
			hashMap.put("jh"+zllb+"Y"+gclb, jh);//计划
			
			//查出这个状态造林类别今年完成了多少
			epcAndTaskStaticti = xbMapper.selectByCompleteTask(zllb,gclb,ccty,jhnd,zynd,stats);
			//完成等于完成的数量乘以15除以10000
			
			if (epcAndTaskStaticti != null) {
				XTJSBMJ = epcAndTaskStaticti.getXTJSBMJ();
			}
			String chardisPos = String.format("%.2f", Double.valueOf(XTJSBMJ));
//			String chardisPos = chardisPos(XTJSBMJ);
			
			hashMap.put("wc"+zllb+"Y"+gclb, chardisPos);
			
			if (plan!=null && chardisPos != null) {
				zjh = Float.valueOf(chardisPos)/Float.valueOf(plan.getTaskprogress())*100;
			}else {
				zjh = 0.0f;
			}
			
			hashMap.put("particulars", "<a class='ptl'  href='#' value='"+"' >详情</a>");
			hashMap.put("zjh"+zllb+"Y"+gclb, String.format("%.2f", zjh));//占计划 等于 完成的除以计划
			hashMap.put("city", ct);//市
			hashMap.put("cityCode", cityCode);//市行政编号
			hashMap.put(yzlEpcAndTaskStaticti2.getZLLB()+"T"+yzlEpcAndTaskStaticti2.getGCLB(), yzlEpcAndTaskStaticti2.getXTJSBMJ());
			hashMaps.add(hashMap);
			
			zjh = null;
			jh = null;
		}
		return hashMaps;
	}
	
	//数据处理
	private HashMap<String, String> disposeData(List<HashMap<String, String>> hashMaps) {
		
		HashMap<String, String> map = new HashMap<>();
		
		for (HashMap<String, String> hashMap : hashMaps) {
			
			Set<Entry<String,String>> entrySet = hashMap.entrySet();
			
			for (Entry<String, String> entry : entrySet) {
				
				String key = entry.getKey();
				String value = entry.getValue();
				map.put(key, value);
			}
		}
		
//		HashMap<String,String> towDispose = towDispose(map);
		
		return map;
	}

	@Override
	public YzlDistrict Ddis(String dcode) {
		YzlDistrict district = districtMapper.selectByPrimaryKey(Integer.valueOf(dcode));
		return district;
	}

	//获取当前登录的用户
	private static YzlUser getUser() {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		YzlUser user = (YzlUser) session.getAttribute("user");
		return user;
	}
	
	//获取用户对应的权限
	private List<String> getMenu(){
		
		//获取等前登录的用户
		YzlUser user = getUser();
		Long id = user.getId();
		
		//市的编号
		List<YzlMenu> mYzlMenus = new ArrayList<>();
		//县的编号
		List<String> permsList = new ArrayList<>();
		
		if (id!=35) {//说明不是系统管理员
			//获取这个用户对应的权限
			List<YzlMenu> menus = monitoringstatisticsMapper.selectByMeun(id);
			
			for (YzlMenu yzlMenu : menus) {
				Long menuId = yzlMenu.getMenuId();
				//获取某个市下的县
				List<YzlMenu> mYzlMenuss = monitoringstatisticsMapper.selectByMenuId(id,menuId);
				for (YzlMenu yzlMenu2 : mYzlMenuss) {
					mYzlMenus.add(yzlMenu2);
				}
			}
			
			for (YzlMenu yzlMenu : mYzlMenus) {
				String perms = yzlMenu.getPerms();
				Pattern pattern = Pattern.compile("[0-9]*");
				Matcher matcher = pattern.matcher(perms);
				if (matcher.matches()) {
					permsList.add(perms);
				}
			}
		}
		
		return permsList;
	}

	//导入
	@Override
	public void derive(String nid, String year, HttpServletResponse response,String disCode,String new_zllb) {
		EasyUIResult result = queryTaskData(1, 10, year, disCode, new_zllb, null);
//		EasyUIResult result = epcTaskData(1, 10, year, disCode, new_zllb, null);
		
		
//		EasyUIResult result = epcTaskData(year, disCode, 1, 10, null,null);
		
		//地区的编号
//		List<String> Anumbers = new ArrayList<>();
//		
//		if (!nid.equals("null") && !nid.equals("GX450")) {
//				Anumbers = xbMapper.selectByFlagOrDcode(nid);
//		}
		
		//创建个工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCellStyle style = workbook.createCellStyle();//设置样式
		HSSFSheet sheet = workbook.createSheet();//创建一个sheet
		
		//遍历集合获取所有的造林类别
		LinkedHashSet<String> hashSet = gainZllb();
		
		//表头的制作hashSet:所有的造林类别 style:样式 year:时间 nid:市县自治区
		makeTable(sheet, hashSet, style, year, disCode);
		
		int st = 5;
		
		
		//遍历所有对数据进行填充
		for (List<Map<String, String>> list2 : list) {
			for (Map<String, String> hashMap : list2) {
				
				Set<Entry<String,String>> entrySet = hashMap.entrySet();
				
				HSSFRow createRow = sheet.createRow(st);//第五航开始//新建的行
				
				for (Entry<String, String> entry : entrySet) {
					String key = entry.getKey();
					String value = entry.getValue();
					
					//获取前面两个字符
					String string = key.substring(0, 2);
					if (key.contains("zl")) {
						//将zl两个字母去掉
						int indexOf = key.indexOf("z");
						String tmark = key.substring(0,indexOf);//造林类别的编号
						YzlTask task = taskMapper.selectByMark(tmark);
						
						//获取合并单元格的植
						int regions = sheet.getNumMergedRegions();
						for(int i=0;i<regions;i++) {
							CellRangeAddress region = sheet.getMergedRegion(i);
							
							int firstRow = region.getFirstRow();//起始行
							int lastRow = region.getLastRow();//结束行
							int firstColumn = region.getFirstColumn();//起始列
//							int lastColumn = region.getLastColumn();//结束列
							
							if (firstRow == 2 && lastRow == 4) {
								HSSFRow row = sheet.getRow(firstRow);
								HSSFCell cell = row.getCell(firstColumn);
								String zllb = cell.getStringCellValue();//得到造林类别的名称
								cell.setCellValue(zllb);//放回去
								if (task.getTname().equals(zllb)) {
									HSSFCell createCell = createRow.createCell(firstColumn);
									createCell.setCellValue(value);
									break;
								}
							}
						}
						
					}
					if (key.equals("city")) {
						HSSFCell cell = createRow.createCell(0);
						cell.setCellValue(value);
					}
					if (nid.length()>1 && key.equals("county")) {
						HSSFCell cell = createRow.createCell(1);
						cell.setCellValue(value);
					}
					if (string.equals("jh")) {
						//jh两个字母去掉后
						String substring = key.substring(2, key.length());
						//16Y8
						int indexOf = substring.indexOf("Y");
						String zllb = substring.substring(0, indexOf);//造林类别编号
						String gclb = substring.substring(indexOf+1, substring.length());//工程编号
						
						//获取指定的工程把数据放到指定的位置
						gainEpcPutLocation(zllb, gclb, sheet, createRow, value, string);
						
					}
					if (key.contains("wc")) {
						//wc两个字母去掉后
						String substring = key.substring(2, key.length());
						//16Y8
						int indexOf = substring.indexOf("Y");
						String zllb = substring.substring(0, indexOf);//造林类别编号
						String gclb = substring.substring(indexOf+1, substring.length());//工程编号
						
						//获取指定的工程把数据放到指定的位置
						gainEpcPutLocation(zllb, gclb, sheet, createRow, value , string);
					}
					if (string.equals("zj")) {
						//zjh三个字母去掉后
						String substring = key.substring(3, key.length());
						//16Y8
						int indexOf = substring.indexOf("Y");
						String zllb = substring.substring(0, indexOf);//造林类别编号
						String gclb = substring.substring(indexOf+1, substring.length());//工程编号
						
						//获取指定的工程把数据放到指定的位置
						gainEpcPutLocation(zllb, gclb, sheet, createRow, value , string);
					}
				}
				st++;
			}
		}
		//对表格样式的渲染Rendering
		rendering(workbook, style);
		
		response.reset();
		OutputStream os;
		try {
			os = response.getOutputStream();
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("content-disposition", "attachment;filename=excel1.xls");
			
			workbook.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//遍历集合获取所有的造林类别
	private LinkedHashSet<String> gainZllb(){
		
		LinkedHashSet<String> hashSet = new LinkedHashSet<>();
		
		for (List<Map<String, String>> list2 : list) {
			for (Map<String, String> hashMap : list2) {
				Set<Entry<String,String>> entrySet = hashMap.entrySet();
				for (Entry<String, String> entry : entrySet) {
					String key = entry.getKey();
					if (key.contains("T")) {
						int indexOf = key.indexOf("T");
						String substring = key.substring(0,indexOf);
						hashSet.add(substring);
					}
				}
			}
		}
		return hashSet;
	}
	
	//表头的制作
	private void makeTable(HSSFSheet sheet,LinkedHashSet<String> hashSet,HSSFCellStyle style,String year,String nid) {
		
		HSSFRow row3 = sheet.createRow(2);//创建行第3行
		HSSFRow row4 = sheet.createRow(3);//创建行第4行
		HSSFRow row5 = sheet.createRow(4);//创建行第5行
		
		int start = 2;//起始位置
		int estart = 2;
		
		if (nid.equals("45")) {
			for (String string : hashSet) {
				//根据编号查询造林类别
				YzlTask task = taskMapper.selectByMark(string);
				sheet.addMergedRegion(new CellRangeAddress(2, 4, start, start));//合并单元格
//				sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow, firstCol, lastCol));
				//一级表头
				HSSFCell cell = row3.createCell(start);
				cell.setCellValue(task.getTname());
				cell.setCellStyle(style);//设置样式
				start++;
				estart++;
			}
		}

		//遍历所有造林类别
		for (String tcode : hashSet) {
			//根据编号查询造林类别
			YzlTask task = taskMapper.selectByMark(tcode);
			//查询该造林类别拥有的工程
			List<String> tas = xbMapper.selectByTaskPossessEpc(tcode,year,TaskWorkSatusEnum.TASK_CITY_VERIFIED.getCode().toString(),nid);
//			List<YzlEpc> epcs = XbMapper.selectByZllb(tcode,year,disCode,menu,zllb,stats);
			int size = tas.size()*3;//这个表头所占据的表格数
			if (tas.size()>0) {
				sheet.addMergedRegion(new CellRangeAddress(2, 2, start, size+start-1));//合并单元格
			}
			//一级表头
			HSSFCell cell = row3.createCell(start);
			cell.setCellValue(task.getTname());
			cell.setCellStyle(style);//设置样式
			
			
			//二级表头
			for (String ecode : tas) {
				
				YzlEpc epc = epcMapper.selectByMark(ecode);//根据工程编号查询
				sheet.addMergedRegion(new CellRangeAddress(3, 3, estart, estart+2));
				HSSFCell cell2 = row4.createCell(estart);
				cell2.setCellValue(epc.getEname());
				cell2.setCellStyle(style);
				
				int atr = 1;
				for(int j=estart;j<estart+3;j++) {
					
					HSSFCell cell3 = row5.createCell(j);
					switch (atr) {
					case 1:
						cell3.setCellValue("计划");
						cell3.setCellStyle(style);
						break;
					case 2:
						cell3.setCellValue("完成");
						cell3.setCellStyle(style);
						break;
					case 3:
						cell3.setCellValue("占计划%");
						cell3.setCellStyle(style);
						break;
					default:
						break;
					}
					atr++;
				}
				
				estart+=3;
			}
			
			start+=tas.size()*3;
		}
	}
	
	//对表格样式的渲染Rendering
	private void rendering(HSSFWorkbook workbook,HSSFCellStyle style) {
		
		HSSFFont font = workbook.createFont();//字体样式
		
		//居中
		style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中（上下居中）
		style.setAlignment(HorizontalAlignment.CENTER);// 左右居中
		
		//设置字体
		font.setFontName("仿宋_GB2312");//字体名称
		font.setFontHeightInPoints((short) 10);//字体的大小
		font.setItalic(false);//设置是否为斜体 
		font.setBold(true);//字体加粗
		style.setWrapText(true);//自动换行
		
		style.setFont(font);//设置需要用到的字体样式
		
//		sheet.setColumnWidth(0, 4*280);
		
	}
	
	//获取指定的工程把数据放到指定的位置
	private void gainEpcPutLocation(String zllb,String gclb,HSSFSheet sheet,HSSFRow createRow,String value,String ex) {
		
		YzlTask task = taskMapper.selectByMark(zllb);
		YzlEpc epc = epcMapper.selectByMark(gclb);
		
		//获取合并单元格的植
		int regions = sheet.getNumMergedRegions();
		stop:for(int i=0;i<regions;i++) {
			CellRangeAddress region = sheet.getMergedRegion(i);
			
			int firstRow = region.getFirstRow();//起始行
//			int lastRow = region.getLastRow();//结束行
			int firstColumn = region.getFirstColumn();//起始列
			int lastColumn = region.getLastColumn();//结束列
			
			if (firstRow == 2) {
				HSSFRow row = sheet.getRow(firstRow);
				HSSFCell cell = row.getCell(firstColumn);
				String tname = cell.getStringCellValue();//取出来//取到的是造林类别名称
				cell.setCellValue(tname);//放回去
				
				//上面获取的是表头
				
				if (task.getTname().equals(tname)) {//判断数据中的造林类别和表头的是否一样
					//相同就进来
					
					HSSFRow row2 = sheet.getRow(3);//获取工程
					
					for(int k=firstColumn; k <= lastColumn;k++) {
						HSSFCell cell2 = row2.getCell(k);
						if (cell2 != null) {
							String ename = cell2.getStringCellValue();//工程名称
							cell2.setCellValue(ename);
							
							if (epc.getEname().equals(ename)) {//判断工程名是否一样
								
								if (ex.equals("jh")) {
									HSSFCell cell3 = createRow.createCell(k);
									cell3.setCellValue(value);
								}else if(ex.equals("wc")){
									HSSFCell cell3 = createRow.createCell(k+1);
									cell3.setCellValue(value);
								}else if(ex.equals("zj")){
									HSSFCell cell3 = createRow.createCell(k+2);
									cell3.setCellValue(value);
								}
								
								break stop;
							}
						}
					}
					
				}
				
			}
		}
	}

	//抽检完成的退回
	@Override
	public YzlResult back(String[] backData, String time,String zllb,String [] countys) {
		String county = null;
		String ZYND = time;
		int result = 0;
		int tableResult = 0;
		List<String> stats = new ArrayList<>();
		stats.add("2");
		Integer code = TaskWorkSatusEnum.TASK_PROVINCE_AUDIT.getCode();
		
		for (String string : countys) {
			YzlDistrict district = districtMapper.selectByCounty(string);
			county = district.getAnumber();
			tableResult += xbMapper.updateByEpcTaskTable(zllb,county,ZYND,stats,code);
			result += xbMapper.updateByMark(null,county,null,ZYND,code.toString(),stats,zllb);
		}
		if (result > 0 || tableResult > 0) {
			return new YzlResult(200);
		}
		return new YzlResult(400);
	}

	
}
