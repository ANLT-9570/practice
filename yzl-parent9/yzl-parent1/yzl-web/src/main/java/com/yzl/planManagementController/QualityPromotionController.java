package com.yzl.planManagementController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yzl.planManagementService.QualityPromotionService;
import com.yzl.utils.EasyUIResult;

@Controller
public class QualityPromotionController {

	@Autowired
	private QualityPromotionService QualityPromotionService;
	
	@RequestMapping("/qualityPromotion/findall")
	@ResponseBody
	public EasyUIResult findAll(int page,int rows) {
		EasyUIResult result = QualityPromotionService.findAll(page,rows);
		return result;
	}
}
