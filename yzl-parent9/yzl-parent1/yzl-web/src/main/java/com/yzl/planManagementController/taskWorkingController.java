package com.yzl.planManagementController;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yzl.LogService.LogAnno;
import com.yzl.LogService.RedisCache;
import com.yzl.planManagementService.TaskWorkingService;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlProceed;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlUser;
import com.yzl.pojo.YzlXb;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.YzlResult;

@Controller
public class taskWorkingController {

	@Autowired
	private TaskWorkingService taskWorkingService;
	
	//查询表头
	@RequestMapping("/takWorking/taskTab")
	@ResponseBody
	public List<YzlTask> taskTab(String year,String disCode,String zllb){
		YzlUser user = LoginUserUtils.getLoginUser();
		return taskWorkingService.taskTab(year,disCode,zllb,null);
	}
	
	//数据展示

//	@Cacheable(value = "taskWork",key = "#disCode+':'+#zllb+':'+#page+':'+#rows+':'+#usr")//key = "#disCode+':'+#uuid",,keyGenerator = "SpringCacheKeyGenret"
	@RedisCache(type = "taksWorkadd")
	@RequestMapping(value="/takWorking/epcTaskData",produces="application/json;charset=utf-8")  //String year,String disCode,String zllb,Integer page,Integer rows,String usr
	@ResponseBody								//String year,String disCode,String usr,String zllb,Integer page,Integer rows
	public EasyUIResult epcTaskData(String year,String disCode,String usr,String zllb,Integer page,Integer rows,String stat,String proceed) {
		return taskWorkingService.epcTaskData(year,disCode,zllb,page,rows,stat,proceed);
	}
	
	@RequestMapping("/takWorking/Ddis")
	@ResponseBody
	public YzlDistrict Ddis(String dcode) {
		return taskWorkingService.Ddis(dcode);
	}
	
	@RequestMapping("/takWorking/CityFlag")
	@ResponseBody
	public YzlDistrict cityFlag(String flag) {
		return taskWorkingService.findByFlag(flag);
	}
	
	//查询所有的造林类别
	@RequestMapping("/taskWorking/show_tasks")
	@ResponseBody
	public List<YzlTask> show_task(){
		return taskWorkingService.show_task();
	}
	
	//根据造林类别查询表头
	@RequestMapping("/taskWorking/taskTab")
	@ResponseBody
	public List<YzlTask> ZLLBFindTable(String year,String disCode,String zllb,String stat){
		return taskWorkingService.taskTab(year, disCode,zllb,stat);
	}
	
//	@LogAnno(opreateType="审核")
	//审核/taskWorking/checkout
	@RequestMapping("/taskWorking/checkout")
	@ResponseBody
	public List<List<YzlXb>> checkOut(String  eids,String disCode,String usr,String year,String [] countys,String [] json,String lea,String [] bdgwj){
		return taskWorkingService.checkOut(eids,year,countys,bdgwj,json);
	}
	
	/**
	 * 日志查询
	 * @param time
	 * @param row
	 * @return
	 */
	@RequestMapping("/takWorking/findLog")
	@ResponseBody				//String row,String time,String county,Integer page,Integer rows,String zllb
	public EasyUIResult findLog(String row,String time,String county,Integer page,Integer rows,String zllb) {
		//System.out.println("ccc");
		return taskWorkingService.findLog(row,time,county,page,rows,zllb);
	}
	
	/**
	 * 文件上传
	 * @param fileName
	 * @return
	 */
	@RequestMapping("/taskWorking/upFile")
	@ResponseBody
	public YzlResult upFile(@RequestParam("fileName")MultipartFile[] fileName,HttpServletRequest request) {
		return taskWorkingService.upFile(fileName,request);
	}
	
	/**
	 * 文件下载
	 * @param file
	 * @return
	 */
	@RequestMapping("/takWorking/deriveFile")
	@ResponseBody
	public YzlResult deriveFile(String file,HttpServletRequest request,HttpServletResponse response) {
		return taskWorkingService.takWorkingDeriveFile(file,request,response);
	}
	
	@RequestMapping("/takWorking/delFile")
	@ResponseBody
	public YzlResult delFile(String msg,HttpServletRequest request) {
		return taskWorkingService.delFile(msg,request);
	}
	
	//事项
	@RequestMapping("/takWorking/proceed")
	@ResponseBody
	public List<YzlProceed> proceed(String [] zllbs,String county,String year) {
		return taskWorkingService.proceed(zllbs,county,year);
	}
	
}
