package com.yzl.planManagementService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlProceed;
import com.yzl.pojo.YzlTask;
import com.yzl.pojo.YzlXb;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;

public interface TaskWorkingService {
	
	public List<YzlTask> taskTab(String year, String disCode,String zllb,String stat);

	public EasyUIResult epcTaskData(String year, String disCode,String zllb, Integer page, Integer rows,String stat,String proceed);

	public YzlDistrict Ddis(String dcode);

	public List<YzlTask> show_task();

	public List<List<YzlXb>> checkOut(String eids, String year, String[] countys,String [] bdgwj,String [] json);

	public EasyUIResult findLog(String row,String time,String county,Integer page,Integer rows,String zllb);

	public YzlResult upFile(MultipartFile[] fileName,HttpServletRequest request) ;

	public YzlResult takWorkingDeriveFile(String file,HttpServletRequest request,HttpServletResponse response);

	public YzlResult delFile(String msg,HttpServletRequest request);

	public YzlDistrict code(String disCode);

	public YzlDistrict findByFlag(String flag);

	public List<YzlProceed> proceed(String[] zllbs,String county,String year);

}
