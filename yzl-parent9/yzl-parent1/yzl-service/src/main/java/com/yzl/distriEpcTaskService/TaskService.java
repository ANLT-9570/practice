package com.yzl.distriEpcTaskService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.yzl.pojo.YzlEpcTaskProgress;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlXb;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;

public interface TaskService {

	//分页查询
	EasyUIResult findByAll(String value,Integer page,Integer rows);
	//根据任务名
	YzlTask findByName(String name);
	//添加
	YzlResult addTask(YzlTask task);
	//批量删除
	YzlResult deleterTask(String [] ids);
	//根据工程id查询所有任务  当前方式适配于任务发布管理
	YzlResult queryByEpcEcode(HttpServletRequest request,int ecode,String year,String areaCode);
	
	EasyUIResult pageQueryProgressByEpc(HttpServletRequest request,Integer ecode, int page, int rows, String value,String area,String year);
	//查询所有任务
	YzlResult findAll();
	//查询所有任务，根据权限判断
	List<YzlTask> queryAllByPerms(String year,String areaCode);
	//添加任务进度
	YzlResult addTaskProgress(HttpServletRequest request,String epcId,Map<String, String> params);
	//修改发布的任务
	YzlResult updateTaskProgress(HttpServletRequest request, Map<String, String> params);
	//删除已发布的任务
	YzlResult deleteByEcodeAndCreateTime(List<YzlEpcTaskProgress> params);
	//搜索
	EasyUIResult searchKey(HttpServletRequest request,String searchKey,Integer page,Integer rows);
	//根据工程id查询所有任务  当前方式适配于任务进度管理
	YzlResult queryByXbGCLB(HttpServletRequest request,int ecode,String year,String disCode);
	
	YzlResult sub(String[] subData,String time,String zllb,String [] countys);
	
	YzlResult back(String [] backData,String time,String zllb,String [] countys);
	
	List<YzlTask> ChangedEpc(String time, String code, String epc);
	
	EasyUIResult epcData(String time, String code, String epc,Integer page,Integer rows);
	
	//审核
	YzlResult audit(String[] auditData,String time,String zllb,String [] countys);
	List<List<YzlXb>> checkOut(String eids,String year,String [] countys);

	
	//-----------------与世隔绝
	List<YzlTask> getTableHeader(String year,String disCode,String GCLB);
}
