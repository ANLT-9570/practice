package com.yzl.distriEpcTaskService.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzl.distriEpcTaskService.TaskService;
import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.mapper.YzlEpcMapper;
import com.yzl.mapper.YzlEpcTaskProgressMapper;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.mapper.YzlMessageMapper;
import com.yzl.mapper.YzlTaskMapper;
import com.yzl.mapper.YzlTaskProgressSheetMapper;
import com.yzl.mapper.YzlXbMapper;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlMessage;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlTaskExample;
import com.yzl.pojo.YzlTaskProgressSheet;
import com.yzl.pojo.YzlTaskExample.Criteria;
import com.yzl.pojo.YzlUser;
import com.yzl.pojo.YzlXb;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.LoginUtils;
import com.yzl.utils.TaskStatuUtils;
import com.yzl.utils.YzlResult;
import com.yzl.utils.YzlTaskNumberEpcResult;
import com.yzl.utils.dto.CountyTaskWorkingDTO;
import com.yzl.utils.enums.AuthorityEnum;
import com.yzl.utils.enums.ResultEnum;
import com.yzl.utils.enums.TaskProgressStatusEnum;
import com.yzl.utils.enums.TaskWorkSatusEnum;
import com.yzl.utils.exception.YzlException;

@Transactional
@Service
public class TaskServiceImpl implements TaskService{

	@Autowired
	private YzlTaskMapper taskMapper;
	@Autowired
	private YzlEpcTaskProgressMapper epcTaskProgressMapper;
	@Autowired
	private YzlTaskProgressSheetMapper taskProgressSheetMapper;
	@Autowired
	private YzlDistrictMapper districtMapper;
	@Autowired
	private YzlMenuMapper menuMapper;
	@Autowired
	private YzlXbMapper xbMapper;
	@Autowired
	private YzlEpcMapper epcMapper;

	@Autowired
	private YzlMessageMapper messageMapper;

	
/*    @Value("${IP_ADDRESS}")
    private String IP_ADDRESS;
    @Value("${PORT}")
    private String PORT;
    @Value("${UPLOAD_FILE_URI}")
    private  String UPLOAD_FILE_URI;
*/

	//查询所有
	@Override
	public EasyUIResult findByAll(String value,Integer page,Integer rows) {
		
		if (page==null) {
			page=1;
		}
		if (rows==null) {
			rows=10;
		}
		
		//设置分页信息
		PageHelper.startPage(page,rows);
		List<YzlTask> list=null;
		
		if (value==null || value.equals("")) {
			YzlTaskExample example=new YzlTaskExample();
			list = taskMapper.selectByExample(example);
		}else {
			list = taskMapper.selectByCondition(value);
		}
		
		PageInfo<YzlTask> pageInfo=new PageInfo<>(list);
		
		EasyUIResult result=new EasyUIResult();
		result.setRows(pageInfo.getList());
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	@Override
	public YzlTask findByName(String name) {
		YzlTaskExample example=new YzlTaskExample();
		Criteria criteria = example.createCriteria();
		criteria.andTnameEqualTo(name);
		List<YzlTask> list = taskMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public YzlResult addTask(YzlTask task) {
		
		int insert = taskMapper.insertSelective(task);
		if (insert > 0) {
			return YzlResult.ok(200);
		}
		return YzlResult.ok(400);
	}

	//批量删除
/*	@Override
	public YzlResult deleterTask(String[] ids) {
		
		List<YzlEpcTaskProgress> list2=new ArrayList<>();
		
		for (int i = 0; i < ids.length; i++) {
			//查询这个id在第三张表有没有关联
			YzlEpcTaskProgressExample example=new YzlEpcTaskProgressExample();
			com.yzl.pojo.YzlEpcTaskProgressExample.Criteria criteria=example.createCriteria();
			criteria.andTpcodeEqualTo(Integer.valueOf(ids[i]));
			
			List<YzlEpcTaskProgress> list = epcTaskProgressMapper.selectByExample(example);
			for (int j = 0; j < list.size(); j++) {
				list2.add(list.get(i));
			}
		}
		//如果有关联不能删除
		if(list2 != null && list2.size() > 0) {
			return YzlResult.ok(300);
		}
		
		int sum=0;
		//循环删除
		if (ids!=null && ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				int ko = taskMapper.deleteByPrimaryKey(Integer.valueOf(ids[i]));
				sum+=ko;
			}
		}
		if (sum==ids.length) {
			return YzlResult.ok(200);
		}
		return YzlResult.ok(400);
	}*/

	
	//根据工程id查询所有任务  当前适配与任务进度
	@Override
	public YzlResult queryByEpcEcode(HttpServletRequest request,int ecode,String year,String areaCode) {
		//如果year为空， 则用当前时间年份
		if(StringUtils.isBlank(year)){
			year = new SimpleDateFormat("yyyy").format(new Date());
		}
		YzlUser loginUser = LoginUserUtils.getLoginUser();
		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(loginUser.getId().toString()));
		//根据year 工程mark ，查询造林类别
		List<YzlTask> taskList = taskMapper.queryByTaskIssuedGCLB(ecode,year,authoritys,areaCode);
		List<YzlTask> resultList = new ArrayList<>();
		if(taskList != null && taskList.size()>0){
			for (YzlTask task : taskList) {
				//将任务id+工程id做页面的field
				task.setField(task.getEpc().getMark() +"T"+ task.getMark());
				resultList.add(task);
			}
			return YzlResult.ok(resultList);
		}
		return YzlResult.build(400, "未查询到任务列表");
	}
	
	//根据工程id查询所有任务 当前适配于任务进度管理
	@Override
	public YzlResult queryByXbGCLB(HttpServletRequest request,int ecode,String year,String disCode) {
		YzlUser user = LoginUserUtils.getLoginSession(request);
		//获取登录用户的权限
		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(user.getId().toString()));
		List<Integer> stats = new ArrayList<>();
		stats.add(TaskWorkSatusEnum.TASK_CITY_AUDIT.getCode());
		stats.add(TaskWorkSatusEnum.TASK_COUNTY_WORK.getCode());
		
		List<YzlTask> list = taskMapper.queryByXbGCLB(ecode,year,authoritys,disCode,stats);
		List<YzlTask> resultList = new ArrayList<>();
		if(list != null && list.size()>0){
			for (YzlTask task : list) {
				//将任务id和工程id用字符串拼接，作用于页面的field
				task.setField(task.getEpc().getMark()+"T"+task.getMark());
				resultList.add(task);
			}
			return YzlResult.ok(resultList);
		}
		return YzlResult.build(400, "未查询到任务列表");
	}

	
	//根据工程id分页查询所有任务进度
	@Override
	public EasyUIResult pageQueryProgressByEpc(HttpServletRequest request,Integer ecode, int page,int rows , String value,String area,String year){
		List<YzlEpcTaskProgress> epcTaskProgresses = new ArrayList<>();
		//取当前登陆用户
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		//取当前用户所拥有的权限
		List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		if(StringUtils.isBlank(value)){
			//epcTaskProgresses = epcTaskProgressMapper.queryTaskDistrictByEcode(PermsList,ecode);\
			epcTaskProgresses = epcTaskProgressMapper.queryTaskDistrictByEcodeAndAreaAndYear(PermsList,ecode,area,year);
		}else{
			epcTaskProgresses = epcTaskProgressMapper.searchKeyAndEcode(PermsList,ecode, value);
		}
		EasyUIResult result = this.pageQueryProgressUtils(epcTaskProgresses, page,rows);
		return result;
	}

	//TODO 放假回来处理
	//分页查询
	public EasyUIResult pageQueryProgressUtils(List<YzlEpcTaskProgress> epcTaskProgresses,Integer page,Integer rows){
		//如果数据为空，则直接返回空
		
		if(epcTaskProgresses==null && epcTaskProgresses.size()==0){
			return new EasyUIResult();
		}
		//计算总记录数 向上取整
 		//int total = (int) Math.ceil((double)epcTaskProgresses.size()/columns);
		//封装要返回的json
		List<Map> dataList = new ArrayList<>();
		//用来判断是否已经存放入集合中
		List idL = new ArrayList<>();
		for(int i=0;i<epcTaskProgresses.size();i++){
			//判断当前任务是否已经添加进入集合中
			if(!idL.contains(epcTaskProgresses.get(i).getId())){
				HashMap<Object, Object> epcTaskProgressesMap = new HashMap<>();
				//取任务进度
				YzlEpcTaskProgress eTaskProgress = epcTaskProgresses.get(i);
				//任务id和工程id拼为字符串 作为页面fild的值
				String k = eTaskProgress.getZllb()+ "T" +eTaskProgress.getGclb();
				//添加第一个任务进入集合中
				epcTaskProgressesMap.put(k , eTaskProgress.getTaskprogress());
				//把第每一组的地区添加到集合中
				//添加地区id
				epcTaskProgressesMap.put("id", eTaskProgress.getGclb());
				epcTaskProgressesMap.put("city",eTaskProgress.getDistrict().getCity());
				epcTaskProgressesMap.put("county", eTaskProgress.getDistrict().getCounty());
				epcTaskProgressesMap.put("dcode", eTaskProgress.getCitycode());
				epcTaskProgressesMap.put("creator", eTaskProgress.getCreator());
				epcTaskProgressesMap.put("JHND", eTaskProgress.getJhnd());
				epcTaskProgressesMap.put("ZYND", eTaskProgress.getZynd());
				for(int j=i+1;j<epcTaskProgresses.size();j++){
					//取任务进度
					YzlEpcTaskProgress ptaskProgress = epcTaskProgresses.get(j);
					//比较两个任务进度的地区是否相同和创建人/工程id/创建时间或修改时间 是否一致4						System.out.println("boolean="+((eTaskProgress.getGclb()==ptaskProgress.getGclb()&&eTaskProgress.getTpcode()==ptaskProgress.getTpcode())||eTaskProgress.getGclb()!= ptaskProgress.getGclb()));
					if(eTaskProgress.getCountycode()==ptaskProgress.getCountycode()
						//判断id相同则任务id不同 或 工程id不同 则任务可以相同   
							){
						//任务id和工程id拼为字符串 作为页面fild的值
						String key =ptaskProgress.getZllb()+"T"+ptaskProgress.getGclb();
						epcTaskProgressesMap.put(key, ptaskProgress.getTaskprogress());
						//把已经添加到集合中的任务进度的id添加到另一个集合中用于判断是否已经加入集合中
						idL.add(ptaskProgress.getId());	
					}
				}
				dataList.add(epcTaskProgressesMap);
			}
		}
		EasyUIResult result = new EasyUIResult();
		//计算分页起始位置
		int beginIndex = (page-1)*rows;
		//计算分页结束位置
		int lastIndex = beginIndex+rows;
		//如果大于总记录数 则 分页最大索引为 总记录数
		if(lastIndex>dataList.size()){
			lastIndex=dataList.size();
		}
		List<Map> resultList = dataList.subList(beginIndex, lastIndex);
		//封装页面需要显示的数据
		result.setRows(resultList);
		result.setTotal(resultList.size());
		return result;
	}
	
	//查询所有任务
	@Override
	public YzlResult findAll() {
		YzlTaskExample example = new YzlTaskExample();
		List<YzlTask> list = taskMapper.selectByExample(example);
		if(list!=null && list.size()>0){
			return YzlResult.ok(list);
		}
		return YzlResult.build(400, "任务表头查询失败!");
	}

	//查询所有任务
	@Override
	public List<YzlTask> queryAllByPerms(String year,String areaCode) {
		List<String> authoritys = LoginUtils.getLoginUserAuthority(menuMapper);
		List<YzlTask> list = new ArrayList<>();
		if(!authoritys.contains(AuthorityEnum.TASK_ISSUED_ADD.getPerms())){//判断是否包含任务下发
			//不包含则根据任务下发已有数据进行查询
			list = taskMapper.queryDISTINCTTnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCode(year, areaCode, authoritys, null);
		}else{
			YzlTaskExample example = new YzlTaskExample();
			list = taskMapper.selectByExample(example);
		}
		return list;
	}

	
	
	//下发任务   方法无效
	@Override
	public YzlResult addTaskProgress(HttpServletRequest request,String epcId, Map<String, String> params) {
		//YzlEpcTaskProgress epcTaskProgres= new YzlEpcTaskProgress();
		if(StringUtils.isBlank(epcId)){
			throw new YzlException(ResultEnum.PROJECT_ID_ISNULL);
		}
		//工程id
		Integer epcode = Integer.parseInt(epcId);
		//地区id
		Integer dpcode=null;
		Set<String> ParamsSet = params.keySet();
		Iterator<String> iterator = ParamsSet.iterator();
		while (iterator.hasNext()) {
			//获取工程id
			String key = iterator.next();
			if(key.equals("county")){
				//获取地区id
				String dpcodeStr =params.get(key);
				dpcode = Integer.parseInt(dpcodeStr);
				iterator.remove();
				break;
			}
		}


		if(Objects.isNull(dpcode)){
			throw new YzlException(ResultEnum.REGION_ID_ISNULL);
		}
		//线程同步锁
	    synchronized(this) {
			try {
				//让线程等待一秒防止出现创建时间相同
				Thread.sleep(1000);
				//根据地区id查询地区
				Integer dcode= dpcode;
				String mark = districtMapper.findMarkByDcode(dcode);
				if(StringUtils.isBlank(mark)){
					throw new YzlException(ResultEnum.DISTRICT_MARK_ISNULL);
				}
				Date currentTime  = new Date();
				for (String k : ParamsSet) {
					//当前K是任务id+"T"+工程id拼起来的， 所有要把任务id拆出来
					String[] epcTask = k.split("T");
					//取第一个 任务Id
					String task = epcTask[0];
					//封装Model
					YzlEpcTaskProgress epcTaskProgress = new YzlEpcTaskProgress();
					/*epcTaskProgress.setEpcode(epcode);
					epcTaskProgress.setDpcode(dpcode);
					//设置地区标识
					epcTaskProgress.setMark(mark);*/
					epcTaskProgress.setCreatetime(currentTime);
					epcTaskProgress.setUpdatetime(currentTime);
					//取当前登录用户
					YzlUser user = (YzlUser) request.getSession().getAttribute("user");
					epcTaskProgress.setCreator(user.getUsername());
				//	epcTaskProgress.setTpcode(Integer.parseInt(task));
					//取任务进度
					String progress = params.get(k);
					epcTaskProgress.setTaskprogress(Float.parseFloat(progress));
					//数据插入基数表
					epcTaskProgressMapper.insert(epcTaskProgress);

					//状态表示 为哪个地区的，或者上级审核中
					//taskProgressSheetMapper.insert(taskProgressSheet);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				return YzlResult.build(400, "发布任务失败");
			}
			
			return YzlResult.ok();
	    }
	}
	
	List<YzlEpcTaskProgress> findByCreateTime(HttpServletRequest request){
		//取当前登陆用户
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		//取当前用户所拥有的权限
		List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		List<YzlEpcTaskProgress> epcTaskProgresses = epcTaskProgressMapper.selectByCreateTime(PermsList,2018+"");
		return epcTaskProgresses;
	}
	
	//TODO 不能修改为添加的任务，，待改进
	//修改任务  方法无效
	@Override
	public YzlResult updateTaskProgress(HttpServletRequest request, Map<String, String> params) {
/*		try {
			//地区id
			Integer dpcode=null;
			//工程id
			Integer ecode=null;
			//创建时间
			String databaseTime = null;
			Set<String> ParamsSet = params.keySet();
			Iterator<String> iterator = ParamsSet.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				if(key.equals("county")){
					//获取地区id
					String dpcodeStr = params.get(key);
					if(CheckStringIsNumberUtils.isInteger(dpcodeStr)){
						dpcode = Integer.parseInt(dpcodeStr);
						
					}
					iterator.remove();
				}
				if(key.equals("id")){
					String ecodeStr = params.get(key);
					ecode = Integer.parseInt(ecodeStr);
					iterator.remove();
				}
				if(key.equals("createtime")){
					databaseTime = params.get(key);
					iterator.remove();
				}
			}
			Date currentTime  = new Date();
			for (String k : ParamsSet) {
				//当前K是任务id+"T"+工程id拼起来的， 所有要把任务id拆出来
				String[] epcTask = k.split("T");
				//判断是否为数字   
				if(CheckStringIsNumberUtils.isInteger(epcTask[0])&& StringUtils.isNotBlank(params.get(k))){
					//取第一个 任务Id
					String task = epcTask[0];
					//封装Model
					YzlEpcTaskProgress epcTaskProgress = new YzlEpcTaskProgress();
					epcTaskProgress.setDpcode(dpcode);
					epcTaskProgress.setUpdatetime(currentTime);
					//取工程id
					epcTaskProgress.setEpcode(ecode);
					//取当前登录用户
					YzlUser user = (YzlUser) request.getSession().getAttribute("user");
					epcTaskProgress.setCreator(user.getUsername());
					epcTaskProgress.setTpcode(Integer.parseInt(task));
					epcTaskProgress.setUpdatetime(currentTime);
					//取任务进度
					String progress = params.get(k);
					epcTaskProgress.setTaskprogress(Float.parseFloat(progress));
					epcTaskProgress.setDatabasetime(databaseTime);
					//数据插入基数表
					epcTaskProgressMapper.updateTaskProgress(epcTaskProgress);
				}

			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return YzlResult.build(400, "发布修改失败");
		}*/
		return YzlResult.ok();
	}

	@Override
	public YzlResult deleteByEcodeAndCreateTime(List<YzlEpcTaskProgress> params) {
		for (YzlEpcTaskProgress epcTaskProgress : params) {
			epcTaskProgressMapper.deleteByEcodeAndCreateTime(epcTaskProgress);
			//删除发布任务的同时把任务进度删除
			YzlTaskProgressSheet taskProgressSheet = new YzlTaskProgressSheet();
			BeanUtils.copyProperties(epcTaskProgress, taskProgressSheet);
			taskProgressSheetMapper.deleteByEcodeAndDpcodeAndCreateTime(taskProgressSheet);
		}
		return YzlResult.ok();
	}

	@Override
	public EasyUIResult searchKey(HttpServletRequest request,String searchKey,Integer page,Integer rows) {
		//取当前登陆用户
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		//取当前用户所拥有的权限
		List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		List<YzlEpcTaskProgress> list = epcTaskProgressMapper.searchKey(PermsList,searchKey);
		EasyUIResult result = this.pageQueryProgressUtils(list,page,rows);
		return null;
	}

	@Override
	public YzlResult deleterTask(String[] ids) {
		// TODO Auto-generated method stub
		return null;
	}

	//提交
	@Override
	public YzlResult sub(String[] subData,String time,String zllb,String [] countys) {
		
		String county = null;
		String ZYND = time;
		int result = 0;
		int tableResult = 0;
		
		LinkedHashSet<String> hashSet = new LinkedHashSet<>();
		
//		stats.add("0");
//		stats.add("3");
		
		for (String string : subData) {
			HashMap hashMap = JSONObject.parseObject(string, HashMap.class);
			String stat = (String)hashMap.get("stat");
			hashSet.add(stat);
		}
		List<String> stats = new ArrayList<>(hashSet);
		Integer code = TaskWorkSatusEnum.TASK_CITY_AUDIT.getCode();//状态1就是	审核
		
		for (String string : countys) {
			
			YzlDistrict district = districtMapper.selectByCounty(string);
			county = district.getAnumber();
			tableResult += xbMapper.updateByEpcTaskTable(zllb,county,ZYND,stats,code);
			result += xbMapper.updateByMark(null,county,null,ZYND,code.toString(),stats,zllb);
			
			
		}
		
		if (result > 0 || tableResult > 0) {
			
			/*for (String fl : subData) {
				HashMap<String,String> hashMap = JSONObject.parseObject(fl, HashMap.class);
				
				String _Countycode = hashMap.get("countyCode");
				if (_Countycode == null) {
					_Countycode = hashMap.get("countycode");
				}
				Set<Entry<String,String>> set = hashMap.entrySet();
				
				for (Entry<String, String> entry : set) {
					String key = entry.getKey();
					if (key.contains("T")) {
						
						String _zllb = key.substring(0, key.indexOf("T"));
						String _gclb = key.substring(key.indexOf("T")+1, key.length());
						//设置为最新任务
						YzlMessage message = new YzlMessage();
						message.setCountycode(_Countycode);
						message.setCreatetime(new Date());
						message.setNumber(1L);
						message.setJhnd(ZYND);
						message.setZynd(ZYND);
						message.setGclb(_gclb);
						message.setZllb(_zllb);
						messageMapper.insert(message);
					}
				}
				
			}*/
			
			return YzlResult.ok(200);
		}
		return YzlResult.ok(400);
	}

	//private 对json数组进行处理
	private List<HashMap<String, String>> dispose(String [] subData){
		
		List<HashMap<String, String>> list = new ArrayList<>();
		
		for (String string : subData) {//遍历数组
			HashMap<String, String> hashMap = new HashMap<>();
			String substring = string.substring(1, string.length()-1);//把头和尾去掉
			String[] split = substring.split(",");//根据都好进行分割
			
			for (String string2 : split) {//遍历分割后的数组
				
				String[] split2 = string2.split(":");//根据:冒号进行分割
				for (int i=0;i<split2.length;i++) {//遍历数组并且把分割好的数据弄成key，value形式放到map中
					if (i==1) {
						continue;
					}
					String substring2 = split2[i].substring(1, split2[i].length()-1);
					String substring3 = split2[i+1].substring(1, split2[i+1].length()-1);
					hashMap.put(substring2, substring3);
				}
			}
			list.add(hashMap);
			
		}
		return list;
	}

	
	//退回
	@Override
	public YzlResult back(String[] backData,String time,String zllb,String [] countys) {
		
		String county = null;
		String ZYND = time;
		int result = 0;
		int tableResult = 0;
		List<String> stats = new ArrayList<>();
		stats.add("1");
		Integer code = TaskWorkSatusEnum.TASK_PROVINCE_AUDIT.getCode();
		for (String string : countys) {
			
			YzlDistrict district = districtMapper.selectByCounty(string);
			county = district.getAnumber();
			tableResult += xbMapper.updateByEpcTaskTable(zllb,county,ZYND,stats,code);
			result += xbMapper.updateByMark(null,county,null,ZYND,code.toString(),stats,zllb);
		}
		if (result > 0 || tableResult > 0) {
			/*for (String fl : backData) {
				HashMap<String,String> hashMap = JSONObject.parseObject(fl, HashMap.class);
				
				String _Countycode = hashMap.get("countyCode");
				if (_Countycode == null) {
					_Countycode = hashMap.get("countycode");
				}
				Set<Entry<String,String>> set = hashMap.entrySet();
				
				for (Entry<String, String> entry : set) {
					String key = entry.getKey();
					if (key.contains("T")) {
						
						String _zllb = key.substring(0, key.indexOf("T"));
						String _gclb = key.substring(key.indexOf("T")+1, key.length());
						//设置为最新任务
						YzlMessage message = new YzlMessage();
						message.setCountycode(_Countycode);
						message.setCreatetime(new Date());
						message.setNumber(1L);
						message.setJhnd(ZYND);
						message.setZynd(ZYND);
						message.setGclb(_gclb);
						message.setZllb(_zllb);
						messageMapper.insert(message);
					}
				}
				
			}*/
			return YzlResult.ok(200);
		}
		return YzlResult.ok(400);
	}

	@Override
	public List<YzlTask> ChangedEpc(String time, String code, String epc) {
		//List<YzlTask> tasks = xbMapper.selectByCodeAndEpc(time,code,epc);
		Integer stat = TaskWorkSatusEnum.TASK_COUNTY_WORK.getCode();
		List<YzlTask> tasks = xbMapper.selectByCodeAndEpc(time,code,epc,String.valueOf(stat));
		for (YzlTask yzlTask : tasks) {
			yzlTask.setField(epc+"T"+yzlTask.getMark());
		}
		return tasks;
	}

	
	@Override
	public EasyUIResult epcData(String time, String code, String epc,Integer page,Integer rows) {
		
		YzlUser loginUser = LoginUserUtils.getLoginUser();
		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(loginUser.getId().toString()));
		//time 时间，	epc		工程编号, code   市，县，自治区的其中一个编号
		List<CountyTaskWorkingDTO> CountyTaskWorkingDTOList = xbMapper.seletByEpcAndTask(time,code,epc,authoritys,TaskStatuUtils.getTaskStatUnreviewed());
		return PageQueryTis(CountyTaskWorkingDTOList, page, rows);
	}

	//对数据进行处理
	private EasyUIResult PageQueryTis(List<CountyTaskWorkingDTO> CountyTaskWorkingDTOList,Integer page,Integer rows) {
		
		List<HashMap> result = new ArrayList<>();
		
		for (CountyTaskWorkingDTO countyTaskWorkingDTO : CountyTaskWorkingDTOList) {
			String city = countyTaskWorkingDTO.getCity();
			HashMap<Object, Object> hashMap = new HashMap<>();
			String county = countyTaskWorkingDTO.getCounty();
			YzlDistrict district = districtMapper.selectByNumber(county);
			hashMap.put("city", district.getCity());
			hashMap.put("county", district.getCounty());
			hashMap.put("JHND", countyTaskWorkingDTO.getJHND());
			hashMap.put("ZYND", countyTaskWorkingDTO.getZYND());
			hashMap.put(countyTaskWorkingDTO.getGCLB()+"T"+countyTaskWorkingDTO.getZLLB(), countyTaskWorkingDTO.getXTJSBMJ());
			
			if(countyTaskWorkingDTO.getStat() == TaskWorkSatusEnum.TASK_COUNTY_WORK.getCode()){
				hashMap.put("stat","<font color='red'>任务进行中</font>");
			}else if(countyTaskWorkingDTO.getStat() == TaskWorkSatusEnum.TASK_CITY_AUDIT.getCode()){
				hashMap.put("stat","<font color='green'>待审核</font>");
			}
			result.add(hashMap);
		}
		//起始也  等于   当前页减1乘以每页记录数
		int pages = (page-1)*rows;
		List<HashMap> resuleMap = new ArrayList<>();
		for (int i=pages;i<result.size();i++) {
			resuleMap.add(result.get(i));
		}
		EasyUIResult results = new EasyUIResult();
		results.setTotal(result.size());
		results.setRows(resuleMap);
		return results;
	}

	//审核
	@Override
	public YzlResult audit(String[] auditData,String time,String zllb,String [] countys) {
		String county = null;
		String ZYND = time;
		int result = 0;
		int tableResult = 0;
		List<String> stats = new ArrayList<>();
		stats.add("1");
		Integer code = TaskWorkSatusEnum.TASK_CITY_VERIFIED.getCode();
		
		for (String string : countys) {
			
			YzlDistrict district = districtMapper.selectByCounty(string);
			county = district.getAnumber();
			tableResult += xbMapper.updateByEpcTaskTable(zllb,county,ZYND,stats,code);
			result += xbMapper.updateByMark(null,county,null,ZYND,code.toString(),stats,zllb);
		}
		if (result > 0 || tableResult > 0) {
			/*for (String fl : auditData) {
				HashMap<String,String> hashMap = JSONObject.parseObject(fl, HashMap.class);
				
				String _Countycode = hashMap.get("countyCode");
				if (_Countycode == null) {
					_Countycode = hashMap.get("countycode");
				}
				Set<Entry<String,String>> set = hashMap.entrySet();
				
				for (Entry<String, String> entry : set) {
					String key = entry.getKey();
					if (key.contains("T")) {
						
						String _zllb = key.substring(0, key.indexOf("T"));
						String _gclb = key.substring(key.indexOf("T")+1, key.length());
						//设置为最新任务
						YzlMessage message = new YzlMessage();
						message.setCountycode(_Countycode);
						message.setCreatetime(new Date());
						message.setNumber(1L);
						message.setJhnd(ZYND);
						message.setZynd(ZYND);
						message.setGclb(_gclb);
						message.setZllb(_zllb);
						messageMapper.insert(message);
					}
				}
				
			}*/
			return YzlResult.ok(200);
		}
		return YzlResult.ok(400);
	}

	@Override
	public List<List<YzlXb>> checkOut(String eids,String year,String [] countys) {
		
		String[] split = eids.split(",");
		
		LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
		
		for (String string : split) {
			linkedHashSet.add(string);
		}
		
		List<List<YzlXb>> list = new ArrayList<>();
		for (String county : countys) {
			for (String eic : linkedHashSet) {
				List<YzlXb> yzlXbs = xbMapper.selectByEidAndTime(eic,year,county);
				if (yzlXbs!=null && yzlXbs.size()>0) {
					list.add(yzlXbs);
				}
			}
		}
		return list;
	}


	//---与世隔绝
	//获取用户权限
	private List<String> getAuthoritys(){
		YzlUser user = LoginUserUtils.getLoginUser();
		List<String> authoritys = menuMapper.queryPermsByUserId(Integer.valueOf(user.getId().toString()));
		return authoritys;
	}
	
	
	
	//获取任务下发数据表格的表头
	@Override
	public List<YzlTask> getTableHeader(String year, String disCode, String GCLB) {
		List<YzlTask> resultTaskList = new ArrayList<>();//返回结果的taskList
		List<String> authoritys = getAuthoritys();//获取当前登录用户的权限
		//根据权限 地区行政编号，年份查询 任务下发中的 task
		List<YzlTask> taskList = taskMapper.queryDISTINCTTnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCode(year, disCode, authoritys,GCLB);
		for (YzlTask task : taskList) {
			String ZLLB = task.getMark();//获取造林类别
			//根据权限 地区行政编号，年份，造林类别。 查询任务下发中的 epc
			List<YzlEpc> epcList = epcMapper.queryDISTINCTEnameAndMarkFormEpctaskprogressTableByZYNDAndCountyCode(year, disCode, authoritys, ZLLB,GCLB);
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
}
