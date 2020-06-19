package com.yzl.distriEpcTaskService;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;
import com.yzl.utils.vo.HighchartsResultVO;

public interface TaskProgressSheetService {
	
	//分页查询所有任务进度
	EasyUIResult pageQuery(HttpServletRequest request,int page,int rows , String searchKey,String area,String year);
	
	//分页查询 根据工程id
	public EasyUIResult pageQueryByEcode(HttpServletRequest request ,Integer page, Integer rows,Integer Ecode ,String area,String year,String searchKey);
	//更新任务进度\
	YzlResult updateTaskProgressSheet(HttpServletRequest request, Map<String, String> params);
	//提交任务
	YzlResult submitTaskProgressSheet(HttpServletRequest request, Map<String, String> params);

	YzlResult sendBack(HttpServletRequest request, Map<String, String> params);

	YzlResult approve(HttpServletRequest request, Map<String, String> params);
	
	//分页查询 根据工程id
	public EasyUIResult pageQueryFinishTaskByEcode(HttpServletRequest request,Integer page, Integer rows,Integer Ecode,String searchKey);
	//分页查询已完成的任务
	EasyUIResult pageQueryFinishTask(HttpServletRequest request,Integer page, Integer rows,String searchKey);

	 List<HighchartsResultVO> queryTaskGroupByCity(HttpServletRequest request); 
}
