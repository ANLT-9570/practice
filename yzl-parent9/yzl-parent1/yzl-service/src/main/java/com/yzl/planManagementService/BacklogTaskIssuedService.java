package com.yzl.planManagementService;

import java.util.List;

import com.yzl.pojo.YzlTask;
import com.yzl.utils.EasyUIResult;
import com.yzl.utils.vo.BacklogVO;

public interface BacklogTaskIssuedService {
	EasyUIResult pageQuery(int page,int rows);

	List<YzlTask> getTableHeader(String year, String disCode, String GCLB, String ZLLB);

	EasyUIResult queryTaskData(int page, int rows, String year, String areaCode, String ZLLB, String GCLB);

}
