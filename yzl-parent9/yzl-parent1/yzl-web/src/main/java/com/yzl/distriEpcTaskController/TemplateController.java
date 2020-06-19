package com.yzl.distriEpcTaskController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.yzl.distriEpcTaskService.TemplateService;
import com.yzl.pojo.YzlTask;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;

@Controller
public class TemplateController {

	@Autowired
	private TemplateService templateService;
	
	//查询所有
	@RequestMapping("/show_template")
	@ResponseBody
	public EasyUIResult findAll(String value,Integer page,Integer rows) {
		//easyui分页需要的json格式{"total":"111","rows":[{},{},{},{}]}
		EasyUIResult result = templateService.findAll(value,page,rows);
		PageHelper.startPage(page, rows);
		
		//总记录数
		return result;
	}
	
	//添加
	@RequestMapping("/addTemplate")
	@ResponseBody
	public YzlResult addTemplate(String eid,String [] tids) {
			YzlResult result = templateService.addTemplate(eid,tids);
			return result;
	}
	//删除
	@RequestMapping("/deleterTemplate")
	@ResponseBody
	public YzlResult deleterTemplate(String [] etdis) {
			YzlResult result = templateService.deleterTemplate(etdis);
			return result;
	}
	
	//修改
	@RequestMapping("/updateTemplate")
	@ResponseBody
	public YzlResult updateTemplate(String edname,String [] ids) {
		try {
			YzlResult result = templateService.updateTemplate(edname,ids);
			return result;
		} catch (Exception e) {
			return YzlResult.ok(400);
		}
	}
	
	//展示所有任务
	@RequestMapping("/show_tasks")
	@ResponseBody
	public List<YzlTask> showTask(){
		return templateService.showTask();
	}
}
