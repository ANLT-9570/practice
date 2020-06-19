package com.yzl.distriEpcTaskController;

import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.yzl.distriEpcTaskService.EpcService;
import com.yzl.pojo.YzlEpc;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;
import com.yzl.utils.YzlTaskNumberEpcResult;

@Controller
public class EpcController {

	@Autowired
	private EpcService epcService;
	
	//分页查询所有工程
	@RequestMapping("/show_epc")
	@ResponseBody
	public EasyUIResult pageQuery(String value,Integer page,Integer rows){
		EasyUIResult result = epcService.pageQuery(value,page,rows);
		return result;
	}

	
	//分页查询所有工程
	@RequestMapping("/epc/queryAll")
	@ResponseBody
	public 	List<YzlEpc> findAll(String year,String areaCode){
		System.out.println("year="+year+" areaCode="+areaCode);
		return epcService.findAll(year,areaCode);
	}
	
	//工程名校验
	@RequestMapping("/verifyName")
	@ResponseBody
	public YzlEpc verifyName(String name) {
		YzlEpc epc = epcService.findByName(name);
		return epc;
	}
	
	//添加工程
	@RequestMapping("/addEpc")
	@ResponseBody
	public YzlResult addEpc(YzlEpc epc) {
		YzlResult result = epcService.addEpc(epc);
		return result;
	}
	
	//删除
	@RequestMapping("/deleteEpc")
	@ResponseBody
	public YzlResult deleteEpc(String [] ids) {
		try {
			YzlResult result = epcService.deleterEpc(ids);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return YzlResult.ok(400);
			//throw new RuntimeException("删除出错!!");
		}
	}
	
	/***
	 * 查询所有工程
	 * @return
	 */
	@RequestMapping("/epc/query")
	@ResponseBody
	public List<YzlEpc> queryByYearAndAreaCodeAndZLLBAndGCLB(String year,String disCode,String ZLLB,String GCLB){
		List<YzlEpc> epcs=null;
		try {
			epcs = epcService.queryByYearAndAreaCodeAndZLLBAndGCLB(year, disCode, ZLLB, GCLB);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return epcs;
	}
	
	/**
	 * 查询所有拥有任务的工程，工程拥有任务的数量
	 * @return
	 */
	@RequestMapping("/project_taskNumber/query")
	@ResponseBody
	public List<YzlTaskNumberEpcResult> queryEpcTaskNumber(String year,String areaCode){
		//如果年份为空则 取当前时间的年份
		if(StringUtils.isBlank(year)){
			year = new SimpleDateFormat("yyyy").format(new Date());
		}
		List<YzlTaskNumberEpcResult> taskNumberEpcResults = epcService.queryAllAndNumber(year,areaCode);
		return taskNumberEpcResults;
	}
	
	//查询所有工程
	@RequestMapping("/show_epcs")
	@ResponseBody
	public List<YzlEpc> show(){
		return epcService.show();
	}
	
	/**
	 * 通过小班完成的数据中查询相对于工程
	 * @return
	 */
	@RequestMapping("/Epc/queryByXbData")
	@ResponseBody
	public List<YzlTaskNumberEpcResult> queryByXbData(String year,String disCode){
		return epcService.queryByXbData(year,disCode);
	}
}
