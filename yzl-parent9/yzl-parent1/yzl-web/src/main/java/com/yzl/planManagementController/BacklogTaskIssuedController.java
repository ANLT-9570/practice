package com.yzl.planManagementController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzl.planManagementService.BacklogTaskIssuedService;
import com.yzl.pojo.YzlEpc;
import com.yzl.pojo.YzlTask;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.vo.BacklogVO;


@RequestMapping("/backlogTaskIssued")
@Controller
public class BacklogTaskIssuedController {

	@Autowired
	private BacklogTaskIssuedService backlogService;
	
	
	@RequestMapping("/pageQuery")
	@ResponseBody
	public EasyUIResult pageQuery(int page,int rows){
		return backlogService.pageQuery(page,rows);
		
	}
	
	//查询表头
	@RequestMapping("/getTableHeader")
	@ResponseBody
	public List<YzlTask> getTableHeader(String year,String disCode,String GCLB,String ZLLB){
		List<YzlTask> result = backlogService.getTableHeader(year, disCode,GCLB,ZLLB);
		return result;
	}
	
	
	//查询数据
	@RequestMapping("/queryTaskData")
	@ResponseBody
	public EasyUIResult queryTaskData(@RequestParam(value="page",defaultValue="1")int page,@RequestParam(value="rows",defaultValue="10")int rows
			,String year,String areaCode,String ZLLB,String usr,@RequestParam("GCLB")String GCLB){
		EasyUIResult result = backlogService.queryTaskData(page, rows, year, areaCode,ZLLB,GCLB);
		return result;
	}
}
