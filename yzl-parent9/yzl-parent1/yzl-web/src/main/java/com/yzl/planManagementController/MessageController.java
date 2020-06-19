package com.yzl.planManagementController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.yzl.planManagementService.MessageService;
import com.yzl.utils.YzlResult;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;


@Controller
public class MessageController {

	@Autowired
	private MessageService messageService;
	
	/***
	 * 信息发送
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/message/queryByRoleId")
	@ResponseBody
	public Integer  getRecords(HttpServletRequest request){
		int count = messageService.getRecordsByUserId(request);//根据当前登录用户获取 已更新的数据
		//request.getSession().setAttribute("updateDataNumber", count);
		return count;
	}
	
	
	@RequestMapping("/message/read")
	@ResponseBody
	public YzlResult read(HttpServletRequest request){
		request.getSession().setAttribute("updateDataNumber", 0);
		return messageService.updateStatuByUserId();
	}
	
/*	@RequestMapping(value="/pic/upload")
	@ResponseBody
	public String upload(MultipartFile uploadFile,String dir){
		System.out.println("upload="+uploadFile);
		Map map = new HashMap<>();
		String url="cc/sss.jpg";
		map.put("error", 0);
		map.put("url", url);
		return JSONUtils.valueToString(map);
		
	}*/

}
