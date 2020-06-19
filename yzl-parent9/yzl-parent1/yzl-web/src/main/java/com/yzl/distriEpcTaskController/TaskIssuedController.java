package com.yzl.distriEpcTaskController;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.yzl.LogService.RedisCache;
import com.yzl.distriEpcTaskService.TaskIssuedService;
import com.yzl.distriEpcTaskService.TaskService;
import com.yzl.planManagementService.MessageService;
import com.yzl.pojo.YzlUser;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.LoginUserUtils;
import com.yzl.utils.YzlResult;

/***
 * 任务发布数据 Controller
 * @author administrator_
 *
 */
@Controller
public class TaskIssuedController {

	@Autowired
	private TaskIssuedService taskIssuedService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private MessageService messageService;
	
	//通过year分页查询下发的任务并通过县级地区编号，作业年度，工程类别，造林类别进行分组
	@RequestMapping("/taskIssued/pageQueryByAreaCodeAndGCLBAndYear")
	@ResponseBody
	public EasyUIResult  pageQueryByAreaCodeAndGCLB(@RequestParam(value="page",defaultValue="1")Integer page,@RequestParam(value="rows",defaultValue="10")Integer rows
			,HttpServletRequest request,String areaCode,String year,String GCLB){
	 	 EasyUIResult result = taskIssuedService.queryTaskIssuedByAreaCodeAndYearGroupCountyAndGCLBAndZLLBAndAreaCodeAndGCLB(page,rows,request,areaCode,year,GCLB);
	 	 return result;
	}

	//通过area、year、GCLB分页查询下发的任务并通过县级地区编号，作业年度，工程类别，造林类别进行分组
	@RequestMapping("/taskIssued/pageQueryByYear")
	@ResponseBody
	public EasyUIResult pageQueryByAreaCodeAndYearAndGCLB(HttpServletRequest request,@RequestParam(value="page",defaultValue="1")int page
				, @RequestParam(value="rows",defaultValue="10")int rows,String year,String GCLB){//TODO
		//分页查询
		EasyUIResult result = taskIssuedService.queryTaskIssuedByYearGroupCountyAndGCLBAndZLLBAndAreaCodeAndGCLB(request,page, rows,year, GCLB);
		return result;
	}
	
	//添加发布任务
	@RedisCache(type="delIsuueFile")
	@RequestMapping("/add/taskIssued")
	@ResponseBody
	public YzlResult add(HttpServletRequest request,@RequestParam("params")List<String> params){
		YzlResult result = taskIssuedService.add(request,params);
		return result;
	}
	
	//修改发布任务
	@RedisCache(type="delIsuueFile")
	@RequestMapping("/update/taskIssued")
	@ResponseBody
	public YzlResult update(HttpServletRequest request,@RequestParam("params")List<String> params){
		YzlResult result = taskIssuedService.update(request,params);
		return result;
	}
	
	//删除发布的任务
//	@CacheEvict(value="TaskIssuedWork",allEntries=true)
	@RedisCache(type="taksWorkdelIsuued")
	@RequestMapping("/delete/taskIssued")
	@ResponseBody
	public YzlResult delete(String[] params,String param,String year,HttpServletRequest request){
		//如果param不为空，则前台直传了一条数据
		if(!StringUtils.isBlank(param)){
			params=new String[1];
			//把要删除的数据添加到数组中
			params[0]=param;
		}
		return taskIssuedService.delete(params,year,request);
	}

	

	//导出
	@RequestMapping("/derive")
	@ResponseBody
	public YzlResult derive(HttpServletResponse response,String year,String click,String areaCode,String ZLLB,String GCLB) throws IOException {
		return taskIssuedService.derive(response,year,click,areaCode,ZLLB,GCLB);
	}
	
	//导入/TaskIssued/toLead/dataDr
//	@CacheEvict(value="TaskIssuedWork",allEntries=true)
	@RedisCache(type="delIsuueFile")
	@RequestMapping("/TaskIssued/toLead")
	@ResponseBody
	public String toLead(@RequestParam("excelNameing")MultipartFile[] excelName,HttpServletRequest request) {
		
		String lead = taskIssuedService.toLead(excelName);
		
		messageService.getRecordsByUserId(request);
		return lead;
	}
	
//	@CacheEvict(value="TaskIssuedWork",allEntries=true)
	@RedisCache(type="delIsuueFile")
	@RequestMapping("/TaskIssued/toLead/dataDr")
	@ResponseBody
	public String toLeadDataDr(@RequestParam("excelName")MultipartFile[] excelName) {
		return taskIssuedService.toLeadDataDr(excelName);
	}
	
	
	/***
	 * 文件上传
	 * @param uploadFile
	 * @param response
	 * @param request
	 * @return
	 */
//	@CacheEvict(value="TaskIssuedWork",allEntries=true)
	@RedisCache(type="delIsuueFile")
	@RequestMapping("/taskIssued/file/upload")
	@ResponseBody
	public Map uploadFile(@RequestParam("uploadFile")MultipartFile[] uploadFile,HttpServletResponse response,HttpServletRequest request) {
		for (MultipartFile multipartFile : uploadFile) {
			System.out.println("multipartFile="+multipartFile.getName());
			System.out.println("getOriginalFilename="+multipartFile.getOriginalFilename());
		}
		System.out.println("uploadFile="+uploadFile.length);
		System.out.println("----------------");
		Map result = taskIssuedService.uploadFile(uploadFile,response,request);
		return result;
	}
	
	/**
	 * 删除已上传的文件
	 */
//	@CacheEvict(value="TaskIssuedWork",allEntries=true)delIsuueFile
	@RedisCache(type="delIsuueFile")
	@RequestMapping("/taskIssued/delete/uploadFile")
	@ResponseBody
	public YzlResult delete_uploadFile( @RequestParam(value="deleteFileName",required=false)String deleteFileName,@RequestParam(value="countyCode",required=false)String countyCode
			,@RequestParam(value="year",required=false)String year,@RequestParam(value="uploadFiles",required=false)String uploadFiles,@RequestParam(value="cityName",required=false,defaultValue="")String cityName,HttpServletRequest request) {
		YzlResult result = taskIssuedService.delete_uploadFileService(deleteFileName,countyCode,year,uploadFiles,cityName,request);
		return result;
	}
	
	/***
	 * 页面查询任务数据
	 * @param request
	 * @param page
	 * @param rows
	 * @param year
	 * @param areaCode
	 * @param ZLLB
	 * @param ZLLB
	 * @return
	 */
	@RedisCache(type="taskIssued")
	@RequestMapping("/taskIssued/queryTaskData")
	@ResponseBody
	public EasyUIResult queryTaskData(HttpServletRequest request,@RequestParam(value="page",defaultValue="1")int page
			,@RequestParam(value="rows",defaultValue="10")int rows,String year,String areaCode,String ZLLB,String usr,@RequestParam("GCLB")String GCLB){
		EasyUIResult result = taskIssuedService.queryTaskData(page, rows, year, areaCode,ZLLB,GCLB);
		return result;
	}
	
	/***
	 *  任务添加
	 * @param taskNumbers "jh"+任务类别+"Y"+工程类别+":"+任务下发的基数  拼接而成 
	 * @param year        //作业年度 和 计划年度
	 * @param countyCode   //县级行政编号
	 * @param uploadFileUrlList //上传文件的文件名 集合
	 */
//	@CacheEvict(value="TaskIssuedWork",allEntries=true)
	@RedisCache(type="taksWorkdel")///,String disCode,String usr
	@RequestMapping("/taskIssued/add")
	@ResponseBody
	public YzlResult addData(@RequestParam("taskNumbers")List<String> taskNumbers,String disCode,String usr,String year,String countyCode,HttpServletRequest request){
//		System.out.println("taskNumbersw="+taskNumbers);
//		System.out.println("taskNumbers="+taskNumbers.size());
		YzlResult result = taskIssuedService.addData(taskNumbers,year,countyCode);
		messageService.getRecordsByUserId(request);
		return result;
	}
	
	/***
	 * 任务更新
	 * @param taskNumbers "jh"+任务类别+"Y"+工程类别+":"+任务下发的基数  拼接而成 
	 * @param year			//作业年度 和 计划年度
	 * @param countyCode   县级行政编号
	 * @param uploadFiles   上传文件的文件名 集合
	 * @return
	 */
//	@CacheEvict(value="TaskIssuedWork",allEntries=true)
	@RedisCache(type="taksWorkdelIsuuedUpdate")
	@RequestMapping("/taskIssued/update")
	@ResponseBody
	public YzlResult updateData(@RequestParam("taskNumbers")List<String> taskNumbers,String year,String countyCode,String uploadFiles,HttpServletRequest request){
		YzlResult result = taskIssuedService.updateData(taskNumbers,year,countyCode,uploadFiles);
		messageService.getRecordsByUserId(request);
		return result;
	}
	
	
	/**
	 * 文件下载
	 * @param fileUrl
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/taskIssued/download/uploadFile")
	@ResponseBody
	public YzlResult downloadUploadFile(@RequestParam("fileUrl")String fileUrl,HttpServletRequest request,HttpServletResponse response){
		try {
			String dowloadFileUrl = new String(fileUrl.getBytes("iso-8859-1"), "utf-8");
			taskIssuedService.downloadUploadFile(fileUrl,request,response);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return YzlResult.ok();
	}
	/***
	 * 更新上传的文件
	 * @param year			//作业年度 和 计划年度
	 * @param countyCode   县级行政编号
	 * @param uploadFiles   上传文件的文件名 集合
	 * @return
	 */
	@RequestMapping("/taskIssued/fileUpload/update")
	@ResponseBody
	public YzlResult updateFileUpload(String year,String countyCode,String uploadFiles){
		System.out.println("casa");
		System.out.println("year="+year+" countyCode="+countyCode +"  uploadFiles="+uploadFiles);
		return taskIssuedService.updateUploadFileData(year,countyCode,uploadFiles,null);
	}
	
	/***
	 * 删除数据库已上传的文件
	 * @param year
	 * @param countyCode
	 * @param uploadFiles
	 * @return
	 */
/*	@RequestMapping("/taskIssued/fileUpload/delete")
	@ResponseBody
	public YzlResult deleteFileUpload(String year,String countyCode,String uploadFiles){
		System.out.println("delete year="+year+" countyCode="+countyCode +"  uploadFiles="+uploadFiles);
		return taskIssuedService.updateUploadFileData(year,countyCode,uploadFiles,null);
	}*/
	

	
}
