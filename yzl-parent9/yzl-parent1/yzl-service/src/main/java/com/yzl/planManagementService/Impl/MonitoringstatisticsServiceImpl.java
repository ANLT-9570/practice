package com.yzl.planManagementService.Impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.mapper.YzlEpcMapper;
import com.yzl.mapper.YzlEpcTaskProgressMapper;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.mapper.YzlMonitoringstatisticsMapper;
import com.yzl.mapper.YzlTaskMapper;
import com.yzl.mapper.YzlXbMapper;
import com.yzl.planManagementService.MonitoringstatisticsService;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlMenu;
import com.yzl.pojo.YzlMonitoringstatistics;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlUser;
import com.yzl.pojo.YzlXb;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;
import com.yzl.utils.trees;

@Service
@Transactional
public class MonitoringstatisticsServiceImpl implements MonitoringstatisticsService{

	@Autowired
	private YzlMenuMapper menuMapper;
	
	@Autowired
	private YzlDistrictMapper districtMapper;
	
	@Autowired
	private YzlEpcMapper epcMapper;
	
	@Autowired
	private YzlTaskMapper taskMapper;
	
	@Autowired
	private YzlEpcTaskProgressMapper epcTaskProgressMapper;
	
	@Autowired
	private YzlMonitoringstatisticsMapper monitoringstatisticsMapper;
	
	@Autowired
	private YzlXbMapper xbMapper;
	
	private static List<List<YzlMonitoringstatistics>> list = new ArrayList<>();
	
	//装小班数据
	private static List<List<YzlXb>> xbLists = new ArrayList<>();
	
//	private static String gcs = null;
//	private static String zls = null;
	private static String mak = "mak";

	@Override
	public List<YzlTask> findByEcode(String epcode) {
		List<YzlTask> tasks = menuMapper.selectByEcode(Integer.valueOf(epcode));
		return tasks;
	}

	//页面左边地区的表格
	@Override
	public EasyUIResult show(Integer page, Integer rows, String did, String epcode, String tpcode, String time) {
		
		List<YzlMonitoringstatistics> list=new ArrayList<>();
		YzlUser user = getUser();
		//PageHelper.startPage(page, rows);
		
		List<YzlDistrict> flag=new ArrayList<>();
		
		if (user.getUsername().equals("mark")) {//为系统管理员
			//查询所有县
			flag = districtMapper.selectFindAllFlag();
		}else{
			
			//存放监测权限的集合
			List<YzlMenu> mList = new ArrayList<>();
			
			//根据用户获取到对应的权限
			List<YzlMenu> menus = epcTaskProgressMapper.findByUid(Integer.valueOf(String.valueOf(user.getId())));
			
			for (YzlMenu yzlMenu : menus) {//遍历所有的权限
				String perms = yzlMenu.getPerms();
				if (perms!=null) {
					if (perms.contains("JC")) {//如果权限中有JC就添加到集合中
						mList.add(yzlMenu);
					}
				}
			}
			
			//创建监测统计的对象
			YzlMonitoringstatistics monitoringstatistics = new YzlMonitoringstatistics();
			//存放权限地区的集合
			List<YzlDistrict> districts=new ArrayList<>();
			for (YzlMenu yzlMenu : mList) {//遍历该用户拥有的监测权限
				
				String perms = yzlMenu.getPerms();//获取权限标识
				String substring = perms.substring(0, 2);//截取
				
				if (!substring.equals("JC")) {//不为JC说明不是市
					
					YzlDistrict dYzlDistrict = new YzlDistrict();
					//截取县出来放到集合中
					String sub = yzlMenu.getName();
					String county = sub.substring(sub.indexOf("市")+1, sub.length());
					
					dYzlDistrict.setCounty(county);
					districts.add(dYzlDistrict);
				}else {//就是市
					//获取到市名
					monitoringstatistics.setCity(yzlMenu.getName());
					YzlDistrict district = new YzlDistrict();
					flag.add(district);
				}
			}
			monitoringstatistics.setCountys(districts);
			list.add(monitoringstatistics);
			//System.out.println();
		}
		
		
		//PageInfo<YzlMonitoringstatistics> pageInfo=new PageInfo<>(list);
//		int i= 1/0;
		EasyUIResult result=new EasyUIResult();
		
		result.setRows(list);
		result.setTotal(flag.size());
		
		return result;
	}

	@Override
	public List<YzlDistrict> sel() {
		List<YzlDistrict> list = districtMapper.selectFindAllFlag();
		return list;
	}

	//根据县查询工程
	@Override
	public List<YzlEpc> findByCity(String county) {
		List<YzlEpc> epcs =  epcTaskProgressMapper.selectByCounty(county);
		return epcs;
	}

	@Override
	public List<YzlTask> findByEcodeAndDname(String ecode, String county) {
		
		List<YzlTask> list = epcTaskProgressMapper.selectByEcodeAndDname(ecode,county);
		
		return list;
	}

	//页面右边的数据表格
	@Override//ecode 工程编号  county 县  tname 字段名
	public EasyUIResult findByTEC(String ecode,String county,String tname,Integer rows,Integer page,String time) {
		time="2018";
		System.out.println(time+"--------------");
		//System.out.println(ecode+county+tname);
		YzlUser user = getUser();//获取当前登录用户
		//根据县名称查询
		YzlDistrict district = districtMapper.selectByCounty(county);
		//根据任务查询
		YzlTask task = taskMapper.selectByTname(tname);
		
		//当前页数据  等于	当前页	-1	乘以当前页显示的记录数
		int page2 = (page-1)*rows;
		
		//总记录数
		//List<YzlEpcTaskProgress> ETPList=new ArrayList<>();
		List<YzlMonitoringstatistics> ETPList=new ArrayList<>();
		YzlMonitoringstatistics m=new YzlMonitoringstatistics();
		//分页查询的结果
		//List<YzlEpcTaskProgress> list=new ArrayList<>();
		List<YzlMonitoringstatistics> list=new ArrayList<>();
		//为系统管理员
		//if (user.getUsername().equals("mark")) {
			//总记录数
			//ETPList = epcTaskProgressMapper.selectAll(ecode,String.valueOf(district.getDcode()),String.valueOf(task.getTcode()));
			//ecode工程编号 ，dcode地区编号，tcode任务编号
			//获取一年的数据
			List<YzlMonitoringstatistics> monitoringstatistics = epcTaskProgressMapper.selectAlls(ecode,String.valueOf(district.getDcode()),String.valueOf(task.getTcode()),time);
			
			YzlMonitoringstatistics yzlMonitoringstatisticss = new YzlMonitoringstatistics();
			
			
			Integer inspectionreportareaSum=0;//上报面积
			Integer verifyAreaSum=0;//核实面积
			Integer certifiedAreaSUm=0;//核实合格面积
			Integer designForestAreaSum=0;//设计育林面积
			Integer verifyForestAreaSum=0;//核实育林面积
			Integer CLMJSum=0;//成林面积	
			Integer fileSizeSum=0;//有档案面积;
			Integer countyInspectionAreaSum=0;//开展县级自检面积
			Integer managementAreaSum=0;//管护面积
			
			for (YzlMonitoringstatistics yzlMonitoringstatistics : monitoringstatistics) {//遍历一年的数据
				//得到每一次小班的上报面积
				String inspectionreportarea = yzlMonitoringstatistics.getInspectionreportarea();
				if (StringUtils.isNotBlank(inspectionreportarea)) {
					inspectionreportareaSum+=Integer.valueOf(inspectionreportarea);
				}
				if (StringUtils.isNotBlank(yzlMonitoringstatistics.getVerifyarea())) {
					verifyAreaSum+=Integer.valueOf(yzlMonitoringstatistics.getVerifyarea());
				}//CertifiedArea
				if (StringUtils.isNotBlank(yzlMonitoringstatistics.getCertifiedarea())) {
					certifiedAreaSUm+=Integer.valueOf(yzlMonitoringstatistics.getCertifiedarea());
				}
				if (StringUtils.isNotBlank(yzlMonitoringstatistics.getDesignforestarea())) {
					designForestAreaSum+=Integer.valueOf(yzlMonitoringstatistics.getDesignforestarea());
				}
				if (StringUtils.isNotBlank(yzlMonitoringstatistics.getVerifyforestarea())) {
					verifyForestAreaSum+=Integer.valueOf(yzlMonitoringstatistics.getVerifyforestarea());
				}
				if (StringUtils.isNotBlank(yzlMonitoringstatistics.getClmj())) {
					CLMJSum+=Integer.valueOf(yzlMonitoringstatistics.getClmj());
				}
				/*if (StringUtils.isNotBlank(yzlMonitoringstatistics.getFilesize())) {
					fileSizeSum+=Integer.valueOf(yzlMonitoringstatistics.getFilesize());
				}*/
				if (StringUtils.isNotBlank(yzlMonitoringstatistics.getCountyinspectionarea())) {
					countyInspectionAreaSum+=Integer.valueOf(yzlMonitoringstatistics.getCountyinspectionarea());
				}
				if (StringUtils.isNotBlank(yzlMonitoringstatistics.getManagementarea())) {
					managementAreaSum+=Integer.valueOf(yzlMonitoringstatistics.getManagementarea());
				}
			}
			
			//面积核实率areaVerificationRate 面积核实率=2核实面积  / 1自检上报面积
			if(inspectionreportareaSum > 0 ) {
				Double as = (double) (verifyAreaSum/inspectionreportareaSum);
				m.setAreaverificationrate(String.valueOf(as*100));
			}
			
			//verifyAreaPassRate核实面积合格率=3核实合格面积 / 2核实面积
			if (verifyAreaSum>0) {
				Double as = (double) (certifiedAreaSUm/verifyAreaSum);
				m.setVerifyareapassrate(String.valueOf(as*100));
			}
			//reportAreaQualificationRate上报面积合格率=3核实合格面积 / 1自检上报面积
			if(inspectionreportareaSum>0) {
				Double as = (double) (certifiedAreaSUm/inspectionreportareaSum);
				m.setReportareaqualificationrate(String.valueOf(as*100));
			}
			//封育当年上报合格面积= 全年累加“3核实合格面积”
			//countySelfInspectionArea全县自检合格面积=所有“5开展县级自检面积” 累加
			m.setCountyselfinspectionarea(String.valueOf(countyInspectionAreaSum));
			
			//calculatedVerificationArea15.推算核实面积= 全年累加“2核实面积”
			m.setCalculatedverificationarea(String.valueOf(verifyAreaSum));
			//推算成效合格面积= 全年累加"成林面积"(成林面积在数据表中有，此表格未使用到。)
			//当年上报成效合格率=16推算成效合格面积 / 13封育当年上报合格面积
			//fileRate档案率=3有档案面积 / 1自检上报面积
			if (inspectionreportareaSum>0) {
				Double as = (double) (fileSizeSum/inspectionreportareaSum);
				m.setFilerate(String.valueOf(as*100));
			}
			
			//selfCheckingRate自查率=2核实面积 / 1自检上报面积
			if(inspectionreportareaSum>0) {
				Double as = (double) (verifyAreaSum/inspectionreportareaSum);
				m.setSelfcheckingrate(String.valueOf(as*100));
			}
			
			//afforestationRate育林率=7核实育林面积 / 6设计育林面积
			if (designForestAreaSum>0) {
				Double as = (double) (verifyForestAreaSum/designForestAreaSum);
				m.setAfforestationrate(String.valueOf(as*100));
			}
			
			//theManagementRate管护率=8管护面积 / 2核实面积
			if(verifyAreaSum>0) {
				Double as = (double) (managementAreaSum/verifyAreaSum);
				m.setManagementarea(String.valueOf(as*100));
			}
			//System.out.println("总记录数"+ETPList.size());
			//分页查询
			//list = epcTaskProgressMapper.selectAllPage(ecode,String.valueOf(district.getDcode()),String.valueOf(task.getTcode()),rows,page2);
			//List<YzlMonitoringstatistics> monitoringstatisticsList = epcTaskProgressMapper.selectAllPages(ecode,String.valueOf(district.getDcode()),String.valueOf(task.getTcode()),rows,page2,time);
			//System.out.println("集合大小"+list.size());
	//	}else {
			//根据用户id查询这个用户拥有的所有记录
			//ETPList = epcTaskProgressMapper.selectByUidAndL(user.getId(),ecode,String.valueOf(district.getDcode()),String.valueOf(task.getTcode()));
			
			//分页查询
			//list = epcTaskProgressMapper.selectByUidAndLPage(user.getId(),ecode,String.valueOf(district.getDcode()),String.valueOf(task.getTcode()),rows,page2);
		//}
		
		//List<YzlMonitoringstatistics> monitoringstatistics = new ArrayList<>();
		//获取到这个县下发的任务
		/*for (YzlEpcTaskProgress yzlEpcTaskProgress : list) {
			
			
			//创建对象
			YzlMonitoringstatistics yzlMonitoringstatistics = new YzlMonitoringstatistics();
			
			//得到下发的任物基数
			Float taskprogress = yzlEpcTaskProgress.getTaskprogress();
			
			//模拟出   推算核实面积
			Float calculatedverificationarea = taskprogress-10;
			
			//任务完成率  =  推算核实面积  除以    计划任务
			Double taskcompletionrate = Double.valueOf(String.valueOf(calculatedverificationarea))/Double.valueOf(String.valueOf(taskprogress));
			
			//乘以百分比后
			String of = String.valueOf(taskcompletionrate*100);
			
			if (of.length()==4) {
				yzlMonitoringstatistics.setTaskcompletionrate(of);
			}else {
				//截取前4位
				 String substring = of.substring(0, 4);
				 
				 //截取第五个
				 String d5g = of.substring(4, 5);
				 
				 //判断是否大于等于5四舍五入
				 if (Integer.valueOf(d5g)>=5) {
					BigDecimal avs=new BigDecimal(substring);
					BigDecimal av = avs.add(new BigDecimal(String.valueOf(0.1)));
					yzlMonitoringstatistics.setTaskcompletionrate(String.valueOf(av));
				}else {
					yzlMonitoringstatistics.setTaskcompletionrate(substring);
				}
			}
			String substring = yzlEpcTaskProgress.getCreateTimetoString().substring(0, 4);
			yzlMonitoringstatistics.setTime(substring);
			yzlMonitoringstatistics.setScheduledtask(String.valueOf(taskprogress));
			yzlMonitoringstatistics.setCalculatedverificationarea(String.valueOf(calculatedverificationarea));
			
			//添加到集合中
			monitoringstatistics.add(yzlMonitoringstatistics);
		}*/
			monitoringstatistics.add(m);
		EasyUIResult result = new EasyUIResult();
		result.setRows(monitoringstatistics);
		result.setTotal(ETPList.size());
		
		return result;
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
	
	//测试
	@Override
	public String text(String name) {
		return name;
	}

	//树
	@Override
	public List<trees> show_trees() {
		
		//获取用户对应得到权限
		List<String> permsList = getMenu();
		
		//查询所有市去重
		List<YzlDistrict> citys = districtMapper.selectByDisinctCity(permsList);
		
		List<trees> zjqList = new ArrayList<>();
		
		//自治区的树
		trees zjq = new trees();
		
		zjq.setId("GX450");
		zjq.setText("广西壮族自治区");
		zjq.setState("open");
		
		//存放市的树集合
		List<trees> cityList = new ArrayList<>();
		
		for (YzlDistrict yzlDistrict : citys) {//遍历所有的市
			
			//市树
			trees cityTree = new trees();
			
			cityTree.setText(yzlDistrict.getCity());
			cityTree.setId(yzlDistrict.getFlag());
			
			
			//根据市查询它所有的县
			List<YzlDistrict> countys = districtMapper.selectByCity(yzlDistrict.getCity(),permsList);
			
			if (countys.size()==1 || citys.size()==1) {//如果只有一个县直接打开节点esle不开
				cityTree.setState("open");
			}else {
				cityTree.setState("closed");
			}
			//存放县树的集合
			List<trees> tList = new ArrayList<>();
			
			for (YzlDistrict yzlDistrict2 : countys) {
				
				//县树
				trees countyTree = new trees();
				
				countyTree.setId(String.valueOf(yzlDistrict2.getDcode()));
				countyTree.setText(yzlDistrict2.getCounty());
				tList.add(countyTree);
			}
			
			cityTree.setChildren(tList);
			cityList.add(cityTree);
		}
		zjq.setChildren(cityList);
		zjqList.add(zjq);
		
		return zjqList;
	}

	//sname：条件
	//自治区
	
	@Override
	public EasyUIResult monitMunicipality(String sname,String time,String stime,Integer page, Integer rows,String cityCode,String zllb,String gclb) {
//		int i = 1/0;
		//清空集合的数据
		xbLists.clear();
//		gcs = gc;
//		zls = zl;
		//获取当前登录用户对应的权限
		List<String> permsList = getMenu();
		//监测统计
		List<YzlMonitoringstatistics> monit = new ArrayList<>();
		
		//当前页数据  等于	当前页	-1	乘以当前页显示的记录数
		int page2= (page - 1)*rows;
		String times = time;
		
		if (stime!=null && !"null".equals(stime)) {
			times = stime;
		}
		if (sname!=null && !"null".equals(sname)) {
			sname = sname.trim();
		}
		//总记录数
		List<YzlXb> total = xbMapper.selecTtotalMunicipality(sname,times,permsList,cityCode,zllb,gclb);
		xbLists.add(total);//添加
		
		//查询所有根据地区和年度分组区分

		List<YzlXb> xbList = xbMapper.selectByGropMunicipality(sname,times,permsList,page2,rows,cityCode,zllb,gclb);
		
		//计算
		long currentTimeMillis = System.currentTimeMillis();
		String mark = "qu";
		monit = getMonit(xbList,mark);
		long currentTimeMillis2 = System.currentTimeMillis();
		System.out.println(currentTimeMillis2-currentTimeMillis+"秒");
		
		List<YzlMonitoringstatistics> mo = new ArrayList<>();
		//List<YzlXb> list2 = xbLists.get(0);
		
		for (YzlMonitoringstatistics  yzlmo : monit) {
			mo.add(yzlmo);
		}
		
		//分页
		EasyUIResult result = getResult(mo, Long.valueOf(total.size()));
		
		return result;
	}

	
	//市
	@Override
	public EasyUIResult moniCity(String flag,Integer page,Integer rows,String sname,String stime,String times,String cityCode,String zllb,String gclb) {
		
		//清空集合的数据
		xbLists.clear();
//		gcs = gc;
//		zls = zl;
		
		//获取用户对应的权限
		List<String> permsList = getMenu();
		
		if (!mak.equals(flag)) {
			page = 1;
		}
		System.out.println("2mak:="+mak);
		System.out.println("page:="+page);
		System.out.println("flag:="+flag);
		System.out.println("times:="+times);
		//查询这个市所有的县
		List<YzlDistrict> countyList = districtMapper.selectByFlags(flag,permsList);
		
		//获取市的行政编号
		String anumber = null;
		if (countyList.size()>0) {
			YzlDistrict district = countyList.get(0);
			String number = district.getAnumber();
			anumber = number.substring(0, 4);
		}
		
		//监测统计
		List<YzlMonitoringstatistics> monitoringstatisticsList = new ArrayList<>();
		
		//当前页数据  等于	当前页	-1	乘以当前页显示的记录数
		int page2= (page - 1)*rows;
		mak = flag;
		
		String string = times;
		if (stime!=null && !"null".equals(stime)) {
			string = stime;
		}
		if (sname!=null && !"null".equals(sname)) {
			sname = sname.trim();
		}
		//总记录数
		List<YzlXb> total = xbMapper.selecTtotalCity(anumber,sname,string,permsList,cityCode,zllb,gclb);
		xbLists.add(total);
		
		//查询所有根据地区和年度分组区分
		List<YzlXb> xbList = xbMapper.selectByGropCity(anumber,sname,string,permsList,page2,rows,cityCode,zllb,gclb);
		
		long currentTimeMillis = System.currentTimeMillis();
		String mark  = "sh";
		//计算....
		monitoringstatisticsList = getMonit(xbList,mark);
		long currentTimeMillis2 = System.currentTimeMillis();
		System.out.println(currentTimeMillis2-currentTimeMillis+"秒");
		
		//当前页数据  等于	当前页	-1	乘以当前页显示的记录数
		
		System.out.println(monitoringstatisticsList.size());
		
		//PageInfo<YzlXb> pageInfo = new PageInfo<>(xbList);
		EasyUIResult result = getResult(monitoringstatisticsList, Long.valueOf(total.size()));
		
		return result;
	}
	
	//县
	@Override//dcode县的编号
	public EasyUIResult monitCounty(String dcode, Integer page, Integer rows,String sname,String stime,String time,String zllb,String gclb) {
		
		//清空集合
		xbLists.clear();
//		gcs = gc;
//		zls = zl;
		
		//根据县编号查询对应的行政编号
		YzlDistrict district = districtMapper.selectByPrimaryKey(Integer.valueOf(dcode));
		String anumber = district.getAnumber();
		System.out.println(anumber+"=======");
		//行政编号
		String number = null;
		if (district!=null) {
			number = district.getAnumber();
		}
		System.out.println(number);
		//当前页数据  等于	当前页	-1	乘以当前页显示的记录数
		int page2= (page - 1)*rows;
		
		String string = time.trim();
		if (stime != null && !"null".equals(stime)) {
			string = stime.trim();
		}
		if (sname!=null && !"null".equals(sname)) {
			sname = sname.trim();
		}
		//根据县查询每一年的所对应的数据总记录数
		List<YzlXb> xbTotal = xbMapper.selecTtotalCounty(number,sname,string,zllb,gclb);
		xbLists.add(xbTotal);
		
		//根据县查询每一年的所对应的数据
		List<YzlXb> xbs = xbMapper.selectByGropCounty(number,sname,string,page2,rows,zllb,gclb);
		
		long currentTimeMillis = System.currentTimeMillis();
		String mark  = "xian";
		List<YzlMonitoringstatistics> monit = getMonit(xbs,mark);
		long currentTimeMillis2 = System.currentTimeMillis();
		System.out.println(currentTimeMillis2-currentTimeMillis+"秒");
		//分页
		EasyUIResult result = getResult(monit, Long.valueOf(xbTotal.size()));
		
		return result;
	}
	
	//除
	private String getDB(Float args1,Float args2) {
		Float areaVer = args1/args2;
		return String.format("%.2f", areaVer*100);
	}
	
	//计算
	private List<YzlMonitoringstatistics> getMonit(List<YzlXb> xbList,String mark) {
		
		List<YzlMonitoringstatistics> monitoringstatisticsList = new ArrayList<>();
		
//		String zh = null;
		String db = null;
//		String gc = null;
//		String zl = null;
//		String county = null;
		String time = null;
		List<YzlEpcTaskProgress> yzlEpcTaskProgresses = new ArrayList<>();
		List<YzlXb> xbs = new ArrayList<>();
		Float inspectionReportAreaAndSfydaSum=0f;//根据是否有档案统计上报面积
		Float inspectionReportAreaSumAndSfzjSum = 0f;//根据是否开展县级自检面积统计上报面积
		Float inspectionReportAreaAndSfghSum = 0f;//根据是否管护面积统计上报面积
		YzlMonitoringstatistics monitoringstatistics = new YzlMonitoringstatistics();
		
		String countyCode = null;
		String gclb = null;
		String zllb = null;
		YzlDistrict district = null;
		YzlEpc epc = null;
		YzlTask task = null;
		//计划任务 
		Float taskprogress = 0F;
		//一个县一个年度的县统计
		Float inspectionReportAreaSum = 0F;//自检上报统计
		Float verifyAreaSum = 0f;//核实面积
		Float certifiedAreaSum = 0f;//核实合格面积
		Float designForestAreaSum = 0f;//设计育林面积
		Float verifyForestAreaSum = 0f;//核实育林面积
		Float clmjSum = 0f;//成林面积
		String ccty = null;
		String city = null;
		
		for (YzlXb yzlXbs : xbList) {
					
			gclb = yzlXbs.getGclb();
			zllb = yzlXbs.getZllb();
			countyCode = yzlXbs.getCounty();
			 time = yzlXbs.getZynd();
			  city = yzlXbs.getCity();
			  
			if ("qu".equals(mark)) {
				ccty = city;
			}else {
				ccty = countyCode;
			}
			 
			//查询下发的任务
			 yzlEpcTaskProgresses = epcTaskProgressMapper.selectTaskIssued(gclb,zllb,ccty,time);

			//查询某个县知道年度的小班
			 xbs = xbMapper.selectByCountyAndTime(ccty,gclb,zllb,time);
			
			if (xbs.size()>0) {
				
				 monitoringstatistics = new YzlMonitoringstatistics();
				
				
				if(yzlEpcTaskProgresses.size() > 0 && yzlEpcTaskProgresses.get(0) != null) {
					taskprogress = yzlEpcTaskProgresses.get(0).getTaskprogress();
				}
						
				monitoringstatistics.setScheduledtask(taskprogress.toString());
				
				
				String xtjsbmj = yzlXbs.getXtjsbmj();
				if (StringUtils.isNotBlank(xtjsbmj)) {
					inspectionReportAreaSum = Float.valueOf(xtjsbmj);
				}
				
				
				String hsmj = yzlXbs.getHsmj();
				if (StringUtils.isNotBlank(hsmj)) {
					verifyAreaSum = Float.valueOf(hsmj);
				}
				
				String hgmj = yzlXbs.getHgmj();
				if (StringUtils.isNotBlank(hgmj)) {
					certifiedAreaSum = Float.valueOf(hgmj);
				}
				
				
				String sjylmj = yzlXbs.getSjylmj();
				if (StringUtils.isNotBlank(sjylmj)) {
					designForestAreaSum = Float.valueOf(sjylmj);
				}
				
				
				String hsylmj = yzlXbs.getHsylmj();
				if (StringUtils.isNotBlank(hsylmj)) {
					verifyForestAreaSum = Float.valueOf(hsylmj);
				}
				
				
				String clmj = yzlXbs.getClmj();
				if (StringUtils.isNotBlank(clmj)) {
					clmjSum = Float.valueOf(clmj);
				}
				
//				Float inspectionReportAreaAndSfydaSum=0f;//根据是否有档案统计上报面积
//				Float inspectionReportAreaSumAndSfzjSum = 0f;//根据是否开展县级自检面积统计上报面积
//				Float inspectionReportAreaAndSfghSum = 0f;//根据是否管护面积统计上报面积
				
				for (YzlXb yzlXb : xbs) {
					
					 countyCode = yzlXb.getCounty();//根据县得到市
					
					 gclb = yzlXb.getGclb();//工程类别
					
					 zllb = yzlXb.getZllb();//造林类别
					
					if (StringUtils.isNotBlank(countyCode)) {
						 district = districtMapper.selectByNumber(countyCode);
						monitoringstatistics.setCity(district.getCity());
					}
					if (StringUtils.isNotBlank(gclb)) {
						 epc = epcMapper.selectByMark(gclb);
						monitoringstatistics.setEname(epc.getEname());
					}
					if (StringUtils.isNotBlank(zllb)) {
						 task = taskMapper.selectByMark(zllb);
						monitoringstatistics.setTname(task.getTname());
					}
					
					//fileSize有档案面积=带有“是否有档案”标识时，实际就是1“自检上报面积”，否则等于0
					if (yzlXb.getSfyda() != null && yzlXb.getSfyda() != "0") {//根据是否有档案统计上报面积
						inspectionReportAreaAndSfydaSum += Float.valueOf(yzlXb.getXtjsbmj());
					}
					//开展县级自检面积=带有“是否开展县级自检面积”标识时，实际就是1“自检上报面积”，否则等于0
					if (yzlXb.getSfzj()!=null && yzlXb.getSfzj() != "0") {
						inspectionReportAreaSumAndSfzjSum += Float.valueOf(yzlXb.getXtjsbmj());
					}
					//管护面积=带有“是否管护面积”标识时，实际就是1“自检上报面积”，否则等于0
					if (yzlXb.getSfgh() !=null && yzlXb.getSfgh() != "0") {
						inspectionReportAreaAndSfghSum+=Float.valueOf(yzlXb.getXtjsbmj());
					}
				}
				
				monitoringstatistics.setFilesize(inspectionReportAreaAndSfydaSum.toString());//有档案面积
				monitoringstatistics.setCountyinspectionarea(inspectionReportAreaSumAndSfzjSum.toString());//开展县级自检面积countyInspectionArea
				monitoringstatistics.setManagementarea(inspectionReportAreaAndSfghSum.toString());//管护面积managementArea
				
				//任务完成率  等于   上班面积除以计划任务
 				//Float taskCompletionRate = 0f;
 				if (taskprogress != null && taskprogress != 0f) {//StringUtils.isNotBlank(taskprogress.toString())
 					 db = getDB(inspectionReportAreaSum,taskprogress);
// 					String zh = zh(db);
 					monitoringstatistics.setTaskcompletionrate(db);
				}else {
					monitoringstatistics.setTaskcompletionrate("0.0");
				}
 				
				if (inspectionReportAreaSum != 0 && inspectionReportAreaSum != null) {//areaVerificationRate面积核实率=2核实面积  / 1自检上报面积
					 db = getDB(verifyAreaSum, inspectionReportAreaSum);
//					String zh = zh(db);Float areaVer = args2/args1;
					monitoringstatistics.setAreaverificationrate(db);//areaVerificationRate面积核实率=2核实面积  / 1自检上报面积
				}else {
					monitoringstatistics.setAreaverificationrate("0.0");
				}
				//verifyAreaPassRate核实面积合格率=3核实合格面积 / 2核实面积
				if (verifyAreaSum != 0 && verifyAreaSum != null) {
					 db = getDB(certifiedAreaSum, verifyAreaSum);
//					String zh = zh(db);
					monitoringstatistics.setVerifyareapassrate(db);
				}else {
					monitoringstatistics.setVerifyareapassrate("0.0");
				}
				//reportAreaQualificationRate上报面积合格率=3核实合格面积 / 1自检上报面积
				if (inspectionReportAreaSum != 0 && inspectionReportAreaSum != null) {
					 db = getDB(certifiedAreaSum, inspectionReportAreaSum);
//					String zh = zh(db);
					monitoringstatistics.setReportareaqualificationrate(db);
				}else {
					monitoringstatistics.setReportareaqualificationrate("0.0");
				}
				//afforestationReportsEligibleYear封育当年上报合格面积= 全年累加“3核实合格面积”
				monitoringstatistics.setAfforestationreportseligibleyear(certifiedAreaSum.toString());
				//countySelfInspectionArea全县自检合格面积=所有“5开展县级自检面积” 累加
				monitoringstatistics.setCountyselfinspectionarea(inspectionReportAreaSumAndSfzjSum.toString());
				//calculatedVerificationArea推算核实面积= 全年累加“2核实面积”
				monitoringstatistics.setCalculatedverificationarea(verifyAreaSum.toString());
				//calculateQualifiedArea推算成效合格面积= 全年累加"成林面积"(成林面积在数据表中有，此表格未使用到。)
				monitoringstatistics.setCalculatequalifiedarea(clmjSum.toString());
				//当年上报成效合格率=16推算成效合格面积 / 13封育当年上报合格面积
				
				//fileRate档案率=3有档案面积 / 1自检上报面积
				if (inspectionReportAreaSum != 0 && inspectionReportAreaSum != null) {
					 db = getDB(inspectionReportAreaAndSfydaSum, inspectionReportAreaSum);
//					String zh = zh(db);
					monitoringstatistics.setFilerate(db);
				}else {
					monitoringstatistics.setFilerate("0.0");
				}
				
				//selfCheckingRate自查率=2核实面积 / 1自检上报面积
				if (inspectionReportAreaSum != 0 && inspectionReportAreaSum != null) {
					 db = getDB(verifyAreaSum, inspectionReportAreaSum);
//					String zh = zh(db);
					monitoringstatistics.setSelfcheckingrate(db);
				}else {
					monitoringstatistics.setSelfcheckingrate("0.0");
				}
				//afforestationRate育林率=7核实育林面积 / 6设计育林面积
				if (designForestAreaSum != 0 && designForestAreaSum != null) {
					 db = getDB(verifyForestAreaSum, designForestAreaSum);
//					String zh = zh(db);
					monitoringstatistics.setAfforestationrate(db);
				}else {
					monitoringstatistics.setAfforestationrate("0.0");
				}
				//theManagementRate管护率=8管护面积 / 2核实面积
				if (verifyAreaSum != 0 && verifyAreaSum != null) {
					 db = getDB(inspectionReportAreaAndSfghSum, verifyAreaSum);
//					String zh = zh(db);
					monitoringstatistics.setThemanagementrate(db);
				}else {
					monitoringstatistics.setThemanagementrate("0.0");
				}
				if (StringUtils.isNotBlank(time)) {
					monitoringstatistics.setTime(time);
				}
				
				//inspectionReportArea自检上报统计
				monitoringstatistics.setInspectionreportarea(inspectionReportAreaSum.toString());
				//verifyArea核实面积
				monitoringstatistics.setVerifyarea(verifyAreaSum.toString());
				//certifiedArea核实合格面积
				monitoringstatistics.setCertifiedarea(certifiedAreaSum.toString());
				//designForestArea设计育林面积
				monitoringstatistics.setDesignforestarea(designForestAreaSum.toString());
				//verifyForestArea核实育林面积
				monitoringstatistics.setVerifyforestarea(verifyForestAreaSum.toString());
				//根据查询县名称
				 district = districtMapper.selectByNumber(countyCode);
				monitoringstatistics.setCounty(district.getCounty());
				monitoringstatisticsList.add(monitoringstatistics);
			}
		}
		return monitoringstatisticsList;
	}
	
	//分页返回
	private EasyUIResult getResult(List<YzlMonitoringstatistics> list,Long total) {
		EasyUIResult result = new EasyUIResult();
		result.setRows(list);
		result.setTotal(total);
		return result;
	}
	
	//所有造林类别
	@Override
	public List<YzlTask> zllb() {
		List<YzlTask> tasks = taskMapper.selectshow();
		return tasks;
	}
	
	//所有工程类别
	@Override
	public List<YzlEpc> gclb() {
		List<YzlEpc> epcs = epcMapper.gclb();
		return epcs;
	}
	
	//导出
	@Override//discod 1区，2市，3县  sname条件，stime条件时间，puptime系统时间
	public YzlResult deriveMunicipality(HttpServletResponse response,String discod,String sname,String stime,String puptime,String qccty) throws IOException{
		
		if("1".equals(discod)) {
			EasyUIResult monitMunicipality = monitMunicipality(sname, puptime, stime, 1, 10,null,null,null);
			System.out.println(monitMunicipality);
		}
		if("2".equals(discod)) {
			EasyUIResult moniCity = moniCity(qccty, 1, 10, sname, stime, puptime,null,null,null);
			System.out.println(moniCity);
		}
		if("3".equals(discod)) {
			EasyUIResult monitCounty = monitCounty(qccty, 1, 10, sname, stime, puptime,null,null);
			System.out.println(monitCounty);
		}
		List<YzlXb> xxb = new ArrayList<>();
		
		for (List<YzlXb> list2 : xbLists) {
			for (YzlXb yzlXb : list2) {
				xxb.add(yzlXb);
			}
		}
		
		List<YzlMonitoringstatistics> monit = getMonit(xxb,null);
		
		HSSFWorkbook workbook = new HSSFWorkbook();//创建工作簿对象（wxcel文档对象）
		
		HSSFSheet sheet = workbook.createSheet();//创建工作表
//		sheet.setColumnWidth(0, 4*256);//设置单元格的宽
		
		HSSFRow row = sheet.createRow(1);//在sheet里创建行
		HSSFCell cell0 = row.createCell(0);//在行里创建列
		row.setHeightInPoints(30);//行高
		
		//设置单元格内容
		cell0.setCellValue("造林表造林表造林表造林表造林表造林表造林表造林表造林表造林表造林表造林表造林表造林表造林表造林表造林表造");
		//合并单元格//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 35));
		
		HSSFRow row1 = sheet.createRow(3);//在sheet里创建行
		
		//在行里创建列
		HSSFCell cell1 = row1.createCell(1);//创建列
		cell1.setCellValue("市");
		sheet.addMergedRegion(new CellRangeAddress(3, 7, 1, 1));
		
		HSSFCell cell2 = row1.createCell(2);
		cell2.setCellValue("县");
		sheet.addMergedRegion(new CellRangeAddress(3, 7, 2, 2));
		
		HSSFCell cell3 = row1.createCell(3);
		cell3.setCellValue("工程类别 ");
		sheet.addMergedRegion(new CellRangeAddress(3, 7, 3, 3));
		
		HSSFCell cell4 = row1.createCell(4);
		cell4.setCellValue("造林类别 ");
		sheet.addMergedRegion(new CellRangeAddress(3, 7, 4, 4));
		
		HSSFCell cell5 = row1.createCell(5);
		cell5.setCellValue("时间");
		sheet.addMergedRegion(new CellRangeAddress(3, 7, 5, 5));
		
		HSSFCell cell6 = row1.createCell(6);
		cell6.setCellValue("抽查情况");//自检上报面积
		sheet.addMergedRegion(new CellRangeAddress(3, 4, 6, 16));//合并列
		
		HSSFCell cell7 = row1.createCell(17);
		cell7.setCellValue("核查质量情况");
		sheet.addMergedRegion(new CellRangeAddress(3, 4, 17, 19));
		
		HSSFCell cell8 = row1.createCell(20);
		cell8.setCellValue("任务完成情况");
		sheet.addMergedRegion(new CellRangeAddress(3, 4, 20, 26));
		
		HSSFCell cell9 = row1.createCell(27);
		cell9.setCellValue("管理指标");
		sheet.addMergedRegion(new CellRangeAddress(3, 4, 27, 35));
		
		//在建一行
		HSSFRow row2 = sheet.createRow(5);//在sheet里创建行
		
		HSSFCell cell10 = row2.createCell(6);
		cell10.setCellValue("自检上报面积 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 6, 6));
		
		HSSFCell cell11 = row2.createCell(7);
		cell11.setCellValue("核实面积  ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 7, 7));
		
		HSSFCell cell12 = row2.createCell(8);
		cell12.setCellValue("核实合格面积 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 8, 8));
		
		HSSFCell cell13 = row2.createCell(9);
		cell13.setCellValue("作业设计面积 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 9, 9));
		
		HSSFCell cell14 = row2.createCell(10);
		cell14.setCellValue("按作业设计施工面 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 10, 10));
		
		HSSFCell cell15 = row2.createCell(11);
		cell15.setCellValue("有档案面积");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 11, 11));
		
		HSSFCell cell16 = row2.createCell(12);
		cell16.setCellValue("开展县级自检面积 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 12, 12));
		
		HSSFCell cell17 = row2.createCell(13);
		cell17.setCellValue("设计育林面积 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 13, 13));
		
		HSSFCell cell18 = row2.createCell(14);
		cell18.setCellValue("核实育林面积 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 14, 14));
		
		HSSFCell cell19 = row2.createCell(15);
		cell19.setCellValue("抚育面积  ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 15, 15));
		
		HSSFCell cell20 = row2.createCell(16);
		cell20.setCellValue("管护面积 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 16, 16));
		
		
		HSSFCell cell21 = row2.createCell(17);
		cell21.setCellValue("面积核实率  ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 17, 17));
		
		HSSFCell cell22 = row2.createCell(18);
		cell22.setCellValue("核实面积合格率 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 18, 18));
		
		HSSFCell cell23 = row2.createCell(19);
		cell23.setCellValue("上报面积合格率 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 19, 19));
		
		
		HSSFCell cell24 = row2.createCell(20);
		cell24.setCellValue("当年上报面积保存 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 20, 20));
		
		HSSFCell cell25 = row2.createCell(21);
		cell25.setCellValue("造林当年上报合格 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 21, 21));
		
		HSSFCell cell26 = row2.createCell(22);
		cell26.setCellValue("全县自检合格面积 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 22, 22));
		
		HSSFCell cell27 = row2.createCell(23);
		cell27.setCellValue("推算核实面积");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 23, 23));
		
		HSSFCell cell28 = row2.createCell(24);
		cell28.setCellValue("推算完成合格面积 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 24, 24));
		
		HSSFCell cell29 = row2.createCell(25);
		cell29.setCellValue("计划任务  ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 25, 25));
		
		HSSFCell cell30 = row2.createCell(26);
		cell30.setCellValue("任务完成率 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 26, 26));
		
		
		HSSFCell cell31 = row2.createCell(27);
		cell31.setCellValue("作业设计率");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 27, 27));
		
		HSSFCell cell32 = row2.createCell(28);
		cell32.setCellValue("按设计施工率 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 28, 28));
		
		HSSFCell cell33 = row2.createCell(29);
		cell33.setCellValue("建档率  ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 29, 29));
		
		HSSFCell cell34 = row2.createCell(30);
		cell34.setCellValue("自查率");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 30, 30));
		
		HSSFCell cell35 = row2.createCell(31);
		cell35.setCellValue("自检率 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 31, 31));
		
		HSSFCell cell36 = row2.createCell(32);
		cell36.setCellValue("档案率 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 32, 32));
		
		HSSFCell cell37 = row2.createCell(33);
		cell37.setCellValue("抚育率 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 33, 33));
		
		HSSFCell cell38 = row2.createCell(34);
		cell38.setCellValue("育林率 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 34, 34));
		
		HSSFCell cell39 = row2.createCell(35);
		cell39.setCellValue("管护率 ");
		sheet.addMergedRegion(new CellRangeAddress(5, 7, 35, 35));
		
		Integer rows = 8;
		//Integer ce = 1;
		for (int i = 0; i < monit.size(); i++) {
			
			YzlMonitoringstatistics yzlMonitoringstatistics = monit.get(i);
		//for (YzlMonitoringstatistics yzlMonitoringstatistics : monit) {
			
//			System.out.println("-------"+rows);
//			System.out.println("======="+ce++);
			
			HSSFRow hssfRow = sheet.createRow(rows++);
			
			hssfRow.createCell(1).setCellValue(yzlMonitoringstatistics.getCity());//市
			hssfRow.createCell(2).setCellValue(yzlMonitoringstatistics.getCounty());//县
			hssfRow.createCell(3).setCellValue(yzlMonitoringstatistics.getEname());//工程类别
			hssfRow.createCell(4).setCellValue(yzlMonitoringstatistics.getTname());//造林类别
			hssfRow.createCell(5).setCellValue(yzlMonitoringstatistics.getTime());//时间
			
			hssfRow.createCell(6).setCellValue(yzlMonitoringstatistics.getInspectionreportarea());//自检上报面积 inspectionReportArea
			hssfRow.createCell(7).setCellValue(yzlMonitoringstatistics.getVerifyarea());//核实面积 verifyArea
			hssfRow.createCell(8).setCellValue(yzlMonitoringstatistics.getCertifiedarea());//核实合格面积certifiedArea
			hssfRow.createCell(9).setCellValue(yzlMonitoringstatistics.getJobdesignarea());//作业设计面积jobDesignArea
			hssfRow.createCell(10).setCellValue(yzlMonitoringstatistics.getDesignconstructionaccordingoperation());//按作业设计施工面积designConstructionAccordingOperation
			hssfRow.createCell(11).setCellValue(yzlMonitoringstatistics.getFilesize());//有档案面积 fileSize
			hssfRow.createCell(12).setCellValue(yzlMonitoringstatistics.getCountyinspectionarea());//开展县级自检面积countyInspectionArea
			hssfRow.createCell(13).setCellValue(yzlMonitoringstatistics.getDesignforestarea());//设计育林面积 designForestArea
			hssfRow.createCell(14).setCellValue(yzlMonitoringstatistics.getVerifyforestarea());//核实育林面积verifyForestArea
			hssfRow.createCell(15).setCellValue(yzlMonitoringstatistics.getBarearea());//抚育面积 bareArea
			hssfRow.createCell(16).setCellValue(yzlMonitoringstatistics.getManagementarea());//管护面积 managementArea
			
			hssfRow.createCell(17).setCellValue(yzlMonitoringstatistics.getAreaverificationrate());//面积核实率areaVerificationRate
			hssfRow.createCell(18).setCellValue(yzlMonitoringstatistics.getVerifyareapassrate());//核实面积合格率verifyAreaPassRate
			hssfRow.createCell(19).setCellValue(yzlMonitoringstatistics.getReportareaqualificationrate());//上报面积合格率reportAreaQualificationRate
			
			hssfRow.createCell(20).setCellValue(yzlMonitoringstatistics.getReportretentionrateyear());//当年上报面积保存reportRetentionRateYear
			hssfRow.createCell(21).setCellValue(yzlMonitoringstatistics.getAfforestationreportseligibleyear());//造林当年上报合格afforestationReportsEligibleYear
			hssfRow.createCell(22).setCellValue(yzlMonitoringstatistics.getCountyselfinspectionarea());//全县自检合格面积countySelfInspectionArea
			hssfRow.createCell(23).setCellValue(yzlMonitoringstatistics.getCalculatedverificationarea());//推算核实面积calculatedVerificationArea
			hssfRow.createCell(24).setCellValue(yzlMonitoringstatistics.getCalculatequalifiedarea());//推算完成合格面积 calculateQualifiedArea
			hssfRow.createCell(25).setCellValue(yzlMonitoringstatistics.getScheduledtask());//计划任务scheduledTask
			hssfRow.createCell(26).setCellValue(yzlMonitoringstatistics.getTaskcompletionrate());//任务完成率taskCompletionRate

			hssfRow.createCell(27).setCellValue(yzlMonitoringstatistics.getJobdesignrate());//作业设计率 jobDesignRate
			hssfRow.createCell(28).setCellValue(yzlMonitoringstatistics.getAccordingdesignconstructionrate());//按设计施工率accordingDesignConstructionRate
			hssfRow.createCell(29).setCellValue(yzlMonitoringstatistics.getByinputtingrate());//建档率 byInputtingRate
			hssfRow.createCell(30).setCellValue(yzlMonitoringstatistics.getSelfcheckingrate());//自查率selfCheckingRate
			hssfRow.createCell(31).setCellValue(yzlMonitoringstatistics.getSelfcheckingsrate());//自检率selfCheckingsRate
			hssfRow.createCell(32).setCellValue(yzlMonitoringstatistics.getFilerate());//档案率 fileRate
			hssfRow.createCell(33).setCellValue(yzlMonitoringstatistics.getRaisingrates());//抚育率 raisingRates
			hssfRow.createCell(34).setCellValue(yzlMonitoringstatistics.getAfforestationrate());//育林率afforestationRate
			hssfRow.createCell(35).setCellValue(yzlMonitoringstatistics.getThemanagementrate());//管护率 theManagementRate
		}
		
		HSSFCellStyle style = workbook.createCellStyle();//设置样式
		HSSFFont font = workbook.createFont();//字体样式
		
		//加边框
//		style.setBorderBottom(BorderStyle.THIN);//下边框 
//		style.setBorderLeft(BorderStyle.THIN);//左边框 
//		style.setBorderRight(BorderStyle.THIN);//右边框 
//		style.setBorderTop(BorderStyle.THIN); //上边框
		
		//居中
		style.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中（上下居中）
		style.setAlignment(HorizontalAlignment.CENTER);// 左右居中
		
		//设置字体
		font.setFontName("仿宋_GB2312");//字体名称
		font.setFontHeightInPoints((short) 12);//字体的大小
		font.setItalic(false);//设置是否为斜体 
		font.setBold(true);//字体加粗
		style.setWrapText(true);//自动换行
		
		style.setFont(font);//设置需要用到的字体样式
		
		List<HSSFCell> list = new ArrayList<>();
		list.add(cell0);list.add(cell1);list.add(cell2);list.add(cell3);list.add(cell4);list.add(cell5);list.add(cell6);list.add(cell7);list.add(cell8);list.add(cell9);list.add(cell10);
		list.add(cell11);list.add(cell12);list.add(cell13);list.add(cell14);list.add(cell15);list.add(cell16);list.add(cell17);list.add(cell18);list.add(cell19);list.add(cell20);
		list.add(cell21);list.add(cell22);list.add(cell23);list.add(cell24);list.add(cell25);list.add(cell26); list.add(cell27); list.add(cell28);list.add(cell29);list.add(cell30);
		list.add(cell31);list.add(cell32);list.add(cell33);list.add(cell34);list.add(cell35);list.add(cell36);list.add(cell37);list.add(cell38);list.add(cell39);
		
		//渲染单元格
		for (HSSFCell hssfCell : list) {
			hssfCell.setCellStyle(style);
		}
		
		//"D://EXXXX.xls"
		/*String path = "D://";//盘符路劲
		String fileName = "excel1.xls";//文件名称
		int i=1;
		//获取某个盘符下的所有文件名称
		File file = new File(path);
		String[] fi = file.list();
		
		for (String string : fi) {
			
			if (string.contains(".")) {
				int indexOf = string.indexOf(".");//获取点出现的下标索引
				String substring = string.substring(indexOf, string.length());//截取后缀
				
				if (substring.equals(".xls")) {//如果后缀名为.xls
					if (fileName.equalsIgnoreCase(string)) {
						fileName = "excel" + ++i + ".xls";
					}
				}
			}
		}*/
		//path+fileName
		//OutputStream outputStream = response.getOutputStream();
		response.reset();
		OutputStream os;
					os = response.getOutputStream();
					response.setContentType("application/vnd.ms-excel");
					response.setHeader("content-disposition", "attachment;filename=excel1.xls");
					workbook.write(os);
				
				//workbook.close();
				os.close();
			
			return YzlResult.ok(400);
	}
	
	//任务发布的导入
	@Override
	public YzlResult taskDr(MultipartFile[] exceName) throws IOException {
		
		InputStream inputStream = null;
		for (MultipartFile multipartFile : exceName) {
			inputStream = multipartFile.getInputStream();
		}
		
		//使用poi解析excel文件
		HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
		
		//获取指定的sheet对象
		HSSFSheet sheet = workbook.getSheet("核查计划任务表");
		for (Row row : sheet) {
			int rowNum = row.getRowNum();
			
			
			if (rowNum <= 7) {
				continue;
			}
			
			row.getCell(0).setCellType(CellType.STRING);
			
			if (row.getCell(0).getStringCellValue().equals("一")|| row.getCell(0).getStringCellValue().equals("二")
			|| row.getCell(0).getStringCellValue().equals("三")|| row.getCell(0).getStringCellValue().equals("四") || 
			row.getCell(0).getStringCellValue().equals("五")|| row.getCell(0).getStringCellValue().equals("六")|| 
			row.getCell(0).getStringCellValue().equals("七")|| row.getCell(0).getStringCellValue().equals("八")|| 
			row.getCell(0).getStringCellValue().equals("久")|| row.getCell(0).getStringCellValue().equals("十")|| row.getCell(0).getStringCellValue().equals("十一")
		|| row.getCell(0).getStringCellValue().equals("十二")|| row.getCell(0).getStringCellValue().equals("十三")|| row.getCell(0).getStringCellValue().equals("十四")) {
				continue;
			}
			for (Cell cell : row) {
				System.out.println();
				if (cell != null) {
					cell.setCellType(CellType.STRING);
					String stringCellValue = cell.getStringCellValue();
					System.out.print(stringCellValue + " ,");
				}
				
			}
		}
	
		System.out.println(exceName);
		return null;
	}

	//查询所有的市
	@Override
	public List<YzlDistrict> AllCity() {
		List<YzlDistrict> list = monitoringstatisticsMapper.AllCity();
		return list;
	}

	//所有工程
	@Override
	public List<YzlEpc> AllEpc() {
		List<YzlEpc> list = monitoringstatisticsMapper.AllGclb();
		return list;
	}

	//查询某个市下的县
	@Override
	public List<YzlDistrict> AllCounty(String cityCode) {
		List<YzlDistrict> list = monitoringstatisticsMapper.AllCounty(cityCode);
		return list;
	}

	//字符串截取跟换算率
//		private String zh(String valueOf) {
//			//0.7433333
//			int i = valueOf.indexOf(".");
//			String substring3 = valueOf.substring(i, valueOf.length());
//			
//			Double floats = 0.0;
//			if (valueOf.length()>=5 && substring3.length()>=2) {//0.3870968  387096.8
//				//截取第五位
//				String substring = valueOf.substring(4, 5);
//				//截取前4位
//				String string = valueOf.substring(0, 4);
//				
//				if (Integer.valueOf(substring)>=5) {//判断第五位数是否大于等于五四舍五入
//					Double of = Double.valueOf(string);
//					//0.21000000000000002
//					floats = of+0.01;
//					if (floats.toString().length()>4) {
//						String valueOf2 = floats.toString();
//						floats = Double.valueOf(valueOf2.substring(0, 4));
//					}
//				}else {
//					floats = Double.valueOf(string);
//				}
//				
//			}else {
//				floats = Double.valueOf(valueOf);
//			}
//			
//			BigDecimal bigDecimal = new BigDecimal("100");
//			BigDecimal fBigDecimal = new BigDecimal(floats.toString());
//			BigDecimal multiply = bigDecimal.multiply(fBigDecimal);
//			
//			String valueOf2 = multiply.toString();
//			int indexOf = valueOf2.indexOf(".");
//			
//			String substring2 = "0.0";
//			String substring = valueOf2.substring(indexOf, valueOf2.length());
//			if (substring.length()>=3) {
//				substring2 = valueOf2.substring(0, valueOf2.length()-1);
//			}else {
//				substring2 = valueOf2;
//			}
//			return substring2;
//		}
}
