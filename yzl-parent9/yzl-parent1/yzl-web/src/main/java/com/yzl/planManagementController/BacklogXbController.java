package com.yzl.planManagementController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzl.planManagementService.BacklogTaskIssuedService;
import com.yzl.planManagementService.BacklogXbService;
import com.yzl.pojo.YzlTask;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.enums.TaskWorkSatusEnum;
import com.yzl.utils.vo.BacklogVO;

@RequestMapping("/backlogXb")
@Controller
public class BacklogXbController {

	@Autowired
	private BacklogXbService backlogXbService;
	
	
	//查询表头
	@RequestMapping("/getTableHeader")
	@ResponseBody
	public List<YzlTask> getTableHeader(String year,String disCode,String GCLB,String ZLLB,String statu){
		List<YzlTask> result = backlogXbService.getTableHeader(year, disCode,GCLB,ZLLB,statu);
		return result;
	}
	
	
	//查询数据
	@RequestMapping("/queryTaskData")
	@ResponseBody
	public EasyUIResult queryTaskData(@RequestParam(value="page",defaultValue="1")int page,@RequestParam(value="rows",defaultValue="10")int rows
			,String year,String disCode,String ZLLB,String usr,String GCLB,@RequestParam(value="statu",defaultValue="3")String statu){
		if ("GX450".equals(disCode)) {
			disCode = "45";
		}
		EasyUIResult result = backlogXbService.queryTaskData(page, rows, year, disCode,ZLLB,GCLB,statu);
		return result;
	}
}