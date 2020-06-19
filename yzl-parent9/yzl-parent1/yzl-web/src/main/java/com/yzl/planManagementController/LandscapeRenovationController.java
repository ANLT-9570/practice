package com.yzl.planManagementController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzl.planManagementService.LandscapeRenovationService;
import com.yzl.utils.EasyUIResult;

@Controller
public class LandscapeRenovationController {

	@Autowired
	private LandscapeRenovationService landscapeRenovationService;
	
	@RequestMapping("/LandscapeRenovation/findall")
	@ResponseBody
	public EasyUIResult findAll(int page,int rows) {
		EasyUIResult result = landscapeRenovationService.findAll(page,rows);
		return result;
	}
}
