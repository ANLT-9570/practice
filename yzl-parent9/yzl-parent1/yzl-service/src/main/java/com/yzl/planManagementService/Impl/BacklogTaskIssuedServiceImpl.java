package com.yzl.planManagementService.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yzl.planManagementService.*;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.mapper.YzlEpcMapper;
import com.yzl.mapper.YzlEpcTaskProgressMapper;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.mapper.YzlMessageMapper;
import com.yzl.mapper.YzlTaskMapper;
import com.yzl.mapper.YzlXbMapper;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlMessage;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlTaskExample;
import com.yzl.pojo.YzlTaskExample.Criteria;
import com.yzl.pojo.YzlUser;
import com.yzl.pojo.YzlXb;
import com.yzl.utils.AdministrativeCode;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.LoginUtils;
import com.yzl.utils.PageBean;
import com.yzl.utils.YzlEpcAndTaskStaticti;
import com.yzl.utils.enums.AuthorityEnum;
import com.yzl.utils.enums.DistrictEnum;
import com.yzl.utils.enums.ResultEnum;
import com.yzl.utils.enums.TaskWorkSatusEnum;
import com.yzl.utils.exception.YzlException;
import com.yzl.utils.vo.BacklogVO;

@Service
public class BacklogTaskIssuedServiceImpl implements BacklogTaskIssuedService{
	
	//日志类
	private static Logger logger = Logger.getLogger(BacklogTaskIssuedServiceImpl.class);
	
	@Autowired
	private YzlMenuMapper menuMapper;
	@Autowired
	private YzlEpcTaskProgressMapper epcTaskProgressMapper;
	@Autowired
	private YzlXbMapper xbMapper;
	@Autowired
	private YzlMessageMapper messageMapper;
	@Autowired
	private YzlTaskMapper taskMapper;	
	@Autowired
	private YzlEpcMapper epcMapper;	
	@Autowired
	private YzlDistrictMapper districtMapper;
	
	
	@Override
	public EasyUIResult pageQuery(int page, int rows) {
		YzlUser loginUser = LoginUserUtils.getLoginUser();
		 String keywork = loginUser.getKeywork();
		//根据用户Id查询所有权限
		List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		List<BacklogVO> backlogVOList = new ArrayList<>();
		//统计最新下发任务数
		//int messageCount = messageMapper.countMessageNumber(PermsList,keywork);
		List<BacklogVO> IssuedTaskbacklogVOList = messageMapper.countMessageNumberGroupByCountyCode(PermsList, keywork);
		for (BacklogVO IssuedTaskbacklogVO : IssuedTaskbacklogVOList) {
			IssuedTaskbacklogVO.setName("已更新的任务");
			
			IssuedTaskbacklogVO.setCheck("<a  class='checkTaskissed' id='taskissued'  value='&backlogTaskIssued.jsp&' href='#'>点击查看</a>");
			backlogVOList.add(IssuedTaskbacklogVO);
		}
		List<Integer> stats = new ArrayList<>();
		if(PermsList.contains(AuthorityEnum.TASK_AUDIT.getPerms())){//判断是否包含审核权限
			stats.add(TaskWorkSatusEnum.TASK_CITY_AUDIT.getCode());//如果包含则查询待审核的任务
		}
		stats.add(TaskWorkSatusEnum.TASK_PROVINCE_AUDIT.getCode());
		List<BacklogVO> xBacklogVOList = xbMapper.queryCountyAndCityAndUpdateTimeAndStat(stats,PermsList);
		String TaskCityAudit = TaskWorkSatusEnum.TASK_CITY_AUDIT.getCode().toString();//待审核状态
		for (BacklogVO xbBacklogVO : xBacklogVOList) {
			xbBacklogVO.setNumber(1);
			/*		xbBacklogVO.setCity(city);
					xbBacklogVO.setCounty(county);*/
			if(TaskCityAudit.equals(xbBacklogVO.getStat())){//判断是否是待审核状态
				xbBacklogVO.setName("待审核的任务");
				xbBacklogVO.setCheck("<a class='checkTaskissed' id='" + TaskWorkSatusEnum.TASK_CITY_AUDIT.getCode() +">"+ "' value='&backlogTaskWorking.jsp&' href='#'>点击查看</a>");
			}else{
				xbBacklogVO.setName("被退回的任务");
				xbBacklogVO.setCheck("<a class='checkTaskissed' id='" + TaskWorkSatusEnum.TASK_PROVINCE_AUDIT.getCode() +">"+ "' value='&backlogTaskWorking.jsp&' href='#'>点击查看</a>");
			}
			backlogVOList.add(xbBacklogVO);
		}
		//封装页面显示记录和总记录数
		EasyUIResult easyUIResult = new EasyUIResult();
		//计算分页起始位置
		int beginIndex = (page-1)*rows;
		//计算分页结束位置
		int lastIndex = beginIndex+rows;
		//如果大于总记录数 则 分页最大索引为 总记录数
		if(lastIndex>backlogVOList.size()) {
			lastIndex=backlogVOList.size();
		}
		if(rows>backlogVOList.size()){
			easyUIResult.setRows(backlogVOList);
		}else{
			//截取页面要显示的数据
			List<BacklogVO> subList = backlogVOList.subList(beginIndex, lastIndex);
			easyUIResult.setRows(subList);
		}
		easyUIResult.setTotal(backlogVOList.size());
		//统计待审核数
		return easyUIResult;
	}

	
	@Override
	public List<YzlTask> getTableHeader(String year, String disCode, String GCLB,String ZLLB) {
		List<YzlTask> resultTaskList = new ArrayList<>();//返回结果的taskList
		YzlUser loginUser = LoginUserUtils.getLoginUser();
		List<String> authoritys = LoginUtils.getLoginUserAuthority(menuMapper);
		String keywork = loginUser.getKeywork();
		List<YzlMessage> messagesList = messageMapper.queryByAuthoritysAndStatu(authoritys, keywork);

		List<YzlTask> taskList= new ArrayList<>();
		List<String> flagTaskList= new ArrayList<>();//存储zllb的mark用来判断mark是否已添加过
		//根据权限 地区行政编号，年份查询 任务下发中的 task
		System.out.println("disCode="+disCode);
		int i=0;
		if(StringUtils.isBlank(ZLLB)){
			for (YzlMessage message : messagesList) {
				List<YzlTask> queryTaskList = taskMapper.queryDISTINCTTnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCodeAndMessagecounty(year,disCode, authoritys, GCLB,message);
				for (YzlTask queryTask : queryTaskList) {
					if(!flagTaskList.contains(queryTask.getMark())){
						flagTaskList.add(queryTask.getMark());
						taskList.add(queryTask);
					}
				}
			}
		}else{
			YzlTaskExample taskExample = new YzlTaskExample();
			Criteria criteria = taskExample.createCriteria();
			criteria.andMarkEqualTo(ZLLB);
			taskList = taskMapper.selectByExample(taskExample);
		}
		for (YzlTask task : taskList) {
			String zllb = task.getMark();//获取造林类别
			//根据权限 地区行政编号，年份，造林类别。 查询任务下发中的 epc
			List<YzlEpc> epcList = new ArrayList<>();
			List<String> flagEpcList = new ArrayList<>();
			for (YzlMessage message : messagesList) {
				List<YzlEpc> queryEpcList = epcMapper.queryDISTINCTEnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCodeAndMessagecounty(year, disCode, authoritys, zllb,GCLB,message);
				for (YzlEpc queryEpc : queryEpcList) {
					if(!flagEpcList.contains(queryEpc.getMark())){
						flagEpcList.add(queryEpc.getMark());
						epcList.add(queryEpc);
					}
				}
			}
			List<YzlEpc> resultEpcList = new ArrayList<>();//返回结果的子节点epcList
			for (YzlEpc epc : epcList) {
				epc.setField(zllb+"T"+epc.getMark());
				resultEpcList.add(epc);
			}
			task.setList(resultEpcList);//把工程list封装到task中
			resultTaskList.add(task);//把任务封装到返回的数组中
		}
		return resultTaskList;
	}

	
	@Override
	public EasyUIResult queryTaskData(int page, int rows, String year, String areaCode, String ZLLB, String GCLB) {
		
		List<Integer> stats = new ArrayList<>();
		stats.add(TaskWorkSatusEnum.TASK_CITY_VERIFIED.getCode());
		if(StringUtils.isBlank(year)){//如果年份等于 空 则获取当前时间的年份
			year = new SimpleDateFormat("yyyy").format(new Date());
		}
		if(StringUtils.isBlank(areaCode)){//如果地区为空 则抛出异常
			logger.error("需要查询的地区行政编号为空-- areaCode="+areaCode);
			throw new YzlException(ResultEnum.AREA_CODE_ISNULL);
		}
		YzlUser loginUser = LoginUserUtils.getLoginUser();
		String userId = loginUser.getId().toString();
		String keywork = loginUser.getKeywork();
		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(userId));
		//获取当前登录用户的所有权限
		List<Map<String,String>> resultList = new ArrayList<>();
		if(areaCode.equals(DistrictEnum.REGION_ADMIN.getCode())){//如果相等就是当前选择的是自治区
			resultList = queryCityDataAndCount(year,areaCode,stats,authoritys,ZLLB,keywork,GCLB); //查询并统计市级数据
		}else{
			//查询并统计县级数据
			resultList = queryCountyDataAndCount(year,areaCode,stats,authoritys,ZLLB,keywork,GCLB);
		}
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

	//查询市级数据并进行统计
	private List<Map<String,String>> queryCityDataAndCount(String year,String areaCode,List<Integer> stats,List<String> authoritys,String ZLLB,String keywork,String GCLB){
		List<Map<String,String>> countDataList = new ArrayList<>();
		List<Map<String,String>> resultList = new ArrayList<>();//数据结果返回页面List
		//查询任务下发的数据 并统计市级数据
		List<YzlMessage> messagesList = messageMapper.queryByAuthoritysAndStatu(authoritys, keywork);
		List<YzlEpcTaskProgress> epcTaskProgressList = new ArrayList<>();
		List<YzlEpcAndTaskStaticti> epcAndTaskStatictiList = new ArrayList<>();
		for (YzlMessage message : messagesList) {
			List<YzlEpcTaskProgress> epcList = epcTaskProgressMapper.queryCityDataByParamGroupByCountyAndGCLBAndZLLBAndJHNDAndZYNDAndMessageCounty(year, areaCode, authoritys,ZLLB,GCLB,message);
			List<YzlEpcAndTaskStaticti> epcAndTaskList = xbMapper.queryCityDataByParamGroupByCityAndGCLBAndZLLBAndJHNDAndZYNDAndMessageCounty(year, stats, authoritys, areaCode, ZLLB, GCLB, message);
			epcTaskProgressList.addAll(epcList);
			epcAndTaskStatictiList.addAll(epcAndTaskList);
		}
		LinkedHashSet<String> hashSet = new LinkedHashSet<>();//存市编号
		for (YzlEpcTaskProgress yzlEpcTaskProgress : epcTaskProgressList) {//不会吧重复的市编号添加进去
			hashSet.add(yzlEpcTaskProgress.getCitycode());
		}
		int record = 0;
		//查询任务完成的数据 并统计市级数据
		try {
			for (String string : hashSet) {//遍历所的市编号
				for (int i = 0; i < epcTaskProgressList.size(); i++) {
					HashMap map = new HashMap<>();
					YzlEpcTaskProgress IepcTaskProgress = epcTaskProgressList.get(i);
					if (string.equals(epcTaskProgressList.get(i).getCitycode())) {//如果跟市编号相等
						List flag = new ArrayList<>();//存储已经添加过的数据id
						HashMap countDataMap = new HashMap<>();
						YzlDistrict district = districtMapper.selectByNumber(IepcTaskProgress.getCountycode());//根据县行政编号查询地区
						map.put("city", district.getCity());
						int log=0;//记录当前任务是否与任务工作中 有相匹配的
						for (int j = 0; j < epcAndTaskStatictiList.size(); j++) {
							YzlEpcAndTaskStaticti JepcAndTaskStaticti = epcAndTaskStatictiList.get(j);
							//把工程 市行政编号和造林类别和工程类别相同的进行合并
							if(IepcTaskProgress.getCitycode().equals(JepcAndTaskStaticti.getCity()) //判断市级行政编号是否相等
									&&IepcTaskProgress.getZllb().equals(JepcAndTaskStaticti.getZLLB()) //判断造林类别是否相等
										&&IepcTaskProgress.getGclb().equals(JepcAndTaskStaticti.getGCLB())){//判断工程类别是否相等
								log++;//有匹配的  则自增
								//获取任务工作完成的数量
								Double XTJSBMJDouble = Double.valueOf(JepcAndTaskStaticti.getXTJSBMJ());
								double zjh =XTJSBMJDouble/IepcTaskProgress.getTaskprogress().doubleValue();
								// 用任务完成数据除以任务发下数量得到  任务完成比例的结果   用结果向上取整保留小数后2位
								map.put("zjh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.format("%.2f", zjh*100)+"%");
								map.put("wc"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.valueOf(XTJSBMJDouble));
							}
						}
						if(log==0){//log 为0 则表示没有任务工作中的数据与其相匹配，则用0补充
							map.put("zjh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), 0);
							map.put("wc"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), 0);
						}
						map.put("cityCode", IepcTaskProgress.getCitycode());//封装市级行政编号			
						//封装市级任务下发的数据 并保留小数点后4位
						map.put("jh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.format("%.4f", IepcTaskProgress.getTaskprogress()));
					}
					
					List flag = new ArrayList<>();//存储已经添加过的数据id
					String filesUrl = IepcTaskProgress.getFilesUrl();//把文件路径进行拆分
					Set<String> FilesUrlSet= new HashSet();//存储文件路径 并去重复
					
					if(!StringUtils.isBlank(filesUrl)){	 
						String[] filesUrlArray = filesUrl.split(",");
						for (String fileUrl : filesUrlArray) {
							if(StringUtils.isBlank(fileUrl)){//判断是否为空文件名
								continue;
							}
							FilesUrlSet.add(fileUrl);
						}
					}
					map.put("fileName", FilesUrlSet);
					map.put("countyCode", IepcTaskProgress.getCountycode());////封装县级行政编号
					map.put("cityCode", IepcTaskProgress.getCitycode());//封装市级行政编号			
					//封装市级任务下发的数据 并保留小数点后4位
					map.put("jh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.format("%.4f", IepcTaskProgress.getTaskprogress()));
					//把所有统计好封装到List集合中
					countDataList.add(map);
//					taskIssuedDTOs.add(map);
					record++;
				}
				int filesNumber = 0;
			}
			
			List<String> messageCountyCodeList= messageMapper.queryCountyCodeByStatuAndCountyCode(authoritys, keywork);//查询更新记录数
			List<Map> AddedMap = new ArrayList<>();//存储 已经合并过的Map  判断是否重复添加
			for (int m=0;m<countDataList.size();m++){
				Map mMap = countDataList.get(m);
				String cityCode = (String) mMap.get("cityCode");
				if(StringUtils.isBlank(cityCode)){
					logger.error("市级行政编号为空");
					throw new YzlException(ResultEnum.CITY_IS_NULL);
				}
				Set<String> mMapFilesSet = (Set<String>) mMap.get("fileName");//获取mMap的文件个数
				if(!AddedMap.contains(mMap)){
					for (int n = m+1; n < countDataList.size(); n++) {
						Map nMap = countDataList.get(n);
						Set<String> nMapFilesSet = (Set<String>) nMap.get("fileName");//获取当前nMap的文件个数
						if(cityCode.equals(nMap.get("cityCode"))){
							mMap.putAll(nMap); //同一个县  则数据进行合并
							//nMap.put("filesUrl", "<a src='' 点击查看("+(mMapFilesNumber+=nMapFilesNumber)+")</a>");//累加 文件个数
							mMapFilesSet.addAll(nMapFilesSet);//去重复
							mMap.put("fileName", mMapFilesSet);//把新filesUrl重新添加
							AddedMap.add(nMap);//把已经合并过的Map 存放到判断List 
						}
					}
					mMap.put("filesUrl", "<a class='uploadEcho'  href='#' value='"+ mMap.get("fileName") +"' >点击查看("+((Set<String>)mMap.get("fileName")).size()+")</a>");//累加 文件个数
					resultList.add(mMap);
				}
			}		
		} catch (Exception e) {
			logger.error("异常信息="+e);
			e.printStackTrace();
		}
		return resultList;
	}
	
	
	//查询县级数据并进行统计
	private List<Map<String,String>> queryCountyDataAndCount(String year,String areaCode,List<Integer> stats,List<String> authoritys,String ZLLB,String keywork,String GCLB){
		List<Map<String,String>> countDataList = new ArrayList<>();
		List<Map<String,String>> resultList = new ArrayList<>();//数据结果返回页面List
		//查询任务下发的数据 并统计县级数据
		List<YzlMessage> messagesList = messageMapper.queryByAuthoritysAndStatu(authoritys, keywork);
		List<YzlEpcTaskProgress> epcTaskProgressList = new ArrayList<>();
		List<YzlEpcAndTaskStaticti> epcAndTaskStatictiList = new ArrayList<>();
		for (YzlMessage message : messagesList) {
			List<YzlEpcTaskProgress> epcList = epcTaskProgressMapper.queryCountyDataByParamGroupByCountyAndGCLBAndZLLBAndJHNDAndZYNDAndMessageCounty(year, areaCode, authoritys,ZLLB,GCLB,message);
			List<YzlEpcAndTaskStaticti> epcAndTaskList = xbMapper.queryCountyDataByParamGroupByCountyAndGCLBAndZLLBAndJHNDAndZYNDAndMessageCounty(year, stats, authoritys, areaCode,ZLLB,GCLB,message);
			epcTaskProgressList.addAll(epcList);
			epcAndTaskStatictiList.addAll(epcAndTaskList);
		}
		//查询任务完成的数据 并统计县级数据
//		taskIssuedDTOs.add(epcTaskProgressList);
		//查询任务完成的数据 并统计市级数据
		
		try {
			for (int i = 0; i < epcTaskProgressList.size(); i++) {
				List flag = new ArrayList<>();//存储已经添加过的数据id
				HashMap countDataMap = new HashMap<>();
				YzlEpcTaskProgress IepcTaskProgress = epcTaskProgressList.get(i);
				YzlDistrict district = districtMapper.selectByNumber(IepcTaskProgress.getCountycode());//根据县行政编号查询地区
				double taskprogress = IepcTaskProgress.getTaskprogress();//获取任务下发的数据量
				countDataMap.put("city", district.getCity());
				countDataMap.put("county", district.getCounty());
				int  log =0;//记录当前任务下发 与 任务工作是否有想匹配的
				for (int j = 0; j < epcAndTaskStatictiList.size(); j++) {
					YzlEpcAndTaskStaticti JepcAndTaskStaticti = epcAndTaskStatictiList.get(j);
					if(IepcTaskProgress.getCountycode().equals(JepcAndTaskStaticti.getCounty()) //判断县级行政编号是否相等
							&&IepcTaskProgress.getZllb().equals(JepcAndTaskStaticti.getZLLB())	//判断造林类别是否相等
								&&IepcTaskProgress.getGclb().equals(JepcAndTaskStaticti.getGCLB())){ //判断工程类别是否相等
						log++;//有匹配的，则自增
						//获取任务下发的数量 
						//获取任务工作完成的数量
						double XTJSBMJDouble = Double.valueOf(JepcAndTaskStaticti.getXTJSBMJ());
						// 用任务完成数据除以任务发下数量得到 占任务完成比例的结果   
						double zjhNumber = XTJSBMJDouble/taskprogress;
						//封装 任务完成量占任务计划量的比例  保留一位小数
						countDataMap.put("stat", JepcAndTaskStaticti.getStat());
						countDataMap.put("zjh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(),  String.format("%.1f", zjhNumber*100)+"%");
						countDataMap.put("wc"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.valueOf(XTJSBMJDouble));
					}
				}
				if(log==0){//log 为0 则表示没有任务工作中的数据与其相匹配，则用0补充
					countDataMap.put("zjh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(),  0);
					countDataMap.put("wc"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), 0);
				}
				countDataMap.put("cityCode", IepcTaskProgress.getCitycode());//封装市级行政编号
				String filesUrl = IepcTaskProgress.getFilesUrl();//把文件路径进行拆分
				Set<String> FilesUrlSet= new HashSet();//存储文件路径 并去重复
				if(!StringUtils.isBlank(filesUrl)){
					 String[] filesUrlArray = filesUrl.split(",");//拆分文件
					for (String fileUrl : filesUrlArray) {
						if(StringUtils.isBlank(fileUrl)){
							continue;
						}
						FilesUrlSet.add(fileUrl);//添加文件路径
					}
				}
				countDataMap.put("fileUrl", FilesUrlSet);
				//countDataMap.put("filesNumber", FilesUrlSet.size());//封装市级行政编号
				//countDataMap.put("filesUrl", "<a href='www.baidu.com' >点击查看("+FilesUrlSet.size()+")</a>");//累加 文件个数
				countDataMap.put("countyCode", IepcTaskProgress.getCountycode());////封装县级行政编号
				//封装县级任务下发的数据并 保留小数点后4位
				countDataMap.put("jh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.format("%.4f", taskprogress));
				countDataList.add(countDataMap);
			}
			
			List<Map> AddedMap = new ArrayList<>();//存储 已经合并过的Map  判断是否重复添加
			for (int m=0;m<countDataList.size();m++){//遍历List已统计好的数据 进行同县合并
				Map mMap = countDataList.get(m);
				if(!AddedMap.contains(mMap)){
					Map resultMap = new HashMap<>();
					Set<String> mMapFilesUrlSet = (Set<String>) mMap.get("fileUrl");//获取mMap的文件个数
					for (int n = m+1; n < countDataList.size(); n++) {
						Map nMap = countDataList.get(n);
						Set<String> nMapFilesUrlSet = (Set<String>) nMap.get("fileUrl");//获取mMap的文件个数
						String countyCode = (String) mMap.get("countyCode");
						if(StringUtils.isBlank(countyCode)){
							logger.error("县级行政编号为空");
							throw new YzlException(ResultEnum.COUNTY_IS_NULL);
						}
						if(countyCode.equals(nMap.get("countyCode"))){
							mMap.putAll(nMap); //同一个县  则数据进行合并
							//nMap.put("filesUrl", "<a src='' 点击查看("+(mMapFilesNumber+=nMapFilesNumber)+")</a>");//累加 文件个数
							mMapFilesUrlSet.addAll(nMapFilesUrlSet);//去重复
							mMap.put("fileUrl", mMapFilesUrlSet);//把新filesUrl重新添加
							AddedMap.add(nMap);//把已经合并过的Map 存放到判断List 
						}
					}
					mMap.put("filesUrl", "<a class='uploadEcho'   href='javascript:void(0);' value='"+ mMap.get("fileUrl") +"'  >点击查看("+((Set<String>)mMap.get("fileUrl")).size()+")</a>");//累加 文件个数
					resultList.add(mMap);
				}
			}
		} catch (Exception e) {
			logger.error("异常信息="+e);
			e.printStackTrace();
		}
		return resultList;
	}
	
	
}
