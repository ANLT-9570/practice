package com.yzl.planManagementService.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.mapper.YzlEpcTaskProgressMapper;
import com.yzl.mapper.YzlLogMapper;
import com.yzl.mapper.YzlMonitoringstatisticsMapper;
import com.yzl.mapper.YzlTaskMapper;
import com.yzl.mapper.YzlXbMapper;
import com.yzl.planManagementService.TaskWorkingService;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlDistrictExample;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlLog;
import com.yzl.pojo.YzlLogExample;
import com.yzl.pojo.YzlLogExample.Criteria;
import com.yzl.pojo.YzlMenu;
import com.yzl.pojo.YzlProceed;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlUser;
import com.yzl.pojo.YzlXb;
import com.yzl.utils.CheckStringIsNumberUtils;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlEpcAndTaskStaticti;
import com.yzl.utils.YzlResult;

@Transactional
@Service
public class TaskWorkingServiceImpl implements TaskWorkingService{

	@Autowired
	private YzlXbMapper XbMapper;
	
	@Autowired
	private YzlMonitoringstatisticsMapper monitoringstatisticsMapper;
	
	@Autowired
	private YzlDistrictMapper districtMapper;	
	
	@Autowired
	private YzlTaskMapper taskMapper;
	
	@Autowired
	private YzlLogMapper logMapper;
	
	@Autowired
	private YzlEpcTaskProgressMapper epcTaskProgressMapper;
	//查询表头
	@Override
	public List<YzlTask> taskTab(String year, String disCode,String zllb,String stat) {
//		System.out.println(stat);·
		if(zllb.equals("10")) {
			zllb = null;
		}
		//要查询的状态
		List<String> stats = new ArrayList<>();
		if (stat == null || stat.equals("undefined") || stat.equals("[object Object]") ) {
			stats.add("0");
			stats.add("1");
			stats.add("3");
		}else {
			stats.add(stat);
		}
		//获取用户对应的全蝎
		List<String> menu = getMenu();
		//查询审核完成的表头
//		List<YzlTask> tasks = XbMapper.selectEpcTabName(stats,year,disCode,menu,zllb);
		//查询下发的表头
		List<YzlTask> tasks = XbMapper.selectByTaskIssuedTableHead(year, disCode, zllb, menu,stats);
		
		for (YzlTask task : tasks) {
			String tcode = task.getMark();
			//查询这个造林类别拥有的工程
			//List<YzlEpc> epcs = XbMapper.selectByEpcPossessTask(tcode,year,stats,disCode,menu,zllb);
			List<YzlEpc> epcs = XbMapper.selectByZllb(tcode,year,disCode,menu,zllb,stats);
//					HashMap<String, String> hashMap = new HashMap<>();
			for (YzlEpc yzlEpc : epcs) {//把造林类别和工程拼在一起
				yzlEpc.setField(tcode+"T"+yzlEpc.getMark());
			}
			if (epcs.size()>0) {
				task.setList(epcs);
//						epcAndTask.add(hashMap);
			}
		}
		return tasks;
	}
	
	//数据展示
	@Override
	public EasyUIResult epcTaskData(String year, String disCode, String zllb,Integer page, Integer rows,String stat,String proceed) {
		
		if(zllb.equals("10")) {
			zllb = null;
		}
		//要查询的状态
		List<String> stats = new ArrayList<>();
		if (stat == null || stat.equals("undefined") || stat.equals("[object Object]") ) {
			stats.add("0");
			stats.add("1");
			stats.add("3");
		}else {
			stats.add(stat);
		}
		
		List<Map<String,String>> list = new ArrayList<>();
		
		//获取用户对应的全蝎
		List<String> menu = getMenu();
		
		
		if (disCode.equals("null") || disCode.equals("GX450")) {//查询的是自治区
//			citys = XbMapper.selectByCityAndTimeAndStat(year, stats,menu);
			list = selectByCityAndCount(year,stats,menu,disCode,zllb);
			
		}else {//查询的是这个市或县
//			citys = XbMapper.selectByCityAndTimeAndStatCountyAndCity(year, stats,disCode,menu);
			list = selectByCountyCount(year,stats,menu,disCode,zllb,proceed);
		}
		
		//起始 等于 当前页-1乘以每页记录数
		int page2 = (page-1)*rows;
		
		//如果集合中的数据没有分页的多久把集合的大小给分页
		if(rows>list.size()) {
			rows = list.size();
		}
		List<Map<String,String>> subList = new ArrayList<>();
		//截取
		if (list.size()<10) {
			subList = list;
		}else {
			subList = list.subList(page2, rows);
		}
		
		EasyUIResult result = new EasyUIResult();
		result.setRows(subList);
		result.setTotal(list.size());
		
		return result;
	}
	
	//对县的统计和查询
	private List<Map<String, String>> selectByCountyCount(String year, List<String> stats, List<String> menu,String disCode,String zllb,String proceed) {
		//统计县的任务下发
		List<YzlEpcTaskProgress> epcTaskProgresses = XbMapper.selectByCountyCountyTaskIssued(year,menu,disCode,zllb,stats,proceed);
		LinkedHashSet<String> hashSet = new LinkedHashSet<>();//装县的集合不含重复
		
		for (YzlEpcTaskProgress yzlEpcTaskProgress : epcTaskProgresses) {
			hashSet.add(yzlEpcTaskProgress.getCountycode());
		}
		List<Map<String,String>> lists = new ArrayList<>();//返回的数据
		
		//查询任务完成的数据并统计市级数据
		List<YzlXb> xbs = XbMapper.selectByCityComplation(year, stats, menu, disCode,zllb,proceed);
		
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
					hashMap.put("zllb", yzlEpcTaskProgress.getZllb());
					hashMap.put("proceeding", "<a id='pro'  href='#' value='"+"' ><div id='pros' onmouseout='outs()' onmouseover='overs()' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><div>事项 </div> </div>  </a>");
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
				hashMap.put("zllb", yzlEpcTaskProgress.getZllb());
				hashMap.put("proceeding", "<a id='pro'  href='#' value='"+"' ><div id='pros' onmouseout='outs()' onmouseover='overs()' style='height:25px;width:70px;margin-left:12px;margin-top:5px'><div>事项</div> </div>  </a>");
			}
			flag = 0;
			lists.add(hashMap);
		}
		
		List<Map<String,String>> countyList = new ArrayList<>();
		for (String countyCode : hashSet) {//县的编号
			
			Integer back = 0;
			Integer submit = 0;
			Integer audit = 0;
			
			LinkedHashSet<String> backList = new LinkedHashSet<>();
			LinkedHashSet<String> submitList = new LinkedHashSet<>();
			LinkedHashSet<String> auditList = new LinkedHashSet<>();
			
			HashMap<String, String> hashMap = new HashMap<>();
			for (Map<String, String> map : lists) {//
				
				String string2 = map.get("countycode");
				if (countyCode.equals(string2)) {
					Set<Entry<String,String>> entrySet = map.entrySet();
					for (Entry<String, String> entry : entrySet) {
						if (entry.getKey().equals("stat")) {
							switch (entry.getValue()) {
							case "0":
								int x=0;
								String backString = map.get("zllb");//造林类别的编号
								for (String string : backList) {
									if (backString.equals(string)) {
										x++;
									}
								}
								if (x==0) {
									submit++;
								}
								backList.add(backString);
								break;
							case "1":
								int y = 0;
								String submitString = map.get("zllb");//造林类别的编号
								for (String string : submitList) {
									if (submitString.equals(string)) {
										y++;
									}
								}
								if (y==0) {
									audit++;	
								}
								submitList.add(submitString);		
								break;
							case "3":
								int z = 0;
								String auditString = map.get("zllb");//造林类别的编号
								for (String string : auditList) {
									if (auditString.equals(string)) {
										z++;
									}
								}
								if (z==0) {
									back++;	
								}
								auditList.add(auditString);	
								break;

							default:
								break;
							}
						}
						hashMap.put(entry.getKey(), entry.getValue());
					}
				}
			}
			hashMap.put("back", back.toString());
			hashMap.put("submit", submit.toString());
			hashMap.put("audit", audit.toString());
			
			countyList.add(hashMap);
			 back = 0;
			 submit = 0;
			 audit = 0;
		}
		return countyList;
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
					hashMap.put("proceeding", "<a id='pro'  href='#' value='"+"' ><div id='pros' onmouseout='outs()' onmouseover='overs()'><div><li>事项   <ul><a href='#'><li>被退回<li><li>待提交</li><li>待审核</li></a></ul> </li></div> </div>  </a>");
					break;
				}
			}
			if (flag == 0) {
				hashMap.put("citycode", epcTaskProgress.getCitycode());
				hashMap.put("stat", epcTaskProgress.getStat());
				hashMap.put("jh"+epcTaskProgress.getZllb()+"Y"+epcTaskProgress.getGclb(), String.format("%.2f", float1));//计划
				hashMap.put("city", list.get(0).getCity());//市
				hashMap.put("wc"+epcTaskProgress.getZllb()+"Y"+epcTaskProgress.getGclb(), "0");
				hashMap.put("zjh"+epcTaskProgress.getZllb()+"Y"+epcTaskProgress.getGclb(), "0");//占计划 等于 完成的除以计划
				hashMap.put("particulars", "<a class='ptl'  href='#' value='"+"' >详情</a>");
				hashMap.put(epcTaskProgress.getZllb()+"T"+epcTaskProgress.getGclb(), "0");
				hashMap.put("proceeding", "<a id='pro'  href='#' value='"+"' ><div id='pros' onmouseout='outs()' onmouseover='overs()'><div><li>事项   <ul><a href='#'><li>被退回<li><li>待提交</li><li>待审核</li></a></ul> </li></div> </div>  </a>");
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
		
		return disList;
	}
	
	
	private List<HashMap<String, String>> municipality(List<YzlEpcAndTaskStaticti> YzlEpcAndTaskStaticti,String disCode){
		
		List<String> stats = new ArrayList<>();
		stats.add("0");
		stats.add("1");
		stats.add("3");
		
		List<HashMap<String, String>> hashMaps = new ArrayList<>();
		HashMap<String, String> hashMap = null;
		String cityCode = null;
		String zllb = null;
		String gclb = null;
		String county2 = null;
		String jhnd = null;
		String zynd = null;
		String stat = null;
		String city = null;
		String ccty = null;
		
		String ct = null;
		YzlEpcTaskProgress plan = null;
		YzlEpcAndTaskStaticti epcAndTaskStaticti = null;
		String XTJSBMJ = null;
		String jh = null;
		Float zjh = null;
		for (YzlEpcAndTaskStaticti yzlEpcAndTaskStaticti2 : YzlEpcAndTaskStaticti) {//遍历
			
			 hashMap = new HashMap<>();
			 cityCode = yzlEpcAndTaskStaticti2.getCity();//获取市的行政编号
			 zllb = yzlEpcAndTaskStaticti2.getZLLB();//造林类别
			 gclb = yzlEpcAndTaskStaticti2.getGCLB();//工程类别
			 county2 = yzlEpcAndTaskStaticti2.getCounty();//县的编号
			 jhnd = yzlEpcAndTaskStaticti2.getJHND();//计划年度
			 zynd = yzlEpcAndTaskStaticti2.getZYND();//作业年度
			 stat = yzlEpcAndTaskStaticti2.getStat();//状态
			 city = yzlEpcAndTaskStaticti2.getCity();
			
			hashMap.put("stat", stat);
			
 			 ct = XbMapper.selectByCity(cityCode);//根据市编号查询市名称
			
			if (yzlEpcAndTaskStaticti2.getCounty()!=null && !disCode.equals("null") && !disCode.equals("GX450")) {
				String county = yzlEpcAndTaskStaticti2.getCounty();
				
				YzlDistrict district = districtMapper.selectByNumber(county);
				
				hashMap.put("county", district.getCounty());
			}
			
			//查出这个造林类别今年的计划任务是多少
			if(disCode.equals("null") || disCode.equals("GX450")) {
				ccty = city;
			}else {
				ccty = county2;
			}//查出这个造林类别今年的计划任务是多少
			 plan = XbMapper.selectByPlanTask(zllb,gclb,ccty,jhnd,zynd);
			
			if (plan!=null) {
				jh = plan.getTaskprogress().toString();
			}else {
				jh = "0.00";
			}
			
			hashMap.put("jh"+zllb+"Y"+gclb, jh);//计划
			
			//查出这个状态造林类别今年完成了多少
			 epcAndTaskStaticti = XbMapper.selectByCompleteTask(zllb,gclb,ccty,jhnd,zynd,stats);
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
			
					
			hashMap.put("zjh"+zllb+"Y"+gclb, String.format("%.2f", zjh));//占计划 等于 完成的除以计划
			hashMap.put("city", ct);//市
			hashMap.put(yzlEpcAndTaskStaticti2.getZLLB()+"T"+yzlEpcAndTaskStaticti2.getGCLB(), yzlEpcAndTaskStaticti2.getXTJSBMJ());
			//"<a class='uploadEcho'  href='#' value='"+ mMap.get("fileName") +"' >点击查看("+((Set<String>)mMap.get("fileName")).size()+")</a>"
			//particulars
			hashMap.put("particulars", "<a class='ptl'  href='#' value='"+"' >详情</a>");
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
			return map;
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
					
					permsList.add(perms);
				}
			}
			
			return permsList;
		}

	
	@Override
	public YzlDistrict Ddis(String dcode) {
		YzlDistrict district = districtMapper.selectByPrimaryKey(Integer.valueOf(dcode));
		return district;
	}

	
	//查询所有的造林类别
	@Override
	public List<YzlTask> show_task() {
		List<YzlTask> list = taskMapper.select();
		return list;
	}
	
	@Override
	public List<List<YzlXb>> checkOut(String eids,String year,String [] countys,String [] bdgwj,String [] json) {
		
		String[] split = eids.split(",");
		
		LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
		
		for (String string : split) {
			linkedHashSet.add(string);
		}
		
		List<List<YzlXb>> list = new ArrayList<>();
		for (String county : countys) {
			for (String eic : linkedHashSet) {
				List<YzlXb> yzlXbs = XbMapper.selectByEidAndTime(eic,year,county);
				if (yzlXbs!=null && yzlXbs.size()>0) {
					list.add(yzlXbs);
				}
			}
		}
		return list;
	}

	/**
	 * 日志查询
	 */
	@Override
	public EasyUIResult findLog(String row,String time,String county,Integer page,Integer rows,String zllb) {
		
		//根据县名称查询
		YzlDistrict district = new YzlDistrict();
		if (county.length() >= 6) {
			YzlDistrictExample example = new YzlDistrictExample();
			com.yzl.pojo.YzlDistrictExample.Criteria criteria = example.createCriteria();
			criteria.andAnumberEqualTo(county);
			List<YzlDistrict> list = districtMapper.selectByExample(example);
			district = list.get(0);
		}else {
			district = districtMapper.selectByCounty(county);
		}
		String anumber = null;
		if (district!=null) {
			anumber = district.getAnumber();//县的编号
		}else {
			anumber = county;
		}
		
		HashSet<String> hashSet = new HashSet<>();
		String[] split = row.split(",");//字符串分割
		for (String string : split) {
			
			int indexOf = string.indexOf("T");//获取字符的下标索引
			String substring = string.substring(0, indexOf);//得到T字符前面的数字
			hashSet.add(substring);
		}
		
		StringBuffer bf = new StringBuffer();
		Iterator<String> iterator = hashSet.iterator();//遍历hashSet集合
		while (iterator.hasNext()) {
			String next = iterator.next();
			bf.append(next+",");
		}
		
//		YzlLogExample example = new YzlLogExample();
//		example.setOrderByClause("operate_time DESC");
//		Criteria criteria = example.createCriteria();//设置查询条件
//		criteria.andDcodeEqualTo(anumber);
//		criteria.andYearEqualTo(time);
//		criteria.andGclbEqualTo(bf.toString());
//		criteria.andZllbNotLike(zllb);
//		criteria.andGclbNotLike(zllb);
//		List<YzlLog> list = logMapper.selectByExample(example);//查询出来的数据
		List<YzlLog> list = logMapper.findLog(anumber,time,zllb);
		//起始 等于 当前页-1乘以每页记录数
		int page2 = (page-1)*rows;
		
		//如果集合中的数据没有分页的多久把集合的大小给分页
		if(rows>list.size()) {
			rows = list.size();
		}
		List<YzlLog> pages = new ArrayList<>();
		//截取
		if (list.size()<10) {
			pages = list;
		}else if(page2+rows >= list.size()) {
			pages = list.subList(page2, list.size());
		}else {
			pages = list.subList(page2, page2+rows);
		}
		
		//遍历页面显示日志
		for (YzlLog yzlLog : pages) {
			yzlLog.setDcode(district.getCounty());
			String filename = yzlLog.getFilename();
			if (filename != null) {
//				String substring2 = null;
//				String [] file = new String[10];
				List<String> list2 = new ArrayList<>();
				//d/20190317/呵呵呵呵呵
				String[] split2 = filename.split(",");
				
				for (int i = 0;i<split2.length;i++) {
					int indexOf = split2[i].indexOf("/");
					String substring = split2[i].substring(indexOf+1, split2[i].length());//取得第一个斜杠的后部分
					int indexOf2 = substring.indexOf("/");
					String substring2 = substring.substring(indexOf2+1, substring.length());//取得第二个斜杠的后部分
					list2.add("<a class='ptl'  href='#' >"+substring2+"</a>");
				}
				String string = list2.toString();
				String substring = string.substring(1, string.length()-1);
				yzlLog.setFilename(substring);
			}
			yzlLog.setHi(filename);
			String gclb = yzlLog.getGclb();//得到工程类别
			String[] split2 = gclb.split(",");//切割
			StringBuffer buffer = new StringBuffer();
			
			for (String string : split2) {
				YzlTask task = taskMapper.selectByMark(string);//根据工程编号查询
				buffer.append(task.getTname()+",");
			}
			yzlLog.setGclb(buffer.toString());
			//yzlLog.setDcode(county);
		}
		
		EasyUIResult result = new EasyUIResult();
		result.setRows(pages);
		result.setTotal(list.size());
		return result;
	}


	/**
	 *文件上传
	 * @throws Exception 
	 */
	@Override
	public YzlResult upFile(MultipartFile[] fileName,HttpServletRequest request) {
		
		//生成uuid
		UUID uuid = UUID.randomUUID();
		
		InputStream is = null;
		String filename = null;
		for (MultipartFile multipartFile : fileName) {
				try {
					is = multipartFile.getInputStream();
					//得到文件的输入流
					System.out.println("inputStream=="+is);
					String contentType = multipartFile.getContentType();
					System.out.println("contentType=="+contentType);
					String name = multipartFile.getName();//得到参数名称
					System.out.println("name=="+name);
					filename = multipartFile.getOriginalFilename();//得到上传的文件名称
					System.out.println("originalFilename=="+filename);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
		//获取上传的路径
		String path = request.getServletContext().getRealPath("/upload");

		//获取当前的日期,利用当前的日期当文件夹
		Date date = new Date();
		//日期转字符串
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMdd");
		String format = simpleDateFormat.format(date);
		path+="/"+format;
		
		File file = new File(path);
		
		//判断上传的目录是否存在
		if (!file.exists() && !file.isDirectory()) {
			//没有就创建
			file.mkdirs();
		}
		
			FileOutputStream os;
			try {
				os = new FileOutputStream(file+"\\"+uuid.toString()+"="+filename);//文件名前面加uuid区分
				//创建一个缓冲区
				byte bte [] = new byte[1024]; 
				
				//判断输入流中的数据是否已经读取完
				int len = 0;
//				int read = is.read(bte);
				//将输入流读入到缓冲区中
				while ((len = is.read(bte)) > 0) {
					os.write(bte, 0, len);
				}
				request.getSession().setAttribute("filename",uuid.toString()+"/"+format+"/"+filename);
				//关闭输入流
				is.close();
				//关闭输出流
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
				return new YzlResult(400);
			}
		
		YzlResult result = YzlResult.build(200, uuid.toString()+"/"+format+"/"+filename,200);
			
		return result;
	}


	//文件下载
	@Override
	public YzlResult takWorkingDeriveFile(String file,HttpServletRequest request,HttpServletResponse response) {
		
		String userAgent = request.getHeader("User-Agent");
		
		String[] split = file.split(",");
		for (String string : split) {
			//4b9c5ff6-5b98-4975-876a-24d668c9dd81/20190317/新建 Microsoft Word 文档.docx
			int indexOf = string.indexOf("/");
			//获取前面的uuid
			String uuid = string.substring(0, indexOf);
			
			String fp = string.substring(indexOf+1,string.length());
			int indexOf2 = fp.indexOf("/");
			
			//截取文件夹的名称  就是这串日期 20190317
			String FilePath = fp.substring(0, indexOf2);
			//文件名称   新建 Microsoft Word 文档.docx
			String fileName = fp.substring(indexOf2+1, fp.length());
			
			//获取下载的路径
			String realPath = request.getServletContext().getRealPath("/upload"+"/"+FilePath);
			
			File file2 = new File(realPath);
			//判断文件路径是否存在
			if (!file2.exists() && !file2.isDirectory()) {
				return new YzlResult("文件路径不存在！！");
			}
			
			//设置文件的输入流
			try {
				FileInputStream fis = new FileInputStream(realPath+"\\"+uuid+"="+fileName);
				
				if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {//判断是否为ie浏览器
					fileName = java.net.URLEncoder.encode(fileName, "UTF-8"); 
				}else {
					fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
				}
				
				// 3.设置两个头和一个流
				// 动态获得文件的MIME的类型:
				String mimeType = request.getServletContext().getMimeType(fileName);
				response.setContentType(mimeType);
				// 设置Content-Dispostion:
				response.setCharacterEncoding("utf-8");
				response.setHeader("content-disposition", "attachment;filename="+fileName);
				response.setContentType("application/x-download;");
				//获取的一个向浏览器输出的流
				ServletOutputStream os = response.getOutputStream();
				
				//创建一个缓冲区
				byte bte [] = new byte[1024]; 
				
				//判断输入流中的数据是否已经读取完
				int len = 0;
//				int read = is.read(bte);
				//将输入流读入到缓冲区中
				while ((len = fis.read(bte)) > 0) {
					os.write(bte, 0, len);
				}
				fis.close();
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		System.out.println(file);
		return new YzlResult(200);
	}

	@Override
	public YzlResult delFile(String msg,HttpServletRequest request) {
		
		if(msg != null) {
			//4b9c5ff6-5b98-4975-876a-24d668c9dd81/20190317/新建 Microsoft Word 文档.docx
			int indexOf = msg.indexOf("/");
			//uuid
			String uuid = msg.substring(0, indexOf);//获取这一部分 4b9c5ff6-5b98-4975-876a-24d668c9dd81
			
			String substring2 = msg.substring(indexOf+1, msg.length());//获取后面这段 20190317/新建 Microsoft Word 文档.docx
			int indexOf2 = substring2.indexOf("/");
			//文件夹的名称
			String folderName = substring2.substring(0, indexOf2);//获取这个 20190317
			
			//文件名
			String fileName = substring2.substring(indexOf2+1, substring2.length());
			
			//获取文件的路径
			String realPath = request.getServletContext().getRealPath("/upload"+"/"+folderName);
			
			File file = new File(realPath+"/"+uuid.toString()+"="+fileName);
			boolean delete = file.delete();
			if (delete) {
				return new YzlResult(200);
			}
		}
		return new YzlResult(400);
	}

	@Override
	public YzlDistrict code(String disCode) {
		YzlDistrict district = districtMapper.selectByPrimaryKey(Integer.valueOf(disCode));
		return district;
	}

	@Override
	public YzlDistrict findByFlag(String flag) {
		YzlDistrict district = districtMapper.selectByFlag(flag);
		return district;
	}

	@Override
	public List<YzlProceed> proceed(String[] zllbs,String county,String year) {
		
		YzlDistrict district = districtMapper.selectByCounty(county);
		
		List<String> stats = new ArrayList<>();
		stats.add("0");
		stats.add("1");
		stats.add("3");
		
		LinkedHashSet<String> hashSet = new LinkedHashSet<>();
		
		for (String string : zllbs) {
			hashSet.add(string);
		}
		
		List<YzlProceed> proceeds = new ArrayList<>();
		
		for (String zllb : hashSet) {
			List<YzlEpcTaskProgress> epcTaskProgresses = epcTaskProgressMapper.selectByProceed(zllb,district.getAnumber(),year,stats);
			for (YzlEpcTaskProgress yzlEpcTaskProgress : epcTaskProgresses) {
				String zllb2 = yzlEpcTaskProgress.getZllb();
				YzlTask task = taskMapper.selectByMark(zllb2);
				YzlProceed proceed = new YzlProceed();
				proceed.setName(task.getTname());
				proceed.setStat(yzlEpcTaskProgress.getStat());
				proceed.setNameAnumber(task.getMark());
				proceed.setZllb(yzlEpcTaskProgress.getZllb());
				proceeds.add(proceed);
			}
		}
		return proceeds;
	}

}
