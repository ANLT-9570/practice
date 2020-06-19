package com.yzl.distriEpcTaskService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yzl.pojo.YzlTask;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;

public interface TaskIssuedService {
	
	//通过year、GCLB分页查询下发的任务并通过县级地区编号，作业年度，工程类别，造林类别进行分组
	EasyUIResult queryTaskIssuedByAreaCodeAndYearGroupCountyAndGCLBAndZLLBAndAreaCodeAndGCLB(Integer page, Integer rows, HttpServletRequest request, String areaCode, String year,String GCLB);

	//通过area和year、GCLB分页查询下发的任务并通过县级地区编号，作业年度，工程类别，造林类别进行分组
	EasyUIResult queryTaskIssuedByYearGroupCountyAndGCLBAndZLLBAndAreaCodeAndGCLB(HttpServletRequest request,int page,int rows,String year,String GCLB);

	YzlResult add(HttpServletRequest request, List<String> params);

	YzlResult update(HttpServletRequest request, List<String> params);

	YzlResult delete(String[] paramList,String year,HttpServletRequest request);

	YzlResult derive(HttpServletResponse response,String year,String click,String areaCode,String ZLLB,String GCLB) throws IOException;

	String toLead(MultipartFile[] excelName);
	
	EasyUIResult  queryTaskData(Integer page,Integer rows, String year,String areaCode,String ZLLB,String GCLB);
	//添加数据
	YzlResult addData(List<String> taskNumbers, String year, String countyCode);
	//更新数据
	YzlResult updateData(List<String> taskNumbers, String year, String countyCode, String uploadFiles);
	//上传文件
	Map uploadFile(MultipartFile[] files,HttpServletResponse response,HttpServletRequest request);
	//删除已上传的文件
	YzlResult delete_uploadFileService(String folderName, String countyCode, String year, String uploadFiles, String cityName,HttpServletRequest request);
	//下载文件
	YzlResult downloadUploadFile(String fileUrl,HttpServletRequest request,HttpServletResponse response);
	//更新数据库文件
	YzlResult updateUploadFileData(String year, String countyCode, String uploadFiles, String cityName);

	String toLeadDataDr(MultipartFile[] excelName);

}
