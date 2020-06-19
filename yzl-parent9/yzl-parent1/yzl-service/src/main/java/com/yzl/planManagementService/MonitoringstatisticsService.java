package com.yzl.planManagementService;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlTask;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;
import com.yzl.utils.trees;

public interface MonitoringstatisticsService {

	EasyUIResult show(Integer page, Integer rows,String did,String epcode,String tpcode,String time);

	List<YzlTask> findByEcode(String epcode);

	List<YzlDistrict> sel();

	List<YzlEpc> findByCity(String county);

	List<YzlTask> findByEcodeAndDname(String ecode, String county);

	EasyUIResult findByTEC(String ecode,String county,String tname,Integer rows,Integer page,String time);
	
	String text(String name);

	List<trees> show_trees();

	//市
	EasyUIResult moniCity(String flag,Integer page,Integer rows,String sname,String stime,String time,String cityCode,String zllb,String gclb);

	//所有的造林类别
	List<YzlTask> zllb();

	//所有工程类别
	List<YzlEpc> gclb();

	//县
	EasyUIResult monitCounty(String dcode, Integer page, Integer rows,String sname,String stime,String time,String zllb,String gclb);

	//自治区
	EasyUIResult monitMunicipality(String sname,String time,String stime,Integer page, Integer rows,String cityCode,String zllb,String gcllb);

	YzlResult deriveMunicipality(HttpServletResponse response,String discod,String sname,String stime,String puptime,String qccty) throws IOException;

	YzlResult taskDr(MultipartFile[] exceName) throws IOException;

	List<YzlDistrict> AllCity();

	List<YzlEpc> AllEpc();

	List<YzlDistrict> AllCounty(String cityCode);

}
