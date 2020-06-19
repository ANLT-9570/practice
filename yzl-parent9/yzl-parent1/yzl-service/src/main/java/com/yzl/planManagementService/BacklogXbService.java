package com.yzl.planManagementService;

import java.util.List;

import com.yzl.pojo.YzlTask;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.vo.BacklogVO;

public interface BacklogXbService {


	List<YzlTask> getTableHeader(String year, String disCode, String gCLB, String zLLB, String statu);

	EasyUIResult queryTaskData(int page, int rows, String year, String areaCode, String zLLB, String gCLB, String statu);

	
	
}
