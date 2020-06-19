package com.yzl.distriEpcTaskController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonObject;
import com.yzl.LogService.LogAnno;
import com.yzl.LogService.RedisCache;
import com.yzl.distriEpcTaskService.TaskService;
import com.yzl.planManagementService.MessageService;
import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlXb;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.YzlResult;
import com.yzl.utils.enums.ResultEnum;
import com.yzl.utils.exception.YzlException;

import net.sf.json.JSONObject;

@Controller
public class TaskController {


	@Autowired
	private TaskService taskService;
	
	@Autowired
	private MessageService messageService;
	//分页查询所有任务
	@RequestMapping("/show_task")
	@ResponseBody
	public EasyUIResult pagefindByAll(String value,Integer page,Integer rows){
		EasyUIResult result = taskService.findByAll(value,page,rows);
		return result;
	}
	
	@RequestMapping("/task/queryAll")
	@ResponseBody
	public List<YzlTask> queryAll(String year,String areaCode){
		List<YzlTask> taskList = taskService.queryAllByPerms(year, areaCode);
		return taskList;
	}
	
	/****
	 *  查询所有任务
	 * @param name
	 * @return
	 */
	@RequestMapping("/task/findAll")
	@ResponseBody
	public YzlResult findAll(){
		YzlResult result = taskService.findAll();
		return result;
	}
	
	//用户名校验
	@RequestMapping("/verifyTname")
	@ResponseBody
	public YzlTask findByName(String name) {
		
		YzlTask task = taskService.findByName(name);
		if (task==null) {
			return null;
		}
		return task;
	}
	
	//添加
	@RequestMapping("/addTask")
	@ResponseBody
	public YzlResult addTask(YzlTask task) {
		try {
			YzlResult result = taskService.addTask(task);
			return result;
		} catch (Exception e) {
			return YzlResult.ok(400);
			//throw new RuntimeException("插入出错了");
		}
	}
	
	//删除
	@RequestMapping("/deleterTask")
	@ResponseBody
	public YzlResult deleterTask(String [] ids) {
		 
		try {
			YzlResult result = taskService.deleterTask(ids);
			return result;
		} catch (Exception e) {
			return YzlResult.ok(400);
			//throw new RuntimeException("删除出错了");
		}
	}
	
	/***
	 * 根据工程id查询所有任务
	 * @return
	 */
	@RequestMapping("/task/queryByEpcEcode")
	@ResponseBody
	public YzlResult queryByEpcEcode(HttpServletRequest request,Integer Ecode,String year,String areaCode){
		YzlResult result = taskService.queryByEpcEcode(request,Ecode,year,areaCode);
		return result;
	}
	
	
	//根据工程id分页查询所有任务进度
	@RequestMapping("/progress/queryProgressByEpc")
	@ResponseBody
	public EasyUIResult pageQueryProgressByEpc(HttpServletRequest request,Integer ecode,@RequestParam(value="page",defaultValue="1")Integer page
			, @RequestParam(value="rows",defaultValue="10")Integer rows,String searchKey,String area,String year){
		//分页查询
		EasyUIResult result = taskService.pageQueryProgressByEpc(request,ecode,page,rows,searchKey,area,year);
		JSONObject fromObject = JSONObject.fromObject(result);
		System.out.println(fromObject);
		return result;
	}
	
	//分页查询所有任务进度
/*	@RequestMapping("/progress/pageQuery")
	@ResponseBody
	public EasyUIResult pageQuery(HttpServletRequest request,@RequestParam(value="page",defaultValue="1")int page
				, @RequestParam(value="rows",defaultValue="10")int rows,String area,String year,String searchKey){
		//分页查询
		EasyUIResult result = taskService.pageQuery(request,page, rows,searchKey,area,year);
		return result;
	}*/
	
	
	/***
	 * 添加任务进度
	 * @return
	 */
	@RequestMapping("/add/taskProgress")
	@ResponseBody
	public YzlResult addTaskProgress(HttpServletRequest request,@RequestParam("epcode")String epcId,@RequestParam("params")List<String> params ){
		Map<String,String> paramMap = new HashMap<>();
		//把集合相邻的两个元素并成K,V对
		for (String str : params) {
			String[] Strs = str.split("=");
			if(Strs.length==2){
				paramMap.put(Strs[0], Strs[1]);
			}
		}
		taskService.addTaskProgress(request,epcId,paramMap);
		return YzlResult.ok();
	}
	
	/***
	 * 修改任务进度
	 * @return
	 */
	@RequestMapping("/update/taskProgress")
	@ResponseBody
	public YzlResult updateTaskProgress(HttpServletRequest request,@RequestParam("params")List<String> params){
		Map<String,String> paramMap = new HashMap<>();
		//把集合相邻的两个元素并成K,V对
		for (String str : params) {
			String[] Strs = str.split("=");
			if(Strs.length==2){
				paramMap.put(Strs[0], Strs[1]);
			}
		}
		taskService.updateTaskProgress(request, paramMap);
		return YzlResult.ok();
	}
	
	@RequestMapping("/index/test")
	public String testData(@RequestParam("year")String year,Model model){
		//分页查询
		//EasyUIResult result = taskService.pageQuery(1, 10, 1);
		//Object json = JSONArray.toJSON(result);
		//model.addAttribute("pageQuery",json);
		return "2019year";
	}
	
	
	@RequestMapping("/search/taskProgress")
	@ResponseBody
	public EasyUIResult searchKey(HttpServletRequest request,@RequestParam("searchKey")String searchKey,@RequestParam("page")Integer page
				,@RequestParam("rows")Integer rows){
		EasyUIResult result = taskService.searchKey(request,searchKey,page,rows);
		return result;
	}
	
	/***
	 * 根据工程id查询所有任务
	 * @return
	 */
	@RequestMapping("/task/queryByEcode")
	@ResponseBody
	public YzlResult queryByEcpde(HttpServletRequest request,Integer Ecode,String year,String disCode){
		YzlResult result = taskService.queryByXbGCLB(request, Ecode,year,disCode);
		return result;
	}
	
//	@CacheEvict(value = "taskWork",allEntries=true)
//	@Caching(evict= {@CacheEvict(value = "taskWork",key = "#disCode+*",beforeInvocation=true) })
	@RedisCache(type="taksWorkdel")
	@LogAnno(opreateType="提交")
	//提交
	@RequestMapping("/submit")
	@ResponseBody
	public YzlResult sub(String[]  subData,String disCode,String usr,String subDatas,String time,String lea,String zllb,String [] countys,String [] fileNames,HttpServletRequest request) {
		if (subDatas!=null) {
			subData = null;
			subData =new String[1];
			subData[0] = subDatas;
		}
		YzlResult sub = taskService.sub(subData,time,zllb,countys);
		messageService.getRecordsByUserId(request);
		return sub;
	}

	//退回
//	@CacheEvict(value = "taskWork",allEntries=true)
//	@Caching(evict= {@CacheEvict(value="taskWork",key = "#disCode+*",beforeInvocation=true)})
	@RedisCache(type="taksWorkdel")
	@LogAnno(opreateType="退回")
	@RequestMapping("/backData")
	@ResponseBody
	public YzlResult back( String[] backData,String disCode,String usr,String backDatas,String time,String lea,String [] fileNames,String [] countys,String zllb,HttpServletRequest request) {
		if (backDatas!=null) {
			backData = null;
			backData =new String [1];
			backData[0] = backDatas;
		}
		YzlResult back = taskService.back(backData,time,zllb,countys);
		messageService.getRecordsByUserId(request);
		return back;
	}
	
	@RedisCache(type="taksWorkdel")
	@LogAnno(opreateType="审核")
	//审核
	@RequestMapping("/auditData")
	@ResponseBody
	public YzlResult audit( String[] auditData,String disCode,String auditDatas,String time,String zllb,String [] countys,String lea,String [] fileNames,HttpServletRequest request) {
		if (auditDatas!=null) {
			auditData = null;
			auditData =new String [1];
			auditData[0] = auditDatas;
		}
		YzlResult yzlResult = taskService.audit(auditData,time,zllb,countys);
		messageService.getRecordsByUserId(request);
		return yzlResult;
	}
	
	//选择工程
	@RequestMapping("/ChangedEpc")
	@ResponseBody
	public List<YzlTask> ChangedEpc(String time,String code,String epc) {
		return taskService.ChangedEpc(time,code,epc);
	}
	
	//
	@RequestMapping("/epcData")
	@ResponseBody
	public EasyUIResult epcData(String time,String code,String epc,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="10")Integer rows) {
		return taskService.epcData(time,code,epc,page,rows);
	}
	
	
	//与世隔绝 ----------------------------------------------------------------
	@RequestMapping("/task/getTableHeader")
	@ResponseBody
	public List<YzlTask> getTableHeader(String year,String disCode,String GCLB){
		List<YzlTask> result = taskService.getTableHeader(year, disCode,GCLB);
		return result;
	}
	

	
}
