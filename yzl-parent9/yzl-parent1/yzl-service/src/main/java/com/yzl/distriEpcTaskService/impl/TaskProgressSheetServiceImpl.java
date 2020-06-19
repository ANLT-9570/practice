package com.yzl.distriEpcTaskService.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.ServerEndpoint;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yzl.mapper.YzlDistrictMapper;
import com.yzl.mapper.YzlEpcTaskProgressMapper;
import com.yzl.mapper.YzlMenuMapper;
import com.yzl.mapper.YzlTaskProgressSheetMapper;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlTaskProgressSheet;
import com.yzl.pojo.YzlTaskProgressSheetExample;
import com.yzl.pojo.YzlUser;
import com.yzl.utils.CheckStringIsNumberUtils;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.ObjTOJson;
import com.yzl.utils.YzlResult;
import com.yzl.utils.enums.ResultEnum;
import com.yzl.utils.enums.TaskProgressStatusEnum;
import com.yzl.utils.enums.TaskWorkSatusEnum;
import com.yzl.utils.exception.YzlException;
import com.yzl.utils.vo.DrilldownNode;
import com.yzl.utils.vo.HighchartsResultVO;
@Service
public class TaskProgressSheetServiceImpl implements com.yzl.distriEpcTaskService.TaskProgressSheetService {

	
	@Autowired
	private YzlTaskProgressSheetMapper taskProgressSheetMapper;
	@Autowired
	private YzlDistrictMapper districtMapper;
	
	private List<YzlTaskProgressSheet> taskProgressSheets;
	
	@Autowired
	private YzlEpcTaskProgressMapper epcTaskProgressMapper;
	@Autowired
	private YzlMenuMapper menuMapper;
	

	
	//分页查询所有
	@Override
	public EasyUIResult pageQuery(HttpServletRequest request ,int page, int rows, String searchKey,String area,String year) {
		List<YzlTaskProgressSheet> taskProgressSheetList = new ArrayList<>();
		//取当前登录用户
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		//取当前用户所拥有的权限
		List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		if(StringUtils.isBlank(searchKey)){
			//0表示查询所有
			System.out.println(area+year);
			taskProgressSheetList = taskProgressSheetMapper.queryByUserPower(PermsList,area,year);
		}else{
			taskProgressSheetList = taskProgressSheetMapper.searchKey(PermsList,searchKey);
		}
		EasyUIResult easyUIResult = this.pageQueryProgressUtils(taskProgressSheetList, page,rows);
		//0默认统计所有数据s
		//Integer total = taskProgressSheetMapper.countCreateTimeAlsoDistinct(0);
		//easyUIResult.setTotal(total);
		return easyUIResult;
	}
	
	//分页查询 根据工程id
	@Override
	public EasyUIResult pageQueryByEcode(HttpServletRequest request,Integer page, Integer rows,Integer ecode,String area,String year,String searchKey){
		//List<YzlTaskProgressSheet> taskProgressSheetList = taskProgressSheetMapper.queryTaskDistrictByEcode(Ecode);
		List<String> marks = new ArrayList<>();
		List<YzlTaskProgressSheet> taskProgressSheetList = new ArrayList<>();
		//取当前登录用户
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		//取当前用户所拥有的权限
		List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		//取当前用户所拥有未完成任务权限
		for (String mark : PermsList) {
			//排除已完成的任务进度
			if(!mark.contains(TaskProgressStatusEnum.TASKPROGRESS_SUCCEED.getCode())){
				//添加未完成任务标识
				marks.add(mark);
			}
		}
		//判断搜索关键字是否为空
		if(StringUtils.isBlank(searchKey)){
		//根据工程id和任务状态相反值查询任务
			//taskProgressSheetList = taskProgressSheetMapper.queryByEcodeAndContraryMark(taskProgressSheet);
			taskProgressSheetList = taskProgressSheetMapper.queryByEcodeAndMark(marks, ecode,area,year);
		}else{
			//搜索关键字不为空，根据关键字进行搜索 
			taskProgressSheetList = taskProgressSheetMapper.searchKeyAndEcode(ecode, marks, searchKey);
		}
		 EasyUIResult easyUIResult = this.pageQueryProgressUtils(taskProgressSheetList,page,rows);
		//根据工程Id统计
		Integer total = taskProgressSheetMapper.countCreateTimeAlsoDistinct(ecode);
		easyUIResult.setTotal(total);
		return easyUIResult;
	}
	
	//分页查询
	public EasyUIResult pageQueryProgressUtils(List<YzlTaskProgressSheet> epcTaskProgresses,Integer page,Integer rows){
		//计算总记录数 向上取整
		//封装要返回的json
		List<Map> dataList = new ArrayList<>();
		//用来判断是否已经存放入集合中
		List idL = new ArrayList<>();
		for(int i=0;i<epcTaskProgresses.size();i++){
			//判断当前任务是否已经添加进入集合中
			if(!idL.contains(epcTaskProgresses.get(i).getId())){
				HashMap<Object, Object> epcTaskProgressesMap = new HashMap<>();
				//取任务进度
				YzlTaskProgressSheet taskProgressSheet = epcTaskProgresses.get(i);
				//添加第一个任务进入集合中
				//任务id和工程id拼为字符串 作为页面fild的值
				String k = taskProgressSheet.getTpcode()+ "T" +taskProgressSheet.getEpcode() ;
				epcTaskProgressesMap.put(k , taskProgressSheet.getTaskprogress());
				
				//添加地区id
				YzlDistrict district = taskProgressSheet.getDistrict();
				epcTaskProgressesMap.put("id", taskProgressSheet.getEpcode());
				 epcTaskProgressesMap.put("city",district.getCity());
				epcTaskProgressesMap.put("county", district.getCounty());
				epcTaskProgressesMap.put("mark", taskProgressSheet.getMark());
				epcTaskProgressesMap.put("dcode", district.getDcode());
				epcTaskProgressesMap.put("createtime", taskProgressSheet.getCreateTimetoString());
				String taskProgressMark = taskProgressSheet.getMark();
				if(taskProgressMark.contains(TaskProgressStatusEnum.TASKPROGRESS_SUCCEED.getCode())){
					epcTaskProgressesMap.put("checkMark", "<font color='green'>已完成</font>");
				}else if(taskProgressMark.contains(TaskProgressStatusEnum.TASKPROGRESS_SEND_BACK.getCode())){
					epcTaskProgressesMap.put("checkMark", "<font color='red'>退回</font>");
				}else if(taskProgressMark.contains(TaskProgressStatusEnum.TASKPROGRESS_CITY_CHECK.getCode())){
					epcTaskProgressesMap.put("checkMark", "<font color='orange'>待市级审核</font>");
				}else if(taskProgressMark.contains(TaskProgressStatusEnum.TASKPROGRESS_PROVINCE_CHECK.getCode())){
					epcTaskProgressesMap.put("checkMark", "<font color='green'>待自治区审核</font>");	
				}else{
					epcTaskProgressesMap.put("checkMark", "<font color='blue'>任务进行中</font>");
				}
				for(int j=i+1;j<epcTaskProgresses.size();j++){
					//取任务进度
					YzlTaskProgressSheet ptaskProgress = epcTaskProgresses.get(j);
					//比较两个任务进度的地区是否相同和创建时间是否一致
					//&&taskProgressSheet.getEpcode()!=ptaskProgress.getEpcode()
					//&&taskProgressSheet.getUpdatetime().equals(ptaskProgress.getUpdatetime())
					if(taskProgressSheet.getDpcode()==ptaskProgress.getDpcode()
							&&taskProgressSheet.getMark().equals(ptaskProgress.getMark())){
						//地区项目则数据就存在同一组 项目显示接收json格式
						//任务id和工程id拼为字符串 作为页面fild的值
						String key =ptaskProgress.getTpcode()+"T"+ptaskProgress.getEpcode();
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
		//取页面要显示的数据
		List<Map> resultList = dataList.subList(beginIndex, lastIndex);
		//封装model
		result.setRows(resultList);
		result.setTotal(resultList.size());
		return result;
	}
	
	
	//提交任务
	public YzlResult submitTaskProgressSheet(HttpServletRequest request, Map<String, String> params){
		//地区id
		Integer dpcode=null;
		//工程id
		Integer ecode=null;
		//创建时间
		String databaseTime = null;
		//地区标识
		String mark=null;
		Set<String> ParamsSet = params.keySet();
		Iterator<String> iterator = ParamsSet.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			if(key.equals("id")){
				String ecodeStr = params.get(key);
				ecode = Integer.parseInt(ecodeStr);
				iterator.remove();
			}else if(key.equals("createtime")){
				databaseTime = params.get(key);
				iterator.remove();
			}else if(key.equals("mark")){
				//判断当前任务是否 是被退回过的
				mark = params.get(key);
				if(mark.contains(TaskProgressStatusEnum.TASKPROGRESS_SEND_BACK.getCode())){
					//删除被退回标志后缀
					mark = mark.split(TaskProgressStatusEnum.TASKPROGRESS_SEND_BACK.getCode())[0];
				}
				iterator.remove();
			}else if(key.equals("dcode")){
				String dcodeStr = params.get(key);
				iterator.remove();
			}
		}
/*		//把标志拆分成 市  、县
		if(mark==null){
			throw new YzlException(ResultEnum.DISTRICT_MARK_ISNULL);
		}
		String updateMark=null;
		if(mark.contains("-")){
			String[] citymarks = mark.split("-");
			//取市级标志
			updateMark = citymarks[0];
			//判断是否是自治区从退回
		}
*/
		for (String k : ParamsSet) {
			//当前K是任务id+"T"+工程id拼起来的， 所有要把任务id拆出来
			String[] epcTask = k.split("T");
			String task = null;
			//判断是否为数字   如果是数字则为任务id
			if(CheckStringIsNumberUtils.isInteger(epcTask[0])){
				//取第一个 任务Id
				task = epcTask[0];
				//封装Model
				YzlTaskProgressSheet taskProgressSheet = new YzlTaskProgressSheet();
				taskProgressSheet.setDpcode(dpcode);
				//取工程id
				taskProgressSheet.setEpcode(ecode);
				//重新设置 地区标识  为待市级审核
				taskProgressSheet.setMark(mark + TaskProgressStatusEnum.TASKPROGRESS_CITY_CHECK.getCode());
				System.out.println("mark="+mark);
				taskProgressSheet.setTpcode(Integer.parseInt(task));
				taskProgressSheet.setDatabasetime(databaseTime);
				System.out.println(taskProgressSheet);
				//数据插入基数表
				taskProgressSheetMapper.updateTaskProgressSheet(taskProgressSheet);
			}
		}
		return YzlResult.ok();
	}
	
	
	//修改任务
	@Override
	public YzlResult updateTaskProgressSheet(HttpServletRequest request, Map<String, String> params) {
		//YzlEpcTaskProgress epcTaskProgres= new YzlEpcTaskProgress();
		try {
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
				if(key.equals("id")){
					String ecodeStr = params.get(key);
					ecode = Integer.parseInt(ecodeStr);
					iterator.remove();
				}else if(key.equals("createtime")){
					databaseTime = params.get(key);
					iterator.remove();
				}else if(key.equals("dcode")){
					String dcodeStr = params.get(key);
					dpcode = Integer.parseInt(dcodeStr);
					iterator.remove();
				}
			}
			Date currentTime  = new Date();
			for (String k : ParamsSet) {
				//当前K是任务id+"T"+工程id拼起来的， 所有要把任务id拆出来
				String[] epcTask = k.split("T");
				String task = null;
				//判断是否为数字   
				if(CheckStringIsNumberUtils.isInteger(epcTask[0])){
					//取第一个 任务Id
					task = epcTask[0];
					//封装Model
					YzlTaskProgressSheet taskProgressSheet = new YzlTaskProgressSheet();
					taskProgressSheet.setDpcode(dpcode);
					taskProgressSheet.setUpdatetime(currentTime);
					//取工程id
					taskProgressSheet.setEpcode(ecode);
					//取当前登录用户
					YzlUser loginUser = LoginUserUtils.getLoginSession(request);
					taskProgressSheet.setModifier(loginUser.getUsername());
					taskProgressSheet.setTpcode(Integer.parseInt(task));
					taskProgressSheet.setDatabasetime(databaseTime);
					//取任务进度
					String progress = params.get(k);
					taskProgressSheet.setTaskprogress(Float.parseFloat(progress));
					//数据插入基数表
					taskProgressSheetMapper.updateTaskProgressSheet(taskProgressSheet);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return YzlResult.build(400, "发布修改失败");
		}
		return YzlResult.ok();
	}
	//退回任务
	@Override
	public YzlResult sendBack(HttpServletRequest request, Map<String, String> params) {
		//YzlEpcTaskProgress epcTaskProgres= new YzlEpcTaskProgress();
		try {
			String mark=null;
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
				if(key.equals("id")){
					String ecodeStr = params.get(key);
					ecode = Integer.parseInt(ecodeStr);
					iterator.remove();
				}else if(key.equals("createtime")){
					databaseTime = params.get(key);
					iterator.remove();
				}else if(key.equals("dcode")){
					String dcodeStr = params.get(key);
					dpcode = Integer.parseInt(dcodeStr);
					iterator.remove();
				}else if(key.equals("mark")){
					mark = params.get(key);
					iterator.remove();
				}
			}
			String updateMark=null;
/*			if(mark.equals("ROOT")){
				String markByDcode = districtMapper.findMarkByDcode(dpcode);
				String[] marks = markByDcode.split("-");
				if(marks.length==2){
					updateMark = marks[0];
				}
			}else{*/
			
			if(mark.contains(TaskProgressStatusEnum.TASKPROGRESS_CITY_CHECK.getCode())){
				updateMark = mark.split(TaskProgressStatusEnum.TASKPROGRESS_CITY_CHECK.getCode())[0];
			}else if(mark.contains(TaskProgressStatusEnum.TASKPROGRESS_PROVINCE_CHECK.getCode())){
				updateMark = mark.split(TaskProgressStatusEnum.TASKPROGRESS_PROVINCE_CHECK.getCode())[0];
			}
				//updateMark = districtMapper.findMarkByDcode(dpcode);	
			//}
			for (String k : ParamsSet) {
				//当前K是任务id+"T"+工程id拼起来的， 所有要把任务id拆出来
				String[] epcTask = k.split("T");
				String task = null;
				//判断是否为数字   
				if(CheckStringIsNumberUtils.isInteger(epcTask[0])){
					//取第一个 任务Id
					task = epcTask[0];
					//封装Model
					YzlTaskProgressSheet taskProgressSheet = new YzlTaskProgressSheet();
					//取工程id
					taskProgressSheet.setEpcode(ecode);
					//取当前登录用户
					YzlUser loginUser = LoginUserUtils.getLoginSession(request);
					taskProgressSheet.setModifier(loginUser.getUsername());
					taskProgressSheet.setTpcode(Integer.parseInt(task));
					taskProgressSheet.setDatabasetime(databaseTime);
					taskProgressSheet.setMark(updateMark+"back");
					System.out.println("mar退回k="+taskProgressSheet.getMark());
					//取任务进度
					//数据插入基数表
					taskProgressSheetMapper.updateTaskProgressSheet(taskProgressSheet);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return YzlResult.build(400, "发布修改失败");
		}
		return YzlResult.ok();
	}

	
	//审核通过任务进度
	@Override
	public YzlResult approve(HttpServletRequest request, Map<String, String> params) {
		//YzlEpcTaskProgress epcTaskProgres= new YzlEpcTaskProgress();
		try {
			String mark=null;
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
				if(key.equals("id")){
					String ecodeStr = params.get(key);
					ecode = Integer.parseInt(ecodeStr);
					iterator.remove();
				}else if(key.equals("createtime")){
					databaseTime = params.get(key);
					iterator.remove();
				}else if(key.equals("dcode")){
					String dcodeStr = params.get(key);
					dpcode = Integer.parseInt(dcodeStr);
					iterator.remove();
				}else if(key.equals("mark")){
					mark = params.get(key);
					iterator.remove();
				}
			}
			String updateMark=null;
			//判断是否是 自治区退回的任务
			//取当前登录用户
			YzlUser loginUser = LoginUserUtils.getLoginSession(request);
			//取当前用户所拥有的权限
			List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
			//取当前用户所拥有未完成任务权限
			if(mark.contains(TaskProgressStatusEnum.TASKPROGRESS_PROVINCE_CHECK.getCode())){
				//判断是否拥有待审核自治区权限
				if(PermsList.contains(TaskProgressStatusEnum.TASKPROGRESS_PROVINCE_AUDIT.getCode())){
					String firstMark = mark.split(TaskProgressStatusEnum.TASKPROGRESS_PROVINCE_CHECK.getCode())[0];
					//自治区审核通过，任务已完成并 通过审核
					updateMark = firstMark + TaskProgressStatusEnum.TASKPROGRESS_SUCCEED.getCode();
				}else {
					return YzlResult.build(303, "您没有审核该数据的权限,如需要请你联系超级管理员");
				}
			}else if(mark.contains(TaskProgressStatusEnum.TASKPROGRESS_CITY_CHECK.getCode())){
				//市级审核通过，提交到自治区审核
				//判断是否拥有待市级审核的权限
				if(PermsList.contains(mark)){
					String firstMark = mark.split(TaskProgressStatusEnum.TASKPROGRESS_CITY_CHECK.getCode())[0];
					//
					updateMark = firstMark + TaskProgressStatusEnum.TASKPROGRESS_PROVINCE_CHECK.getCode();
					//
				}else {
					return YzlResult.build(303, "您没有审核该数据的权限,如需要请你联系超级管理员");
				}
			}
			System.out.println("权限="+updateMark);
			for (String k : ParamsSet) {
				//当前K是任务id+"T"+工程id拼起来的， 所有要把任务id拆出来
				String[] epcTask = k.split("T");
				String task = null;
				//判断是否为数字   
				if(CheckStringIsNumberUtils.isInteger(epcTask[0])){
					//取第一个 任务Id
					task = epcTask[0];
					//封装Model
					YzlTaskProgressSheet taskProgressSheet = new YzlTaskProgressSheet();
					//取工程id
					taskProgressSheet.setEpcode(ecode);
					//取当前登录用户
/*					YzlUser user = (YzlUser) request.getSession().getAttribute("user");
					taskProgressSheet.setModifier(user.getUsername());*/
					taskProgressSheet.setTpcode(Integer.parseInt(task));
					taskProgressSheet.setDatabasetime(databaseTime);
					taskProgressSheet.setMark(updateMark);
					//取任务进度
					//数据插入基数表
					taskProgressSheetMapper.updateTaskProgressSheet(taskProgressSheet);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return YzlResult.build(400, "发布修改失败");
		}
		return YzlResult.ok();
	}
	
	
	/***
	 * 根据工程id查询所有已完成的任务
	 */
	@Override
	public EasyUIResult pageQueryFinishTaskByEcode(HttpServletRequest request,Integer page, Integer rows, Integer Ecode,String searchKey) {
		List<YzlTaskProgressSheet> taskProgressSheets = new ArrayList<>();
		//取当前登录用户
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		//取当前用户所拥有的权限
		List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		List<String> markList = new ArrayList<>();
		for (String mark : PermsList) {
			//获取当前用户查询 所有任务数据完成权限
			if(mark.contains(TaskProgressStatusEnum.TASKPROGRESS_SUCCEED.getCode())){
				markList.add(mark);
			}
		}
		List<YzlTaskProgressSheet> taskprogressList = taskProgressSheetMapper.queryTaskProgressGroupByDpcodeAndTpcode(PermsList);
		//判断搜索关键字，是否为空，如果不为空则进行搜索
		if(StringUtils.isBlank(searchKey)){
			PageHelper.startPage(page, rows);
			//根据工程id查询所有已完成的任务 ，如果工程id=0则查询所有已完成的任务
			taskProgressSheets  = taskProgressSheetMapper.queryByEcodeAndMark(markList,Ecode,null,null);

		}else{
			//根据关键字进行搜索
			taskProgressSheets = taskProgressSheetMapper.searchKeyAndEcodeMark(Ecode,markList, searchKey);
		}
		EasyUIResult easyUIResult = this.pageQueryProgressUtils(taskProgressSheets, page,rows);
		return easyUIResult;
	}

	/***
	 * 查询所有已完成的任务
	 */
	@Override
	public EasyUIResult pageQueryFinishTask(HttpServletRequest request,Integer page, Integer rows, String searchKey) {
		List<String> list = new ArrayList<>();
		list.add("荒山人工造林");
		list.add("迹地人工更新造林");
		list.add("低效林改造");
		list.add("封山育林");
		list.add("退耕还林");
		list.add("林下种植中药材");
		//取当前登录用户
		YzlUser loginUser = LoginUserUtils.getLoginSession(request);
		//取当前用户所拥有的权限
		List<String> PermsList = menuMapper.queryPermsByUserId(Integer.valueOf(String.valueOf(loginUser.getId())));
		//存储查看已完成任务权限
		List<String> markList = new ArrayList<>();
		for (String mark : PermsList) {
			//获取当前用户查询 所有任务数据完成权限
			if(mark.contains(TaskProgressStatusEnum.TASKPROGRESS_SUCCEED.getCode())){
				markList.add(mark);
			}
		}
		List<YzlTaskProgressSheet> taskProgressSheetsList = new ArrayList<>();
		List<YzlTaskProgressSheet> taskProgressSheetListTwo  = new ArrayList<>();
		EasyUIResult easyUIResult = new EasyUIResult();	
		//判断搜索关键字，是否为空，如果不为空则进行搜索
		if(StringUtils.isBlank(searchKey)){
			//根据权限查询所有任务进度，并通过地区id和任务id进行
			taskProgressSheetsList = taskProgressSheetMapper.queryTaskProgressGroupByDpcodeAndTpcode(markList);
			//根据权限查询所有任务进度
			taskProgressSheetListTwo = taskProgressSheetMapper.queryByMark(markList);

		}else{
			//根据权限和搜索关键字查询所有任务进度，并通过地区id和任务id进行
			taskProgressSheetsList = taskProgressSheetMapper.searchTaskProgressGroupByDpcodeAndTpcode(markList, searchKey);
			//根据权限和搜索关键字查询所有任务进度
			taskProgressSheetListTwo = taskProgressSheetMapper.searchKeyAndMark(markList, searchKey);	
		}
			
			//对结果进行封装
			List targetList = new ArrayList<>();
			//存储已经封装过的对象id
			List IdList = new ArrayList<>();
			for (int i=0;i<taskProgressSheetsList.size();i++) {
				YzlTaskProgressSheet taskProgressSheet = taskProgressSheetsList.get(i);
				System.out.println("");
				//判断当前任务名是否是需要求和的对象
				Float sum=0f;

				//判断当前任务进度已经进行封装过了   封装过不需要在封装s
				if(!IdList.contains(taskProgressSheetsList.get(i).getId())){
					//对对象进行页面需要的格式进行封装
					Map map  = new HashMap<>();
					//封装数据   key跟页面field对应
					map.put(taskProgressSheet.getTask().getTname(), taskProgressSheet.getTaskprogress());
					map.put("city", taskProgressSheet.getDistrict().getCity());
					map.put("county", taskProgressSheet.getDistrict().getCounty());
					map.put("planningTime", taskProgressSheet.getCreateTimetoString());
					if(list.contains(taskProgressSheet.getTask().getTname())){
						//对需要求和的任务进度进行求和
						sum+=taskProgressSheet.getTaskprogress();//合计
					}
					for (int j = i+1; j < taskProgressSheetsList.size(); j++) {
						
						//判断地区是否相等
						YzlTaskProgressSheet taskProgressSheetTwo = taskProgressSheetsList.get(j);
						if(list.contains(taskProgressSheetTwo.getTask().getTname())){
							sum+=taskProgressSheetTwo.getTaskprogress();
						}
						//判断地区是否等于   相等的则进行封装
						if(taskProgressSheet.getDpcode()==taskProgressSheetTwo.getDpcode())
						{
							map.put(taskProgressSheetTwo.getTask().getTname(), taskProgressSheetTwo.getTaskprogress());
							//将已经封装的对象的id 添加到集合中
							IdList.add(taskProgressSheetTwo.getId());
						}
					}



					for (YzlTaskProgressSheet taskProgressSheetTwo : taskProgressSheetListTwo) {
						if(taskProgressSheet.getDpcode()==taskProgressSheetTwo.getDpcode()){
							map.put(taskProgressSheetTwo.getTpcode()+"T"+taskProgressSheetTwo.getEpcode(), taskProgressSheetTwo.getTaskprogress());
							//计算sum
						}
					}
					//封装需要统计的任务的和
					map.put("sum", sum);
					//封装map
					targetList.add(map);
				}
			}
			//计算分页起始位置
			int beginIndex = (page-1)*rows;
			//计算分页结束位置
			int lastIndex = beginIndex+rows;
			//如果大于总记录数 则 分页最大索引为 总记录数
			if(lastIndex>targetList.size()){
				lastIndex=targetList.size();
			}
			//获取页面需要显示数据
			List result = targetList.subList(beginIndex, lastIndex);
			//封装数据和总记录数
			easyUIResult.setRows(result);
			easyUIResult.setTotal(result.size());
			//taskProgressSheets = taskProgressSheetMapper.queryByMark(markList);
			return easyUIResult;
	}

	@Override
	public List<HighchartsResultVO> queryTaskGroupByCity(HttpServletRequest request) {
		
/*		List<Map> taskProgressSheetHighchartsList = taskProgressSheetMapper.queryTaskGroupByCity();
		List<Map> epcTaskProgressHighchartsList = epcTaskProgressMapper.queryTaskGroupByCity();
		List<HighchartsResultVO> list = new ArrayList<>();
		for (Map taskProgressSheetMap : taskProgressSheetHighchartsList) {
			for (Map epcTaskProgressMap : epcTaskProgressHighchartsList) {
				Map<String,Object> map = new HashMap<>();
				if(taskProgressSheetMap.get("city").equals(epcTaskProgressMap.get("city"))){
				    Double	progress = (Double)taskProgressSheetMap.get("taskprogreses")/(Double)epcTaskProgressMap.get("taskprogreses");
				    HighchartsResultVO resultVO = new HighchartsResultVO();
				    String city = (String)taskProgressSheetMap.get("city");
				    resultVO.setName(city);
				    resultVO.setDrilldown(city);
				    resultVO.setY(progress*100);
				    DrilldownNode drilldownNode = new DrilldownNode();
				    drilldownNode.setName(city);
				    drilldownNode.setId(city);
				    List<Map> taskProgressSheetList= taskProgressSheetMapper.queryTaskGroupByCounty(city);
				    List<List> maps= new ArrayList<>();
				    for (Map taskProgressSheet : taskProgressSheetList) {
						List data = new ArrayList();
						data.add(taskProgressSheet.get("county"));
						data.add((Double)taskProgressSheet.get("taskprogress")*100);
						maps.add(data);
					}
				    drilldownNode.setData(maps);
				    resultVO.setDrilldownNode(drilldownNode);
				    list.add(resultVO);
				    continue;
				}
			}
		}*/
		return null;
	}
	
/*	public List<HighchartsResultVO> qcueryTaskGroupByCity(HttpServletRequest request) {
		List<String> citys= districtMapper.findDistinctCity();
		for (String city : citys) {
			
		}
		List<Map> taskProgressSheetHighchartsList = taskProgressSheetMapper.queryTaskGroupByCity();
		List<Map> epcTaskProgressHighchartsList = epcTaskProgressMapper.queryTaskGroupByCity();
		List<Map> list = new ArrayList<>();
		for (Map taskProgressSheetMap : taskProgressSheetHighchartsList) {
			for (Map taskProgressSheetc : epcTaskProgressHighchartsList) {
				Map<String,Object> map = new HashMap<>();
				if(taskProgressSheetc.get("name").equals(taskProgressSheetc.get("name"))){
				    double	Result = (Double)taskProgressSheetMap.get("y")/(Double)taskProgressSheetc.get("y");
				    map.put("name", taskProgressSheetMap.get("name"));
				    map.put("y", Result*100);
				    list.add(map);
				    continue;
				}
			}
		}
		return null;
	}*/
}
