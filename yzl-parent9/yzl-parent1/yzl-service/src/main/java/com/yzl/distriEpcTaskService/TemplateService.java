package com.yzl.distriEpcTaskService;

import java.util.List;

import com.yzl.pojo.YzlTask;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.YzlResult;

public interface TemplateService {

	//查询所有
	EasyUIResult findAll(String value,Integer page,Integer rows);
	//添加
	YzlResult addTemplate(String eid,String [] tids);
	//删除
	YzlResult deleterTemplate(String[] etdis);
	//修改
	YzlResult updateTemplate(String edname, String[] ids);
	//总记录数
//	Integer getTotalCount();
	//所有任务
	List<YzlTask> showTask();
}
