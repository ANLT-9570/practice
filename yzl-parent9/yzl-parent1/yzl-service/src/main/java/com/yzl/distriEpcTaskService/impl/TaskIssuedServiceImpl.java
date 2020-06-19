package com.yzl.distriEpcTaskService.impl;


import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
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
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Map.Entry;

import javax.mail.Folder;
import javax.mail.Flags.Flag;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.scripting.xmltags.VarDeclSqlNode;
import org.apache.log4j.Logger;
import org.apache.poi.hpsf.Decimal;
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
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.common.utils.StringUtils;
import com.yzl.distriEpcTaskService.TaskIssuedService;
import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.mapper.YzlEpcMapper;
import com.yzl.mapper.YzlEpcTaskProgressMapper;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.mapper.YzlMessageMapper;
import com.yzl.mapper.YzlRoleMenuMapper;
import com.yzl.mapper.YzlTaskMapper;
import com.yzl.mapper.YzlUserRoleMapper;
import com.yzl.mapper.YzlXbMapper;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlMessage;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlUser;
import com.yzl.utils.AdministrativeCode;
import com.yzl.utils.CheckStringIsNumberUtils;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.FileSpliceMark;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.YzlEpcAndTaskStaticti;
import com.yzl.utils.YzlResult;
import com.yzl.utils.dto.TaskIssuedDTO;
import com.yzl.utils.enums.DistrictEnum;
import com.yzl.utils.enums.KeyNameEnum;
import com.yzl.utils.enums.MessageStatus;
import com.yzl.utils.enums.ResultEnum;
import com.yzl.utils.enums.TaskWorkSatusEnum;
import com.yzl.utils.exception.YzlException;

@Transactional
@Service
public class TaskIssuedServiceImpl implements TaskIssuedService {

	@Autowired
	private YzlEpcTaskProgressMapper epcTaskProgressMapper;
	@Autowired
	private YzlMenuMapper menuMapper;
	@Autowired
	private YzlDistrictMapper districtMapper;
	@Autowired
	private YzlMessageMapper messageMapper;
	@Autowired
	private YzlRoleMenuMapper roleMenuMapper;
	@Autowired
	private YzlEpcMapper epcMapper;
	@Autowired
	private YzlTaskMapper taskMapper;
	@Autowired
	private YzlXbMapper xbMapper;
	@Autowired
	private YzlUserRoleMapper userRoleMapper;

	
	private String uploadFolderURI="uploadFile/"; //上传文件的路径
	
	private static Logger logger = Logger.getLogger(TaskIssuedServiceImpl.class);
	
	private static List<Map<String,String>> taskIssuedDTOs = new ArrayList<>();//用于存取导出的数据
	
	
	//通过作业年度和地区行政编号查询任务下发数据根据县、工程类别、县级编号进行分组
/*	@Override
	public EasyUIResult queryTaskIssuedByAreaCodeAndYearGroupCountyAndGCLBAndZLLBAndAreaCodeAndGCLB(Integer page, Integer rows,
			HttpServletRequest request, String areaCode, String year,String GCLB) {
		taskIssuedDTOs.clear();
		//如果当前年是Null,则默认是当前时间的年
		if(StringUtils.isBlank(year)){
			year = new SimpleDateFormat("yyyy").format(new Date());
		}
		//获取当前登录用户的权限
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		List<String> markList = menuMapper.queryPermsByUserId(Integer.valueOf(loginUser.getId().toString()));
		List<TaskIssuedDTO> TaskIssuedDTOList = epcTaskProgressMapper.queryTaskIssuedByGroupCountyAndGCLBAndZLLBAndAreaCodeAndGCLB(markList,areaCode,year,GCLB);
		taskIssuedDTOs.add(TaskIssuedDTOList);
		
		EasyUIResult result = this.pageQueryUtils(TaskIssuedDTOList, page, rows);
		return result;
	}*/
	
	//查询所有任务进度
	@Override
	public EasyUIResult queryTaskIssuedByYearGroupCountyAndGCLBAndZLLBAndAreaCodeAndGCLB(HttpServletRequest request,int page,int rows
			,String year,String GCLB){
		//清空集合
		taskIssuedDTOs.clear();
		//如果Year为空 则取当前时间为默认值
		if(StringUtils.isBlank(year)){
      		year = new SimpleDateFormat("yyyy").format(new Date());
		}
		List<TaskIssuedDTO> epcTaskProgresses = new ArrayList<>();
		//取当前登陆用户
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		//取当前用户所拥有的权限
		List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		  
		epcTaskProgresses = epcTaskProgressMapper.queryByYearAndGCLB(PermsList,year,GCLB);
		
//		taskIssuedDTOs.add(epcTaskProgresses);
		
		//如果查询数据不为空 则对数据进行分页显示处理
		 EasyUIResult result = this.pageQueryUtils(epcTaskProgresses,page,rows);
		 List<HashMap<Object,Object>> list = (List<HashMap<Object, Object>>) result.getRows();
		 //查询任务进度并 根据地区Id去重复  结果作为总记录数
		return result;
	}

	//对数据进行分页处理
	private EasyUIResult pageQueryUtils(List<TaskIssuedDTO> TaskIssuedDTOList,Integer page,Integer rows){
		List<HashMap<Object,Object>> resultList = new ArrayList<>();
		//用做判断当前数据是否已经添加过
		List<Integer> idList = new ArrayList<>();
		try {
			for (int i = 0; i < TaskIssuedDTOList.size(); i++) {
				TaskIssuedDTO ItaskIssuedDTO = TaskIssuedDTOList.get(i);
				Integer IcountyNumber = ItaskIssuedDTO.getCountyNumber();
				//判断当前数据是否已添加过，如果添加过则直接跳过本次循环 不在进行遍历判断
				if(idList.contains(ItaskIssuedDTO.getId())) continue;
				//封装数据
				HashMap<Object, Object> resultMap = new HashMap<>();
				resultMap.put("city", ItaskIssuedDTO.getCity());
				resultMap.put("county", ItaskIssuedDTO.getCounty());
				resultMap.put("JHND", ItaskIssuedDTO.getJHND());
				resultMap.put("ZYND", ItaskIssuedDTO.getZYND());
				String XZJSBMJnumber = ItaskIssuedDTO.getTaskNumber().setScale(5, BigDecimal.ROUND_HALF_DOWN).toString();
				//添加县统计面积到集合、用工程编号+任务类别编号做拼接为Key
				resultMap.put(ItaskIssuedDTO.getGCLB()+"T"+ItaskIssuedDTO.getZLLB(), XZJSBMJnumber);//向上取整，取小数位后5位
				for (int j = i+1; j < TaskIssuedDTOList.size(); j++) {
					//判断当前数据是否已添加过，如果添加过则直接跳过本次循环 不在进行遍历判断
					TaskIssuedDTO JtaskIssuedDTO = TaskIssuedDTOList.get(j);
					//判断当前数据是否已添加过，如果添加过则直接跳过本次循环 不在进行遍历判断
					if(idList.contains(JtaskIssuedDTO.getId())) continue;
					//获取县行政编号
					Integer JcountyNumber = JtaskIssuedDTO.getCountyNumber();
					//判断是不是同一个县级
					if(IcountyNumber.equals(JcountyNumber)){
						//判断作业年度和计划年度是否一致
						if(ItaskIssuedDTO.getZYND().equals(JtaskIssuedDTO.getZYND())
								&&ItaskIssuedDTO.getJHND().equals(JtaskIssuedDTO.getJHND())){
							//添加县统计面积到集合、用工程类别编号+造林类别编号做拼接为Key
							String XZJSBMJNmuber2 = JtaskIssuedDTO.getTaskNumber().setScale(5, BigDecimal.ROUND_HALF_UP).toString();
							resultMap.put(JtaskIssuedDTO.getGCLB()+"T"+JtaskIssuedDTO.getZLLB(),XZJSBMJNmuber2);//向上取整，取小数位后5位
							//resultMap.put(JtaskIssuedDTO.getGCLB()+"T"+JtaskIssuedDTO.getZLLB(), String.valueOf(JtaskIssuedDTO.getTaskNumber()));
							idList.add(JtaskIssuedDTO.getId());
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
		List<HashMap<Object,Object>> result = new ArrayList<HashMap<Object,Object>>();
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

	@Override
	public YzlResult add(HttpServletRequest request, List<String> params) {
		if(params==null || params.size()<=0){
			return YzlResult.build(400, "没有任务需要添加的数据");
		}
		int result=0;
		//获取当前登录用户
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		//数据封装
		YzlEpcTaskProgress epcTaskProgress = new YzlEpcTaskProgress();
		epcTaskProgress.setCreatetime(new Date());
		epcTaskProgress.setCreator(loginUser.getName());//创建人
//		epcTaskProgress.setStat("0");
		for (String param : params) {
			if(!StringUtils.isBlank(param)){
			 String[] Strs = param.split(":");
				 if(Strs.length==2){
					 switch (Strs[0]) {
						case "county":  //如果key为county则为县行政编号 数据
							String countyCode = Strs[1];
							epcTaskProgress.setCountycode(countyCode);//设置县行政编号
							String cityCode = countyCode.substring(0, 4);//取钱4位做 市级行政编号
							epcTaskProgress.setCitycode(cityCode);
															break;
						case "JHND"://如果key为JHND则为计划年度 的数据
							epcTaskProgress.setJhnd(Strs[1]);
															break;
						case "ZYND"://如果key为ZYND则为作业年度 的数据
							epcTaskProgress.setZynd(Strs[1]);
															break;
						default:
							String[] epcTasks = Strs[0].split("T"); //Strs索引0 为工程类别编号+T+造林类别编号拼接成
							if(epcTasks.length==2){
								epcTaskProgress.setGclb(epcTasks[0]);//索引0为工程类别
								epcTaskProgress.setZllb(epcTasks[1]);//索引1为造林类别
							}
							if(CheckStringIsNumberUtils.isDecimals(Strs[1])){//判断下发任务基础 是否为数字
								epcTaskProgress.setTaskprogress(Float.valueOf(Strs[1]));//索引1为任务下发数据基数
								result += epcTaskProgressMapper.insert(epcTaskProgress);//执行插入
							}else{
								new YzlException(ResultEnum.TASK_BASE_NOT_NUMBER);
							}
															break;
						}
				 }
			}
			
		}
		String countyCode = epcTaskProgress.getCountycode();
		YzlMessage message = new YzlMessage();
		//message.setContent("有新下发的任务请注意查收");
		//message.setStat(MessageStatus.MESSAGE_STATU_NOT_READ.getStat());
		message.setCreatetime(new Date());
		message.setCountycode(countyCode);
		Integer menuId = menuMapper.selectbyPerms(countyCode);
		List<Integer> roleIdList = roleMenuMapper.selectBymenuId((long)menuId);
		for (Integer roleId : roleIdList) {
			message.setRid(roleId);
			messageMapper.insert(message);
		}
		if(result<=0){
			return YzlResult.build(201, "数据插入失败");
		}
		return YzlResult.ok();
	}

	/***
	 * 更新任务下发数据
	 */
	@Override
	public YzlResult update(HttpServletRequest request, List<String> params) {
		if(params==null || params.size()<=0){
			return YzlResult.build(400, "没有任务需要修改的数据");
		}
		YzlEpcTaskProgress epcTaskProgress = new YzlEpcTaskProgress();
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		List<Float> taskNumberList = new ArrayList<>();
		epcTaskProgress.setModifier(loginUser.getName());//修改人
		for (String param : params) {
			if(!StringUtils.isBlank(param)){
			 String[] Strs = param.split(":");
				 if(Strs.length==2){
					 int i=0;
					 switch (Strs[0]) {
						case "county":  //如果key为county则为县行政编号 数据
							YzlDistrict district = districtMapper.selectByCounty(Strs[1]);//根据县名查询
							String countyCode = district.getAnumber();//获取县级行政编号
							epcTaskProgress.setCountycode(countyCode);//设置县行政编号
							String cityCode = countyCode.substring(0, 4);//取钱4位做 市级行政编号
							epcTaskProgress.setCitycode(cityCode);
							i++;
															break;
						case "JHND"://如果key为JHND则为计划年度 的数据
							epcTaskProgress.setJhnd(Strs[1]);
							i++;
															break;
						case "ZYND"://如果key为ZYND则为作业年度 的数据
							epcTaskProgress.setZynd(Strs[1]);
							i++;
															break;
						default:
							if(Strs[0].contains("T")){
								int count=0;
								String[] epcTasks = Strs[0].split("T"); //Strs索引0 为工程类别编号+T+造林类别编号拼接成
								if(epcTasks.length==2){
										epcTaskProgress.setGclb(epcTasks[0]);//索引0为工程类别
										epcTaskProgress.setZllb(epcTasks[1]);//索引1为造林类别
										 count = epcTaskProgressMapper.countByGCLBAndJHNDAndZYNDAndZLLBAndCountyCode(epcTaskProgress);
									
									if(CheckStringIsNumberUtils.isDecimals(Strs[1])){//判断下发任务基础 是否为数字
										epcTaskProgress.setTaskprogress(Float.valueOf(Strs[1]));//索引1为任务下发数据基数
										//查询不到数据， 说明数据不存在，则执行插入
										if(count==0){
											epcTaskProgress.setCreator(loginUser.getName());//创建人
											epcTaskProgressMapper.insert(epcTaskProgress);//执行插入
										}else{
											
											epcTaskProgressMapper.updateByZYNDAndJHNDAndGCLBAndZLLBAndCountyCode(epcTaskProgress);//执行修改
										}
									}else{
										new YzlException(ResultEnum.TASK_BASE_NOT_NUMBER);
									}
								}
							}
							break;
					  }
				 }
			}
			
		}
		return YzlResult.ok();
	}

	/***
	 * 删除任务 下发数据
	 */
	@Override
	public YzlResult delete(String[] paramsJson,String year,HttpServletRequest request) {
		if(paramsJson==null||paramsJson.length<0){
			//参数为空异常
			throw new YzlException(ResultEnum.DELETE_PARAM_IS_NULL);
		}
		//字符串数组进行转换成List
		List<HashMap<String, String>> DataList = this.StrJsonArraysConvertList(paramsJson);
		for (HashMap<String, String> DatahashMap : DataList) {
				Set<Entry<String,String>> entrySet = DatahashMap.entrySet();
				//遍历集合
				String countyCode=null;//县编号
				String cityCode = null;//市编号
				String JHND=year;//计划时间
				String ZYND=year;//作业时间
				Set<String> zllbSet = new HashSet<>();
				Set<String> gclbSet = new HashSet<>();
//				List<String> file = new ArrayList<>();
				
				for (Entry<String, String> entry : entrySet) {
					//取数组转换后的HashMapKey的值
					String key = entry.getKey();
					String string = key.substring(0, 2);
					
					if (key.equals(KeyNameEnum.county.getName())) {
						String county = entry.getValue();
						//取县行政编码
						YzlDistrict district = districtMapper.selectByCounty(county);
						countyCode = district.getAnumber();
					}
					if (string.contains(KeyNameEnum.JH.getName())) {//判断是否是计划任务
						//截取字符串
						String substring = key.substring(2, key.length());//把jh两字母去掉
						int indexOf = substring.indexOf("Y");
						
						String zllb = substring.substring(0,indexOf);//造林类别
						String gclb = substring.substring(indexOf+1, substring.length());//工程类别
						gclbSet.add(gclb);//封装所有工程类别
						zllbSet.add(zllb);//封装所有造林类别
					}
					if (key.equals(KeyNameEnum.CITY_CODE.getName())) {
						cityCode = entry.getValue();
					}
				}
				//获取文件路径
				String url = request.getServletContext().getRealPath("uploadFile/");
//				System.out.println(url);

				List<String> epcTaskProgresses = epcTaskProgressMapper.selectByZJCCZ(ZYND,JHND,countyCode,cityCode,gclbSet,zllbSet);
				for (String string : epcTaskProgresses) {
					if (!StringUtils.isBlank(string)) {
						String path = url+string;
						File file = new File(path);
						file.delete();//删除文件
					}
				}
				
//				String filesUrl = epcTaskProgresses.getFilesUrl();
				//执行
				epcTaskProgressMapper.deleteByZYNDAndJHNDAndCountyCode(ZYND,JHND,countyCode,cityCode,zllbSet,gclbSet);
		}
		return YzlResult.ok();
	}
	
	//导出
	@Override
	public YzlResult derive(HttpServletResponse response,String year,String clcik,String areaCode,String ZLLB,String GCLB) throws IOException {
		EasyUIResult queryTaskData = queryTaskData(1, 10, year, areaCode, ZLLB,GCLB);
		long total = queryTaskData.getTotal();
		List<?> rows = queryTaskData.getRows();
		HSSFWorkbook workbook = new HSSFWorkbook();//excel对象
		
		HSSFSheet sheet = workbook.createSheet();//创建一个工作本
		
		HSSFRow row1 = sheet.createRow(1);//在sheet里创建行
		
		HSSFCellStyle style = workbook.createCellStyle();//设置样式
		
		LinkedHashSet<String> hashSet = new LinkedHashSet<>();//所有的造林类别
		
		//遍历获取所以的造林类别并且去重
		for (Map<String, String> map : taskIssuedDTOs) {
			Set<Entry<String,String>> entrySet = map.entrySet();
			for (Entry<String, String> entry : entrySet) {
				String key = entry.getKey();
//				String value = entry.getValue();
				
				String substring = key.substring(0, 2);//jh
				if (substring.equals("jh")) {
					
					int indexOf = key.indexOf("Y");
					String zllb = key.substring(2, indexOf);//造林类别
					hashSet.add(zllb);
					
				}
			}
		}

		//获取用户的所有权限
		List<String> authoritys = getLoginUserAuthoritys();
		

		//创建第二行
		HSSFRow row = sheet.createRow(2);
		HSSFRow row2 = sheet.createRow(3);//创建第三行
		HSSFRow row4 = sheet.createRow(4);//创建第四行
		
		int start = 2;
		int estart = 2;
		for (String zllb : hashSet) {//遍历所以造林类别
			YzlTask task = taskMapper.selectByMark(zllb);
			//查询这个造林拥有的工程
			List<String> epcTaskProgresses = epcTaskProgressMapper.selectByTaskPosessEpc(year,zllb,clcik,authoritys);
			
			int size = epcTaskProgresses.size()*3;
			//合并单元格
			sheet.addMergedRegion(new CellRangeAddress(2, 2, start, start+size-1));
			
			//一级表头
			String tname = task.getTname();
			HSSFCell cell = row.createCell(start);
			cell.setCellValue(tname);
			cell.setCellStyle(style);
			
			//二级表头
			for (String gclb : epcTaskProgresses) {
				
				YzlEpc epc = epcMapper.selectByMark(gclb);
				sheet.addMergedRegion(new CellRangeAddress(3, 3, estart, estart+2));
				HSSFCell cell2 = row2.createCell(estart);
				cell2.setCellValue(epc.getEname());
				cell2.setCellStyle(style);
				
				int atr = 1;
				for(int j=estart;j<estart+3;j++) {
					
					HSSFCell cell3 = row4.createCell(j);
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
			
			start +=epcTaskProgresses.size()*3;
		}
		
		
		int st = 5;
		//遍历所以数据进行填充
		for (Map<String, String> map : taskIssuedDTOs) {
			
			Set<Entry<String,String>> entrySet = map.entrySet();
			
			HSSFRow createRow = sheet.createRow(st);//从第五行开始、新建的行
			
			for (Entry<String, String> entry : entrySet) {
				
				String key = entry.getKey();
				if (key.equalsIgnoreCase("fileName")) {
					continue;
				}
				
				String value = null;
				if(!"fileUrl".equals(key)) {
					value = entry.getValue();
				}
				
				
				//获取前面两个字符
				String string = key.substring(0, 2);
				
				if (key.equals("city")) {//如果为市
					HSSFCell cell = createRow.createCell(0);
					cell.setCellValue(value);
				}
				if (clcik.length() == 4 || clcik.length() == 6) {
					if (key.equals("county")) {
						HSSFCell cell = createRow.createCell(1);
						cell.setCellValue(value);
					}
					
				}
				if (string.equals("jh")) {//如果为计划
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
				
//				if (string.equals("zj")) {
//					//zjh三个字母去掉后
//					String substring = key.substring(3, key.length());
//					//16Y8
//					int indexOf = substring.indexOf("Y");
//					String zllb = substring.substring(0, indexOf);//造林类别编号
//					String gclb = substring.substring(indexOf+1, substring.length());//工程编号
//					
//					//获取指定的工程把数据放到指定的位置
//					gainEpcPutLocation(zllb, gclb, sheet, createRow, value , string);
//				}
			}
			st++;
		}
		
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
		
		sheet.setColumnWidth(0, 4*280);
		
		//渲染单元格
		
		response.reset();
		OutputStream os = response.getOutputStream();
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("content-disposition", "attachment;filename=excel1.xls");
		
		workbook.write(os);
		
		os.close();
		
		return YzlResult.ok(200);
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
//				int lastRow = region.getLastRow();//结束行
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
	
	//private 对json数组进行处理
	private List<HashMap<String, String>> StrJsonArraysConvertList(String [] paramsJson){
		List<HashMap<String, String>> resultList = new ArrayList<>();
		for (String string : paramsJson) {//遍历数组
			HashMap<String, String> hashMap = new HashMap<>();
			String substring = string.substring(1, string.length()-1);//把头和尾去掉
			String[] strs = substring.split(",");//根据逗号 , 进行分割
			for (String str : strs) {//遍历分割后的数组
				if(str.indexOf(KeyNameEnum.WC.getName())>0||str.indexOf("filesUrl") > 0 || str.contains(KeyNameEnum.ZJH.getName())){
					continue;
				}
				if(str.indexOf(":")>0){
					String[] strings = str.split(":");//根据:冒号进行分割
						for (int i=0;i<strings.length;i++) {//遍历数组并且把分割好的数据弄成key，value形式放到map中
							if (i==1) {
								continue;
							}
								String key = strings[i].substring(1, strings[i].length()-1);
								
								String value = strings[i+1].substring(1, strings[i+1].length()-1);
								hashMap.put(key, value);
						}
				}
			}
			resultList.add(hashMap);
			
		}
		return resultList;
	}

	@SuppressWarnings("null")
	@Override
	public String toLead(MultipartFile[] excelName) {
		
		
		for (MultipartFile multipartFile : excelName) {
			try {
				//获取文件的输入流
				InputStream is = multipartFile.getInputStream();
				
				//使用poi解析excel文档
				HSSFWorkbook workbook = new HSSFWorkbook(is);
				
				//获取指定的sheet对象
				HSSFSheet sheet = workbook.getSheet("Sheet2");
				String time = null;
				//遍历这个sheet的所有行和列
				for (Row row : sheet) {
					//获取行号
					int rowNum = row.getRowNum();
					
					//获取时间
					if (rowNum == 1) {
						Cell cell = row.getCell(0);
						String value = cell.getStringCellValue();
						int indexOf = value.indexOf("年");
						time = value.substring(indexOf-4,indexOf);//把时间截取出来
					}
					
					if (rowNum < 2) {//小于两行的都跳过
						continue;
					}
					
					for (Cell cell : row) {//遍历这一行中的所有列
						//获取列索引
						int columnIndex = cell.getColumnIndex();
						int col = columnIndex;
						if (rowNum == 2) {
							if (columnIndex > 0) {
								
								String tname = cell.getStringCellValue();//取得造林类型
								System.out.println(tname + "---");
								
								if (!"".equals(tname)) {//如果不为空
									
									//根据造林类型的名称查询造林类别
									YzlTask task = taskMapper.selectByTname(tname);
									
									if (task == null) {//如果为空说明没有造林类别就为其它的
										YzlTask other = taskMapper.selectByTname("其它");
										//根据工程名称查询
//										String string = tname.replaceAll("\r", "");
										 Pattern p = Pattern.compile("\\s*|\t|\r|\n");//使用正则表达式去掉换行空格
								         Matcher m = p.matcher(tname);
								         String string = m.replaceAll("");
								         
										YzlEpc epc = epcMapper.selectByEname(string);
										
										for(int i=5;;i++) {//从第五行开始读取数据
											HSSFRow row2 = sheet.getRow(i);
											
											if (row2 == null) {
												break;
											}
											HSSFCell cell2 = row2.getCell(col);//col是这一列有数据的列索引
											cell2.setCellType(CellType.STRING);//将数字转为字符串输出
											
											String dataValue = cell2.getStringCellValue();
											
											if (!"".equals(dataValue) && !"0".equals(dataValue)) {//如果数据不为空并且不为0
												HSSFCell cell3 = row2.getCell(0);
												cell3.setCellType(CellType.STRING);
												String county = cell3.getStringCellValue();//获取市县名称
												//去除空格
												String cy = county.replace(" ", "");
												
												int insert = insert(cy, epc.getMark(), other.getMark(), dataValue,time);
												System.out.println(insert);
											}
										}
									}else {
										int tstart = col + 1;//表示这个任务后面的一个位置
										int tend = 0;// 用来记录工程结束位置的记录
										
										for(int i = tstart;;i++) {
											
											Cell cell2 = row.getCell(i);
											String value = cell2.getStringCellValue();
											
											if (!"".equals(value)) {//如果不想等说明已经到达下一个造林类别
												tend = i - 1;
												break;
											}
										}
										
										//取这个造林类别下面有多少个工程
										stop:for(int a=rowNum+1;;a++) {//第三行开始
											
											HSSFRow row2 = sheet.getRow(a);//取这一行的植
											
											for(int k=tstart-1;k<=tend;k++) {//根据这个造林类别所占领的列号开始和结束
												
												HSSFCell cell2 = row2.getCell(k);
												
												int index = cell2.getColumnIndex();
												
												String stringCellValue = cell2.getStringCellValue();//取得工程名称
												System.out.println(stringCellValue);
												
												if (tend == index) {
													break stop;
												}
												//根据工程名称查询
												YzlEpc epc = epcMapper.selectByEname(stringCellValue);
												
												if (epc != null) {
													
													for(int x=6;;x++) {//如果工程不为空从第五话开始遍历数据
														
														HSSFRow row3 = sheet.getRow(x);
														if (row3 == null) {
															break;
														}
														HSSFCell cell3 = row3.getCell(k);
														cell3.setCellType(CellType.STRING);//将数字转为字符串输出
														String dataValue = cell3.getStringCellValue();
														
														if (!"".equals(dataValue) && !"0".equals(dataValue)) {//如果数据不为空并且不为0
															
															HSSFCell cell4 = row3.getCell(0);
															String county = cell4.getStringCellValue();//获取市县名称
															//去除空格
															String cy = county.replace(" ", "");
															//将数据处理插入数据库
															int insert = insert(cy, epc.getMark(), task.getMark(), dataValue,time);
															
															//设置为最新任务
															YzlMessage message = new YzlMessage();
															message.setCountycode(cy);
															message.setCreatetime(new Date());
															message.setNumber(1L);
															message.setJhnd(time);
															message.setZynd(time);
															message.setGclb(epc.getMark());
															message.setZllb(task.getMark());
															messageMapper.insert(message);
															//System.out.println(insert);
														}
														
													}
													
												}
												
											}
										}
									}
								}
							}
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "1";
	}
	
	@Override
	public String toLeadDataDr(MultipartFile[] excelName)  {
		
		//查询所有的任务
		List<YzlTask> list = taskMapper.select();
		
		InputStream inputStream = null;
		for (MultipartFile multipartFile : excelName) {
			try {
				inputStream = multipartFile.getInputStream();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		//使用poi解析excel文档
//		HSSFWorkbook workbook = null;XSSFWorkbook 
//		XSSFWorkbook workbook = null;
		Workbook workbook= null;
		try {
			workbook = new XSSFWorkbook(inputStream);
		} catch (Exception e1) {
			try {
				workbook = new HSSFWorkbook(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			
			//获取指定的sheet对象
			//遍历所有的行和列
//			HSSFSheet sheet = workbook.getSheet("核查计划任务表");
			int sheets = workbook.getNumberOfSheets();
			for(int i = 0;i < sheets;i++) {
				Sheet sheet = workbook.getSheetAt(i);
				System.out.println(sheets);
			}
			
			XSSFSheet sheet = (XSSFSheet) workbook.getSheet("核查计划任务表");
//			HSSFSheet sheet = workbook.getSheet("Sheet2");
			for (Row row : sheet) {
				
				//获取行号
				int rowNum = row.getRowNum();
				
				if (rowNum<3) {//小于三行的都跳过
					continue;
				}
				
				for (Cell cell : row) {
					int columnIndex = cell.getColumnIndex();//获取列索引
					
					if (rowNum == 4) {//第四行的时候就跳过
						break;
					}
					if (rowNum == 3) {
						if (columnIndex > 8) {//columnIndex第九列开始
							
							cell.setCellType(CellType.STRING);//将数字转为字符串输出
							String stringCellValue = cell.getStringCellValue();//取的工程名
							System.out.println(stringCellValue+"   ");
							
							if (!stringCellValue.equals("")) {
								YzlEpc epc = epcMapper.selectByEname(stringCellValue);//根据工程名查询工程
								String Emark = epc.getMark();//工程编号 id   这里报空指针说明没有查询到该工程
								
								int start = columnIndex+1;//工程的后一个位置
								int end = 0; //工程所占位置的结束索引
								for(int i=start;;i++) {
									
									Cell cell2 = row.getCell(i);
									
									if (cell2 == null) {//说明是最后一个工程了
										end = i-1;
										break;
									}else {
										String stringCellValue2 = cell2.getStringCellValue();
										if (!stringCellValue2.equals("")) {
											end = i-1;
											break;
										}
									}
									
								}
								
								//取这个下面的任务
								for(int i=rowNum+2;i<=6;i++) {//rowNum第五行开始
									//HSSFRow row2 = 
									XSSFRow row2 = sheet.getRow(i);//获取这一行的植
									
									int celStart = 0;
									int celEnd = 0;
									
									int Taskcel = columnIndex;//任务的索引编号
									int subCel = 0;
									
									for(int j=start-1;j<=end;j++) {//根据这个工程所占的列号开始和结束
										
										celStart = i;
										
//										HSSFCell cell2 = row2.getCell(j);
										XSSFCell cell2 = row2.getCell(j);
										cell2.setCellType(CellType.STRING);
										String stringCellValue2 = cell2.getStringCellValue();//获得这个值
										
										for (YzlTask yzlTask : list) {//遍历所有任务
											
											boolean contains = false;
											String tname = yzlTask.getTname();
											
											if (i==6) {
												
												boolean sub = stringCellValue2.contains("计");
												
												if (sub) {
													subCel = j;//计的索引
												}
											}
											contains = stringCellValue2.contains(tname);//是否包含这个任务
											
											
											if (contains) {//说明包含这个任务
												String Tmark = yzlTask.getMark();//任务编号
												
												String firstTime = null;//获得前面第一个的时间
												String secondTime = null;//获得第二个时间
												
												String substring = null;
												
												if (i==6) {//如果是第六行
													
													//获取第五行的标题
//													HSSFRow hssfRow = sheet.getRow(5);
													XSSFRow hssfRow = sheet.getRow(5);
//													HSSFCell cell3 = hssfRow.getCell(subCel);
													XSSFCell cell3 = hssfRow.getCell(subCel);
													substring = cell3.getStringCellValue();
													
												}else {//就是第五行
													int indexOf = stringCellValue2.indexOf(tname);//获得这个任务在这个串的索引位置
													
													substring = stringCellValue2.substring(0, indexOf);//任务前面的字符
												}
												int first = 0;//计划时间
												int second = 0;//实施时间
												
												//遍历这个串
												for(int k=0;k<substring.length();k++) {
													
													char charAt = substring.charAt(k);//获得每一个字符
													String valueOf = String.valueOf(charAt);//转成字符串
													
													if (valueOf.equals("年")) {
														if (first==0) {
															first = k;
														}else {
															second = k;
														}
													}
												}
												firstTime = substring.substring(0, first);//获得前面第一个的时间
												if (second != 0) {
													secondTime = substring.substring(second-4, second);//获得第二个时间
												}
												
												
											//创建对象
											YzlEpcTaskProgress yzlEpcTaskProgress = new YzlEpcTaskProgress();
											
											yzlEpcTaskProgress.setGclb(Emark);
											yzlEpcTaskProgress.setZllb(Tmark);
											yzlEpcTaskProgress.setJhnd(firstTime);
											
											if (secondTime == null) {
												yzlEpcTaskProgress.setZynd(firstTime);
											}else {
												yzlEpcTaskProgress.setZynd(secondTime);
											}
											//columnIndex
											
											for(Row row3 : sheet) {
												
												int rowNum2 = row3.getRowNum();//行索引
												
												if (rowNum2 > 7) {
													
													Cell cell3 = row3.getCell(0);
													cell3.setCellType(CellType.STRING);//将数字转为字符串输出
													
													String zero = cell3.getStringCellValue();
													
													if (!zero.equals("一") && !zero.equals("二") && !zero.equals("三") && !zero.equals("四") && !zero.equals("五")
												&& !zero.equals("六") && !zero.equals("七") && !zero.equals("八") && !zero.equals("九") && !zero.equals("十")
												&& !zero.equals("十一") && !zero.equals("十二") && !zero.equals("十三") && !zero.equals("十四")) {
														
														//获取这个任务对应的值
														Cell tdata = row3.getCell(Taskcel);
														tdata.setCellType(CellType.STRING);//将数字转为字符串输出
														String tvalue = tdata.getStringCellValue();
														
														if (tvalue != "" && !tvalue.equals("0")) {//如果这个任务的值不为空才才查询这个县
															Cell cell4 = row3.getCell(1);
															String county = cell4.getStringCellValue();//获得县名称
															//去除空格
															String cy = county.replace(" ", "");
															System.out.println(cy+"====="+stringCellValue+"==="+tname+"==="+zero);
															//根据县名称查询
															YzlDistrict district = districtMapper.selectByCounty(cy);
															
															yzlEpcTaskProgress.setCitycode(district.getCitycode());//这里报空指针说明没查询到这个县
															
															yzlEpcTaskProgress.setCountycode(district.getAnumber());
															yzlEpcTaskProgress.setTaskprogress(Float.valueOf(tvalue));
															yzlEpcTaskProgress.setCreatetime(new Date());
															yzlEpcTaskProgress.setStat("0");
															//吧数据插入数据库
															int insertSelective = epcTaskProgressMapper.insertSelective(yzlEpcTaskProgress);
															System.out.println(insertSelective);
														}
														
													}
												}
											}
										}
									}
										Taskcel++;
								}
							}
						}
					}else {
						cell.setCellType(CellType.STRING);//将数字转为字符串输出
					}
				}
			}
		}
			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			return "2";
		}
		
	}
	
	/**
	 * 将数据处理插入数据库
	 * @param cy 地区的名称
	 * @param emark 工程的编号
	 * @param tmark 造林类别的编号
	 * @param dataValue 要插入的数据
	 * @param time 时间
	 * @return
	 */
	private int insert(String cy,String emark,String tmark,String dataValue,String time) {
		//根据名称查询地区
		YzlDistrict district = districtMapper.selectByCounty(cy);
		
		if (district!=null) {
			//创建对象
			YzlEpcTaskProgress yzlEpcTaskProgress = new YzlEpcTaskProgress();
			
			yzlEpcTaskProgress.setGclb(emark);
			yzlEpcTaskProgress.setZllb(tmark);
			yzlEpcTaskProgress.setJhnd(time);
			yzlEpcTaskProgress.setZynd(time);
			yzlEpcTaskProgress.setCitycode(district.getCitycode());
			yzlEpcTaskProgress.setCountycode(district.getAnumber());
			yzlEpcTaskProgress.setTaskprogress(Float.valueOf(dataValue));
			yzlEpcTaskProgress.setCreatetime(new Date());
			yzlEpcTaskProgress.setStat("0");
			
			//吧数据插入数据库
			int insertSelective = epcTaskProgressMapper.insertSelective(yzlEpcTaskProgress);
			//System.out.println(insertSelective);
			return insertSelective;
		}else {
			return 0;
		}
	}
	///-----------------------------与世隔绝
	
	private List<String> getLoginUserAuthoritys(){
		YzlUser loginUser = LoginUserUtils.getLoginUser();
		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(loginUser.getId().toString()));
		return authoritys;
	}
	
	//通过作业年度和地区行政编号查询任务下发数据根据县、工程类别、县级编号进行分组
	@Override
	public EasyUIResult queryTaskIssuedByAreaCodeAndYearGroupCountyAndGCLBAndZLLBAndAreaCodeAndGCLB(Integer page, Integer rows,
			HttpServletRequest request, String areaCode, String year,String GCLB) {
		taskIssuedDTOs.clear();
		//如果当前年是Null,则默认是当前时间的年
		if(StringUtils.isBlank(year)){
			year = new SimpleDateFormat("yyyy").format(new Date());
		}
		//获取当前登录用户的权限
		List<String> authoritys = getLoginUserAuthoritys();
		List<TaskIssuedDTO> TaskIssuedDTOList = epcTaskProgressMapper.queryTaskIssuedByGroupCountyAndGCLBAndZLLBAndAreaCodeAndGCLB(authoritys,areaCode,year,GCLB);
//		taskIssuedDTOs.add(TaskIssuedDTOList);
		EasyUIResult result = this.pageQueryUtils(TaskIssuedDTOList, page, rows);
		return result;
	}
	
	
	//查询市级数据并进行统计
	

	//查询市级数据并进行统计
	private List<Map<String,String>> queryCityDataAndCount(String year,String areaCode,List<Integer> stats,List<String> authoritys,String ZLLB,String keywork,String GCLB){
		taskIssuedDTOs.clear();
		List<Map<String,String>> countDataList = new ArrayList<>();
		List<Map<String,String>> resultList = new ArrayList<>();//数据结果返回页面List
		//查询任务下发的数据 并统计市级数据
		List<YzlEpcTaskProgress> epcTaskProgressList = epcTaskProgressMapper.queryCityData(year, areaCode, authoritys,ZLLB,GCLB,null);
		LinkedHashSet<String> hashSet = new LinkedHashSet<>();//存市编号
		for (YzlEpcTaskProgress yzlEpcTaskProgress : epcTaskProgressList) {//不会吧重复的市编号添加进去
			hashSet.add(yzlEpcTaskProgress.getCitycode());
		}
		int record = 0;
		//查询任务完成的数据 并统计市级数据
		List<YzlEpcAndTaskStaticti> epcAndTaskStatictiList = xbMapper.queryCityDataByParamGroupByCityAndGCLBAndZLLBAndJHNDAndZYND(year, stats, authoritys, areaCode,ZLLB,GCLB);
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
						int log = 0;
						for (int j = 0; j < epcAndTaskStatictiList.size(); j++) {
							YzlEpcAndTaskStaticti JepcAndTaskStaticti = epcAndTaskStatictiList.get(j);
							//把工程 市行政编号和造林类别和工程类别相同的进行合并
							if(IepcTaskProgress.getCitycode().equals(JepcAndTaskStaticti.getCity()) //判断市级行政编号是否相等
									&&IepcTaskProgress.getZllb().equals(JepcAndTaskStaticti.getZLLB()) //判断造林类别是否相等
										&&IepcTaskProgress.getGclb().equals(JepcAndTaskStaticti.getGCLB())){//判断工程类别是否相等
								log++;
								//获取任务工作完成的数量
								Double XTJSBMJDouble = Double.valueOf(JepcAndTaskStaticti.getXTJSBMJ());
								double zjh =XTJSBMJDouble/IepcTaskProgress.getTaskprogress().doubleValue();
								// 用任务完成数据除以任务发下数量得到  任务完成比例的结果   用结果向上取整保留小数后2位
								map.put("zjh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.format("%.2f", zjh*100)+"%");
								map.put("wc"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.valueOf(XTJSBMJDouble));
							}
						}
						if (log == 0) {
							map.put("zjh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), 0+"");
							map.put("wc"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), 0+"");
						}
						map.put("cityCode", IepcTaskProgress.getCitycode());//封装市级行政编号			
						//封装市级任务下发的数据 并保留小数点后4位
						map.put("jh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.format("%.4f", IepcTaskProgress.getTaskprogress()));
					}
					
					List flag = new ArrayList<>();//存储已经添加过的数据id
				/*	HashMap countDataMap = new HashMap<>();
					YzlEpcTaskProgress IepcTaskProgress = epcTaskProgressList.get(i);
					YzlDistrict district = districtMapper.selectByNumber(IepcTaskProgress.getCountycode());//根据县行政编号查询地区
					countDataMap.put("city", district.getCity());
					for (int j = 0; j < epcAndTaskStatictiList.size(); j++) {
						YzlEpcAndTaskStaticti JepcAndTaskStaticti = epcAndTaskStatictiList.get(j);
						if(IepcTaskProgress.getCitycode().equals(JepcAndTaskStaticti.getCity()) //判断市级行政编号是否相等
								&&IepcTaskProgress.getZllb().equals(JepcAndTaskStaticti.getZLLB()) //判断造林类别是否相等
									&&IepcTaskProgress.getGclb().equals(JepcAndTaskStaticti.getGCLB())){//判断工程类别是否相等
							//获取任务工作完成的数量
							Double XTJSBMJDouble = Double.valueOf(JepcAndTaskStaticti.getXTJSBMJ());
							double zjh =XTJSBMJDouble/IepcTaskProgress.getTaskprogress().doubleValue();
							// 用任务完成数据除以任务发下数量得到  任务完成比例的结果   用结果向上取整保留小数后2位
							countDataMap.put("zjh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.format("%.2f", zjh*100)+"%");
							countDataMap.put("wc"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.valueOf(XTJSBMJDouble));
						}
					}
					countDataMap.put("cityCode", IepcTaskProgress.getCitycode());//封装市级行政编号			
					//封装市级任务下发的数据 并保留小数点后4位
					countDataMap.put("jh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), String.format("%.4f", IepcTaskProgress.getTaskprogress()));
*/					//把所有统计好封装到List集合中
/*					if (record < epcTaskProgressList.size()) {
						countDataList.add(map);
					}*/
//					String filesUrl = IepcTaskProgress.getFilesUrl();//把文件路径进行拆分
					String filesUrl = IepcTaskProgress.getFilesUrl();
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
				for (String messageCountyCode : messageCountyCodeList) {
					String messageCityCode = AdministrativeCode.countyCodeConvertCityCode(messageCountyCode);
					if(cityCode.equals(messageCityCode)){
						mMap.put("newData", "<font color='red'>*</font>");
					}
				}
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
		taskIssuedDTOs.clear();
		List<Map<String,String>> countDataList = new ArrayList<>();
		List<Map<String,String>> resultList = new ArrayList<>();//数据结果返回页面List
		//查询任务下发的数据 并统计县级数据
		List<YzlEpcTaskProgress> epcTaskProgressList = epcTaskProgressMapper.queryCountyData(year, areaCode, authoritys,ZLLB,GCLB,null);
		//查询任务完成的数据 并统计县级数据
//		taskIssuedDTOs.add(epcTaskProgressList);
		//查询任务完成的数据 并统计市级数据
		List<YzlEpcAndTaskStaticti> epcAndTaskStatictiList = xbMapper.queryCountyDataByParamGroupByCountyAndGCLBAndZLLBAndJHNDAndZYND(year, stats, authoritys, areaCode,ZLLB,GCLB);
		try {
			for (int i = 0; i < epcTaskProgressList.size(); i++) {
				List flag = new ArrayList<>();//存储已经添加过的数据id
				HashMap countDataMap = new HashMap<>();
				YzlEpcTaskProgress IepcTaskProgress = epcTaskProgressList.get(i);
				YzlDistrict district = districtMapper.selectByNumber(IepcTaskProgress.getCountycode());//根据县行政编号查询地区
				double taskprogress=0L;
				if(IepcTaskProgress.getTaskprogress()!=null){
					taskprogress = IepcTaskProgress.getTaskprogress();//获取任务下发的数据量
				}
				countDataMap.put("city", district.getCity());
				countDataMap.put("county", district.getCounty());
				int log = 0;
				for (int j = 0; j < epcAndTaskStatictiList.size(); j++) {
					YzlEpcAndTaskStaticti JepcAndTaskStaticti = epcAndTaskStatictiList.get(j);
					if(IepcTaskProgress.getCountycode().equals(JepcAndTaskStaticti.getCounty()) //判断县级行政编号是否相等
							&&IepcTaskProgress.getZllb().equals(JepcAndTaskStaticti.getZLLB())	//判断造林类别是否相等
								&&IepcTaskProgress.getGclb().equals(JepcAndTaskStaticti.getGCLB())){ //判断工程类别是否相等
						log++;
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
				if (log == 0) {
					countDataMap.put("zjh"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(),  0);
					countDataMap.put("wc"+IepcTaskProgress.getZllb()+"Y"+IepcTaskProgress.getGclb(), 0);
				}
				countDataMap.put("cityCode", IepcTaskProgress.getCitycode());//封装市级行政编号
//				String filesUrl = IepcTaskProgress.getFilesUrl();//把文件路径进行拆分
				String filesUrl = IepcTaskProgress.getFilesUrl();
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
						List<String> messageCountyCodeList= messageMapper.queryCountyCodeByStatuAndCountyCode(authoritys, keywork);//查询更新记录数
						for (String messageCountyCode : messageCountyCodeList) {
							if(countyCode.equals(messageCountyCode)){
								mMap.put("newData", "<font color='red'>*</font>");
							}
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
	
	
	@Override
	public EasyUIResult queryTaskData(Integer page, Integer rows, String year, String areaCode,String ZLLB,String GCLB) {
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
		for (Map<String, String> map : resultList) {
			taskIssuedDTOs.add(map);
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

	
	@Override
	public YzlResult addData(List<String> taskNumbers, String year, String countyCode) {
		if(StringUtils.isBlank(year)){
			logger.error("任务计划或作业年度为空: year="+year);
			throw new YzlException(ResultEnum.ZYND_OR_JHND_IS_NULL);
		}
		if(StringUtils.isBlank(countyCode)){
			logger.error("地区行政编号为空: CountyCode="+countyCode);
			throw new YzlException(ResultEnum.COUNTY_OR_CITY_CODE_IS_NULL);
		}
		long flag=0L;//记录数据插入的 次数
		YzlUser loginUser = LoginUserUtils.getLoginUser();//获取当前登录用户
		YzlEpcTaskProgress epcTaskProgress = new YzlEpcTaskProgress();//数据添加 封装数据的对象
		epcTaskProgress.setZynd(year);//封装作业年度
		epcTaskProgress.setJhnd(year);//封装计划年度
		epcTaskProgress.setCountycode(countyCode);//封装县编号
		epcTaskProgress.setCreator(loginUser.getName());//创建人								//当前登录用户			
		//县行政编号的前4位 是市编号
		String cityCode = countyCode.substring(0, 4);//截取 市行政编号
		epcTaskProgress.setCitycode(cityCode);//封装市行政编号
		for (String param : taskNumbers) {
			if(param.contains("jh") && param.contains("Y")){
				String taskDataKeyAndValue = param.split("jh")[1];//截取掉jh前缀
				String[] KeyAndValue = taskDataKeyAndValue.split("Y");//把key=(ZLLB+GCLB)  value任务下发的基础 截取分开
				if(KeyAndValue.length==2){
					String ZLLB = KeyAndValue[0];//获取ZLLB
					String taskDataGCLBAndValue = KeyAndValue[1];
					if(StringUtils.isBlank(taskDataGCLBAndValue)){
						continue;
					}
					if(taskDataGCLBAndValue.contains(":")){
						String[] GCLBAndValue = taskDataGCLBAndValue.split(":");
						if (GCLBAndValue.length == 2) {
							String GCLB = GCLBAndValue[0];//获取工程类别3
							float newtaskBaseNumber=0f;//存储任务下发数据的基数
							if(!StringUtils.isBlank(GCLBAndValue[1])&&CheckStringIsNumberUtils.isFloat(GCLBAndValue[1])){
								newtaskBaseNumber = Float.valueOf(GCLBAndValue[1]);//获取下发任务的基数
							}else{
								logger.error("任务下发的基数不是纯数字：taskBase="+GCLBAndValue[1]);
								throw new YzlException(ResultEnum.TASKBASE_NOT_IS_NUMBER);
							}
							//数据封装
							epcTaskProgress.setGclb(GCLB);
							epcTaskProgress.setZllb(ZLLB);
							//epcTaskProgress.setStat("0");
							//根据工程类别、计划年度、作业年度、造林类别和县级编号 查询该数据是否已添加过
							List<YzlEpcTaskProgress> epcTaskProgressList = epcTaskProgressMapper.queryByGCLBAndJHNDAndZYNDAndZLLBAndCountyCode(epcTaskProgress);
							Date currentTime = new Date();
							if(epcTaskProgressList.size()>0){
								for (YzlEpcTaskProgress yzlEpcTaskProgress : epcTaskProgressList) {
									float oldNumber = yzlEpcTaskProgress.getTaskprogress()==null?0f:yzlEpcTaskProgress.getTaskprogress();
									yzlEpcTaskProgress.setUpdatetime(currentTime);
									yzlEpcTaskProgress.setTaskprogress(oldNumber+newtaskBaseNumber);
									flag += epcTaskProgressMapper.updateByZYNDAndJHNDAndGCLBAndZLLBAndCountyCode(yzlEpcTaskProgress);//执行更新.
								}
							}else{
								epcTaskProgress.setCreatetime(currentTime);//创建时间
								epcTaskProgress.setUpdatetime(currentTime);//创建时间
								epcTaskProgress.setTaskprogress(Float.valueOf(GCLBAndValue[1]));
								epcTaskProgress.setStat("0");
								flag += epcTaskProgressMapper.insert(epcTaskProgress);//执行插入
							}
							
							//设置为最新任务
							YzlMessage message = new YzlMessage();
							message.setCountycode(countyCode);
							message.setCreatetime(currentTime);
							message.setNumber(1L);
							message.setJhnd(year);
							message.setZynd(year);
							message.setGclb(GCLB);
							message.setZllb(ZLLB);
							messageMapper.insert(message);
						}
					}
				}
			}
		}
		if(flag==0){
			logger.error("任务下发数据插入失败");
			throw new YzlException(ResultEnum.DATA_INSERT_FAILURE);
		}
		return YzlResult.ok();
	}
	

	@Override
	public YzlResult updateData(List<String> taskNumbers, String year, String countyCode, String uploadFiles) {
		if(StringUtils.isBlank(year)){
			logger.error("任务计划或作业年度为空: year="+year);
			throw new YzlException(ResultEnum.ZYND_OR_JHND_IS_NULL);
		}
		if(StringUtils.isBlank(countyCode)){
			logger.error("地区行政编号为空: CountyCode="+countyCode);
			throw new YzlException(ResultEnum.COUNTY_OR_CITY_CODE_IS_NULL);
		}
		if(StringUtils.isBlank(year)){
			logger.error("任务计划或作业年度为空: year="+year);
			throw new YzlException(ResultEnum.ZYND_OR_JHND_IS_NULL);
		}
		if(StringUtils.isBlank(countyCode)){
			logger.error("地区行政编号为空: CountyCode="+countyCode);
			throw new YzlException(ResultEnum.COUNTY_OR_CITY_CODE_IS_NULL);
		}
		long number=0l;//记录数据插入的 次数
		YzlEpcTaskProgress epcTaskProgress = new YzlEpcTaskProgress();//数据添加 封装数据的对象
		epcTaskProgress.setZynd(year);//封装作业年度
		epcTaskProgress.setJhnd(year);//封装计划年度
		if(!CheckStringIsNumberUtils.isInteger(countyCode)){//判断是否是纯数字
			//纯数字则为县级编号，，不是则为县级名称
			YzlDistrict district = districtMapper.selectByCounty(countyCode);//根据县的名称查询地区
			countyCode = district.getAnumber();//获取县级编号
		}
		epcTaskProgress.setCountycode(countyCode);//封装县编号
		//县行政编号的前4位 是市编号
		String cityCode = countyCode.substring(0, 4);//截取 市行政编号
		epcTaskProgress.setCitycode(cityCode);//封装市行政编号
		YzlUser user = LoginUserUtils.getLoginUser();//获取当前登录用户
		String name = user.getName();//获取用户名
		for (String param : taskNumbers) {
			if(param.contains("jh") && param.contains("Y")){
				String taskDataKeyAndValue = param.split("jh")[1];//截取掉jh前缀
				String[] KeyAndValue = taskDataKeyAndValue.split("Y");//把key=(ZLLB+GCLB)  value任务下发的基础 截取分开
				if(KeyAndValue.length==2){
					String ZLLB = KeyAndValue[0];//获取ZLLB
					String taskDataGCLBAndValue = KeyAndValue[1];
					if(taskDataGCLBAndValue.contains(":")){
						String[] GCLBAndValue = taskDataGCLBAndValue.split(":");
						if(GCLBAndValue.length==2){
							String GCLB = GCLBAndValue[0];//获取工程类别
							String taskBase = GCLBAndValue[1];//获取下发任务的基数
							//数据封装
							epcTaskProgress.setGclb(GCLB);//工程类别
							epcTaskProgress.setZllb(ZLLB);//造林类别
							Date currentTime = new Date();
							epcTaskProgress.setUpdatetime(currentTime);
							epcTaskProgress.setModifier(name);//修改人
							if(!CheckStringIsNumberUtils.isDecimals(taskBase)){//判断任务基数是否是纯数字
								logger.error("任务下发的基数不是纯数字：taskBase="+taskBase);
								throw new YzlException(ResultEnum.TASKBASE_NOT_IS_NUMBER);
							}
							epcTaskProgress.setTaskprogress(Float.valueOf(taskBase));
							//根据工程类别、计划年度、作业年度、造林类别和县级编号 查询该数据是否已添加过
							int count = epcTaskProgressMapper.countByGCLBAndJHNDAndZYNDAndZLLBAndCountyCode(epcTaskProgress);
							if(count==0){//数据未添加过
//								epcTaskProgress.setFilesUrl(uploadFiles);//上传插入的文件
								epcTaskProgress.setStat("0");
								epcTaskProgress.setFilesUrl(uploadFiles);
								epcTaskProgress.setCreator(name);//数据创建人
								epcTaskProgress.setCreatetime(currentTime);//创建时间
								//执行插入
								number += epcTaskProgressMapper.insertSelective(epcTaskProgress);
							}else{
								//执行更新
								number += epcTaskProgressMapper.updateByZYNDAndJHNDAndGCLBAndZLLBAndCountyCode(epcTaskProgress);//执行更新
							}
							//设置为最新任务
							YzlMessage message = new YzlMessage();
							message.setCountycode(countyCode);
							message.setCreatetime(currentTime);
							message.setNumber(1L);
							message.setJhnd(year);
							message.setZynd(year);
							message.setGclb(GCLB);
							message.setZllb(ZLLB);
							messageMapper.insert(message);
						}
					}
				}
			}
		}
		if(number==0){
			logger.error("任务下发数据更新失败");
			throw new YzlException(ResultEnum.DATA_INSERT_FAILURE);
		}

		return YzlResult.ok();
	}
	
	
	//上传
	@Override
	public Map uploadFile(MultipartFile[] files,HttpServletResponse response,HttpServletRequest request) {
		//生成uuid
		UUID uuid = UUID.randomUUID();
		InputStream inputStream = null;
		String originalFilename = null;
		String fileUrl = null;
		for (MultipartFile multipartFile : files) {
			try {
				inputStream = multipartFile.getInputStream();
				originalFilename = multipartFile.getOriginalFilename();//获取文件的名称以及各式
				//随机生成uuid
				String contextPath = request.getContextPath();
				//String string = originalFilename.substring(originalFilename.indexOf("."), originalFilename.length());//截取文件的后缀
				//新的文件名
				//String fileName = uuis+string;
				//获取上传的路径
				String url = request.getServletContext().getRealPath("");
				//获取上传的路径
				String folder = request.getServletContext().getRealPath(uploadFolderURI);
				//获取当前的日期,利用当前的日期当文件夹
				Date date = new Date();
				//日期转字符串
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMdd");
				String format = simpleDateFormat.format(date);
				folder+=format;//拼接成绝对路径
				File file = new File(folder);
				//创建文件目录v
				// 如果目录不存在就创建
			   if (!file.exists()) {
				   file.mkdirs();//创建文件
			   }
			   //存放数据库存储文件名(唯一标识)
			   String fileName=uuid.toString()+FileSpliceMark.TASK_ISSUED_FILE_SPLICE_MARK.getMark()+originalFilename;
			   //文件路径+文件名拼接返回页面
			   fileUrl=format+"\\"+fileName;
			   //获取输出流
				FileOutputStream os = new FileOutputStream(file+"\\"+fileName);
				//将流读取并保存到指定目录
				int tem=0;
				byte byteData [] = new byte[1024];
				while ((tem=inputStream.read(byteData))!=(-1)) {
					os.write(tem);
				}
				//将上传的文件保存到相应的文件目录下
				os.flush();
				os.close();
				//关闭流
				inputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Map resultMap = new HashMap<>();
		InetAddress localHost = null;
		try {
			localHost = Inet4Address.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		resultMap.put("url", "http://"+localHost+":8080/yzl-web/"+uploadFolderURI+originalFilename);
		resultMap.put("fileName", originalFilename);
		resultMap.put("status", 200);
		resultMap.put("fileUrl", fileUrl);
		return resultMap;
	}

	@Override
	public YzlResult delete_uploadFileService(String deleteFileName, String countyCode, String year,String uploadFiles,String cityName,HttpServletRequest request) {
		if(StringUtils.isBlank(year)){
			logger.error("年份为空");
			throw new YzlException(ResultEnum.COUNTYCODE_YEAR_ERROR);
		}
		if(StringUtils.isBlank(countyCode)&&StringUtils.isBlank(cityName)){
			logger.error("县级行政编号为空");
			throw new YzlException(ResultEnum.COUNTY_IS_NULL);
		}
		if(countyCode!=null&&!CheckStringIsNumberUtils.isInteger(countyCode)){//判断是否是纯数字
			//纯数字则为县级编号，，不是则为县级名称
			YzlDistrict district = districtMapper.selectByCounty(countyCode);//根据县的名称查询地区
			countyCode = district.getAnumber();//获取县级编号
		}
		
		String realPath = request.getServletContext().getRealPath("/uploadFile"+"/"+deleteFileName);
		//刪除文件
		File file = new File(realPath);
		if(file.exists()){//判断文件是否存在
			file.delete(); //执行删除
		}
		if(file.exists()){//文件删除完成后在判断文件是否还存在，存在则删除失败
			return YzlResult.build(400, "文件删除失败");			
		}
/*		if(deleteFileName.indexOf("\\")>0||deleteFileName.indexOf("&")>0)
		{
			deleteFileName = deleteFileName.substring(deleteFileName.indexOf("\\")+1, deleteFileName.indexOf("=="));
		}
		deleteFileName=deleteFileName.trim();
		String cityCode=null;
		if(cityName!=null&&!CheckStringIsNumberUtils.isInteger(cityName)){
			cityCode = districtMapper.selectCitycodeByCity(cityName);//根据市的名称查询地区
		}
		YzlUser user = LoginUserUtils.getLoginUser();
		//刪除数据库中的文件
		epcTaskProgressMapper.updateByZYNDAndJHNDAndGCLBAndZLLBAndCountyCode(epcTaskProgress);*/
		return this.updateUploadFileData(year, countyCode, uploadFiles, cityName);
	}

	
	
	@Override
	public YzlResult downloadUploadFile(String fileUrl,HttpServletRequest request,HttpServletResponse response) {
		String fileNmae=fileUrl.trim();//去空格
		
		String uri = request.getServletContext().getRealPath("");
		String url = uri+uploadFolderURI+fileNmae;
		File file = new File(url);
		try {
			if (!file.exists()) {
				response.sendError(404, "File not found!");
			}
			// 创建一个缓冲输入流对象
			BufferedInputStream br = new BufferedInputStream(new FileInputStream(file));
			byte[] buf = new byte[10240];
			int len = 0;
			response.reset(); //重新设定

			String fileName=fileUrl;
			System.out.println("fineNmae="+fileName);
			if(fileUrl.contains(FileSpliceMark.TASK_ISSUED_FILE_SPLICE_MARK.getMark())){//判断是否包含文件标识符
				String[] fileMarkAndfileName = fileUrl.split(FileSpliceMark.TASK_ISSUED_FILE_SPLICE_MARK.getMark());//将唯一标识和文件名进行拆分
				if(fileMarkAndfileName.length==2){
					fileName = fileMarkAndfileName[1];//获取文件名
				}
			}
			// 3.设置两个头和一个流
			// 动态获得文件的MIME的类型:
			String mimeType = request.getServletContext().getMimeType(fileName);
			response.setContentType(mimeType);
			String dowloadFileName = URLEncoder.encode(fileName,"UTF-8");
			response.setHeader("content-disposition", "attachment;filename="+dowloadFileName);
			//获取的一个向浏览器输出的流
			ServletOutputStream servletOutputStream = response.getOutputStream();
			// 开始输出
			while ((len = br.read(buf)) > 0)
			{
				servletOutputStream.write(buf, 0, len);
			}
			// 关闭流对象
			br.close();
			servletOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return YzlResult.ok();
	}

	@Override
	public YzlResult updateUploadFileData(String year, String countyCode, String uploadFiles,String cityName) {
		if(StringUtils.isBlank(year)){
			logger.error("年份为空");
			throw new YzlException(ResultEnum.COUNTYCODE_YEAR_ERROR);
		}
		YzlUser user = LoginUserUtils.getLoginUser();
		if(StringUtils.isBlank(countyCode)&&StringUtils.isBlank(cityName)){
			logger.error("县级行政编号为空");
			throw new YzlException(ResultEnum.COUNTY_IS_NULL);
		}
		
		String cityCode="";
		if(StringUtils.isNotEmpty(countyCode)&&!CheckStringIsNumberUtils.isInteger(countyCode)){//判断是否是纯数字
			//纯数字则为县级编号，，不是则为县级名称
			YzlDistrict district = districtMapper.selectByCounty(countyCode);//根据县的名称查询地区
			countyCode = district.getAnumber();//获取县级编号
			cityCode = district.getCitycode();
		}else if(StringUtils.isNotEmpty(cityName)&&!CheckStringIsNumberUtils.isInteger(cityName)){
			cityCode = districtMapper.selectCitycodeByCity(cityName);//根据市的名称查询地区
		}
		int flag = epcTaskProgressMapper.updatefilesUrlByCountCodeAndZYND(year, countyCode,cityCode, uploadFiles, user.getName(),new Date());
		if(flag==0){
			logger.error("文件上传插入数据库失败");
			return YzlResult.build(400, "文件上传失败");
		}
		//设置为最新任务
		//YzlMessage message = new YzlMessage();
		//message.setCountycode(countyCode);
		//message.setCreatetime(new Date());
		//message.setNumber(1L);
		//messageMapper.insert(message);
		return YzlResult.ok();
	}
	
}
