package com.yzl.planManagementService.Impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yzl.planManagementService.*;
import com.yzl.distriEpcTaskService.impl.TaskIssuedServiceImpl;
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
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlTaskExample;
import com.yzl.pojo.YzlTaskExample.Criteria;
import com.yzl.pojo.YzlUser;
import com.yzl.utils.AdministrativeCode;
import com.yzl.utils.CountZLLBDTO;
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
public class BacklogXbServiceImpl implements BacklogXbService{
	
	//日志类
	private static Logger logger = Logger.getLogger(BacklogXbServiceImpl.class);
	
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
	public List<YzlTask> getTableHeader(String year, String disCode, String GCLB,String ZLLB,String statu) {
		List<YzlTask> resultTaskList = new ArrayList<>();//返回结果的taskList
		YzlUser loginUser = LoginUserUtils.getLoginUser();
		List<String> authoritys = LoginUtils.getLoginUserAuthority(menuMapper);
		String keywork = loginUser.getKeywork();
		List<String> messageCountys = messageMapper.queryDISTINCTCountyCodeByStatuAndCountyCode(authoritys, keywork);
		List<String> stats = new ArrayList<>();
		stats.add(statu);//添加要出查询的状态
		List<YzlTask> taskList = taskMapper.queryDISTINCTEnameAndMarkFormXbTableByZYNDAndCountyCode(year, disCode, authoritys, stats, ZLLB);
		for (YzlTask task : taskList) {
			String mark = task.getMark();//获取造林类别
			//根据权限 地区行政编号，年份，造林类别。 查询任务下发中的 epc
			List<YzlEpc> epcList = epcMapper.queryDISTINCTEnameAndMarkFormXbTableByZYNDAndCountyCode(year, disCode, authoritys, ZLLB, stats);
			List<YzlEpc> resultEpcList = new ArrayList<>();//返回结果的子节点epcList
			for (YzlEpc epc : epcList) {
				epc.setField(ZLLB+"T"+epc.getMark());
				resultEpcList.add(epc);
			}
			task.setList(resultEpcList);//把工程list封装到task中
			resultTaskList.add(task);//把任务封装到返回的数组中
		}
		return resultTaskList;
	}

	
	@Override
	public EasyUIResult queryTaskData(int page, int rows, String year, String areaCode, String ZLLB, String GCLB,String statu) {

		if(StringUtils.isBlank(year)){//如果年份等于 空 则获取当前时间的年份
			year = new SimpleDateFormat("yyyy").format(new Date());
		}
		if(StringUtils.isBlank(areaCode)){//如果地区为空 则抛出异常
			logger.error("需要查询的地区行政编号为空-- areaCode="+areaCode);
			throw new YzlException(ResultEnum.REGION_ID_ISNULL);
		}
		YzlUser loginUser = LoginUserUtils.getLoginUser();
		String userId = loginUser.getId().toString();
		String keywork = loginUser.getKeywork();
		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(userId));
		List<Integer> stats = new ArrayList<>();
		if(StringUtils.isNotBlank(statu)){
			stats.add(Integer.valueOf(statu));
		}
		//获取当前登录用户的所有权限
		List<Map<String,String>> resultList = new ArrayList<>();
		List<String> messageCountys = new ArrayList<>();
		messageCountys = messageMapper.queryDISTINCTCountyCodeByStatuAndCountyCode(authoritys, keywork);
		if(areaCode.equals(DistrictEnum.REGION_ADMIN.getCode())){//如果相等就是当前选择的是自治区
			resultList = queryCityDataAndCount(year,areaCode,stats,authoritys,ZLLB,keywork,GCLB,messageCountys); //查询并统计市级数据
		}else{
			//查询并统计县级数据
			resultList = queryCountyDataAndCount(year,areaCode,stats,authoritys,ZLLB,keywork,GCLB,messageCountys);
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
	private List<Map<String,String>> queryCityDataAndCount(String year,String areaCode,List<Integer> stats,List<String> authoritys,String ZLLB,String keywork,String GCLB,List<String> messageCountyList){
		List<Map<String,String>> countDataList = new ArrayList<>();
		List<Map<String,String>> resultList = new ArrayList<>();//数据结果返回页面List
		//查询任务下发的数据 并统计市级数据
		List<YzlEpcTaskProgress> epcTaskProgressList = epcTaskProgressMapper.queryCityData(year, areaCode, authoritys, ZLLB, GCLB,stats);
		LinkedHashSet<String> hashSet = new LinkedHashSet<>();//存市编号
		for (YzlEpcTaskProgress yzlEpcTaskProgress : epcTaskProgressList) {//不会吧重复的市编号添加进去
			hashSet.add(yzlEpcTaskProgress.getCitycode());
		}
		int record = 0;
		//查询任务完成的数据 并统计市级数据
		List<YzlEpcAndTaskStaticti> epcAndTaskStatictiList = xbMapper.queryCityDataByParamGroupByCityAndGCLBAndZLLBAndJHNDAndZYND(year, stats, authoritys, areaCode, ZLLB, GCLB);
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
										&&IepcAndTaskStaticti.getGCLB().equals(JepcTaskProgress.getGclb()) && IepcAndTaskStaticti.getStat().equals(JepcTaskProgress.getStat())){//判断工程类别是否相等
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
		return resultList;
	}
	
	
	//查询县级数据并进行统计
	private List<Map<String,String>> queryCountyDataAndCount(String year,String areaCode,List<Integer> stats,List<String> authoritys,String ZLLB,String keywork,String GCLB,List<String> messageCountyList){
		List<Map<String,String>> countDataList = new ArrayList<>();
		List<Map<String,String>> resultList = new ArrayList<>();//数据结果返回页面List
		//查询任务下发的数据 并统计县级数据
		List<YzlEpcTaskProgress> epcTaskProgressList = epcTaskProgressMapper.queryCountyData(year, areaCode, authoritys, ZLLB, GCLB,stats);
		//查询任务完成的数据 并统计县级数据
//		taskIssuedDTOs.add(epcTaskProgressList);
		//查询任务完成的数据 并统计市级数据
		List<YzlEpcAndTaskStaticti> epcAndTaskStatictiList = xbMapper.queryCountyDataByParamGroupByCountyAndGCLBAndZLLBAndJHNDAndZYND(year, stats, authoritys, areaCode, ZLLB, GCLB);
		try{
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
					
					String Jepcounty = JepcTaskProgress.getCountycode();
					String Jezllb = JepcTaskProgress.getZllb();
					String Jegclb = JepcTaskProgress.getGclb();
					String Jestat = JepcTaskProgress.getStat();
					
					String Iepcounty = IepcAndTaskStaticti.getCounty();//县
					String Iezllb = IepcAndTaskStaticti.getZLLB();
					String Iegclb = IepcAndTaskStaticti.getGCLB();
					String Iestat = IepcAndTaskStaticti.getStat();
					
					if(Iepcounty.equals(JepcTaskProgress.getCountycode()) //判断县级行政编号是否相等
							&& Iezllb.equals(JepcTaskProgress.getZllb())	//判断造林类别是否相等
								&& Iegclb.equals(JepcTaskProgress.getGclb()) && Iestat.equals(JepcTaskProgress.getStat())){ //判断工程类别是否相等
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
				countDataMap.put("stat", IepcAndTaskStaticti.getStat());
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
			
			
/*			for (int i = 0; i < epcTaskProgressList.size(); i++) {
				List flag = new ArrayList<>();//存储已经添加过的数据id
				HashMap countDataMap = new HashMap<>();
				YzlEpcTaskProgress IepcTaskProgress = epcTaskProgressList.get(i);
				YzlDistrict district = districtMapper.selectByNumber(IepcTaskProgress.getCountycode());//根据县行政编号查询地区
				double taskprogress = IepcTaskProgress.getTaskprogress();//获取任务下发的数据量
				countDataMap.put("city", district.getCity());
				countDataMap.put("county", district.getCounty());
				for (int j = 0; j < epcAndTaskStatictiList.size(); j++) {
					YzlEpcAndTaskStaticti JepcAndTaskStaticti = epcAndTaskStatictiList.get(j);
					if(IepcTaskProgress.getCountycode().equals(JepcAndTaskStaticti.getCounty()) //判断县级行政编号是否相等
							&&IepcTaskProgress.getZllb().equals(JepcAndTaskStaticti.getZLLB())	//判断造林类别是否相等
								&&IepcTaskProgress.getGclb().equals(JepcAndTaskStaticti.getGCLB())){ //判断工程类别是否相等
						//获取任务下发的数量 
						//获取任务工作完成的数量
						double XTJSBMJDouble = Double.valueOf(JepcAndTaskStaticti.getXTJSBMJ());
						// 用任务完成数据除以任务发下数量得到 占任务完成比例的结果   
						double zjhNumber = XTJSBMJDouble/taskprogress;
						//封装 任务完成量占任务计划量的比例  保留一位小数
						countDataMap.put("zjh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(),  String.format("%.1f", zjhNumber*100)+"%");
						countDataMap.put("wc"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.valueOf(XTJSBMJDouble));
					}
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
				countDataMap.put("particulars", "<a class='ptl'  href='#' value='"+"' >详情</a>");
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
			}*/
		} catch (Exception e) {
			logger.error("异常信息="+e);
			e.printStackTrace();
		}
		return resultList;
	}
	
	
}
