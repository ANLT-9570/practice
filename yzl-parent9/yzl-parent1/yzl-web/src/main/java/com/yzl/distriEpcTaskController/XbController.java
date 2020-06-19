package com.yzl.distriEpcTaskController;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.fabric.xmlrpc.base.Data;
import com.yzl.distriEpcTaskService.XbService;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.ObjTOJson;
import com.yzl.utils.vo.HighchartsResultVO;


/***
 * 小班数据Controller
 * @author administrator_
 *
 */
@Controller
public class XbController {

	@Autowired
	private XbService xbService;
	
	//统计县级上报率 
	@RequestMapping(value="/xb/show/highcharts", produces={"text/json; charset=UTF-8"})
	@ResponseBody
	public String showHighcharts(HttpServletRequest request,String year){
		List<HighchartsResultVO> list = xbService.queryTaskGroupByCity(request,year);
		//转换成JSON
		String resultJson = ObjTOJson.listjToJson(list, new String[]{});
		return resultJson;
		
	}
	
	//统计县级上报率 
		@RequestMapping(value="/xb/show/pie", produces={"text/json; charset=UTF-8"})
		@ResponseBody
		public String showPie(HttpServletRequest request,String year){
			List<HighchartsResultVO> list = xbService.pie(request,year);
			//转换成JSON
			String resultJson = ObjTOJson.listjToJson(list, new String[]{});
			return resultJson;
		}
	
	//根据作业年度 统计县任务完成量
	@RequestMapping(value="/TaskWorking/pageQuery")
	@ResponseBody
	public EasyUIResult  pageQueryByAreaCodeAndYear(@RequestParam(value="page",defaultValue="1")Integer page,@RequestParam(value="rows",defaultValue="10")Integer rows,HttpServletRequest request,@RequestParam("year")String year){
		return xbService.queryTaskWorkingByGroupCountyAndGCLBAndZLLB(request,page,rows,year);
	}
	
	
	//根据作业年度和地区行政编号 统计县任务完成量
	@RequestMapping(value="/TaskWorking/pageQueryByAreaCode")
	@ResponseBody
	public EasyUIResult  pageQueryByYear(@RequestParam(value="page",defaultValue="1")Integer page,@RequestParam(value="rows",defaultValue="10")Integer rows
			,HttpServletRequest request,String areaCode,String year){
		return xbService.queryTaskWorkingByGroupCountyAndGCLBAndZLLBAndAreaCode(request,page,rows,areaCode,year);
	}
	
	
	@RequestMapping("/query/TaskWorkingBySearchKey")
	@ResponseBody
	public EasyUIResult searchKey(){
		return null;
	}
}
		
		
		
