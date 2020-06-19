package com.yzl.planManagementController;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yzl.LogService.RedisCache;
import com.yzl.planManagementService.MonitoringstatisticsService;
import com.yzl.pojo.YzlDistrict;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlTask;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;
import com.yzl.utils.trees;

@Controller
public class MonitoringstatisticsController {

	@Autowired
	private MonitoringstatisticsService monitoringstatisticsService;
	
	@RequestMapping("/show_ms")
	@ResponseBody
	public EasyUIResult show(Integer page, Integer rows,String did,String epcode,String tpcode,String time) {
		return monitoringstatisticsService.show(page,rows,did,epcode,tpcode,time);
	}
	
	//根据工程id所用有任务
	@RequestMapping("/findByEcode")
	@ResponseBody
	public List<YzlTask> findByEcode(String epcode){
		return monitoringstatisticsService.findByEcode(epcode);
	}
	
	@RequestMapping("/sel_diss")
	@ResponseBody
	public List<YzlDistrict> showDis(){
		return monitoringstatisticsService.sel();
	}
	
	//根据县查询工程
	@RequestMapping("/findByCity")
	@ResponseBody
	public List<YzlEpc> findByCity(String county){
		return monitoringstatisticsService.findByCity(county);
	}
	
	//根据工程查询拥有的工程字段
	@RequestMapping("/findByEcodeAndDname")
	@ResponseBody
	public List<YzlTask> findByEcodeAndDname(String ecode,String county){
		return monitoringstatisticsService.findByEcodeAndDname(ecode,county);
	}
	
	@RequestMapping("/selectByTEC")
	@ResponseBody//ecode 工程编号  county 县  tname 字段名
	public EasyUIResult selectByTEC(String ecode,String county,String tname,Integer rows,Integer page,String time){
		/*if (time.equals("0")) {
			return null;
		}*/
		return monitoringstatisticsService.findByTEC(ecode,county,tname,rows,page,time);
	}
	
	//ztree树的初始化
	@RequestMapping("/show_dis")
	@ResponseBody
	public List<trees> strees(){
//		int i = 1/0;
		return monitoringstatisticsService.show_trees();
	}
	
	/**
	 * 自治区
	 * @param page
	 * @param rows
	 * @return
	 */
//	@Cacheable(value = "moniCache",key = "#usr+':'+#page+':'+#stime+':'+#sname+':'+#rows")
	@RedisCache(type="monitMunicipality")
	@RequestMapping("/show_xbMunicipality")
	@ResponseBody
	public EasyUIResult monitMunicipality(String stime,String sname,String time,Integer page,Integer rows,String usr,String cityCode,String zllb,String gclb) {
		return monitoringstatisticsService.monitMunicipality(sname,time,stime,page,rows,cityCode,zllb,gclb);
	}
	
	/**
	 * 市
	 */
//	@Cacheable(value = "cityCache",key = "#flag+':'+#usr+':'+#page+':'+#stime+':'+#sname+':'+#rows")
	@RedisCache(type="monitCity")
	@RequestMapping("/show_xbCity")
	@ResponseBody
	public EasyUIResult monitCity(String flag,Integer page,Integer rows,String sname,String stime,String time,String usr,String cityCode,String zllb,String gclb){
		return monitoringstatisticsService.moniCity(flag,page,rows,sname,stime,time,cityCode,zllb,gclb);
	}
	
	/**
	 * 县
	 * @return
	 */
//	@Cacheable(value = "countyCache",key = "#dcode+':'+#usr+':'+#page+':'+#stime+':'+#sname+':'+#rows")
	@RedisCache(type="monitCounty")
	@RequestMapping("/show_xbCounty")
	@ResponseBody
	public EasyUIResult monitCounty(String dcode,Integer page,Integer rows,String sname,String stime,String time,String usr,String zllb,String gclb) {
		return monitoringstatisticsService.monitCounty(dcode,page,rows,sname,stime,time,zllb,gclb);
	}
	
	//导出
	@RequestMapping("/deriveMunicipality")
	@ResponseBody
	public String deriveMunicipality(HttpServletResponse response,String discod,String sname,String stime,String puptime,String qccty) throws IOException {
		monitoringstatisticsService.deriveMunicipality(response,discod,sname,stime,puptime,qccty);
		//return monitoringstatisticsService.deriveMunicipality();
		return "monitoringstatistics";
	}
	
	@RequestMapping("/zllb")
	@ResponseBody
	public List<YzlTask> zllb(){
		return monitoringstatisticsService.zllb();
	}
	
	@RequestMapping("/gclb")
	@ResponseBody
	public List<YzlEpc> gclb(){
		return monitoringstatisticsService.gclb();
	}
	

	//任务导入
	@RequestMapping("/dr")
	@ResponseBody
	public YzlResult TaskDr(@RequestParam("exceName")MultipartFile[] exceName) throws IOException {
		return monitoringstatisticsService.taskDr(exceName);
	}
	
	//查询所有的市
	@RequestMapping("/monitoringstatistics/AllCity")
	@ResponseBody
	public List<YzlDistrict> AllCity(){
		return monitoringstatisticsService.AllCity();
	}
	
	///monitoringstatistics/Allgclb
	@RequestMapping("/monitoringstatistics/Allgclb")
	@ResponseBody
	public List<YzlEpc> AllEpc(){
		return monitoringstatisticsService.AllEpc();
	}
	
//	/monitoringstatistics/AllCounty
	@RequestMapping("/monitoringstatistics/AllCounty")
	@ResponseBody
	public List<YzlDistrict> AllCounty(String cityCode){
		return  monitoringstatisticsService.AllCounty(cityCode);
	}
}
